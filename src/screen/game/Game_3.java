package screen.game;

import screen.ui.MainScreen;
import support.SQLiteManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game_3 extends JFrame implements ActionListener {
    Random rnd;
    static int btn_del, btn_dele;
    int i;
    int num[];
    int fc, sc, click_count, cor_count;
    JFrame frm;
    static JButton btn[];
    static JLabel image_label[];
    static int click_stop;
    static int sec_time;
    static int dec_time;
    static int stop_time;
    static int timer_stop;
    String id;
    ImageIcon game_bt;

    public Game_3(String id) {
        frm = new JFrame();
        Timer timer = new Timer();
        WorkTask worktask = new WorkTask();
        btn = new JButton[16];
        rnd = new Random();
        num = new int[16];
        image_label = new JLabel[16];

        frm.setTitle("미니게임");
        this.id = id;
        game_bt = new ImageIcon(support.ThemeManager.getCardBackImgURL(this.id));
        frm.setBounds(600, 150, 490, 670);
        frm.setLayout(null);
        frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        timer.schedule(worktask, 0, 100);
        click_stop = 0; // 클릭 했을 시 다른 버튼이 클릭이 안되게 함
        sec_time = 0; // 1초 단위
        dec_time = 0; // 0.1초 단위
        stop_time = -100; // 버튼이나 이미지를 1초후에 사라지게 할 변수
        click_count = 0;
        timer_stop = 0;
        this.i = 0;
        btn_del = 0;
        btn_dele = 0;// 삭제할 버튼의 번호의 변수

        for (i = 0; i < 16; i++) // 중복 검사
        {
            num[i] = rnd.nextInt(16);
            if (i > 0) {
                for (int j = 0; j < i; j++) {
                    if (num[i] == num[j]) {
                        i--;
                        break;
                    }
                }
            }
        }

        for (i = 0; i < 16; i++) {
            frm.add(btn[i] = new JButton(game_bt)); // 프레임안에 버튼을 넣음

            btn[i].addActionListener(this); // 버튼에 이벤트를 사용하겠다
            btn[i].setSize(120, 160);
            if (i < 4)
                btn[i].setLocation(0 + 120 * (i), 0);
            else if (i < 8)
                btn[i].setLocation(0 + 120 * (i - 4), 160);
            else if (i < 12)
                btn[i].setLocation(0 + 120 * (i - 8), 320);
            else if (i < 16)
                btn[i].setLocation(0 + 120 * (i - 12), 480);
        }

        for (i = 0; i < 16; i++) {
            if (num[i] == 0 || num[i] == 15)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/DogeCoin.png")));
            else if (num[i] == 1 || num[i] == 14)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/BitCoin.png")));
            else if (num[i] == 2 || num[i] == 13)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/EthCoin.png")));
            else if (num[i] == 3 || num[i] == 12)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/RippleCoin.png")));
            else if (num[i] == 4 || num[i] == 11)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/NanoCoin.png")));
            else if (num[i] == 5 || num[i] == 10)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/TronCoin.png")));
            else if (num[i] == 6 || num[i] == 9)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/NeoCoin.png")));
            else if (num[i] == 7 || num[i] == 8)
                frm.add(image_label[i] = new JLabel(new ImageIcon("src/asset/game2/QtumCoin.png")));

            image_label[i].setSize(120, 160);

            if (i < 4)
                image_label[i].setLocation(0 + 120 * (i), 0);
            else if (i < 8)
                image_label[i].setLocation(0 + 120 * (i - 4), 160);
            else if (i < 12)
                image_label[i].setLocation(0 + 120 * (i - 8), 320);
            else if (i < 16)
                image_label[i].setLocation(0 + 120 * (i - 12), 480);
            image_label[i].setVisible(false);

        }
        frm.setVisible(true); // 프레임을 볼수 있게 설정
    }

    public void finish() {
        JFrame ffrm = new JFrame("게임종료");
        ffrm.setBounds(500, 400, 500, 200);
        ffrm.setVisible(true);
        ffrm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        SQLiteManager b = new SQLiteManager("", "", "");
        if (sec_time <= 10 && sec_time > 0) {
            b.givePoint(id, 50);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n50포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        } else if (sec_time <= 20 && sec_time > 10) {
            b.givePoint(id, 40);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n40포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        } else if (sec_time <= 30 && sec_time > 20) {
            b.givePoint(id, 30);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n30포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        } else if (sec_time <= 40 && sec_time > 30) {
            b.givePoint(id, 20);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n20포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        } else if (sec_time <= 50 && sec_time > 40) {
            b.givePoint(id, 10);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n10포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        } else {
            b.givePoint(id, 5);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n5포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        frm.removeAll();
        repaint();
        dispose();
    }

    public static class WorkTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("WorkTask RUN");
            dec_time++;
            if (dec_time % 10 == 0) {
                sec_time++;
            }

            if (stop_time + 10 == dec_time) // 버튼 클릭 후 1초가 지나면 버튼을 보여주고 이미지를 사라지게함
            {
                btn[btn_del].setVisible(true); // 버튼을 다시 보여줌
                image_label[btn_del].setVisible(false); // 이미지를 다시 사라지게함
                btn[btn_dele].setVisible(true);
                image_label[btn_dele].setVisible(false);
                click_stop = 0;
                System.out.println("이미지 보여주고 사라지고 반복");
            }

            if (timer_stop == 1){
                cancel();
                System.out.println("cancel");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (click_stop != 1) // 먼저 다른 버튼을 클릭 했을 시 실행되지 않게함
        {
            for (i = 0; i < 16; i++) {
                if (e.getSource().equals(btn[i])) {

                    if (click_count == 0) {
                        btn_del = i;
                        fc = num[i];
                        click_count++;

                        btn[btn_del].setVisible(false);
                        image_label[btn_del].setVisible(true);
                    } else if (click_count == 1)
                    {
                        btn_dele = i;
                        sc = num[i];
                        click_count--;

                        btn[btn_dele].setVisible(false);
                        image_label[btn_dele].setVisible(true);

                        if (fc + sc == 15) {
                            cor_count++;
                        } else {
                            click_stop = 1;
                            stop_time = dec_time;
                        }
                    }
                }
            }
        }
        if (cor_count == 8) {
            frm.setVisible(false);
            timer_stop = 1; // 타이머 정지 변수를 1로 만듬
            new MainScreen(id);
            finish();
        }
    }
}
