package Main;
import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<MouseEvent>{
	
	BorderPane bp;
	GridPane gp;
	Scene scene;
	Label idLbl, nameLbl;
	TextField idTxtField, nameTxtField;
	Button updateBtn, deleteBtn;
	TableView<User> tv;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void initialize() {
		bp = new BorderPane();
		gp = new GridPane();
		idLbl = new Label("User Id");
		nameLbl = new Label ("User's Name");
		idTxtField = new TextField();
		nameTxtField = new TextField();
		updateBtn = new Button();
		deleteBtn = new Button();
		tv = new TableView<>();
	}
	
	public void positioning() {
		gp.add(idLbl, 0, 0);
		gp.add(idTxtField, 1, 0);
		
		gp.add(nameLbl, 0, 1);
		gp.add(nameTxtField, 1, 1);
		
		gp.add(updateBtn, 0, 2);
		gp.add(deleteBtn, 1, 2);
		
		bp.setTop(tv);
		bp.setCenter(gp);
	}

	public void setEvent() {
		updateBtn.setOnMouseClicked(this);
		deleteBtn.setOnMouseClicked(this);
		tv.setOnMouseClicked(this);
	}
	
	public void setTableColumn() {
		TableColumn<User, String> nameC = new TableColumn<>();
		TableColumn<User, Integer> idC = new TableColumn<>();
		
		idC.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tv.getColumns().addAll(idC);
		tv.getColumns().addAll(nameC);
	}
	
	public void setTableData() {
		Vector<User> users = User.getAllUser();
		ObservableList<User> userObservable = FXCollections.observableArrayList(users);
		tv.setItems(userObservable);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		initialize();
		positioning();
		setEvent();
		setTableColumn();
		setTableData();
		primaryStage.setScene(scene);
		primaryStage.show();
		scene = new Scene(bp, 400, 600);
		
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == updateBtn) {
			// ambil dari textfield
			// panggil method update
			// refresh
			String name = nameTxtField.getText();
			Integer id = Integer.parseInt(idTxtField.getText());
			User.update(id, name);
			setTableData();
		}
			
		else if (event.getSource() == deleteBtn) {
			// ambil dari textfield
			// panggil method update
			// refresh
			Integer id = Integer.parseInt(idTxtField.getText());
			User.delete(id);
			setTableData();
			
		}
		else if (event.getSource() == tv) {
			TableSelectionModel<User> tsm = tv.getSelectionModel();
			tsm.setSelectionMode(SelectionMode.SINGLE);
			User u = tsm.getSelectedItem();
		}

	}

}
