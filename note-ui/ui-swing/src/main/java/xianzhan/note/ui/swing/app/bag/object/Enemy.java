package xianzhan.note.ui.swing.app.bag.object;

import xianzhan.note.ui.swing.app.bag.Game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author xianzhan
 * @since 2023-10-30
 */
public class Enemy extends GameObject {

    public Enemy(int x, int y) {
        super(x, y, ID.ENEMY);

        velX = 1;
        velY = 1;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x < 0 || Game.WIDTH - 32 < x) {
            velX *= -1;
        }
        if (y < 0 || Game.HEIGHT - 56 < y) {
            velY *= -1;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 16, 16);
    }
}
