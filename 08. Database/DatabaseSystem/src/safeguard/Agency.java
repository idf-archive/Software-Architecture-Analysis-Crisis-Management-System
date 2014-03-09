package safeguard;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the agency database table.
 * 
 */
@Entity
@NamedQuery(name="Agency.findAll", query="SELECT a FROM Agency a")
public class Agency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="agencyid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="agency_agencyid_seq")
	@javax.persistence.SequenceGenerator(name="agency_agencyid_seq", sequenceName = "agency_agencyid_seq")
	private Integer agencyid;

	private String branchaddress;

	private String branchpostal;

	private String phone;

	//bi-directional many-to-one association to Agenttype
	@ManyToOne
	@JoinColumn(name="agenttype_typeid")
	private Agenttype agenttype;

	public Agency() {
	}
	
	public Agency(String branchAddress, String branchPostal, String phone, Agenttype agentType) {
		
		this.branchaddress = branchAddress;
		this.branchpostal = branchPostal;
		this.phone = phone;
		this.agenttype = agentType;
	}


	public Integer getAgencyid() {
		return this.agencyid;
	}

	public void setAgencyid(Integer agencyid) {
		this.agencyid = agencyid;
	}

	public String getBranchaddress() {
		return this.branchaddress;
	}

	public void setBranchaddress(String branchaddress) {
		this.branchaddress = branchaddress;
	}

	public String getBranchpostal() {
		return this.branchpostal;
	}

	public void setBranchpostal(String branchpostal) {
		this.branchpostal = branchpostal;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Agenttype getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(Agenttype agenttype) {
		this.agenttype = agenttype;
	}

}