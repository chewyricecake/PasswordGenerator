;
import java.util.Scanner;

public class Generator {

    public int minLength = 0;
    public static Scanner keyboard;

    public StringBuilder pool;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "1234567890";
    public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";



    public String Characters(boolean Upper, boolean Lower, boolean Number, boolean Symbol) {

        pool = new StringBuilder();

        if (Upper) pool.append(UPPERCASE_LETTERS);
        if (Lower) pool.append(LOWERCASE_LETTERS);
        if (Number) pool.append(NUMBERS);
        if (Symbol) pool.append(SYMBOLS);

        return pool.toString();
    }

    private char randomCharacter(int type){
        switch(type){
            case 1 -> {
                return (char)randomNumber(65,90);
            }
            case 2 -> {
                return (char)randomNumber(97,122);
            }
            case 3 -> {
                return (char)randomNumber(48,57);
            }
            case 4 -> {
                int chance = randomNumber(1,3);
                switch(chance){
                    case 1 ->{
                        return (char)randomNumber(34,47);
                    }
                    case 2->{
                        return (char)randomNumber(58,64);
                    }
                    case 3->{
                        return (char)randomNumber(91,96);
                    }
                }
            }
        }
        return 'a';
    }

    private int randomNumber(int min, int max){
        return min + (int)(Math.random()*((max-min)+1));
    }




    private String GeneratePassword(boolean Upper, boolean Lower, boolean Number, boolean Symbol, int length) {

        String password = "";

        if(Upper){
            password+=randomCharacter(1);
            length -=1;
        }

        if(Lower){
            password+=randomCharacter(2);
            length -=1;
        }
        if(Number){
            password+=randomCharacter(3);
            length -=1;
        }
        if(Symbol){
            password+=randomCharacter(4);
            length -=1;
        }

        for(int i =0; i<length; i++){
            String pool = Characters(Upper,Lower,Number,Symbol);
            password += pool.charAt(randomNumber(0,pool.length()));
        }

        return password;
    }

    public void requestPassword() {
        minLength = 0;

        boolean Upper = false;
        boolean Lower = false;
        boolean Number = false;
        boolean Symbol = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Answer the following questions with O or X \n");

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Lowercase? ");
                input = keyboard.next();
            } while (!input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("X"));

            if (input.equalsIgnoreCase("O")) {
                Lower = true;
                minLength += 1;
            }
            else if (input.equalsIgnoreCase("X")) Lower = false;

            do {
                System.out.println("Uppercase? ");
                input = keyboard.next();
            } while (!input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("X"));

            if (input.equalsIgnoreCase("O")) {
                Upper = true;
                minLength += 1;
            }
            else if (input.equalsIgnoreCase("X")) Upper = false;

            do {
                System.out.println("Numbers? ");
                input = keyboard.next();
            } while (!input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("X"));

            if (input.equalsIgnoreCase("O")) {
                Number = true;
                minLength += 1;
            }
            else if (input.equalsIgnoreCase("X")) Number = false;

            do {
                System.out.println("Symbols? ");
                input = keyboard.next();
            } while (!input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("X"));

            if (input.equalsIgnoreCase("O")) {
                Symbol = true;
                minLength += 1;
            }
            else if (input.equalsIgnoreCase("X")) Symbol = false;

            if (!Upper && !Lower && !Number && !Symbol) {
                System.out.println("Password cannot be empty\n");
                correctParams = true;
            }

        } while (correctParams);

        // minimum length based on the must-have characters
        System.out.printf("Your minimum length based on the character requirements is %d \n",minLength);
        System.out.print("Enter the length of the password in integer value: ");
        int length = keyboard.nextInt();

        final String password = GeneratePassword(Upper, Lower, Number, Symbol, length);

        System.err.println("Your password -> " + password);
    }


    public void testPassword() {

        System.out.print("\nEnter your password:");
        String input = keyboard.next();

        final Password password = new Password(input);

        System.out.println(password.calculateScore());
    }


}