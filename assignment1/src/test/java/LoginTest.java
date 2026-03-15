import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seng2050.Student;
import seng2050.StudentService;

public class LoginTest {
    private final StudentService studentService = new StudentService();

    @Test
    public void testLogin() {
        Student loginSuccess = studentService.authenticateStudent("c1111", "1234");
        assertEquals(true, loginSuccess != null);

        Student loginFail = studentService.authenticateStudent("c1111", "wrongpassword");
        assertEquals(false, loginFail != null);

        Student loginNonExistent = studentService.authenticateStudent("nonexistent", "1234");
        assertEquals(false, loginNonExistent != null);
    }
}
