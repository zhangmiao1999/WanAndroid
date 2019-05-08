package com.example.wanandroid.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class ColorUtil {
    public static String getRandomColor() {
        List<String> colorList = new ArrayList<String>();
        colorList.add("#8D4674");

        colorList.add("#395370");

        colorList.add("#9E65A9");

        colorList.add("#10334C");

        colorList.add("#087277");

        colorList.add("#A4645A");

        colorList.add("#6AA742");

        colorList.add("#317D31");

        colorList.add("#4D9713");

        colorList.add("#246D43");

        colorList.add("#1A1C0C");

        colorList.add("#2E9862");

        colorList.add("#75272B");

        colorList.add("#6E7599");

        colorList.add("#56673B");

        colorList.add("#653A4D");

        colorList.add("#485A29");

        colorList.add("#739962");

        colorList.add("#824480");

        colorList.add("#69862A");

        colorList.add("#176C8C");

        colorList.add("#3C5291");

        colorList.add("#6F6334");

        colorList.add("#9A4871");

        colorList.add("#6C3A44");

        colorList.add("#266693");

        colorList.add("#686064");

        colorList.add("#9380B1");

        colorList.add("#69862A");

        return colorList.get((int) (Math.random() * colorList.size()));
    }
}
