package nz.ac.vuw.jenz.spotbugs;

/**
 * Bean-style simple domain object with a seeded bug:
 * class implements equals, but not hashCode. This violates the contract, and will lead to unexpected
 * behaviour in collections based on hashing (HashSet, HashMap), in particular failed lookups (get(..) does not return expected value).
 * @author jens dietrich
 */
public class Person {
    private String name = "";
    private String firstName = "";
    private int age = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        if (age != person.age) return false;
        if (!name.equals(person.name)) return false;
        return firstName.equals(person.firstName);
    }

}
