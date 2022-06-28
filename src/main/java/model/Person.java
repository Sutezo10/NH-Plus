package model;

/**
 * Abstract Class to get implemented from other model classes, which need the structure of a person
 */
public abstract class Person {
    private String firstName;
    private String surname;

    /**
     * Default constructor
      * @param firstName sets the val of the private var
     * @param surname sets the val of the private var
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     * Getter of firstname
     * @return the val of firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter of firstname
     * @param firstName sets the val of firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter of surname
     * @return sets the val of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter of surname
     * @param surname sets the val of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
