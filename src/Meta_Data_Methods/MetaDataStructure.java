package Meta_Data_Methods;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class MetaDataStructure{
    
   private ArrayList<Table> tables;
   
   public MetaDataStructure(Connection connection, String management) throws SQLException{
       
       DatabaseMetaData metaData = connection.getMetaData();
       ResultSet tablas  = metaData.getTables(connection.getCatalog(), "%", "%", null);
       fillTables(tablas, connection, management);
       fillPK(connection);
   }

    private void fillTables(ResultSet TABLES, Connection connection, String management) throws SQLException {

        tables = new ArrayList<>();
        Statement stmt;
        ResultSet rset;
        while(TABLES.next()){
            if ("TABLE".equals(TABLES.getString(4))){
                Table tmpTable = new Table(TABLES.getString("TABLE_NAME"));  
                ArrayList<Attribute> attTmp = new ArrayList<>();
                stmt = connection.createStatement();        
                String separ = "";
                switch (management) {
                    case "PgSQL":
                        separ="\"";
                        break;
                    case "MySQL":
                        separ="`";
                        break;
                }
                rset = stmt.executeQuery("Select * from "+separ+TABLES.getString("TABLE_NAME")+separ+" limit 0;");             
                ResultSetMetaData  metaData = rset.getMetaData();  
                int NCols = metaData.getColumnCount();   

                for (int i = 1; i <= NCols; i++) {
                    Attribute tmpA = new Attribute(metaData.getColumnLabel(i),metaData.getColumnTypeName(i),String.valueOf(metaData.getPrecision(i)));
                    attTmp.add(tmpA);
                }        
                tmpTable.setAttributes(attTmp);
                tables.add(tmpTable);               
            }
        }
    }
    
    public ArrayList<Table> getTables(){
        return tables;
    }

    private void fillPK(Connection connection) throws SQLException {
        for(Table i : tables ){
            ResultSet rs = connection.getMetaData().getPrimaryKeys(connection.getCatalog(), "", i.getName());
            while (rs.next()) {
                for(Attribute j : i.getAttributes()){                              
                        if (j.getName().equals(rs.getString("column_name"))) {                
                            j.setPK(true);
                        }
                }                
            }
        }       
    }
    
        
    public Attribute getAttribute(String name, String table){
        for(Table i : tables){
            if(i.getName().equals(table)){
                for(Attribute j : i.getAttributes()){
                    if(j.getName().equals(name)){
                        return j;
                    }
                }
            }
        }
        return null;
    }
    
    public Table getTable(String name){
        for(Table i : tables){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }
    
}