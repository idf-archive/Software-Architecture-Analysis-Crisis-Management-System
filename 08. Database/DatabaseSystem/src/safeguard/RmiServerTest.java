package safeguard;

import static org.junit.Assert.*;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RmiServerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//-Djava.security.policy=C:\Users\Kiri\workspace\safeguard\src\safeguard\policy.all
		RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//192.168.1.103:1099/RmiServer");
        if (System.getSecurityManager() == null)
 	       System.setSecurityManager ( new RMISecurityManager() );
        
        SafeGuardDAO dao = new SafeGuardDAO();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("safeguard");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction transaction = em.getTransaction();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
			
	}
		

	@After
	public void tearDown() throws Exception {
	}
	/*
	@Test
	public void testGetMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAgency() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAgentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDengueSpot() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHaze() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHazeType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncident() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncidentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOperator() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShelter() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNearestShelter() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAgency() {
		
		JSONObject newJSON = new JSONObject();
	    newJSON.put("address", "new agency test one");
	    newJSON.put("phone", "412414124");
	    newJSON.put("postal", "12567");
	    newJSON.put("typeId", "1");
	    obj.addAgency(newJSON.toJSONString());
	    
		//fail("Not yet implemented");
	}

	@Test
	public void testAddAgencyType() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDengueSpot() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddHaze() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddHazeType() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIncident() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIncidentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOperatorString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddShelterString() {
		fail("Not yet implemented");
	}
*/
}
