package xianzhan.note.ui.swing.app.bag;

import xianzhan.note.ui.swing.app.bag.object.GameObject;
import xianzhan.note.ui.swing.app.bag.object.GameObjectHandler;
import xianzhan.note.ui.swing.app.bag.object.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author xianzhan
 * @since 2023-10-29
 */
public class KeyInput extends KeyAdapter {

    private final GameObjectHandler gameObjectHandler;

    public KeyInput(GameObjectHandler gameObjectHandler) {
        this.gameObjectHandler = gameObjectHandler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("Game over");
            System.exit(1);
        }

        for (GameObject gameObject : gameObjectHandler.getObjectList()) {
            if (gameObject.getId() != ID.PLAYER) {
                return;
            }

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

        for (GameObject gameObject : gameObjectHandler.getObjectList()) {
            if (gameObject.getId() != ID.PLAYER) {
                return;
            }

            switch (keyCode) {
                case KeyEvent.VK_W, KeyEvent.VK_S -> gameObject.setVelY(0);
                case KeyEvent.VK_A, KeyEvent.VK_D -> gameObject.setVelX(0);
            }
        }
    }
}
