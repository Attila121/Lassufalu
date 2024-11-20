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
public class Firedepartment extends Block{
    
    public Firedepartment(int x, int y, int width, int height){
        super(GameEngine.loaded ? 5 : 1,x,y,width,height);
        this.capacity = 10;
        this.moodBoost = 1.3;
    }
    
    @Override
    public void setSprite(){
        this.sprite.updateImage(new ImageIcon("src/sprites/fire_depart.jpg").getImage());
    }
    
}
