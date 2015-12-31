package display;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import object.Student;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Display extends Application {
	public static ObservableList<Student> studentlist = FXCollections.observableArrayList();
	public static final TableView<Student> table = new TableView<>();
	private static VBox mainpane = new VBox();
	private static HBox hbox = new HBox();
	private static MenuBar menuBar = new MenuBar();
	private static HBox check = new HBox();
	private static Menu menuFile = new Menu("文件");
	private static MenuItem open1 = new MenuItem("打开文本文件");
	private static MenuItem save1 = new MenuItem("另存为文本文件");
	private static MenuItem open2 = new MenuItem("打开二进制文件");
	private static MenuItem save2 = new MenuItem("另存为二进制文件");
	private static MenuItem clear = new MenuItem("清除数据");
	private static Label locate = new Label();
	private static VBox vbox = new VBox();
	private static VBox countpane = new VBox();
	private static GridPane countpane1 = new GridPane();
	private static GridPane countpane2 = new GridPane();
	private static HBox countpane3 = new HBox();
	private static TextField data = new TextField();
	private static TableColumn<Student, String> numberCol = new TableColumn<>("学号");
	private static TableColumn<Student, String> nameCol = new TableColumn<>("姓名");
	private static TableColumn<Student, String> scoreCol = new TableColumn<>("成绩");
	private static TextField max = new TextField();
	private static TextField min = new TextField();
	private static TextField ave = new TextField();
	private static TextField rank1 = new TextField();
	private static TextField rank2 = new TextField();
	private static TextField rank3 = new TextField();
	private static TextField rank4 = new TextField();
	private static TextField rank5 = new TextField();
	private static TextField rate1 = new TextField();
	private static TextField rate2 = new TextField();
	private static TextField rate3 = new TextField();
	private static TextField rate4 = new TextField();
	private static TextField rate5 = new TextField();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		Scene mainscene = new Scene(mainpane, 600, 550);
		mainpane.getChildren().addAll(menuBar, check,hbox, locate);
		mainscene.getStylesheets().add("display/Display.css");
		check.getChildren().addAll(new Label("输入学号、名字或成绩："),data);
		check.setAlignment(Pos.CENTER_LEFT);
		check.setSpacing(20);
		check.setPadding(new Insets(10,10,10,10));
		
		data.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				if(data.getText().length()!=0){
					table.setItems(null);
					ObservableList<Student> checklist = FXCollections.observableArrayList();
					String str = data.getText();
					for(Student s:studentlist){
						if((s.getNumber().indexOf(str)>=0)||(s.getName().indexOf(str)>=0)||(s.getScore().indexOf(str)>=0))
							checklist.add(s);
					}
					table.setItems(checklist);
				}
				else{
					table.setItems(studentlist);
				}
			}
		});
		
		hbox.getChildren().addAll(vbox, countpane);

		menuBar.getMenus().addAll(menuFile);
		menuFile.getItems().addAll(open1,save1,new SeparatorMenuItem(),open2,save2,new SeparatorMenuItem(),clear);

		numberCol.setPrefWidth(100);
		numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		numberCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
		numberCol.setOnEditCommit(
	            (CellEditEvent<Student, String> t) -> {
	                ((Student) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNumber(t.getNewValue());
	        });
		nameCol.setPrefWidth(70);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
		nameCol.setOnEditCommit(
	            (CellEditEvent<Student, String> t) -> {
	                ((Student) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setName(t.getNewValue());
	                Controller.counting();
	        });
		scoreCol.setPrefWidth(60);
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		scoreCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
		scoreCol.setOnEditCommit(
	            (CellEditEvent<Student, String> t) -> {
	                ((Student) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setScore(t.getNewValue());
	                Controller.counting();
	        });
		table.setEditable(true);
		table.getColumns().addAll(numberCol, nameCol, scoreCol);
		
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(new Label("成绩单"), table);
		vbox.setStyle("-fx-stroke: black; -fx-fill: red;");
		
		
		
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
		
		max.setMaxWidth(70);
		max.setAlignment(Pos.TOP_RIGHT);
		max.setEditable(false);
		
		min.setMaxWidth(70);
		min.setAlignment(Pos.TOP_RIGHT);
		min.setEditable(false);
		
		ave.setMaxWidth(70);
		ave.setAlignment(Pos.TOP_RIGHT);
		ave.setEditable(false);
		
		rank1.setMaxWidth(70);
		rank1.setAlignment(Pos.TOP_RIGHT);
		rank1.setEditable(false);
		
		rank2.setMaxWidth(70);
		rank2.setAlignment(Pos.TOP_RIGHT);
		rank2.setEditable(false);
		
		rank3.setMaxWidth(70);
		rank3.setAlignment(Pos.TOP_RIGHT);
		rank3.setEditable(false);
		
		rank4.setMaxWidth(70);
		rank4.setAlignment(Pos.TOP_RIGHT);
		rank4.setEditable(false);
		
		rank5.setMaxWidth(70);
		rank5.setAlignment(Pos.TOP_RIGHT);
		rank5.setEditable(false);

		
		rate1.setMaxWidth(70);
		rate1.setAlignment(Pos.TOP_RIGHT);
		rate1.setEditable(false);
		
		rate2.setMaxWidth(70);
		rate2.setAlignment(Pos.TOP_RIGHT);
		rate2.setEditable(false);
		
		rate3.setMaxWidth(70);
		rate3.setAlignment(Pos.TOP_RIGHT);
		rate3.setEditable(false);
		
		rate4.setMaxWidth(70);
		rate4.setAlignment(Pos.TOP_RIGHT);
		rate4.setEditable(false);
		
		rate5.setMaxWidth(70);
		rate5.setAlignment(Pos.TOP_RIGHT);
		rate5.setEditable(false);
		
		
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
		countpane2.setHgap(7);
		countpane2.setVgap(14);
		for(int i=0;i<=4;i++){
			countpane2.add(new Label("人,占"), 2, i);
			countpane2.add(new Label("%"), 4, i);
		}
		open1.setOnAction(e -> Controller.openFile("ANSI"));
		save1.setOnAction(e-> Controller.saveStudentlist("ANSI"));
		open2.setOnAction(e -> Controller.openFile("UTF-8"));
		save2.setOnAction(e-> Controller.saveStudentlist("UTF-8"));
		clear.setOnAction(e -> Controller.cleanFile());
		
		Button display1 = new Button("显示柱型分析图");
		Button display2 = new Button("显示饼型分析图");
		countpane3.getChildren().addAll(display1,display2);
		display1.setOnAction(e -> barChart());
		display2.setOnAction(e -> pieChart());
		countpane3.setPadding(new Insets(30,10,10,10));
		countpane3.setSpacing(40);
		
		
		
		primaryStage.setScene(mainscene);
		primaryStage.setTitle("学生成绩分析程序");
		primaryStage.show();

		
	}
	
	private void barChart(){
		Stage bcstage = new Stage();
		final NumberAxis x = new NumberAxis();
        final CategoryAxis y = new CategoryAxis();
        final BarChart<String,Number> bc = new BarChart<>(y,x);
        
        XYChart.Series series = new XYChart.Series();
        int[] number = new int[5];
        if(getRank1().getText().length()==0)number[0]=0;
        else number[0]=Integer.parseInt(getRank1().getText());
        if(getRank2().getText().length()==0)number[1]=0;
        else number[1]=Integer.parseInt(getRank2().getText());
        if(getRank3().getText().length()==0)number[2]=0;
        else number[2]=Integer.parseInt(getRank3().getText());
        if(getRank4().getText().length()==0)number[3]=0;
        else number[3]=Integer.parseInt(getRank4().getText());
        if(getRank5().getText().length()==0)number[4]=0;
        else number[4]=Integer.parseInt(getRank5().getText());
        series.getData().add(new XYChart.Data("优秀(90-100)",number[0]));
        series.getData().add(new XYChart.Data("良好(80-89)",number[1]));
        series.getData().add(new XYChart.Data("中等(70-79)",number[2]));
        series.getData().add(new XYChart.Data("及格(60-69)",number[3]));
        series.getData().add(new XYChart.Data("不及格(0-60)",number[4]));
        
        bc.getData().addAll(series);
        bc.setLegendVisible(false);
        bcstage.setScene(new Scene(bc,500,300));
        bcstage.setTitle("柱形分析图");
        bcstage.show();
	}
	
	private void pieChart(){
		Stage pcstage = new Stage();
		int[] number = new int[5];
        if(getRank1().getText().length()==0)number[0]=0;
        else number[0]=Integer.parseInt(getRank1().getText());
        if(getRank2().getText().length()==0)number[1]=0;
        else number[1]=Integer.parseInt(getRank2().getText());
        if(getRank3().getText().length()==0)number[2]=0;
        else number[2]=Integer.parseInt(getRank3().getText());
        if(getRank4().getText().length()==0)number[3]=0;
        else number[3]=Integer.parseInt(getRank4().getText());
        if(getRank5().getText().length()==0)number[4]=0;
        else number[4]=Integer.parseInt(getRank5().getText());
		ObservableList<PieChart.Data> pcData =
                FXCollections.observableArrayList(
                new PieChart.Data("优秀(90-100)", number[0]),
                new PieChart.Data("良好(80-89)", number[1]),
                new PieChart.Data("中等(70-79)", number[2]),
                new PieChart.Data("及格(60-69)", number[3]),
                new PieChart.Data("不及格(0-60)", number[4]));
        final PieChart pc = new PieChart(pcData);
        pc.setLegendVisible(false);
        
        pcstage.setScene(new Scene(pc,500,300));
        pcstage.setTitle("饼型分析图");
        pcstage.show();
	}
	
	public static void setStudentlist(ObservableList<Student> list) {
		studentlist = list;
	}
	
	public static TableView<Student> getTable() {
		return table;
	}

	public static HBox getCheck() {
		return check;
	}

	public static Label getLocate() {
		return locate;
	}

	public static TextField getData() {
		return data;
	}

	public static TextField getMax() {
		return max;
	}

	public static TextField getMin() {
		return min;
	}

	public static TextField getAve() {
		return ave;
	}

	public static TextField getRank1() {
		return rank1;
	}

	public static TextField getRank2() {
		return rank2;
	}

	public static TextField getRank3() {
		return rank3;
	}

	public static TextField getRank4() {
		return rank4;
	}

	public static TextField getRank5() {
		return rank5;
	}

	public static TextField getRate1() {
		return rate1;
	}

	public static TextField getRate2() {
		return rate2;
	}

	public static TextField getRate3() {
		return rate3;
	}

	public static TextField getRate4() {
		return rate4;
	}

	public static TextField getRate5() {
		return rate5;
	}
	
	public static int getScore(int i){
		return Integer.parseInt(table.getItems().get(i).getScore());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
