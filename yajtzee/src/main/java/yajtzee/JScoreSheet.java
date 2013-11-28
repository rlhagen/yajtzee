/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yajtzee;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author babka-lipka
 */
@SuppressWarnings("serial")
public class JScoreSheet extends JPanel {

//    protected JHighScore highscore;
    private TableModel tablemodel;
    private JTable jtable;
    private final Game game;
    private int currentgame;
    private String player = "Player";
    private int games = 1;

    public JScoreSheet(final Game game) {
//        highscore = new JHighScore();
        GridLayout layout = new GridLayout(0, 1);
        this.setLayout(layout);
        this.game = game;
        init();//initalizes the GUI and data
        showPlayerDialog();//enters the players name
    }

    private void init() {
        if (this.getComponentCount() > 0) {//removes all existing components if reinitialized
            this.removeAll();
        }
        currentgame = 2;
        String[] colnames = {"", "Score", "Game " + games++, "Game " + games++, "Game " + games++, "Game " + games++};//the column names
        Object[][] tabledata = {
            {"1", "Aces", "", "", "", ""},
            {"2", "Twos", "", "", "", ""},
            {"3", "Threes", "", "", "", ""},
            {"4", "Fours", "", "", "", ""},
            {"5", "Fives", "", "", "", ""},
            {"6", "Sixes", "", "", "", ""},
            {"Upper Total", ">>>>", "0", "0", "0", "0"},
            {"3 of Kind", "All Dice", "", "", "", ""},
            {"4 of Kind", "All Dice", "", "", "", ""},
            {"Full House", "25", "", "", "", ""},
            {"Sm Straight", "30", "", "", "", ""},
            {"Lg Straight", "40", "", "", "", ""},
            {"YAJTZEE", "50", "", "", "", ""},
            {"Chance", "All Dice", "", "", "", ""},
            {"Bonus", "100", "", "", "", ""},
            {"Lower Total", ">>>>", "0", "0", "0", "0"},
            {"Total Score", ">>>>", "", "", "", ""}
        };

        tablemodel = new DefaultTableModel(tabledata, colnames);//reads data from XML
        jtable = new JTable(tablemodel);

        jtable.addMouseListener(new MouseClick());
        
        JScrollPane scrollPane = new JScrollPane(jtable);//ScrollPane for the table
        jtable.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        this.add(scrollPane);

        jtable.setRowHeight(27);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(100);


        jtable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {//used renderer because JTable was not working correctly or something.

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocused, int row, int col) {
                JLabel rendererComponent = new JLabel();
                rendererComponent.setHorizontalAlignment(JLabel.CENTER);
                if (value.toString().length() == 1) {
                    //image = Toolkit.getDefaultToolkit().createImage(imagelink);
                    ImageIcon img = new javax.swing.ImageIcon(getClass().getResource("/icons/unselected/" + value + ".png"));
                    if (img.getIconWidth() > 25) {
                        img.setImage(img.getImage().getScaledInstance(25, -1, 0));//auto adjusts the image to meet viewer dimensions
                    }
                    if (img.getIconHeight() > 25) {
                        img.setImage(img.getImage().getScaledInstance(-1, 25, 0));
                    }
                    rendererComponent.setIcon(img);
                } else {
                    rendererComponent.setText(value.toString());
                }
                return rendererComponent;
            }
        });

        jtable.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel rendererComponent = new JLabel(value.toString());
                rendererComponent.setHorizontalAlignment(JLabel.CENTER);
                if (value.toString().equals(">>>>")) {
                    rendererComponent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/misc/arrow.png")));
                    rendererComponent.setText("");
                }
                return rendererComponent;
            }
        });

        for (int i = 2; i < jtable.getModel().getColumnCount(); i++) {//sets all game cells inactie
            jtable.getColumnModel().getColumn(i).setCellRenderer(new InActiveGame());
        }

        jtable.getColumnModel().getColumn(currentgame).setCellRenderer(new ActiveGame());//changes the renderer so current game is active
        this.validate();
    }

    private class InActiveGame implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel rendererComponent = new JLabel(value.toString());
            rendererComponent.setEnabled(false);
            rendererComponent.setHorizontalAlignment(JLabel.CENTER);
            rendererComponent.setFont(new Font(table.getFont().getName(), Font.PLAIN, 14));
            return rendererComponent;
        }
    }

    private class ActiveGame implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JToggleButton rendererComponent = new JToggleButton(value.toString());
            rendererComponent.setEnabled(false);
            rendererComponent.setHorizontalAlignment(JLabel.CENTER);
            rendererComponent.setFont(new Font(table.getFont().getName(), Font.BOLD, 16));
            return rendererComponent;
        }
    }

    /**
     * needs refactoring
     * @param e
     */
    private class MouseClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            //JTable t = (JTable) e.getSource();
            int row = jtable.getSelectedRow();
            int column = jtable.getSelectedColumn();

            if (jtable.getValueAt(row, column).toString().equals("") || row == 14) {//checks to see if cell has a value or bonus

                if (row < 6) {//uppersection
                    jtable.setValueAt(game.addSingle(row + 1), row, column);//set the value for upper section
                    //now configure the total
                    int total = 0;
                    for (int i = 0; i <= 5; i++) {
                        if (!jtable.getValueAt(i, currentgame).toString().equals("")) {
                            total += Integer.parseInt(jtable.getValueAt(i, currentgame).toString());
                        }
                    }
                    if (total >= 63) {//sets the bonus for upper section
                        total += 35;
                    }

                    jtable.setValueAt(total, 6, currentgame);//sets total for upper section

                } else {//lower section calculations
                    int score = 0;
                    switch (row) {
                        case 7://Three of kind
                            if (game.isThreeOfKind()) {
                                score = game.addAll();
                            }
                            break;
                        case 8://Four of kind
                            if (game.isFourOfKind()) {
                                score = game.addAll();
                            }
                            break;
                        case 9://Full house
                            if (game.isFullHouse()) {
                                score = 25;
                            }
                            break;
                        case 10:
                            if (game.isSmallStraight()) {
                                score = 30;
                            }
                            break;
                        case 11:
                            if (game.isLargeStraight()) {
                                score = 40;
                            }
                            break;
                        case 12:
                            if (game.isYajtzee()) {
                                score = 50;
                            }
                            break;
                        case 13://Chance
                            score = game.addAll();
                            break;
                        case 14://Bonus Yahtzee
                            String temp = jtable.getValueAt(row, column).toString();
                            if (game.isYajtzee()) {
                                if (temp.equals("")) {//checks to see if it has a value
                                    score = 100;
                                } else {
                                    score = Integer.parseInt(temp) + 100;
                                }
                            }

                            break;
                    }
                    jtable.setValueAt(score, row, column);//sets value at selection
                    int total = 0;
                    for (int i = 7; i < jtable.getRowCount() - 2; i++) {//calculates total for lower section
                        if (!jtable.getValueAt(i, currentgame).toString().equals("")) {
                            total += Integer.parseInt(jtable.getValueAt(i, currentgame).toString());
                        }
                    }
                    jtable.setValueAt(total, 15, currentgame);

                }
                jtable.setValueAt(Integer.parseInt(jtable.getValueAt(6, currentgame).toString()) + Integer.parseInt(jtable.getValueAt(15, currentgame).toString()), 16, currentgame);//total score
                if (row != 14) {//does not increment if a bonus Yahtzee
                    game.dice.resetDice();
                    game.rolls++;
                }
                if (game.isOver()) {//checks to see if game is over, if it is, sets to next game
                    for (int i = 0; i < jtable.getRowCount(); i++) {
                        if (jtable.getValueAt(i, currentgame).toString().equals("")) {
                            jtable.setValueAt(0, i, currentgame);
                        }
                    }
//                    highscore.addScore(new Score(player, Integer.valueOf(jtable.getValueAt(16, currentgame).toString())));
//                    highscore.save();//saves to data file

                    jtable.getColumnModel().getColumn(currentgame).setCellRenderer(new InActiveGame());//changes the renderer
                    currentgame++;
                    game.reset();
                    if (currentgame < jtable.getColumnCount()) {//moves the current game to next colum
                        jtable.getColumnModel().getColumn(currentgame).setCellRenderer(new ActiveGame());//changes the renderer
                    } else {
                        System.out.println("Re-initializing");
                        init();//reinitializes the game
                    }
                }
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }
    }


    /**
     * Shows Dialog to capture the players name...
     */
    private void showPlayerDialog() {
        player = (String) JOptionPane.showInputDialog(
                null,
                "Enter your name...",
                "Yajtzee",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                player);
    }
}
