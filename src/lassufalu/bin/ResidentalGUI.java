/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import Tax.CounterPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mielec Attila (MNZVUM)
 */
public class ResidentalGUI {
    private JFrame frame;
    private JButton exitButton;
    private JPanel panel;
    private JLabel moodLabel;
    
    //private final JPanel panel;
    
    
    public ResidentalGUI(String title, double mood, int num, int capacity){
        String moodEmoji = "";
        if(mood <= 0.25){
            moodEmoji = "😠";
        } else if(mood <= 0.4){
            moodEmoji = "😟";
        } else if(mood <= 0.6){
            moodEmoji = "😐";
        } else if(mood <= 0.8){
            moodEmoji = "🙂";
        } else {
            moodEmoji = "😁";
        }
        
        
        frame = new JFrame(title);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        moodLabel = new JLabel("The capacity of the zone: "+ Integer.toString(capacity));
        panel.add(moodLabel);
        moodLabel = new JLabel("The number of residents: "+ Integer.toString(num));
        panel.add(moodLabel);
        moodLabel = new JLabel("The mood of the residents: "+ moodEmoji);
        panel.add(moodLabel);
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(80, 40));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameGUI.t.normalSpeed();
                frame.dispose(); // Close the window when the exit button is clicked
            }
        });
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panel.add(exitButton);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}
