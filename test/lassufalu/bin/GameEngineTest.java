/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change gameEngine license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit gameEngine template
 */
package lassufalu.bin;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abm
 */
public class GameEngineTest {
    
    private GameEngine gameEngine;
    private static Block[][] blocks;
    
    @Before
    public void setUp() {
        gameEngine = new GameEngine(10,10);
        blocks = new Block[5][5];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                blocks[i][j] = new Grass(0,0,10,10);
            }
        }
        blocks[1][0] = new Road(1,0,10,10,0);
        gameEngine.setBlocks(blocks);
    }

    /**
     * Test of seeIfThereIsWorkPlaceInd method, of class GameEngine.
     */
    @Test
    public void testSeeIfThereIsWorkPlaceInd() {
        Industrial ind = new Industrial(0,0,10,10);
        ArrayList<Industrial> list = new ArrayList<>();
        list.add(ind);
        boolean expResult = true;
        boolean result = gameEngine.seeIfThereIsWorkPlaceInd(list);
        assertEquals(expResult, result);
    }

    /**
     * Test of seeIfThereIsWorkPlaceCom method, of class GameEngine.
     */
    @Test
    public void testSeeIfThereIsWorkPlaceCom() {
        ArrayList<Commercial> list = new ArrayList<>();
        boolean expResult = false;
        boolean result = gameEngine.seeIfThereIsWorkPlaceCom(list);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfEmptyBlock method, of class GameEngine.
     */
    @Test
    public void testCheckIfEmptyBlock() {
        boolean expResult = true;
        boolean result = gameEngine.checkIfEmptyBlock(0, 0);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkIfEmptyBlock method, of class GameEngine.
     */
    @Test
    public void testCheckForRoad() {
        boolean expResult = true;
        boolean result = gameEngine.checkForRoad(0, 0);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkBlockIf method, of class GameEngine.
     */
    @Test
    public void testCheckBlockIf() {
        boolean expResult = true;
        boolean result = gameEngine.checkBlockIf(0, 0);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of placeResidental method, of class GameEngine.
     */
    @Test
    public void testPlaceResidental() {
        gameEngine.placeResidental(0, 0, 0, 0, 200);
        Residental r = new Residental(0,0,10,10);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }

    /**
     * Test of placeCommercial method, of class GameEngine.
     */
    @Test
    public void testPlaceCommercial() {
        gameEngine.placeCommercial(0, 0, 0, 0, 200);
        Commercial r = new Commercial(0,0,10,10);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }
    
    /**
     * Test of placeIndustrial method, of class GameEngine.
     */
    @Test
    public void testPlaceIndustrial() {
        gameEngine.placeIndustrial(0, 0, 0, 0, 200);
        Industrial r = new Industrial(0,0,10,10);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }
    
    /**
     * Test of placeRoad method, of class GameEngine.
     */
    @Test
    public void testPlaceRoad() {
        gameEngine.placeRoad(0, 0);
        Road r = new Road(0,0,10,10,0);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }
    
    /**
     * Test of placeStadium method, of class GameEngine.
     */
    @Test
    public void testPlaceStadium() {
        boolean expResult = false;
        boolean result = gameEngine.placeStadium(3, 3,0,0,200);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkIfStadiumCanBePlacedTop method, of class GameEngine.
     */
    @Test
    public void testCheckIfStadiumCanBePlacedTop() {
        boolean expResult = true;
        boolean result = gameEngine.checkIfStadiumCanBePlacedTop(3, 3);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfStadiumCanBePlacedBottom method, of class GameEngine.
     */
    @Test
    public void testCheckIfStadiumCanBePlacedBottom() {
        boolean expResult = true;
        boolean result = gameEngine.checkIfStadiumCanBePlacedTop(3, 3);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of placePoliceStation method, of class GameEngine.
     */
    @Test
    public void testPlacePoliceStation() {
        gameEngine.placePoliceStation(0, 0,0,0,100);
        Police r = new Police(0,0,10,10);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }
    
    /**
     * Test of placeForest method, of class GameEngine.
     */
    @Test
    public void testPlaceForest() {
        gameEngine.placeForest(0, 0,0,0,100);
        Forest r = new Forest(0,0,10,10);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }
    
    /**
     * Test of placeFireStation method, of class GameEngine.
     */
    @Test
    public void testPlaceFireStation() {
        gameEngine.placeFireStation(0, 0,0,0,100);
        Firedepartment r = new Firedepartment(0,0,10,10);
        assertEquals(r.getClass(), blocks[0][0].getClass());
    }

    /**
     * Test of checkIfResBlock method, of class GameEngine.
     */
    @Test
    public void testCheckIfResBlock() {
        Residental b = new Residental(0,0,0,0);
        boolean expResult = true;
        boolean result = gameEngine.checkIfResBlock(b);
        assertEquals(expResult, result);
    }

    /**
     * Test of setXstartPosition method, of class GameEngine.
     */
    @Test
    public void testSetXstartPosition() {
        int expResult = 2;
        int actual = gameEngine.setXstartPosition(5);
        assertEquals(expResult, actual);
    }

    /**
     * Test of setYstartPosition method, of class GameEngine.
     */
    @Test
    public void testSetYstartPosition() {
        int expResult = 4;
        int actual = gameEngine.setYstartPosition(7);
        assertEquals(expResult, actual);
    }

    /**
     * Test of setXEndPosition method, of class GameEngine.
     */
    @Test
    public void testSetXEndPosition() {
        int expResult = 4;
        int actual = gameEngine.setXEndPosition(2);
        assertEquals(expResult, actual);
    }

    /**
     * Test of setYEndPosition method, of class GameEngine.
     */
    @Test
    public void testSetYEndPosition() {
        int expResult = 3;
        int actual = gameEngine.setYEndPosition(0);
        assertEquals(expResult, actual);
    }

    /**
     * Test of getNeighbors method, of class GameEngine.
     */
    @Test
    public void testGetNeighbors() {
        Block[][] blocks = {
            { new Grass(), new Road(), new Grass() },
            { new Grass(), new Road(), new Grass() },
            { new Grass(), new Road(), new Grass() }
        };
    
        GameEngine gameEngine = new GameEngine(10, 10);
        gameEngine.setBlocks(blocks);
    
        Block block = blocks[1][1]; // középső Road blokk
        ArrayList<Block> neighbors = gameEngine.getNeighbors(block);
        
        assertEquals(1, neighbors.size());
    
        // Ellenőrzés, hogy minden szomszédos blokk Road típusú-e
        for (Block neighbor : neighbors) {
            assertTrue(neighbor instanceof Road);
        }  
    }
    
}
