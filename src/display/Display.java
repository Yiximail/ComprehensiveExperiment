package display;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.event.*;
import object.Student;
import data.Data;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Display extends Application {
	private final TableView<Student> table = new TableView<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		VBox mainpane = new VBox();
		Scene mainscene = new Scene(mainpane, 600, 500);
		HBox hbox = new HBox();
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("文件");
		MenuItem open1 = new MenuItem("打开文本文件");
		MenuItem save1 = new MenuItem("保存文本文件");
		MenuItem open2 = new MenuItem("打开二进制文件");
		MenuItem save2 = new MenuItem("保存二进制文件");
		MenuItem clear = new MenuItem("清除数据");
		Label locate = new Label();
		VBox vbox = new VBox();
		VBox countpane = new VBox();
		GridPane countpane1 = new GridPane();
		GridPane countpane2 = new GridPane();
		HBox countpane3 = new HBox();

		mainpane.getChildren().addAll(menuBar, hbox, locate);

		hbox.getChildren().addAll(vbox, countpane);

		menuBar.getMenus().addAll(menuFile);
		menuFile.getItems().add(open1);
		menuFile.getItems().add(open2);
		menuFile.getItems().add(clear);

		TableColumn numberCol = new TableColumn("学号");
		TableColumn nameCol = new TableColumn("姓名");
		TableColumn scoreCol = new TableColumn("成绩");

		numberCol.setPrefWidth(120);
		numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		nameCol.setPrefWidth(70);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		scoreCol.setPrefWidth(60);
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		table.setEditable(true);
		table.getColumns().addAll(numberCol, nameCol, scoreCol);

		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(new Label("成绩单"), table);
		
		countpane.getChildren().addAll(new Label("数据分析"),countpane1,countpane2,countpane3);
		countpane.setPadding(new Insets(10, 10, 10, 10));
		countpane.setSpacing(20);
		countpane.setMaxWidth(400);
		countpane1.setHgap(5);
		countpane1.setVgap(7);
		countpane1.setAlignment(Pos.CENTER_LEFT);
		countpane2.setHgap(5);
		countpane2.setVgap(7);
		countpane2.setAlignment(Pos.CENTER_LEFT);
		TextField max = new TextField();
		max.setMaxWidth(70);
		max.setAlignment(Pos.TOP_RIGHT);
		TextField min = new TextField();
		min.setMaxWidth(70);
		min.setAlignment(Pos.TOP_RIGHT);
		TextField ave = new TextField();
		ave.setMaxWidth(70);
		ave.setAlignment(Pos.TOP_RIGHT);
		TextField rank1 = new TextField();
		rank1.setMaxWidth(70);
		rank1.setAlignment(Pos.TOP_RIGHT);
		TextField rank2 = new TextField();
		rank2.setMaxWidth(70);
		rank2.setAlignment(Pos.TOP_RIGHT);
		TextField rank3 = new TextField();
		rank3.setMaxWidth(70);
		rank3.setAlignment(Pos.TOP_RIGHT);
		TextField rank4 = new TextField();
		rank4.setMaxWidth(70);
		rank4.setAlignment(Pos.TOP_RIGHT);
		TextField rank5 = new TextField();
		rank5.setMaxWidth(70);
		rank5.setAlignment(Pos.TOP_RIGHT);

		TextField rate1 = new TextField();
		rate1.setMaxWidth(70);
		rate1.setAlignment(Pos.TOP_RIGHT);
		TextField rate2 = new TextField();
		rate2.setMaxWidth(70);
		rate2.setAlignment(Pos.TOP_RIGHT);
		TextField rate3 = new TextField();
		rate3.setMaxWidth(70);
		rate3.setAlignment(Pos.TOP_RIGHT);
		TextField rate4 = new TextField();
		rate4.setMaxWidth(70);
		rate4.setAlignment(Pos.TOP_RIGHT);
		TextField rate5 = new TextField();
		rate5.setMaxWidth(70);
		rate5.setAlignment(Pos.TOP_RIGHT);

		
		countpane1.setPadding(new Insets(20,10,20,10));
		countpane1.addColumn(1, max,min,ave);
		countpane2.addColumn(1, rank1,rank2,rank3,rank4,rank5);
		countpane2.addColumn(3, rate1,rate2,rate3,rate4,rate5);
		countpane1.add(new Label("最高分             "), 0, 0);
		countpane1.add(new Label("最低分"), 0, 1);
		countpane1.add(new Label("平均分"), 0, 2);
		countpane2.add(new Label("   优秀(90-100)   "), 0, 0);
		countpane2.add(new Label("   良好(80-89)"), 0, 1);
		countpane2.add(new Label("   中等(70-79)"), 0, 2);
		countpane2.add(new Label("   及格(60-69)"), 0, 3);
		countpane2.add(new Label("   不及格(0-60)"), 0, 4);
		for(int i=0;i<=4;i++){
			countpane2.add(new Label("人,占"), 2, i);
			countpane2.add(new Label("%"), 4, i);
		}
		open1.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("打开文件");
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				final ObservableList<Student> studentlist = Data.getStudentlist(file, "ANSI");
				table.setItems(studentlist);
				locate.setText("  " + file.getPath());
			}
		});
		open2.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("打开文件");
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				final ObservableList<Student> studentlist = Data.getStudentlist(file, "UTF-8");
				table.setItems(studentlist);
				locate.setText("  " + file.getPath());

			}
		});
		clear.setOnAction(e -> {
			table.setItems(null);
		});

		primaryStage.setScene(mainscene);
		primaryStage.setTitle("学生成绩分析程序");
		primaryStage.show();

		class count implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根

			}

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
