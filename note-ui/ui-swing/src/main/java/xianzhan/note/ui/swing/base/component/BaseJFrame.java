package xianzhan.note.ui.swing.base.component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author xianzhan
 * @since 2023-10-17
 */
public class BaseJFrame extends JFrame {

    public BaseJFrame() {
        setTitle("Top Level");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final var width = 400;

        var bar = new JMenuBar();
        bar.setOpaque(true);
        bar.setBackground(new Color(154, 165, 127));
        bar.setPreferredSize(new Dimension(width, 20));

        var label = new JLabel();
        label.setOpaque(true);
        label.setBackground(new Color(248, 213, 131));
        label.setPreferredSize(new Dimension(width, 180));

        setJMenuBar(bar);
        getContentPane().add(label, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public static void main() {
        SwingUtilities.invokeLater(BaseJFrame::new);
    }
}
