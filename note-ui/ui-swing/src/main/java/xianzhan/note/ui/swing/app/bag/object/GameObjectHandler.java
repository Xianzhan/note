package xianzhan.note.ui.swing.app.bag.object;

import lombok.Getter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xianzhan
 * @since 2023-10-28
 */
@Getter
public class GameObjectHandler implements IGameObject {

    private final List<GameObject> objectList = new ArrayList<>();

    @Override
    public void tick() {
        for (IGameObject gameObject : objectList) {
            gameObject.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        for (IGameObject gameObject : objectList) {
            gameObject.render(g);
        }
    }
}
