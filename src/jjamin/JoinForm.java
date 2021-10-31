package jjamin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JoinForm extends JPanel{
	private Image back;
	JLabel title = new JLabel("",JLabel.CENTER);
	JLabel la1,la2,la3,la4;
	JTextField tf1,tf2,tf3,tf4;
	JPasswordField pf1,pf2;
	JButton b1 = new JButton("로그인");
	JButton b2=new JButton("회원가입");
	
	public JoinForm() {
		
		back=Toolkit.getDefaultToolkit().getImage("assets/background.png");
		setLayout(null);
		la1 = new JLabel("ID",JLabel.RIGHT);
		la2 = new JLabel("PassWord",JLabel.RIGHT);
		la3 = new JLabel("PW Check",JLabel.RIGHT);
		la4 = new JLabel("NickName",JLabel.RIGHT);
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		
		pf1 = new JPasswordField();
		pf2 = new JPasswordField();
	
		
		title.setBounds(10,15,1000,55);
		add(title);
		la1.setBounds(300,100,80,30);
		tf1.setBounds(385,100,200,30);
		b1.setBounds(590,100,150,30);
		add(la1);
		add(tf1);
		add(b1);
		JPanel p=new JPanel();
		p.add(b2);
		p.setOpaque(false);
        p.setBounds(718, 840, 235, 30);
        add(p);
        setSize(1280,1024);
		setVisible(true);
	}
	public void paintComponet(Graphics g) {
		g.drawImage(back,0,0,getWidth(),getHeight(),this);
	}
	public static void main(String[]args) {
		
	}
}
