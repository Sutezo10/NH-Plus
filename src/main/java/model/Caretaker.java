package model;

/**
 * This class contains the structure of caretakers in a nursing home
 */
public class Caretaker extends Person {

    private long cid;

    private String phoneNumber;

    /**
     * Default constructor, with cid
     *
     * @param firstName   sets the val of the private var
     * @param surname     sets the val of the private var
     * @param cid         sets the val of the private var
     * @param phoneNumber sets the val of the private var
     */
    public Caretaker(String firstName, String surname, long cid, String phoneNumber) {
        super(firstName, surname);
        this.cid = cid;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Default constructor, without cid
     *
     * @param firstName   sets the val of the private var
     * @param surname     sets the val of the private var
     * @param phoneNumber sets the val of the private var
     */
    public Caretaker(String firstName, String surname, String phoneNumber) {
        super(firstName, surname);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter of cid
     *
     * @return the val of cid
     */
    public long getCid() {
        return cid;
    }

    /**
     * Getter of phoneNumber
     *
     * @return the val of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter of phoneNumber
     *
     * @param phoneNumber sets the val of phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

