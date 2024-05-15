package xianzhan.note.ui.console;

/**
 * @author xianzhan
 * @since 2024-05-15
 */
public class Snake {

    static final int HEIGHT = 10;
    static final int WIDTH  = 20;

    static final char WALL = '#';
    static final char ROAD = ' ';

    public static void main(String[] args) {
        char[] buff = init();
        while (Thread.currentThread().isAlive()) {
            // TODO lxz 更新数据
//            update();
            paint(buff);
            break;
        }
    }

    private static char[] init() {
        var buff = new char[HEIGHT * WIDTH];
        initWall(buff);
        return buff;
    }

    private static void initWall(char[] buff) {
        for (var i = 0; i < HEIGHT; i++) {
            var j = 0;
            for (; j < WIDTH - 1; j++) {
                char ch = (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 2) ? WALL : ROAD;
                buff[i * WIDTH + j] = ch;
            }
            buff[i * WIDTH + j] = '\n';
        }
    }

    private static void paint(char[] buff) {
        System.out.print(buff);
    }

    private static void clear() {
        // 清屏
        // 光标复位
        System.out.print("\033[2J\033[0;0H");
    }
}
