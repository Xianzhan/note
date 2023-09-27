package xianzhan.frame.ui.swing.app;

import lombok.extern.slf4j.Slf4j;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * @author xianzhan
 * @since 2023-09-27
 */
@Slf4j
public final class ExplorerJPanel extends JPanel {
    private ExplorerJPanel() {
        super(new BorderLayout());
        JTree tree0 = new JTree(new DefaultTreeModel(makeTreeRoot())) {
            @Override
            public void updateUI() {
                setCellRenderer(null);
                super.updateUI();
                TreeCellRenderer r = getCellRenderer();
                setCellRenderer((tree, value, selected, expanded, leaf, row, hasFocus) -> {
                    Component c = r.getTreeCellRendererComponent(
                            tree, value, selected, expanded, leaf, row, hasFocus);
                    if (c instanceof JComponent) {
                        ((JComponent) c).setToolTipText(Objects.toString(value, null));
                    }
                    return c;
                });
            }
        };
        ToolTipManager.sharedInstance().registerComponent(tree0);

        JTree tree1 = new TooltipTree(new DefaultTreeModel(makeTreeRoot()));
        ToolTipManager.sharedInstance().registerComponent(tree1);

        JPanel p = new JPanel(new GridLayout(2, 1));
        p.add(makeTitledPanel("Default location", tree0));
        p.add(makeTitledPanel("Draw directly above the cell", tree1));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p, new JPanel());
        split.setResizeWeight(.5);
        split.setDividerLocation(160);

        add(split);
        setPreferredSize(new Dimension(320, 240));
    }

    private static DefaultMutableTreeNode makeTreeRoot() {
        DefaultMutableTreeNode set4 = new DefaultMutableTreeNode("Set 00000004");
        set4.add(new DefaultMutableTreeNode("222222111111111111111122222"));
        set4.add(new DefaultMutableTreeNode("00000000000"));
        set4.add(new DefaultMutableTreeNode("1111111111"));
        set4.add(new DefaultMutableTreeNode("22222222"));

        DefaultMutableTreeNode set1 = new DefaultMutableTreeNode("Set 00000001");
        set1.add(new DefaultMutableTreeNode("33333333333333333333333333333333333"));
        set1.add(new DefaultMutableTreeNode("111111111"));
        set1.add(new DefaultMutableTreeNode("22222222222"));
        set1.add(set4);
        set1.add(new DefaultMutableTreeNode("222222"));
        set1.add(new DefaultMutableTreeNode("222222222"));

        DefaultMutableTreeNode set2 = new DefaultMutableTreeNode("Set 00000002");
        set2.add(new DefaultMutableTreeNode("333333333"));
        set2.add(new DefaultMutableTreeNode("4444444444444"));

        DefaultMutableTreeNode set3 = new DefaultMutableTreeNode("Set 00000003");
        set3.add(new DefaultMutableTreeNode("5555555555"));
        set3.add(new DefaultMutableTreeNode("66666666666"));
        set3.add(new DefaultMutableTreeNode("7777777777"));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        root.add(new DefaultMutableTreeNode("88888888888888888"));
        root.add(set3);
        root.add(new DefaultMutableTreeNode("9999999999999999999999999"));
        root.add(set1);
        root.add(set2);
        root.add(new DefaultMutableTreeNode("22222222222222222222222222222222222"));
        root.add(new DefaultMutableTreeNode("1111111111111111111111111"));
        return root;
    }

    private static Component makeTitledPanel(String title, Component c) {
        JScrollPane scroll = new JScrollPane(c);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(scroll);
        return p;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(ExplorerJPanel::createAndShowGui);
    }

    private static void createAndShowGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ignored) {
            Toolkit.getDefaultToolkit().beep();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            return;
        }
        JFrame frame = new JFrame("@title@");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ExplorerJPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class TooltipTree extends JTree {
    private final JLabel label = new JLabel() {
        @Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            d.height = getRowHeight();
            return d;
        }
    };

    protected TooltipTree(TreeModel model) {
        super(model);
    }

    @Override
    public void updateUI() {
        setCellRenderer(null);
        super.updateUI();
        // setRowHeight(24);
        TreeCellRenderer r = getCellRenderer();
        setCellRenderer((tree, value, selected, expanded, leaf, row, hasFocus) -> {
            Component c = r.getTreeCellRendererComponent(
                    tree, value, selected, expanded, leaf, row, hasFocus);
            if (c instanceof JComponent) {
                ((JComponent) c).setToolTipText(Objects.toString(value, null));
            }
            return c;
        });
    }

    @Override
    public Point getToolTipLocation(MouseEvent e) {
        Point p = e.getPoint();
        int i = getRowForLocation(p.x, p.y);
        Rectangle cellBounds = getRowBounds(i);
        if (Objects.nonNull(cellBounds) && cellBounds.contains(p)) {
            TreeSelectionModel tsm = getSelectionModel();
            Object node = getPathForRow(i).getLastPathComponent();
            // System.out.println(node);
            boolean hasFocus = hasFocus() && tsm.getLeadSelectionRow() == i;
            boolean isLeaf = getModel().isLeaf(node);
            TreeCellRenderer r = getCellRenderer();
            Component tcr = r.getTreeCellRendererComponent(
                    this, node, isRowSelected(i), isExpanded(i), isLeaf, i, hasFocus);
            if (tcr instanceof JComponent && Objects.nonNull(((JComponent) tcr).getToolTipText())) {
                // System.out.println(((JComponent) tcr).getToolTipText());
                Point pt = cellBounds.getLocation();
                // label.setBorder(BorderFactory.createLineBorder(Color.RED));
                Insets ins = label.getInsets();
                pt.translate(-ins.left, -ins.top);
                label.setIcon(new RendererIcon(tcr, cellBounds));
                // System.out.println(pt);
                return pt;
            }
        }
        return null;
    }

    @Override
    public JToolTip createToolTip() {
        JToolTip tip = new JToolTip() {
            @Override
            public Dimension getPreferredSize() {
                return label.getPreferredSize();
            }
        };

        tip.setBorder(BorderFactory.createEmptyBorder());
        tip.setLayout(new BorderLayout());
        tip.setComponent(this);
        tip.add(label);
        return tip;
    }
}

class RendererIcon implements Icon {
    private final Component renderer;
    private final Rectangle rect;

    protected RendererIcon(Component renderer, Rectangle rect) {
        this.renderer = renderer;
        this.rect = rect;
        rect.setLocation(0, 0);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (c instanceof Container) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.clearRect(0, 0, c.getWidth(), c.getHeight());

            SwingUtilities.paintComponent(g2, renderer, (Container) c, rect);
            g2.dispose();
        }
    }

    @Override
    public int getIconWidth() {
        return renderer.getPreferredSize().width;
    }

    @Override
    public int getIconHeight() {
        return renderer.getPreferredSize().height;
    }
}