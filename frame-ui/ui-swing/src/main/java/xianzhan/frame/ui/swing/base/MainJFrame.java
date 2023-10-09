package xianzhan.frame.ui.swing.base;

import javax.swing.JFrame;

/**
 * @author xianzhan
 * @since 2023-10-08
 */
public class MainJFrame extends JFrame implements Runnable {

    private static final int MAIN_WIDTH  = 960;
    private static final int MAIN_HEIGHT = 640;

    /**
     * 窗口居中
     */
    private void center() {
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        setTitle("主窗口");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MAIN_WIDTH, MAIN_HEIGHT);

        center();

        setVisible(true);
    }
}
