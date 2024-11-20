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
public class Grass extends Block{
    
    public Grass(int x, int y,int width, int height){
        super(0,x,y,width,height);
        this.active = true;
        Image grassImage = new ImageIcon("src/sprites/grass.jpg").getImage();
        this.sprite = new Sprite(x,y,width,height,grassImage);
    }

    public Grass() {}
    
}
