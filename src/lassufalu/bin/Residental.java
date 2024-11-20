/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author Roland
 */
public class Residental extends Zone{
    
    private double startMood = 0.5;
    
    public Residental(int x, int y,int width, int height){
        super(GameEngine.loaded ? 2 : 1,x,y,width,height);
        this.capacity = 10;
        this.capacity = 20;
        this.pop = true;
    }
    
    
    @Override
    public void setSprite(){
        this.sprite.updateImage(new ImageIcon("src/sprites/residental_building.jpg").getImage());
    }
    
    public void setPopulatedSprite(){
        this.sprite.updateImage(new ImageIcon("src/sprites/populated_residental_sprite.jpg").getImage());
    }
    
    
    @Override
    public void startTimer(){
        int watchedRate = Time.getRate();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(seconds == buildTime){
                    active = true;
                    setSprite();
                    startPopulate();
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
    
    @Override
    public void startTimer(int p){
        int watchedRate = Time.getRate();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(seconds == 0){
                    active = true;
                    setSprite();
                    startPopulate(p);
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
    
    //Kell hogy mennyi az alapMood
    @Override
    public void startPopulate(){
        double moodBoost = 1;
        int watchedRate = Time.getRate();
        if(people.size() != 0){
            if(getSelfMood()<=0.25){
                moodBoost = 1.5;
            }else if(getSelfMood()<=0.5){
                moodBoost = 1.2;
            }else if(getSelfMood()<=0.75){
                moodBoost = 0.8;
            }else if(getSelfMood()<=1){
                moodBoost = 0.7;
            }
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(people.size() == capacity / 2){
                    setPopulatedSprite();
                }
                if(people.size() >= capacity || pop == false){
                    this.cancel();
                }else if(watchedRate != Time.getRate()){
                    this.cancel();
                    startPopulate();
                }
                else{
                    people.add(new People(startMood));
                    Mood.moodSum += startMood;
                    Mood.moodCount +=1;
                }
            }
        }, 0, Math.round(Time.getRate()*moodBoost));
    }
    
    @Override
    public void startPopulate(int p){
        for(int i = 0; i < p;i++){
            people.add(new People(startMood));
            Mood.moodSum += startMood;
            Mood.moodCount +=1;
        }
    }
    
    @Override
    public double getSelfMood(){
        //Összeszámlálós algoritmus
        double sum = 0;
        for(int i = 0; i < people.size();i++){
            sum += people.get(i).mood.getSelfMood();
        }
        return sum / (double)people.size()>1 ? 1 : sum / (double)people.size();
    }
    
    @Override
    public void setStartMood(double rate){
        this.startMood *= rate;
        modifyMood(rate);
    }
    
    public int getPeopleNum(){
        return people.size();
    }
    
    public void checkAges(){
        for(int i = 0; i < people.size(); i++){
            if(people.get(i).endOfLife()){
                Mood.moodCount -= 1;
                Mood.moodSum -= people.get(i).getSelfMood();
                people.remove(i);
                startPopulate();
            }
        }
    }
    
    @Override
    public void modifyMood(double rate){
        for(int i = 0; i < people.size();i++){
            double oldMood = people.get(i).getSelfMood();
            Mood.moodSum -= oldMood;
            people.get(i).setSelfMood(oldMood*rate);
            Mood.moodSum += oldMood*rate;
        }
    }
    
    @Override
    public void stopTimer(){
        this.pop = false;
    }
    
}
