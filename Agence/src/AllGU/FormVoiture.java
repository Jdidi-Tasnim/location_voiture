package AllGU;


    import Data.ConnectBd;

    import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

    public class FormVoiture implements ActionListener{
        private JLabel marqueLabel, modeleLabel, anneeLabel, couleurLabel, carburantLabel, prixLabel, transmissionLabel, etatLabel;
        private JTextField marqueField, modeleField, anneeField, couleurField, carburantField, prixField;
        private JRadioButton essenceButton, mazoutButton, courantButton, manuelleButton, automatiqueButton;
        private ButtonGroup carburantGroup, transmissionGroup;
        private JComboBox etatBox, anneeBox;
        private JButton submitButton;
        private JButton infoButton;
        private JFrame frame;

        public FormVoiture()  {
            frame = new JFrame();
            // Initialisation des labels
            marqueLabel = new JLabel("Matricule :");
            modeleLabel = new JLabel("Modèle :");
            anneeLabel = new JLabel("Année :");
            couleurLabel = new JLabel("Couleur :");
            carburantLabel = new JLabel("Carburant :");
            prixLabel = new JLabel("Prix par jour :");
            transmissionLabel = new JLabel("Transmission :");
            etatLabel = new JLabel("État avec carburant :");

            // Initialisation des champs de texte
            marqueField = new JTextField(20);
            modeleField = new JTextField(20);
            String[] annees = new String[20];
            for (int i = 0; i < 20; i++) {
                annees[i] = Integer.toString(2003 + i);
            }
            anneeBox = new JComboBox(annees);

            couleurField = new JTextField(20);
            carburantField = new JTextField(20);
            prixField = new JTextField(10);

            // Initialisation des boutons radio pour le carburant

            // Initialisation des boutons radio pour la transmission
            manuelleButton = new JRadioButton("Manuelle");
            automatiqueButton = new JRadioButton("Automatique");
            transmissionGroup = new ButtonGroup();
            transmissionGroup.add(manuelleButton);
            transmissionGroup.add(automatiqueButton);

            // Initialisation de la liste déroulante pour l'état avec carburant
            String[] etatOptions = {"Essence", "Mazout", "Courant"};
            etatBox = new JComboBox(etatOptions);

            // Initialisation du bouton de soumission
            submitButton = new JButton("Soumettre");
            infoButton =new JButton("Information");


            // Ajout des composants au formulaire
            JPanel formPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(6, 6, 6, 6);

            gbc.gridx = 0;
            gbc.gridy = 0;
            formPanel.add(marqueLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(marqueField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            formPanel.add(modeleLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(modeleField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(anneeLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(anneeBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            formPanel.add(couleurLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(couleurField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            formPanel.add(prixLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(prixField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            formPanel.add(transmissionLabel, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 3;
            formPanel.add(manuelleButton, gbc);
            manuelleButton.setFocusable(false);

            gbc.gridx = 2;
            formPanel.add(automatiqueButton, gbc);
            automatiqueButton.setFocusable(false);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 1;
            formPanel.add(etatLabel, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 3;
            formPanel.add(etatBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            formPanel.add(submitButton, gbc);
            submitButton.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 8;
            gbc.gridwidth = 5;
            formPanel.add(infoButton, gbc);
            infoButton.addActionListener(this);
            submitButton.setEnabled(false);
            infoButton.setBackground(Color.decode("#4F5D75"));
            submitButton.setBackground(Color.decode("#4F5D75"));
            gbc.gridx = 200;
            gbc.gridy = 40;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.NORTH;

            submitButton.setFocusable(false);
            infoButton.setFocusable(false);
            infoButton.setForeground(Color.white);
            submitButton.setForeground(Color.white);

            submitButton.setVisible(true);
            infoButton.setVisible(true);

            frame.add(formPanel);
            formPanel.setVisible(true);
// Configuration de la fenêtre
            frame.setTitle("Formulaire Voiture");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);

            frame.setResizable(false);

            submitButton.addActionListener( this);
            infoButton.addActionListener(this);
            frame.setBackground(Color.decode("#ECEFF1"));
            frame.setVisible(true);
        }

        public void actionPerformed (ActionEvent e) {
            boolean valid = validateForm();

            if (e.getSource() == infoButton) {


                if (valid) {
                    submitButton.setEnabled(true);

                    String marque = marqueField.getText();
                    String modele = modeleField.getText();
                    String annee = (String) anneeBox.getSelectedItem();
                    String couleur = couleurField.getText();
                    String carburant = (String) etatBox.getSelectedItem();
                    String prix = prixField.getText();
                    String transmission = "";
                    if (manuelleButton.isSelected()) {
                        transmission = "Manuelle";
                    } else if (automatiqueButton.isSelected()) {
                        transmission = "Automatique";
                    }
                    String etat = (String) etatBox.getSelectedItem();
                    String infoMessage = "Marque: " + marque + "\nModèle: " + modele + "\nAnnée: " + annee + "\nCouleur: " + couleur + "\nCarburant: " + carburant + "\nPrix par jour: " + prix + "\nTransmission: " + transmission + "\nÉtat avec carburant: " + etat;
                    JOptionPane.showMessageDialog(frame, infoMessage, "Informations sur la voiture", JOptionPane.INFORMATION_MESSAGE);
                    if(e.getSource()==submitButton){
                        ConnectBd cnx=new ConnectBd();
                        cnx.open();
                        //cnx.delete("INSERT INTO voiture VALUES ("+marque+","+prix+ ",'Bonne etat',1)");
                        //cnx.add("")

                        cnx.close();

                        System.out.println("hello");
                    }
                }


            }
        }




        // Méthode pour récupérer la transmission choisie
        private String getTransmission () {
            if (manuelleButton.isSelected()) {
                return "Manuelle";
            } else if (automatiqueButton.isSelected()) {
                return "Automatique";
            } else {
                return "";
            }
        }
        // Vérification finale
        private boolean validateForm () {
            boolean isValid = true;
            String errorMessage = null;
            ConnectBd cnx=new ConnectBd();


            if (marqueField.getText().isEmpty() ) {
                errorMessage="- Le champ 'Matricule' est obligatoire.\n";
                isValid = false;
            }
            if (modeleField.getText().isEmpty()) {
                errorMessage="- Le champ 'Modèle' est obligatoire.\n";
                isValid = false;
            }
            if (anneeBox.getSelectedIndex() == -1) {
                errorMessage = "- Le champ 'Année' est obligatoire.\n";
                isValid = false;
            } else {
                try {
                    Integer.parseInt(anneeBox.getSelectedItem().toString());
                } catch (NumberFormatException e) {
                    errorMessage = "- Le champ 'Année' doit être un nombre entier.\n";
                    isValid = false;
                }
            }

            if (couleurField.getText().isEmpty()) {
                errorMessage="- Le champ 'Couleur' est obligatoire.\n";
                isValid = false;
            }
            if (prixField.getText().isEmpty()) {
                errorMessage="- Le champ 'Prix par jour' est obligatoire.\n";
                isValid = false;
            } else {
                try {
                    Double.parseDouble(prixField.getText());
                } catch (NumberFormatException e) {
                    errorMessage="- Le champ 'Prix par jour' doit être un nombre décimal.\n";
                    isValid = false;
                }
            }
            String carburant = (String) etatBox.getSelectedItem();
            if (carburant.isEmpty()) {
                errorMessage="- Au moins une option d'état avec carburant doit être sélectionnée.\n";
                isValid = false;
            }
            if (getTransmission().isEmpty()) {
                errorMessage="- Au moins une option d'état avec carburant doit être sélectionnée.\n";
                isValid = false;
            }

            if (errorMessage != null) {
                JOptionPane.showMessageDialog(frame, errorMessage, "ERREUR",
                        JOptionPane.ERROR_MESSAGE);
            }

            return errorMessage == null;
        }

    }

