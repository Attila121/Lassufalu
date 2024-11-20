/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

/**
 *
 * @author Ambrus Balázs Miklós (FDCN9F)
 */
public class Finances {
    private static double budget;

    public Finances(double v) {
        budget = v;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }
    
    
}