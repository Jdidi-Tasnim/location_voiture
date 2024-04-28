package AllGU;


import Data.ConnectBd;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CarPage extends JFrame implements ActionListener {
    private  final ImageIcon image=new ImageIcon("src/image/car_48px.png");
    private final JPanel panelWest=new JPanel();
    private  JTable table;
    private final JButton addButton =new JButton();
    private final JButton editButton=new JButton();
    private final JButton deleteButton=new JButton();
    private final JButton closeButton=new JButton();
    private final JButton dispoButton=new JButton();
    private final JButton rentButton=new JButton();
    private final JButton allButton=new JButton();
    private DefaultTableModel model;
    public CarPage(){
        this.setSize(1000,500);
        this.setTitle("Car");
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(image.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
        this.setLocationRelativeTo(null);
        init_menu();
        init_center();
        this.setVisible(true);

    }
    private void init_menu() {
        this.add(panelWest);
        this.setBackground(Color.decode("#ECEFF1"));
        //panelWest.setBackground(Color.white);
        panelWest.setBounds(780, 100, 180, 300);

        addButton.setText("      New       ");
        addButton.setBounds(5,50,180,35);
        addButton.setFocusable(false);
        addButton.setBackground(Color.decode("#4F5D75"));
        addButton.setForeground(Color.white);
        panelWest.add(addButton);
        addButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        addButton.setHorizontalTextPosition(JButton.RIGHT);
        ImageIcon add=new ImageIcon("src/image/add-24.png");
        Image im=add.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        add=new ImageIcon(im);
        addButton.setIcon(add);
        addButton.setHorizontalTextPosition(JButton.RIGHT);
        addButton.addActionListener(this);

        editButton.setText("    Modify     ");
        editButton.setBounds(5,95,180,35);
        editButton.setFocusable(false);
        editButton.setBackground(Color.decode("#4F5D75"));
        editButton.setForeground(Color.white);
        panelWest.add(editButton);
        editButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        editButton.setHorizontalTextPosition(JButton.RIGHT);
        ImageIcon edit=new ImageIcon("src/image/edit-2-24.png");
        im=edit.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        edit=new ImageIcon(im);
        editButton.setIcon(edit);
        editButton.setHorizontalTextPosition(JButton.RIGHT);
        editButton.addActionListener(this);

        deleteButton.setText("     Delete    ");
        deleteButton.setBounds(5,150,180,35);
        deleteButton.setFocusable(false);
        deleteButton.setBackground(Color.decode("#4F5D75"));
        deleteButton.setForeground(Color.white);
        panelWest.add(deleteButton);
        deleteButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        deleteButton.setHorizontalTextPosition(JButton.RIGHT);
        ImageIcon delete=new ImageIcon("src/image/trash-9-24.png");
        im=delete.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        delete=new ImageIcon(im);
        deleteButton.setIcon(delete);
        deleteButton.setHorizontalTextPosition(JButton.RIGHT);
        deleteButton.addActionListener(this);

        dispoButton.setText("  Available  ");
        dispoButton.setBounds(5,200,180,35);
        dispoButton.setFocusable(false);
        dispoButton.setBackground(Color.decode("#4F5D75"));
        dispoButton.setForeground(Color.white);
        panelWest.add(dispoButton);
        dispoButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        dispoButton.setHorizontalTextPosition(JButton.RIGHT);
        ImageIcon close=new ImageIcon("src/image/available-updates-32.png");
        im=close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        close=new ImageIcon(im);
        dispoButton.setIcon(close);
        dispoButton.setHorizontalTextPosition(JButton.RIGHT);
        dispoButton.addActionListener(this);

        rentButton.setText("    Rented    ");
        rentButton.setBounds(5,250,180,35);
        rentButton.setFocusable(false);
        rentButton.setBackground(Color.decode("#4F5D75"));
        rentButton.setForeground(Color.white);
        panelWest.add(rentButton);
        rentButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        rentButton.setHorizontalTextPosition(JButton.RIGHT);
         close=new ImageIcon("src/image/buy-32.png");
        im=close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        close=new ImageIcon(im);
        rentButton.setIcon(close);
        rentButton.setHorizontalTextPosition(JButton.RIGHT);
        rentButton.addActionListener(this);

        closeButton.setText("      Quit       ");
        closeButton.setBounds(5,300,180,35);
        closeButton.setFocusable(false);
        closeButton.setBackground(Color.decode("#4F5D75"));
        closeButton.setForeground(Color.white);
        panelWest.add(closeButton);
        closeButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        closeButton.setHorizontalTextPosition(JButton.RIGHT);
         close=new ImageIcon("src/image/close-window-24.png");
        im=close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        close=new ImageIcon(im);
        closeButton.setIcon(close);
        closeButton.setHorizontalTextPosition(JButton.RIGHT);
        closeButton.addActionListener(this);

        allButton.setText("              ");
        allButton.setBounds(10,350,35,35);
        allButton.setFocusable(false);
        allButton.setBackground(Color.decode("#4F5D75"));
        //allButton.setForeground(Color.white);
        panelWest.add(allButton);
        //allButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        allButton.setHorizontalTextPosition(JButton.RIGHT);
         close=new ImageIcon("src/image/available-updates-32.png");
        im=close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        close=new ImageIcon(im);
        allButton.setIcon(close);
        allButton.setHorizontalTextPosition(JButton.RIGHT);
        allButton.addActionListener(this);


    }
    private void init_center(){
         model = new DefaultTableModel(0, 9);
        model.setColumnIdentifiers(new String[]{"Matricule","marque", "couleur", "Annee", "Prix par jour", "etat", "carburant", "auto/manu","ID"});
       ConnectBd cnx=new ConnectBd();
       cnx.open();
       cnx.aff_voiture(model,"");

       cnx.close();

        table = new JTable(model);
        this.add(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBounds(0,50,500,400);
        table.setRowHeight(27);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(237, 237, 233) : Color.WHITE);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        scrollPane.setBounds(30,50,680,400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==closeButton) {
            this.dispose();
            new Dashboard();
        }
        if(e.getSource()==addButton){
            new FormVoiture();


        }
        if(e.getSource()==rentButton) {
            model.setNumRows(0);
            ConnectBd cnx=new ConnectBd();
            cnx.open();
            cnx.aff_voiture(this.model,"rented");

            cnx.close();
        }
        if(e.getSource()==dispoButton){
            model.setNumRows(0);
            ConnectBd cnx=new ConnectBd();
            cnx.open();
            cnx.aff_voiture(this.model,"available");

            cnx.close();
        }
        if(e.getSource()==allButton)
        {model.setNumRows(0);
            ConnectBd cnx=new ConnectBd();
            cnx.open();
            cnx.aff_voiture(this.model,"");

            cnx.close();

        }
        if(e.getSource()==deleteButton) {

            if(table.getRowCount()==0) {
                JOptionPane.showMessageDialog(this,"Table is empty");
            }
            else {
                if (table.getSelectedColumnCount() == 1) {
                    String mat=table.getModel().getValueAt(table.getSelectedRow(),0).toString();
                    ConnectBd cnx=new ConnectBd();
                    cnx.open();
                    cnx.delete("delete from voiture where Matricule = '"+mat+"' ;");
                    cnx.close();
                    model.removeRow(table.getSelectedRow());
                }
                else {
                    JOptionPane.showMessageDialog(this,"Please select a ligne!");
                }
            }
        }
        if(e.getSource()==editButton){
            if(table.getRowCount()==0) {
                JOptionPane.showMessageDialog(this,"Table is empty");
            }
            else {
                if (table.getSelectedRowCount() == 1) {
                    String mat=table.getModel().getValueAt(table.getSelectedRow(),0).toString();
                    String prix=table.getModel().getValueAt(table.getSelectedRow(),1).toString();
                    String etat=table.getModel().getValueAt(table.getSelectedRow(),2).toString();
                    String marq=table.getModel().getValueAt(table.getSelectedRow(),3).toString();
                    String carb=table.getModel().getValueAt(table.getSelectedRow(),4).toString();
                    String trans=table.getModel().getValueAt(table.getSelectedRow(),5).toString();
                    String coul=table.getModel().getValueAt(table.getSelectedRow(),6).toString();
                    String annee=table.getModel().getValueAt(table.getSelectedRow(),7).toString();
                    String ID=table.getModel().getValueAt(table.getSelectedRow(),8).toString();
                    System.out.println(annee);

                    ConnectBd cnx=new ConnectBd();
                    cnx.open();
                    cnx.delete("update voiture set Prix_par_jour=" +prix+ ", Etat='"+etat+"' where Matricule = '"+mat+"' ;");
                    cnx.delete("update modele set Marque='"+marq+"',Carburant='"+carb+"', Transmission='"+trans+"',Couleur='"+coul +"', Année="+annee.charAt(0)+annee.charAt(1)+annee.charAt(2)+annee.charAt(3)+" where ID = "+ID+" ;");
                    cnx.close();
                    JOptionPane.showMessageDialog(this,"Success");
                }
                else {
                    JOptionPane.showMessageDialog(this,"Please select a ligne!");
                }
            }

        }
    }

}


