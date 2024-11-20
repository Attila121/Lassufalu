/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Roland
 */
public class MouseClickListener extends MouseAdapter {
    private int mouseX;
    private int mouseY;
    private int blockWidth;
    private int blockHeight;
    public MouseClickListener(int blockWidth, int blockHeight){
        super();
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
    }
    
    public int calculateXBlock(){
        return this.mouseX/this.blockWidth;
    }
    
    public int calculateYBlock(){
        return this.mouseY/this.blockHeight;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        System.out.println("MouseX: "+this.mouseX+", MouseY:"+this.mouseY);
    }
    
    
}
