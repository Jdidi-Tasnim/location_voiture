package AllGU;

import Data.ConnectBd;
import Defaults.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Defaults.Verification.*;

public class FormClient implements ActionListener {
    private final DefaultFrame frame;
    private JButton submitButton,resetButton;
    private JTextField cinTextField,numPermisTextField,nameTextField,prenomTextField,numtelTextField,passeportTextField,natTextField,adrTextField;
    public FormClient(){
        JPanel panel=new JPanel();
        frame=new DefaultFrame(400,550);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel.setLayout(null);
        panel.setBackground(Color.decode("#ECEFF1"));

        JLabel  num_permisLabel=new JLabel("Num Permis:");
        num_permisLabel.setBounds(50, 50, 150, 30);
        frame.add(num_permisLabel);
       numPermisTextField = new JTextField();
        numPermisTextField.setBounds(200,50,150,30);
        frame.add(numPermisTextField);

        JLabel nameLabel=new JLabel("Nom:");
        nameLabel.setBounds(50, 95, 150, 30);
        frame.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setBounds(200,95,150,30);
        frame.add(nameTextField);


        JLabel prenomLabel = new JLabel("Prenom:");
        prenomLabel.setBounds(50, 140, 150, 30);
        frame.add(prenomLabel);
        prenomTextField = new JTextField();
        prenomTextField.setBounds(200,140,150,30);
        frame.add(prenomTextField);


        JLabel cinLabel = new JLabel("Cin:");
        cinLabel.setBounds(50, 185, 150, 30);
        frame.add(cinLabel);
         cinTextField = new JTextField();
        cinTextField.setBounds(200,185,150,30);
        frame.add(cinTextField);

        JLabel num_telLabel = new JLabel("Num télephone:");
        num_telLabel.setBounds(50, 230, 150, 30);
        frame.add(num_telLabel);
        numtelTextField =new JTextField();
        numtelTextField.setBounds(200, 230, 150, 30);
        frame.add(numtelTextField);

        JLabel passeportLabel = new JLabel("Passeport:");
        passeportLabel.setBounds(50, 275, 150, 30);
        frame.add(passeportLabel);
        passeportTextField = new JTextField();
        passeportTextField.setBounds(200,275,150,30);
        frame.add(passeportTextField);

        JLabel natLabel = new JLabel("Nationalité:");
        natLabel.setBounds(50, 320, 150, 30);
        frame.add(natLabel);
        natTextField = new JTextField();
        natTextField.setBounds(200,320,150,30);
        frame.add(natTextField);

        JLabel adrLabel = new JLabel("Adresse:");
        adrLabel.setBounds(50, 365, 150,30);
        frame.add(adrLabel);
         adrTextField = new JTextField();
        adrTextField.setBounds(200,365,150,30);
        frame.add(adrTextField);

        submitButton = new JButton("Enregistrer");
        submitButton.setFocusable(false);
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.decode("#4F5D75"));
        submitButton.setBounds(200, 450, 130, 30);
        submitButton.addActionListener(this);
        frame.add(submitButton);

        resetButton = new JButton("Réinitialiser");
        resetButton.setFocusable(false);
        resetButton.setBackground(Color.decode("#4F5D75"));
        resetButton.setForeground(Color.white);
        resetButton.setBounds(50, 450, 130, 30);
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
            String num_permis=numPermisTextField.getText();
            String name=nameTextField.getText();
            String prenom=prenomTextField.getText();
            String num_tel=numtelTextField.getText();
            String pass=passeportTextField.getText();
            String nat=natTextField.getText();
            String adr=adrTextField.getText();

            if(num_permis.length()!=6)
                JOptionPane.showMessageDialog(null,"Longuer permis egal a 6 ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if (!Num(num_permis)) {
                JOptionPane.showMessageDialog(null,"permis doit etre des nombres  ","Error 404",JOptionPane.WARNING_MESSAGE);
            } else if (cnx.rech_permis("select Num_permis from client;",num_permis)) {
                JOptionPane.showMessageDialog(null,"permis existant? ","Error 404",JOptionPane.WARNING_MESSAGE);
                
            } else if (name.length()>15 || name.length()<3) {
                JOptionPane.showMessageDialog(null,"Longeur nom doit etre entre 3 et 15 ","Error 404",JOptionPane.WARNING_MESSAGE);

            }
            else if (prenom.length()>15 || prenom.length()<3) {
                JOptionPane.showMessageDialog(null,"Longeur prenom doit etre entre 3 et 15 ","Error 404",JOptionPane.WARNING_MESSAGE);

            }else if(!Alpha(name))
                JOptionPane.showMessageDialog(null,"Nom contient des numeros? !","Error 404",JOptionPane.WARNING_MESSAGE);
            else if (!Alpha(prenom)) {
                JOptionPane.showMessageDialog(null,"Prenom contient des numeros?","Error 404",JOptionPane.WARNING_MESSAGE);
            }
            else if (cin.length()!=8) {
                JOptionPane.showMessageDialog(null,"Longeur cin doit etre 8 ","Error 404",JOptionPane.WARNING_MESSAGE);
            }
            else if(!Num(cin) || (cin.charAt(0)!='0' && cin.charAt(0)!='1' ))
                JOptionPane.showMessageDialog(null,"Cin doit etre des nombres start with 0 or 1 ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if (cnx.rech_cin("select Cin from client;",cin)) {
                JOptionPane.showMessageDialog(null,"Cin existe deja? ","Error 404",JOptionPane.WARNING_MESSAGE);

            } else if (num_tel.length()!=8) {
                JOptionPane.showMessageDialog(null,"Longeur tel doit etre 8 ","Error 404",JOptionPane.WARNING_MESSAGE);
            }
            else if(!Num(num_tel)  ) {
                JOptionPane.showMessageDialog(null, "tel doit etre des nombres ", "Error 404", JOptionPane.WARNING_MESSAGE);
            } else if (!Alpha(nat))
                JOptionPane.showMessageDialog(null,"Nationalite avec digits? ","Error 404",JOptionPane.WARNING_MESSAGE);
            else if(pass.length()!=0)
            {
                if(!verif(pass) )
                    JOptionPane.showMessageDialog(null,"Numero passport erreur ","Error 404",JOptionPane.WARNING_MESSAGE);
                if(cnx.rech_passport("select Num_Passeport from client;",pass))
                    JOptionPane.showMessageDialog(null,"Numero passport erreur ","Error 404",JOptionPane.WARNING_MESSAGE);
                         
            }
            else{
                 JOptionPane.showMessageDialog(null,"insertion avec success");
                cnx.delete("INSERT INTO client (Num_permis, Nom, Prenom, Cin, Num_tel, Num_Passeport, Nationalite, Adress) VALUES ( '" + num_permis + "' , '" + name + "' , '" + prenom + "','" + cin + "','" + num_tel + "','" + pass + "','" + nat + "','" + adr + "');");
            }

            cnx.close();
        }

    }
}
