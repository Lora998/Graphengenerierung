package GraphischeDarstellung;
	
import Graph.Raum;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	Stage fenster;
	Raum raum;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		/*
		 * erstellt das Hauptfenster
		 */
		this.fenster = primaryStage;
		this.fenster.setTitle("Graphengenerierung");
		Pane hauptFenster = new Pane();
		hauptFenster.setPadding(new Insets(8, 8, 8, 8));
		this.raum = new Raum(0, 800, 100, 1);
		
		/*
		 *erstellt Buttonleiste fuer grundlegende Funktionen 
		 */
		HBox buttonLeiste = new HBox();
		buttonLeiste.setSpacing(15);
		Button neuerGraphButton = new Button("Neu");
		Button beendenButton = new Button("Beenden");
		buttonLeiste.getChildren().addAll(neuerGraphButton, beendenButton);
		BorderPane.setAlignment(buttonLeiste, Pos.TOP_CENTER);
		
		
		Zeichenwand z = new Zeichenwand(this.raum);
		Pane tmp = new Pane();
		tmp.getChildren().add(z);
		BorderPane.setAlignment(tmp, Pos.BOTTOM_CENTER);
		BorderPane layout = new BorderPane(tmp, buttonLeiste, null, null, null);
	//	hauptFenster.getChildren().addAll(buttonLeiste, tmp);
		//this.fenster.setScene(new Scene(hauptFenster, 400, 400, Color.BLACK));
		this.fenster.setScene(new Scene(layout));
		this.fenster.setResizable(false);
		this.fenster.show();
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
