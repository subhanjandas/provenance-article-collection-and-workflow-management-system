package test.collective.start;

import io.collective.start.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private App app;

    @Before
    public void setUp() {
        app = new App(8888); // Ensure the port number matches the constructor
        app.start();
    }

    @After
    public void tearDown() {
        app.stop();
    }

    @Test
    public void testApp() {
        // Add your test cases here
    }
}
