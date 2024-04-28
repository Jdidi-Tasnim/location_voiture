package AllGU;

import Data.ConnectBd;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractPage extends JFrame implements ActionListener {
    private ImageIcon image=new ImageIcon("src/image/car_48px.png");
    private JPanel panelWest=new JPanel();
    private JTable table;
    private JButton addButton =new JButton();
    private JButton editButton=new JButton();
    private JButton deleteButton=new JButton();
    private JButton closeButton=new JButton();
    private DefaultTableModel model;
    public ContractPage(){
        this.setSize(900,500);
        this.setTitle("Contract");
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
        panelWest.setBounds(680, 100, 180, 300);

        addButton.setText("  New Contract  ");
        addButton.setBounds(5,50,180,35);
        addButton.setFocusable(false);
        addButton.setBackground(Color.decode("#4F5D75"));
        addButton.setForeground(Color.white);
        panelWest.add(addButton);
        addButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        addButton.setHorizontalTextPosition(JButton.RIGHT);
        ImageIcon add=new ImageIcon("src/image/add-file-24.png");
        Image im=add.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        add=new ImageIcon(im);
        addButton.setIcon(add);
        addButton.setHorizontalTextPosition(JButton.RIGHT);
        addButton.addActionListener(this);

        editButton.setText("Modify Contract");
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

        deleteButton.setText("Delete Contract");
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
        closeButton.setText("         Quit           ");
        closeButton.setBounds(5,200,180,35);
        closeButton.setFocusable(false);
        closeButton.setBackground(Color.decode("#4F5D75"));
        closeButton.setForeground(Color.white);
        panelWest.add(closeButton);
        closeButton.setFont(new Font("italic",Font.CENTER_BASELINE,18));
        closeButton.setHorizontalTextPosition(JButton.RIGHT);
        ImageIcon close=new ImageIcon("src/image/close-window-24.png");
        im=close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);
        close=new ImageIcon(im);
        closeButton.setIcon(close);
        closeButton.setHorizontalTextPosition(JButton.RIGHT);
        closeButton.addActionListener(this);


    }
    private void init_center(){
         model = new DefaultTableModel(0, 6);
        model.setColumnIdentifiers(new String[]{"CIN", "Matricule ", "Date deb", "Date fin", "Montant totale", "penalite"});
        ConnectBd cnx=new ConnectBd();
        cnx.open();
        cnx.aff_contract(model);

        cnx.close();

        table = new JTable(model);
        this.add(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBounds(0,50,400,400);
        table.setRowHeight(27);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        scrollPane.setBounds(100,70,485,360);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==closeButton) {
            this.dispose();
            new Dashboard();
        }
        if(e.getSource()==addButton){

            new FormLocation();
        }
        if (e.getSource() == deleteButton) {

            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                if (table.getSelectedColumnCount() == 1) {
                    String cin = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
                    String mat = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                    String date_deb = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
                    ConnectBd cnx = new ConnectBd();
                    cnx.open();
                    cnx.delete("delete from location where Cin = '" + cin + "'and  Matricule= '"+mat+"' and date_debut='"+date_deb+"';");
                    cnx.close();
                    model.removeRow(table.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a ligne!");
                }
            }
        }
        if (e.getSource() == editButton) {
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                if (table.getSelectedRowCount() == 1) {
                    String mat = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                    String cin = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
                    String date_deb = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
                    String date_fin= table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
                    String tot = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
                    String pen = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();



                    ConnectBd cnx = new ConnectBd();
                    cnx.open();
                    cnx.delete("update location set  date_fin='"+date_fin+"', Montant_totale="+tot+", Penalite="+pen+" where Cin = '" + cin + "'and  Matricule= '"+mat+"' and date_debut='"+date_deb+"';");
                    cnx.close();
                    JOptionPane.showMessageDialog(this, "Success");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a ligne!");
                }
            }
        }



    }
}
