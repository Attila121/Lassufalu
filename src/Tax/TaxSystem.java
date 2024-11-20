/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tax;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lassufalu.bin.GameGUI;

/**
 *
 * @author Mielec Attila (MNZVUM)
 */
public class TaxSystem {

    private JFrame frame;
    private JButton exitButton;
    private JButton revenueButton;
    private JPanel panel;
    private JPanel buttonPanel;
    private CounterPanel cPanel1, cPanel2, cPanel3, cPanel4;
    private int[] values = new int[]{0, 0, 0, 0};
    private String[] names;

    public TaxSystem(int[] values) {
        this.values = values;
        names = new String[]{"Residential zone", "Commercial zone", "Industrial zone", "Stadium"};
        frame = new JFrame("Taxes");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        cPanel1 = new CounterPanel(names[0], values[0]);
        cPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cPanel1);

        cPanel2 = new CounterPanel(names[1], values[1]);
        cPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cPanel2);

        cPanel3 = new CounterPanel(names[2], values[2]);
        cPanel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cPanel3);

        cPanel4 = new CounterPanel(names[3], values[3]);
        cPanel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cPanel4);

        // Create new buttons and set their action listeners
        revenueButton = new JButton("Revenue and expands");
        revenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewWindow();
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(revenueButton);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(buttonPanel);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(80, 40));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values[0] = cPanel1.getValue();
                values[1] = cPanel2.getValue();
                values[2] = cPanel3.getValue();
                values[3] = cPanel4.getValue();
                GameGUI.t.normalSpeed();
                frame.dispose();
            }
        });
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(exitButton);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public int[] getValues() {
        return this.values;
    }

    private void createNewWindow() {
        JFrame newFrame = new JFrame("Revenue and expands");
        newFrame.setSize(400, 300);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());

        JPanel variablePanel = createVariablePanel();
        newPanel.add(variablePanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
            }
        });

        newPanel.add(backButton, BorderLayout.SOUTH);
        newFrame.add(newPanel);
        newFrame.setVisible(true);
    }

    private JPanel createVariablePanel() {
        JPanel variablePanel = new JPanel();
        variablePanel.setLayout(new BoxLayout(variablePanel, BoxLayout.Y_AXIS));

        int rtax, ctax, itax, stax, rev, expands;
        rtax = ((cPanel1.getValue()) * GameGUI.getPopulation());
        ctax = ((cPanel2.getValue()) * GameGUI.getCount()[1]) * 10;
        itax = ((cPanel3.getValue()) * GameGUI.getCount()[2]) * 10;
        stax = ((cPanel4.getValue()) * GameGUI.getCount()[3]) * 10;
        rev = rtax + ctax + itax + stax;
        JLabel label0 = new JLabel("Revenues:");
        JLabel label1 = new JLabel("Residential taxes: " + (cPanel1.getValue()) + " * " + GameGUI.getPopulation() + " = " + (cPanel1.getValue()) * GameGUI.getPopulation() + "$");
        JLabel label2 = new JLabel("Commertial taxes: " + cPanel2.getValue() + " * " + 10 + " * " + GameGUI.getCount()[1] + " = " + (cPanel2.getValue()) * (GameGUI.getCount()[1]) * 10 + "$");
        JLabel label3 = new JLabel("Industrial taxes: " + cPanel3.getValue() + " * " + 10 + " * " + GameGUI.getCount()[2] + " = " + (cPanel3.getValue()) * (GameGUI.getCount()[2]) * 10 + "$");
        JLabel label4 = new JLabel("Stadium taxes: " + cPanel4.getValue() + " * " + 10 + " * " + GameGUI.getCount()[3] + " = " + (cPanel4.getValue()) * (GameGUI.getCount()[3]) * 10 + "$");
        JLabel labelRev = new JLabel("Sum revenues: " + rev + "$");
        JLabel label5 = new JLabel("Expands: ");
        JLabel label6 = new JLabel("Road expand: " + GameGUI.getCount()[4] + " * " + 20 + " = -" + GameGUI.getCount()[4] * 20 + "$");
        JLabel label7 = new JLabel("Police expand: " + GameGUI.getCount()[5] + " * " + 200 + " = -" + GameGUI.getCount()[5] * 20 + "$");
        JLabel label8 = new JLabel("Firedepartment expand: " + GameGUI.getCount()[6] + " * " + 200 + " = -" + GameGUI.getCount()[6] * 20 + "$");
        JLabel label9 = new JLabel("Forest expand: " + GameGUI.getCount()[7] + " * " + 100 + " = -" + GameGUI.getCount()[7] * 20 + "$");
        JLabel labelExpands = new JLabel("Sum expands: -" + (GameGUI.getCount()[4] * 20 + GameGUI.getCount()[5] * 20
                + GameGUI.getCount()[6] * 20 + GameGUI.getCount()[7] * 20) + "$");

        JLabel label10 = new JLabel(" ");
        JLabel labelTotal = new JLabel("Total : " + (rev - (GameGUI.getCount()[4] * 20 + GameGUI.getCount()[5] * 20
                + GameGUI.getCount()[6] * 20 + GameGUI.getCount()[7] * 20)) + "$");

        variablePanel.add(label0);
        variablePanel.add(label1);
        variablePanel.add(label2);
        variablePanel.add(label3);
        variablePanel.add(label4);
        variablePanel.add(labelRev);
        variablePanel.add(label5);
        variablePanel.add(label6);
        variablePanel.add(label7);
        variablePanel.add(label8);
        variablePanel.add(label9);
        variablePanel.add(labelExpands);
        variablePanel.add(label10);
        variablePanel.add(labelTotal);

        return variablePanel;
    }

}
