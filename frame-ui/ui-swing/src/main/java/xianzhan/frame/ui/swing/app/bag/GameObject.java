package xianzhan.frame.ui.swing.app.bag;

import lombok.Data;

import java.awt.Graphics;

/**
 * @author xianzhan
 * @since 2023-10-28
 */
@Data
public abstract class GameObject {

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

    public abstract void tick();
    public abstract void render(Graphics g);
}
