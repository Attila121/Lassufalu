/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Roland
 */
public class Road extends Block{
    
    public Road(int x, int y,int width, int height, int nearby){
        super(0,x,y,width,height);
        // left-right
        Image roadImage = new ImageIcon("src/sprites/road.jpg").getImage();
        // Road left-right
        if(nearby == 0){
            roadImage = new ImageIcon("src/sprites/roadR.jpg").getImage();
        }
        // up
        if(nearby == 1){
            roadImage = new ImageIcon("src/sprites/roadUP.jpg").getImage();
        }
        // left up
        if(nearby == 2){
            roadImage = new ImageIcon("src/sprites/roadLU.jpg").getImage();
        }
        // left down
        if(nearby == 3){
            roadImage = new ImageIcon("src/sprites/roadLD.jpg").getImage();
        }
        // right up
        if(nearby == 4){
            roadImage = new ImageIcon("src/sprites/roadRU.jpg").getImage();
        }
        // right down
        if(nearby == 5){
            roadImage = new ImageIcon("src/sprites/roadRD.jpg").getImage();
        }
        // kereszteződés
        if(nearby == 6){
            roadImage = new ImageIcon("src/sprites/roadK.jpg").getImage();
        }
        
        this.sprite = new Sprite(x,y,width,height,roadImage);
    }
    
    public Road() {}
}
