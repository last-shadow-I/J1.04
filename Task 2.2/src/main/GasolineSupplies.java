
import static java.util.Arrays.stream;

public class GasolineSupplies {

  private final int[] MAXIMUM_PURCHASE_VOLUME;
  private final double[] PURCHASE_PRICE;
  private final double[][] SHIPPING_COSTS;

  public GasolineSupplies(int[] MAXIMUM_PURCHASE_VOLUME, double[] PURCHASE_PRICE, double[][] SHIPPING_COSTS) {
    if(stream(MAXIMUM_PURCHASE_VOLUME).min().getAsInt() < 0 ||
            stream(PURCHASE_PRICE).min().getAsDouble() < 0 ||
            Main.getMinElement(SHIPPING_COSTS) < 0){
      System.err.println("Значения < 0");
      this.MAXIMUM_PURCHASE_VOLUME = null;
      this.PURCHASE_PRICE = null;
      this.SHIPPING_COSTS = null;
    } else if (MAXIMUM_PURCHASE_VOLUME.length != PURCHASE_PRICE.length || MAXIMUM_PURCHASE_VOLUME.length != SHIPPING_COSTS.length){
      System.err.println("Данные от поставщиков не совпадают");
      this.MAXIMUM_PURCHASE_VOLUME = null;
      this.PURCHASE_PRICE = null;
      this.SHIPPING_COSTS = null;
    } else {
      this.MAXIMUM_PURCHASE_VOLUME = MAXIMUM_PURCHASE_VOLUME;
      this.PURCHASE_PRICE = PURCHASE_PRICE;
      this.SHIPPING_COSTS = SHIPPING_COSTS;
    }
  }

  public int[] getMaximumPurchaseVolume() {
    return MAXIMUM_PURCHASE_VOLUME;
  }

  public double[] getPurchasePrice() {
    return PURCHASE_PRICE;
  }

  public double[][] getShippingCosts() {
    return SHIPPING_COSTS;
  }

  public void printTable() {
    if(this.MAXIMUM_PURCHASE_VOLUME != null && this.PURCHASE_PRICE != null && this.SHIPPING_COSTS != null){
      System.out.println("##########################################################################################");
      System.out.println("| Поставщик | Макс. объём | Цена закупки |               Стоимость закупки               |");
      System.out.println("|___________|_____________|______________|     А     |     Б     |     В     |     Г     |");
      for (int i = 0; i < this.getMaximumPurchaseVolume().length; i++) {
        double[] ints = this.getShippingCosts()[i];
        System.out.printf("|%13s", (i+1) + "  |\t");
        System.out.printf("%12s", this.getMaximumPurchaseVolume()[i] + "  |\t");
        System.out.printf("%15s", this.getPurchasePrice()[i] + "  |\t");
        System.out.printf("%11s", ints[0] + " |\t");
        System.out.printf("%11s", ints[1] + " |\t");
        System.out.printf("%11s", ints[2] + " |\t");
        System.out.printf("%10s", ints[3] + " |");
        System.out.println("\n|___________|_____________|______________|___________|___________|___________|___________|");
      }
    } else {
      System.err.println("Неправильно заданы данные");
    }

  }
}
