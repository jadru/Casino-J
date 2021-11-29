package support;

import java.awt.*;

import static java.lang.Integer.decode;

public class ThemeManager {
    private static int skin = 0;
    private String [] background_color = new String[]{"#FFFFFF", "#FFFFFF", "#FFFFFF", "#000000", "#000000"};

    public ThemeManager(String id){
        SQLiteManager b = new SQLiteManager("","","");
        skin = b.getSkin(id);
    }

    public Color getBackgroundColor(){
        return new Color(decode(background_color[skin]));
    }
    public static String getCardBackImgURL(){
        return "asset/card_back_" + skin;
    }
}
