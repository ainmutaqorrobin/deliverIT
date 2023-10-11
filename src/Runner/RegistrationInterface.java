package Runner;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegistrationInterface implements ActionListener
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
    JButton savebutton = new JButton("Save Info");
    JButton cancel = new JButton("Cancel");
    Color save = new Color(66, 199, 50);
    
    public RegistrationInterface()
    {
        registerGui.setBounds(30,0,300,35);
        registerGui.setFont(new Font(null,Font.PLAIN,25));
        registerGui.setText("Runner Register Page");
        
        id.setBounds(30,60,100,25);
        runnerid.setBounds(100,60,200,25);
         
        name.setBounds(30,90,100,25);
        runnername.setBounds(100,90,200,25);
        
        ic.setBounds(30,120,100,25);
        runneric.setBounds(100,120,200,25);
        
        email.setBounds(30,150,100,25);
        runneremail.setBounds(100,150,200,25);
        
        address.setBounds(30,180,100,25);
        runneraddress.setBounds(100,180,200,25);
        
        phoneno.setBounds(30,240,100,25);
        runnerphone.setBounds(100,240,200,25);
        
        plateno.setBounds(30,210,100,25);
        runnerplateno.setBounds(100,210,200,25);
        
        psd.setBounds(30,270,100,25);
        pswd.setBounds(100,270,200,25);
        
        savebutton.setBounds(90,330,100,25);
        savebutton.setFocusable(false);
        savebutton.setBackground(save);
        savebutton.setForeground(Color.WHITE);
        savebutton.addActionListener(this);
        savebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
               savebutton.setForeground(Color.BLACK);
               savebutton.setBackground(new Color(130, 255, 115));
            }
            @Override
            public void mouseExited(MouseEvent me) {
               savebutton.setForeground(Color.WHITE);
               savebutton.setBackground(save);
            }
        });
        
        cancel.setBounds(210,330,80,25);
        cancel.setFocusable(false);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
               cancel.setForeground(Color.BLACK);
               cancel.setBackground(new Color(237, 97, 97));
            }
            @Override
            public void mouseExited(MouseEvent me) {
               cancel.setBackground(Color.RED);
               cancel.setForeground(Color.WHITE);
            }
      });
        
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
        frame.add(savebutton);
        frame.add(cancel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Registration Interface");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        RunnerController registercontroller = new RunnerController();
        
        if (e.getSource()==savebutton){
            registercontroller.saveNewdata(runnerid,runnername,runneric,runneremail,runneraddress,runnerplateno,runnerphone,pswd,frame);     
        }
        
        if (e.getSource()==cancel){
            registercontroller.cancelRegister(frame); 
        }
    }
}
