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
public class Stadium extends Block{
    public enum STADIUMTYPE{
        BLEFT,
        BRIGHT,
        TLEFT,
        TRIGHT
    }
    private STADIUMTYPE type;
    public Stadium(int x, int y,int width, int height,STADIUMTYPE st){
        super(GameEngine.loaded ? 10 : 1,x,y,width,height);
        this.capacity = 10;
        this.type = st;
        this.moodBoost = 1.5;
    }
    
    @Override
    public void setSprite(){
        switch(this.type){
            case BLEFT:{
                this.sprite.updateImage(new ImageIcon("src/sprites/stadium_bleft.jpg").getImage());
                break;
            }
            case TLEFT:{
                this.sprite.updateImage(new ImageIcon("src/sprites/stadium_tleft.jpg").getImage());
                break;
            }
            case BRIGHT:{
                this.sprite.updateImage(new ImageIcon("src/sprites/stadium_bright.jpg").getImage());
                break;
            }
            case TRIGHT:{
                this.sprite.updateImage(new ImageIcon("src/sprites/stadium_tright.jpg").getImage());
                break;
            }
        }
        
    }
    
    
    public STADIUMTYPE getType() {
        return this.type;
    }
}
