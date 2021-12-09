package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/2
 */

/**
 * date formal
 */
@Component
public class DateUtil {
    // format date (yyyy-MM-dd HH:mm:ss)
    public String Time(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        return time;
    }

    // format date (yyyy-MM-dd)
    public String Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(date);
        return time;
    }


//    public static void main(String[] args) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = df.format(date);
//    }
//    private Date timeStamp() {
//        System.out.println(df);
//    }
}
