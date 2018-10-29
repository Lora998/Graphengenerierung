package GraphischeDarstellung;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Stage fenster;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		/*
		 * erstellt das Hauptfenster
		 */
		this.fenster = primaryStage;
		this.fenster.setTitle("Graphengenerierung");
		VBox hauptFenster = new VBox();
		hauptFenster.setSpacing(10);
		hauptFenster.setPadding(new Insets(8, 8, 8, 8));
		
		/*
		 *erstellt Buttonleiste fuer grundlegende Funktionen 
		 */
		HBox buttonLeiste = new HBox();
		buttonLeiste.setSpacing(15);
		Button neuerGraphButton = new Button("Neu");
		Button beendenButton = new Button("Beenden");
		buttonLeiste.getChildren().addAll(neuerGraphButton, beendenButton);
		
		hauptFenster.getChildren().addAll(buttonLeiste);
		this.fenster.setScene(new Scene(hauptFenster, 400, 400));
		this.fenster.setResizable(false);
		this.fenster.show();
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
