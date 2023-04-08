public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }

    public int Strength() {
        int Score = 0;

        String s = this.Value;

        boolean Upper = false;
        boolean Lower = false;
        boolean Num = false;
        boolean Symbol = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if ((int) c >= 65 && (int) c <= 90) Upper = true;
            if ((int) c >= 97 && (int) c <= 122) Lower = true;
            if ((int) c >= 48 && (int) c <= 57) Num = true;
            if (((int) c >= 34 && (int) c <= 47)||((int) c >= 58 && (int) c <= 64)||((int) c >= 91 && (int) c <= 96)) Symbol = true;

        }

        if (Upper) {
            Score += 1;
        }

        if (Lower) {
            Score += 1;
        }
        if (Num) {
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