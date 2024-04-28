package Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashMap;

public class ConnectBd {
    private String username="root";
    private String password="azeqsd123";
    private String url=url="jdbc:mysql://localhost:3306/agence";
    private Connection connection;
    private Statement statement;
    public ConnectBd(){
    }
    public Connection open()  {
        try {
            this.connection=DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            System.out.println(e);
        }

        return this.connection;
    }
    public void close() {
        try {
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
    public HashMap<String,String>Login(){
        HashMap<String,String> logininfo=new HashMap<String,String>();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery("select ID,pass from Employee;");
            while(resultset.next()){
                logininfo.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
         return logininfo;
    }


    public void Top3_Cars(DefaultTableModel mod)
    {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT v.Matricule, v.Prix_par_jour,v.Etat,m.Marque,m.Carburant,m.Transmission,m.Couleur,m.Année, COUNT(*) as Nombre_Locations\n" +
                    "FROM voiture v\n" +
                    "JOIN location l ON v.Matricule = l.Matricule\n" +
                    "Join modele m ON v.ID=m.ID\n" +
                    "GROUP BY v.Matricule\n" +
                    "ORDER BY COUNT(*) DESC\n" +
                    "LIMIT 3;");
            int s;

            while (rs.next()) {

                String Matricule = rs.getString("v.Matricule");
                String prixParJour = String .valueOf(rs.getDouble("v.Prix_par_jour"));
                String etat = rs.getString("v.Etat");
                String marque = rs.getString("m.Marque");
                String year=rs.getString("m.Année");
                String Carburant = rs.getString("m.Carburant");
                String Transmission = rs.getString("m.Transmission");
                String couleur = rs.getString("m.couleur");
                String  nombre_Locations = String .valueOf(rs.getInt(9)); //  recuperation champ n 9
                String tbDataStrings[] ={Matricule,prixParJour, etat,marque, Carburant,Transmission,couleur,year,nombre_Locations};
                mod.addRow(tbDataStrings);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int num(String req){
        int s=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery(req);
            resultset.next();
            s=resultset.getInt(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }

    public String[] client_reg()
    {
        String []t=new String[8];
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery("SELECT c.CIN, c.Nom, c.Prenom,c.Nationalite,c.Num_tel,c.Num_permis,c.Adress, COUNT(*) as Nombre_Locations\n" +
                    "FROM client c\n" +
                    "JOIN location l ON c.Cin = l.Cin\n" +
                    "GROUP BY c.Cin\n" +
                    "ORDER BY COUNT(*) DESC\n" +
                    "LIMIT 1;");
            while(resultset.next()) {
                for (int i = 0; i < 7; i++)
                    t[i] = resultset.getString(i + 1);
                int s = resultset.getInt(8);
                t[7] = Integer.toString(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return t;
    }


 public void aff_voiture(DefaultTableModel mod,String op){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            if(op.equals(""))
             rs = statement.executeQuery("SELECT v.Matricule, v.Prix_par_jour,v.Etat,m.Marque,m.Carburant,m.Transmission,m.Couleur,m.Année,m.ID " +
                    "FROM voiture v " +
                    "Join modele m ON v.ID=m.ID;");
            if(op.equals("rented"))
                rs = statement.executeQuery("SELECT v.Matricule, v.Prix_par_jour,v.Etat,m.Marque,m.Carburant,m.Transmission,m.Couleur,m.Année,m.ID " +
                        "FROM voiture v " +
                        "Join modele m ON v.ID=m.ID "+
                        "WHERE v.Etat='Louee';");

                if(op.equals("available"))
                    rs = statement.executeQuery("SELECT v.Matricule, v.Prix_par_jour,v.Etat,m.Marque,m.Carburant,m.Transmission,m.Couleur,m.Année,m.ID " +
                            "FROM voiture v " +
                            "Join modele m ON v.ID=m.ID "+
                            "WHERE v.Etat='Bonne etat';");


            while (rs.next()) {
                String Matricule = rs.getString("v.Matricule");
                String prixParJour = String .valueOf(rs.getDouble("v.Prix_par_jour"));
                String etat = rs.getString("v.Etat");
                String marque = rs.getString("m.Marque");
                String year=rs.getString("m.Année");
                String Carburant = rs.getString("m.Carburant");
                String Transmission = rs.getString("m.Transmission");
                String couleur = rs.getString("m.couleur");
                String  ID = String .valueOf(rs.getInt("m.ID")); //  recuperation champ n 9
                String tbDataStrings[] ={Matricule,prixParJour, etat,marque, Carburant,Transmission,couleur,year,ID};
                mod.addRow(tbDataStrings);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
    public void aff_client(DefaultTableModel mod){

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from client;");

            while (rs.next()) {

                String per = rs.getString("Num_permis");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String cin= rs.getString("Cin");
                String tel=rs.getString("Num_tel");
                String pass = rs.getString("Num_Passeport");
                String nat = rs.getString("Nationalite");
                String adr = rs.getString("Adress");
                String tbDataStrings[] ={per,nom, prenom,cin, tel,pass,nat,adr};
                mod.addRow(tbDataStrings);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void aff_employee(DefaultTableModel mod){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from employee;");

            while (rs.next()) {

                String id = rs.getString("ID");
                String pss=rs.getString("pass");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String cin= rs.getString("Cin");
                String tel=rs.getString("Num_tel");
                String pass = rs.getString("Num_Passeport");
                String nat = rs.getString("Nationalite");
                String adr = rs.getString("Adress");
                String tbDataStrings[] ={id,pss,nom, prenom,cin, tel,pass,nat,adr};
                mod.addRow(tbDataStrings);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void aff_contract(DefaultTableModel mod){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from location;");

            while (rs.next()) {

                String id = rs.getString("Matricule");
                String cin= rs.getString("Cin");
                String tel=rs.getString("date_debut");
                String pass = rs.getString("date_fin");
                String nat = rs.getString("Montant_totale");
                String adr = rs.getString("Penalite");
                String tbDataStrings[] ={id, cin, tel,pass,nat,adr};
                mod.addRow(tbDataStrings);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public boolean rech_cin(String req, String text) {
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery(req);
            while(resultset.next()){
                if(text.equals(resultset.getString("Cin")))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean rech_permis(String req, String text) {
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery(req);
            while(resultset.next()){
                if(text.equals(resultset.getString("Num_permis")))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean rech_id(String req, String text) {
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery(req);
            while(resultset.next()){
                if(text.equals(resultset.getString("ID")))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean rech_passport(String req, String text) {
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery(req);
            while(resultset.next()){
                if(text.equals(resultset.getString("Num_Passeport")))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }



    public boolean rech_mat(String req, String text) {
        try{
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery(req);
            while(resultset.next()){
                if(text.equals(resultset.getString("Matricule")))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public void delete(String req){
        try{
            PreparedStatement statement=connection.prepareStatement(req);
            statement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public String proc(String cin){
        try{
            CallableStatement s=connection.prepareCall("{call afficher_modele_voiture_allouee_recente('"+cin+"')}");
            ResultSet rs=s.executeQuery();
            rs.next();
                return rs.getString(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"cin non trouvee");
        }
        return "";
    }
}

