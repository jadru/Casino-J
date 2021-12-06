package support;

import screen.game.Game_1;
import screen.game.Game_2;

public class ShuffleCard {
    public static Game_1 game;

    public static String[][] makeCardDeck() {
        String[] marks = {"♠", "♦", "♣", "♥"};
        String[][] cards = new String[4][13];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                switch (j) {
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

    public static int getCardFromDeck() {
        int cnt = 0;
        int result = 0;
        while (cnt < 100) {
            result = (int) (Math.random() * 52);
            if (!game.getAtUsedCard(result)) {
                cnt++;
            } else {
                game.setAtUsedCard(true, result);
                break;
            }
        }
        return result;
    }

    public static int getOneCardFromDeck() {
        return (int) (Math.random() * 52);
    }


}