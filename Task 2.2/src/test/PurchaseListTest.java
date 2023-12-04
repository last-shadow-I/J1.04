import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseListTest {

  double[][] shippingCosts = new double[6][4];


  GasolineSupplies gasolineSupplies;
  int[] numberPurchases;

  {
    shippingCosts[0] = new double[] {803, 952, 997, 931};
    shippingCosts[1] = new double[] {967, 1012, 848, 1200};
    shippingCosts[2] = new double[] {825, 945, 777, 848};
    shippingCosts[3] = new double[] {1024, 1800, 931, 999};
    shippingCosts[4] = new double[] {754, 817, 531, 628}; // 531
    shippingCosts[5] = new double[] {911, 668, 865, 1526};

    int[] Maximum_Purchase_Volume = new int[] {600, 120, 360, 250, 700, 390};

    double[] Purchase_Prise = new double[] {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

    gasolineSupplies = new GasolineSupplies(Maximum_Purchase_Volume, Purchase_Prise, shippingCosts);

    numberPurchases = new int[] {400, 550, 280, 310};
  }


  @Test
  void defaultAlgorithmTest(){

    PurchaseList purchaseList = PurchaseList.defaultAlgorithm(gasolineSupplies, new int[] {300, 200});

    assertNull(purchaseList); // проверка при не совпадении кол-ва закупок

    purchaseList = PurchaseList.defaultAlgorithm(gasolineSupplies, numberPurchases);

    assertNotNull(purchaseList);

    int[] sum = new int[4];
    for (int i = 0; i < purchaseList.getPurchaseTable().length; i++){
      for (int j = 0; j < purchaseList.getPurchaseTable()[i].length; j++){
        sum[j] += purchaseList.getPurchaseTable()[i][j];
      }
    }

    assertArrayEquals(numberPurchases, sum);

    purchaseList.printTable();
  }

  @Test
  void myAlgorithmTest(){

    PurchaseList purchaseList = PurchaseList.myAlgorithm(gasolineSupplies, new int[] {300, 200});

    assertNull(purchaseList); // проверка при не совпадении кол-ва закупок

    purchaseList = PurchaseList.myAlgorithm(gasolineSupplies, numberPurchases);

    assertNotNull(purchaseList);

    int[] sum = new int[4];
    for (int i = 0; i < purchaseList.getPurchaseTable().length; i++){
      for (int j = 0; j < purchaseList.getPurchaseTable()[i].length; j++){
        sum[j] += purchaseList.getPurchaseTable()[i][j];
      }
    }

    assertArrayEquals(numberPurchases, sum);

    purchaseList.printTable();
  }

  @Test
  void bestAlgorithmTest1(){
    // изменил кол-во топлива там где цена 6.1, и уменьшил цену доставки
    // мой алгоритм работает гораздо лучше так как считает полную цену
    shippingCosts[0] = new double[] {803, 952, 997, 931};
    shippingCosts[1] = new double[] {967, 1012, 848, 1200};
    shippingCosts[2] = new double[] {500, 500, 500, 500};
    shippingCosts[3] = new double[] {1024, 1800, 931, 999};
    shippingCosts[4] = new double[] {754, 817, 531, 628}; // 531
    shippingCosts[5] = new double[] {911, 668, 865, 1526};

    int[] Maximum_Purchase_Volume = new int[] {600, 120, 1000, 250, 700, 390}; // изменил 400 на 120

    double[] Purchase_Prise = new double[] {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

    gasolineSupplies = new GasolineSupplies(Maximum_Purchase_Volume, Purchase_Prise, shippingCosts);

    numberPurchases = new int[] {400, 550, 280, 310};

    PurchaseList defaultPurchaseList = PurchaseList.defaultAlgorithm(gasolineSupplies, numberPurchases);

    PurchaseList myPurchaseList = PurchaseList.myAlgorithm(gasolineSupplies, numberPurchases);

    assert defaultPurchaseList != null;
    defaultPurchaseList.printTable();

    assert myPurchaseList != null;
    myPurchaseList.printTable();

    assertTrue(defaultPurchaseList.calculateTotal() > myPurchaseList.calculateTotal());
  }

  @Test
  void bestAlgorithmTest2(){
    // Увеличил кол-во дешёвого топлива и потребовал большей доставки
    shippingCosts[0] = new double[] {803, 952, 997, 931};
    shippingCosts[1] = new double[] {967, 1012, 848, 1200};
    shippingCosts[2] = new double[] {825, 945, 777, 848};
    shippingCosts[3] = new double[] {1024, 1800, 931, 999};
    shippingCosts[4] = new double[] {754, 817, 531, 628}; // 531
    shippingCosts[5] = new double[] {911, 668, 865, 1526};

    int[] Maximum_Purchase_Volume = new int[] {600, 120, 360, 900, 700, 390}; // изменил 250 на 900

    double[] Purchase_Prise = new double[] {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

    gasolineSupplies = new GasolineSupplies(Maximum_Purchase_Volume, Purchase_Prise, shippingCosts);

    numberPurchases = new int[] {400, 550, 600, 310};

    PurchaseList defaultPurchaseList = PurchaseList.defaultAlgorithm(gasolineSupplies, numberPurchases);

    PurchaseList myPurchaseList = PurchaseList.myAlgorithm(gasolineSupplies, numberPurchases);

    assert defaultPurchaseList != null;
    defaultPurchaseList.printTable();

    assert myPurchaseList != null;
    myPurchaseList.printTable();

    assertTrue(defaultPurchaseList.calculateTotal() > myPurchaseList.calculateTotal());
  }

  @Test
  void bestAlgorithmTest3(){
    // Потребовал меньше доставки
    //
    shippingCosts[0] = new double[] {803, 952, 997, 931};
    shippingCosts[1] = new double[] {967, 1012, 848, 1200};
    shippingCosts[2] = new double[] {825, 945, 777, 848};
    shippingCosts[3] = new double[] {1024, 1800, 931, 999};
    shippingCosts[4] = new double[] {754, 817, 531, 628}; // 531
    shippingCosts[5] = new double[] {911, 668, 865, 1526};

    int[] Maximum_Purchase_Volume = new int[] {600, 120, 360, 250, 700, 390};

    double[] Purchase_Prise = new double[] {5.2, 3.95, 6.1, 3.8, 3.9, 5.6}; // заменил 4.5 и 6.4 на 3.9

    gasolineSupplies = new GasolineSupplies(Maximum_Purchase_Volume, Purchase_Prise, shippingCosts);

    numberPurchases = new int[] {10, 20, 30, 40};

    PurchaseList defaultPurchaseList = PurchaseList.defaultAlgorithm(gasolineSupplies, numberPurchases);

    PurchaseList myPurchaseList = PurchaseList.myAlgorithm(gasolineSupplies, numberPurchases);

    assert defaultPurchaseList != null;
    defaultPurchaseList.printTable();

    assert myPurchaseList != null;
    myPurchaseList.printTable();

    assertTrue(defaultPurchaseList.calculateTotal() > myPurchaseList.calculateTotal());
  }
}