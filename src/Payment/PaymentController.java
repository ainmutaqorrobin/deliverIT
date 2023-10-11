package Payment;

import java.sql.*;

public class PaymentController {

    String card_name;
    String paymentid2,orderid, cardnumber, bankname, payment, cardcvv;
    double totalPrice;
    
    public void getCardDetails(String paymentid2, String orderid, double totalPrice, String cardnumber, String bankname, String payment, String cardcvv) {
        this.paymentid2=paymentid2;
        this.orderid=orderid;
        this.cardnumber=cardnumber;
        this.bankname=bankname;
        this.payment=payment;
        this.cardcvv=cardcvv;
        this.totalPrice=totalPrice;
        
         String sql1 = "INSERT INTO payment VALUES(?,?,?,?,?,?)";
         try
         {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, paymentid2);      
            pstmt.setString(2, orderid);
            pstmt.setDouble(3, totalPrice); 
            pstmt.setString(4, cardnumber);       
            pstmt.setString(5,bankname + " " +payment); 
            pstmt.setString(6, cardcvv);
         
            pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("Not connect to DB Payment - Error:");
        }
        
        
    }
    
}
