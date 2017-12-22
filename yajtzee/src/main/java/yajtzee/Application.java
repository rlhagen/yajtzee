/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yajtzee;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

/**
 * @author babka-lipka
 */
@SuppressWarnings("serial")
public class Application extends JFrame {

    public Application() {
        try {
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit
        this.setTitle("Yajtzee");
        this.setResizable(false);
        Container pane = getContentPane(); //grabs the content pane for manipulation
        BorderLayout layout = new BorderLayout();
        pane.setLayout(layout);
        pane.add(new Yajtzee(), BorderLayout.CENTER);
        this.pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {//creates own thread for UI

            public void run() {//implemented from Runnable interface
                new Application().setVisible(true);
            }
        });

    }
}
