package AllGU;

import Data.ConnectBd;
import Defaults.DefaultFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPage implements ActionListener {
    private ImageIcon logimage=new ImageIcon("src/image/promoting.png");
    private JButton loginButton=new JButton("Login");
    private JTextField EmpIDField=new JTextField("");
    private JPasswordField EmpPSField=new JPasswordField();
    private  DefaultFrame frame ;
    private final JLabel pan;
    private JProgressBar bar;




    public LoginPage(){
        frame=new DefaultFrame(350,470);
        frame.setVisible(true);
       this.int_img(frame);
       this.champ_un(frame,"src\\image\\contacts_96px.png",230,"");
       this.champ_un(frame,"src\\image\\key_96px.png",265,"p");

        frame.add(loginButton);
        loginButton.setBounds(95,320,160,25);
        loginButton.setFont(new Font("Mv Boli",Font.PLAIN,21));
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.decode("#4F5D75"));
        pan=new JLabel();
        pan.setBounds(70,320,220,40);

        bar=new JProgressBar();
        pan.add(bar);
        bar.setBounds(0,0,220,30);
        bar.setStringPainted(true);
        bar.setVisible(false);
    }

    private void champ_un(DefaultFrame frame,String url,int y,String p) {
        JLabel EmpIDLabel=new JLabel();
        ImageIcon idimage=new ImageIcon(url);

        frame.add(EmpIDLabel);
        Image im=idimage.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
        idimage=new ImageIcon(im);
        EmpIDLabel.setIcon(idimage);
        EmpIDLabel.setBounds(60,y,25,25);
        if(p.equals("p"))
        {
            frame.add(EmpPSField);
            EmpPSField.setBounds(90,y,170,25);
            EmpPSField.setBorder(BorderFactory.createEmptyBorder());
        }
        else {
            frame.add(EmpIDField);
            EmpIDField.setBounds(90, y, 170, 25);
            EmpIDField.setFont(new Font("Mv Boli", Font.PLAIN, 16));
            EmpIDField.setBorder(BorderFactory.createEmptyBorder());
        }

    }

    private void int_img(DefaultFrame frame){
        JLabel label=new JLabel();
        Image im=logimage.getImage().getScaledInstance(140,140, Image.SCALE_SMOOTH);;
        logimage=new ImageIcon(im);
        frame.add(label);
        label.setIcon(logimage);
        label.setBounds(105,30,200,200);
    }

    public class task extends Thread{
        private String empID;
        public task(String p){
            this.empID=p;

        }
        @Override
        public void run() {
            int i=0;
            while(i<=100) {
                bar.setValue(i);
                try{
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                i+=5;
            }

            try {
                bar.setString("Welcome back!");
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            if(empID.equals("admin")){
            frame.dispose();
            new MenuAdmin();}
            else
            {
                frame.dispose();
                 new Dashboard();
            }



        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton){

             String empPS=String.valueOf(EmpPSField.getPassword());
             String empID=EmpIDField.getText();

            ConnectBd connect=new ConnectBd();
            connect.open();


            if( empID.equals("admin")&&empPS.equals("Admin@123")) {
                frame.add(pan);
                loginButton.setVisible(false);
                bar.setVisible(true);
                task ts=new task(empID);
                ts.start();
                try {
                    ts.wait();

                } catch (Exception ex) {
                    System.out.println(e);
                }


            }
            else if(connect.Login().containsKey(empID)){
                if(connect.Login().get(empID).equals(empPS)) {
                    connect.close();
                    frame.add(pan);
                    loginButton.setVisible(false);
                    bar.setVisible(true);
                    task ts=new task(empID);
                    ts.start();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong password !","Error 404",JOptionPane.WARNING_MESSAGE);
            }
        }

        }

    }

