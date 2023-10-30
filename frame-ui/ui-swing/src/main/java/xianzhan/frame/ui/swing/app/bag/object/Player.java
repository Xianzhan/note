package xianzhan.frame.ui.swing.app.bag.object;

import xianzhan.frame.ui.swing.app.bag.Game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author xianzhan
 * @since 2023-10-28
 */
public class Player extends GameObject {

    public Player(int x, int y) {
        super(x, y, ID.PLAYER);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Math.clamp(x, 0, Game.WIDTH - 48);
        y = Math.clamp(y, 0, Game.HEIGHT - 72);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }
}
