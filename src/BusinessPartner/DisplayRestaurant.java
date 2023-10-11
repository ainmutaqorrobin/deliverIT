package BusinessPartner;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DisplayRestaurant extends JFrame implements ActionListener {

 
    JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox c1;
    JButton b1;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"Restaurant Name", "Restaurant Address", "Restaurant Email", "Restaurant Phone Number"};
    String from;
    static BusinessPartnerController tableshow;
    
    DisplayRestaurant() {
        
        l0 = new JLabel("Fatching Restaurant Information");
        l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l1 = new JLabel("Select Name");
        b1 = new JButton("submit");


        l0.setBounds(100, 50, 350, 40);
        l1.setBounds(75, 110, 75, 20);
        b1.setBounds(150, 150, 150, 20);
        b1.addActionListener(this);

        setTitle("Fetching Restaurant Info From DataBase");
        setLayout(null);
        setVisible(true);
        this.setLocation(100,100);
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

 
        add(l0);
        add(l1);;
        add(b1);

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deliverit", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("select Partner_Name from BusinessPartner");

            Vector v = new Vector();

            while (rs.next()) {

                ids = rs.getString(1);

                v.add(ids);

            }

            c1 = new JComboBox(v);
            c1.setBounds(150, 110, 150, 20);

            add(c1);
            st.close();
            rs.close();

        } catch (Exception e) {

        }

    }

 

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1) {
            BusinessPartnerController C1 = new BusinessPartnerController();
            C1.showTableData(c1);

        }
    }

 

    /*public void showTableData() {

 

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

            pst = con.prepareStatement("select * from BusinessPartner where Partner_Name='" + from + "'");

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

    }*/

 

    public static void main(String args[]) {

        new DisplayRestaurant();

    }

}