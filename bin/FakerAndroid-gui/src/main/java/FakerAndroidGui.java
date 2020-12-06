import faker.android.decoder.util.Jar;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;

public class FakerAndroidGui extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
//		File file = Jar.getResourceAsFile("/FileChooser.fxml");
//
//		URL uri = file.toURI().toURL();
		Parent root = FXMLLoader.load(getClass().getResource("/FileChooser.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("FakerAndroid GUI");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
