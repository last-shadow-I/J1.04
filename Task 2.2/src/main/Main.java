
import java.util.Scanner;

public class Main {

  public static double getMinElement(double[][] doubles){
    double minElement = Integer.MAX_VALUE;
    for (double[] anDouble : doubles) {
      for (double i : anDouble) {
        if (minElement > i) {
          minElement = i;
        }
      }
    }
    return minElement;
  }

  public static int getMinElement(int[][] ints){
    int minElement = Integer.MAX_VALUE;
    for (int[] anInt : ints) {
      for (int i : anInt) {
        if (minElement > i) {
          minElement = i;
        }
      }
    }
    return minElement;
  }

  public static void main(String[] args) {

    double[][] shippingCosts = new double[6][4];

    shippingCosts[0] = new double[] {803, 952, 997, 931};
    shippingCosts[1] = new double[] {967, 1012, 848, 1200};
    shippingCosts[2] = new double[] {825, 945, 777, 848};
    shippingCosts[3] = new double[] {1024, 1800, 931, 999};
    shippingCosts[4] = new double[] {754, 817, 531, 628}; // 531
    shippingCosts[5] = new double[] {911, 668, 865, 1526};

    int[] Maximum_Purchase_Volume = new int[] {600, 120, 360, 250, 700, 390}; // 420

    double[] Purchase_Prise = new double[] {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

    GasolineSupplies gasolineSupplies = new GasolineSupplies(Maximum_Purchase_Volume, Purchase_Prise, shippingCosts);

    gasolineSupplies.printTable();

    int[] numberPurchases = new int[4]; // {400, 550, 280, 310};

    Scanner sc = new Scanner(System.in);

    int i = 0;
    System.out.println("\nВведите число закупок для АЗС:");
    while(i < 4) {
      if(sc.hasNextInt()){
        int quantity = sc.nextInt();
        if(quantity > 0){
          numberPurchases[i] = quantity;
          i++;
          System.out.println("Введите число закупок для АЗС:");
        } else {
          System.err.println("Неверный ввод: число < 0");
        }
      } else {
        sc.next();
        System.err.println("Неверный ввод: число не целое");
      }
    }

    PurchaseList purchaseList = PurchaseList.defaultAlgorithm(gasolineSupplies, numberPurchases);

    System.out.println("\nПолучившийся лист закупок: ");
    assert purchaseList != null;

    purchaseList.printTable();

    purchaseList = PurchaseList.myAlgorithm(gasolineSupplies, numberPurchases);

    System.out.println("\nПолучившийся лист закупок по моему алгоритму: ");
    assert purchaseList != null;

    purchaseList.printTable();


  }
}