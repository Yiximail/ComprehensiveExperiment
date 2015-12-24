package object;
import javafx.beans.property.SimpleStringProperty; 
public class Student {
	private SimpleStringProperty number;
	private SimpleStringProperty name;
	private SimpleStringProperty score;
	
	public Student(String number, String name, String score) {
		super();
		this.number = new SimpleStringProperty(number);
		this.name = new SimpleStringProperty(name);
		this.score = new SimpleStringProperty(score);
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

	public String getScore() {
		return score.get();
	}

	public void setScore(String score) {
		this.score = new SimpleStringProperty(score);
	}
	
	
}
