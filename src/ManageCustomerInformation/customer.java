package ManageCustomerInformation;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class customer
{   
    public static void main(String[] args) 
    {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/deliverit","root", "");
            System.out.print("Database is connected !\n");
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.print("Not connect to DB - Error:"+e);
        }          
        CustomerLoginInterface login = new CustomerLoginInterface();          
    }
}

