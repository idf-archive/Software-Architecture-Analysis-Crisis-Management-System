package safeguard.cms.database;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import safeguard.RmiServerIntf;

public class RMIDatabaseConnector extends DatabaseConnector {
	private RmiServerIntf DBObj;
	public RMIDatabaseConnector() {
		 try {
			if (System.getSecurityManager() == null)
				System.setSecurityManager ( new RMISecurityManager() );
			System.out.println("connecting");
			DBObj = (RmiServerIntf) Naming.lookup("//192.168.1.103:1099/RmiServer");
			System.out.println("Connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String sendIncident(String str) {
		try {
			System.out.println("Send to database " + str);
			return DBObj.addIncident(str);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*public void send(String str) {
		try {
			System.out.println("Send to database " + str);
			DBObj.addIncident(str);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	@Override
	public String getNearestShelter(String str) {
		System.out.println("Send to database " + str);
		try {
			return DBObj.getNearestShelter(str);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getNearestAgency(String str) {
		System.out.println("Send to database " + str);
		try {
			return DBObj.getNearestAgency(str);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getRecentTrend() {
		try {
			return DBObj.getRecentTrend();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	@Override
	public String getIncident() {
		try {
			return DBObj.getIncident();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}

}
