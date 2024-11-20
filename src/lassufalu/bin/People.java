/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Roland
 */
public class People {
    enum Job{
        COMMERCIALWORKER,
        INDUSTRIALWORKER,
        SPECIALBUILDINGWORKER
    }
    private int age;
    public Mood mood;
    public Block workSpace;
    public People(double mood){
        Random r = new Random();
        this.age = (int)Math.floor(Math.random() *(60 - 18 + 1) + 18);
        GameGUI.setPopulation(1);
        this.mood = new Mood(mood);
    }
    
    public void scheduleAge(){
        int initialRate = Time.getRate();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(Time.getRate() != initialRate){
                    this.cancel();
                    scheduleAge();
                }
                age++;
            }
        }, 0, Time.getRate());
        
    }
    
    public void setWorkSpace(Block b){
        this.workSpace = b;
    }
    
    public void removeWorkSpace(){
        this.workSpace = null;
    }
    
    public void setSelfMood(double rate){
        this.mood.setSelfMood(rate);
    }
    
    public double getSelfMood(){
        return this.mood.getSelfMood();
    }
    
    public int getAge(){
        return age;
    }
    
    public boolean endOfLife(){
        Random r = new Random();
        return r.nextDouble() <= 0.2 && age >=65;
    }
    
}
