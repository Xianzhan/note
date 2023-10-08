package xianzhan.frame.ui.swing;

import lombok.extern.slf4j.Slf4j;
import xianzhan.frame.ui.swing.base.MainJFrame;

import javax.swing.SwingUtilities;

/**
 * <pre>
 * Object
 * +-----------------------------------------+
 * |                                         |
 * Container                                 AWT Components...
 * +-------+--------------------+
 * |       |                    |
 * Panel   Window               JComponent
 * |       +----------+         +---------+----------------+--------+--------------+
 * |       |          |         |         |                |        |              |
 * Applet  Frame      Dialog    JLable    JAastractButton  JPanel   JTextComponent JScrollPane
 * |       |          |         +------------+             +-----------+
 * |       |          |         |            |             |           |
 * JApplet JFrame     JDialog   JButton      JToggleButton JTextField  JTextArea
 * </pre>
 *
 * @author xianzhan
 * @since 2023-03-07
 */
@Slf4j
public class SwingMain {

    public static void main(String[] args) {

        try {
            SwingUtilities.invokeAndWait(new MainJFrame());
            log.info("SwingMain - main: 启动完成.");
        } catch (Exception e) {
            log.error("SwingMain - main: 启动异常.", e);
        }
    }
}
