package jadru;

import java.util.*;

public class ShuffleCard{

    protected static String[][] makeCard(){
        String[] marks = {"♠", "◆", "♣", "♥"};
        String[][] cards = new String[4][13];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                switch (j){
                    case 0:
                        cards[i][j] = marks[i] + " A";
                        break;
                    case 10:
                        cards[i][j] = marks[i] + " K";
                        break;
                    case 11:
                        cards[i][j] = marks[i] + " Q";
                        break;
                    case 12:
                        cards[i][j] = marks[i] + " J";
                    default:
                        cards[i][j] = marks[i] + " " + (j+1);
                }

            }
        }
        return cards;
    }
    protected static int[] pickCards(int size){
        Set<Integer> set = new HashSet<>();
        while (set.size() < size)
        { double d = Math.random() * 52 + 1;
            set.add((int) d);
        }
        return set.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}