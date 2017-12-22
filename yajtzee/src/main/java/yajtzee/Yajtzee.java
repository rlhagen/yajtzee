/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yajtzee;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author babka-lipka
 */
@SuppressWarnings("serial")
public class Yajtzee extends JPanel implements ActionListener{

    private JScoreSheet sheet;

    public Yajtzee(){
        JDice dice = new JDice();
        Game game = new Game(dice.dice);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        this.add(panel, BorderLayout.NORTH);

        JMenuBar jmenu = new JMenuBar();
        JMenu jaboutMenu = new JMenu("About");
        jmenu.add(jaboutMenu);
        JMenuItem jaboutitem = new JMenuItem("High Scores");
        jaboutMenu.add(jaboutitem);
        jaboutitem.addActionListener(this);
        panel.add(jmenu, BorderLayout.NORTH);

//        JLabel logo = new JLabel();
//        logo.setText("Yahtzee");
//        logo.setPreferredSize(new Dimension(440, 151));
//        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/misc/yahtzee.png")));
//        logo.setHorizontalAlignment(JLabel.CENTER);
//        logo.setBorder(BorderFactory.createEtchedBorder());
//        panel.add(logo, BorderLayout.SOUTH);

        sheet = new JScoreSheet(game, dice);
        this.add(sheet, BorderLayout.SOUTH);

        this.add(dice, BorderLayout.CENTER);
        this.setBackground(dice.getBackground());
    }

    public void actionPerformed(ActionEvent e) {
//        sheet.highscore.setVisible(true);
    }



}
