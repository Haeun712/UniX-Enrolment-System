import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seng2050.Semester;

public class SemesterTest {
    
    private final Semester semester = new Semester(200, 1, 2023, true);

    @Test
    public void testGetId() {
        assertEquals(200, semester.getSemesterID());
    }

    @Test
    public void testGetSemester() {
        assertEquals(1, semester.getSemester());
    }

    @Test
    public void testGetYear() {
        assertEquals(2023, semester.getYear());
    }

    @Test
    public void testGetOpenForEnrolment() {
        assertEquals(true, semester.getOpenForEnrolment());
    }

}