import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsTest {

  int[] emptyArray = new int[0];
  int[] oneElementArray = new int[]{9};
  int[] threeElementArray = new int[]{9, 5, 3};
  int[] tenElementArray = new int[]{9, 9, 1, 2, 3, 4, 7, 8, 9, 4};
  int[] hundredThousandElementArray = new int[1_000_000];

  {
    Random generator = new Random(42);
    for (int i = 0; i < hundredThousandElementArray.length; i++) {
      hundredThousandElementArray[i] = generator.nextInt(-5, 6);
    }
  }

  @Test
  void getMode() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    assertNull(arrayStatistics.getMode());

    arrayStatistics = new ArrayStatistics(oneElementArray);
    assertEquals("[9]", arrayStatistics.getMode());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    assertEquals("[3, 5, 9]", arrayStatistics.getMode());

    arrayStatistics = new ArrayStatistics(tenElementArray);
    assertEquals("[9]", arrayStatistics.getMode());

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    assertEquals("[-5]", arrayStatistics.getMode());
  }

  @Test
  void getMedian() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    assertNull(arrayStatistics.getMedian());

    arrayStatistics = new ArrayStatistics(oneElementArray);
    assertEquals(9, arrayStatistics.getMedian());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    assertEquals(5, arrayStatistics.getMedian());

    arrayStatistics = new ArrayStatistics(tenElementArray);
    assertEquals(7, arrayStatistics.getMedian());

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    assertEquals(0, arrayStatistics.getMedian());
  }

  @Test
  void getAverage() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    assertEquals( NaN, arrayStatistics.getAverage());

    arrayStatistics = new ArrayStatistics(oneElementArray);
    assertEquals(9.0, arrayStatistics.getAverage());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    assertEquals(5.666666666666667, arrayStatistics.getAverage());

    arrayStatistics = new ArrayStatistics(tenElementArray);
    assertEquals(5.6, arrayStatistics.getAverage());

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    assertEquals(-0.002322, arrayStatistics.getAverage());
  }

  @Test
  void getVariance() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    assertEquals( NaN, arrayStatistics.getVariance());

    arrayStatistics = new ArrayStatistics(oneElementArray);
    assertEquals(0.0, arrayStatistics.getVariance());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    assertEquals(6.222222222222221, arrayStatistics.getVariance());

    arrayStatistics = new ArrayStatistics(tenElementArray);
    assertEquals(8.84, arrayStatistics.getVariance());

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    assertEquals(9.997578608231892, arrayStatistics.getVariance());
  }

  @Test
  void getGeometricMean() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    assertEquals( NaN, arrayStatistics.getGeometricMean());

    arrayStatistics = new ArrayStatistics(oneElementArray);
    assertEquals(9.0, arrayStatistics.getGeometricMean());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    assertEquals(5.12992784003009, arrayStatistics.getGeometricMean());

    arrayStatistics = new ArrayStatistics(tenElementArray);
    assertEquals(4.563716718670783, arrayStatistics.getGeometricMean());

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    assertEquals(0.0, arrayStatistics.getGeometricMean());
  }

  @Test
  void getShuffle() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    ArrayStatistics shuffleStatistic = new ArrayStatistics(arrayStatistics.getShuffle());
    assertEquals(shuffleStatistic.getMap(), arrayStatistics.getMap());

    arrayStatistics = new ArrayStatistics(oneElementArray);
    shuffleStatistic = new ArrayStatistics(arrayStatistics.getShuffle());
    assertEquals(shuffleStatistic.getMap(), arrayStatistics.getMap());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    shuffleStatistic = new ArrayStatistics(arrayStatistics.getShuffle());
    assertEquals(shuffleStatistic.getMap(), arrayStatistics.getMap());

    arrayStatistics = new ArrayStatistics(tenElementArray);
    shuffleStatistic = new ArrayStatistics(arrayStatistics.getShuffle());
    assertEquals(shuffleStatistic.getMap(), arrayStatistics.getMap());

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    shuffleStatistic = new ArrayStatistics(arrayStatistics.getShuffle());
    assertEquals(shuffleStatistic.getMap(), arrayStatistics.getMap());
  }

  @Test
  void getSample() {
    ArrayStatistics arrayStatistics = new ArrayStatistics(emptyArray);
    ArrayStatistics sampleStatistic = new ArrayStatistics(arrayStatistics.getSample(100));
    assertNull(arrayStatistics.getSample(100));

    arrayStatistics = new ArrayStatistics(oneElementArray);
    sampleStatistic = new ArrayStatistics(arrayStatistics.getSample(100));
    assertEquals(sampleStatistic.getMap().keySet(), arrayStatistics.getMap().keySet());

    arrayStatistics = new ArrayStatistics(threeElementArray);
    sampleStatistic = new ArrayStatistics(arrayStatistics.getSample(100));
    assertEquals(sampleStatistic.getMap().keySet(), arrayStatistics.getMap().keySet());
    assertNotEquals(0, Arrays.compare(threeElementArray, sampleStatistic.getInputArray()));

    arrayStatistics = new ArrayStatistics(tenElementArray);
    sampleStatistic = new ArrayStatistics(arrayStatistics.getSample(100));
    assertEquals(sampleStatistic.getMap().keySet(), arrayStatistics.getMap().keySet());
    assertNotEquals(0, Arrays.compare(tenElementArray, sampleStatistic.getInputArray()));

    arrayStatistics = new ArrayStatistics(hundredThousandElementArray);
    sampleStatistic = new ArrayStatistics(arrayStatistics.getSample(100));
    assertEquals(sampleStatistic.getMap().keySet(), arrayStatistics.getMap().keySet());
    assertNotEquals(0, Arrays.compare(hundredThousandElementArray, sampleStatistic.getInputArray()));
  }
}