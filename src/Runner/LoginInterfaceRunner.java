package Runner;

import DeliverITMainPage.MainPage;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import java.util.Scanner;

public class LoginInterfaceRunner implements ActionListener
{     
       Connection conn;
       Scanner scan = new Scanner(System.in);
        JFrame frame = new JFrame();
        JLabel title = new JLabel("Runner Login Page");
        JLabel idlabel = new JLabel("Runner ID : ");
        JTextField runnerId = new JTextField();
        JLabel passwordlabel = new JLabel("Password : ");
        JPasswordField runnerpassword = new JPasswordField();
        JButton loginbutton = new JButton("Login");
        JButton resetbutton = new JButton("Reset");
        JButton back = new JButton("Back");
        JLabel registerlabel = new JLabel("Click Here to Register");
        JButton registerbutton = new JButton("Register");
        JButton forget = new JButton("Forgot Password ?");
        JLabel messageLabel = new JLabel();
        Color myColor1 = new Color(61, 191, 94);
        Color myColor2 = new Color(11, 111, 181);
        Color myColor3 = new Color(232, 53, 68);
        
    public LoginInterfaceRunner() {
                     
        title.setBounds(55,40,300,35);//[x,y,width,height]
        title.setFont(new Font(null,Font.BOLD,30));
                
        idlabel.setBounds(50,100,75,25);
        passwordlabel.setBounds(50,150,75,25);
              
        messageLabel.setBounds(60,350,300,35);
        messageLabel.setFont(new Font(null,Font.ROMAN_BASELINE,25));
        
        runnerId.setBounds(125,100,200,25);
        runnerpassword.setBounds(125,150,200,25);
        
        loginbutton.setBounds(90,200,100,25);
        loginbutton.setFocusable(false);
        loginbutton.setBackground(myColor1);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.addActionListener(this);
        loginbutton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent me) {
            loginbutton.setBackground(new Color(64, 227, 107)); 
            loginbutton.setForeground(Color.BLACK);
         }
         @Override
         public void mouseExited(MouseEvent me) {
            loginbutton.setBackground(myColor1);
            loginbutton.setForeground(Color.WHITE);
         }
      });
        
        resetbutton.setBounds(190,200,100,25);
        resetbutton.setFocusable(false);
        resetbutton.setBackground(myColor3);
        resetbutton.setForeground(Color.WHITE);
        resetbutton.addActionListener(this);
        resetbutton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent me) {
            resetbutton.setBackground(new Color(242, 87, 100)); 
            resetbutton.setForeground(Color.BLACK);
         }
         @Override
         public void mouseExited(MouseEvent me) {
            resetbutton.setBackground(myColor3);
            resetbutton.setForeground(Color.WHITE);
         }
      });
        
        registerbutton.setBounds(90,290,200,35);
        registerbutton.setFocusable(false);
        registerbutton.setBackground(myColor2);
        registerbutton.setForeground(Color.WHITE);
        registerbutton.addActionListener(this);
        registerbutton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent me) {
            registerbutton.setBackground(new Color(17, 171, 237));
             registerbutton.setForeground(Color.BLACK);
        }
         @Override
         public void mouseExited(MouseEvent me) {
            registerbutton.setBackground(myColor2);
             registerbutton.setForeground(Color.WHITE);
         }
      });
        
        forget.setBounds(120,240,153,25);
        forget.setFocusable(false);
        forget.setFont(new Font(null,Font.ITALIC,14));
        forget.setBackground(Color.lightGray);
        forget.setBorderPainted(false);
        forget.setOpaque(false);
        forget.setForeground(Color.BLUE);
        forget.addActionListener(this);
        forget.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent me) {
            forget.setForeground(Color.RED);
         }
         @Override
         public void mouseExited(MouseEvent me) {
            forget.setForeground(Color.BLUE);
         }
      });
        
        back.setBounds(10,5,65,25);
        back.setFocusable(false);
        back.setBackground(new Color(240, 246, 252));
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        
        frame.add(title);
        frame.add(idlabel);
        frame.add(passwordlabel);
        frame.add(runnerId);
        frame.add(runnerpassword);
        frame.add(loginbutton);
        frame.add(resetbutton);
        frame.add(registerbutton);
        frame.add(messageLabel);
        frame.add(forget);
        frame.add(back);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Runner Login Interface");
    }  
        
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String runnerid = runnerId.getText();
        String pswd = String.valueOf(runnerpassword.getPassword());
        RunnerController logincontroller = new RunnerController(runnerid,pswd);
        
        if (e.getSource()==resetbutton){
            logincontroller.resetlogin(runnerId,runnerpassword,messageLabel);
        }
        
        if (e.getSource()==loginbutton)
        {   
             logincontroller.login(runnerId,runnerpassword,messageLabel,frame);
        }            

        if (e.getSource()==registerbutton){
             logincontroller.registerrunner(frame);
        } 

        if (e.getSource()==forget){
             logincontroller.forgetlogin();
        }
        
        if (e.getSource()==back){
             MainPage main = new MainPage();
             main.setVisible(true);
             frame.dispose();
        }
    }
}
