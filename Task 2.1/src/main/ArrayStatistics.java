import java.util.*;

import static java.util.Collections.max;


public class ArrayStatistics {

  private int[] inputArray;

  public ArrayStatistics(int[] inputArray) {
    this.inputArray = inputArray;
  }

  public Map<Integer,Integer> getMap(){
    Map<Integer, Integer> valueByCounter = new HashMap<>();

    for (int value : inputArray) {
      Integer counter = valueByCounter.getOrDefault(value, 0);
      counter++;
      valueByCounter.put(value, counter);
    }
    return valueByCounter;
  }

  public String getMode(){
    if (inputArray.length == 0){
      return null;
    }
    Map<Integer, Integer> valueByCounter = getMap();

    int maxValue = max(valueByCounter.values());

    List<Integer> list = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : valueByCounter.entrySet()) {
      if (entry.getValue() == maxValue) {
        list.add(entry.getKey());
      }
    }
    return list.toString();
  }

  public Object getMedian(){
    if (inputArray.length == 0){
      return null;
    }
    int[] array = inputArray.clone();

    Arrays.sort(array);
    return array[array.length/2];
  }

  public double getAverage(){
    double sum = 0.0;
    for (int i: inputArray) {
      sum += i;
    }
    return sum/inputArray.length;
  }

  public double getVariance(){
    double sum = 0.0;
    double average = getAverage();
    for (int i: inputArray) {
      sum += Math.pow(i - average, 2);
    }
    return sum/inputArray.length;
  }

  public double getGeometricMean(){
    double multiplication = 1.0;
    for (int i: inputArray) {
      multiplication *= i;
    }
    return Math.pow(multiplication, 1.0/inputArray.length);
  }

  public int[] getShuffle(){
    int[] shuffleArr = inputArray.clone();
    Random generator = new Random();

    // алгоритм Фишера-Йетса
    for (int i = shuffleArr.length - 1; i > 0; i--) {
      int index = generator.nextInt(i + 1);
      int temp = shuffleArr[index];
      shuffleArr[index] = shuffleArr[i];
      shuffleArr[i] = temp;
    }
    return shuffleArr;
  }

  public int[] getSample(int length){
    if (inputArray.length == 0){
      return null;
    }
    int[] sampleArr = new int[length];

    Random generator = new Random();

    for (int i = 0; i < length; i++) {
      int index = generator.nextInt(0, inputArray.length);
      sampleArr[i] = inputArray[index];
    }
    return sampleArr;
  }

  public int[] getInputArray() {
    return inputArray;
  }

  public void setInputArray(int[] inputArray) {
    this.inputArray = inputArray;
  }
}
