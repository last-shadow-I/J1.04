import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatesRange {

  public static String[] getDatesRange(String startDate, String endDate){
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate startLocalDate = LocalDate.parse(startDate, dateTimeFormatter);
    LocalDate endLocalDate = LocalDate.parse(endDate, dateTimeFormatter);

    int countDays = (int) ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

    if(countDays < 0){
      System.err.println(startDate + " раньше " + endDate);
      return new String[0];
    } else {
      String[] returnStrings = new String[countDays + 1];
      LocalDate localDate = startLocalDate;
      for (int i = 0; i < countDays + 1; i++) {
        returnStrings[i] = localDate.toString();
        localDate = localDate.plusDays(1);
      }
      return returnStrings;
    }


  }
}
