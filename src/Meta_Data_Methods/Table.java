package Meta_Data_Methods;


import java.util.ArrayList;


public class Table{
    
    private String name;
    private ArrayList<Attribute> attributes =  new ArrayList<>();
    
    public Table(String name){
        this.name = name;
    }

 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }
}
