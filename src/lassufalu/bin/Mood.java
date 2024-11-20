/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

/**
 *
 * @author Ambrus Balázs Miklós (FDCN9F)
 */
public class Mood {
    
    public static double moodSum = 0;
    public static int moodCount = 0;
    private double selfMood;
    
    public Mood(double selfMood) {
        this.selfMood = selfMood;
    }

    public static double getAllMood() {
        return moodCount != 0 ? moodSum / (double)moodCount : 0.5;
    }
    
    public double getSelfMood(){
        return selfMood;
    }
    
    public void setSelfMood(double selfMood){
        this.selfMood = selfMood;
    }
    
    public void increaseSelfMood(double rate){
        this.selfMood *= rate;
    }
    
    public void decreaseAllMood(double rate){
        moodSum -= rate;
    }
    
    public void decreseByBlock(int count, double rate){
        for(int i = 0; i < count; i++){
            moodSum -= rate;
        }
    }
    
    public void increaseAllMood(double rate){
        moodSum += rate;
    }
    
    public void increaseByBlock(int count, double rate){
        for(int i = 0; i < count; i++){
            moodSum += rate;
        }
    }
    
    
}
