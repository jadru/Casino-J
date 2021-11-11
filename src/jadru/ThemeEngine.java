package jadru;

import jjam.SQLiteManager;
import jjam.SQLiteManager.*;

import java.awt.*;

import static java.lang.Integer.decode;

public class ThemeEngine {
    int skin = 0;
    String [] background_color = new String[]{"#FFFFFF", "#FFFFFF", "#FFFFFF", "#000000", "#000000"};

    public ThemeEngine(String id){
        SQLiteManager b = new SQLiteManager("","","");
        skin = b.getSkin(id);
    }

    public Color getBackgroundColor(){
        return new Color(decode(background_color[skin]));
    }
    public String getCardBackImgURL(){
        return "asset/card_back_" + skin;
    }
}
