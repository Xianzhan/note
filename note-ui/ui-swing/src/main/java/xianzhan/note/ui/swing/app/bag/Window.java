package xianzhan.note.ui.swing.app.bag;

import javax.swing.JFrame;
import java.awt.Dimension;

/**
 * @author xianzhan
 * @since 2023-10-28
 */
public class Window {

    public Window(Game game) {
        var frame = new JFrame(game.getTitle());

        var dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);

        game.start();
    }
}
