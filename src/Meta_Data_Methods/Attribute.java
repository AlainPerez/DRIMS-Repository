package Meta_Data_Methods;

public class Attribute {
    private String name;
    private String type;
    private String size;
    private boolean PK;
    
    
    
    public String getSize() {
        return size;
    }
    
    public Attribute(String name, String type, String size) {
        this.PK = false;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getType(){
        return this.type;
    }

    public void setPK(boolean isPK) {
        this.PK = isPK;
    }


    public boolean isPK() {
        return PK;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
}
