/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Roland
 */
public class Zone extends Block{
    protected double fireRate = 0.03;
    public Zone(int buildtime,int x, int y,int width, int height){
        super(buildtime,x,y,width,height);
        this.sprite = new Sprite(x,y,width,height,new ImageIcon("src/sprites/construction.jpg").getImage());
    }
    
    @Override
    public boolean checkIfFire(){
        Random r = new Random();
        boolean result = r.nextDouble() <= fireRate;
        this.onFire = result ? true : false;
        return result;
    }
    
    @Override
    public void setZoneFireRate(){
        this.fireRate = 0.01;
    }
    
    @Override
    public void defaultZoneFireRate(){
        this.fireRate = 0.03;
    }
    
    
}
