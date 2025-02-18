package Pawn.System.StudentMg;

import java.util.ArrayList;



public class StudentManager {
    private ArrayList<Student> studentArrayList = new ArrayList<Student>();

    class Student {
        String name;
        int age;
        int grade;

        public int getGrade() {
            return grade;
        }

        public Student(String name, int age, int grade) {
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public Student() {

        }
    }
    public void addStudent(Student s){

        this.studentArrayList.add(s);
    }


    public ArrayList<Student> setStudent(){
        return this.studentArrayList ;
    }

}