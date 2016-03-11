package ca.fuzzlesoft.stethoexample;

/**
 * @author mitch
 * @since 3/10/16.
 */
public class Ninja {
    private final String name;
    private final String email;
    private final String pictureUrl;

    public Ninja(String name, String email, String pictureUrl) {
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public static String deobfuscateEmail(String email) {
        StringBuilder result = new StringBuilder(email.length());
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c += 13;
                if (c > 122) {
                    c -= 26;
                }
            }

            result.append(c);
        }
        return result.toString();
    }
}
