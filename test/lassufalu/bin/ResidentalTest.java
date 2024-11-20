/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package lassufalu.bin;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abm
 */
public class ResidentalTest {
    
    private Residental res;
    private Time mockTime;
    
    @Before
    public void setUp() {
        res = new Residental(0,0,10,10);
        res.capacity = 20;
        res.pop = true;
        res.seconds = 0;
        res.moodBoost = 1;
        res.buildTime = 10;
        mockTime = new Time();
        ArrayList<People> people = new ArrayList<>();
        people.add(new People(0.8));
        people.add(new People(0.6));
        people.add(new People(0.9));
        res.people = people;
    }

    /**
     * Test of startTimer method, of class Residental.
     */
    @Test
    public void testStartTimer_0args() { 
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                assertTrue(res.seconds == res.buildTime);
                timer.cancel();
            }
        }, res.buildTime * 3 + 1000);
    }

    /**
     * Test of getSelfMood method, of class Residental.
     */
    @Test
    public void testGetSelfMood() {
        double expectedAverageMood = (0.8 + 0.6 + 0.9) / 3.0;
        double result = res.getSelfMood();

        assertEquals(expectedAverageMood, result, 0.001);
    }

    /**
     * Test of getPeopleNum method, of class Residental.
     */
    @Test
    public void testGetPeopleNum() {
        int expResult = 3;
        int result = res.getPeopleNum();
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyMood method, of class Residental.
     */
    @Test
    public void testModifyMood() {
        double rate = 0.5;
        for (People person : res.people) {
            double expectedMood = person.getSelfMood() * rate;
            res.modifyMood(rate);
            assertEquals(expectedMood, person.getSelfMood(), 0.001);
        }
    }
    
}
