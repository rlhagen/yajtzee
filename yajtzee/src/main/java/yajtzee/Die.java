/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yajtzee;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;

public class Die extends JToggleButton implements ItemListener, Comparable {

    private final int min = 1;
    private final int max = 6;
    private int num = 0;
    private final Random rand = new Random(System.currentTimeMillis());
    private final ImageIcon defaultImage = new javax.swing.ImageIcon(getClass().getResource("/icons/misc/load.png"));

    public Die() {
        setDefaultIcon();
        setPreferredSize(new Dimension(55, 55));
        addItemListener(this);
    }

    public void setDefaultIcon() {
        setIcon(defaultImage);
    }


    public int roll() throws Exception {
        Animation updater = new Animation();
        updater.execute();
        num = rand.nextInt(max - min + 1) + min;
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unselected/" + num + ".png")));
        return num;
    }

    public int getValue() {
        return num;
    }

    public void setValue(int value) {
        this.num = value;
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/selected/" + num + ".png")));
        } else {
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unselected/" + num + ".png")));
        }

    }

    public int compareTo(Object o) {
        Die d = (Die) o;
        return this.getValue() - d.getValue();
    }

    class Animation extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            for (int i = 0; i < 15; i++) {
                num = rand.nextInt(max - min + 1) + min;
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unselected/" + num + ".png")));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Die.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
        }
    }
}
