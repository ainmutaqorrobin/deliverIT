package BusinessPartner;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

public class BusinessPartnerController {
    static JTable table;
    String[] columnNames = {"Restaurant Name", "Restaurant Address", "Restaurant Email", "Restaurant Phone Number"};
    String from;
    JFrame frame1;
    int upperbound=1000;
    Random rand = new Random();
    int partnerid;
    String partnerid1;
    String partnerid2;
    public void updatedata(JTextField nameres, JTextArea address, JTextField email, JTextField contact){
        try {  
       Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deliverit", "root", "");  
        Statement myStmt = myConn.createStatement();  
        myStmt.execute("UPDATE BusinessPartner SET Partner_Address='" + address.getText() + "',Partner_Email='" + email.getText() 
                + "',Partner_Phone='" + contact.getText() + "' WHERE Partner_Name='" + nameres.getText() + "'");  
        JOptionPane.showMessageDialog(null, "Data Successfully Updated!");  
        myStmt.close();  
        myConn.close();
    } catch (SQLException se) {  
        JOptionPane.showMessageDialog(null, se);  
    }
    }
    
    public void add(JTextField nameres, JTextArea address, JTextField email, JTextField contact){
        try {  
            String sql = "insert into businesspartner values(?,?,?,?,?)";
            partnerid=rand.nextInt(upperbound);
            partnerid1=String.valueOf(partnerid);
            partnerid2="P".concat(partnerid1);
         
             Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql);
                  
            pstmt.setString(1,partnerid2);
            pstmt.setString(2,nameres.getText());
            pstmt.setString(3,address.getText());
            pstmt.setString(4,email.getText());
            pstmt.setString(5,contact.getText());
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Successfully Added!");  

    } catch (SQLException se) {  
        JOptionPane.showMessageDialog(null, se);  
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(BusinessPartnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletedata(JTextField nameres){
    try {  
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deliverit", "root", "");  
        Statement myStmt = myConn.createStatement();  
        myStmt.execute("delete from businesspartner where Partner_Name = '"+nameres.getText()+"'"); 
        JOptionPane.showMessageDialog(null, "Data Successfully Deleted!");  
        myStmt.close();  
        myConn.close();
    } catch (SQLException se) {  
        JOptionPane.showMessageDialog(null, se);  
    }
    }

    public void showTableData(JComboBox c1) {
         
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        
        frame1.setLocation(500,350);

//TableModel tm = new TableModel();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());

//table = new JTable(model);

        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.setFillsViewportHeight(true);
        
        
        JScrollPane scroll = new JScrollPane(table);

        scroll.setHorizontalScrollBarPolicy(

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setVerticalScrollBarPolicy(

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        from = (String) c1.getSelectedItem();

//String textvalue = textbox.getText();

        String resname = "";
        String resadd = "";
        String resemail = "";
        String resphone = "";

        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deliverit", "root", "");  
            //Statement myStmt = myConn.createStatement();
            //PreparedStatement pst = myConn.prepareStatement();
            PreparedStatement pst = myConn.prepareStatement("select * from BusinessPartner where Partner_Name= ?");
            pst.setString(1,from);
            ResultSet rs = pst.executeQuery();
            int i = 0;

            if (rs.next()) {

                resname = rs.getString("Partner_Name");
                resadd = rs.getString("Partner_Address");
                resemail = rs.getString("Partner_Email");
                resphone = rs.getString("Partner_Phone");

                model.addRow(new Object[]{resname, resadd, resemail, resphone});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " Record Found");

            } else {

                System.out.println(i + " Records Found");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(1200, 300);

    
    }
    
}
