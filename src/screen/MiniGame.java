package screen;
import java.util.*;
import java.awt.event.*;
import java.util.Timer;
import javax.swing.*;
import support.SQLiteManager;

public class MiniGame extends JFrame implements ActionListener {
    Random rnd = new Random();

    static int btn_del = 0, btn_dele = 0;// 삭제할 버튼의 번호의 변수
    static int i = 0, num[] = new int[16];
    static int fc, sc, click_count = 0, cor_count;// 첫번째 클릭, 두번째 클릭, 클릭 횟수, 정답
    static JFrame frm = new JFrame();
    static JButton btn[] = new JButton[16];
    static JLabel image_label[] = new JLabel[16];

    static int click_stop = 0; // 클릭 했을 시 다른 버튼이 클릭이 안되게 함
    static int sec_time = 0; // 1초 단위
    static int dec_time = 0; // 0.1초 단위
    static int stop_time=-100; // 버튼이나 이미지를 1초후에 사라지게 할 변수
    static int timer_stop; // 타이머를 종료할 변수

    static Timer timer = new Timer();
    String id;
    public MiniGame(String id){
        this.id=id;
        frm.setBounds(600, 150, 812, 838); // 프레임 위치, 크기 설정
        frm.setLayout(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            frm.add(btn[i] = new JButton("")); // 프레임안에 버튼을 넣음

            btn[i].addActionListener(this); // 버튼에 이벤트를 사용하겠다
            btn[i].setSize(200, 200);
            if (i < 4)
                btn[i].setLocation(0 + 200 * (i), 0);
            else if (i < 8)
                btn[i].setLocation(0 + 200 * (i - 4), 200);
            else if (i < 12)
                btn[i].setLocation(0 + 200 * (i - 8), 400);
            else if (i < 16)
                btn[i].setLocation(0 + 200 * (i - 12), 600);
        }

        for (i = 0; i < 16; i++) {
            if (num[i] == 0 || num[i] == 15)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/card_back_0.png")));
            else if (num[i] == 1 || num[i] == 14)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image1.png")));
            else if (num[i] == 2 || num[i] == 13)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image2.png")));
            else if (num[i] == 3 || num[i] == 12)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image3.png")));
            else if (num[i] == 4 || num[i] == 11)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image4.png")));
            else if (num[i] == 5 || num[i] == 10)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image5.png")));
            else if (num[i] == 6 || num[i] == 9)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image6.png")));
            else if (num[i] == 7 || num[i] == 8)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image7.png")));

            image_label[i].setSize(200, 200);

            if (i < 4)
                image_label[i].setLocation(0 + 200 * (i), 0);
            else if (i < 8)
                image_label[i].setLocation(0 + 200 * (i - 4), 200);
            else if (i < 12)
                image_label[i].setLocation(0 + 200 * (i - 8), 400);
            else if (i < 16)
                image_label[i].setLocation(0 + 200 * (i - 12), 600);
            image_label[i].setVisible(false);
        }

        frm.setVisible(true); // 프레임을 볼수 있게 설정
    }

    public MiniGame() {
        frm.setBounds(600, 150, 812, 838); // 프레임 위치, 크기 설정
        frm.setLayout(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            frm.add(btn[i] = new JButton("")); // 프레임안에 버튼을 넣음

            btn[i].addActionListener(this); // 버튼에 이벤트를 사용하겠다
            btn[i].setSize(200, 200);
            if (i < 4)
                btn[i].setLocation(0 + 200 * (i), 0);
            else if (i < 8)
                btn[i].setLocation(0 + 200 * (i - 4), 200);
            else if (i < 12)
                btn[i].setLocation(0 + 200 * (i - 8), 400);
            else if (i < 16)
                btn[i].setLocation(0 + 200 * (i - 12), 600);
        }

        for (i = 0; i < 16; i++) {
            if (num[i] == 0 || num[i] == 15)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/card_back_0.png")));
            else if (num[i] == 1 || num[i] == 14)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image1.png")));
            else if (num[i] == 2 || num[i] == 13)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image2.png")));
            else if (num[i] == 3 || num[i] == 12)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image3.png")));
            else if (num[i] == 4 || num[i] == 11)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image4.png")));
            else if (num[i] == 5 || num[i] == 10)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image5.png")));
            else if (num[i] == 6 || num[i] == 9)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image6.png")));
            else if (num[i] == 7 || num[i] == 8)
                frm.add(image_label[i] = new JLabel(new ImageIcon("asset/image7.png")));

            image_label[i].setSize(200, 200);

            if (i < 4)
                image_label[i].setLocation(0 + 200 * (i), 0);
            else if (i < 8)
                image_label[i].setLocation(0 + 200 * (i - 4), 200);
            else if (i < 12)
                image_label[i].setLocation(0 + 200 * (i - 8), 400);
            else if (i < 16)
                image_label[i].setLocation(0 + 200 * (i - 12), 600);
            image_label[i].setVisible(false);
        }

        frm.setVisible(true); // 프레임을 볼수 있게 설정

    }

    public void finish() {
        JFrame ffrm = new JFrame("게임종료");
        ffrm.setBounds(700, 400, 300, 200);
        ffrm.setVisible(true);
        ffrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SQLiteManager b = new SQLiteManager("","","");
        if(sec_time<=10&&sec_time>0){
            b.givePoint(id,50);
            JLabel finish = new JLabel(sec_time+"초 걸렸습니다.\n50포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        else if(sec_time<=20&&sec_time>10){
            b.givePoint(id,40);
            JLabel finish = new JLabel(sec_time+"초 걸렸습니다.\n40포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        else if(sec_time<=30&&sec_time>20){
            b.givePoint(id,30);
            JLabel finish = new JLabel(sec_time+"초 걸렸습니다.\n30포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        else if(sec_time<=40&&sec_time>30){
            b.givePoint(id,20);
            JLabel finish = new JLabel(sec_time+"초 걸렸습니다.\n20포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        else if(sec_time<=50&&sec_time>40){
            b.givePoint(id,10);
            JLabel finish = new JLabel(sec_time+"초 걸렸습니다.\n10포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        else {
            b.givePoint(id, 5);
            JLabel finish = new JLabel(sec_time + "초 걸렸습니다.\n5포인트를 획득하였습니다.", JLabel.CENTER);
            ffrm.add(finish);
        }
        new MainScreen(id);
    }
    public static class WorkTask extends TimerTask {
        @Override
        public void run() {
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
            }

            if (timer_stop == 1)
                timer.cancel(); // 타이머 정지
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

                        btn[btn_del].setVisible(false); // 버튼을 보이지 않게함
                        image_label[btn_del].setVisible(true); // 이미지를 보이게 함
                    }
                    else if (click_count == 1) // count를 이용하여 두번째 클릭을 구분
                    {
                        btn_dele = i;
                        sc = num[i];
                        click_count--;

                        btn[btn_dele].setVisible(false); // 버튼을 보이지 않게함
                        image_label[btn_dele].setVisible(true); // 이미지를 보이게 함

                        if (fc + sc == 15) {
                            cor_count++;
                        }

                        else {

                            /*
                             * try { Thread.sleep(500); } catch
                             * (InterruptedException e1) 버튼 클릭시 1초 지연. 미구현 {
                             * e1.printStackTrace(); }
                             */
                            click_stop = 1;
                            stop_time = dec_time;	//1초동안 버튼이 보이게 현재 시간(0.1초 단위)를 넣음
                        }
                    }
                }
            }
        }
        if (cor_count == 8) {
            frm.setVisible(false);
            timer_stop = 1; // 타이머 정지 변수를 1로 만듬
            finish();
        }
    }
}
