import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestApp {

    private App app = new App();

    @Test
    public void testValidInputFirstClosest() {
        int[] x_pos = {-3,1, 5,};
        int[] y_pos = {2, 4, -2};
        int targetX = 0;
        int targetY = 0;
        assertEquals(Math.sqrt(13), app.demo(x_pos, y_pos, targetX, targetY), "The closest point should be (-3, 2)");
    }

    @Test
    public void testValidInputNotFirstClosest() {
        int[] x_pos = {10, -1, 2};
        int[] y_pos = {10, 2, -2};
        int targetX = 0;
        int targetY = 0;
        assertEquals(Math.sqrt(5), app.demo(x_pos, y_pos, targetX, targetY), "The closest point should be (-1, 2)");
    }

    @Test
    public void testNullInputArrays() {
        int targetX = 0;
        int targetY = 0;
        assertEquals(-1, app.demo(null, null, targetX, targetY), "Should return -1 for null inputs");
    }

    @Test
    public void testArraysOfDifferentLengths() {
        int[] x_pos = {1, 2};
        int[] y_pos = {3};
        int targetX = 0;
        int targetY = 0;
        assertEquals(-1, app.demo(x_pos, y_pos, targetX, targetY), "Should return -1 for arrays of different lengths");
    }

    @Test
    public void testEmptyArrays() {
        int[] x_pos = {};
        int[] y_pos = {};
        int targetX = 0;
        int targetY = 0;
        assertEquals(-1, app.demo(x_pos, y_pos, targetX, targetY), "Should return -1 for empty arrays");
    }
}
