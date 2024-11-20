/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tax;

/**
 *
 * @author Mielec Attila (MNZVUM)
 */
import javax.swing.*;
import java.awt.*;
import static java.awt.SystemColor.text;

public class CounterPanel extends JPanel {

    private int value; // kezdeti érték
    private JLabel label, label2;

    public CounterPanel(String text, int value) {
        this.value = value;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // BoxLayout használata
        label = new JLabel(text); // JLabel hozzáadása
        add(label);
        // hozzáadja a + gombot
        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            this.value++;
            label2.setText(Integer.toString(this.value)); // értékek frissítése
        });
        add(addButton);

        label2 = new JLabel(Integer.toString(this.value));
        add(label2);

        // hozzáadja a - gombot
        JButton minusButton = new JButton("-");
        minusButton.addActionListener(e -> {
            this.value--;
            label2.setText(Integer.toString(this.value)); // értékek frissítése
        });
        add(minusButton);
    }

    // a számláló értékének beállítása
    public void setValue(int value) {
        this.value = value;
        label2.setText(Integer.toString(value)); // értékek frissítése
    }

    // a számláló értékének lekérdezése
    public int getValue() {
        return value;
    }

}
