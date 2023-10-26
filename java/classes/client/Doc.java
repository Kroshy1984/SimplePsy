
package classes.client;


public class Doc{

    private NameDocType docType;
    private String number;
    private String serial;

    public Doc(NameDocType docType, String number, String serial){
        setDocType(docType);
        setNumber(number);
        setSerial(serial);
    }

    public NameDocType getDocType() {
    
        return docType;
    }

    public void setDocType(NameDocType docType) {
    
        this.docType = docType;
        
    }

    public String getNumber() {
    
        return number;
    }

    public void setNumber(String number) {
    
        this.number = number;
        
    }

    public String getSerial() {
    
        return serial;
    }

    public void setSerial(String serial) {
    
        this.serial = serial;
        
    }
    
}