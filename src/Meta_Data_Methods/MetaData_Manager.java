package Meta_Data_Methods;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class MetaData_Manager {
    
    private Connection connection;
    private String management; 
    private MetaData meta;
    private boolean connected;

    public Connection getConnection() {
        return connection;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public void create(String host, String user, String passwd, String dbase) {
        setConnected(connect(host, user, passwd, dbase));
        fillMetaDataStructure();
    }
    
    public MetaData_Manager(){
        this.connected = false;
    }
    
    public boolean connect(String host, String user, String passwd, String dbase) {
        
        try {
            String conn_gest = "";
            String attb_multiquery = "";
            if ("MySQL".equals(management)) {
                Class.forName("com.mysql.jdbc.Driver");
                conn_gest = "jdbc:mysql://"; 
                attb_multiquery = "?allowMultiQueries=true";}
            else if ("PgSQL".equals(management)) {
                Class.forName("org.postgresql.Driver"); 
                conn_gest = "jdbc:postgresql://";}
            connection = DriverManager.getConnection(conn_gest + host + "/" + dbase + attb_multiquery, user, passwd);
            Statement query = connection.createStatement();
            query.executeQuery("SELECT 1;");
            query.close();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Driver not Found !","ERROR",JOptionPane.ERROR_MESSAGE);        
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"SQL Exception: "+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static ArrayList<String> databases_List(String host, String user, String passwd, String gestor) {
        String conn_gest = "";
        String query = "";
        ArrayList<String> result = new ArrayList<>();
        try {
            if ("MySQL".equals(gestor)) {
                Class.forName("com.mysql.jdbc.Driver");
                conn_gest = "jdbc:mysql://";
                query = "show databases;";}
            else if ("PgSQL".equals(gestor)) {
                Class.forName("org.postgresql.Driver"); 
                conn_gest = "jdbc:postgresql://";
                query = "SELECT datname FROM pg_database where datacl is null;";}
            Connection connection = DriverManager.getConnection(conn_gest + host + "/", user, passwd);
            Statement stm = connection.createStatement();
            ResultSet rslt = stm.executeQuery(query);            
            while (rslt.next()) {
                 result.add(rslt.getString(1));
            }
            stm.close();
            connection.close();
            return result;    
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Driver not Found !","ERROR",JOptionPane.ERROR_MESSAGE); 
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"SQL Exception: "+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"SQL Exception: "+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fillMetaDataStructure() {
        try {
            meta = new MetaData(connection,management);
        } catch (SQLException ex) {
            connected = false;
            JOptionPane.showMessageDialog(null,"SQL Exception: "+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setConnected(boolean con) {
        connected = con;
    }

    public boolean isConnected() {
        return connected;
    }

    public MetaData getMeta() {
        return meta;
    }
    
    
}
