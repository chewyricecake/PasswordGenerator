import java.util.Scanner;

public class Generator {

    public int minLength = 0;
    public static Scanner keyboard;

    public static StringBuilder pool;


    public Generator(){

    }
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

    private int randomNumber(int min, int max){
        return min + (int)(Math.random()*((max-min)+1));
    }


    public String CheckGeneratedPassword(boolean Upper, boolean Lower, boolean Number, boolean Symbol, int length){
        return GeneratePassword(Upper,Lower,Number,Symbol,length);
    }

    private String GeneratePassword(boolean Upper, boolean Lower, boolean Number, boolean Symbol, int length) {

        String password = "";

        if(!Upper&&!Lower&&!Number&&!Symbol){
            throw new IllegalArgumentException("At least one criteria should be selected.");
        }

        if(length == 0){
            throw new IllegalArgumentException("Password length cannot be 0.");
        }

        if(length<minLength){
            throw new IllegalArgumentException("Input length smaller than the minimum length.");
        }


        if(Upper){
            password+=UPPERCASE_LETTERS.charAt(randomNumber(0,UPPERCASE_LETTERS.length()-1));
            length -=1;
        }

        if(Lower){
            password+=LOWERCASE_LETTERS.charAt(randomNumber(0,LOWERCASE_LETTERS.length()-1));
            length -=1;
        }
        if(Number){
            password+=NUMBERS.charAt(randomNumber(0,NUMBERS.length()-1));
            length -=1;
        }
        if(Symbol){
            password+=SYMBOLS.charAt(randomNumber(0,SYMBOLS.length()-1));
            length -=1;
        }

        for(int i =0; i<length; i++){
            String pool = Characters(Upper,Lower,Number,Symbol);
            password += pool.charAt(randomNumber(0,pool.length()-1));
        }

        return password;
    }

    public void requestPassword() {
        minLength = 0;

        boolean Upper = false;
        boolean Lower = false;
        boolean Number = false;
        boolean Symbol = false;

        int length = 0;

        boolean wrongChar;
        boolean wrongLength;

        System.out.println();
        System.out.println("Answer the following questions with O or X \n");

        do {
            String input;
            wrongChar = false;

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
                System.out.println("<<Password cannot be empty>>\n");
                wrongChar = true;
            }

        } while (wrongChar);

        do{
            wrongLength = false;
            // minimum length based on the must-have characters
            System.out.printf("Your minimum length based on the character requirements is %d \n",minLength);
            System.out.print("Enter the length of the password in integer value: ");
            length = keyboard.nextInt();
            if(length<=minLength||length==0){
                System.out.println("<<Invalid length chosen: Please check the minimum length again>>");
                wrongLength = true;
            }
        }while(wrongLength);

        final String password = GeneratePassword(Upper, Lower, Number, Symbol, length);

        System.err.print("Your password -> " + password);
        System.out.println();

    }


    public void testPassword() {

        System.out.print("\nEnter your password:");
        String input = keyboard.next();

        final Password password = new Password(input);

        System.err.print("Your Score -> " + password.calculateScore());
        System.out.println();
    }



}