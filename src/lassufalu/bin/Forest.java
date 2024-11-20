/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import javax.swing.ImageIcon;

/**
 *
 * @author Roland
 */
public class Forest extends Block{
    public int buildYear;
    public Forest(int x, int y,int width, int height){
        super(GameEngine.loaded ? 5 : 1,x,y,width,height);
        this.capacity = 0;
        this.moodBoost = 1.2;
        this.buildYear = Time.year;
    }
    
    @Override
    public void setSprite(){
        this.sprite.updateImage(new ImageIcon("src/sprites/forest.jpg").getImage());
    }
    
    public int getAge(){
        return buildYear - Time.year;
    }
    
    public void checkAge(){
        if(getAge()<=2&&getAge()>0){
            
        }else if(getAge()<=6){
            this.moodBoost = 1.3;
        }else if(getAge()<=10){
            this.moodBoost = 1.5;
        }
    }
    
}
