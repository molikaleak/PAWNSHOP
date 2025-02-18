package Pawn.System.InnerClass;

public class Outer{
    private String messString = "Hello";
    class Inner{
        void message(){
            System.out.println(messString);
        }

    }

}