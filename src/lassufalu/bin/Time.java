/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import Tax.MonthTax;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ambrus Balázs Miklós (FDCN9F)
 */
public class Time {
    private int day;
    private int seconds;
    private Timer timer;
    private int month;
    public static int year;
    private MonthTax mTax = new MonthTax();
    private static int rate;
    public boolean lose;
    public static LocalDate lDate;
    public Time() {
        timer = new Timer();
        lose = false; 
    }
    
    private void scheduleTimer(double r) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        rate = (int)Math.floor(1000 * r);
        lDate = LocalDate.parse(df.format(currentDate),formatter );
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                if (seconds == 1) {
                    day++;
                    seconds = 0;
                    cal.setTime(currentDate);
                    cal.add(Calendar.DATE, day);
                    Date uD = cal.getTime();
                    month = cal.get(Calendar.MONTH);
                    year = cal.get(Calendar.YEAR);
                    
                    GameGUI.setDate(df.format(uD));
                    
                    if(GameGUI.getMonth() == getCurrentMonth()){
                        int m = getCurrentMonth() + 1;
                        mTax.setBudget();
                        GameGUI.setMonth(m);
                        
                        if( GameGUI.getBudget() >= 0){
                            lose = false;
                        }
                    }
                    if(GameGUI.getBudget() < 0 && lose == false){
                            lose = true;
                            lDate = LocalDate.parse(df.format(uD),formatter );
                            System.out.println(lDate);
                    }
                    boolean b = df.format(uD).equals(lDate.plusYears(1).format(formatter));
                    if(b && GameGUI.getBudget() < 0 ){
                        GameGUI.endGame();
                    }
                    

                }
            }
        }, 0, rate);
    }
    
    private void scheduleTimerLoaded(double r) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal = Calendar.getInstance();
            String date = GameMenu.getDate();
            String[] parts = date.split("/");            
            String y = parts[0];
            String mo = parts[1] ;
            String d = parts[2];
            int m = Integer.parseInt(mo) -1 ;
            cal.set(Calendar.YEAR, Integer.parseInt(y));
            cal.set(Calendar.MONTH, m); // Months are 0-based in Calendar
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");


            Date currentDate = cal.getTime();
            rate = (int)Math.floor(1000 * r);

            timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                if (seconds == 1) {
                    day++;
                    seconds = 0;
                    cal.setTime(currentDate);
                    cal.add(Calendar.DATE, day);
                    Date uD = cal.getTime();
                    month = cal.get(Calendar.MONTH);
                    year = cal.get(Calendar.YEAR);

                    GameGUI.setDate(df.format(uD));
                    if(GameGUI.getMonth() == getCurrentMonth()){
                        int m = getCurrentMonth() + 1;
                        mTax.setBudget();
                        GameGUI.setMonth(m);
                        if(GameGUI.getBudget() < 0 && !lose){
                            lose = true;
                            lDate = LocalDate.parse(df.format(uD),formatter );
                        }
                        if( GameGUI.getBudget() >= 0){
                            lose = false;
                        }
                        
                        

                    }
                    if(GameGUI.getYear() == getCurrentYear()){
                        int y = getCurrentYear() + 1;
                        /*double budget = GameGUI.getBudget(); 

                        budget = budget - (GameGUI.getCount()[4] * 20);
                        budget -= GameGUI.getCount()[5]*300 + GameGUI.getCount()[6]*300 + GameGUI.getCount()[7]*200;

                        GameGUI.setMoney(Double.toString(budget) + " $");*/
                        GameGUI.setYear(y);
                    }
                    if(GameGUI.getBudget() < 0 && lose == false){
                               lose = true;
                               lDate = LocalDate.parse(df.format(uD),formatter );
                               System.out.println(lDate);
                       }
                       boolean b = df.format(uD).equals(lDate.plusYears(1).format(formatter));
                       if(b && GameGUI.getBudget() < 0 ){
                           GameGUI.endGame();
                       }
                }
            }
        }, 0, rate);
    }
    
    public void normalTime(double r) {
        if(timer == null) {
            timer = new Timer();
        }
        if(GameGUI.getLoaded()){
            scheduleTimerLoaded(r);
        }else{
            scheduleTimer(r);
        }
    }
    
    public void stopTime() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void normalSpeed() {
        normalTime(3);
    }
    
    public void speedUpTimeFast() {
        normalTime(1.4);
    }
    
    public void speedUpTimeFaster() {
        normalTime(0.5);
    }
    
    public void slowDownTime() {
        normalTime(4);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getDay() {
        return day;
    }

    public int getSeconds() {
        return seconds;
    }
    
    public int getCurrentMonth() {
        return month;
    }
    
    public static int getRate(){
        return rate;
    }
    public int getCurrentYear() {
        return year;
    }
    
    
    
    
}
