//package Pawn.System.StudentMg;
//
//@FunctionalInterface
//interface StudentFilter {
//    boolean filter(StudentManager.Student student);
//}
//
//public class Test {
//
//    public static void main(String[] args) {
//
//        StudentManager student = new StudentManager();
//        StudentManager.Student Student1 = student.new Student("nalen", 12, 90);
//        StudentManager.Student Student2 = student.new Student("molika", 13, 75);
//        StudentManager.Student Student3 = student.new Student("roth", 13, 75);
//
//        student.addStudent(Student1);
//        student.addStudent(Student2);
//        student.addStudent(Student3);
//// Calculate average grade
//        double averageGrade = calculateAverageGrade(StudentManager);
//        System.out.println("Average Grade: " + averageGrade);
//    }
//
//    // Method to calculate the average grade
//    private static double calculateAverageGrade(StudentManager studentManager) {
//        double sum = 0;
//        int count = 0;
//
//        for (StudentManager.Student student : studentManager.getStudents()) {
//            sum += student.getGrade();
//            count++;
//        }
//}
//
//
