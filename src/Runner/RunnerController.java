package Runner;

import java.awt.Color;
import java.awt.Font;
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

public class RunnerController
{
    String runnerID;
    String runnerPassword;
            
    public RunnerController(String runnerId, String runnerpassword) {
        runnerId = this.runnerID;
        runnerpassword = this.runnerPassword;
    }

    RunnerController() {
        
    }
       
    public void registerrunner(JFrame frame){ //RUNNER LOGIN CONTROLLER
        RegistrationInterface register = new RegistrationInterface();
        frame.dispose();
    }
    
    public void resetlogin(JTextField runnerId, JPasswordField runnerpassword, JLabel messageLabel){//RUNNER LOGIN CONTROLLER
        runnerId.setText("");
        runnerpassword.setText("");
        messageLabel.setText("");
    }
    
    public void forgetlogin(){//RUNNER LOGIN CONTROLLER
        String sql2 = "select Runner_Password from Runner where Runner_ID = ?";  
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter your USER ID:");
            JTextField id = new JTextField(10);
            panel.add(label);
            panel.add(id);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(null, panel, "Password Recovery",
            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[1]);
            pstmt2.setString(1,id.getText());
            ResultSet rs2 = pstmt2.executeQuery();

            if(rs2.next()){   
               if(option == 0){ // pressing OK button
                    JFrame frame2 = new JFrame();
                    JLabel label1 = new JLabel("EMAIL");
                    JLabel label2 = new JLabel("Your password is:   ' " + rs2.getString("Runner_Password")+" '");
                    label1.setBounds(50,10,300,35);//[x,y,width,height]
                    label1.setFont(new Font(null,Font.BOLD,20));
                    label2.setBounds(50,50,300,25);
                    frame2.add(label1);
                    frame2.add(label2);
                    frame2.setSize(400,400);
                    frame2.setLayout(null);
                    frame2.setVisible(true);    
               }
            }        
        }
        catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect to DB - Error:"+a);
        }
    }

    public void login(JTextField runnerId, JPasswordField runnerpassword, JLabel messageLabel,JFrame frame) {//RUNNER LOGIN CONTROLLER
        String sql = "select * from Runner where Runner_ID = ? and Runner_Password = ? ";
        try{     
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,runnerId.getText());
            pstmt.setString(2,new String(runnerpassword.getPassword()));
         
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {   
                JOptionPane.showMessageDialog(null, "Welcome "+ rs.getString("Runner_Name") + " , " + runnerId.getText()  , "Successfull Login",JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
                WelcomePage welcome = new WelcomePage(runnerId.getText());
            }
            else if (runnerId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Enter USER ID" , "ERROR",JOptionPane.OK_CANCEL_OPTION);
            }
            else if (runnerpassword.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Enter PASSWORD" , "ERROR",JOptionPane.OK_CANCEL_OPTION);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Username / Password " , "Unsuccessfull Login",JOptionPane.ERROR_MESSAGE);
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Login Unsuccessfull !!!");
            }            
        }            
            
        catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect to DB - Error:"+a);
        }
    }
    
    public void saveNewdata(JTextField runnerid, JTextField runnername, JTextField runneric, JTextField runneremail, JTextField runneraddress, JTextField runnerplateno, JTextField runnerphone, JTextField pswd, JFrame frame) { //RUNNER REGISTER CONTROLLER
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            if(runnerid.getText().equals(""))
            {
                 JOptionPane.showMessageDialog(null, "Enter ID" , "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String sql = "insert into runner values (?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,runnerid.getText());
                pstmt.setString(2,runnername.getText());
                pstmt.setString(3,runneric.getText());
                pstmt.setString(4,runneremail.getText());
                pstmt.setString(5,runneraddress.getText());
                pstmt.setString(6,runnerplateno.getText());
                pstmt.setString(7,runnerphone.getText());
                pstmt.setString(8,pswd.getText());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Info Saved Successfully" , "Saved",JOptionPane.PLAIN_MESSAGE);
                conn.close();
                frame.dispose();
                LoginInterfaceRunner login = new LoginInterfaceRunner();
            }
        }
        catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect to DB - Error:"+a);
        }
    }

    public void cancelRegister(JFrame frame) {
        frame.dispose();
        LoginInterfaceRunner login = new LoginInterfaceRunner();
    }

    public void viewProfile(JTextField runnerid, JTextField runnername, JTextField runneric, JTextField runneremail, JTextField runneraddress, JTextField runnerplateno, JTextField runnerphone, JTextField pswd, String runid) {
        String sql = "select * from Runner where Runner_ID = ?";
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,runid);                             
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                runnerid.setText(runid);
                runnername.setText(rs.getString("Runner_Name"));
                runneric.setText(rs.getString("Runner_ICNo"));             
                runneremail.setText(rs.getString("Runner_Email"));
                runneraddress.setText(rs.getString("Runner_Address"));
                runnerplateno.setText(rs.getString("Runner_PlateNo"));
                runnerphone.setText(rs.getString("Runner_PhoneNo"));
                pswd.setText(rs.getString("Runner_Password"));  
            }
            con.close();
            ps.close();
        }
        catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect to DB - Error:"+a);
        }
    }

    void backWelcome(JFrame frame, String runid) {
         frame.dispose();
         WelcomePage welcome = new WelcomePage(runid);
    }

    void updateProfile(JTextField runnerid, JTextField runnername, JTextField runneric, JTextField runneremail, JTextField runneraddress, JTextField runnerplateno, JTextField runnerphone, JTextField pswd, String runid, JFrame frame) 
    {  
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            String sql2 = "update runner set Runner_ID = ?, Runner_Name = ? , Runner_ICNo = ? , Runner_Email = ? , Runner_Address = ? , Runner_PlateNo = ? , Runner_PhoneNo = ? , Runner_Password = ? where Runner_ID = ?";
            PreparedStatement pstmt =  con.prepareStatement(sql2);
            pstmt.setString(1,runnerid.getText());
            pstmt.setString(2,runnername.getText());
            pstmt.setString(3,runneric.getText());
            pstmt.setString(4,runneremail.getText());
            pstmt.setString(5,runneraddress.getText());
            pstmt.setString(6,runnerplateno.getText());
            pstmt.setString(7,runnerphone.getText());
            pstmt.setString(8,pswd.getText());
            pstmt.setString(9,runid);

            if(runnerid.getText().equals(""))
            {
                 JOptionPane.showMessageDialog(null, "Enter ID" , "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Info Updated Successfully" , "Updated",JOptionPane.PLAIN_MESSAGE);
                con.close();
                frame.dispose();
                WelcomePage login = new WelcomePage(runnerid.getText());                
            }
        }
        catch(ClassNotFoundException | SQLException a) 
        {
            System.out.print("Not connect to DB - Error:"+a);
        }
    }

      
}
