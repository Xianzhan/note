package xianzhan.frame.ui.swing.app.bag;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author xianzhan
 * @since 2023-10-29
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        for (GameObject gameObject : handler.getObjectList()) {
            switch (keyCode) {
                case KeyEvent.VK_W -> gameObject.setVelY(-5);
                case KeyEvent.VK_S -> gameObject.setVelY(5);
                case KeyEvent.VK_A -> gameObject.setVelX(-5);
                case KeyEvent.VK_D -> gameObject.setVelX(5);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        for (GameObject gameObject : handler.getObjectList()) {
            switch (keyCode) {
                case KeyEvent.VK_W, KeyEvent.VK_S -> gameObject.setVelY(0);
                case KeyEvent.VK_A, KeyEvent.VK_D -> gameObject.setVelX(0);
            }
        }
    }
}
