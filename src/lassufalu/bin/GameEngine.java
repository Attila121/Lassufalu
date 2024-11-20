/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.time.Instant;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import static lassufalu.bin.GameGUI.t;
import lassufalu.bin.Stadium.STADIUMTYPE;


/**
 *
 * @author Mielec Attila (MNZVUM)
 */
public class GameEngine extends JPanel implements ActionListener {
    private static final int FPS = 240;
    private static int Width,Height,blockHeight,blockWidth;
    private static  int blockCountOnRow, blockCountOnColumn;
    private static Block[][] blocks;
    private MouseClickListener mouseListener;
    private static int[] count = new int[]{0,0,0,0,0,0,0,0,0};
    Mood mood;
    private boolean selected;
    private HashSet<String> visitedBlocks;
    private int commercialWorkers = 0;
    private int industrialWorkers = 0;
    private ArrayList<Residental> residentals;
    private ArrayList<Commercial> commercials;
    private ArrayList<Industrial> industrials;
    private ArrayList<Firedepartment> fireStations;
    private ArrayList<Sprite> fireFighters;
    private ArrayList<Sprite> fires;
    Level level;
    private double money;
    private int population;
    private String date;    
    private String sysdate;
    public static boolean loaded;
    private ArrayList<int[]> stadions;

    
    public GameEngine(int onBlockwWidth, int oneBlockHeight){
        super(new GridLayout(10,10));
        this.selected = false;
        this.setBackground(Color.red);
        GameEngine.blockWidth = onBlockwWidth;
        GameEngine.blockHeight = oneBlockHeight;
        this.visitedBlocks = new HashSet<>();
        GameEngine.loaded = GameGUI.getLoaded();
        this.residentals = new ArrayList<>();
        this.commercials = new ArrayList<>();
        this.industrials = new ArrayList<>();
        this.fireStations = new ArrayList<>();
        this.fireFighters = new ArrayList<>();
        this.fires = new ArrayList<>();
        this.stadions = new ArrayList<>();
        new java.util.Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                repaint();
            }
        }, 0,50);
        this.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                //Itt kapjuk meg, hogy melyik Blockot kell néznünk a mátrixban
                int y = (int)(Math.floor((e.getX()/ blockWidth)));
                int x = (int)(Math.floor((e.getY()/ blockHeight)));
                int repX = blocks[x][y].getX();
                int repY = blocks[x][y].getY();
                //double v = Double.parseDouble(GameGUI.getMoney().split(" ")[0]);
                double v = GameGUI.getMoney();
                boolean topStadium = false;
                Block residental = new Residental(0,0,0,0);    
                if(e.getButton() == MouseEvent.BUTTON1) {
                    switch(ClickHandler.getMouseMode()){
                        case SELECT:{
                            //Ide kell majd az on
                            break;
                        }
                        case RESIDENTAL:{
                            placeResidental(x,y,repX,repY,v);
                            break;
                        }
                        case COMMERCIAL:{
                            placeCommercial(x,y,repX,repY,v);
                            break;
                        }
                        case ROAD:{
                            //Ide megint csak kell egy pénzlevonás
                            ArrayList<int[]> array = new ArrayList<>();
                            if(checkIfEmptyBlock(x,y)){
                                checkRoad(x, y);
                                count[4]++;
                                resetVisitedBlocks();
                                array = shortestPath(blocks[x][y], blocks[4][0]);
                                    for (int[] coordinates : array) {
                                    System.out.println("x: " + coordinates[0] + ", y: " + coordinates[1]);
                                }
                            }
                            placeRoad(x,y);
                            break;
                        }
                        case INDUSTRIAL:{
                            placeIndustrial(x,y,repX,repY,v);
                            break;
                        }
                        case STADIUM:{
                            topStadium = placeStadium(x,y,repX,repY,v);
                            int[] coordinates = new int[2]; 
                            coordinates[0] = x;
                            coordinates[1] = y;
                            stadions.add( coordinates);
                            break;
                        }
                        case POLICE:{
                            placePoliceStation(x,y,repX,repY,v);
                            break;
                        }
                        case FIREDEPARTMENT:{
                            placeFireStation(x,y,repX,repY,v);
                            break;
                        }
                        case FOREST:{
                            placeForest(x,y,repX,repY,v);
                        
                        break;
                    }
                    case REMOVE:{
                            Block res = new Residental(0,0,0,0);
                            Block com = new Commercial(0,0,0,0);
                            Block ind = new Industrial(0,0,0,0);
                            Block std = new Stadium(0,0,0,0, STADIUMTYPE.BLEFT);
                            if(!(checkIfEmptyBlock(x,y))){
                                if(res.getClass() == blocks[x][y].getClass()){
                                    count[0]--;
                                    blocks[x][y].stopTimer();
                                    GameGUI.setPopulation(-(blocks[x][y].getResNumber()));
                                    residentals.remove(blocks[x][y]);
                                }
                                if(com.getClass() == blocks[x][y].getClass()){
                                    count[1]--;
                                    commercials.remove(blocks[x][y]);
                                }
                                if(ind.getClass() == blocks[x][y].getClass()){
                                    count[2]--;
                                    industrials.remove(blocks[x][y]);
                                }
                                if(std.getClass() == blocks[x][y].getClass()){
                                    count[3]--;
                                }
                                if(blocks[x][y] instanceof Road){
                                    count[4]--;
                                }
                                blocks[x][y] = new Grass(repX,repY,blockWidth,blockHeight);

                            }
                            break;
                        }

                    }
                    if(blocks[x][y].getbuildTime()!=0){
                        blocks[x][y].startTimer();
                        if(blocks[x][y] instanceof Stadium){
                            if(topStadium){
                                blocks[x-1][y].startTimer();
                                blocks[x-1][y+1].startTimer();
                                blocks[x][y+1].startTimer();
                            }
                            else{
                                blocks[x+1][y].startTimer();
                                blocks[x+1][y+1].startTimer();
                                blocks[x][y+1].startTimer();
                            }
                        }
                    }
                    
                }
                
                // Példányosítok mindegyik zónatípusból egyet, hogy össze lehessen hasonlítani az osztályokat
                Block res = new Residental(0,0,0,0);
                Block com = new Commercial(0,0,0,0);
                Block ind = new Industrial(0,0,0,0);
                
                //Ezt kell árírni, hogy a ResdientalGUI a Mood.getAllMood()-ból kapja az értéket
                if(e.getButton() == MouseEvent.BUTTON3 && blocks[x][y].getClass() == res.getClass()) {
                   GameGUI.t.stopTime();
                   ResidentalGUI resGUI = new ResidentalGUI("Residential", blocks[x][y].getSelfMood(), blocks[x][y].getResNumber(), blocks[x][y].getCapacity()); //Ezt vissza kell írni
                }
                
                if(e.getButton() == MouseEvent.BUTTON3 && blocks[x][y].getClass() == com.getClass()) {
                   GameGUI.t.stopTime();
                   ResidentalGUI resGUI = new ResidentalGUI("Commercial", blocks[x][y].getSelfMood(), blocks[x][y].getResNumber(), blocks[x][y].getCapacity()); //Ezt vissza kell írni
                }
                
                if(e.getButton() == MouseEvent.BUTTON3 && blocks[x][y].getClass() == ind.getClass()) {
                   GameGUI.t.stopTime();
                   ResidentalGUI resGUI = new ResidentalGUI("Industrial", blocks[x][y].getSelfMood(), blocks[x][y].getResNumber(), blocks[x][y].getCapacity()); //Ezt vissza kell írni
                }
            }
            
            
        });
    }
    
    public void startGame(){
        int yStartPos = 0;
        int xStartPos = 0;
        blockCountOnRow = (int)(Math.ceil(this.getWidth()/this.blockWidth)+1);
        blockCountOnColumn = (int)(Math.ceil(this.getHeight()/this.blockHeight)+1);
        blocks = new Block[blockCountOnColumn][blockCountOnRow];
        for(int i = 0; i < blockCountOnColumn; i++){
            xStartPos = 0;
            for(int n = 0; n < blockCountOnRow; n++){
                Grass b = new Grass(xStartPos,yStartPos,blockWidth,blockHeight);
                blocks[i][n] = b;
                xStartPos += this.blockWidth;
            }
            yStartPos += this.blockHeight;
        }
        int roadStartX = blocks[4][0].sprite.getX();
        int roadStartY = blocks[4][0].sprite.getY();
        blocks[4][0] = new Road(roadStartX,roadStartY,blockWidth,this.blockHeight, 0);
        count[4]++;
        if(GameGUI.getLoaded()){
            loadBlocksFromFile(GameMenu.file);
            for(int i = 0; i < blockCountOnColumn-1; i++){
                for(int j = 0; j < blockCountOnRow-1; j++){
                    if(blocks[i][j] instanceof Road){
                        checkRoad(i, j);

                    }
                }
            }
        }
        loaded = false;
        new java.util.Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                for(int i = 0; i < residentals.size(); i++){
                    for(People p : residentals.get(i).people){
                        if(commercialWorkers >= industrialWorkers&&seeIfThereIsWorkPlaceInd(industrials)){
                            for(Industrial b : industrials){
                                if(b.getResNumber() < b.getCapacity()){
                                    p.setWorkSpace(b);
                                    b.setWorker(p);
                                    industrialWorkers++;
                                }
                            }
                        }else if(commercialWorkers <= industrialWorkers&&seeIfThereIsWorkPlaceCom(commercials)){
                            for(Commercial b : commercials){
                                if(b.getResNumber() < b.getCapacity()){
                                    p.setWorkSpace(b);
                                    b.setWorker(p);
                                    commercialWorkers++;
                                }
                            }
                        }else if(seeIfThereIsWorkPlaceInd(industrials)){
                            for(Industrial b : industrials){
                                if(b.getResNumber() < b.getCapacity()){
                                    p.setWorkSpace(b);
                                    b.setWorker(p);
                                    industrialWorkers++;
                                }
                            }
                        }else if(seeIfThereIsWorkPlaceCom(commercials)){
                            for(Commercial b : commercials){
                                if(b.getResNumber() < b.getCapacity()){
                                    p.setWorkSpace(b);
                                    b.setWorker(p);
                                    commercialWorkers++;
                                }
                            }
                        }
                    }
                }                
                for(int i = 0; i < blockCountOnColumn; i++){
                    for(int n = 0; n < blockCountOnRow; n++){
                        if(blocks[i][n].active&&(blocks[i][n].checkIfFire()||blocks[i][n].isOnFire())){
                            startSpreadTimer(i,n);
                            startFire(i,n);
                        }
                    }
                }
            }
        }, 0,5000);
    }
    
    public static void setBlocks(Block[][] blocks) {
        GameEngine.blocks = blocks;
    }
    
    public boolean seeIfThereIsWorkPlaceInd(ArrayList<Industrial> list){
        for(Industrial b : list){
            if(b.getResNumber() < b.getCapacity()){
                return true;
            }
        }
        return false;
    }
    
    public boolean seeIfThereIsWorkPlaceCom(ArrayList<Commercial> list){
        for(Commercial b : list){
            if(b.getResNumber() < b.getCapacity()){
                return true;
            }
        }
        return false;
    }
    
    //Megnézi, hogy van-e út a block körül
    public boolean checkForRoad(int x, int y){
        if(x>0 && blocks[x-1][y] instanceof Road){
            return true;
        }
        else if(x<blocks.length-1 && blocks[x+1][y] instanceof Road){
            return true;
        }
        else if(y>0 && blocks[x][y-1] instanceof Road){
            return true;
        }
        else if(y<blocks[x].length-1 && blocks[x][y+1] instanceof Road){
            return true;
        }
        return false;
    }
    
    public void placeResidental(int x, int y, int repX, int repY, double v){
        if (checkBlockIf(x,y)) {
            Residental r = new Residental(repX,repY,blockWidth,blockHeight);
            blocks[x][y] = r;
            residentals.add(r);
            count[0]++;
            v = v - 200;
            GameGUI.setMoney(v + " $");
            checkIfResidentalHasBoostNeighbour(x,y);
            checkIfResidentalHasFireStationNeighbour(x, y);
        }
    }
    
    public void startFire(int x, int y){
        Image fireSprite = new ImageIcon("src/sprites/fire.png").getImage();
        Sprite fire = new Sprite(blocks[x][y].getX(),blocks[x][y].getY(),blockWidth,blockHeight, fireSprite);
        fires.add(fire);
        searchFirefighter(blocks[x][y],fire);
    }
    
    public void placeCommercial(int x, int y, int repX, int repY, double v){
        if(checkBlockIf(x,y)){
            Commercial c = new Commercial(repX, repY, blockWidth, blockHeight);
            blocks[x][y]=c;
            commercials.add(c);
            count[1]++;
            v-=200;
            GameGUI.setMoney(v + " $");
        }
    }
    
    public void placeIndustrial(int x, int y, int repX, int repY, double v){
        if (checkBlockIf(x,y)) {
            Industrial ind = new Industrial(repX,repY,blockWidth,blockHeight);
            blocks[x][y] = ind;
            industrials.add(ind);
            count[2]++;
            v = v - 200;
            GameGUI.setMoney(v + " $");
            checkIfBuildingHasResidentalNeighbour(x,y);
        }
    }
    
    public void placeRoad(int x, int y){
        if(checkIfEmptyBlock(x,y)){
            checkRoad(x, y);
            count[4]++;
            resetVisitedBlocks();
        }
    }
    
    public boolean placeStadium(int x, int y, int repX, int repY, double v){
        if(checkBlockIf(x,y)&&checkIfStadiumCanBePlacedTop(x,y)){
            blocks[x][y] = new Stadium(repX,repY,blockWidth,blockHeight,STADIUMTYPE.BLEFT);
            blocks[x-1][y] = new Stadium(blocks[x-1][y].getX(),blocks[x-1][y].getY(),blockWidth,blockHeight,STADIUMTYPE.TLEFT);
            blocks[x-1][y+1] = new Stadium(blocks[x-1][y+1].getX(),blocks[x-1][y+1].getY(),blockWidth,blockHeight,STADIUMTYPE.TRIGHT);
            blocks[x][y+1] = new Stadium(blocks[x][y+1].getX(),blocks[x][y+1].getY(),blockWidth,blockHeight,STADIUMTYPE.BRIGHT);
            count[3]++;
            v = v - 800;
            GameGUI.setMoney(v + " $");
            checkIfBuildingHasResidentalNeighbour(x,y);
            return true;
        }else if(checkBlockIf(x,y)&&checkIfStadiumCanBePlacedBottom(x,y)){
            blocks[x][y] = new Stadium(repX,repY,blockWidth,blockHeight,STADIUMTYPE.TLEFT);
            blocks[x+1][y] = new Stadium(blocks[x+1][y].getX(),blocks[x+1][y].getY(),blockWidth,blockHeight,STADIUMTYPE.BLEFT);
            blocks[x+1][y+1] = new Stadium(blocks[x+1][y+1].getX(),blocks[x+1][y+1].getY(),blockWidth,blockHeight,STADIUMTYPE.BRIGHT);
            blocks[x][y+1] = new Stadium(blocks[x][y+1].getX(),blocks[x][y+1].getY(),blockWidth,blockHeight,STADIUMTYPE.TRIGHT);
            count[3]++;
            v = v - 800;
            GameGUI.setMoney(v + " $");
            checkIfBuildingHasResidentalNeighbour(x,y);
            return false;
        }
        return false;
    }
    
    public void placePoliceStation(int x, int y, int repX, int repY, double v){
        if(checkBlockIf(x,y)){
            blocks[x][y]=new Police(repX, repY, blockWidth, blockHeight);
            v = v - 500;
            GameGUI.setMoney(v + " $");
            checkIfBuildingHasResidentalNeighbour(x,y);
            count[5]++;
        }
    }
    
    public void placeForest(int x, int y, int repX, int repY, double v){
        if(checkIfEmptyBlock(x,y)){
            blocks[x][y]=new Forest(repX, repY, blockWidth, blockHeight);
            v = v - 350;
            GameGUI.setMoney(v + " $");
            checkIfBuildingHasResidentalNeighbour(x,y);
            count[7]++;
        }
    }
    
    public void placeFireStation(int x, int y, int repX, int repY, double v){
        if(checkBlockIf(x,y)){
            Firedepartment f = new Firedepartment(repX, repY, blockWidth, blockHeight);
            blocks[x][y]= f;
            fireStations.add(f);
            v = v - 500;
            GameGUI.setMoney(v + " $");
            checkIfBuildingHasResidentalNeighbour(x,y);
            checkIfFireStationHasZoneNeighbour(x, y);
            count[6]++;
        }
    }
    
    public int[] getNeighborRoadXY(int x, int y){
        int[] result = new int[2];
        if(x>0 && blocks[x-1][y] instanceof Road){
            result[1] = x-1;
            result[0] = y;
            return result;
        }
        else if(x<blocks.length-1 && blocks[x+1][y] instanceof Road){
            result[1] = x+1;
            result[0] = y;
            return result;
        }
        else if(y>0 && blocks[x][y-1] instanceof Road){
            result[1] = x;
            result[0] = y-1;
            return result;
        }
        else{
            result[1] = x;
            result[0] = y+1;
            return result;
        }
    }
    
    
    //Megnézi hogy amire akarunk építkezni egyáltalán üres-e
    public boolean checkIfEmptyBlock(int x, int y){
        return blocks[x][y] instanceof Grass;
    }
    
    
    public void searchFirefighter(Block b,Sprite fire){
        Firedepartment f;
        int minLength = Integer.MAX_VALUE;
        ArrayList<int[]> array = new ArrayList<>();
        ArrayList<int[]> maxArray = new ArrayList<>();
        int endY = (int)(Math.floor((b.getX()/ blockWidth)));
        int endX = (int)(Math.floor((b.getY()/ blockHeight)));
        int[] endRoadCoords = getNeighborRoadXY(endX,endY);
        for(int i = 0; i < fireStations.size(); i++){
            if(fireStations.get(i).active){
                int startY = (int)(Math.floor((fireStations.get(i).getX()/ blockWidth)));
                int startX = (int)(Math.floor((fireStations.get(i).getY()/ blockHeight)));
                int[] startRoadCoords = getNeighborRoadXY(startX,startY);
                array = shortestPath(blocks[startRoadCoords[1]][startRoadCoords[0]], blocks[endRoadCoords[1]][endRoadCoords[0]]);
                if(array.size()>0){
                    array.add(0,endRoadCoords);
                    array.add(startRoadCoords);
                }

                if(array.size() < minLength){
                    minLength = array.size();
                    f = fireStations.get(i);
                    maxArray = array;
                }
            }
        }
        if(minLength != Integer.MAX_VALUE){
            dispatchFirefighters(maxArray,b,fire);
        }
    }
    
    public void startSpreadTimer(int x,int y){
        new java.util.Timer().schedule(new TimerTask(){
            @Override
            public void run(){
                if(blocks[x][y].isOnFire()){
                    spreadFire(x,y);
                }
            }
            
        },1000);
    }
    
    public void spreadFire(int x, int y){
        if(x>0 && !(blocks[x-1][y] instanceof Road||blocks[x-1][y] instanceof Grass||blocks[x-1][y] instanceof Stadium)){
            startFire(x-1,y);
        }
        else if(x<blocks.length-1 && !(blocks[x+1][y] instanceof Road||blocks[x+1][y] instanceof Grass||blocks[x+1][y] instanceof Stadium)){
            startFire(x+1,y);
        }
        else if(y>0 && !(blocks[x][y-1] instanceof Road||blocks[x][y-1] instanceof Grass||blocks[x][y-1] instanceof Stadium)){
            startFire(x,y-1);
        }
        else if(y<blocks[x].length-1 && !(blocks[x][y+1] instanceof Road||blocks[x][y+1] instanceof Grass||blocks[x][y+1] instanceof Stadium)){
            startFire(x,y+1);
        }
    }
    
    
    public void dispatchFirefighters(ArrayList<int[]> road,Block b,Sprite fire){
        Image fireFighterImage = new ImageIcon("src/sprites/fire_truck_right.png").getImage();
        int firstRoadIndex = road.size()-1;
        Sprite sprite = new Sprite(blocks[road.get(firstRoadIndex)[1]][road.get(firstRoadIndex)[0]].getX(),blocks[road.get(firstRoadIndex)[1]][road.get(firstRoadIndex)[0]].getY(),30,30,fireFighterImage);
        fireFighters.add(sprite);
        new java.util.Timer().scheduleAtFixedRate(new TimerTask(){
            public int index = road.size()-2;
            @Override
            public void run(){
                if(index >= 0){
                    int distanceX = blocks[road.get(index)[1]][road.get(index)[0]].getX() - blocks[road.get(index+1)[1]][road.get(index+1)[0]].getX();
                    int distanceY = blocks[road.get(index)[1]][road.get(index)[0]].getY() - blocks[road.get(index+1)[1]][road.get(index+1)[0]].getY();
                    if(distanceX<0){
                        Image fireFighterImage = new ImageIcon("src/sprites/fire_truck_left.png").getImage();
                        sprite.updateImage(fireFighterImage);
                    }else if(distanceX>0){
                        Image fireFighterImage = new ImageIcon("src/sprites/fire_truck_right.png").getImage();
                        sprite.updateImage(fireFighterImage);
                    }else if(distanceY<0){
                        Image fireFighterImage = new ImageIcon("src/sprites/fire_truck_up.png").getImage();
                        sprite.updateImage(fireFighterImage);
                    }else{
                        Image fireFighterImage = new ImageIcon("src/sprites/fire_truck_down.png").getImage();
                        sprite.updateImage(fireFighterImage);
                    }
                    if(blocks[road.get(index)[1]][road.get(index)[0]].getX()==sprite.getX()&&blocks[road.get(index)[1]][road.get(index)[0]].getY()==sprite.getY()){
                        index--;
                        if(index >= 0){

                            distanceX = blocks[road.get(index)[1]][road.get(index)[0]].getX() - blocks[road.get(index+1)[1]][road.get(index+1)[0]].getX();
                            distanceY = blocks[road.get(index)[1]][road.get(index)[0]].getY() - blocks[road.get(index+1)[1]][road.get(index+1)[0]].getY();
                            sprite.setX(sprite.getX()+distanceX/50);
                            sprite.setY(sprite.getY()+distanceY/50);
                            
                        }
                    }else{
                        sprite.setX(sprite.getX()+distanceX/50);
                        sprite.setY(sprite.getY()+distanceY/50);
                    }
                    /*
                    sprite.setX(blocks[road.get(index)[1]][road.get(index)[0]].getX());
                    sprite.setY(blocks[road.get(index)[1]][road.get(index)[0]].getY());
                    index--;
                    */
                }
                else{
                    sprite.setInvisible();
                    fireFighters.remove(sprite);
                    fires.remove(fire);
                    this.cancel();
                }
            }
        }, 0,10);    
    }
    
    public static boolean checkIfResBlock(Block b){
        return b instanceof Residental;
    }
    
    public boolean checkIfStadiumCanBePlacedTop(int x,int y){
        
        if(x<1||y>=blocks[0].length-1){
            return false;
        }
        return checkIfEmptyBlock(x-1,y)&&checkIfEmptyBlock(x-1,y+1)&&checkIfEmptyBlock(x,y+1)&&checkIfEmptyBlock(x,y);
    }
    
    public boolean checkIfStadiumCanBePlacedBottom(int x, int y){
        if(y>=blocks[0].length-1||x==blocks.length-1){
            return false;
        }
        return checkIfEmptyBlock(x+1,y)&&checkIfEmptyBlock(x+1,y+1)&&checkIfEmptyBlock(x,y+1)&&checkIfEmptyBlock(x,y);
    }
    
    public void checkIfResidentalHasBoostNeighbour(int x, int y){
        
        int xStartPosition = setXstartPosition(x);
        int yStartPosition = setYstartPosition(y);
        int xEndPosition = setXEndPosition(x);
        int yEndPosition = setYEndPosition(y);
        
        double moodAll = 1;
        for(int i = xStartPosition; i <= xEndPosition;i++){
            for(int n = yStartPosition; n <= yEndPosition; n++){
                if(blocks[i][n] instanceof Industrial || blocks[i][n] instanceof Stadium || blocks[i][n] instanceof Police || blocks[i][n] instanceof Firedepartment|| blocks[i][n] instanceof Forest){
                    moodAll *= blocks[i][n].getMoodBoost();
                }
            }
        }
        blocks[x][y].setStartMood(moodAll);
    }
    
    public void checkIfResidentalHasFireStationNeighbour(int x, int y){
        
        int xStartPosition = setXstartPosition(x);
        int yStartPosition = setYstartPosition(y);
        int xEndPosition = setXEndPosition(x);
        int yEndPosition = setYEndPosition(y);
        
        boolean found = false;
        for(int i = xStartPosition; i <= xEndPosition&&!found;i++){
            for(int n = yStartPosition; n <= yEndPosition; n++){
                if(blocks[i][n] instanceof Firedepartment){
                    blocks[x][y].setZoneFireRate();
                    found = true;
                }
            }
        }
    }
    
    public void checkIfBuildingHasResidentalNeighbour(int x, int y){
        int xStartPosition = setXstartPosition(x);
        int yStartPosition = setYstartPosition(y);
        int xEndPosition = setXEndPosition(x);
        int yEndPosition = setYEndPosition(y);

        for(int i = xStartPosition; i <= xEndPosition;i++){
            for(int n = yStartPosition; n <= yEndPosition; n++){
                if(blocks[i][n] instanceof Residental){
                    blocks[i][n].setStartMood(blocks[x][y].getMoodBoost());
                }
            }
        }
    }
    
    public int setXstartPosition(int x){
        int xStartPosition = x;
        for(int i = x; i >= x-3&& i >= 0;i--){
            xStartPosition = i;
        }
        return xStartPosition;
    }
    
    public int setYstartPosition(int y){
        int yStartPosition = y;
        for(int i = y; i >= y-3&& i >= 0;i--){
            yStartPosition = i;
        }
        return yStartPosition;
    }
    
    public int setXEndPosition(int x){
        int xEndPosition = x;
        for(int i = x; i <= x+3 && i < blocks.length;i++){
            xEndPosition = i;
        }
        return xEndPosition;
    }
    
    public int setYEndPosition(int y){
        int yEndPosition = y;
        for(int i = y; i <= y+3&& i < blocks[0].length;i++){
            yEndPosition = i;
        }
        return yEndPosition;
    }
    
    
    
    public void checkIfFireStationHasZoneNeighbour(int x, int y){
        int xStartPosition = setXstartPosition(x);
        int yStartPosition = setYstartPosition(y);
        int xEndPosition = setXEndPosition(x);
        int yEndPosition = setYEndPosition(y);

        for(int i = xStartPosition; i <= xEndPosition;i++){
            for(int n = yStartPosition; n <= yEndPosition; n++){
                if(blocks[i][n] instanceof Commercial ||blocks[i][n] instanceof Residental||blocks[i][n] instanceof Industrial){
                    blocks[i][n].setZoneFireRate();
                }
            }
        }
    }
    
    public boolean checkBlockIf(int x, int y){
        return checkIfEmptyBlock(x,y) && checkForRoad(x,y);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < blockCountOnColumn; i++){
            for(int n = 0; n < blockCountOnRow; n++){
                blocks[i][n].draw(g);
            }
        }
        for(int i = 0; i < fireFighters.size(); i++){
            fireFighters.get(i).draw(g);
        }
        for(int i = 0; i < fires.size(); i++){
            fires.get(i).draw(g);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
    
    public static int[] getCount() {
        return count;
    }
    
    private void checkRoad(int x, int y){
        String key = x + "," + y;
        int r = checkNearby(x, y);
        if (!visitedBlocks.contains(key)) {
            blocks[x][y] = new Road(blocks[x][y].getX(),blocks[x][y].getY(),
                    blockWidth,blockHeight, r);
            visitedBlocks.add(key);
            if(checkLeft(x, y) && checkRight(x, y) && checkUp(x, y) && checkDown(x, y)){
                checkRoad(x, y-1);
                checkRoad(x, y+1);
                checkRoad(x-1, y);
                checkRoad(x+1, y);
            }
            else if((checkLeft(x, y) && checkRight(x, y) && checkUp(x, y))){
                checkRoad(x, y-1);
                checkRoad(x, y+1);
                checkRoad(x-1, y);
            }
            else if(checkLeft(x, y) && checkRight(x, y) && checkDown(x, y)){
                checkRoad(x, y-1);
                checkRoad(x, y+1);
                checkRoad(x+1, y);
            }
            else if(checkLeft(x, y) && checkUp(x, y) && checkDown(x, y)){
                checkRoad(x, y-1);
                checkRoad(x+1, y);
                checkRoad(x-1, y);
            }
            else if(checkRight(x, y) && checkUp(x, y) && checkDown(x, y)){
                checkRoad(x, y+1);
                checkRoad(x+1, y);
                checkRoad(x-1, y);
            }
            else if(r == 5){
                checkRoad(x, y+1);
                checkRoad(x+1, y);              
            }
            else if(r == 4){
                checkRoad(x, y+1);
                checkRoad(x-1, y);
            }
            else if(r == 3){
                checkRoad(x+1, y);
                checkRoad(x, y-1);
            }
            else if(r == 2){
                checkRoad(x-1, y);
                checkRoad(x, y-1);
            }
            else if(checkDown(x, y) && checkUp(x, y)){
                checkRoad(x+1, y);
                checkRoad(x-1, y);
            }
            else if(checkLeft(x, y) && checkRight(x, y)){
                checkRoad(x, y+1);
                checkRoad(x, y-1);
            }
            else if(checkDown(x, y)){
                checkRoad(x+1, y);
            }
            else if(checkUp(x, y)){
                checkRoad(x-1, y);
            }
            else if(checkRight(x, y)){
                checkRoad(x, y+1);
            }
            else if(checkLeft(x, y)){
                checkRoad(x, y-1);
            }
        }
    }
   
    private int checkNearby(int x, int y) {
        if(checkLeft(x, y) && checkRight(x, y) && checkUp(x, y) && checkDown(x, y)){
            return 6;
        }
        if((checkLeft(x, y) && checkRight(x, y) && checkUp(x, y)) ||
           (checkLeft(x, y) && checkRight(x, y) && checkDown(x, y)) ||
           (checkLeft(x, y) && checkUp(x, y) && checkDown(x, y)) ||
           (checkRight(x, y) && checkUp(x, y) && checkDown(x, y))){
            return 6;
        }
        if(checkRight(x, y) && checkDown(x, y)){
            return 5;
        }
        if(checkRight(x, y) && checkUp(x, y)){
            return 4;
        }
        if(checkLeft(x, y) && checkDown(x, y)){
            return 3;
        }
        if(checkLeft(x, y) && checkUp(x, y)){
            return 2;
        }
        if(checkDown(x, y) && checkUp(x, y)){
            return 1;
        }
        if(checkLeft(x, y) && checkRight(x, y)){
            return 0;
        }
        if(checkDown(x, y)){
            return 1;
        }
        if(checkUp(x, y)){
            return 1;
        }
        if(checkRight(x, y)){
            return 0;
        }
        if(checkLeft(x, y)){
            return 0;
        }
        return 0;
    }

    private boolean checkLeft(int x, int y){
        if(y != 0){
            return blocks[x][y-1] instanceof Road;
        }
        return false;
    }
    private boolean checkRight(int x, int y){
        return blocks[x][y+1] instanceof Road;
    }
    
    private boolean checkUp(int x, int y){
        if(x != 0){
            return blocks[x-1][y] instanceof Road;
        }
        return false;
    }
    
    private boolean checkDown(int x, int y){
        return blocks[x+1][y] instanceof Road;
    }
    
    public void resetVisitedBlocks() {
        visitedBlocks.clear();
    }
    
    class Node {
    Block block;
    Node previous;
    double distance;

    Node(Block block, Node previous, double distance) {
        this.block = block;
        this.previous = previous;
        this.distance = distance;
    }
}
    
public ArrayList<int[]> shortestPath(Block start, Block end) {
    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));
    HashMap<Block, Node> blockToNode = new HashMap<>();

    for (Block[] blockRow : blocks) {
        for (Block block : blockRow) {
            if (block == start) {
                Node startNode = new Node(block, null, 0);
                queue.add(startNode);
                blockToNode.put(block, startNode);
            } else {
                blockToNode.put(block, new Node(block, null, Double.POSITIVE_INFINITY));
            }
        }
    }

    while (!queue.isEmpty()) {
        Node currentNode = queue.poll();

        for (Block neighbor : getNeighbors(currentNode.block)) {
            Node neighborNode = blockToNode.get(neighbor);

            double newDistance = currentNode.distance + 1; // minden szomszédos cella 1 "távolságra" van

            if (newDistance < neighborNode.distance) {
                neighborNode.distance = newDistance;
                neighborNode.previous = currentNode;
                queue.add(neighborNode); // újra hozzáadjuk a queue-hoz az új távolsággal
            }
        }
    }

    ArrayList<int[]> path = new ArrayList<>();
    Node currentNode = blockToNode.get(end).previous;

    while (currentNode != null && currentNode.previous != null) {
        int[] coordinates = new int[2]; 
        coordinates[0] = (int)(Math.floor((currentNode.block.getX()/ blockHeight))); // Assuming Block class has getX() method
        coordinates[1] = (int)(Math.floor((currentNode.block.getY()/ blockWidth))); // Assuming Block class has getY() method
        path.add( coordinates); // hozzáadjuk az utat az elején, mert visszafelé haladunk
        currentNode = currentNode.previous;
    }
    return path;
}




