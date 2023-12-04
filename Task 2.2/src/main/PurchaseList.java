import java.util.Arrays;

public class PurchaseList {

  private int[][] purchaseTable;
  private double[] purchaseCost;
  private double[] shippingCost;

  private PurchaseList(int[][] purchaseTable, double[] purchaseCost, double[] shippingCost) {
    setPurchaseTable(purchaseTable);
    setPurchaseCost(purchaseCost);
    setShippingCost(shippingCost);
  }

  public static PurchaseList defaultAlgorithm(GasolineSupplies gasolineSupplies, int[] numberPurchases){
    for (double[] ints: gasolineSupplies.getShippingCosts()) {
      if(ints.length != numberPurchases.length){
        System.err.println("Список закупки не совпадает с количеством заправок");
        return null;
      }
    }

    int numberSuplier = gasolineSupplies.getShippingCosts().length;
    int numberGasStation = numberPurchases.length;
    int[][] purchaseTable = new int[numberSuplier][numberGasStation]; // массив покупок

    double[][] shippingCosts = gasolineSupplies.getShippingCosts(); // цены доставки
    double[] purchasePrice = gasolineSupplies.getPurchasePrice(); // цены топлива
    int[] nowPurchaseVolume = gasolineSupplies.getMaximumPurchaseVolume().clone(); // количество топлива после планируемых покупок
    int[] nowNumberPurchases = numberPurchases.clone(); // количество закупок после планируемых покупок

    while (Arrays.stream(nowNumberPurchases).max().getAsInt() > 0){

      double minPrice = Integer.MAX_VALUE;
      int indexMinPrice = -1;
      for (int i = 0; i < purchasePrice.length; i++){
        if(nowPurchaseVolume[i] != 0 && minPrice > purchasePrice[i]){
          minPrice = purchasePrice[i];
          indexMinPrice = i;
        }
      }
      double minShippingCost = Integer.MAX_VALUE;
      int indexMinShippingCost= -1;
      for (int i = 0; i < shippingCosts[indexMinPrice].length; i++) {
        if(nowNumberPurchases[i] != 0 && minShippingCost > shippingCosts[indexMinPrice][i]){
          minShippingCost = shippingCosts[indexMinPrice][i];
          indexMinShippingCost = i;
        }
      }

      // Сколько в итоге сможем закупить у этого поставщика
      int purchaseVolume = Math.min(nowPurchaseVolume[indexMinPrice], nowNumberPurchases[indexMinShippingCost]);

      purchaseTable[indexMinPrice][indexMinShippingCost] = purchaseVolume; // Закупаем
      nowPurchaseVolume[indexMinPrice] -= purchaseVolume; // Уменьшаем кол-во у поставщика
      nowNumberPurchases[indexMinShippingCost] -= purchaseVolume;
    }

    double[] purchaseCost = calculatePurchaseCost(gasolineSupplies, purchaseTable);
    double[] shippingCost = calculateShippingCost(gasolineSupplies, purchaseTable);

    return new PurchaseList(purchaseTable, purchaseCost, shippingCost);
  }

  public static PurchaseList myAlgorithm(GasolineSupplies gasolineSupplies, int[] numberPurchases){
    for (double[] ints: gasolineSupplies.getShippingCosts()) {
      if(ints.length != numberPurchases.length){
        System.err.println("Список закупки не совпадает с количеством заправок");
        return null;
      }
    }

    int numberSuplier = gasolineSupplies.getShippingCosts().length;
    int numberGasStation = numberPurchases.length;
    int[][] purchaseTable = new int[numberSuplier][numberGasStation]; // массив покупок

    double[][] shippingCosts = gasolineSupplies.getShippingCosts(); // цены доставки
    double[] purchasePrice = gasolineSupplies.getPurchasePrice(); // цены топлива
    int[] nowPurchaseVolume = gasolineSupplies.getMaximumPurchaseVolume().clone(); // количество топлива после планируемых покупок
    int[] nowNumberPurchases = numberPurchases.clone(); // количество закупок после планируемых покупок

    while (Arrays.stream(nowNumberPurchases).max().getAsInt() > 0){

      double maximumPurchaseVolume= -1;
      int indexMaximumPurchaseVolume = -1; // Определяем где нужно больше закупить топлива
      for (int i = 0; i < nowNumberPurchases.length; i++){
        if(maximumPurchaseVolume < nowNumberPurchases[i]){
          maximumPurchaseVolume = nowNumberPurchases[i];
          indexMaximumPurchaseVolume = i;
        }
      }

      int purchaseVolume;
      double[] fullFuelPrices = new double[numberSuplier];
      for (int i = 0; i < fullFuelPrices.length; i++) {
        // Сколько сможем закупить у этого поставщика
        purchaseVolume = Math.min(nowPurchaseVolume[i], nowNumberPurchases[indexMaximumPurchaseVolume]);
        // Полная стоимость ед. топлива = (purchaseVolume * purchasePrice + shippingCosts) / purchaseVolume
        // Полная стоимость ед. топлива = purchasePrice + shippingCosts / purchaseVolume
        fullFuelPrices[i] = purchasePrice[i] + shippingCosts[i][indexMaximumPurchaseVolume] / purchaseVolume;
      }

      double minPrice = Integer.MAX_VALUE;
      int indexMinPrice = -1;
      for (int i = 0; i < fullFuelPrices.length; i++){
        if(nowPurchaseVolume[i] != 0 && minPrice > fullFuelPrices[i]){
          minPrice = fullFuelPrices[i];
          indexMinPrice = i;
        }
      }

      // Сколько в итоге сможем закупить у этого поставщика
      purchaseVolume = Math.min(nowPurchaseVolume[indexMinPrice], nowNumberPurchases[indexMaximumPurchaseVolume]);

      purchaseTable[indexMinPrice][indexMaximumPurchaseVolume] = purchaseVolume; // Закупаем
      nowPurchaseVolume[indexMinPrice] -= purchaseVolume; // Уменьшаем кол-во у поставщика
      nowNumberPurchases[indexMaximumPurchaseVolume] -= purchaseVolume;

    }

    double[] purchaseCost = calculatePurchaseCost(gasolineSupplies, purchaseTable);
    double[] shippingCost = calculateShippingCost(gasolineSupplies, purchaseTable);

    return new PurchaseList(purchaseTable, purchaseCost, shippingCost);
  }

