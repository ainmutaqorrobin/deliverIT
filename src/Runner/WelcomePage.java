
package Runner;

import Delivery.Deliver;
import Delivery.Undeliver;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class WelcomePage implements ActionListener
{
    JFrame frame = new JFrame();
    JLabel welcomelabel = new JLabel("Hello");
    JButton profile = new JButton("View Profile");
    JButton delivered = new JButton("Delivered Orders");
    JButton undelivered = new JButton("New Orders");
    JButton logout = new JButton("Logout");
    Color myColor = new Color(229, 204, 255);
    Color logoutcolor = new Color(255,154,154);
    static JTable table;
    String column[]={"Delivery_ID","Runner_ID","Customer_ID","Total_Price (RM)"};     
    public String ID;
    public String deliver = "delivered";
    
    public WelcomePage(String runnerid)
    {
        ID = runnerid;
        welcomelabel.setBounds(30,30,200,35);
        welcomelabel.setFont(new Font(null,Font.BOLD,25));
        welcomelabel.setText("Welcome " +runnerid+ ",");
        
        profile.setBounds(100,120,200,25);
        profile.setFocusable(false);
        profile.setBackground(myColor);
        profile.setForeground(Color.BLACK);
        profile.addActionListener(this);
        
        delivered.setBounds(100,180,200,25);
        delivered.setFocusable(false);
        delivered.setBackground(myColor);
        delivered.setForeground(Color.BLACK);
        delivered.addActionListener(this);
        
        undelivered.setBounds(100,240,200,25);
        undelivered.setFocusable(false);
        undelivered.setBackground(myColor);
        undelivered.setForeground(Color.BLACK);
        undelivered.addActionListener(this);
        
        logout.setBounds(300,10,75,25);
        logout.setFocusable(false);
        logout.setBackground(logoutcolor);
        logout.setForeground(Color.BLACK);
        logout.addActionListener(this);
        
        frame.add(welcomelabel);
        frame.add(profile);
        frame.add(logout);
        frame.add(delivered);
        frame.add(undelivered);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Runner Interface");
    }
    
    public void actionPerformed(ActionEvent e) 
    {           
        if (e.getSource()==profile){
            frame.dispose();
            RunnerProfileInterface profilegui = new RunnerProfileInterface(ID);
        }
        
        if (e.getSource()==logout){
            frame.dispose();
            LoginInterfaceRunner login = new LoginInterfaceRunner();
        }
        
        if (e.getSource()==delivered)
        {
           frame.dispose();
           Deliver deliverystatus = new Deliver(ID);
           deliverystatus.setVisible(true);
        }
        
        if (e.getSource()==undelivered)
        {
           frame.dispose();
           Undeliver newdelivery = new Undeliver(ID);
           newdelivery.setVisible(true);
        }
    }
}
