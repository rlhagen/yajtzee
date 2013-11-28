/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.score;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author babka-lipka
 *
 *
 */
@SuppressWarnings("serial")
public final class Score implements Serializable, Comparable<Score> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
    private String player;
    private int score;
    private Date date;

    public Score() {
    }

    public Score(String player, int score) {
        this.player = player;
        this.score = score;
        this.setDate();
    }

    public String getDate() {
        return sdf.format(date);
    }

    public void setDate() {
        date = new Date(System.currentTimeMillis());
    }


    /**
     * Need to add a JDialog to grab the players name
     * @return
     */
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public Object[] toArray() {
        return new Object[]{player, sdf.format(date), String.valueOf(score)};
    }

	public int compareTo(Score o) {
	     return o.score - this.score;
	}
}
