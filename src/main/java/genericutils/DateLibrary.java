package genericutils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLibrary {

    public static String getCurrentDateDDMMYYYY() {
        return new SimpleDateFormat("ddMMyyyy").format(new Date());
    }

    public static String getCurrentDateDDMMYYYYHHMMSS() {
        return new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
    }
}
