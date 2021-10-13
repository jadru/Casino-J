package jjamin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  

public class Login extends JFrame{
	
    // private MyPanel panel = new MyPanel();
    JLabel la1, la2;
    JTextField tf;
    JPasswordField pf;
    JButton b1, b2;
    
    public Login()
    {	
    	Container c = getContentPane();
    	

        // c.setLayout(null);
        c.setLayout(new BorderLayout());
        la1=new JLabel("로그인",JLabel.RIGHT);
        la1.setBounds(650, 775, 80, 30);
        la1.setFont(new Font("함초롱돋움",Font.BOLD,17));
        tf=new JTextField();
        tf.setBounds(750, 775, 176, 30);
        tf.setBackground(Color.white);
        tf.setFont(new Font("함초롱돋움",Font.BOLD,15));
        c.add(la1);c.add(tf);
        
        la2=new JLabel("비밀번호",JLabel.RIGHT);
        la2.setBounds(650, 810, 80, 30);
        la2.setFont(new Font("함초롱돋움",Font.BOLD,17));
        pf=new JPasswordField();
        pf.setBounds(750, 810, 176, 30);
        pf.setBackground(Color.white);
        c.add(la2); c.add(pf);
        
        b1=new JButton("로그인");
        
        b1.setFont(new Font("함초롱돋움",Font.BOLD,15));
        b1.setBackground(Color.white);
        b1.setBounds(750,845,80,30);
        b2=new JButton("회원가입");
        b2.setFont(new Font("함초롱돋움",Font.BOLD,15));
        b2.setBackground(Color.white);
        b2.setBounds(835,845,90,30);
        
        
        c.add(b1);
        c.add(b2);
        setSize(1280,1024);
        setVisible(true);
    }
//    class MyPanel extends JPanel{
//    	private ImageIcon icon = new ImageIcon("assets/background.png");
//    	private Image img = icon.getImage();
//    	public void painComponent(Graphics g) {
//    		super.paintComponent(g);
//
//    		g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
//    	}
////    }
    public static void main(String[]args) {
    	new Login();
    }
}
