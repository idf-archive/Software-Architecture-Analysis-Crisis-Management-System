package cms.model;

public class IncidentFormBuilder {
	private String callerName;
	private String callerPhone;
	private String address;
	private String postal;
	private String description;
	private String type;
	private String level;
	private String operatorUsername;
	
	
    public IncidentFormBuilder callerName(String callerName) {
        this.callerName = new String(callerName);
        return this;
    }

    public IncidentFormBuilder callerPhone(String callerPhone) {
        this.callerPhone = new String(callerPhone);
        return this;
    }
    
    public IncidentFormBuilder address(String address) {
        this.address =  new String(address);
        return this;
    }
    
    public IncidentFormBuilder postal(String postal) {
        this.postal =  new String(postal);
        return this;
    }
 
    public IncidentFormBuilder description(String description) {
        this.description =  new String(description);
        return this;
    }

    public IncidentFormBuilder type(String type) {
        this.type =  new String(type);
        return this;
    }
    
    
    public IncidentFormBuilder level(String level) {
        this.level =  new String(level);
        return this;
    }
    
    public IncidentFormBuilder operatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
        return this;
    }
    
    
    public IncidentForm build() {
        return new IncidentForm(callerName,callerPhone,address,postal,description,type,level,operatorUsername);
    }

}
