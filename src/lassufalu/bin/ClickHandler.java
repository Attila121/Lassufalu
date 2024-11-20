/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;



/**
 *
 * @author Roland
 */
public class ClickHandler {
    enum mouseMode{
        SELECT,
        RESIDENTAL,
        COMMERCIAL,
        INDUSTRIAL,
        STADIUM,
        FIREDEPARTMENT,
        POLICE,
        FOREST,
        ROAD,
        REMOVE
    }
    private static mouseMode mouse;
    
    public ClickHandler(){
        mouse = mouseMode.SELECT;
    }
    
    public void clickOnSelect(){
        mouse = mouseMode.SELECT;
    }
    public void clickOnCommercial(){
        mouse = mouseMode.COMMERCIAL;
    }
    public void clickOnResidental(){
        mouse = mouseMode.RESIDENTAL;
    }
    public void clickOnIndustrial(){
        mouse = mouseMode.INDUSTRIAL;
    }
    public void clickOnFireDepartment(){
        mouse = mouseMode.FIREDEPARTMENT;
    }
    public void clickOnPolice(){
        mouse = mouseMode.POLICE;
    }
    public void clickOnStadium(){
        mouse = mouseMode.STADIUM;
    }
    public void clickOnForest(){
        mouse = mouseMode.FOREST;
    }
    public void clickOnRemove(){
        mouse = mouseMode.REMOVE;
    }
    public void clickOnRoad(){
        mouse = mouseMode.ROAD;
    }
    public static mouseMode getMouseMode(){
        return mouse;
    }
}
