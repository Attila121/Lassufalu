/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import javax.swing.ImageIcon;

/**
 *
 * @author Mielec Attila (MNZVUM)
 */
public class Industrial extends Zone {

    public Industrial(int x, int y, int width, int height) {
        super(GameEngine.loaded ? 2 : 1, x, y, width, height);
        this.moodBoost = 0.8;
        this.capacity = 15;
    }

    @Override
    public void setSprite() {
        this.sprite.updateImage(new ImageIcon("src/sprites/industrial.jpg").getImage());
    }

    public void setWorker(People p) {
        this.people.add(p);
    }

    public void removeWorker(People p) {
        this.people.remove(p);
    }

}
