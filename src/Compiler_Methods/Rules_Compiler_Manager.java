/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compiler_Methods;

import Rules_Methods.Rule;
import Meta_Data_Methods.Attribute;
import Meta_Data_Methods.MetaData_Manager;
import java.util.ArrayList;
import org.stringtemplate.v4.*;


/**
 *
 * @author Alain
 */

public class Rules_Compiler_Manager {

  private Rule compiling_rule; 
  private String id_rule;
  private String TempQ;
  private String antecedent;
  private String consecuent;     
  private Boolean Is_Ant = true;
  private MetaData_Manager meta;
  private ArrayList<String> error_messages;
  private ArrayList<String> ant_att;
  private ArrayList<String> con_att;
  private ArrayList<String> labels_ant_con;
   
  
    public Rules_Compiler_Manager() {
        this.compiling_rule = new Rule(); // fill linguistic labels; // fill type // fill relation
        this.labels_ant_con = new ArrayList<>();
        this.con_att = new ArrayList<>();
        this.ant_att = new ArrayList<>();
        this.error_messages = new ArrayList<>();
        this.TempQ = "";
        this.consecuent = "";
        this.antecedent = "";
    }

    public MetaData_Manager getMeta() {
        return meta;
    }

    public void setMeta(MetaData_Manager meta) {
        this.meta = meta;
    }


    public void setCompiling_rule(Rule compiling_rule) {
        this.compiling_rule = compiling_rule;
    }

    public void setId_rule(String id_rule) {
        this.id_rule = id_rule;
    }

