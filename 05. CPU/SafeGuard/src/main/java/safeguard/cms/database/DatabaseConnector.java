package safeguard.cms.database;

public abstract class DatabaseConnector {
	public abstract String sendIncident(String str);
	public abstract String getNearestShelter(String str);
	public abstract String getNearestAgency(String str);
	public abstract String getRecentTrend();
	public abstract String getIncident();
}
