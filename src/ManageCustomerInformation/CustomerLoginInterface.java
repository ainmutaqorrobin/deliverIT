package ManageCustomerInformation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class CustomerLoginInterface implements ActionListener
{    
    private String customerId;

    Connection conn;
      Scanner scan = new Scanner(System.in);
    //HashMap <String,String> logininfo = new HashMap <String,String>();
        JFrame frame = new JFrame();
        JLabel title = new JLabel("Customer Login Page");
        JLabel idlabel = new JLabel("Customer ID : ");
        JTextField CustomerId = new JTextField();
        JLabel passwordlabel = new JLabel("Password : ");
        JPasswordField Customerpassword = new JPasswordField();
        JButton loginbutton = new JButton("Login");
        JButton resetbutton = new JButton("Reset");
        JLabel registerlabel = new JLabel("Click Here to Register");
        JButton registerbutton = new JButton("Register");
        JLabel messageLabel = new JLabel();
        Color myColor1 = new Color(51, 249, 186);
        Color myColor2 = new Color(9, 147, 243);
        Color myColor3 = new Color(235, 73, 86);
    
        
    public CustomerLoginInterface() {
                     
        title.setBounds(55,40,410,35);//[x,y,width,height]
        title.setFont(new Font(null,Font.BOLD,30));
                
        idlabel.setBounds(50,100,90,25);
        passwordlabel.setBounds(50,150,75,25);
              
        messageLabel.setBounds(60,300,300,35);
        messageLabel.setFont(new Font(null,Font.ROMAN_BASELINE,25));
        
        CustomerId.setBounds(130,100,200,25);
        Customerpassword.setBounds(125,150,200,25);
        
        loginbutton.setBounds(90,200,100,25);
        loginbutton.setFocusable(false);
        loginbutton.setBackground(Color.darkGray);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.addActionListener(this);
                
        resetbutton.setBounds(190,200,100,25);
        resetbutton.setFocusable(false);
        resetbutton.setBackground(Color.RED);
        resetbutton.setForeground(Color.BLACK);
        resetbutton.addActionListener(this);
        
        registerlabel.setBounds(70,250,250,25);
        
        registerbutton.setBounds(200,250,100,25);
        registerbutton.setFocusable(false);
        registerbutton.setBackground(myColor2);
        registerbutton.setForeground(Color.WHITE);
        registerbutton.addActionListener(this);
        
        frame.add(title);
        frame.add(idlabel);
        frame.add(passwordlabel);
        frame.add(CustomerId);
        frame.add(Customerpassword);
        frame.add(loginbutton);
        frame.add(resetbutton);
        frame.add(registerlabel);
        frame.add(registerbutton);
        frame.add(messageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }  
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {  
        this.customerId=CustomerId.getText();
        Customercontroller control = new Customercontroller();
                                 
            if (e.getSource()==resetbutton){
                control.resetLogin(CustomerId , Customerpassword , messageLabel);     
            }
        
            if (e.getSource()==loginbutton)
            {           
                control.loginbutton(CustomerId, Customerpassword, messageLabel, frame , e);
                control.getID(customerId);
            }            
        
            if (e.getSource()==registerbutton){
                control.registerCustomer(frame);
            } 
        }               
        
    }
        
  
   


