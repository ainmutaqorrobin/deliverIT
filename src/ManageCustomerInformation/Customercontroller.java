package ManageCustomerInformation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Customercontroller {
    
    public static String CustomerId;
    String Customerpassword; 
    private static String cID;
 
    public Customercontroller(String CustomerId , String Customerpassword)
    {
        CustomerId = this.CustomerId;
        Customerpassword = this.Customerpassword;     
    }

    Customercontroller() {   
    }
  
    public void registerCustomer(JFrame frame){
        CustomerRegisterInterface register = new CustomerRegisterInterface();
        frame.dispose();
    }

    void resetLogin(JTextField CustomerId, JPasswordField Customerpassword, JLabel messageLabel) {
        CustomerId.setText("");
        Customerpassword.setText("");
        messageLabel.setText("");
    }
    
    public void loginbutton(JTextField CustomerId , JPasswordField Customerpassword , JLabel messageLabel , JFrame frame , ActionEvent e){
        
        String sql = "select * from customerinformation where ID = ? and Password = ? ";   
        
         try
        {     
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1,CustomerId.getText());
            pstmt.setString(2,new String(Customerpassword.getPassword()));
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                {   
                    JOptionPane.showMessageDialog(null, "Welcome "+ rs.getString("Name") + " ! " , "Successfull Login",JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    Customerpage x = new Customerpage(CustomerId.getText());
                    x.setVisible(true);          
                }
            else if (CustomerId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "ENTER USER ID !" , "ERROR",JOptionPane.OK_CANCEL_OPTION);
            }
            else if (Customerpassword.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "ENTER PASSWORD !" , "ERROR",JOptionPane.OK_CANCEL_OPTION);
            }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Username / Password " , "Unsuccessfull Login",JOptionPane.ERROR_MESSAGE);
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Login Unsuccessfull !!!");
                    CustomerId.setText("");
                    Customerpassword.setText("");
                }
    }
         
         catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect Ato DB - Error:"+a);
        }
   
}
       
    public void saveCustomerInforamtion(JTextField Customerid, JTextField Customername, JTextField Customeric, JTextField Customeremail, JTextField Customeraddress, JTextField Customerphone, JTextField pswd, JFrame frame) {
         String sql = "INSERT INTO customerinformation(ID,Name,IC,Email,Address,Phoneno,Password,chat_log) VALUES(?,?,?,?,?,?,?,?)";
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
             pstmt.setString(1, Customerid.getText());
             pstmt.setString(2, Customername.getText());
             pstmt.setString(3, Customeric.getText());
             pstmt.setString(4, Customeremail.getText());
             pstmt.setString(5, Customeraddress.getText());
             pstmt.setString(6, Customerphone.getText());
             pstmt.setString(7, pswd.getText());
             pstmt.setString(8, "No Messages Yet");
             
             pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Info Saved Successfully" , "Saved",JOptionPane.PLAIN_MESSAGE);
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("Not connect Bto DB - Error:");
        }
    }   

    public void savebutton(JFrame frame) {
        frame.dispose(); 
        CustomerLoginInterface login = new CustomerLoginInterface();
    }
   

    public void customerprofile(JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5 , String Customerid) {
        
        String sql = "select * from customerinformation where ID = '" + Customerid + "'";
       
        try
        {     
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql);

                ResultSet rs = pstmt.executeQuery(sql);
                
                while(rs.next())
                {   
                    String id = String.valueOf(rs.getString("ID"));
                    String name = rs.getString("Name");
                    String ic = rs.getString("IC");
                    String email = rs.getString("Email");
                    String add = rs.getString("Address");
                    String pn = rs.getString("PhoneNo");
                    String pswd = rs.getString("Password");
                    
                    jTextField1.setText(name);
                    jTextField2.setText(id);
                    jTextField3.setText(pn);
                    jTextField4.setText(email);
                    jTextField5.setText(add);
                             
                }
        }                     
        catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect to CDB - Error:"+a);
        }
    }

    public void getID(String customerId) {
        cID=customerId;
    }
    
    public static String ID(){
        return cID;
    }
    
}
  



 