    public void setTempQ(String TempQ) {
        this.TempQ = TempQ;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public void setConsecuent(String consecuent) {
        this.consecuent = consecuent;
    }

    public void setIs_Ant(Boolean Is_Ant) {
        this.Is_Ant = Is_Ant;
    }

    public void setAnt_att(ArrayList<String> ant_att) {
        this.ant_att = ant_att;
    }

    public void setCon_att(ArrayList<String> con_att) {
        this.con_att = con_att;
    }

    public void setLabels_ant_con(ArrayList<String> labels_ant_con) {
        this.labels_ant_con = labels_ant_con;
    }


    public Rule getCompiling_rule() {
        return compiling_rule;
    }

    public String getId_rule() {
        return id_rule;
    }

    public String getTempQ() {
        return TempQ;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public String getConsecuent() {
        return consecuent;
    }

    public Boolean isIs_Ant() {
        return Is_Ant;
    }

    public ArrayList<String> getAnt_att() {
        return ant_att;
    }

    public ArrayList<String> getCon_att() {
        return con_att;
    }

    public ArrayList<String> getLabels_ant_con() {
        return labels_ant_con;
    }
    
    public ArrayList<String> getError_messages() {
        return error_messages;
    }

    public void setError_messages(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
    }
    
    public void Ant_Check() {
        antecedent = TempQ;
        TempQ = "";
        Is_Ant = false;          
    }
    
    public void Con_Check() {
        consecuent = TempQ;
    }
    
    public void Check_INT_ATT(String ID) {  
        
        if (meta.isConnected() && meta.getMeta().getAttribute(ID, compiling_rule.getRelation())!= null) {
            String type = meta.getMeta().getAttribute(ID, compiling_rule.getRelation()).getType();
            switch (meta.getManagement()) {
              case "PgSQL":
                  if (!(type.contains("int")||type.contains("integer")||type.contains("bigint")||type.contains("smallint")||type.contains("smallserial")||type.contains("serial")||type.contains("bigserial")||type.contains("real")||type.contains("double precision")||type.contains("numeric")||type.contains("float")||type.contains("money")))
                     error_messages.add("Attribute \""+ID+"\" is not an integer data type.");   
                  break;
              case "MySQL":
                  if (!(type.contains("INT")||type.contains("TINYINT")||type.contains("SMALLINT")||type.contains("MEDIUMINT")||type.contains("INTEGER")||type.contains("BIGINT")||type.contains("FLOAT")||type.contains("DOUBLE")||type.contains("NUMERIC")||type.contains("DECIMAL")||type.contains("DEC")))
                     error_messages.add("Attribute \""+ID+"\" is not an integer data type.");   
                  break;
            }
        }
    }
            
    public void Check_FLOAT_ATT(String ID) {
       
        if (meta.isConnected() && meta.getMeta().getAttribute(ID, compiling_rule.getRelation())!= null) { 
            String type = meta.getMeta().getAttribute(ID, compiling_rule.getRelation()).getType();
            switch (meta.getManagement()) {
              case "PgSQL":
                  if (!(type.contains("real")||type.contains("double precision")||type.contains("numeric")||type.contains("float")||type.contains("money")))
                     error_messages.add("Attribute \""+ID+"\" is not a float data type.");   
                  break;
              case "MySQL":
                  if (!(type.contains("FLOAT")||type.contains("DOUBLE")||type.contains("NUMERIC")||type.contains("DECIMAL")||type.contains("DEC")))
                     error_messages.add("Attribute \""+ID+"\" is not a float data type.");   
                  break;
            }
        }  
    }
            
    public void Check_STRING_ATT(String ID) {
        
        if (meta.isConnected() && meta.getMeta().getAttribute(ID, compiling_rule.getRelation())!= null) {  
            String type = meta.getMeta().getAttribute(ID, compiling_rule.getRelation()).getType();
            switch (meta.getManagement()) {
              case "PgSQL":
                  if (!(type.contains("character varying")||type.contains("character")||type.contains("char")||type.contains("text")))
                     error_messages.add("Attribute "+ID+" is not a text data type.");   
                  break;
              case "MySQL":
                  if (!(type.contains("CHARACTER VARYING")||type.contains("TINYBLOB")||type.contains("TINYTEXT")||type.contains("BLOB")||type.contains("MEDIUMBLOB")||type.contains("MEDIUMTEXT")||type.contains("LONGBLOB")||type.contains("LONGTEXT")||type.contains("CHARSET")||type.contains("CHARACTER SET")||type.contains("char")||type.contains("VARCHAR")||type.contains("TEXT")||type.contains("ENUM")||type.contains("SET")))
                     error_messages.add("Attribute "+ID+" is not a text data type.");   
                  break;
            }
        } 
    }
    
    public void Add_ID(String ID) {
      if (meta.isConnected() && meta.getMeta().getAttribute(ID, compiling_rule.getRelation())==null) {
            error_messages.add("Attribute \""+ID+"\" not exists in relation "+compiling_rule.getRelation());
      }
      switch (compiling_rule.getType()) {
          case "Association_Rule":
                    if (Is_Ant) ant_att.add(ID);
                    else  {
                        if (ant_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is part of the antecedent and consequent.");
                        con_att.add(ID);
                    }                    
              break;
          case "Approximate_Dependence":
                    if (Is_Ant) {                            
                            if (ant_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is repeatedly defined in the antecedent.");
                            ant_att.add(ID);
                    }
                    else {                        
                        if (con_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is repeatedly defined in the consequent.");
                        if (ant_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is part of the antecedent and consequent.");
                        con_att.add(ID);
                    }

              break;
          case "Fuzzy_Association_Rule":
                    if (Is_Ant) {                            
                            if (ant_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is repeatedly defined in the antecedent.");
                            ant_att.add(ID);
                    }
                    else {                        
                        if (con_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is repeatedly defined in the consequent.");
                        if (ant_att.indexOf(ID) != -1) error_messages.add("Attribute \""+ID+"\" is part of the antecedent and consequent.");
                        con_att.add(ID);
                    }
              break;
      }

    }
    
    public void Check_AB(String A, String B) {
      
       if (Double.compare(Double.valueOf(A),Double.valueOf(B))>0) 
           error_messages.add(""+A+" > "+B+".");
    }
    
    public void Check_ID_Label(String ID, String label) {
        
        Add_ID(ID);        
        label = label.substring(1, label.length()-1);
        labels_ant_con.add(label);
        
        if (!meta.isConnected() || meta.getMeta().getAttribute(ID, compiling_rule.getRelation())!=null) {     
            if (!meta.isConnected()) ID = "";
            int i = compiling_rule.find_att_ll(ID);
            if (i != -1) {
                int j = compiling_rule.find_label(i, label);
                if (j == -1) error_messages.add("Attribute \""+ID+"\" not define a linguistic label "+label+".");
            } else 
                error_messages.add("Attribute \""+ID+"\" not define any linguistic label.");       
        }
        
    }
  public String generate_resources(String method){
   
   String result = "The following resources has been created:\n"+
             " 1. Table(s):\n"+
             "        'data_rules' -- store all rules measures.\n";
   if ("Immediate".equals(method)){
       switch (compiling_rule.getType()) {
            case "Association_Rule":    
             if ("MySQL".equals(meta.getManagement())){result+="        'count_row_"+compiling_rule.getRelation()+"' -- immediate store "+compiling_rule.getRelation()+" row count.\n";}
    result+= " 2. Triggers on table "+compiling_rule.getRelation()+":\n"+
             "        'deleting_"+id_rule+"'  -- update rule measures in 'data_rules' after delete event.\n"+
             "        'inserting_"+id_rule+"' -- update rule measures in 'data_rules' after insert event.\n"+
             "        'updating_"+id_rule+"'  -- update rule measures in 'data_rules' after update event.\n"+
             " 3. View:\n"+
             "        'rules_certainty_factor' -- view all rules certainty factor.\n"+
             " 4. Procedure:\n"+
             "        'measures_update_"+id_rule+"' -- update rule certainty factor for changes.\n";            
            if ("PgSQL".equals(meta.getManagement())){result+=" 5. Sequence:\n        'count_row_"+compiling_rule.getRelation()+"' -- immediate store "+compiling_rule.getRelation()+" row count.\n";}
                break;    
            case "Approximate_Dependence": 
             if ("MySQL".equals(meta.getManagement())){result+="        'count_row_"+compiling_rule.getRelation()+"' -- immediate store "+compiling_rule.getRelation()+" row count.\n";}
    result+= "        'ad_"+id_rule+"_sa'  -- store antecedent instances count.\n"+
             "        'ad_"+id_rule+"_sc'  -- store consequent  instances count.\n"+
             "        'ad_"+id_rule+"_sac' -- store rule instances count.\n"; 
    result+= " 2. Triggers on table "+compiling_rule.getRelation()+":\n"+
             "        'deleting_"+id_rule+"'  -- update instances count in sa,sc,sac tables after delete event.\n"+
             "        'inserting_"+id_rule+"' -- update instances count in sa,sc,sac tables after insert event.\n"+
             "        'updating_"+id_rule+"'  -- update instances count in sa,sc,sac tables after update event.\n"+
             " 3. View:\n"+
             "        'rules_certainty_factor' -- view all rules certainty factor.\n"+
             " 4. Procedure:\n"+
             "        'measures_update_"+id_rule+"' -- update 'data_rules' table and certainty factor from sa,sc,sac tables.\n";            
            if ("PgSQL".equals(meta.getManagement())){result+=" 5. Sequence:\n        'count_row_"+compiling_rule.getRelation()+"' -- immediate store "+compiling_rule.getRelation()+" row count.\n";}                               
                break;
                
            case "Fuzzy_Association_Rule":
            if ("MySQL".equals(meta.getManagement())){result+="        'count_row_"+compiling_rule.getRelation()+"' -- immediate store "+compiling_rule.getRelation()+" row count.\n";}
    result+= " 2. Triggers on table "+compiling_rule.getRelation()+":\n"+
             "        'deleting_"+id_rule+"'  -- update, with member functions, rule measures in 'data_rules' after delete event.\n"+
             "        'inserting_"+id_rule+"' -- update, with member functions, rule measures in 'data_rules' after insert event.\n"+
             "        'updating_"+id_rule+"'  -- update, with member functions, rule measures in 'data_rules' after update event.\n"+
             " 3. View:\n"+
             "        'rules_certainty_factor' -- view all rules certainty factor.\n"+
             " 4. Functions:\n"+
             "        'measures_update_"+id_rule+"' -- update rule certainty factor for changes.\n"; 
          int cont = 0;  
          String attribute = "";
          for (String lab : labels_ant_con) {
            if (cont < ant_att.size()) attribute = ant_att.get(cont);
            else attribute = con_att.get(cont-ant_att.size()); 
    result+= "        '"+attribute+"_"+lab+"' -- member function of linguistic label "+lab+" for attribute"+attribute+".\n";                           
            cont++;
          }    
            if ("PgSQL".equals(meta.getManagement())){result+=" 5. Sequence:\n        'count_row_"+compiling_rule.getRelation()+"' -- immediate store "+compiling_rule.getRelation()+" row count.\n";}           
                break;
       }                 
   }
   else {
       switch (compiling_rule.getType()) {
            case "Association_Rule":    
             if ("MySQL".equals(meta.getManagement())){result+="        'count_row_"+compiling_rule.getRelation()+"' -- deferred store "+compiling_rule.getRelation()+" row count.\n";}
    result+= "        'it_"+compiling_rule.getRelation()+"' -- store inserted/updated relevant instances in "+compiling_rule.getRelation()+".\n"+
             "        'dt_"+compiling_rule.getRelation()+"' -- store deleted/updated  instances in "+compiling_rule.getRelation()+".\n";
    result+= " 2. Triggers on table "+compiling_rule.getRelation()+":\n"+
             "        'deleting'  -- implement politics for structural events interactions after a delete event.\n"+
             "        'inserting' -- implement politics for structural events interactions after an insert event.\n"+
             "        'updating'  -- implement politics for structural events interactions after an update event.\n"+
             " 3. View:\n"+
             "        'rules_certainty_factor' -- view all rules certainty factor.\n"+
             " 4. Procedure:\n"+
             "        'measures_update_"+id_rule+"' -- update 'data_rules' table and certainty factor from it_/dt_"+compiling_rule.getRelation()+".\n";            
            if ("PgSQL".equals(meta.getManagement())){result+=" 5. Sequence:\n        'count_row_"+compiling_rule.getRelation()+"' -- deferred store "+compiling_rule.getRelation()+" row count.\n";}
                break;    
            case "Approximate_Dependence": 
             if ("MySQL".equals(meta.getManagement())){result+="        'count_row_"+compiling_rule.getRelation()+"' -- deferred store "+compiling_rule.getRelation()+" row count.\n";}
    result+= "        'it_"+compiling_rule.getRelation()+"' -- store inserted/updated relevant instances in "+compiling_rule.getRelation()+".\n"+
             "        'dt_"+compiling_rule.getRelation()+"' -- store deleted/updated  instances in "+compiling_rule.getRelation()+".\n";             
    result+= "        'ad_"+id_rule+"_sa'  -- deferred store antecedent instances count.\n"+
             "        'ad_"+id_rule+"_sc'  -- deferred store consequent  instances count.\n"+
             "        'ad_"+id_rule+"_sac' -- deferred store rule instances count.\n"; 
    result+= " 2. Triggers on table "+compiling_rule.getRelation()+":\n"+
             "        'deleting'  -- implement politics for structural events interactions after a delete event.\n"+
             "        'inserting' -- implement politics for structural events interactions after an insert event.\n"+
             "        'updating'  -- implement politics for structural events interactions after an update event.\n"+
             " 3. View:\n"+
             "        'rules_certainty_factor' -- view all rules certainty factor.\n"+
             " 4. Procedure:\n"+
             "        'measures_update_"+id_rule+"' -- update sa,sc,sac tables and certainty factor from it_/dt_"+compiling_rule.getRelation()+".\n";            
            if ("PgSQL".equals(meta.getManagement())){result+=" 5. Sequence:\n        'count_row_"+compiling_rule.getRelation()+"' -- deferred store "+compiling_rule.getRelation()+" row count.\n";}                               
                break;
                
            case "Fuzzy_Association_Rule":
            if ("MySQL".equals(meta.getManagement())){result+="        'count_row_"+compiling_rule.getRelation()+"' -- deferred store "+compiling_rule.getRelation()+" row count.\n";}
    result+= "        'it_"+compiling_rule.getRelation()+"' -- store inserted/updated relevant instances in "+compiling_rule.getRelation()+".\n"+
             "        'dt_"+compiling_rule.getRelation()+"' -- store deleted/updated  instances in "+compiling_rule.getRelation()+".\n";              
    result+= " 2. Triggers on table "+compiling_rule.getRelation()+":\n"+
             "        'deleting'  -- implement politics for structural events interactions after a delete event.\n"+
             "        'inserting' -- implement politics for structural events interactions after an insert event.\n"+
             "        'updating'  -- implement politics for structural events interactions after an update event.\n"+
             " 3. View:\n"+
             "        'rules_certainty_factor' -- view all rules certainty factor.\n"+
             " 4. Functions:\n"+
             "        'measures_update_"+id_rule+"' -- update 'data_rules' table and certainty factor with member functions from it_/dt_"+compiling_rule.getRelation()+".\n"; 
          int cont = 0;  
          String attribute = "";
          for (String lab : labels_ant_con) {
            if (cont < ant_att.size()) attribute = ant_att.get(cont);
            else attribute = con_att.get(cont-ant_att.size()); 
    result+= "        '"+attribute+"_"+lab+"' -- member function of linguistic label "+lab+" for attribute"+attribute+".\n";                           
            cont++;
          }    
            if ("PgSQL".equals(meta.getManagement())){result+=" 5. Sequence:\n        'count_row_"+compiling_rule.getRelation()+"' -- deferred store "+compiling_rule.getRelation()+" row count.\n";}           
                break;
       }               
        }           
   return result;
  } 
    
    
    
  @SuppressWarnings("null")
    public String generate_script(String method){
              
      String result = "";
      STGroup group = new  STRawGroupDir("./Templates");
      ST st = null;
          
          if ("MySQL".equals(meta.getManagement()))
              if ("Immediate".equals(method)) 
                    switch (compiling_rule.getType()) {
                    case "Association_Rule":       st = group.getInstanceOf("AR_Immediate_MySQL"); break;
                    case "Approximate_Dependence": st = group.getInstanceOf("AD_Immediate_MySQL"); break;
                    case "Fuzzy_Association_Rule": st = group.getInstanceOf("FAR_Immediate_MySQL");break;
                } else 
                    switch (compiling_rule.getType()) {
                    case "Association_Rule":       st = group.getInstanceOf("AR_Deferred_MySQL"); break;
                    case "Approximate_Dependence": st = group.getInstanceOf("AD_Deferred_MySQL"); break;
                    case "Fuzzy_Association_Rule": st = group.getInstanceOf("FAR_Deferred_MySQL");break;
                    }
          else  if ("Immediate".equals(method))
                    switch (compiling_rule.getType()) {
                    case "Association_Rule":       st = group.getInstanceOf("AR_Immediate_PgSQL"); break;
                    case "Approximate_Dependence": st = group.getInstanceOf("AD_Immediate_PgSQL"); break;
                    case "Fuzzy_Association_Rule": st = group.getInstanceOf("FAR_Immediate_PgSQL");break;
                } else 
                    switch (compiling_rule.getType()) {
                    case "Association_Rule":       st = group.getInstanceOf("AR_Deferred_PgSQL"); break;
                    case "Approximate_Dependence": st = group.getInstanceOf("AD_Deferred_PgSQL"); break;
                    case "Fuzzy_Association_Rule": st = group.getInstanceOf("FAR_Deferred_PgSQL");break;
                    }        

 st.add("rule_id", id_rule);
 st.add("table_name", compiling_rule.getRelation());
 
          
          String separ = "";
          String conv_type = "";
          String create_table_sa = "";
          String create_table_sc = "";
          String create_table_sac = "";
          String create_table_dt = "";
          String create_table_it = "";
          String quotes = "";
          switch (meta.getManagement()) {
                    case "PgSQL":
                        quotes = "'";
                        conv_type = "::double precision";                        
                        separ = "\"";
                        create_table_sa  = "CREATE TABLE IF NOT EXISTS \"ad_"+id_rule+"_sa\"(\n";
                        create_table_sc  = "CREATE TABLE IF NOT EXISTS \"ad_"+id_rule+"_sc\"(\n";
                        create_table_sac = "CREATE TABLE IF NOT EXISTS \"ad_"+id_rule+"_sac\"(\n";
                        create_table_dt  = "CREATE TABLE IF NOT EXISTS \"dt_"+compiling_rule.getRelation()+"\"(\n";
                        create_table_it  = "CREATE TABLE IF NOT EXISTS \"it_"+compiling_rule.getRelation()+"\"(\n";
                    break;
                    case "MySQL":    
                        quotes = "'";
                        conv_type = "";
                        separ = "`";
                        create_table_sa  = "DROP TABLE IF EXISTS `ad_"+id_rule+"_sa`;\nCREATE TABLE `ad_"+id_rule+"_sa`(\n";
                        create_table_sc  = "DROP TABLE IF EXISTS `ad_"+id_rule+"_sc`;\nCREATE TABLE `ad_"+id_rule+"_sc`(\n";
                        create_table_sac = "DROP TABLE IF EXISTS `ad_"+id_rule+"_sac`;\nCREATE TABLE `ad_"+id_rule+"_sac`(\n";
                        create_table_dt  = "DROP TABLE IF EXISTS `dt_"+compiling_rule.getRelation()+"`;\nCREATE TABLE `dt_"+compiling_rule.getRelation()+"`(\n";
                        create_table_it  = "DROP TABLE IF EXISTS `it_"+compiling_rule.getRelation()+"`;\nCREATE TABLE `it_"+compiling_rule.getRelation()+"`(\n";
                    break;
           }
          
//$create_table_sa$
          String type_size = "";
          for (String ant_a : ant_att) {
              create_table_sa += "    "+ separ + ant_a + separ + "  ";
              create_table_sa += meta.getMeta().getAttribute(ant_a, compiling_rule.getRelation()).getType();
              if ("MySQL".equals(meta.getManagement())) create_table_sa += "  ("+meta.getMeta().getAttribute(ant_a, compiling_rule.getRelation()).getSize()+"),\n";
              else create_table_sa += ",\n";
          }
          create_table_sa += "    "+separ+"support"+separ+" bigint,\n";
          switch (meta.getManagement()) {
                case "PgSQL":
                    create_table_sa += "    CONSTRAINT \"ad_"+id_rule+"_sa_pk\" PRIMARY KEY (";
                break;
                case "MySQL":
                    create_table_sa += "    PRIMARY KEY (";
                break;
          } 
          for (int i = 0; i < ant_att.size(); i++) {
              create_table_sa += separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  create_table_sa += ", ";
              else create_table_sa += ")";
          }
          create_table_sa += "\n);\n\n";
 st.add("create_table_sa", create_table_sa);
          
 //$create_table_sc$
          for (String con_a : con_att) {
              create_table_sc += "    "+ separ + con_a + separ + "  ";
              create_table_sc += meta.getMeta().getAttribute(con_a, compiling_rule.getRelation()).getType();
              if ("MySQL".equals(meta.getManagement())) create_table_sc += "  ("+meta.getMeta().getAttribute(con_a, compiling_rule.getRelation()).getSize()+"),\n";
              else create_table_sc += ",\n";
          }
            create_table_sc += "    "+separ+"support"+separ+" bigint,\n";
            switch (meta.getManagement()) {
                case "PgSQL":
                    create_table_sc += "    CONSTRAINT \"ad_"+id_rule+"_sc_pk\" PRIMARY KEY (";
                break;
                case "MySQL":
                    create_table_sc += "    PRIMARY KEY (";
                break;
            } 
          for (int i = 0; i < con_att.size(); i++) {
              create_table_sc += separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  create_table_sc += ", ";
              else create_table_sc += ")";
          }
          create_table_sc += "\n);\n\n";             
st.add("create_table_sc", create_table_sc);  

//$create_table_sac$ 
          for (String ant_a : ant_att) {
              create_table_sac += "    "+ separ + ant_a + separ + "  ";
              create_table_sac += meta.getMeta().getAttribute(ant_a, compiling_rule.getRelation()).getType();
              if ("MySQL".equals(meta.getManagement())) create_table_sac += "  ("+meta.getMeta().getAttribute(ant_a, compiling_rule.getRelation()).getSize()+"),\n";
              else create_table_sac += ",\n";
          }
          for (String con_a : con_att) {
              create_table_sac += "    "+ separ + con_a + separ + "  ";
              create_table_sac += meta.getMeta().getAttribute(con_a, compiling_rule.getRelation()).getType();
              if ("MySQL".equals(meta.getManagement())) create_table_sac += "  ("+meta.getMeta().getAttribute(con_a, compiling_rule.getRelation()).getSize()+"),\n";
              else create_table_sac += ",\n";              
          }
          create_table_sac += "    "+separ+"support"+separ+" bigint,\n";
          switch (meta.getManagement()) {
                case "PgSQL":
                    create_table_sac += "    CONSTRAINT \"ad_"+id_rule+"_sac_pk\" PRIMARY KEY (";
                break;
                case "MySQL":
                    create_table_sac += "    PRIMARY KEY (";
                break;
            } 
          for (String ant_a : ant_att) {
              create_table_sac += separ + ant_a + separ + ", ";
          }
          for (int i = 0; i < con_att.size(); i++) {
              create_table_sac += separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  create_table_sac += ", ";
              else create_table_sac += ")";
          }
          create_table_sac += "\n);\n\n";       
st.add("create_table_sac", create_table_sac);     

//$create_table_dt$  
          String tmp_table = "";
          String tmp_pk = "";
          ArrayList<String> pk = new ArrayList<>();
          for (Attribute att : meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes()) {
              tmp_table += "    "+ separ + att.getName() + separ + "  " + att.getType();
              if ("MySQL".equals(meta.getManagement())) tmp_table += "  ("+att.getSize()+"),\n";
              else tmp_table += ",\n";               
              if (att.isPK()) pk.add(separ+att.getName()+separ);
          }
          for (int i = 0; i < pk.size(); i++) {
              if (i != pk.size()-1) tmp_pk += pk.get(i) + ", ";
              else tmp_pk += pk.get(i);
          } 
          create_table_dt += tmp_table;          
            switch (meta.getManagement()) {
                case "PgSQL":
                    create_table_dt += "    CONSTRAINT \"dt_"+id_rule+"\" PRIMARY KEY (";
                break;
                case "MySQL":
                    create_table_dt += "    PRIMARY KEY (";
                break;
            } 
          create_table_dt += tmp_pk + ")\n);\n\n"; 
st.add("create_table_dt", create_table_dt);  

//$create_table_it$
          create_table_it += tmp_table;          
            switch (meta.getManagement()) {
                case "PgSQL":
                    create_table_it += "    CONSTRAINT \"it_"+id_rule+"\" PRIMARY KEY (";
                break;
                case "MySQL":
                    create_table_it += "    PRIMARY KEY (";
                break;
            } 
          create_table_it += tmp_pk + ")\n);\n\n";
st.add("create_table_it", create_table_it);  

//$create_FAR_functions$          
          String create_FAR_functions = "";
          int cont = 0;  
          String attribute = "";
          for (String lab : labels_ant_con) {
            if (cont < ant_att.size()) attribute = ant_att.get(cont);
            else attribute = con_att.get(cont-ant_att.size()); 
            
            String tpe = "";
            tpe = meta.getMeta().getAttribute(attribute, compiling_rule.getRelation()).getType();            
            if ("MySQL".equals(meta.getManagement())) tpe += "("+meta.getMeta().getAttribute(attribute, compiling_rule.getRelation()).getSize()+")";

            int att_ll = compiling_rule.find_att_ll(attribute);            
            int label = compiling_rule.find_label(att_ll,lab);
            String a = compiling_rule.getRule_attrib_lab().get(att_ll).Linguistic_labels_attribute.get(label).a.toString();
            String b = compiling_rule.getRule_attrib_lab().get(att_ll).Linguistic_labels_attribute.get(label).b.toString();
            String c = compiling_rule.getRule_attrib_lab().get(att_ll).Linguistic_labels_attribute.get(label).c.toString();
            String d = compiling_rule.getRule_attrib_lab().get(att_ll).Linguistic_labels_attribute.get(label).d.toString();
            
            switch (meta.getManagement()) {
                    case "PgSQL":
                        create_FAR_functions += "CREATE OR REPLACE FUNCTION \""+attribute+"_"+lab+"\"(x "+tpe+")  RETURNS double precision\n LANGUAGE plpgsql AS $$\nBEGIN\n  if (x is null or x <= ";                           
                    break;
                    case "MySQL":
                        create_FAR_functions += "DROP FUNCTION IF EXISTS `"+attribute+"_"+lab+"`;\n"
                                             + "CREATE FUNCTION `"+attribute+"_"+lab+"`(x "+tpe+") RETURNS float\nBEGIN\n  if (x is null or x <= ";                        
                    break;
            }
            
            create_FAR_functions +=    a+") then RETURN 0;\n  else if (x <= "
                                     + b+") then RETURN (x-"+a+")"+conv_type+"/("+b+"-"+a+")"+conv_type+";\n  else if (x <= "
                                     + c+") then RETURN 1;\n  else if (x <= "
                                     + d+") then RETURN ("+d+"-x)"+conv_type+"/("+d+"-"+c+")"+conv_type+";\n  else RETURN 0;\n  end if;\n  end if;\n  end if;\n  end if;";
            switch (meta.getManagement()) {
                    case "PgSQL":
                        create_FAR_functions += "\nEND$$;\n";
                    break;
                    case "MySQL":
                        create_FAR_functions += "\nEND;\n";
                    break;
            }
            cont++;
          }
st.add("create_FAR_functions", create_FAR_functions);  

 //$antecedent_change_UPDATE$
        String antecedent_change_UPDATE="";
        for (int i = 0; i < ant_att.size(); i++) {
              antecedent_change_UPDATE += "(old."+separ+ant_att.get(i)+separ+" <> new."+separ+ant_att.get(i)+separ+") or (old."+separ+ant_att.get(i)+separ+" is null and new."+separ+ant_att.get(i)+separ+" is not null) or (old."+separ+ant_att.get(i)+separ+" is not null and new."+separ+ant_att.get(i)+separ+" is null)";
              if (i != ant_att.size()-1)
                  antecedent_change_UPDATE += " OR ";
        }
st.add("antecedent_change_UPDATE", antecedent_change_UPDATE);          
        
//$consequent_change_UPDATE$
        String consequent_change_UPDATE="";
        for (int i = 0; i < con_att.size(); i++) {
              consequent_change_UPDATE += "(old."+separ+con_att.get(i)+separ+" <> new."+separ+con_att.get(i)+separ+") or (old."+separ+con_att.get(i)+separ+" is null and new."+separ+con_att.get(i)+separ+" is not null) or (old."+separ+con_att.get(i)+separ+" is not null and new."+separ+con_att.get(i)+separ+" is null)";
              if (i != con_att.size()-1)
                  consequent_change_UPDATE += " OR ";
        }
st.add("consequent_change_UPDATE", consequent_change_UPDATE);        


//$ant_rule_expr$   ///definir para data rulesssssssssssss          
        String ant_rule_expr = antecedent.replaceAll("\"", quotes).replaceAll("%MK%", "").replaceAll("%CO%", separ);
        String ant_rule_expr_xx = antecedent.replaceAll("\"", "").replaceAll("'", "").replaceAll("%MK%", "").replaceAll("%CO%", "");
st.add("ant_rule_expr", ant_rule_expr);
st.add("ant_rule_expr_xx", ant_rule_expr_xx);

//$con_rule_expr$
       String con_rule_expr = consecuent.replaceAll("\"", quotes).replaceAll("%MK%", "").replaceAll("%CO%", separ);
       String con_rule_expr_xx = consecuent.replaceAll("\"", "").replaceAll("'", "").replaceAll("%MK%", "").replaceAll("%CO%", "");
st.add("con_rule_expr", con_rule_expr);
st.add("con_rule_expr_xx", con_rule_expr_xx);

//$ant_rule_old_expr$
        String ant_rule_old_expr = antecedent.replaceAll("\"", quotes).replaceAll("%MK%", "old.").replaceAll("%CO%", separ);
st.add("ant_rule_old_expr", ant_rule_old_expr);  

//$ant_rule_new_expr$
        String ant_rule_new_expr = antecedent.replaceAll("\"", quotes).replaceAll("%MK%", "new.").replaceAll("%CO%", separ);
st.add("ant_rule_new_expr", ant_rule_new_expr);  

//$con_rule_new_expr$        
        String con_rule_new_expr = consecuent.replaceAll("\"", quotes).replaceAll("%MK%", "new.").replaceAll("%CO%", separ);
st.add("con_rule_new_expr", con_rule_new_expr);  

//$con_rule_old_expr$        
        String con_rule_old_expr = consecuent.replaceAll("\"", quotes).replaceAll("%MK%", "old.").replaceAll("%CO%", separ);  
st.add("con_rule_old_expr", con_rule_old_expr);  

//$ant_rule_not_null$        
        String ant_rule_not_null="";
        for (int i = 0; i < ant_att.size(); i++) {
              ant_rule_not_null += "("+separ+ant_att.get(i)+separ+" is not null)";
              if (i != ant_att.size()-1)
                  ant_rule_not_null += " AND ";
        }
st.add("ant_rule_not_null", ant_rule_not_null); 

//$con_rule_not_null$        
        String con_rule_not_null="";
        for (int i = 0; i < con_att.size(); i++) {
              con_rule_not_null += "("+separ+con_att.get(i)+separ+" is not null)";
              if (i != con_att.size()-1)
                  con_rule_not_null += " AND ";
        } 
st.add("con_rule_not_null", con_rule_not_null); 

//$ant_rule_old_not_null$        
        String ant_rule_old_not_null="";
        for (int i = 0; i < ant_att.size(); i++) {
              ant_rule_old_not_null += "(old."+separ+ant_att.get(i)+separ+" is not null)";
              if (i != ant_att.size()-1)
                  ant_rule_old_not_null += " AND ";
        }   
st.add("ant_rule_old_not_null", ant_rule_old_not_null); 

//$ant_rule_new_not_null$        
        String ant_rule_new_not_null="";
        for (int i = 0; i < ant_att.size(); i++) {
              ant_rule_new_not_null += "(new."+separ+ant_att.get(i)+separ+" is not null)";
              if (i != ant_att.size()-1)
                  ant_rule_new_not_null += " AND ";
        } 
st.add("ant_rule_new_not_null", ant_rule_new_not_null); 

//$con_rule_new_not_null$        
        String con_rule_new_not_null="";
        for (int i = 0; i < con_att.size(); i++) {
              con_rule_new_not_null += "(new."+separ+con_att.get(i)+separ+" is not null)";
              if (i != con_att.size()-1)
                  con_rule_new_not_null += " AND ";
        } 
st.add("con_rule_new_not_null", con_rule_new_not_null); 

 //$con_rule_old_not_null$        
        String con_rule_old_not_null="";
        for (int i = 0; i < con_att.size(); i++) {
              con_rule_old_not_null += "(old."+separ+con_att.get(i)+separ+" is not null)";
              if (i != con_att.size()-1)
                  con_rule_old_not_null += " AND ";
        } 
st.add("con_rule_old_not_null", con_rule_old_not_null); 

 //$ant_rule_recd_not_null$       
        String ant_rule_recd_not_null="";
        for (int i = 0; i < ant_att.size(); i++) {
              ant_rule_recd_not_null += "(recd."+separ+ant_att.get(i)+separ+" is not null)";
              if (i != ant_att.size()-1)
                  ant_rule_recd_not_null += " AND ";
        }       
st.add("ant_rule_recd_not_null", ant_rule_recd_not_null); 

 //$con_rule_recd_not_null$       
        String con_rule_recd_not_null="";
        for (int i = 0; i < con_att.size(); i++) {
              con_rule_recd_not_null += "(recd."+separ+con_att.get(i)+separ+" is not null)";
              if (i != con_att.size()-1)
                  con_rule_recd_not_null += " AND ";
        }
st.add("con_rule_recd_not_null", con_rule_recd_not_null);

//$table_att_list_new$
        String table_att_list_new = "";
          for (int i = 0; i < meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().size(); i++) {
            table_att_list_new += "new."+separ+meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().get(i).getName()+separ;
              if (i != meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().size()-1)
                  table_att_list_new += ", ";
          }
st.add("table_att_list_new", table_att_list_new);

//$table_att_list_old$
        String table_att_list_old = "";
          for (int i = 0; i < meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().size(); i++) {
            table_att_list_old += "old."+separ+meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().get(i).getName()+separ;
              if (i != meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().size()-1)
                  table_att_list_old += ", ";
          }
st.add("table_att_list_old", table_att_list_old);

//$ant_att_list_recd$        
        String ant_att_list_recd = "";
          for (int i = 0; i < ant_att.size(); i++) {
            ant_att_list_recd += "recd."+separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  ant_att_list_recd += ", ";
          }
st.add("ant_att_list_recd", ant_att_list_recd);

//$con_att_list_recd$        
        String con_att_list_recd = "";
          for (int i = 0; i < con_att.size(); i++) {
            con_att_list_recd += "recd."+separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  con_att_list_recd += ", ";
          }
st.add("con_att_list_recd", con_att_list_recd);

//$ant_att_list$       
        String ant_att_list = "";
          for (int i = 0; i < ant_att.size(); i++) {
            ant_att_list += separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  ant_att_list += ", ";
          } 
st.add("ant_att_list", ant_att_list);

//$con_att_list$       
        String con_att_list = "";
          for (int i = 0; i < con_att.size(); i++) {
            con_att_list += separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  con_att_list += ", ";
          }
st.add("con_att_list", con_att_list);

// $pk_list_new_match$
        String pk_list_new_match = "";
          for (Attribute att : meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes()) {
            if (att.isPK())
              pk_list_new_match += separ+att.getName()+separ + "=new."+ separ+att.getName()+separ+" and ";
          }          
        pk_list_new_match = pk_list_new_match.substring(0, pk_list_new_match.length()-5);
st.add("pk_list_new_match", pk_list_new_match);

// $pk_list_old_match$
        String pk_list_old_match = "";
        for (Attribute att : meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes()) {
            if (att.isPK())
              pk_list_old_match += separ+att.getName()+separ + "=old."+ separ+att.getName()+separ+" and ";
        }          
        pk_list_old_match = pk_list_old_match.substring(0, pk_list_old_match.length()-5);  
st.add("pk_list_old_match", pk_list_old_match);

 //$ant_old_match$       
        String ant_old_match = "";
          for (int i = 0; i < ant_att.size(); i++) {
            ant_old_match += separ+ant_att.get(i)+separ+"=old."+separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  ant_old_match += " and ";
          }
st.add("ant_old_match", ant_old_match);

//$con_old_match$       
        String con_old_match = "";
          for (int i = 0; i < con_att.size(); i++) {
            con_old_match += separ+con_att.get(i)+separ+"=old."+separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  con_old_match += " and ";
          }
st.add("con_old_match", con_old_match);

//$ant_new_match$       
        String ant_new_match = "";
          for (int i = 0; i < ant_att.size(); i++) {
            ant_new_match += separ+ant_att.get(i)+separ+"=new."+separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  ant_new_match += " and ";
          }  
st.add("ant_new_match", ant_new_match);

//$con_new_match$       
        String con_new_match = "";
          for (int i = 0; i < con_att.size(); i++) {
            con_new_match += separ+con_att.get(i)+separ+"=new."+separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  con_new_match += " and ";
          }
st.add("con_new_match", con_new_match);

//$ant_recd_match$       
        String ant_recd_match = "";
          for (int i = 0; i < ant_att.size(); i++) {
            ant_recd_match += separ+ant_att.get(i)+separ+"=recd."+separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  ant_recd_match += " and ";
          }
st.add("ant_recd_match", ant_recd_match);

//$con_recd_match$       
        String con_recd_match = "";
          for (int i = 0; i < con_att.size(); i++) {
            con_recd_match += separ+con_att.get(i)+separ+"=recd."+separ+con_att.get(i)+separ;
              if (i != con_att.size()-1)
                  con_recd_match += " and ";
          }
st.add("con_recd_match", con_recd_match);

//$all_match_new$
        String all_match_new = "";
          for (int i = 0; i < meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().size(); i++) {
            all_match_new += separ+meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().get(i).getName()+separ+"=new."+separ+meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().get(i).getName()+separ;
              if (i != meta.getMeta().getTable(compiling_rule.getRelation()).getAttributes().size()-1)
                  all_match_new += ", ";
          }
st.add("all_match_new", all_match_new);

//$ant_new_list$       
        String ant_new_list = "";
          for (int i = 0; i < ant_att.size(); i++) {
            ant_new_list += "new."+separ+ant_att.get(i)+separ;
              if (i != ant_att.size()-1)
                  ant_new_list += ", ";
          }
st.add("ant_new_list", ant_new_list);

//$con_new_list$       
        String con_new_list = "";
          for (int i = 0; i < con_att.size(); i++) {
            con_new_list += "new."+separ+ant_att.get(i)+separ;
              if (i != con_att.size()-1)
                  con_new_list += ", ";
          }
st.add("con_new_list", con_new_list);

//$ant_FAR_list$
//$con_FAR_list$
//$ant_con_FAR_list_old$
//$ant_con_FAR_list_new$
//$ant_FAR_old_neg$
//$con_FAR_old_neg$
//$ant_FAR_new_pos$
//$con_FAR_new_pos$
      if ("Fuzzy_Association_Rule".equals(compiling_rule.getType()))  {                       
         
          
         String ant_FAR_list = "";  
         String con_FAR_list = "";
         String ant_con_FAR_list = "least(";
         String ant_FAR_list_old = "";
         String con_FAR_list_old = "";
         String ant_con_FAR_list_old = "least(";
         String ant_FAR_list_new = "";         
         String con_FAR_list_new = "";
         String ant_con_FAR_list_new = "least(";
         
         if (ant_att.size()>1) {
             ant_FAR_list = "least(";
             ant_FAR_list_old = "least(";
             ant_FAR_list_new = "least(";
         }
         if (con_att.size()>1) {
             con_FAR_list = "least(";
             con_FAR_list_old = "least(";
             con_FAR_list_new = "least(";
         }         
         
         cont = 0;  
         attribute = "";
          for (String lab : labels_ant_con) {
            if (cont < ant_att.size()) {
                attribute = ant_att.get(cont);
                ant_FAR_list += separ+attribute+"_"+lab+separ+"("+separ+attribute+separ+"), ";
                ant_FAR_list_old += separ+attribute+"_"+lab+separ+"(old."+separ+attribute+separ+"), ";
                ant_FAR_list_new += separ+attribute+"_"+lab+separ+"(new."+separ+attribute+separ+"), ";
                ant_con_FAR_list += separ+attribute+"_"+lab+separ+"("+separ+attribute+separ+"), ";
                ant_con_FAR_list_old += separ+attribute+"_"+lab+separ+"(old."+separ+attribute+separ+"), ";
                ant_con_FAR_list_new += separ+attribute+"_"+lab+separ+"(new."+separ+attribute+separ+"), ";
            }
            else {
                attribute = con_att.get(cont-ant_att.size());
                con_FAR_list += separ+attribute+"_"+lab+separ+"("+separ+attribute+separ+"), ";
                con_FAR_list_old += separ+attribute+"_"+lab+separ+"(old."+separ+attribute+separ+"), ";
                con_FAR_list_new += separ+attribute+"_"+lab+separ+"(new."+separ+attribute+separ+"), ";
                ant_con_FAR_list += separ+attribute+"_"+lab+separ+"("+separ+attribute+separ+"), ";
                ant_con_FAR_list_old += separ+attribute+"_"+lab+separ+"(old."+separ+attribute+separ+"), ";
                ant_con_FAR_list_new += separ+attribute+"_"+lab+separ+"(new."+separ+attribute+separ+"), ";
            }    
            
            cont++;
          }          
          ant_FAR_list = ant_FAR_list.substring(0, ant_FAR_list.length()-2);
          con_FAR_list = con_FAR_list.substring(0, con_FAR_list.length()-2);
          ant_FAR_list_old = ant_FAR_list_old.substring(0, ant_FAR_list_old.length()-2);
          ant_FAR_list_new = ant_FAR_list_new.substring(0, ant_FAR_list_new.length()-2);
          con_FAR_list_old = con_FAR_list_old.substring(0, con_FAR_list_old.length()-2);
          con_FAR_list_new = con_FAR_list_new.substring(0, con_FAR_list_new.length()-2);
          ant_con_FAR_list = ant_con_FAR_list.substring(0, ant_con_FAR_list.length()-2)+")";
          ant_con_FAR_list_old = ant_con_FAR_list_old.substring(0, ant_con_FAR_list_old.length()-2)+")";
          ant_con_FAR_list_new = ant_con_FAR_list_new.substring(0, ant_con_FAR_list_new.length()-2)+")";
         if (ant_att.size()>1) {
             ant_FAR_list += ")";
             ant_FAR_list_old += ")";
             ant_FAR_list_new += ")";
         }
         if (con_att.size()>1) {
             con_FAR_list += ")";
             con_FAR_list_old += ")";
             con_FAR_list_new += ")";
         }                     
st.add("ant_FAR_list", ant_FAR_list);
st.add("con_FAR_list", con_FAR_list);
st.add("ant_FAR_list_old", ant_FAR_list_old);
st.add("ant_FAR_list_new", ant_FAR_list_new);
st.add("con_FAR_list_old", con_FAR_list_old);
st.add("con_FAR_list_new", con_FAR_list_new);
st.add("ant_con_FAR_list", ant_con_FAR_list);
st.add("ant_con_FAR_list_old", ant_con_FAR_list_old);
st.add("ant_con_FAR_list_new", ant_con_FAR_list_new);

         }
//$AD_att_Rule_DEC$
//$AD_ant_att_list$
//$AD_con_att_list$   
//$AD_ant_att_match$
//$AD_con_att_match$           
//$AD_ant_att_not_null$
//$AD_con_att_not_null$         
          String AD_att_Rule_DEC = "";
          String AD_ant_att_list = "";
          String AD_con_att_list = "";
          String AD_ant_att_match = "";
          String AD_con_att_match = "";
          String AD_ant_att_not_null = "";
          String AD_con_att_not_null = "";          
          for (String ant_a : ant_att) {
              AD_ant_att_match += separ+ant_a+separ+"="+separ+"t_"+ant_a+separ+" and "; 
              AD_ant_att_list += separ+"t_"+ant_a+separ+", ";
              AD_ant_att_not_null += "("+separ+"t_"+ant_a+separ+" is not null) and ";
              AD_att_Rule_DEC += "    DECLARE "+separ+"t_"+ant_a+separ+"  ";
              AD_att_Rule_DEC += meta.getMeta().getAttribute(ant_a, compiling_rule.getRelation()).getType();
              if ("MySQL".equals(meta.getManagement())) AD_att_Rule_DEC += "  ("+meta.getMeta().getAttribute(ant_a, compiling_rule.getRelation()).getSize()+");\n";
              else AD_att_Rule_DEC += ";\n";              
          }
          AD_ant_att_list = AD_ant_att_list.substring(0, AD_ant_att_list.length()-2);
          AD_ant_att_match = AD_ant_att_match.substring(0, AD_ant_att_match.length()-5);
          AD_ant_att_not_null = AD_ant_att_not_null.substring(0, AD_ant_att_not_null.length()-5);
          
          for (String con_a : con_att) {
              AD_con_att_match += separ+con_a+separ+"="+separ+"t_"+con_a+separ+" and ";
              AD_con_att_list += separ+"t_"+con_a+separ+", ";
              AD_con_att_not_null += "("+separ+"t_"+con_a+separ+" is not null) and ";
              AD_att_Rule_DEC += "    DECLARE "+separ+"t_"+con_a+separ+"  ";
              AD_att_Rule_DEC += meta.getMeta().getAttribute(con_a, compiling_rule.getRelation()).getType();
              if ("MySQL".equals(meta.getManagement())) AD_att_Rule_DEC += "  ("+meta.getMeta().getAttribute(con_a, compiling_rule.getRelation()).getSize()+");\n";
              else AD_att_Rule_DEC += ";\n"; 
          }          
          AD_con_att_list = AD_con_att_list.substring(0, AD_con_att_list.length()-2);
          AD_con_att_match = AD_con_att_match.substring(0, AD_con_att_match.length()-5);
          AD_con_att_not_null = AD_con_att_not_null.substring(0, AD_con_att_not_null.length()-5);
          
st.add("AD_att_Rule_DEC", AD_att_Rule_DEC);
st.add("AD_ant_att_list", AD_ant_att_list);
st.add("AD_con_att_list", AD_con_att_list);
st.add("AD_ant_att_match", AD_ant_att_match);
st.add("AD_con_att_match", AD_con_att_match);
st.add("AD_ant_att_not_null", AD_ant_att_not_null);
st.add("AD_con_att_not_null", AD_con_att_not_null);


result = st.render();
         
        return result;
    }
   
}