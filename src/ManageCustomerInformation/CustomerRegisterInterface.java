package ManageCustomerInformation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CustomerRegisterInterface implements ActionListener
{
    JFrame frame = new JFrame();
    JLabel registerGui = new JLabel();
    JLabel id = new JLabel("Customer ID :");
    JTextField Customerid = new JTextField();
    JLabel name = new JLabel("Name :");
    JTextField Customername = new JTextField();
    JLabel ic = new JLabel("IC No :");
    JTextField Customeric = new JTextField();
    JLabel email = new JLabel("Email :");
    JTextField Customeremail = new JTextField();
    JLabel address = new JLabel("Address :");
    JTextField Customeraddress = new JTextField();
    JLabel phoneno = new JLabel("Phone No :");
    JTextField Customerphone = new JTextField();
    JLabel psd = new JLabel("Password :");
    JTextField pswd = new JTextField();
    JButton savebutton = new JButton("Save Info");
    Color myColor = new Color(9, 147, 243);
    
    CustomerRegisterInterface()
    {
        registerGui.setBounds(60,10,300,35);
        registerGui.setFont(new Font(null,Font.BOLD,25));
        registerGui.setText("Customer Register Page");
        
        
        id.setBounds(30,60,100,25);
        Customerid.setBounds(110,60,200,25);
         
        name.setBounds(30,90,100,25);
        Customername.setBounds(110,90,200,25);
        
        ic.setBounds(30,120,100,25);
        Customeric.setBounds(110,120,200,25);
        
        email.setBounds(30,150,100,25);
        Customeremail.setBounds(110,150,200,25);
        
        address.setBounds(30,180,100,25);
        Customeraddress.setBounds(110,180,200,25);
        
        phoneno.setBounds(30,210,100,25);
        Customerphone.setBounds(110,210,200,25);
        
        psd.setBounds(30,240,100,25);
        pswd.setBounds(110,240,200,25);
        
        savebutton.setBounds(250,330,100,25);
        savebutton.setFocusable(false);
        savebutton.setBackground(Color.darkGray);
        savebutton.setForeground(Color.WHITE);
        savebutton.addActionListener(this);
        
        frame.add(registerGui);
        frame.add(id);
        frame.add(Customerid);
        frame.add(name);
        frame.add(Customername);
        frame.add(ic);
        frame.add(Customeric);
        frame.add(email);
        frame.add(Customeremail);
        frame.add(address);
        frame.add(Customeraddress);
        frame.add(phoneno);
        frame.add(Customerphone);
        frame.add(psd);
        frame.add(pswd);
        frame.add(savebutton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
       Customercontroller control = new Customercontroller();
       
       control.saveCustomerInforamtion(Customerid , Customername , Customeric , Customeremail , Customeraddress , Customerphone , pswd , frame);
       
       if (e.getSource()==savebutton){
            control.savebutton(frame);
        }
                                  
    }
    
    
}
