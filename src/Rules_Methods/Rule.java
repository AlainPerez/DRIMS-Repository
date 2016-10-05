/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rules_Methods;

import java.util.ArrayList;

/**
 *
 * @author Alain
 */
public class Rule {
    private Boolean active;
    private String relation, antecedent, consequent, type;    
    private ArrayList<Att_Linguistic_labels> rule_attrib_lab;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public void setConsequent(String consequent) {
        this.consequent = consequent;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRule_attrib_lab(ArrayList<Att_Linguistic_labels> rule_attrib_lab) {
        this.rule_attrib_lab = rule_attrib_lab;
    }

    public Boolean isActive() {
        return active;
    }

    public String getRelation() {
        return relation;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public String getConsequent() {
        return consequent;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Att_Linguistic_labels> getRule_attrib_lab() {
        return rule_attrib_lab;
    }   
    
    public Rule() {
        this.type = "Association_Rule";
        this.consequent = null;
        this.antecedent = null;
        this.rule_attrib_lab = new ArrayList<>();
        this.relation = null;
        this.active = false;
    }
    
    public static class  Att_Linguistic_labels {
      public  String att_name;  
      public  ArrayList<Linguistic_label> Linguistic_labels_attribute;      
    }
    
    public static class  Linguistic_label {
      public String label_name;
      public Double a, b, c, d;        
    }
    
    public int find_att_ll(String att){
        int i = 0;
        for (Att_Linguistic_labels att_ll : rule_attrib_lab) {
        if (!att_ll.att_name.equals(att)) {i++;}
        else {return i;}
        }
        return -1;
    }
    
    public int find_label(int i, String l_name){
        int j = 0;
        for (Linguistic_label lab : rule_attrib_lab.get(i).Linguistic_labels_attribute) {
        if (!lab.label_name.equals(l_name)) {j++;}
        else {return j;}
        }
        return -1;
    }
    
    public boolean new_attribute_ling_label(String attribute, String l_name, double a1, double b1,double c1,double d1) {
     Linguistic_label ll = new Linguistic_label();
     ll.a = a1; ll.b = b1; ll.c = c1; ll.d = d1;
     ll.label_name = l_name;
     int i = find_att_ll(attribute);     
     if (i == -1) {Att_Linguistic_labels all = new Att_Linguistic_labels();
                  all.att_name = attribute;
                  all.Linguistic_labels_attribute = new ArrayList<>();
                  all.Linguistic_labels_attribute.add(ll);
                  rule_attrib_lab.add(all);
                  return true;
     } else {  
         int j = find_label(i, l_name);
         if ( j == -1) {rule_attrib_lab.get(i).Linguistic_labels_attribute.add(ll); return true;}
         else return false;}
    }
    
    public void del_ling_label(String attribute, String l_name){
    int i = find_att_ll(attribute);
    if (i != -1) { int j = find_label(i, l_name);
        rule_attrib_lab.get(i).Linguistic_labels_attribute.remove(j);
    }
    }
    
}