package support;

import screen.GameScreen;

import java.util.*;

public class ShuffleCard{
    public static GameScreen game;

    public static String[][] makeCardDeck(){
        String[] marks = {"♠", "♦", "♣", "♥"};
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
                        break;
                    default:
                        int k = j + 1;
                        cards[i][j] = marks[i] + " " + k;
                }
            }
        }
        return cards;
    }
    public static int[] getCardFromDeck(int size){
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        while (set.size() < size)
        { int d = (int)(Math.random() * 52);
            if (!game.getAtUsedCard(d))
                set.add(d);
            else if(cnt > 100)
                System.out.println("카드를 선택할 수 없습니다");
            else
                cnt++;
        }
        int[] pickedcards = set.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        for(int item:pickedcards){
            game.setAtUsedCard(true, item);
        }

        return pickedcards;
    }

}