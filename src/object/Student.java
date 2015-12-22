package object;
import javafx.beans.property.SimpleStringProperty; 
public class Student {
	private SimpleStringProperty number;
	private SimpleStringProperty name;
	private int score;
	
	public Student(String number, String name, int score) {
		super();
		this.number = new SimpleStringProperty(number);
		this.name = new SimpleStringProperty(name);
		this.score = score;
	}

	public String getNumber() {
		return number.get();
	}

	public void setNumber(String number) {
		this.number = new SimpleStringProperty(number);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
