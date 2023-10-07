package xianzhan.frame.ui.swing.app;

import lombok.extern.slf4j.Slf4j;
import xianzhan.frame.base.util.PathUtil;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 资源管理器
 *
 * @author xianzhan
 * @since 2023-09-27
 */
@Slf4j
public final class Explorer extends JPanel {

    private static class ExplorerJFrame extends JFrame implements Runnable {

        public ExplorerJFrame(String title) throws HeadlessException {
            super(title);
        }

        @Override
        public void run() {
            try {
                String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
                log.info("Explorer - start: 本机系统外观类名称. systemLookAndFeelClassName: {}", systemLookAndFeelClassName);
                UIManager.setLookAndFeel(systemLookAndFeelClassName);
            } catch (Exception e) {
                log.error("Explorer - start: 本机系统外观类设置异常.", e);
            }

            var frame = new ExplorerJFrame("Explorer");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ExplorerJPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    private static class ExplorerJPanel extends JPanel {
        public ExplorerJPanel() {
            super(new BorderLayout());

            var tree = new JTree(new DefaultTreeModel(treeNode()));
            ToolTipManager.sharedInstance().registerComponent(tree);

            var p = new JPanel(new GridLayout(1, 1));
            p.add(treeTitle(tree));

            var split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p, new JPanel());
            split.setResizeWeight(.5);
            split.setDividerLocation(320);

            add(split);
            setPreferredSize(new Dimension(960, 720));
        }

        private Component treeTitle(Component view) {
            var scroll = new JScrollPane(view);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            var p = new JPanel(new BorderLayout());
            p.setBorder(BorderFactory.createTitledBorder("树形目录"));
            p.add(scroll);
            return p;
        }

        private DefaultMutableTreeNode treeNode() {
            return tree(PathUtil.currentDir().toAbsolutePath().toString());
        }

        private DefaultMutableTreeNode tree(String dir) {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(Path.of(dir).getFileName());
            PathUtil.list(dir)
                    .map(path -> {
                        DefaultMutableTreeNode tn;
                        if (Files.isDirectory(path)) {
                            String directory = path.toAbsolutePath().toString();
                            tn = tree(directory);
                        } else {
                            String filename = path.getFileName().toString();
                            tn = new DefaultMutableTreeNode(filename);
                        }
                        return tn;
                    })
                    .forEach(root::add);
            return root;
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(new ExplorerJFrame("Explorer"));
    }
}