  public int[][] getPurchaseTable() {
    return purchaseTable;
  }

  private void setPurchaseTable(int[][] purchaseTable) {
    if(Main.getMinElement(purchaseTable) < 0){
      this.purchaseTable = null;
      System.err.println("Нельзя закупить меньше 0");
    }
    this.purchaseTable = purchaseTable;
  }

  public double[] getPurchaseCost() {
    return purchaseCost;
  }

  public void setPurchaseCost(double[] purchaseCost) {
    this.purchaseCost = purchaseCost;
  }

  public double[] getShippingCost() {
    return shippingCost;
  }

  public void setShippingCost(double[] shippingCost) {
    this.shippingCost = shippingCost;
  }

  private static double[] calculatePurchaseCost(GasolineSupplies gasolineSupplies, int[][] purchaseTable){

    double[] purchasePrice = gasolineSupplies.getPurchasePrice();

    double[] purchaseCost = new double[purchaseTable.length];

    double totalPurchaseVolume; // total purchase volume
    for (int i = 0; i < purchaseCost.length; i++) {
      totalPurchaseVolume = 0.0;
      for (int j = 0; j < purchaseTable[i].length; j++) {
        totalPurchaseVolume += purchaseTable[i][j];
      }
      purchaseCost[i] = Math.round(totalPurchaseVolume * purchasePrice[i] * 100) / 100.0;
    }
    return purchaseCost;
  }

  private static double[] calculateShippingCost(GasolineSupplies gasolineSupplies, int[][] purchaseTable){

    double[][] suppliesShippingCosts = gasolineSupplies.getShippingCosts();

    double[] shippingCost = new double[purchaseTable.length];

    double totalShippingCost; // total purchase volume
    for (int i = 0; i < shippingCost.length; i++) {
      totalShippingCost = 0.0;
      for (int j = 0; j < purchaseTable[i].length; j++) {
        if(purchaseTable[i][j] > 0 ){
          totalShippingCost += suppliesShippingCosts[i][j];
        }
      }
      shippingCost[i] = totalShippingCost;
    }
    return shippingCost;
  }

  public double calculateTotal(){
    double total = 0;

    for (int i = 0; i < getPurchaseCost().length; i++) {
      total += getPurchaseCost()[i] + getShippingCost()[i];
    }
    return total;
  }

  public void printTable() {
    double[] purchaseCost = getPurchaseCost();
    double[] shippingCost = getShippingCost();

    System.out.println("##############################################################################################");
    System.out.println("| Поставщик |  АЗС А  |  АЗС Б  |  АЗС В  |  АЗС Г  | Стоимость закупки | С учетом доставки |");
    System.out.println("|___________|_________|_________|_________|_________|___________________|___________________|");
    double total = 0;
    for (int i = 0; i < this.getPurchaseTable().length; i++) {
      total += purchaseCost[i] + shippingCost[i];
      int[] ints = this.getPurchaseTable()[i];
      System.out.printf("|%13s", (i+1) + "  |\t");
      System.out.printf("%8s", ints[0] + " |\t");
      System.out.printf("%10s", ints[1] + " |\t");
      System.out.printf("%8s", ints[2] + " |\t");
      System.out.printf("%10s", ints[3] + " |\t");
      System.out.printf("%15.2f", purchaseCost[i]);
      System.out.print(" |\t");
      System.out.printf("%15.2f", (purchaseCost[i] + shippingCost[i]));
      System.out.print(" |\n");
      System.out.println("|___________|_________|_________|_________|_________|___________________|___________________|");
    }
    System.out.print("ИТОГО: ");
    System.out.printf("%.2f\n", total);
  }
}
