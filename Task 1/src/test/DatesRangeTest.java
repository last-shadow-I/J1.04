import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatesRangeTest {

  @Test
  void getDatesRangeTest() {
    String[] strings = DatesRange.getDatesRange("2023-11-12", "2023-11-11");

    assertEquals(0, strings.length);
    assertEquals(Arrays.toString(new String[0]), Arrays.toString(strings));

    strings = DatesRange.getDatesRange("2023-11-11", "2023-11-12");

    assertEquals(2, strings.length);
    assertEquals("[2023-11-11, 2023-11-12]", Arrays.toString(strings));

    strings = DatesRange.getDatesRange("2023-02-28", "2023-02-28");
    assertEquals(1, strings.length);
    assertEquals("[2023-02-28]", Arrays.toString(strings));

    strings = DatesRange.getDatesRange("2020-05-28", "2020-06-02");
    assertEquals(6, strings.length);
    assertEquals("[2020-05-28, 2020-05-29, 2020-05-30, 2020-05-31," +
            " 2020-06-01, 2020-06-02]", Arrays.toString(strings));

    strings = DatesRange.getDatesRange("1900-12-30", "1901-01-05");
    assertEquals(7, strings.length);
    assertEquals("[1900-12-30, 1900-12-31, 1901-01-01, 1901-01-02," +
            " 1901-01-03, 1901-01-04, 1901-01-05]", Arrays.toString(strings));
  }
}