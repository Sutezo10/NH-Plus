package model;

public class Caretaker extends Person {

    private long cid;

    private String phoneNumber;

    public Caretaker(String firstName, String surname, long cid, String phoneNumber) {
        super(firstName, surname);
        this.cid = cid;
        this.phoneNumber = phoneNumber;
    }

    public Caretaker(String firstName, String surname, String phoneNumber) {
        super(firstName, surname);
        this.phoneNumber = phoneNumber;
    }


    public long getCid() {
        return cid;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