public ArrayList<Block> getNeighbors(Block b) {
    ArrayList<Block> neighbors = new ArrayList<>();

    int[] dx = {0, 0, -1, 1}; // left, right, up, down
    int[] dy = {-1, 1, 0, 0}; // left, right, up, down
    int x = (int)(Math.floor((b.getTrueY()/ blockHeight)));
    int y = (int)(Math.floor((b.getTrueX()/ blockWidth)));


    for (int i = 0; i < 4; i++) {
        int newX = x + dx[i];
        int newY = y + dy[i];
        
        if (newX >= 0 && newX < blocks.length && newY >= 0 && newY < blocks[0].length && blocks[newX][newY] instanceof Road) {
        neighbors.add(blocks[newX][newY]);
        }
    }
    return neighbors;
}

private int getBlockTypeAsInt(Block block) {
    if (block instanceof Grass) {
        return 0;
    } else if (block instanceof Residental) {
        return 1;
    } else if (block instanceof Commercial) {
        return 2;
    } else if (block instanceof Industrial) {
        return 3;
    } else if (block instanceof Road) {
        return 4;
    }else if (block instanceof Police) {
        return 5;
    }else if (block instanceof Firedepartment) {
        return 6;
    }else if (block instanceof Stadium ) {
        int x = (int)(Math.floor((block.getTrueY()/ blockHeight)));
        int y = (int)(Math.floor((block.getTrueX()/ blockWidth)));
        for(int i = 0; i < stadions.size() ; i++){
            if(stadions.get(i)[0] == x && stadions.get(i)[1] == y) {
                return 7;
            }
        }
    } else if (block instanceof Forest) {
        return 8;
    }
    return -1;
}

