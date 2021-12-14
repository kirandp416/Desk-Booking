package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/2
 */

/**
 *  white list
 */
public class XssFilterUtil {

    private static final Whitelist whitelist = Whitelist.basicWithImages();

    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    static {
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
}
