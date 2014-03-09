package cms.model;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ResolvedForm {

	@NotEmpty(message="Cannot be empty")
	@Min(value=1, message="Must be numerical digits")
	private String incidentId;
	
	private String operatorUsername;

	public ResolvedForm(){
	}
	
	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getOperatorUsername() {
		return operatorUsername;
	}

	public void setOperatorUsername(String operatorUsername) {
		this.operatorUsername = operatorUsername;
	}

}
