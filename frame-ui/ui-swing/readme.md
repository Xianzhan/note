
[The Java Tutorials](https://docs.oracle.com/javase/tutorial/uiswing/index.html)

继承关系

```
Object
+-----------------------------------------+
|                                         |
Container                                 AWT Components...
+-------+--------------------+
|       |                    |
Panel   Window               JComponent
|       +----------+         +---------+----------------+--------+--------------+
|       |          |         |         |                |        |              |
Applet  Frame      Dialog    JLable    JAastractButton  JPanel   JTextComponent JScrollPane
|       |          |         +------------+             +-----------+
|       |          |         |            |             |           |
JApplet JFrame     JDialog   JButton      JToggleButton JTextField  JTextArea
```

# Top-Level Containers

JFrame
JDialog
JApplet
