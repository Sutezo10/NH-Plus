package model;

public class Caretaker extends Person {

    private long cid;

    private long phoneNumber;

    public Caretaker(String firstName, String surname, long cid, long phoneNumber) {
        super(firstName, surname);
        this.cid = cid;
        this.phoneNumber = phoneNumber;
    }


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
