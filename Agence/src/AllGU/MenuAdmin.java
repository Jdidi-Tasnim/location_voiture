package AllGU;

import Defaults.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin implements ActionListener {
    private DefaultFrame frame;
    private JButton DashButton=new JButton("Dashboard");

    private JButton EmpButton=new JButton("Employees");
    public MenuAdmin(){
        frame =new DefaultFrame(215,150);
        frame.setVisible(true);
        frame.add(DashButton);
        DashButton.setBounds(15,15,170,40);
        DashButton.setFont(new Font("Mv Boli",Font.PLAIN,21));
        DashButton.setFocusable(false);
        DashButton.addActionListener(this);
        DashButton.setBackground(Color.decode("#4F5D75"));


        DashButton.setForeground(Color.white);
        DashButton.setHorizontalTextPosition(JButton.LEFT);


        //Empbutton modif
        frame.add(EmpButton);
        EmpButton.setBounds(15,60,170,40);
        EmpButton.setFont(new Font("Mv Boli",Font.PLAIN,21));
        EmpButton.setFocusable(false);
        EmpButton.setForeground(Color.white);
        EmpButton.addActionListener(this);
        EmpButton.setBackground(new Color(146,176,227));
        EmpButton.setForeground(Color.white);
        EmpButton.setHorizontalTextPosition(JButton.LEFT);
        EmpButton.setBackground(Color.decode("#4F5D75"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==EmpButton){
            frame.dispose();
            new EmployeePage();

        }
        if(e.getSource()==DashButton){
            frame.dispose();
            new Dashboard();
        }

    }
}
