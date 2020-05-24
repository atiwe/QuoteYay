package se.mau.ai0405.p3;

/**
 * An entity class for a user of the application.
 *
 * @author Hampus Hansson
 */
public class User {
    private String name;

    /**
     * Constructor
     *
     * @param name The name of the user
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }
}
