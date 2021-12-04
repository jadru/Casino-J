package screen;

import support.GlobalGUI;
import support.SQLiteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExchangeScreen extends GlobalGUI {
    private Image card1_back, card2_back, card3_back, card4_back;
    public boolean checkFrontBack = true;

    ExchangeScreen(String id) {
        super("상점", "src/asset/bg/exchangeImg.png"); //타이틀

        SQLiteManager b = new SQLiteManager("", "", "");
        String username = b.getNickname(id);
        int userpoint = b.getPoint(id);
        JLabel usertext = new JLabel(username);
        JLabel userPoint = new JLabel("$" + String.valueOf(userpoint));

        usertext.setFont(casinoFont(30));
        userPoint.setFont(casinoFont(30));
        usertext.setBounds(260, 12, 150, 100);
        userPoint.setBounds(410, 12, 400, 100);
        add(usertext);
        add(userPoint);

        ImageIcon mainbtn = new ImageIcon("src/asset/btn/main_btn.png");
        JButton mainButton = new JButton(mainbtn);
        ImageIcon profile_bt_img = new ImageIcon("src/asset/game1/icons8-test-account-96.png");
        JButton profile_bt = new JButton(profile_bt_img);
        ImageIcon topbar_img = new ImageIcon("src/asset/ui/topbar.png");
        ImageIcon buyBt = new ImageIcon("src/asset/btn/buyBt_Img.png");
        ImageIcon possessionBt = new ImageIcon("src/asset/btn/possessionBt_Img.png");

        ImageIcon consImg = new ImageIcon("src/asset/ui/cost_Img.png");
        JLabel cost = new JLabel(consImg);

        JPanel jp = new JPanel() {
            @Override
            public void paint(Graphics g) {
                if (checkFrontBack == true) {
                    g.drawImage(card1_back, 43, 0, 200, 270, this);
                    g.drawImage(card2_back, 323, 0, 200, 270, this);
                    g.drawImage(card3_back, 605, 0, 200, 270, this);
                    g.drawImage(card4_back, 885, 0, 200, 270, this);

                }

            }
        };
        ImageIcon card1_backside = new ImageIcon("src/asset/theme/card_back_1.png");
        ImageIcon card2_backside = new ImageIcon("src/asset/theme/card_back_2.png");
        ImageIcon card3_backside = new ImageIcon("src/asset/theme/card_back_3.png");
        ImageIcon card4_backside = new ImageIcon("src/asset/theme/card_back_4.png");


        card1_back = card1_backside.getImage();
        card2_back = card2_backside.getImage();
        card3_back = card3_backside.getImage();
        card4_back = card4_backside.getImage();


        jp.setBounds(80, 270, 1200, 300);
        add(jp);


        /*bt[0] = new JButton(backBt);
        bt[1] = new JButton(frontBt);

        bt[0].setBounds(100,200,285,65);
        bt[1].setBounds(400,200,285,65);*/

        JButton bt[] = new JButton[4];
        for (int i = 0; i < 4; i++)
            bt[i] = new JButton(buyBt);

        for (int i = 0; i < 4; i++)
            bt[i].setBounds(100 + ((i) * 280), 555, 250, 65);

        for (int i = 0; i < 4; i++) {
            bt[i].setBorderPainted(false);
            bt[i].setContentAreaFilled(false);
        }
        mainButton.setBounds(30, 20, 100, 100);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);

        profile_bt.setBounds(170, 30, 70, 70);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);

        cost.setBounds(410, 150, 465, 100);

        JLabel topBar = new JLabel(topbar_img);
        topBar.setBounds(150, 25, 971, 81);


        for (int i = 0; i < 4; i++)
            add(bt[i]);
        add(mainButton);
        add(profile_bt);
        add(topBar);
        add(cost);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });

        bt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLiteManager b = new SQLiteManager("", "", "");
                if (b.skinCheck(id, 1) == true) {
                    JOptionPane.showMessageDialog(null, "이미 구매한 상품입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {

                    } else if (result == JOptionPane.YES_OPTION) {
                        if (b.getPoint(id) >= 1000) {
                            b.buySkin(id, 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "포인트가 부족합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                    }
                }
            }
        });
        bt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLiteManager b = new SQLiteManager("", "", "");
                if (b.skinCheck(id, 2) == true) {
                    JOptionPane.showMessageDialog(null, "이미 구매한 상품입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {

                    } else if (result == JOptionPane.YES_OPTION) {
                        if (b.getPoint(id) >= 1000) {
                            b.buySkin(id, 2);
                        } else {
                            JOptionPane.showMessageDialog(null, "포인트가 부족합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                    }
                }
            }
        });
        bt[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLiteManager b = new SQLiteManager("", "", "");
                if (b.skinCheck(id, 3) == true) {
                    JOptionPane.showMessageDialog(null, "이미 구매한 상품입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {

                    } else if (result == JOptionPane.YES_OPTION) {
                        if (b.getPoint(id) >= 1000) {
                            b.buySkin(id, 3);
                        } else {
                            JOptionPane.showMessageDialog(null, "포인트가 부족합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                    }
                }
            }
        });
        bt[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLiteManager b = new SQLiteManager("", "", "");
                if (b.skinCheck(id, 4) == true) {
                    JOptionPane.showMessageDialog(null, "이미 구매한 상품입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {

                    } else if (result == JOptionPane.YES_OPTION) {
                        if (b.getPoint(id) >= 1000) {
                            b.buySkin(id, 4);
                        } else {
                            JOptionPane.showMessageDialog(null, "포인트가 부족합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                    }
                }
            }
        });

        /*bt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFrontBack = true;
                revalidate();
                repaint();
            }
        });
        bt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFrontBack = false;
                revalidate();
                repaint();
            }
        });*/

        repaintGUI();
    }
}
