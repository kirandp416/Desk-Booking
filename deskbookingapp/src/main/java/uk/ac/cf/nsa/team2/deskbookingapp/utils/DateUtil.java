package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/2
 */
@Component
public class DateUtil {

    public String Time(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        return time;
    }

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
