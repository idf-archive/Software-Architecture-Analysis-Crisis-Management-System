package safeguard;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-09T16:26:53.544+0800")
@StaticMetamodel(Incident.class)
public class Incident_ {
	public static volatile SingularAttribute<Incident, Integer> incidentid;
	public static volatile SingularAttribute<Incident, String> address;
	public static volatile SingularAttribute<Incident, String> callername;
	public static volatile SingularAttribute<Incident, Timestamp> datetimereported;
	public static volatile SingularAttribute<Incident, String> description;
	public static volatile SingularAttribute<Incident, Boolean> display;
	public static volatile SingularAttribute<Incident, Integer> levelreported;
	public static volatile SingularAttribute<Incident, String> phone;
	public static volatile SingularAttribute<Incident, String> postal;
	public static volatile SingularAttribute<Incident, Incidenttype> incidenttype;
	public static volatile SingularAttribute<Incident, String> operator;
}
