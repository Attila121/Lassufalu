/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import lassufalu.bin.ClickHandler.mouseMode;
import lassufalu.bin.GameGUI;
import java.util.ArrayList;
import java.util.Random;
import lassufalu.bin.Stadium.STADIUMTYPE;

/**
 *
 * @author Roland
 */
public class Block{
    protected Sprite sprite;
    protected int buildTime;
    protected int x,y,width,height;
    protected int seconds;
    protected boolean active;
    protected int capacity;
    protected ArrayList<People> people;
    protected boolean pop;
    protected double moodBoost = 1;
    protected double security;
    private STADIUMTYPE type;
    protected int blockX;
    protected int blockY;
    protected boolean onFire = false;
    
    public Block(int buildTime, int x, int y, int width, int height){
        this.buildTime = buildTime;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        active = false;
        seconds = 0;
        Image grassImage = new ImageIcon("src/sprites/grass.jpg").getImage();
        this.sprite = new Sprite(x,y,width,height,grassImage);
        this.capacity = 0;
        this.people = new ArrayList<People>();
    }
    
    public Block() {}

    public int getCapacity() {
        return capacity;
    }
    
    public void putOutFire(){
        this.onFire = false;
    }
    
    public boolean isOnFire(){
        return this.onFire;
    }
    
    public double getSelfMood(){
        return 0;
    }
    
    public void startTimer(){
        this.sprite = new Sprite(x,y,width,height,new ImageIcon("src/sprites/construction.jpg").getImage());
        int watchedRate = Time.getRate();
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(seconds == buildTime){
                    active = true;
                    setSprite();
                    this.cancel();
                }else if(watchedRate != Time.getRate()){
                    seconds++;
                    this.cancel();
                    startTimer();
                }else{
                    seconds++;
                }
                
            }
        }, 0, Time.getRate());
    }
    
    public void spreadFire(){
        this.onFire = true;
    }
    
    
    public void startTimer(int p){
        this.sprite = new Sprite(x,y,width,height,new ImageIcon("src/sprites/construction.jpg").getImage());
        int watchedRate = Time.getRate();
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(seconds == 0){
                    active = true;
                    setSprite();
                    this.cancel();
                }else if(watchedRate != Time.getRate()){
                    seconds++;
                    this.cancel();
                    startTimer();
                }else{
                    seconds++;
                }
                
            }
        }, 0, Math.round(Time.getRate()*999999999));
    }
    
    public void setSprite(){
    }
    
    public void modifyMood(double rate){
    }
    
    public boolean checkIfFire(){
        return false;
    }
    
    public void setZoneFireRate(){
    }
    
    public void defaultZoneFireRate(){
    }

    public void startPopulate(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(people.size() >= capacity){
                    this.cancel();
                }
                else{
                    people.add(new People(0.5));
                }
            }
        }, 0, Time.getRate());
    }
    
    public void startPopulate(int p){
        
    }
    
    public void draw(Graphics g){
        this.sprite.draw(g);
    }
    
    public Sprite getSprite(){
        return this.sprite;
    }
    
    public int getX(){
        return this.sprite.getX();
    }
    
    public int getY(){
        return this.sprite.getY();
    }
    
    public int getbuildTime(){
        return buildTime;
    }
    
    public void setStartMood(double def){
    }
    
    public int getResNumber(){
        return people.size();
    }
    
    public double getMoodBoost(){
        return this.moodBoost;
    }
    
    public void stopTimer(){
        
    }
    
    public int getTrueY(){
        return this.y;
    }
    
    public int getTrueX(){
        return this.x;
    }
    
    public STADIUMTYPE getType() {
        return this.type;
    }
}