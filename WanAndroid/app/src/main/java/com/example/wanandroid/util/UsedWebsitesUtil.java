package com.example.wanandroid.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class UsedWebsitesUtil {
    private static class CircleTextImageUtilHolder {
        private static final UsedWebsitesUtil INSTANCE = new UsedWebsitesUtil();
    }
    private UsedWebsitesUtil(){}
    public static final UsedWebsitesUtil getInstance() {
        return UsedWebsitesUtil.CircleTextImageUtilHolder.INSTANCE;
    }

    /**
     * Get the random color.
     * @return
     */
    public static String getRandomColor() {
        List<String> colorList = new ArrayList<String>();
        colorList.add("#E78F8F");
        colorList.add("#F6BC7E");
        colorList.add("#90C5F0");
        colorList.add("#67CCB7");
        colorList.add("#F88F55");
        colorList.add("#91CED5");
        colorList.add("#C0AFD0");

/*        colorList.add("#A75F6E");
        colorList.add("#423775");
        colorList.add("#905773");
        colorList.add("#873F13");
        colorList.add("#aed581");
        colorList.add("#fdd835");
        colorList.add("#f2a600");
        colorList.add("#ff8a65");
        colorList.add("#f48fb1");
        colorList.add("#7986cb");
        colorList.add("#FFFFE0");*/
/*        colorList.add("#ADD8E6");
        colorList.add("#DEB887");
        colorList.add("#C0C0C0");
        colorList.add("#AFEEEE");
        colorList.add("#F0FFF0");
        colorList.add("#FF69B4");
        colorList.add("#FFE4B5");
        colorList.add("#FFE4E1");
        colorList.add("#FFEBCD");
        colorList.add("#FFEFD5");
        colorList.add("#FFF0F5");
        colorList.add("#FFF5EE");
        colorList.add("#FFF8DC");
        colorList.add("#FFFACD");*/
        return colorList.get((int)(Math.random() * colorList.size()));
    }


    /**
     * Interception of the first string of characters.
     * @param str
     * @return
     */
    public static String subFirstCharacter(String str) {
        if (Character.isLetter(str.charAt(0))) {
            return Character.toUpperCase(str.charAt(0))+"";
        } else {
            return str.charAt(0) +"";
        }
    }
}
