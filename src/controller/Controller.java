package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import display.Display;
import object.*;


public class Controller{
	
	public static ObservableList<Student> getStudentlist(File file,String encoding){
		ObservableList<Student> studentlist = FXCollections.observableArrayList();
		String line;
		String[] data = new String[3];
		try {
			if(encoding=="ANSI"){
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath())));
				while((line = reader.readLine())!=null){
					if(line.length()==0);
					else{
						data = line.split(",");
						studentlist.add(new Student(data[0],data[1],data[2]));
					}
				}
				reader.close();
			}
			else if(encoding=="UTF-8"){
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()),"UTF-8"));
				while((line = reader.readLine())!=null){
					if(line.length()==0);
					else{
						data = line.split(",");
						studentlist.add(new Student(data[0],data[1],data[2]));
					}
				}
				reader.close();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return studentlist;
	}
	
	public static void saveStudentlist(String encoding){
		FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("保存文件");
	    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
	    if (encoding=="ANSI") {
	        try {
	    	    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文本文件", "*.java"),new FileChooser.ExtensionFilter("所有文件", "*.*"));
	    	    File file = fileChooser.showSaveDialog(null);
	        	FileOutputStream fos = new FileOutputStream(file);
	        	OutputStreamWriter osw = new OutputStreamWriter(fos);
	        	int i=0;
	        	while(Display.getTable().getColumns().get(0).getCellData(i)!=null){
	        		osw.write((String) Display.getTable().getColumns().get(0).getCellData(i)+","+(String) Display.getTable().getColumns().get(1).getCellData(i)+","+(String) Display.getTable().getColumns().get(2).getCellData(i)+"\r\n");
	        		i++;
	        		if(i>1000)break;
	        	}
	        	osw.flush();
	        	osw.close();
	        	fos.close();
	        } catch (IOException ex) {
	             System.out.println(ex.getMessage());
	        } catch (NullPointerException ex){
	        	System.out.println(ex.getMessage());
	        }
	    }
	    else if (encoding=="UTF-8") {
	        try {
	    	    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("score文件", "*.score"),new FileChooser.ExtensionFilter("所有文件", "*.*"));
	    	    File file = fileChooser.showSaveDialog(null);
	        	FileOutputStream fos = new FileOutputStream(file);
	        	OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
	        	int i=0;
	        	while(Display.getTable().getColumns().get(0).getCellData(i)!=null){
	        		osw.write((String) Display.getTable().getColumns().get(0).getCellData(i)+","+(String) Display.getTable().getColumns().get(1).getCellData(i)+","+(String) Display.getTable().getColumns().get(2).getCellData(i)+"\r\n");
	        		i++;
	        		if(i>1000)break;
	        	}
	        	osw.flush();
	        	osw.close();
	        	fos.close();
	        } catch (IOException ex) {
	             System.out.println(ex.getMessage());
	        } catch (NullPointerException ex){
	        	System.out.println(ex.getMessage());
	        }
	    }
	}
	
	public static void openFile(String encoding){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开文件");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			Display.setStudentlist(Controller.getStudentlist(file, encoding));
			Display.getTable().setItems(Controller.getStudentlist(file, encoding));
			Display.getLocate().setText("  " + file.getPath()+",共"+Controller.getStudentlist(file, encoding).size()+"人");
			counting();
		}
	}
	
	public static void counting(){
		int sum=0,maxScore=0,minScore=100,aveScore,score;
		int[] rank = {0,0,0,0,0};
		int number = Display.getTable().getItems().size();
		for(int i=0;i<number;i++){
			score=Display.getScore(i);
			sum += score;
			if(maxScore<score) maxScore = score;
			if(minScore>score) minScore = score;
			if(score>=90)rank[0]++;
			else if(score>=80) rank[1]++;
			else if(score>=70) rank[2]++;
			else if(score>=60) rank[3]++;
			else rank[4]++;
		}
		Display.getMax().setText(maxScore+"");
		Display.getMin().setText(minScore+"");
		Display.getAve().setText(String.format("%.2f", (double)sum/number));
		Display.getRank1().setText(rank[0]+"");
		Display.getRank2().setText(rank[1]+"");
		Display.getRank3().setText(rank[2]+"");
		Display.getRank4().setText(rank[3]+"");
		Display.getRank5().setText(rank[4]+"");
		Display.getRate1().setText(String.format("%.2f", (double)rank[0]/number*100));
		Display.getRate2().setText(String.format("%.2f", (double)rank[1]/number*100));
		Display.getRate3().setText(String.format("%.2f", (double)rank[2]/number*100));
		Display.getRate4().setText(String.format("%.2f", (double)rank[3]/number*100));
		Display.getRate5().setText(String.format("%.2f", (double)rank[4]/number*100));
	}
	
	public static void cleanFile(){
		Display.getTable().setItems(null);
		Display.getMax().setText("");
		Display.getMin().setText("");
		Display.getAve().setText("");
		Display.getRank1().setText("");
		Display.getRank2().setText("");
		Display.getRank3().setText("");
		Display.getRank4().setText("");
		Display.getRank5().setText("");
		Display.getRate1().setText("");
		Display.getRate2().setText("");
		Display.getRate3().setText("");
		Display.getRate4().setText("");
		Display.getRate5().setText("");
		Display.getLocate().setText("");
	}
}
