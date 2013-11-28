/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.score;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author babka-lipka
 */
public class ObjectStream {

    public static void write(Object object, String filename) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        os.writeObject(object);
        os.close();
    }

    public static Object read(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
        Object o = is.readObject();
        is.close();
        return o;
    }
}
