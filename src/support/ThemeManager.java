package support;

import java.awt.*;

import static java.lang.Integer.decode;

public class ThemeManager {

    public static Color getBackgroundColor(String id){
        String [] background_color = new String[]{"#FFFFFF", "#FFFFFF", "#FFFFFF", "#000000", "#000000"};
        return new Color(decode(background_color[getSQL(id)]));
    }
    public static String getCardBackImgURL(String id){
        return "src/asset/theme/card_back_" + getSQL(id)+".png";
    }
    private static int getSQL(String id){
        SQLiteManager b = new SQLiteManager("","","");
        return b.getSkin(id);
    }
}
