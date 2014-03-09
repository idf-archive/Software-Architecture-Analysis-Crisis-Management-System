
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the phonebook database table.
 * 
 */
@Entity
@NamedQuery(name="Phonebook.findAll", query="SELECT p FROM Phonebook p")
public class Phonebook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String phoneNum;

	private String postal;
	private String name;

	
	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Phonebook() {
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}