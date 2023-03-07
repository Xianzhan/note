package xianzhan.frame.ui.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class SwingMain {

    public static void main(String[] args) {
        var frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        var panel = new JPanel();
        frame.add(panel);

        var label = new JLabel("label");
        panel.add(label);

        var button = new JButton("button");
        button.addActionListener(actionEvent -> label.setText("你点击了按钮~"));
        panel.add(button);

        frame.setVisible(true);
    }
}
