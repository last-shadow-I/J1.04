import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  @Test
  void getMinElementTest() {
    int[][] ints = new int[2][4];

    ints[0] = new int[] {1, 2, 3, 4};
    ints[1] = new int[] {-1, 0, -2, 10};

    assertEquals(-2, Main.getMinElement(ints));

    double[][] doubles = new double[2][4];

    doubles[0] = new double[] {4, 3, 3, 5};
    doubles[1] = new double[] {1, -10, -2, 10};

    assertEquals(-10, Main.getMinElement(doubles));

  }


}