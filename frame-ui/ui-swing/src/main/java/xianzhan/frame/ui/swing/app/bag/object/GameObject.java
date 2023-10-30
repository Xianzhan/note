package xianzhan.frame.ui.swing.app.bag.object;

import lombok.Data;

import java.awt.Graphics;

/**
 * @author xianzhan
 * @since 2023-10-28
 */
@Data
public abstract class GameObject implements IGameObject {

    protected int x;
    protected int y;
    protected int velX;
    protected int velY;

    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
}
