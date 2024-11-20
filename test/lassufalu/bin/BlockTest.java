/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package lassufalu.bin;

import java.util.ArrayList;
import lassufalu.bin.People;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abm
 */
public class BlockTest {
    
    private Block block;
    
    @Before
    public void setUp() {
        block = new Block(5,10,20,30,40);
        block.active = false;
        block.seconds = 0;
        block.capacity = 10;
        block.people = new ArrayList<People>();
        block.onFire = false;
    }

    /**
     * Test of getCapacity method, of class Block.
     */
    @Test
    public void testGetCapacity() {
        int expResult = 10;
        int result = block.getCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of putOutFire method, of class Block.
     */
    @Test
    public void testPutOutFire() {
        block.putOutFire();
        assertFalse(block.onFire);
    }
    
    /**
    * Test of spreadFire method, of class Block.
    */
    @Test
    public void testSpreadFire() {
        block.spreadFire();
        assertTrue(block.onFire);
    }
    
    /**
     * Test of isOnFire method, of class Block.
     */
    @Test
    public void testIsOnFire() {
        boolean expResult = false;
        block.isOnFire();
        assertEquals(expResult, block.onFire);
    }
    
    /**
     * Test of getSelfMood method, of class Block.
     */
    @Test
    public void testGetSelfMood() {
        double expResult = 0;
        double result = block.getSelfMood();
        assertEquals(expResult, result, 0);
    }
    
}
