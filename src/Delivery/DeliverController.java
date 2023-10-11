package Delivery;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;

public class DeliverController{
    
    private static DecimalFormat df2 = new DecimalFormat("#,###,##0.00");
    JButton accept = new JButton("Accept Order");
    JButton finish = new JButton("Delivered");
    JFrame frame2 = new JFrame(); 
    public String deliver = "new order";
    public String process = "Undelivered";
    public String done = "Delivered";
    JFrame frame = new JFrame();
    
    public void viewDeliveredTable(String ID, String deliver, String order, String delivery, String cust, JTable jTable1, String[] column) {
       DefaultTableModel model = new DefaultTableModel();
       model.setColumnIdentifiers(column);
        try {    
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");

            String sql = "SELECT orders.Order_ID , orders.Delivery_ID, delivery.Cust_ID FROM `delivery` INNER JOIN `orders` ON delivery.Delivery_ID = orders.Delivery_ID where delivery.Runner_ID = ? and delivery.Delivery_Status = ? ";

            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setString(1,ID);
            ps.setString(2,deliver);

            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {      
                order=rs.getString("Order_ID");
                delivery=rs.getString("Delivery_ID");
                cust=rs.getString("Cust_ID");
                model.addRow(new Object[]{order,delivery, cust});
                i++;
            }

            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } 
            else {
                System.out.println(i + " Records Found");
            }
            jTable1.setModel(model);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DeliveredList(String deliver) 
    {
        String sql2 = "SELECT foodorder.Food_Name, foodorder.Food_Quantity ,foodorder.Food_Price,delivery.Total_Delivery, orders.Order_Address FROM `orders` INNER JOIN `foodorder` ON foodorder.Order_ID = orders.Order_ID INNER JOIN `delivery` ON delivery.Delivery_ID = orders.Delivery_ID where delivery.Delivery_ID = ? and delivery.Delivery_Status = ? ";  
        String sql3 = "SELECT orders.Total_FoodPrice FROM `orders` INNER JOIN `delivery` ON delivery.Delivery_ID = orders.Delivery_ID where delivery.Delivery_ID = ? and delivery.Delivery_Status = ? ";  
         
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            PreparedStatement pstmt3 = con.prepareStatement(sql3);
            
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter Delivery ID:");
            JTextField id = new JTextField(10);
            panel.add(label);
            panel.add(id);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(null, panel, "View Delivered Item List",
            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[1]);
            pstmt2.setString(2,deliver);
            pstmt2.setString(1,id.getText());
            ResultSet rs2 = pstmt2.executeQuery();
            pstmt3.setString(2,deliver);
            pstmt3.setString(1,id.getText());
            ResultSet rs3 = pstmt3.executeQuery();

            if(option == 0){ // pressing OK button if(rs2.next()){
                JFrame frame2 = new JFrame();
                if(rs2.next()){  
                    JLabel label1 = new JLabel("Delivered Food");
                    JLabel label2 = new JLabel("Delivery Address:  " + rs2.getString("orders.Order_Address"));
                    JLabel label3 = new JLabel("Food Lists  ");
                    JLabel label6 = new JLabel("Food Price(RM)");
                    JLabel label4 = new JLabel(rs2.getString("foodorder.Food_Name") +" x"+rs2.getInt("foodorder.Food_Quantity"));
                    JLabel label5 = new JLabel("     "+df2.format(rs2.getDouble("foodorder.Food_Price")));
                    JLabel label25 = new JLabel("Total Costs (RM)  "+df2.format(rs2.getDouble("delivery.Total_Delivery")));
                    label4.setBounds(20,100,300,25);
                    frame2.add(label4);
                    label5.setBounds(310,100,300,25);
                    frame2.add(label5);
                    int i = 120;
                    while(rs2.next()){
                        JLabel label14 = new JLabel(rs2.getString("foodorder.Food_Name") +" x"+rs2.getInt("foodorder.Food_Quantity"));
                        JLabel label15 = new JLabel("     "+df2.format(rs2.getDouble("foodorder.Food_Price")));
                        label14.setBounds(20,i,300,25);
                        frame2.add(label14);
                        label15.setBounds(310,i,300,25);
                        frame2.add(label15);
                        i = i+20;
                    }
                    label25.setBounds(20,280,300,25);            
                    frame2.add(label25);
                    label1.setBounds(20,10,300,35);//[x,y,width,height]
                    label1.setFont(new Font(null,Font.BOLD,20));
                    label2.setBounds(20,50,300,25);
                    label3.setBounds(20,80,300,25);
                    label6.setBounds(310,80,300,25);
                    frame2.add(label3);
                    frame2.add(label1);
                    frame2.add(label2);
                    frame2.add(label6);
                }
                if(rs3.next()){  
                    JLabel label13 = new JLabel("Total Food Costs (RM)  "+df2.format(rs3.getDouble("orders.Total_FoodPrice")));
                    label13.setBounds(20,250,300,25);            
                    frame2.add(label13);
                }
                frame2.setSize(450,400);
                frame2.setLayout(null);
                frame2.setVisible(true);  
                frame2.setTitle("Delivered Item Lists");
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Deliver.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Deliver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void viewUndeliveredTable(String deliver, String order, String delivery, String cust, String status, JTable jTable1, String[] column) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {    
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");

            String sql = "SELECT orders.Order_ID , orders.Delivery_ID, delivery.Cust_ID, delivery.Delivery_Status FROM `delivery` INNER JOIN `orders` ON delivery.Delivery_ID = orders.Delivery_ID where delivery.Delivery_Status = ? ";

            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setString(1,deliver);

            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {      
                order=rs.getString("Order_ID");
                delivery=rs.getString("Delivery_ID");
                cust=rs.getString("Cust_ID");
                status=rs.getString("Delivery_Status");
                model.addRow(new Object[]{order,delivery,cust,status});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } 
            else {
                System.out.println(i + " Records Found");
            }
            jTable1.setModel(model);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }    
}
