package safeguard;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-09T12:22:26.224+0800")
@StaticMetamodel(Haze.class)
public class Haze_ {
	public static volatile SingularAttribute<Haze, Integer> hazeid;
	public static volatile SingularAttribute<Haze, Timestamp> datetimereported;
	public static volatile SingularAttribute<Haze, String> psi;
	public static volatile SingularAttribute<Haze, String> classification;
	public static volatile SingularAttribute<Haze, String> location;
}
