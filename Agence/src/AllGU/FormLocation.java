package AllGU;

import Data.ConnectBd;
import Defaults.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import static Defaults.Verification.Num;
import static Defaults.Verification.verifdate;

public class FormLocation implements ActionListener {
    private final DefaultFrame frame;
    private JButton submitButton,resetButton;
    private JTextField cinTextField,matTextField,finTextField,totTextField,debTextField;
    public FormLocation(){
        JPanel panel=new JPanel();
        frame=new DefaultFrame(400,410);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel.setLayout(null);
        panel.setBackground(Color.decode("#ECEFF1"));

        JLabel  cinLabel=new JLabel("Cin:");
        cinLabel.setBounds(50, 50, 150, 30);
        frame.add(cinLabel);
        cinTextField = new JTextField();
        cinTextField.setBounds(200,50,150,30);
        frame.add(cinTextField);

        JLabel matLabel=new JLabel("Matricule:");
        matLabel.setBounds(50, 95, 150, 30);
        frame.add(matLabel);
        matTextField = new JTextField();
        matTextField.setBounds(200,95,150,30);
        frame.add(matTextField);


        JLabel debLabel = new JLabel("Date debut:");
        debLabel.setBounds(50, 140, 150, 30);
        frame.add(debLabel);
        debTextField = new JTextField();
        debTextField.setBounds(200,140,150,30);
        frame.add(debTextField);


        JLabel finLabel = new JLabel("Date Fin:");
        finLabel.setBounds(50, 185, 150, 30);
        frame.add(finLabel);
        finTextField = new JTextField();
        finTextField.setBounds(200,185,150,30);
        frame.add(finTextField);

        JLabel totLabel = new JLabel("Montant Totale:");
        totLabel.setBounds(50, 230, 150, 30);
        frame.add(totLabel);
        totTextField =new JTextField();
        totTextField.setBounds(200, 230, 150, 30);
        frame.add(totTextField);


        submitButton = new JButton("Enregistrer");
        submitButton.setFocusable(false);
        submitButton.setBackground(Color.decode("#4F5D75"));
        submitButton.setForeground(Color.white);
        submitButton.setBounds(200, 290, 130, 30);
        submitButton.addActionListener(this);
        frame.add(submitButton);

        resetButton = new JButton("Réinitialiser");
        resetButton.setFocusable(false);
        resetButton.setForeground(Color.white);
        resetButton.setBackground(Color.decode("#4F5D75"));
        resetButton.setBounds(50, 290, 130, 30);
        resetButton.addActionListener(this);
        frame.add(resetButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton){

        }
        if (e.getSource()==submitButton) {
            ConnectBd cnx=new ConnectBd();
            cnx.open();
            String cin=cinTextField.getText();
            String mat=matTextField.getText();
            String deb=debTextField.getText();
            String fin=finTextField.getText();
            String tot=totTextField.getText();
            if (!cnx.rech_cin("select Cin from client;",cin)) 
                JOptionPane.showMessageDialog(null,"Cin n'existe pas ? ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if (!cnx.rech_mat("select Matricule from voiture;",mat))
                JOptionPane.showMessageDialog(null,"matricule n'existe pas ? ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if(deb.length()==0)
                JOptionPane.showMessageDialog(null,"Date deb type: YYYY-MM-DD ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if(!verifdate(deb)  )
                JOptionPane.showMessageDialog(null,"Date deb type: YYYY-MM-DD ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if(fin.length()==0)
                JOptionPane.showMessageDialog(null,"Date fin type: YYYY-MM-DD ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if(!verifdate(fin))
                JOptionPane.showMessageDialog(null,"Date fin type: YYYY-MM-DD ","Error 404",JOptionPane.WARNING_MESSAGE);

             else
                {
                    JOptionPane.showMessageDialog(null,"insertion avec success");
                    cnx.delete("INSERT INTO location (Cin, Matricule, date_debut, date_fin, Montant_totale, Penalite) VALUES ( '" + cin + "' , '" + mat + "' , '" + deb + "','" + fin + "'," + tot + ",0);");
                }
            cnx.close();
        }

    }

    public static void main(String[] args) {
        new FormLocation();
    }

}