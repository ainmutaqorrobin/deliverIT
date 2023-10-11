package Runner;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RunnerProfileInterface implements ActionListener 
{
    JFrame frame = new JFrame();
    JLabel registerGui = new JLabel();
    JLabel id = new JLabel("Runner ID :");
    JTextField runnerid = new JTextField();
    JLabel name = new JLabel("Name :");
    JTextField runnername = new JTextField();
    JLabel ic = new JLabel("IC No :");
    JTextField runneric = new JTextField();
    JLabel email = new JLabel("Email :");
    JTextField runneremail = new JTextField();
    JLabel address = new JLabel("Address :");
    JTextField runneraddress = new JTextField();
    JLabel phoneno = new JLabel("Phone No :");
    JTextField runnerphone = new JTextField();
    JLabel plateno = new JLabel("Vehicle No :");
    JTextField runnerplateno = new JTextField();
    JLabel psd = new JLabel("Password :");
    JTextField pswd = new JTextField();
    JButton updatebutton = new JButton("Update");
    JButton view = new JButton("View");
    JButton back = new JButton("Back");
    Color viewbut = new Color(95, 171, 208);
    Color update = new Color(88, 221, 72);
    private String runid;
    
    public RunnerProfileInterface(String runnerID){
        runid = runnerID;
        registerGui.setBounds(110,40,300,35);
        registerGui.setFont(new Font(null,Font.BOLD,25));
        registerGui.setText("Profile Page");
        
        id.setBounds(30,90,100,25);
        runnerid.setBounds(100,90,200,25);
        runnerid.setEditable(false);
         
        name.setBounds(30,120,100,25);
        runnername.setBounds(100,120,200,25);
        
        
        ic.setBounds(30,150,100,25);
        runneric.setBounds(100,150,200,25);
        
        
        email.setBounds(30,180,100,25);
        runneremail.setBounds(100,180,200,25);
        
        
        address.setBounds(30,210,100,25);
        runneraddress.setBounds(100,210,200,25);
        
        
        phoneno.setBounds(30,240,100,25);
        runnerphone.setBounds(100,240,200,25);
        
        
        plateno.setBounds(30,270,100,25);
        runnerplateno.setBounds(100,270,200,25);
        
        
        psd.setBounds(30,300,100,25);
        pswd.setBounds(100,300,200,25);
        
        
        updatebutton.setBounds(170,340,80,25);
        updatebutton.setFocusable(false);
        updatebutton.setBackground(update);
        updatebutton.setForeground(Color.WHITE);
        updatebutton.addActionListener(this);
        
        view.setBounds(70,340,70,25);
        view.setFocusable(false);
        view.setBackground(viewbut);
        view.setForeground(Color.WHITE);
        view.addActionListener(this);
        
        back.setBounds(10,5,70,25);
        back.setFocusable(false);
        back.setBackground(Color.lightGray);
        back.setBorderPainted(false);
        back.setOpaque(false);
        back.setForeground(Color.DARK_GRAY);
        back.addActionListener(this);
                
        frame.add(registerGui);
        frame.add(id);
        frame.add(runnerid);
        frame.add(name);
        frame.add(runnername);
        frame.add(ic);
        frame.add(runneric);
        frame.add(email);
        frame.add(runneremail);
        frame.add(address);
        frame.add(runneraddress);
        frame.add(phoneno);
        frame.add(runnerphone);
        frame.add(plateno);
        frame.add(runnerplateno);
        frame.add(psd);
        frame.add(pswd);
        frame.add(updatebutton);
        frame.add(view);
        frame.add(back);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Runner Profile Interface");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        RunnerController C3 = new RunnerController();
        
        if (e.getSource()== view)
        { 
            C3.viewProfile(runnerid,runnername,runneric,runneremail,runneraddress,runnerplateno,runnerphone,pswd,runid);
        }
        if (e.getSource()== back)
        {
            C3.backWelcome(frame,runid);
        }
        if (e.getSource()==updatebutton){        
            C3.updateProfile(runnerid,runnername,runneric,runneremail,runneraddress,runnerplateno,runnerphone,pswd,runid,frame);
        }
    }
}