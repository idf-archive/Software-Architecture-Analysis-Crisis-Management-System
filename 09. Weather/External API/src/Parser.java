
public interface Parser {
	public String targetAddr = "192.168.1.102";
	public int targetPort=5000;

	public void getData();
	public void pushData();
	public boolean isSuccessful();
}
