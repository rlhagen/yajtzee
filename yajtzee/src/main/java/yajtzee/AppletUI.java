/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yajtzee;

import javax.swing.JApplet;

/**
 *
 * @author babka-lipka
 */
@SuppressWarnings("serial")
public class AppletUI extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    @Override
	public void init() {
        System.out.println(getCodeBase());
        System.out.println(getCodeBase().getFile());
        Yajtzee yajtzee = new Yajtzee();
        this.add(yajtzee);
    }
    // TODO overwrite start(), stop() and destroy() methods
}
