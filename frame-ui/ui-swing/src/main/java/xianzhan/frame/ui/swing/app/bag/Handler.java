package xianzhan.frame.ui.swing.app.bag;

import xianzhan.frame.ui.swing.app.bag.object.GameObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xianzhan
 * @since 2023-10-28
 */
public class Handler {

    private final List<GameObject> objectList = new ArrayList<>();

    public void tick() {
        for (GameObject gameObject : objectList) {
            gameObject.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject gameObject : objectList) {
            gameObject.render(g);
        }
    }

    public List<GameObject> getObjectList() {
        return objectList;
    }

    public void addObject(GameObject object) {
        objectList.add(object);
    }

    public void removeObject(GameObject object) {
        objectList.remove(object);
    }
}
