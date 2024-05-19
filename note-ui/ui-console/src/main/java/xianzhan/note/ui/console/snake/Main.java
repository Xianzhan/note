package xianzhan.note.ui.console.snake;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

/**
 * @author xianzhan
 * @since 2024-05-15
 */
public class Main {

    static final int HEIGHT = 10;
    static final int WIDTH  = 20;

    static final char WALL       = '#';
    static final char ROAD       = ' ';
    static final char SNAKE_HEAD = '@';
    static final char SNAKE_BODY = 'O';
    static final char SNAKE_FOOD = 'F';

    static class World {

        private final char[]     map;
        private final int        h;
        private final int        w;
        private final List<Node> roadList;

        World(int height, int width) {
            map = new char[width * height];
            h = height;
            w = width;
            roadList = new ArrayList<>();
        }

        public boolean isRoad(int x, int y) {
            return map[x * w + y] == ROAD;
        }

        public void addRoad(Node road) {
            roadList.add(road);
        }

        public void map(Object obj) {
            switch (obj) {
                case Snake s -> {
                    for (var i = 0; i < s.body.size(); i++) {
                        if (i == 0) {
                            map[s.body.get(i).x * w + s.body.get(i).y] = SNAKE_HEAD;
                        } else {
                            map[s.body.get(i).x * w + s.body.get(i).y] = SNAKE_BODY;
                        }
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + obj);
            }
        }

        public void init() {
            initWall();
        }

        private void initWall() {
            for (var i = 0; i < HEIGHT; i++) {
                var j = 0;
                for (; j < WIDTH - 1; j++) {
                    char ch = (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 2) ? WALL : ROAD;
                    map[i * WIDTH + j] = ch;
                }
                map[i * WIDTH + j] = '\n';
            }
        }

        public void update() {
            for (Node node : roadList) {
                map[node.x() * WIDTH + node.y()] = ROAD;
            }

            roadList.clear();
        }

        public void paint() {
            System.out.print(map);
        }
    }

    record Node(int x, int y, Object type) {
    }

    @Getter
    static class Food {

        private int x;
        private int y;

        private final RandomGenerator r = RandomGenerator.getDefault();

        public void init(World w) {
            x = r.nextInt(w.h);
            y = r.nextInt(w.w);
        }

        public void update(World w) {
            init(w);
        }
    }

    static class Snake {
        private       Direction        direction;
        private final LinkedList<Node> body;

        public Snake() {
            this.body = new LinkedList<>();
        }

        public void init() {
            body.addFirst(new Node(1, 2, this));
            body.add(new Node(1, 1, this));

            direction = Direction.RIGHT;
        }

        public void update(World world) {
            // move
            var oldHead = body.getFirst();
            var newHead = new Node(oldHead.x() + direction.getX(), oldHead.y() + direction.getY(), this);
            if (!world.isRoad(newHead.x(), newHead.y())) {
                System.out.println("Game over");
                System.exit(-1);
            }
            body.addFirst(newHead);

            world.map(this);

            var last = body.removeLast();
            world.addRoad(last);
        }

        @Getter
        enum Direction {

            UP(-1, 0),
            DOWN(1, 0),
            LEFT(0, -1),
            RIGHT(0, 1),
            ;

            private int x;
            private int y;

            Direction(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }

    private static void init(World w, Food f, Snake s) {
        w.init();
        f.init(w);
        s.init();
    }

    private static void update(World w, Food f, Snake s) {
        f.update(w);
        s.update(w);
        w.update();
    }

    private static void paint(World w) {
        w.paint();
    }

    private static void clear() {
        // 清屏
        // 光标复位
        System.out.print("\033[2J\033[0;0H");
    }

    public static void main(String[] args) throws InterruptedException {
        var w = new World(HEIGHT, WIDTH);
        var f = new Food();
        var s = new Snake();

        init(w, f, s);
        while (Thread.currentThread().isAlive()) {
            clear();
            update(w, f, s);
            paint(w);

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
