package safeguard;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-09T12:22:26.094+0800")
@StaticMetamodel(Agency.class)
public class Agency_ {
	public static volatile SingularAttribute<Agency, Integer> agencyid;
	public static volatile SingularAttribute<Agency, String> branchaddress;
	public static volatile SingularAttribute<Agency, String> branchpostal;
	public static volatile SingularAttribute<Agency, String> phone;
	public static volatile SingularAttribute<Agency, Agenttype> agenttype;
}
