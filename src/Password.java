public class Password {
    Generator generator = new Generator();
    String Value;
    int Length;

    public Password(){
        Value = "";
        Length = 0;
    }
    public Password(String s) {
        Value = s;
        Length = s.length();
    }

    public int Strength() {
        int Score = 0;

        String s = this.Value;

        boolean Upper = false;
        boolean Lower = false;
        boolean Number = false;
        boolean Symbol = false;

        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));

            if(generator.UPPERCASE_LETTERS.contains(c)) Upper = true;
            if(generator.LOWERCASE_LETTERS.contains(c)) Lower = true;
            if(generator.NUMBERS.contains(c)) Number = true;
            if(generator.SYMBOLS.contains(c)) Symbol = true;


        }

        if (Upper) {
            Score += 1;
        }

        if (Lower) {
            Score += 1;
        }
        if (Number) {
            Score += 1;
        }
        if (Symbol) {
            Score += 1;

        }
        if (s.length() >= 8) {
            Score += 1;
        }

        return Score;
    }

    public String calculateScore() {
        int Score = this.Strength();

        switch (Score){
            case 5 -> {
                return "5/5";
            }
            case 4 -> {
                return "4/5";
            }
            case 3 -> {
                return "3/5";
            }
            case 2 -> {
                return "2/5";
            }
            case 1 -> {
                return "1/5";
            }
        }
        return "0/5";

    }

}