/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package lassufalu.bin;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abm
 */
public class MoodTest {
    
    private Mood mood;

    @Before
    public void setUp() {
        mood = new Mood(0.8);
    }

    /**
     * Test of getAllMood method, of class Mood.
     */
    @Test
    public void testGetAllMood() {
        double expResult = 0.5;
        double result = Mood.getAllMood();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of increaseSelfMood method, of class Mood.
     */
    @Test
    public void testIncreaseSelfMood() {
        mood.increaseSelfMood(1.2);
        double selfMood = mood.getSelfMood();
        assertEquals(0.96, selfMood, 0.001);
    }

    /**
     * Test of decreaseAllMood method, of class Mood.
     */
    @Test
    public void testDecreaseAllMood() {
        Mood.moodSum = 5.0;
        mood.decreaseAllMood(1.2);
        double allMood = Mood.moodSum;
        assertEquals(3.8, allMood, 0.001);
    }

    /**
     * Test of decreseByBlock method, of class Mood.
     */
    @Test
    public void testDecreseByBlock() {
        Mood.moodSum = 5.0;
        mood.decreseByBlock(2, 0.8);
        double allMood = Mood.moodSum;
        assertEquals(3.4, allMood, 0.001);
    }

    /**
     * Test of increaseAllMood method, of class Mood.
     */
    @Test
    public void testIncreaseAllMood() {
        Mood.moodSum = 2.5;
        mood.increaseAllMood(0.8);
        double allMood = Mood.moodSum;
        assertEquals(3.3, allMood, 0.001);
    }

    /**
     * Test of increaseByBlock method, of class Mood.
     */
    @Test
    public void testIncreaseByBlock() {
        Mood.moodSum = 2.5;
        mood.increaseByBlock(3, 0.6);
        double allMood = Mood.moodSum;
        assertEquals(4.3, allMood, 0.001);
    }
    
}
