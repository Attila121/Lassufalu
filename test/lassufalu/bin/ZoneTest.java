/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package lassufalu.bin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abm
 */
public class ZoneTest {
    
    private Zone zone;
    
    @Before
    public void setUp() {
        zone = new Zone(10,0,0,10,10);
        zone.fireRate = 1;
    }
    

    /**
     * Test of checkIfFire method, of class Zone.
     */
    @Test
    public void testCheckIfFire() {
        boolean expResult = true;
        boolean result = zone.checkIfFire();
        assertEquals(expResult, result);
    }

    /**
     * Test of setZoneFireRate method, of class Zone.
     */
    @Test
    public void testSetZoneFireRate() {
        double expResult = 0.01;
        zone.setZoneFireRate();
        assertEquals(expResult, zone.fireRate,0);
    }

    /**
     * Test of defaultZoneFireRate method, of class Zone.
     */
    @Test
    public void testDefaultZoneFireRate() {
        double expResult = 0.03;
        zone.defaultZoneFireRate();
        assertEquals(expResult, zone.fireRate,0);
    }
    
}