public void saveBlocksToFile(String filename) {
        LocalDate currentDate = LocalDate.now();

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Format the current date
        String formattedDate = currentDate.format(formatter);
    try (PrintWriter out = new PrintWriter(new FileWriter(GameMenu.directory + "/" +filename))) {
        for (int i = 0; i < blockCountOnColumn; i++) {
            for (int j = 0; j < blockCountOnRow; j++) {
                out.print(getBlockTypeAsInt(blocks[i][j]));
                if (j < blockCountOnRow - 1) {
                    out.print(" ");
                }
            }
            out.println();
        }
        out.println();
        for (int i = 0; i < blockCountOnColumn; i++) {
            for (int j = 0; j < blockCountOnRow; j++) {
                if(blocks[i][j] instanceof Residental){
                    out.print((blocks[i][j].getResNumber()));
                }else{
                    out.print(-1);
                }
                if (j < blockCountOnRow - 1) {
                    out.print(" ");
                }
            }
            out.println();
        }
        out.println();
        out.print(GameGUI.getBudget());
        out.println();
        out.println();
        out.print(GameGUI.getPopulation());
        out.println();
        out.println();
        out.print(GameGUI.getDate());
        out.println();
        out.println();
        out.print(formattedDate);
        out.println();
        out.print(Time.lDate);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void loadBlocksFromFile(String filename) {
    try (BufferedReader in = new BufferedReader(new FileReader(GameMenu.directory+ "/" + filename))) {
        String line;
        int i = 0;
        while ((line = in.readLine()) != null) {
            if (line.trim().isEmpty()) {
                break;
            }
            String[] blockTypes = line.split(" ");
            for (int j = 0; j < blockTypes.length; j++) {
                int blockType = Integer.parseInt(blockTypes[j]);
                int y = (int)(Math.floor((blocks[i][j].getX()/ blockWidth)));
                int x = (int)(Math.floor((blocks[i][j].getY()/ blockHeight)));
                int repX = blocks[i][j].getX();
                int repY = blocks[i][j].getY();

                switch (blockType) {
                    case 0:
                        blocks[i][j] = new Grass(repX,repY,blockWidth,blockHeight);
                        break;
                    case 1:
                        Residental r = new Residental(repX,repY,blockWidth,blockHeight);
                        blocks[x][y] = r;
                        residentals.add(r);
                        count[0]++;
                        checkIfResidentalHasBoostNeighbour(x, y);
                        break;
                    case 2:
                        Commercial c = new Commercial(repX, repY, blockWidth, blockHeight);
                        blocks[x][y]=c;
                        commercials.add(c);  
                        count[1]++;
                        break;
                    case 3:
                        Industrial ind = new Industrial(repX,repY,blockWidth,blockHeight);
                        blocks[x][y] = ind;
                        industrials.add(ind);
                        count[2]++;
                        checkIfBuildingHasResidentalNeighbour(x,y);
                        break;
                    case 4:
                        blocks[i][j] = new Road(repX, repY, blockWidth, blockHeight, 0);
                        count[4]++;
                        break;
                    case 5:
                        blocks[i][j] = new Police(repX,repY,blockWidth,blockHeight);
                        count[5]++;
                        checkIfBuildingHasResidentalNeighbour(x,y);
                        break;
                    case 6:
                        blocks[i][j] = new Firedepartment(repX, repY, blockWidth, blockHeight);
                        count[6]++;
                        checkIfBuildingHasResidentalNeighbour(x,y);
                        break;    
                    case 7:
                        int[] coordinates = new int[2]; 
                            coordinates[0] = x;
                            coordinates[1] = y;
                            stadions.add( coordinates);
                        break;
                    case 8:
                        blocks[i][j] = new Forest(repX, repY, blockWidth, blockHeight);
                        checkIfBuildingHasResidentalNeighbour(x,y);
                        count[7]++;
                        break; 
                    default:
                        Grass b = new Grass(repX,repY,blockWidth,blockHeight);
                        blocks[i][j] = b;
                        break;
                }                
            }
            i++;
        }
        i = 0;
        while ((line = in.readLine()) != null) {
            if (line.trim().isEmpty()) {
                break;
            }
            String[] blockTypes = line.split(" ");
            for (int j = 0; j < blockTypes.length; j++) {
                boolean topStadium = false;

                for(int x = 0; x < stadions.size() ; x++){
                    System.out.println(stadions.get(x)[0]);
                    if(stadions.get(x)[0] == i && stadions.get(x)[1] == j) {
                        topStadium = placeStadium(i,j,blocks[i][j].getX(),blocks[i][j].getY(),0);
                        if(topStadium){
                                blocks[i-1][j].startTimer(1);
                                blocks[i-1][j+1].startTimer(1);
                                blocks[i][j+1].startTimer(1);
                            }
                            else{
                                blocks[i+1][j].startTimer(1);
                                blocks[i+1][j+1].startTimer(1);
                                blocks[i][j+1].startTimer(1);
                            }

                    }
                }
                if(blocks[i][j] instanceof Residental){
                    System.out.println(blockTypes[j]);
                    blocks[i][j].startTimer(Integer.parseInt(blockTypes[j]));
                    blocks[i][j].startPopulate();
                }
                    if(blocks[i][j].getbuildTime()!=0 && !(blocks[i][j] instanceof Residental)){
                        blocks[i][j].startTimer(1);
                        
                    }
            }
            i++;

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}



}