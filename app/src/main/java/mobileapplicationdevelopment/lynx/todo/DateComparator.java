package mobileapplicationdevelopment.lynx.todo;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateComparator {


    private String todoDate;
    private boolean isOld = false;

    public DateComparator(String todoDate) {
        this.todoDate = todoDate;
    }

    protected boolean oldToDo() {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdf.parse(todoDate);
            Date d2 = sdf.parse(sdf.format(new Date()));

            if (d1.before(d2)) {
                return true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isOld;
    }

}
