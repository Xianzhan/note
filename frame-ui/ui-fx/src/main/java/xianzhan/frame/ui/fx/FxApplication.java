package xianzhan.frame.ui.fx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX 架构
 * +------------------------------------------------------------------------------+----+
 * |                  JavaFX Public APIs and Scene Graph                          |    |
 * +------------------------+-------------------------+--------------+------------+    |
 * |                           Quantum Toolkit                                    |    |
 * +------------------------+-------------------------+--------------+------------+    |
 * |         Prism          | Glass Windowing Toolkit | Media Engine | Web Engine |    |
 * +------------------------+-------------------------+--------------+------------+    |
 * | Java 2d | OpenGL | D3D |                                                          |
 * +------------------------+                                                          |
 * |                          JDK API Libraries & Tools                                |
 * +-----------------------------------------------------------------------------------+
 * |                            Java Virtual Machine                                   |
 * +-----------------------------------------------------------------------------------+
 */
public class FxApplication extends Application {

    @Override
    public void init() throws Exception {
        // 初始化
        System.out.println("启动 JavaFX");
    }

    @Override
    public void stop() throws Exception {
        // 停止操作
        System.out.println("结束 JavaFX");
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 标题");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
