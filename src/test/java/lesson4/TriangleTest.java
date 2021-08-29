package lesson4;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class TriangleTest {

    CalculatingTheAreaOfTriangle calcTriangle = new CalculatingTheAreaOfTriangle();
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.error("error");
    }

    @Test
    void CalculatingSquare() throws NegativeSideException {
        double result = calcTriangle.calcTheAreaOfTriangle(4, 6, 9);
        Assertions.assertEquals(9.56229574945264, result);
    }

    @Test
    void CalculatingSquareException() {
        assertThatExceptionOfType(NegativeSideException.class).isThrownBy(
                () -> calcTriangle.calcTheAreaOfTriangle(-3, 4, 5));
    }
}
