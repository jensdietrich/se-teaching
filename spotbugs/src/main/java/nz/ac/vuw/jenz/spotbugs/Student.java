package nz.ac.vuw.jenz.spotbugs;

/**
 * Bean-style simple domain object with a seeded bug:
 * class implements equals and hashCode. However, hashCode also uses the age field, while equals does not.
 * This violates the contract between the methods, and will lead to unexpected
 * behaviour in collections based on hashing (HashSet, HashMap), in particular failed lookups (get(..) does not return expected value).
 * (the current version of) SpotBugs does not detect this bug !! This example illustrates the limitations of the tool.
 * @author jens dietrich
 */
public class Student {
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
        Student student = (Student) o;
        if (!name.equals(student.name)) return false;
        return firstName.equals(student.firstName);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
