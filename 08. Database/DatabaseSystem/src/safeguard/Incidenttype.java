package safeguard;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the incidenttype database table.
 * 
 */
@Entity
@NamedQuery(name="Incidenttype.findAll", query="SELECT i FROM Incidenttype i")
public class Incidenttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="incidenttypeid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incidenttype_incidenttypeid_seq")
	@javax.persistence.SequenceGenerator(name="incidenttype_incidenttypeid_seq", sequenceName = "incidenttype_incidenttypeid_seq")
	private Integer incidenttypeid;

	private Integer defaultemergencylevel;

	private String incidenttypename;

	//bi-directional many-to-one association to Incident
	@OneToMany(mappedBy="incidenttype")
	private List<Incident> incidents;

	//bi-directional many-to-one association to Agenttype
	@ManyToOne
	private Agenttype agenttype;

	public Incidenttype() {
		
	}
	public Incidenttype(String typeName, Integer defaultemergencylevel,  Agenttype agenttype) {
		this.incidenttypename = typeName;
		this.defaultemergencylevel = defaultemergencylevel;
		this.agenttype = agenttype;
	}

	public Integer getIncidenttypeid() {
		return this.incidenttypeid;
	}

	public void setIncidenttypeid(Integer incidenttypeid) {
		this.incidenttypeid = incidenttypeid;
	}

	public Integer getDefaultEmergencyLevel() {
		return this.defaultemergencylevel;
	}

	public void setDefaultEmergencyLevel(Integer defaultemergencylevel) {
		this.defaultemergencylevel = defaultemergencylevel;
	}

	public String getIncidentTypeName() {
		return this.incidenttypename;
	}

	public void setIncidentTypeName(String incidenttypename) {
		this.incidenttypename = incidenttypename;
	}

	public List<Incident> getIncidents() {
		return this.incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public Incident addIncident(Incident incident) {
		getIncidents().add(incident);
		incident.setIncidenttype(this);

		return incident;
	}

	public Incident removeIncident(Incident incident) {
		getIncidents().remove(incident);
		incident.setIncidenttype(null);

		return incident;
	}

	public Agenttype getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(Agenttype agenttype) {
		this.agenttype = agenttype;
	}

}