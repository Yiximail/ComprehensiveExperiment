package object;

import java.util.ArrayList;

public class Lesson {
	private String classname;
	private String lessonname;
	ArrayList<Student> studentlist;
	
	public Lesson(String classname,String lessonname){
		this.classname = classname;
		this.lessonname = lessonname;
		studentlist = new ArrayList<Student>();
		
	}
	
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	public String getLessonname() {
		return lessonname;
	}

	public void setLessonname(String lessonname) {
		this.lessonname = lessonname;
	}

	public void print(){
		System.out.println("°à¼¶Ãû×Ö£º"+classname);
		for(Student i:studentlist){
			System.out.println(i.getNumber()+"  "+i.getName()+"   "+i.getScore());
		}
	}
}
