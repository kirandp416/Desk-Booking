package uk.ac.cf.nsa.team2.deskbookingapp.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/5
 */
public class StringUtil {
    /**
     * line to camel case
     * because in the database, the name in fields always use camel case, but Java does not.
     */
    public static String lineToHump(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        // tries to find the occurrence of a regex pattern within a given string
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
