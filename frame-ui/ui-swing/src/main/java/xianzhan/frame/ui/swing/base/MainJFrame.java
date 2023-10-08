package xianzhan.frame.ui.swing.base;

import javax.swing.JFrame;
import java.awt.HeadlessException;

/**
 * @author xianzhan
 * @since 2023-10-08
 */
public class MainJFrame extends JFrame implements Runnable {

    private static final int MAIN_WIDTH  = 960;
    private static final int MAIN_HEIGHT = 640;

    public MainJFrame() throws HeadlessException {
        super("主窗口");
    }

    /**
     * 窗口居中
     */
    private void center() {
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        var frame = new MainJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(MAIN_WIDTH, MAIN_HEIGHT);

        frame.center();

        frame.setVisible(true);
    }
}
