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
public class ClickHandlerTest {
    
    private ClickHandler clickHandler;

    @Before
    public void setUp() {
        clickHandler = new ClickHandler();
    }

    /**
     * Test of clickOnSelect method, of class ClickHandler.
     */
    @Test
    public void testClickOnSelect() {
        clickHandler.clickOnSelect();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.SELECT, currentMouseMode);

    }

    /**
     * Test of clickOnCommercial method, of class ClickHandler.
     */
    @Test
    public void testClickOnCommercial() {
        clickHandler.clickOnCommercial();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.COMMERCIAL, currentMouseMode);
    }

    /**
     * Test of clickOnResidental method, of class ClickHandler.
     */
    @Test
    public void testClickOnResidental() {
        clickHandler.clickOnResidental();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.RESIDENTAL, currentMouseMode);
    }

    /**
     * Test of clickOnIndustrial method, of class ClickHandler.
     */
    @Test
    public void testClickOnIndustrial() {
        clickHandler.clickOnIndustrial();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.INDUSTRIAL, currentMouseMode);
    }

    /**
     * Test of clickOnFireDepartment method, of class ClickHandler.
     */
    @Test
    public void testClickOnFireDepartment() {
        clickHandler.clickOnFireDepartment();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.FIREDEPARTMENT, currentMouseMode);
    }

    /**
     * Test of clickOnPolice method, of class ClickHandler.
     */
    @Test
    public void testClickOnPolice() {
        clickHandler.clickOnPolice();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.POLICE, currentMouseMode);
    }

    /**
     * Test of clickOnStadium method, of class ClickHandler.
     */
    @Test
    public void testClickOnStadium() {
        clickHandler.clickOnStadium();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.STADIUM, currentMouseMode);
    }

    /**
     * Test of clickOnForest method, of class ClickHandler.
     */
    @Test
    public void testClickOnForest() {
        clickHandler.clickOnForest();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.FOREST, currentMouseMode);
    }

    /**
     * Test of clickOnRemove method, of class ClickHandler.
     */
    @Test
    public void testClickOnRemove() {
        clickHandler.clickOnRemove();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.REMOVE, currentMouseMode);
    }

    /**
     * Test of clickOnRoad method, of class ClickHandler.
     */
    @Test
    public void testClickOnRoad() {
        clickHandler.clickOnRoad();
        ClickHandler.mouseMode currentMouseMode = ClickHandler.getMouseMode();
        assertEquals(ClickHandler.mouseMode.ROAD, currentMouseMode);
    }
    
}
