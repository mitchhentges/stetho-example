package ca.fuzzlesoft.stethoexample;

/**
 * @author mitch
 * @since 3/10/16.
 */
public class Ninja {
    private final String name;
    private final String email;

    public Ninja(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
