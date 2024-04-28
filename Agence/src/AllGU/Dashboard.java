package AllGU;

import Data.ConnectBd;
import Defaults.CustomButton;
import Defaults.DefaultFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard implements ActionListener {
    private  DefaultFrame frame;
    private  CustomButton Dashboard=new CustomButton("src/image/1.png","Dashboard ", 200,158,35);
    private CustomButton Client=new CustomButton("src/image/3.png","Client     ", 238,158,35);
    private CustomButton Cars=new CustomButton("src/image/car-4-32.png"," Cars      ", 277,158,35);
    private CustomButton Contract=new CustomButton("src/image/6.png","Contract  ", 315,158,35);
    private CustomButton logout=new CustomButton("src/image/logout.png","Log out    ", 480,158,35);
    private JProgressBar bar=new JProgressBar();
    private final JButton latestBut=new JButton("Latest");
    private JTextField latest,marque;
    public Dashboard(){
        frame=new DefaultFrame(1180,580);
        frame.getContentPane().setBackground(Color.decode("#ECEFF1"));

        this.init_menu(frame);
        this.int_top();
        this.init_bot();
        frame.setVisible(true);


    }
    private void init_menu(DefaultFrame frame){
        JPanel panelWest=new JPanel();
        frame.add(panelWest);
        panelWest.setBackground(Color.decode("#d6d7d4"));
        panelWest.setBounds(0,0,160,580);
        panelWest.setLayout(null);


        JLabel label=new JLabel();
        panelWest.add(label);
        label.setBounds(10,30,155,180);
        ImageIcon im=new ImageIcon("src/image/images.png");
        Image trans=im.getImage().getScaledInstance(148,148, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(trans));

        panelWest.add(Dashboard);
        Dashboard.addActionListener(this);

        panelWest.add(Client);
        Client.addActionListener(this);

        panelWest.add(Cars);
        Cars.addActionListener(this);

        panelWest.add(Contract);
        Contract.addActionListener(this);

        panelWest.add(logout);
        logout.addActionListener(this);
    }
    private void init_card(String url,String name,int ...t){
        JPanel panelbot=new JPanel();
        frame.add(panelbot);
        panelbot.setBackground(Color.decode("#d6d7d4"));
        panelbot.setBounds(t[0],100,250,70);

        JLabel pip=new JLabel(name);
        pip.setIcon(new ImageIcon(url));
        pip.setHorizontalTextPosition(JButton.RIGHT);
        pip.setFont(new Font("Mv Boli",Font.PLAIN,20));
        pip.setBounds(10,30,200,30);
        panelbot.add(pip);
         bar=new JProgressBar(0,t[2]);
        bar.setBounds(10,80,200,50);
        bar.setStringPainted(true);
        panelbot.add(bar);
        task ts=new task(bar,t[1],t[2]);
        ts.start();

    }
    public class task extends Thread{
        private JProgressBar bar;
        private  int min,max;
        public task(JProgressBar bar,int min,int max) {

            this.bar=bar;
            this.min=min;
            this.max=max;
        }
        @Override
        public void run() {
            int i=0;
            while(i<=min) {
                bar.setValue(i);
                try{
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                i+=1;
            }
            bar.setString(i-1+"/"+max);
        }
    }
    private void int_top(){
        JLabel lab=new JLabel("Dashboard");
        frame.add(lab);
        lab.setBounds(170,30,350,50);
        lab.setFont(new Font("Mv Boli",Font.PLAIN,23));
        ConnectBd cnx=new ConnectBd();
        cnx.open();
        int max=cnx.num("select count(*) from voiture ");
        int Available=cnx.num("select count(*) from voiture where etat='Bonne etat'");
        int rented=cnx.num("select count(*) from voiture where etat='Louee'");
        int broken=cnx.num("select count(*) from voiture where etat='en panne'");
        int money=cnx.num("select count(*) from location");
        cnx.close();
       this.init_card("src/image/good.png","Available Cars",180,Available,max);
       this.init_card("src/image/Rented.png","Rented Cars",400,rented,max);
       this.init_card("src/image/Panne.png","Broken Cars",640,broken,max);
       this.init_card("src/image/analytics-32.png","All time profits",870,money,20);

    }
    private  void init_bot(){
        JPanel midd=new JPanel();
        frame.add(midd);
        midd.setBackground(Color.white);
        midd.setBounds(165,190,400,50);
        JLabel lab=new JLabel("Top 3 Rented Cars:");
        ImageIcon star=new ImageIcon("src/image/star-6-32.png");
        lab.setIcon(star);
        lab.setBounds(5,5,200,40);
        lab.setHorizontalTextPosition(JButton.LEFT);
        lab.setFont(new Font("Mv Boli",Font.PLAIN,21));
        midd.add(lab);
        JLabel labimg=new JLabel("");
        midd.add(labimg);
        labimg.setIcon(star);
        JLabel labimg2=new JLabel("");
        midd.add(labimg2);
        labimg2.setIcon(star);
        JLabel labimg3=new JLabel("");
        midd.add(labimg3);
        labimg3.setIcon(star);
        JLabel labimg4=new JLabel("");
        midd.add(labimg4);
        labimg4.setIcon(star);
        ConnectBd cnx=new ConnectBd();
        cnx.open();


        DefaultTableModel model = new DefaultTableModel(0, 9);
        model.setColumnIdentifiers(new String[]{"Matricule","prixParJour","etat","marque","Carburant","Transmission","couleur","year","nombre_Locations"});
         cnx.Top3_Cars(model);
        JTable table = new JTable(model);
        frame.add(table);
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
        frame.add(scrollPane);
        scrollPane.setBounds(180,250,650,120);



        JLabel labt=new JLabel("Faithful Client:");
        labt.setBounds(180,370,200,50);
        labt.setFont(new Font("Mv Boli",Font.PLAIN,21));
        frame.add(labt);


        DefaultTableModel modele = new DefaultTableModel(0, 8);
       String[] t = cnx.client_reg();

        modele.setColumnIdentifiers(new String[]{"CIN","PrénomNom", "Prénom", "Nationalité", "Téléphone", "Num Permis", "Adresse", "Nbre de foix"});
        modele.addRow(new Object[]{t[0], t[1], t[2], t[3], t[4], t[5], t[6], t[7]});
        JTable tablee = new JTable(modele);
        frame.add(tablee);
        tablee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablee.setBounds(0,50,400,400);
        tablee.setRowHeight(27);

        JScrollPane scrollPanee = new JScrollPane(tablee);
        frame.add(scrollPanee);
        scrollPanee.setBounds(180,430,610,50);

        JPanel panelTo=new JPanel();
        frame.add(panelTo);
        panelTo.setBackground(Color.white);
        panelTo.setBounds(850,290,250,50);
        JLabel labo=new JLabel("Latest Client Modele:");
        labo.setFont(new Font("Mv Boli",Font.PLAIN,21));
        panelTo.add(labo);


        latestBut.setBounds(1000,350,100,25);
        latestBut.setFocusable(false);
        latestBut.setBackground(Color.decode("#4F5D75"));
        latestBut.setForeground(Color.white);
        latestBut.addActionListener(this);
        frame.add(latestBut);
        latest=new JTextField("");
        latest.setBounds(880,350,120,25);
        frame.add(latest);
        marque=new JTextField();

        marque.setBounds(900,390,150,30);

        cnx.close();


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logout){
            frame.dispose();
            new LoginPage();}
        if(e.getSource()==Dashboard) {
            frame.dispose();
            new Dashboard();
        }
        if(e.getSource()==Cars){
            frame.dispose();
            new CarPage();
        }
        if(e.getSource()==Client){
            frame.dispose();
            new ClientPage();
        }
        if(e.getSource()==Contract){
            frame.dispose();
            new ContractPage();
        }
        if(e.getSource()==latestBut) {
            ConnectBd cnx=new ConnectBd();
            cnx.open();
            frame.add(marque);
            marque.setText(cnx.proc(latest.getText()));
            cnx.close();
        }

    }
}
