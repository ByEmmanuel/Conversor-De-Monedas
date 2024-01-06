package conversor.conversordemonedas;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de monedas");
        Controller app = new Controller();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(app.getRoot(), app.getDocumentacion());
        
        
        Scene scene = new Scene(vbox, 415, 435);

        String CSS = String.valueOf(Main.class.getResource("/Styles/styles.css"));
        scene.getStylesheets().add(CSS);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
