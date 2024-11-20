/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;
import java.util.ArrayList;


/**
 *
 * @author Roland
 */
public class Tile {
    private ArrayList<Block> blocks;
    
    public Tile(ArrayList<Block> blocks){
        this.blocks = blocks;
    }
    
    
    public int blockCount(){
        return this.blocks.size();
    }
    
}
