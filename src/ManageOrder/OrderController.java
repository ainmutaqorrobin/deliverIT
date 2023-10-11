
package ManageOrder;
import Payment.MainPayment;
import java.sql.*;
import java.util.Random;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class OrderController 
{
    Order o=new Order();
    double porridge,rice,burger,wedges,tenders,totalkfc,
            cake,nasi,pancake,sandwich,sundae,totalmcd,
            alohac,chickenp,hawaiic,hawaiip,rayac,totalpizza;
    int porridgeunit,riceunit,burgerunit,wedgesunit,tendersunit,
            cakeunit,nasiunit,pancakeunit,sandwichunit,sundaeunit,
            alohacunit,chickenpunit,hawaiicunit,hawaiipunit,rayacunit
            ,orderid;
    
     String[] foodname=new String[5];
    String myObj = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Create a date object
    String name,orderid1,orderid2,deliveryid2,deliveryid1,custID,address,partnerId;
    double price,totalFoodprice,totalPrice,Foodprice;
    int quantity,neworderid,deliveryid;
    
    public static void main(String[] args)
    {

    }
    public String getOrderID()
    {
        int upperbound=1000;
        Random rand = new Random();
        orderid=rand.nextInt(upperbound);
        orderid2=String.valueOf(orderid);
        orderid1="O".concat(orderid2);
        return orderid1;   
    }
    
      public String getDeliveryID()
    {
        int upperbound=1000;
        Random rand = new Random();
        deliveryid=rand.nextInt(upperbound);
        deliveryid1=String.valueOf(deliveryid);
        deliveryid2="D".concat(deliveryid1);
        return deliveryid2;   
    }
    
   void getfunction(String orderid1, double totalFoodprice, String deliveryid2, String custID, String address, String partnerId) {
         
        this.totalFoodprice=totalFoodprice;
        this.orderid1=orderid1;
        this.deliveryid2=deliveryid2;
        this.custID =custID;
        this.address =address;
        this.partnerId = partnerId;
        totalPrice = (totalFoodprice+10);
        
        
        String sql1 = "INSERT INTO orders(Order_ID,Delivery_ID,Partner_ID,Order_Date,Order_Address,Total_FoodPrice)"
                          +"VALUES(?,?,?,?,?,?)";
        String sql2 = "INSERT INTO delivery VALUES(?,?,?,?,?)";
            
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            PreparedStatement pstmt = conn.prepareStatement(sql1);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt.setString(1, orderid1);      
            pstmt.setString(2, deliveryid2);
            pstmt.setString(3, partnerId); 
            pstmt.setString(4, myObj);        
            pstmt.setString(5,address); 
            pstmt.setDouble(6, totalFoodprice);
            
            pstmt2.setString(1, deliveryid2);
            pstmt2.setString(2, "");
            pstmt2.setString(3, custID);
            pstmt2.setDouble(4,totalPrice);
            pstmt2.setString(5, "New Order");

            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            String st = "Order Submitted";
            JOptionPane.showMessageDialog(null, st);
            MainPayment pay = new MainPayment(orderid1,totalPrice,address,custID);
            pay.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("Not connect to DB Order - Error:");
        }
         
   }
    
    void getfoodorder(String name, double price, int quantity,String orderid1) 
    {
        
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.orderid1=orderid1;
        Foodprice = (price*quantity);
        
        String sql = "INSERT INTO foodorder(Order_ID,Food_Name,Food_Price,Food_Quantity)"
                + "VALUES(?,?,?,?)";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
 
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.setString(1, orderid1);
             pstmt.setString(2, name);
             pstmt.setDouble(3, Foodprice);
             pstmt.setInt(4, quantity);          

             pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("Not connect to DB FoodOrder - Error:");
        }
         
    }  
     
    
 }
    

