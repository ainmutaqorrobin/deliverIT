package CustomerService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import CustomerService.LiveChatInterfaceDeliverit;
import java.sql.ResultSet;
import static CustomerService.LiveChatInterfaceClient.msg_inputC;
import static CustomerService.LiveChatInterfaceClient.msg_areaC;
import static CustomerService.LiveChatInterfaceDeliverit.msg_area;
import static CustomerService.LiveChatInterfaceDeliverit.msg_input;

public class CustomerServiceController {
    public ServerSocket ss;
    public Socket s,s1;
    public DataInputStream dis,dis1;
    public DataOutputStream dout,dout1;
    private String msgin="";
    static db_con db = new db_con();
    public CustomerServiceController(ServerSocket ss,Socket s,DataInputStream dis,DataOutputStream dout){
        LiveChatInterfaceDeliverit.msg_input.setText("");
        this.ss=ss;
        this.s=s;
        this.dis=dis;
        this.dout=dout;
    }
    public CustomerServiceController(Socket s1,DataInputStream dis1,DataOutputStream dout1){
        this.s1=s1;
        this.dis1=dis1;
        this.dout1=dout1;
    }
    public void getMsg(){
        try{
        while(!msgin.equals("exit")){
          msgin=dis.readUTF(); 
            msg_area.setText(LiveChatInterfaceDeliverit.msg_area.getText() + "\n" + "Client: " + msgin);
            db.update(msg_area.getText());
            }
    }
    catch(Exception e){
        
    }
    }
    public void getMsgClient(){
        try{
            while(!msgin.equals("exit")){
            msgin=dis1.readUTF();  //UTF is an object that can hold msg received by the server.
            msg_areaC.setText(msg_areaC.getText() + "\n" + "Deliverit: " + msgin);
            db.updateClient(msg_areaC.getText());
            }
        }catch(Exception e){
            
        }
    }
    public void sendBtn(){
            try{
                String msg="";
                msg=msg_input.getText();
                msg_area.setText(msg_area.getText() + "\n" + "Deliverit: " + msg);
                db.update(msg_area.getText());
                dout.writeUTF(msg);
                msg_input.setText("");
            }catch(Exception e){
                
            }
    }
    public void sendBtnClient(){
          try{
                String msg="";
                msg=msg_inputC.getText();
                msg_areaC.setText(msg_areaC.getText() + "\n" + "Client: " + msg);
                db.update(msg_areaC.getText());
                dout1.writeUTF(msg);
                msg_inputC.setText("");
            }catch(Exception e){
                
            }
    }
    public void displayAll(){
        try{
            
        db.openConnection();
        ResultSet rs;
        rs=db.queryAlldata();
        while(rs.next()){
            msg_area.setText(rs.getString("chat_log"));
        }
         }catch(Exception e){
             
         }
    }
    public void displayAllClient(){
        try{
        db.openConnection();
        ResultSet rs;
        rs=db.queryAlldata();
        while(rs.next()){
            msg_areaC.setText(rs.getString("chat_log"));
        }
        }
        catch(Exception e){
                
                }
    }
  /* public String receiveMsg(DataInputStream dis){
   
        try{
            msgin=dis.readUTF();     //UTF is an object that can hold msg received by the server.
        } 
        catch(Exception e){   
        }
        return msgin;
   }*/
   /*public void receiveMsg1(DataInputStream dis1){
   
   }
      */
  /* public void client_setup(){
     try{

     }catch(Exception e){
       
    }   
   }*/
}
