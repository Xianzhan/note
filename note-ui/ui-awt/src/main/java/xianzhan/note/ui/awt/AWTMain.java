package xianzhan.note.ui.awt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;

/**
 * @author xianzhan
 * @since 2023-08-13
 */
public class AWTMain {

    public static void main() {
        var frame = new Frame("Title");
        frame.setLocation(600, 300);
        frame.setSize(500, 500);
        frame.setBackground(Color.BLACK);

        frame.add(new Button("Click me"));

        frame.setVisible(true);
    }
}
