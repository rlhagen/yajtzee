/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.score;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *A JFrame that holds the highscores for the games played thus far...
 * Holds a JTable with the values...
 *
 * @author babka-lipka
 */
@SuppressWarnings("serial")
public class JHighScore extends JFrame {

    private final String[] columns = {"Player", "Date", "Score"};
    private ArrayList<Score> scores = new ArrayList<Score>();
    private final JTable table;
    private TableModel tablemodel;

    public JHighScore() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//exit
        this.setTitle("High Scores");
        this.setResizable(false);
        Container pane = getContentPane(); //grabs the content pane for manipulation
        BorderLayout layout = new BorderLayout();
        pane.setLayout(layout);
        readData();//reads in existing scores in data file
        tablemodel = new DefaultTableModel(getTwoArray(), columns);
        table = new JTable(tablemodel);
        table.setEnabled(false);
        table.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(table);//ScrollPane for the table
        table.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        pane.add(scrollPane, BorderLayout.CENTER);
        this.pack();
    }

    /**
     * Adds the score to the list and sorts from highest to lowest
     * @param score
     */
	public void addScore(Score score) {
        scores.add(score);
        Collections.sort(scores);//sorts the scores
    }

    /**
     * Writes the list to .dat file
     */
    public void save() {
        try {
            ObjectStream.write(scores, "highscore.dat");
        } catch (IOException ex) {
            Logger.getLogger(JHighScore.class.getName()).log(Level.SEVERE, null, ex);
        }

        tablemodel = new DefaultTableModel(getTwoArray(), columns);
        table.setModel(tablemodel);

    }

    /**
     * Reads the data into the list
     */
    @SuppressWarnings("unchecked")
	private void readData() {
        try {
            scores = (ArrayList<Score>) ObjectStream.read("highscore.dat");
            Collections.sort(scores);//sorts the scores
        } catch (IOException ex) {
            Logger.getLogger(JHighScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JHighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to create a two-dimensional array from ArrayList<Score>
     * @return Object[][]
     */
    private Object[][] getTwoArray() {
        Object[][] temp = new Object[scores.size()][scores.get(0).toArray().length];//could be more dynamic but cool for here
        for (int i = 0; i < temp.length; i++) {
            Object[] m = scores.get(i).toArray();//to array method definced in movies
            temp[i] = m;
        }
        return temp;
    }
}
