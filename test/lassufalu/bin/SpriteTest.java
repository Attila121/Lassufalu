/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package lassufalu.bin;

import java.awt.Image;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abm
 */
public class SpriteTest {
    
    private Sprite sprite;
    
    @Before
    public void setUp() {
        int x = 0;
        int y = 0;
        int width = 100;
        int height = 100;
        Image image = null;
        sprite = new Sprite(x, y, width, height, image);
    }
    
    /**
     * Test of collides method, of class Sprite.
     */
    @Test
    public void testCollides() {
        Sprite other = new Sprite(10, 10, 50, 50, null);
        boolean result = sprite.collides(other);
        assertTrue(result);
    }

    /**
     * Test of getX method, of class Sprite.
     */
    @Test
    public void testGetX() {
        int expectedX = 0;
        int actualX = sprite.getX();
        assertEquals(expectedX, actualX);
    }

    /**
     * Test of setX method, of class Sprite.
     */
    @Test
    public void testSetX() {
        int newX = 50;
        sprite.setX(newX);
        int expectedX = 50;
        int actualX = sprite.getX();
        assertEquals(expectedX, actualX);
    }

    /**
     * Test of getY method, of class Sprite.
     */
    @Test
    public void testGetY() {
        int expectedY = 0;
        int actualY = sprite.getY();
        assertEquals(expectedY, actualY);
    }

    /**
     * Test of setY method, of class Sprite.
     */
    @Test
    public void testSetY() {
        int newY = 50;
        sprite.setY(newY);
        int expectedY = 50;
        int actualY = sprite.getY();
        assertEquals(expectedY, actualY);
    }

    /**
     * Test of getWidth method, of class Sprite.
     */
    @Test
    public void testGetWidth() {
        int expectedWidth = 100;
        int actualWidth = sprite.getWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    /**
     * Test of setWidth method, of class Sprite.
     */
    @Test
    public void testSetWidth() {
        int newWidth = 0;
        sprite.setWidth(newWidth);
        int expectedWidth = 0;
        int actualWidth = sprite.getWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    /**
     * Test of getHeight method, of class Sprite.
     */
    @Test
    public void testGetHeight() {
        int expectedHeight = 100;
        int actualHeight = sprite.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    /**
     * Test of setHeight method, of class Sprite.
     */
    @Test
    public void testSetHeight() {
        int newHeight = 0;
        sprite.setHeight(newHeight);
        int expectedHeight = 0;
        int actualHeight = sprite.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }
    
}
