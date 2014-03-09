package safeguard;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the operator database table.
 * 
 */
@Entity
@NamedQuery(name="Operator.findAll", query="SELECT o FROM Operator o")
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String password;

	public Operator() {
	}

	public Operator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}