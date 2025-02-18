package Pawn.System.InnerClass;


import Pawn.System.LoginManager;
import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.IdentityHashMap;

public class NewTest {


    public static void main(String[] agrs){
        JFrame mainFrame = new JFrame("My First GUI ");
        mainFrame.setSize(300,300);
        mainFrame.setLayout(new GridLayout(3,1));
        JTextField nametext = new JTextField("Enter your name");
        JTextField passWord = new JTextField("Enter your Password");
        JButton submit = new JButton("Submit");
        JLabel SubmitHere = new JLabel("Submit you info here ");

        JPanel submitPanel = new JPanel(new GridLayout(1,2));
        submitPanel.add(SubmitHere);
        submitPanel.add(submit);
        submit.setBackground(new Color(225,225,225));

        JCheckBox maleCheckBox = new JCheckBox("Male");
        JCheckBox femaleCheckBox = new JCheckBox("Female");
        JPanel gender = new JPanel(new GridLayout(1,2));
        gender.add(maleCheckBox);
        gender.add(femaleCheckBox);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(femaleCheckBox);
        genderGroup.add(maleCheckBox);

        JPanel userinfo = new JPanel(new GridLayout(1,2));

        userinfo.add(nametext);
        userinfo.add(passWord);

        mainFrame.add(userinfo);
        mainFrame.add(gender);
        mainFrame.add(submitPanel);





        LoginManager loginManager = new LoginManager();
        loginManager.populateInitialEmployees();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StringBuilder gender = new StringBuilder();
                if (maleCheckBox.isSelected()) {
                    gender.append("Male ");
                }
                if (femaleCheckBox.isSelected()) {
                    gender.append("Female ");
                }
                System.out.println("Selected Gender: " + gender.toString().trim());
                String a = nametext.getText();
                String b = passWord.getText();
                LoginManager.LoginTest(a,b);
                mainFrame.dispose();


            }
        });

//        JPanel myPanel = new JPanel();
//        myPanel.setLayout(new GridLayout(2,2));
//        myPanel.add(new JButton("1.1"));
//        myPanel.add(new JButton("1.2"));
//        myPanel.add(new JButton("2.1"));
//        myPanel.add(new JButton("2.2"));
//
//        mainFrame.add(myPanel);
//        mainFrame.add(new JButton("2"));
//        mainFrame.add(new JButton("3"));
//        mainFrame.add(new JButton("4"));
//        mainFrame.add(new JButton("5"));
//        mainFrame.add(new JButton("6"));
//


//        JButton firstButton = new JButton("Click me");
//        firstButton.setBackground(Color.yellow);
//        mainFrame.add(firstButton);
//        // with Lambda expression it shorts and essay for something short or short action just to print
//        firstButton.addActionListener(e->System.out.println("Hello from first button"));
//
//
//
//
//        JButton SecondButton = new JButton("Click Me Again");
//        SecondButton.setBackground(Color.red);
//        mainFrame.add(SecondButton);
//        SecondButton.addActionListener(new ActionListener() {
//            // it's useful when you try to do something complicate
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Hey you click it again!");
//            }
//        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);





    }
}
////@FunctionalInterface
////interface greeting{
////    // if we want to use Functional interface we can use only one method
////    void sayhello();
////@FunctionalInterface
////interface vote{
////    Boolean testing(int age);
////}
////
////
////abstract class Animal{
////    void sayname(){
////        System.out.println("Dog");
////    }
////}
//
//@FunctionalInterface
//interface calculator{
//    int input(int a , int b);
//}
//public class NewTest {
//
//
//    public static void main(String[] args) {
//
//        calculator add = (a,b) -> a + b;
//        calculator minus = (a,b) -> a - b;
//        calculator mul = (a,b) -> a * b;
//        calculator div = (a,b) -> a / b;
//
//        System.out.println("add result : " + add.input(2,4));
//        System.out.println("minus result : " + minus.input(10,6));
//        System.out.println("multiply result : " + mul.input(8,4));
//        System.out.println("Division result : " + div.input(9,6));
//
//
//
//
////        Animal dog = new Animal() {
////            @Override
////            void sayname() {
////                super.sayname();
////            }
////        };
////        greeting g = new greeting() {
////
////            @Override
////            public void sayhello() {
////                System.out.println("Hello");
////            }
////
////        };
////
////        vote v = (age) -> {
////            if (age < 18) {
////                return true;
////
////            } else {
////                return false;
////            }
////        };
////
////
////        dog.sayname();
////        g.sayhello();
////
////
//////        Outer OC = new Outer();
//////        Outer.Inner In = OC.new Inner();
//////        In.message();
//////
//////        Outer.Inner oct = OC. new Inner(){
//////            @Override
//////            void message(){
//////                System.out.println("hi");
//////                this.sayName();
//////            }
//////            void sayName(){
//////              System.out.println("Hi again");
//////
//////            }
//////        };
//////        oct.message();
////    }
////
////}
//    }
//}
