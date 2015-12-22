package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;  
import javafx.collections.ObservableList; 
import java.util.ArrayList;
import java.util.Scanner;

import object.*;


public class Data{
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
						studentlist.add(new Student(data[0],data[1],Integer.parseInt(data[2])));
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
						studentlist.add(new Student(data[0],data[1],Integer.parseInt(data[2])));
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
}
