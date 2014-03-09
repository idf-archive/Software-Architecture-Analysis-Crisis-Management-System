package cms.model;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class IncidentForm {

	@NotEmpty(message="Cannot be empty")
	private String callerName;
	
	@NotEmpty(message="Cannot be empty")
	@Pattern(regexp="(^$|[0-9]{8})", message="Must be 8 digits")
	private String callerPhone;
	
	@NotEmpty(message="Cannot be empty")
	private String address;
	
	@Pattern(regexp="(^$|[0-9]{6})", message="Must be 6 digits")
	private String postal;
	
	@NotEmpty(message="Cannot be empty")
	private String description;
	
	@NotEmpty(message="Cannot be empty")
	private String type;
	
	@NotEmpty(message="Cannot be empty")
	private String level;
	
	private String operatorUsername;

	public IncidentForm(){
	}
	
	public IncidentForm(String username){
		this.setOperatorUsername(username);
	}

	public IncidentForm(String callerName, String callerPhone,
			String address, String postal, String description, String type,
			String level, String operatorUsername) {
		this.setCallerName(callerName);
		this.setCallerPhone(callerPhone);
		this.setAddress(address);
		this.setPostal(postal);
		this.setDescription(description);
		this.setType(type);
		this.setLevel(level);
		this.setOperatorUsername(operatorUsername);
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getCallerPhone() {
		return callerPhone;
	}

	public void setCallerPhone(String callerPhone) {
		this.callerPhone = callerPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperatorUsername() {
		return operatorUsername;
	}

	public void setOperatorUsername(String operatorUsername) {
		this.operatorUsername = operatorUsername;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
