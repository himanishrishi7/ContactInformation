import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
	
class FirstPage extends JFrame implements ActionListener {
    JLabel l1;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;

    public FirstPage(String t) {
        super(t);
        l1 = new JLabel("Contact Information");
        b1 = new JButton("Add");
        b2 = new JButton("View");
        b3 = new JButton("Search");
        b4 = new JButton("Exit");
        add(l1);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        setLayout(null);

        l1.setBounds(190, 50, 150, 30);
        b1.setBounds(190, 90, 100, 30);
        b2.setBounds(190,130,100,30);
        b3.setBounds(190,170,100,30);
        b4.setBounds(190,210,100,30);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Add")) {
            SecondPage s = new SecondPage("Add to existing contact");
            s.setSize(500, 500);
            s.setVisible(true);

        }
        if(e.getActionCommand().equals("View")){
            Fourthpage f=new Fourthpage("Contact List");
                    f.setSize(500,500);
            f.setVisible(true);
        }
        if(e.getActionCommand().equals("Search")) {
            Thirdpage t=new Thirdpage("Search Contact");
            t.setSize(500,500);
            t.setVisible(true);
        }
        if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
    }
}
    class SecondPage extends JFrame implements ActionListener
    {
        JLabel l1,l2,l3,l4;
        JTextField t1,t2,t3;
        JButton b5,b6;
        public SecondPage(String t)
        {
            l1=new JLabel("Name");
            l2=new JLabel("Mobile Number");
            l3=new JLabel("E-mail");
            l4=new JLabel();
            t1=new JTextField();
            t2=new JTextField();
            t3=new JTextField();
            b5=new JButton("Back");
            b6=new JButton("Add");
            add(l2);
            add(l1);
            add(l3);
            add(t1);
            add(t2);
            add(t3);
            add(b5);
            add(b6);
            add(l4);
            setLayout(null);

            l1.setBounds(50,50,100,30);
            t1.setBounds(160,50,200,30);
            l2.setBounds(50,90,100,30);
            t2.setBounds(160,90,200,30);
            l3.setBounds(50,130,100,30);
            t3.setBounds(160,130,200,30);
            b5.setBounds(50,170,100,30);
            b6.setBounds(160,170,200,30);
            l4.setBounds(50,210,200,30);
            b6.addActionListener(this);
            b5.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Add"))
            {
                try
                {
                    Connection cn;
                    Statement st;
                    int r;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactInfo","root","1234");
                    st=cn.createStatement();
                    r=st.executeUpdate("insert into Numbers values('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"')");
                    if (r>0)
                    {
                        l4.setText("Number Inserted");
                    }
                    else {
                        l4.setText("Error");
                    }
                }
                catch(Exception a)
                {
                    System.out.println(a);
                }
            }
            if(e.getActionCommand().equals("Back"))
            {
               this.setVisible(false);

            }


        }
    }
    class Thirdpage extends JFrame implements ActionListener
    {
        JLabel l1,l2,l3,l4;
        JTextField t1,t2,t3,t4;
        JButton b1,b2;
        public Thirdpage(String t)
        {
            super(t);
            l1=new JLabel("Enter Name:");
            t1=new JTextField();
            t2=new JTextField();
            t3=new JTextField();
            t4=new JTextField();
            l2=new JLabel("Name:");
            l3=new JLabel("Phone Number:");
            l4=new JLabel("E-mail:");
            b1=new JButton("Search");
            b2=new JButton("Back");
            setLayout(null);
            add(t1);
            add(l1);
            add(b1);
            add(b2);

            l1.setBounds(50,50,150,30);
            t1.setBounds(210,50,150,30);
            b2.setBounds(50,90,100,30);
            b1.setBounds(160,90,200,30);

            t2.setBounds(210,130,150,30);

            t3.setBounds(210,170,150,30);

            t4.setBounds(210,210,150,30);

            b1.addActionListener(this);
            b2.addActionListener(this);

        }
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Search")) {
                try {
                    Connection cn;
                    Statement st;
                    ResultSet rs;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactInfo", "root", "1234");
                    st = cn.createStatement();
                    rs = st.executeQuery("Select * from Numbers where name='" + t1.getText() + "'");
                    while (rs.next()) {
                        add(t2);
                        add(t3);
                        add(t4);
                        add(l2);
                        add(l3);
                        add(l4);

                        t2.setText(rs.getString("name"));
                        t3.setText(rs.getString("number"));
                        t4.setText(rs.getString("mail"));
                        l2.setBounds(50, 130, 150, 30);
                        l3.setBounds(50, 170, 150, 30);
                        l4.setBounds(50, 210, 150, 30);
                    }

                } catch (Exception ee) {
                    System.out.println(ee);
                }
            }
            if (e.getActionCommand().equals("Back"))
            {
                this.setVisible(false);
            }

        }
    }
    class Fourthpage extends JFrame
    {
        JTextArea t1;
        public Fourthpage(String t) {
            super(t);
            t1 = new JTextArea();
            add(t1);
            setLayout(null);
            t1.setBounds(50,50,400,400);
            try {
                Connection cn;
                ResultSet rs;
                Statement st;

                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactInfo", "root", "1234");
                st = cn.createStatement();
                rs = st.executeQuery("select * from Numbers");
                while (rs.next()) {
                    t1.append(rs.getString("Name"));t1.append("\n");
                    t1.append(rs.getString("Number"));t1.append("\n");
                    t1.append(rs.getString("mail"));t1.append("\n");
                }
            } catch (Exception a) {
                System.out.println(a);
            }
        }
    }

public class ContactInformation {
    public static void main(String args[])
    {
        FirstPage f=new FirstPage("Contact Information");
        f.setSize(500,500);
        f.setVisible(true);

    }
}
