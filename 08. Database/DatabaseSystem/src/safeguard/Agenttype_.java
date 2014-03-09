package safeguard;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-09T12:22:26.206+0800")
@StaticMetamodel(Agenttype.class)
public class Agenttype_ {
	public static volatile SingularAttribute<Agenttype, Integer> agenttypeid;
	public static volatile SingularAttribute<Agenttype, String> typename;
	public static volatile ListAttribute<Agenttype, Agency> agencies;
	public static volatile ListAttribute<Agenttype, Incidenttype> incidenttypes;
}
