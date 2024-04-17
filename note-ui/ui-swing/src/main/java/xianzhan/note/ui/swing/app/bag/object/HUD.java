package xianzhan.note.ui.swing.app.bag.object;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author xianzhan
 * @since 2023-10-30
 */
public class HUD implements IGameObject {

    private int health;

    public HUD() {
        health = 100;
    }

    @Override
    public void tick() {
        health = Math.clamp(--health, 0, 100);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);

        g.setColor(Color.GREEN);
        g.fillRect(15, 15, health * 2, 32);

        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200, 32);
    }
}
