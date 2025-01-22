import Exceptions.PreaMulteCursuriException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcademieTest {

    @Test
    @DisplayName("Test if singleton generates a single instance on all threads")
    public void singletonInstanceThreadSafeTest() {
        // Create multiple threads to access the singleton instance concurrently
        Thread t1 = new Thread(() -> {
            Academie a1 = Academie.getInstance();
            try {
                Thread.sleep(100);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            Academie a2 = Academie.getInstance();

            //Assert both instances are obtained in the same thread at the same time;
            assertEquals(a1, a2);
        });

        Thread t2 = new Thread(() -> {
            Academie a3 = Academie.getInstance();
            try {
                Thread.sleep(100);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            Academie a4 = Academie.getInstance();

            //Assert both instances are obtained in the same thread at the same time;
            assertEquals(a3, a4);
        });

        //start threads
        t1.start();
        t2.start();

        //wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Academie cursuri;

    @BeforeEach
    void setUp() {
        cursuri = Academie.getInstance(); // Initialize the Cursuri class before each test
    }

    @AfterEach
    void setup(){
        cursuri.reset();
    }


    @Test
    void testAdaugaCurs_ValidProgramare() {
        //Given
        String[] data = {"Programare", "Java Course", "250.0"};
        //When
        cursuri.adaugaCurs(data);
        //Then
        assertEquals(1, cursuri.getCursuri().size()); // Assuming getCursuri() returns the list of courses
    }

    @Test
    void testAdaugaCurs_ValidLimbiStraine() {
        String[] data = {"Limbi Straine", "English Course", "200.0"};
        cursuri.adaugaCurs(data);
        assertEquals(1, cursuri.getCursuri().size());
    }

    @Test
    void testAdaugaCurs_InvalidCourseType() {
        String[] data = {"Mathematics", "Algebra Course", "150.0"};
        cursuri.adaugaCurs(data);
        assertEquals(0, cursuri.getCursuri().size()); // No course should be added
    }

    @Test
    void testAdaugaCurs_ExceedingMaxCourses() {
        //Given
        for (int i = 1; i <= 30; i++) {
            String[] data = {"Programare", "Course " + i, "100.0"};
            cursuri.adaugaCurs(data);
        }
        //When
        Exception exception = assertThrows(PreaMulteCursuriException.class, () -> {
            String[] data = {"Programare", "Another Course", "100.0"};
            cursuri.adaugaCurs(data);
        });
        //Then
        assertEquals("Nu mai sunt locuri disponibile", exception.getMessage()); // Adjust the message accordingly
    }

    @Test
    void testAdaugaCurs_InvalidDataFormat() {
        String[] data = {"Programare", "Python Course", "invalid_number"};
        cursuri.adaugaCurs(data);
        assertEquals(0, cursuri.getCursuri().size()); // No course should be added
    }
}