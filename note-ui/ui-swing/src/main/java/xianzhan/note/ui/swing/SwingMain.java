package xianzhan.note.ui.swing;

import lombok.extern.slf4j.Slf4j;
import xianzhan.note.ui.swing.base.MainJFrame;

import javax.swing.SwingUtilities;

/**
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
