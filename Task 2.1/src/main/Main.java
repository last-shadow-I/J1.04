import java.util.Arrays;
import java.util.Random;

public class Main {
  public static void main(String[] args) {

    int[] ints = new int[0];
    ArrayStatistics arrayStatistics = new ArrayStatistics(ints);

    System.out.println("Количество чисел в массиве: " + arrayStatistics.getMap());

    System.out.println("Мода: " + arrayStatistics.getMode());

    System.out.println("Медиана: " + arrayStatistics.getMedian());

    System.out.println("Мат. ожидание: " + arrayStatistics.getAverage());

    System.out.println("Дисперсия: " + arrayStatistics.getVariance());

    System.out.println("Среднее геометрическое: " + arrayStatistics.getGeometricMean());

    System.out.println("Shuffle: " + Arrays.toString(arrayStatistics.getShuffle()));

    System.out.println("Sample: " + Arrays.toString(arrayStatistics.getSample(12)));

    System.out.println(Arrays.toString(arrayStatistics.getInputArray()));

    System.out.println();

    ints = new int[] {9};
    arrayStatistics = new ArrayStatistics(ints);

    System.out.println("Количество чисел в массиве: " + arrayStatistics.getMap());

    System.out.println("Мода: " + arrayStatistics.getMode());

    System.out.println("Медиана: " + arrayStatistics.getMedian());

    System.out.println("Мат. ожидание: " + arrayStatistics.getAverage());

    System.out.println("Дисперсия: " + arrayStatistics.getVariance());

    System.out.println("Среднее геометрическое: " + arrayStatistics.getGeometricMean());

    System.out.println("Shuffle: " + Arrays.toString(arrayStatistics.getShuffle()));

    System.out.println("Sample: " + Arrays.toString(arrayStatistics.getSample(12)));

    System.out.println(Arrays.toString(arrayStatistics.getInputArray()));

    System.out.println();

    ints = new int[] {9, 5, 3};
    arrayStatistics = new ArrayStatistics(ints);

    System.out.println("Количество чисел в массиве: " + arrayStatistics.getMap());

    System.out.println("Мода: " + arrayStatistics.getMode());

    System.out.println("Медиана: " + arrayStatistics.getMedian());

    System.out.println("Мат. ожидание: " + arrayStatistics.getAverage());

    System.out.println("Дисперсия: " + arrayStatistics.getVariance());

    System.out.println("Среднее геометрическое: " + arrayStatistics.getGeometricMean());

    System.out.println("Shuffle: " + Arrays.toString(arrayStatistics.getShuffle()));

    System.out.println("Sample: " + Arrays.toString(arrayStatistics.getSample(12)));

    System.out.println(Arrays.toString(arrayStatistics.getInputArray()));

    System.out.println();

    ints = new int[] {9, 9, 1, 2, 3, 4, 7, 8, 9, 4};
    arrayStatistics = new ArrayStatistics(ints);

    System.out.println("Количество чисел в массиве: " + arrayStatistics.getMap());

    System.out.println("Мода: " + arrayStatistics.getMode());

    System.out.println("Медиана: " + arrayStatistics.getMedian());

    System.out.println("Мат. ожидание: " + arrayStatistics.getAverage());

    System.out.println("Дисперсия: " + arrayStatistics.getVariance());

    System.out.println("Среднее геометрическое: " + arrayStatistics.getGeometricMean());

    System.out.println("Shuffle: " + Arrays.toString(arrayStatistics.getShuffle()));

    System.out.println("Sample: " + Arrays.toString(arrayStatistics.getSample(12)));

    System.out.println();

    ints = new int[1_000_000];
    Random generator = new Random(42);
    for (int i = 0; i < ints.length; i++) {
      ints[i] = generator.nextInt(-5, 6);
    }

    arrayStatistics = new ArrayStatistics(ints);

    System.out.println("Количество чисел в массиве: " + arrayStatistics.getMap());

    System.out.println("Мода: " + arrayStatistics.getMode());

    System.out.println("Медиана: " + arrayStatistics.getMedian());

    System.out.println("Мат. ожидание: " + arrayStatistics.getAverage());

    System.out.println("Дисперсия: " + arrayStatistics.getVariance());

    System.out.println("Среднее геометрическое: " + arrayStatistics.getGeometricMean());

    int[] shuffleShort = new int[50];
    int[] shuffle = arrayStatistics.getShuffle();
    System.arraycopy(shuffle, 0, shuffleShort, 0, 50);
    System.out.println("Shuffle[0:50]: " + Arrays.toString(shuffleShort));
    ArrayStatistics shuffleStatistic = new ArrayStatistics(shuffle);
    System.out.println("Количество чисел в Shuffle: " + shuffleStatistic.getMap());

    int[] sampleShort = new int[50];
    int[] sample = arrayStatistics.getSample(10000);
    System.arraycopy(sample, 0, sampleShort, 0, 50);
    System.out.println("Sample[0:50]: " + Arrays.toString(sampleShort));
    ArrayStatistics sampleStatistic = new ArrayStatistics(sample);
    System.out.println("Количество чисел в Sample[0:50]: " + sampleStatistic.getMap());
  }
}