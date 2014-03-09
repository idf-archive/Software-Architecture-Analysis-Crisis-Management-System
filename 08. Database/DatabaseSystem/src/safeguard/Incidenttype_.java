package safeguard;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-09T12:22:26.241+0800")
@StaticMetamodel(Incidenttype.class)
public class Incidenttype_ {
	public static volatile SingularAttribute<Incidenttype, Integer> incidenttypeid;
	public static volatile SingularAttribute<Incidenttype, Integer> defaultemergencylevel;
	public static volatile SingularAttribute<Incidenttype, String> incidenttypename;
	public static volatile ListAttribute<Incidenttype, Incident> incidents;
	public static volatile SingularAttribute<Incidenttype, Agenttype> agenttype;
}
