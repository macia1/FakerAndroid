import brut.util.Jar;
import com.faker.android.FakerTransfer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class FakerAndroidGui extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		File file = Jar.getResourceAsFile("/FileChooser.fxml");
		URL uri = file.toURI().toURL();
		Parent root = FXMLLoader.load(uri);
		Scene scene = new Scene(root);
		stage.setTitle("FakerAndroid GUI");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
