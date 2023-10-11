/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerService;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static ManageCustomerInformation.Customercontroller.CustomerId;
/**
 *
 * @author Harry
 */
public class db_con {
    private Connection conn;
private Statement statement;
private ResultSet rs;
private PreparedStatement pstmt;
public Connection openConnection() throws SQLException{
    if(conn == null){
        String url="jdbc:mysql://localhost:3306/";
        String dbName="deliverit";
        String driver="com.mysql.jdbc.Driver";
        String userName="root";
        String password="";
        try{
            Class.forName(driver);
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
            statement = (Statement) conn.createStatement();
            System.out.println("connection succeed");
        }
        catch(ClassNotFoundException | SQLException sqle){
            System.out.println("connection failed");
        }
    }
       return conn;
}
public ResultSet queryAlldata(){
    try{
        rs=statement.executeQuery("select chat_log from customerinformation");
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return rs;
}
public void insertdata(String chat_log){
    /* insert default values*/
    /*
    try{
        int i = statement.executeUpdate("insert into test_data_student values(name,mat,fac,age)");
        if (i>0){
            System.out.println("Insert Successful");
        }
        else{
            System.out.println("Insert Failed");
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }*/
    String sql="INSERT INTO customerinformation VALUES(?,?)";
    try{
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(2, chat_log);
        pstmt.executeUpdate();
        System.out.println("Insert Successful");
    }catch(Exception e){
        e.printStackTrace();
    }
    
}
public void update(String chat){
    String sql= "update customerinformation set chat_log=? where id='" + CustomerId + "'";
    try{
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, chat);
         pstmt.executeUpdate();
         System.out.println("Update Successful");
    }catch(Exception e){
        
    }
} 
public void updateClient(String chat){
    
   String sql= "update customerinformation set chat_log=? where id='" + CustomerId + "'";
    try{
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, chat);
         pstmt.executeUpdate();
         System.out.println("Update Successful");
    }catch(Exception e){
        
    }
} 
public ResultSet queryAlldataClient(){
    try{
        rs=statement.executeQuery("select chat_log from customerinformation");
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return rs;
}
}
