/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tax;

/**
 *
 * @author Mielec Attila (MNZVUM)
 */
import lassufalu.bin.GameGUI;

public class MonthTax {

    public MonthTax() {

    }

    public void setBudget() {
        double budget = GameGUI.getBudget();
        double rtax, ctax, itax, stax;
        rtax = ((GameGUI.getValues()[0]) * GameGUI.getPopulation());
        ctax = ((GameGUI.getValues()[1]) * GameGUI.getCount()[1]) * 10;
        itax = ((GameGUI.getValues()[2]) * GameGUI.getCount()[2]) * 10;
        stax = ((GameGUI.getValues()[3]) * GameGUI.getCount()[3]) * 10;

        budget = budget + rtax + ctax + itax + stax;

        budget = budget - (GameGUI.getCount()[4] * 20) - (GameGUI.getCount()[5] * 200)
                - (GameGUI.getCount()[6] * 200) - (GameGUI.getCount()[7] * 100);

        GameGUI.setMoney(Double.toString(budget) + " $");
    }
}
