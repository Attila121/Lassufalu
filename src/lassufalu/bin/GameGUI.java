    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package lassufalu.bin;
    import Tax.TaxSystem;
    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.FlowLayout;
    import java.awt.GridLayout;
    import java.awt.Image;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
    import java.io.InputStream;
import java.io.PrintWriter;
    import javax.swing.BorderFactory;
    import javax.swing.ImageIcon;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.SwingConstants;
    import javax.swing.border.Border;
    import java.util.Arrays;
    import java.util.TimerTask;
    import javax.swing.Box;
    import lassufalu.bin.ClickHandler.mouseMode;
    import lassufalu.bin.Finances;
    import lassufalu.bin.Time;
   


    /**
     *
     * @author Mielec Attila (MNZVUM)
     */

    public class GameGUI {
        private static JFrame frame;
        private final JPanel panel;
        private final JPanel speedPanel;
        private JPanel captionPanel;
        private final JPanel topPanel;
        private JLabel moneyLabel, happinessLabel, populationLabel, dateLabel;
        private static JButton moneyValue = new JButton();;
        private static JLabel moodValue;
        private static JLabel dateValue = new JLabel();
        private static JLabel populationValue = new JLabel();
        private JLabel speedLabel;
        private final JButton normalSpeedButton, stopButton, accelerateButton , exitButton;
        private static final int GAME_ENGINE_WIDTH = 1810;
        private static final int GAME_ENGINE_HEIGHT = 1039;
        private static final int LEFT_PANEL_HEIGHT = GAME_ENGINE_HEIGHT;  
        private static final int VALUES_WIDTH = 100;
        private static final int WINDOW_WIDTH = 1920;
        private static final int WINDOW_HEIGHT = 1080;
        private static final int LEFT_PANEL_TOP_MARGIN = 200;
        private static final int LEFT_PANEL_WIDTH = WINDOW_WIDTH - GAME_ENGINE_WIDTH;
        private static final int TOP_PANEL_HEIGHT = WINDOW_HEIGHT - GAME_ENGINE_HEIGHT;
        private static final int SPRITE_SIZE = 150;
        private static final String SPRITE_IMAGE = "sprites/proba.jpg";
        private static final int GOODS_PRICE = 300;
        private final JPanel buildingPanel;
        private Building[] buildings;
        private static GameEngine gameEngine = new GameEngine(50, 50); ;
        private ClickHandler clickHandler;
        private static Finances finance;
        
        private static int[] values = new int[]{10,0,0,0};
        
        public static Time t;
        private static int month;        
        private static int year;

        
        private static int population;
        private static int mood = 0;
        
        private static String date;
        private static boolean loaded;
        

        public GameGUI(boolean l) {
            
            GameGUI.loaded = l;
            if(GameGUI.loaded){
                finance = new Finances(GameMenu.getMoney());
                population = GameMenu.getPopulation();
                date = GameMenu.getDate();
                System.out.println(date);
            }else{
                finance = new Finances(2000);
                population = 0;
                date = "";
            }
            moneyValue = new JButton();
            moneyValue.setText(finance.getBudget() + " $");
            t = new Time();
            t.normalSpeed();
            // Create the frame with a BorderLayout
            this.clickHandler = new ClickHandler();
            frame = new JFrame("Lassu Falu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            // Create the main panel with a BorderLayout
            panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.WHITE);

            buildingPanel = new JPanel(new GridLayout(9, 1));
            buildingPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT));

            createBuildingButtons();
            //buildingPanel.add(Box.createRigidArea(new Dimension(LEFT_PANEL_WIDTH, 0)), BorderLayout.WEST);

            
            panel.add(buildingPanel, BorderLayout.WEST);


            // Create the top panel with GridLayout
            topPanel = new JPanel();
            topPanel.setLayout(new GridLayout(1, 6, 0, 50)); // Use a 1x6 grid with 20 pixels spacing between the cells
            topPanel.setBackground(Color.WHITE); // Set the background color to white
            topPanel.setPreferredSize(new Dimension(GAME_ENGINE_WIDTH, TOP_PANEL_HEIGHT));

            // Kezdőtőke beállítása
            
            
            moneyValue.setPreferredSize(new Dimension(100, 50)); // Set a fixed size for the button
            Border moneyBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            moneyBorder = BorderFactory.createCompoundBorder(moneyBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10));
            moneyValue.setBorder(moneyBorder);
            
            moneyValue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    t.stopTime();
                    TaxSystem tax = new TaxSystem(values);
                    values = tax.getValues();
                    //t.normalSpeed();
                }
            });

            topPanel.add(moneyValue);

            // Create the happiness value label
            moodValue = new JLabel("☺");
            new java.util.Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                    if(Mood.getAllMood() <= 0.25){
                        moodValue.setText("😠");
                    }else if(Mood.getAllMood() <= 0.4){
                        moodValue.setText("😟");
                    }else if(Mood.getAllMood() <= 0.6){
                        moodValue.setText("😐");
                    }else if(Mood.getAllMood() <= 0.8){
                        moodValue.setText("🙂");
                    }else{
                        moodValue.setText("😁");
                    }
                }
            }, 0,100);
            moodValue.setHorizontalAlignment(JLabel.CENTER);
            moodValue.setVerticalAlignment(JLabel.CENTER);
            moodValue.setPreferredSize(new Dimension(VALUES_WIDTH, 40));
            Border happinessBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            happinessBorder = BorderFactory.createCompoundBorder(happinessBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10));
            moodValue.setBorder(happinessBorder);
            topPanel.add(moodValue);

            // Create the population value label
            populationValue = new JLabel(Integer.toString(population));
            populationValue.setHorizontalAlignment(JLabel.CENTER);
            populationValue.setVerticalAlignment(JLabel.CENTER);
            populationValue.setPreferredSize(new Dimension(VALUES_WIDTH, 40));
            Border populationBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            populationBorder = BorderFactory.createCompoundBorder(populationBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10));
            populationValue.setBorder(populationBorder);
            topPanel.add(populationValue);

            // Create the date value label
            
            dateValue.setHorizontalAlignment(JLabel.CENTER);
            dateValue.setVerticalAlignment(JLabel.CENTER);
            dateValue.setPreferredSize(new Dimension(VALUES_WIDTH, 40));
            Border dateBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            dateBorder = BorderFactory.createCompoundBorder(dateBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10));
            dateValue.setBorder(dateBorder);
            topPanel.add(dateValue);

            // Create the speed buttons panel
            speedPanel = new JPanel();
            speedPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Use FlowLayout with center alignment and 20px spacing
            speedPanel.setBackground(Color.WHITE); // Set the background color to white
            
            // Create the start button
            normalSpeedButton = new JButton("▶");
            normalSpeedButton.setFocusable(false);
            normalSpeedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    t.stopTime();
                    t.normalSpeed();
                }
            });
            normalSpeedButton.setPreferredSize(new Dimension(50, 40)); // Set a fixed size for the button

            // Create the stop button
            stopButton = new JButton("⏸");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    t.stopTime();
                }
            });
            stopButton.setPreferredSize(new Dimension(50, 40));

            // Create the accelerate button
            accelerateButton = new JButton("⏭");
            accelerateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    t.stopTime();
                    t.speedUpTimeFaster();
                }
            });
            accelerateButton.setPreferredSize(new Dimension(50, 40));

            // Add the speed buttons to the speed panel
            speedPanel.add(normalSpeedButton);
            speedPanel.add(stopButton);
            speedPanel.add(accelerateButton);

            // Add the speed panel to the top panel
            topPanel.add(speedPanel);

            // Create the exit button
            exitButton = new JButton("Exit");
            exitButton.setPreferredSize(new Dimension(80, 40));
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameEngine.saveBlocksToFile(GameMenu.file);
                    frame.dispose(); // Close the window when the exit button is clicked
                }
            });
            topPanel.add(exitButton);

            // Add the top panel to the main panel
            panel.add(topPanel, BorderLayout.NORTH);
            month = t.getCurrentMonth() + 1;
            year = t.getCurrentYear() +1;
            
            

            panel.add(gameEngine);
            // Add the main panel to the frame and show it
            frame.add(panel);
            frame.setVisible(true);
            gameEngine.startGame();

        }

        // Inner class for handling sprite click events
        private class MouseClickListener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: Handle sprite click events
            }
        }
        public static class Building {
            String name;
            String imagePath;
            int price;

            public Building(String name, String imagePath, int price) {
                this.name = name;
                this.imagePath = imagePath;
                this.price = price;
            }
        }


        private void createBuildingButtons() {
            buildings = new Building[]{
                    new Building("Residential zone", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 200),
                    new Building("Commercial zone", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 200),
                    new Building("Industrial zone", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 200),
                    new Building("Road", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 0),
                    new Building("Police station", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 500),
                    new Building("Fire department", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 500),
                    new Building("Stadium", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 800),
                    new Building("Forest", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 350),
                    new Building("Remove", "C:\\Users\\attil\\Desktop\\Egyetem\\4.Felev\\Szoftver technológia\\lassufalu\\Lassufalu\\src\\sprites\\proba.jpg", 0),
                    

                    // Add more buildings as needed
            };

            int iconWidth = 64;
            int iconHeight = iconWidth;

            for (int i = 0; i < buildings.length; i++) {
                final int index = i;
                JButton buildingButton = new JButton();
                ImageIcon buildingIcon = new ImageIcon(buildings[i].imagePath);
                Image buildingImage = buildingIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                buildingIcon = new ImageIcon(buildingImage);
                buildingButton.setIcon(buildingIcon);
                buildingButton.setText(buildings[i].name);
                buildingButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                buildingButton.setHorizontalTextPosition(SwingConstants.CENTER);
                buildingButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(buildings[index].name.equals("Residential zone")){
                            clickHandler.clickOnResidental();
                        }
                        if(buildings[index].name.equals("Commercial zone")){
                            clickHandler.clickOnCommercial();
                        }
                        if(buildings[index].name.equals("Industrial zone")){
                            clickHandler.clickOnIndustrial();
                        }
                        else if(buildings[index].name.equals("Remove")){
                            clickHandler.clickOnRemove();
                        }
                        else if(buildings[index].name.equals("Road")){
                            clickHandler.clickOnRoad();
                        }
                        else if(buildings[index].name.equals("Commercial zone")){
                            clickHandler.clickOnCommercial();
                        }
                        else if(buildings[index].name.equals("Industrial zone")){
                            clickHandler.clickOnIndustrial();
                        }
                        else if(buildings[index].name.equals("Stadium")){
                            clickHandler.clickOnStadium();
                        }
                        else if(buildings[index].name.equals("Fire department")){
                            clickHandler.clickOnFireDepartment();
                        }
                        else if(buildings[index].name.equals("Police station")){
                            clickHandler.clickOnPolice();
                        }
                        else if(buildings[index].name.equals("Forest")){
                            clickHandler.clickOnForest();
                        }
                        
                    }
                });
                buildingPanel.add(buildingButton);
               
            }
        }
        
        public static double getBudget(){
            return Double.parseDouble(moneyValue.getText().split(" ")[0]);
        }
        
        public static int[] getValues(){
            return values;
        }
        
        /*
        public static String getMoney(){
            return moneyValue.getText();
        }
        */
        public static double getMoney(){
            return Double.parseDouble(moneyValue.getText().split(" ")[0]);
        }
        
        public static int[] getCount(){
            return GameEngine.getCount();
        }
        
        public static int getMonth(){
            return GameGUI.month;
        }
        
        public static void setMonth(int m){
            GameGUI.month = m;
        }
        
        public static int getYear(){
            return GameGUI.year;
        }
        
        public static void setYear(int y){
            GameGUI.year = y;
        }
        
        public static void setMoney(String s){
            moneyValue.setText(s);
        }
        
        public static void setDate(String date){
            dateValue.setText(date);
            GameGUI.date = date;
        }
        
        public static String getDate(){
            return GameGUI.date;
        }
        
        
        public static int getPopulation(){
            return GameGUI.population;
        }
        
        public static void setPopulation(int p){
            population = population + p;
            GameGUI.populationValue.setText(Integer.toString(population));
        }
        
        public static int getMood(){
            return GameGUI.mood;
        }
        
        public static void setMood(int m){
            mood = mood + m;
            GameGUI.moodValue.setText(Integer.toString(mood));
        }
        
        public static boolean getLoaded(){
            return GameGUI.loaded;
        }
        
        public static void endGame() {
        t.stopTime(); 
        try (PrintWriter out = new PrintWriter(new FileWriter(GameMenu.directory + "/" +GameMenu.file))) {
        out.print("");
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

        JFrame endFrame = new JFrame();
        endFrame.setSize(300, 200); // or any size you prefer
        endFrame.setLocationRelativeTo(frame);
        endFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the message and the button
        JPanel endPanel = new JPanel();
        endPanel.setLayout(new BorderLayout());

        // Create the game over message
        JLabel messageLabel = new JLabel("The game is over you lost", SwingConstants.CENTER);

        // Create the close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the endFrame and the main frame
                endFrame.dispose();
                frame.dispose();
            }
        });

        // Add the message and the button to the panel
        endPanel.add(messageLabel, BorderLayout.CENTER);
        endPanel.add(closeButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        endFrame.add(endPanel);

        // Show the end frame
        endFrame.setVisible(true);
    }

        
    }
