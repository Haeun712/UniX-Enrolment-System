import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seng2050.Student;

public class StudentTest {
    
    private final Student student = new Student("c123", "John", "Doe", "password123", null);

    @Test
    public void testGetId() {
        assertEquals("c123", student.getStdNo());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", student.getGivenNames());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", student.getLastName());
    }

    @Test
    public void testGetPasswordHash() {
        assertEquals("password123", student.getPasswordHash());
    }
}