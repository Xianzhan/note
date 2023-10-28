package xianzhan.frame.ui.swing.app.bag;

import lombok.Getter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Let's Build a Game!
 *
 * @author xianzhan
 * @since 2023-10-28
 */
@Getter
public class Game extends Canvas implements Runnable {

    public static int WIDTH  = 640;
    public static int HEIGHT = WIDTH / 12 * 9;

    private final String  title;
    private       Thread  thread;
    private       boolean running;

    private final Handler handler;

    public Game() {
        title = "Let's Build a Game!";

        handler = new Handler();

        handler.addObject(new Player(100, 100, ID.PLAYER));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        var lastTime = System.nanoTime();
        var amountOfTicks = 60.0;
        var ns = 1000000000 / amountOfTicks;
        var delta = 0.0;
        var timer = System.currentTimeMillis();
        var frames = 0;

        while (running) {
            var now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void render() {
        var bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bufferStrategy.show();
    }

    private void tick() {
        handler.tick();
    }
}
