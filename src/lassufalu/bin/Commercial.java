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
public class Commercial extends Zone {

    public Commercial(int x, int y, int width, int height) {
        super(5, x, y, width, height);
        this.capacity = 20;
    }

    @Override
    public void setSprite() {
        this.sprite.updateImage(new ImageIcon("src/sprites/commercial.jpg").getImage());
    }

    public void setWorker(People p) {
        this.people.add(p);
    }

    public void removeWorker(People p) {
        this.people.remove(p);
    }

}
