package model;

/**
 * This class provides the model "User", which is used for the login process
 */
public class User {

    private final String userName;

    private final String password;

    /**
     * default constructor
     *
     * @param userName sets val the private var
     * @param password sets val the private var
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Getter of the username
     *
     * @return the username of the private var
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter of the password
     *
     * @return the password of the private var
     */
    public String getPassword() {
        return password;
    }

}
