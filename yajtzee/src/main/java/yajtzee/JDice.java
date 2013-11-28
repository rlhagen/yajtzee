/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yajtzee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *Graphical JPanel that holds 5 die
 * @author babka-lipka
 */
@SuppressWarnings("serial")
public class JDice extends JPanel implements ActionListener{

    private final JPanel dicePanel, rollPanel;
    protected Die[] dice = new Die[5];
    private final JButton roll;
    protected int rollnum = 0;

    public JDice() {
        
        this.setLayout(new BorderLayout());

        FlowLayout layout = new FlowLayout();
        layout.setHgap(15);
        dicePanel = new JPanel(layout);

        this.add(dicePanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEtchedBorder());

        initDice();//adds the Die to the pane
        //Dimension d = dice[0].getPreferredSize();

        rollPanel = new JPanel();
        this.add(rollPanel, BorderLayout.PAGE_END);

        roll = new JButton("Roll ");
        roll.setHorizontalTextPosition(JButton.LEADING);
        roll.setPreferredSize(new Dimension(340, 35));

        roll.setToolTipText("Roll the dice.");
        roll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/misc/roll.png")));
        roll.addActionListener(this);
        //this.add(roll, BorderLayout.PAGE_END);
        rollPanel.add(roll);
    }

    private void initDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
            dicePanel.add(dice[i]);
        }
    }

    public void resetDice() {
        for (Die d : dice) {
            d.setSelected(false);
            d.setDefaultIcon();
        }
        rollnum = 0;
        roll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/misc/roll.png")));
    }

    /**
     * ActionListener, rolls all the dice that isn't selected...
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        rollDice();
    }

    public void rollDice(){
        if (rollnum < 3) {
            rollnum++;
            for (Die d : dice) {
                if (!d.isSelected()) {
                    try {
                        d.roll();
                    } catch (Exception ex) {
                        Logger.getLogger(JDice.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        roll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/misc/" + rollnum + ".png")));//sets the label to current roll number
    }

}
