/**
 * fix: 错误: 缺少 JavaFX 运行时组件, 需要使用该组件来运行此应用程序
 *
 * @author xianzhan
 * @since 2023-05-29
 */
module ui.fx {
    requires javafx.graphics;
    exports xianzhan.frame.ui.fx;
}