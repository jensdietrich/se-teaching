package test.nz.ac.vuw.jenz.xmlser;

import nz.ac.vuw.jenz.xmlser.Student;
import org.junit.Test;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import static org.junit.Assert.assertEquals;

public class TestStudentSerialization {

    @Test
    public void roundtripStudentInstance() throws Exception {

        Student student = new Student(1,"Jim","Jones");
        File f = new File("student.xml");

        // write Student instance to file
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(f));
        encoder.writeObject(student);
        encoder.close();
        System.out.println("Object written to " + f.getAbsolutePath());

        // read Student instance from file
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(f));
        Student student2 = (Student)decoder.readObject();
        decoder.close();

        // compare !
        assertEquals(student.getId(),student2.getId());
        assertEquals(student.getFirstName(),student2.getFirstName());
        assertEquals(student.getName(),student2.getName());

    }
}
