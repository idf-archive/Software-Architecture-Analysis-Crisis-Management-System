package safeguard;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public interface RmiServerIntf extends Remote{
	
	//public String getMessage() throws RemoteException;
	
	
	public String getNearestShelter(String postalCode) throws RemoteException;
	public String getNearestAgency(String postalCode) throws RemoteException;
	public String getAgentType() throws RemoteException;
	public String getDengueSpot() throws RemoteException;
	public String getHaze() throws RemoteException;
	public String getIncident() throws RemoteException;
	public String getIncidentType() throws RemoteException;
	public String getOperator() throws RemoteException; 
	public String getShelter() throws RemoteException; 
	public String getAgency() throws RemoteException;
	public String getRecentTrend() throws RemoteException;
	public String updateIncident(String jsonString) throws RemoteException;

	
	public void addAgency(String jsonString) throws RemoteException;
	public void addAgencyType(String jsonString) throws RemoteException;
	public void addDengueSpot(String jsonString) throws RemoteException;
	public void addHaze(String jsonString) throws RemoteException;
	public String addIncident(String jsonString) throws RemoteException; 
	public void addIncidentType(String jsonString) throws RemoteException; 
	public void addOperator(String jsonString) throws RemoteException; 
	public void addShelter(String jsonString) throws RemoteException;


}
