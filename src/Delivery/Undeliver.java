package Delivery;

import Runner.WelcomePage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import java.text.DecimalFormat;

public class Undeliver extends javax.swing.JFrame implements ActionListener {
    
    private static DecimalFormat df2 = new DecimalFormat("#,###,##0.00");
    JFrame frame = new JFrame();
    String ID;
    public String deliver = "new order";
    public String process = "Undelivered";
    public String done = "Delivered";
    String column[]={"Order_ID","Delivery_ID","Customer_ID","Delivery_Status"};
    String cust = "";
    String delivery = "";
    String order="";
    String status=""; 
    JButton accept = new JButton("Accept Order");
    JButton finish = new JButton("Delivered");
    JFrame frame2 = new JFrame();
    JTextField id = new JTextField(10);    
    public Undeliver() {
        
    }

    public Undeliver(String runnerid) {
        ID=runnerid;
        initComponents(); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToggleButton3 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton1.setText("Back");
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("New Delivery List");

        jToggleButton2.setBackground(new java.awt.Color(153, 204, 255));
        jToggleButton2.setText("View List");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Delivery_ID", "Order_ID", "Customer_ID", "Delivery_Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jToggleButton3.setText("View Order List");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToggleButton1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jToggleButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jToggleButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jToggleButton3)
                .addGap(0, 52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        DeliverController C3 = new DeliverController();
        C3.viewUndeliveredTable(deliver,order,delivery,cust,status,jTable1,column);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        this.dispose();
        WelcomePage welcome = new WelcomePage(ID);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
       String sql2 = "SELECT foodorder.Food_Name, foodorder.Food_Quantity ,foodorder.Food_Price, delivery.Total_Delivery, orders.Order_Address FROM `orders` INNER JOIN `foodorder` ON foodorder.Order_ID = orders.Order_ID INNER JOIN `delivery` ON delivery.Delivery_ID = orders.Delivery_ID where delivery.Delivery_ID = ? and delivery.Delivery_Status = ? ";  
       String sql3 = "SELECT orders.Total_FoodPrice FROM `orders` INNER JOIN `delivery` ON delivery.Delivery_ID = orders.Delivery_ID where delivery.Delivery_ID = ? and delivery.Delivery_Status = ? ";  
         
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            PreparedStatement pstmt3 = con.prepareStatement(sql3);
            
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter Delivery ID:");
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
              accept.setBounds(150,350,150,25);
              accept.setFocusable(false);
              accept.setBackground(new Color(162, 247, 42));
              accept.setForeground(Color.BLACK);
              accept.addActionListener(this);
               accept.addMouseListener(new MouseAdapter() {
               public void mouseEntered(MouseEvent me) {
                  accept.setBackground(new Color(178, 252, 93)); 
                  accept.setForeground(Color.BLACK);
               }
               public void mouseExited(MouseEvent me) {
                  accept.setBackground(new Color(162, 247, 42));
                  accept.setForeground(Color.BLACK);
               }
              });
              
              frame2.add(accept);
              frame2.setSize(450,450);
              frame2.setLayout(null);
              frame2.setVisible(true);  
              frame2.setTitle("New Item Lists");
              
               
       }
       }
         catch (SQLException ex) {
            Logger.getLogger(Deliver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Deliver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jToggleButton3ActionPerformed

     public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Undeliver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables

     @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==accept){
             try {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
                    String sqlu1 = "update delivery set Runner_ID = ?, Delivery_Status = ? where Delivery_ID = ? ";
                    PreparedStatement pstmtu1 =  con.prepareStatement(sqlu1);
                    pstmtu1.setString(1,ID);
                    pstmtu1.setString(2,process);
                    pstmtu1.setString(3,id.getText());
                    
                    pstmtu1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Order Accepted" , "Accept Order",JOptionPane.PLAIN_MESSAGE);
                    accept.setVisible(false);
                    
                    finish.setBounds(150,350,150,25);
                    finish.setFocusable(false);
                    finish.setBackground(new Color(69, 155, 209));
                    finish.setForeground(Color.WHITE);
                    finish.addActionListener(this);             
                    frame2.add(finish);
                    
                    
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Undeliver.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(Undeliver.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
         if (ae.getSource()==finish){
              try {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
                    String sqlu2 = "update delivery set Delivery_Status = ? where Delivery_ID = ? ";
                        PreparedStatement pstmtu2 =  con.prepareStatement(sqlu2);
                        pstmtu2.setString(1,done);
                        pstmtu2.setString(2,id.getText());
                        pstmtu2.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null, "Food Item Delivered Successfully" , "Delivery Done",JOptionPane.PLAIN_MESSAGE);
                        con.close();
                        finish.setVisible(false);
                        frame.dispose();
                    
                    
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Undeliver.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(Undeliver.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        }
         
    }
}
