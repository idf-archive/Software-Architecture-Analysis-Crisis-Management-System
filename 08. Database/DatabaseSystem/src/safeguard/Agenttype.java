package safeguard;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the agenttype database table.
 * 
 */
@Entity
@NamedQuery(name="Agenttype.findAll", query="SELECT a FROM Agenttype a")
public class Agenttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="agenttypeid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="agenttype_typeid_seq")
	@javax.persistence.SequenceGenerator(name="agenttype_typeid_seq", sequenceName = "agenttype_typeid_seq")
	private Integer agenttypeid;

	private String typename;

	//bi-directional many-to-one association to Agency
	@OneToMany(mappedBy="agenttype")
	private List<Agency> agencies;

	//bi-directional many-to-one association to Incidenttype
	@OneToMany(mappedBy="agenttype")
	private List<Incidenttype> incidenttypes;

	public Agenttype() {
	}

	public Agenttype(String typeName)
	{
		this.typename = typeName;
		
	}
	public Integer getAgenttypeid() {
		return this.agenttypeid;
	}

	public void setAgenttypeid(Integer agenttypeid) {
		this.agenttypeid = agenttypeid;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public List<Agency> getAgencies() {
		return this.agencies;
	}

	public void setAgencies(List<Agency> agencies) {
		this.agencies = agencies;
	}

	public Agency addAgency(Agency agency) {
		getAgencies().add(agency);
		agency.setAgenttype(this);

		return agency;
	}

	public Agency removeAgency(Agency agency) {
		getAgencies().remove(agency);
		agency.setAgenttype(null);

		return agency;
	}

	public List<Incidenttype> getIncidenttypes() {
		return this.incidenttypes;
	}

	public void setIncidenttypes(List<Incidenttype> incidenttypes) {
		this.incidenttypes = incidenttypes;
	}

	public Incidenttype addIncidenttype(Incidenttype incidenttype) {
		getIncidenttypes().add(incidenttype);
		incidenttype.setAgenttype(this);

		return incidenttype;
	}

	public Incidenttype removeIncidenttype(Incidenttype incidenttype) {
		getIncidenttypes().remove(incidenttype);
		incidenttype.setAgenttype(null);

		return incidenttype;
	}

}