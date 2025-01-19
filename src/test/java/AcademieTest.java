import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcademieTest {

    @Test
    public void testSingletonInstance() {
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
}