
import java.util.Scanner;

public class Main {

    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        Generator generator = new Generator(keyboard);

        menu();

        String input = "-1";

        while(!input.equals("3")){
            input = keyboard.next();

            switch(input){
                case "1":{
                    generator.requestPassword();
                    menu();
                }
                case "2":{
                    generator.testPassword();
                    menu();
                }
                case "3":{
                    System.out.println("Exiting program");
                }
            }

        }
        keyboard.close();
    }

    private static void menu() {
        System.out.println();
        System.out.println(":: PASSWORD GENERATOR ::");
        System.out.println();
        System.out.println("1 Generate Password");
        System.out.println("2 Test Password");
        System.out.println("3 Quit");
        System.out.print("Enter: ");
    }
}
