package xianzhan.frame.ui.fx;

import javafx.application.Application;
import javafx.stage.Stage;

public class FxApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 标题");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
