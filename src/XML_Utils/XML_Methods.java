/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. change
 */

package XML_Utils;


import Rules_Methods.Rule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;



/**
 *
 * @author Alain
 */

public class XML_Methods {
    
        public static boolean validateXMLSchema(URL xsdPath, String xmlPath){
                    
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdPath);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            JOptionPane.showMessageDialog(null,"XML NOT VALID: "+e.getMessage(),"Load Repository",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
        }

        public static boolean saveXMLFile(Document doc, String[] directory){
        
         String xmlPath;  
         if (directory[0]== null){directory[0] = ".";} 
         JFileChooser fc = new JFileChooser(directory[0]);
         fc.setDialogTitle("Specify a XML to save");
         File file = new File("untitled.xml");
         fc.setSelectedFile(file);
         fc.addChoosableFileFilter(new FileFilter() {
         @Override
         public String getDescription() {
         return "XML Documents (*.xml)";
         }
         @Override
         public boolean accept(File f) {
         if (f.isDirectory()) {
            return true;
         } else {
            return f.getName().toLowerCase().endsWith(".xml");
         }
         }
         });
         if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
         xmlPath = fc.getSelectedFile().getAbsolutePath();
         directory[0] = fc.getCurrentDirectory().toString();
         directory[1] = fc.getSelectedFile().getName();
         } else {JOptionPane.showMessageDialog(null, "No file selected.");
                return false;}

         try{
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult streamResult =  new StreamResult(new File(xmlPath));
         transformer.transform(source, streamResult);
         } catch (TransformerException e) {
            Logger.getLogger(XML_Methods.class.getName()).log(Level.SEVERE, null, e);
            return false;
         } 
         return true;
         }
        
        public static Document createXMLDocument(String[] directory){
     
         Document doc = null;   
         directory[1] = "not saved...";         
         try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element elem  = doc.createElement("Repository");
            doc.appendChild(elem);    
            Element elem_date = doc.createElement("Date");
            elem_date.appendChild(doc.createTextNode((new SimpleDateFormat( "yyyy-MM-dd")).format(Calendar.getInstance().getTime())));
            elem.appendChild(elem_date);    
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(XML_Methods.class.getName()).log(Level.SEVERE, null, ex);
            }
         return (doc);
        } 
        
        public static Document loadXMLFile(String[] directory){
         
         
         if (directory[0]== null){directory[0] = ".";} 
         JFileChooser fc = new JFileChooser(directory[0]);
         fc.setDialogTitle("Specify a XML to load");
         fc.addChoosableFileFilter(new FileFilter() {
         @Override
         public String getDescription() {
         return "XML Documents (*.xml)";
         }
         @Override
         public boolean accept(File f) {
         if (f.isDirectory()) {
            return true;
         } else {
            return f.getName().toLowerCase().endsWith(".xml");
         }
         }
         });
         if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {       
           directory[0] = fc.getCurrentDirectory().toString();
           directory[1] = fc.getSelectedFile().getName();
           try {
            File file = new File(fc.getSelectedFile().getAbsolutePath());
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
           } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(XML_Methods.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }            
           
         } else {JOptionPane.showMessageDialog(null, "No file selected.");
                return null;}
         
        }
        
          
        public static Document addNewRuleXML(Document doc, Rule new_rule){
           
            Node repository = doc.getFirstChild();
            Element elem_rule = doc.createElement("Rule");            
            repository.appendChild(elem_rule);
            
            elem_rule.setAttribute("rule_id", "ID_" + System.currentTimeMillis());          
            
            Element elem_date = doc.createElement("Date");
            elem_date.appendChild(doc.createTextNode((new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance().getTime())));
            elem_rule.appendChild(elem_date);
            
            Element elem_active = doc.createElement("Active");
            elem_active.appendChild(doc.createTextNode(new_rule.isActive().toString()));
            elem_rule.appendChild(elem_active);
            
            Element elem_relt = doc.createElement("Relation");
            elem_relt.appendChild(doc.createTextNode(new_rule.getRelation()));
            elem_rule.appendChild(elem_relt);
                        
            Element elem_ant = doc.createElement("Antecedent");            
            elem_ant.appendChild(doc.createTextNode(new_rule.getAntecedent()));
            elem_rule.appendChild(elem_ant);
            
            Element elem_cons = doc.createElement("Consequent");
            elem_cons.appendChild(doc.createTextNode(new_rule.getConsequent()));
            elem_rule.appendChild(elem_cons);
            
            Element elem_type = doc.createElement(new_rule.getType());
            if (null != new_rule.getType())switch (new_rule.getType()) {
                case "Fuzzy_Association_Rule":
                    for (Rule.Att_Linguistic_labels att_llab : new_rule.getRule_attrib_lab()){
                        Element elem_att = doc.createElement("Attribute");
                        
                        Element elem_att_name = doc.createElement("Attribute_Name");
                        elem_att_name.appendChild(doc.createTextNode(att_llab.att_name));
                        elem_att.appendChild(elem_att_name);
                        
                        for (Rule.Linguistic_label lab : att_llab.Linguistic_labels_attribute) {
                            Element elem_lab = doc.createElement("Linguistic_Label");
                            
                            Element elem_lab_name = doc.createElement("Label_Name");
                            elem_lab_name.appendChild(doc.createTextNode(lab.label_name));
                            elem_lab.appendChild(elem_lab_name);
                            
                            Element elem_lab_a = doc.createElement("A");
                            elem_lab_a.appendChild(doc.createTextNode(lab.a.toString()));
                            elem_lab.appendChild(elem_lab_a);
                            
                            Element elem_lab_b = doc.createElement("B");
                            elem_lab_b.appendChild(doc.createTextNode(lab.b.toString()));
                            elem_lab.appendChild(elem_lab_b);
                            
                            Element elem_lab_c = doc.createElement("C");
                            elem_lab_c.appendChild(doc.createTextNode(lab.c.toString()));
                            elem_lab.appendChild(elem_lab_c);
                            
                            Element elem_lab_d = doc.createElement("D");
                            elem_lab_d.appendChild(doc.createTextNode(lab.d.toString()));
                            elem_lab.appendChild(elem_lab_d);
                            
                            elem_att.appendChild(elem_lab);
                        }
                        elem_type.appendChild(elem_att);
                    }    break;
                case "Association_Rule":
                    Element elem_ar = doc.createElement("Association_Rule");
                    elem_ar.appendChild(doc.createTextNode(new_rule.getType()));
                    break;
                case "Approximate_Dependence":
                    Element elem_ad = doc.createElement("Approximate_Dependence");
                    elem_ad.appendChild(doc.createTextNode(new_rule.getType()));
                    break;
            }
                        
         elem_rule.appendChild(elem_type);
         return (doc);
        }
}
    
 
