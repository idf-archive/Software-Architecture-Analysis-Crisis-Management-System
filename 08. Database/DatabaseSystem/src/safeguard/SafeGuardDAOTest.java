package safeguard;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SafeGuardDAOTest {

	SafeGuardDAO dao = new SafeGuardDAO();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("safeguard");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	
	@Test
	public void testPersistAgency() {
		Agency tempAgency = new Agency();
		tempAgency.setBranchaddress("Branch Address");
		tempAgency.setBranchpostal("654321");
		tempAgency.setPhone("1234567");
		Agenttype tempType = (Agenttype) dao.findById("AgentType", 1); 
		System.out.println(tempType.toString());
		tempAgency.setAgenttype(tempType);
		dao.persist(tempAgency);
		Agency query = em.createQuery("SELECT a from Agency a where a.branchaddress = :test", Agency.class ).setParameter("test", "Branch Address").getSingleResult();
		System.out.println(query.getAgencyid());
		System.out.println(tempAgency.getAgencyid());
		assertSame(query.getAgencyid(), tempAgency.getAgencyid());
		dao.remove(tempAgency);
		//fail("Not yet implemented");
	}

	@Test
	public void testPersistAgentType() {
		Agenttype tempAgentType = new Agenttype();
		tempAgentType.setTypename("Temp Test");
		dao.persist(tempAgentType);
		Agenttype query = em.createQuery("SELECT a from Agenttype a where a.typename = :test", Agenttype.class ).setParameter("test", "Temp Test").getSingleResult();
		assertSame(query.getAgenttypeid(), tempAgentType.getAgenttypeid());
		dao.remove(tempAgentType);
		//fail("Not yet implemented");
	}
	@Test
	public void testPersistOperator() {
		Operator oper = new Operator("unit test", "unit test");
		dao.persist(oper);
		Operator query = em.createQuery("SELECT a from Operator a where a.username = :test", Operator.class ).setParameter("test", "unit test").getSingleResult();
		assertSame(oper.getUsername(), query.getUsername());
		dao.remove(oper);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testPersistShelter() {
		
		Shelter shelter = new Shelter("test 123","test 123", "123456");
		dao.persist(shelter);
		Shelter query = em.createQuery("SELECT a from Shelter a where a.address = :test", Shelter.class ).setParameter("test", "test 123").getSingleResult();
		assertSame(shelter.getShelterid(), query.getShelterid());
		dao.remove(shelter);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testPersistHaze() {
		Haze haze = new Haze();
		java.util.Date date= new java.util.Date();
		haze.setPsi("999");
		haze.setDatetimereported(new Timestamp(date.getTime()));
		haze.setLocation("South");
		haze.setClassification("Hazardeous");
		dao.persist(haze);
		Haze query = em.createQuery("SELECT a from Haze a where a.psi = :test", Haze.class ).setParameter("test", "999").getSingleResult();
		assertSame(haze.getHazeid(), query.getHazeid());
		dao.remove(haze);


		//fail("Not yet implemented");
	}
	
	
	@Test
	public void testPersistDengueSpot() {
		Denguespot ds = new Denguespot();
		ds.setAddress("test 123 test 123");
		ds.setCount(99);
		ds.setPostal("532345");
		dao.persist(ds);
		Denguespot query = em.createQuery("SELECT a from Denguespot a where a.address = :test", Denguespot.class ).setParameter("test", "test 123 test 123").getSingleResult();
		assertSame(ds.getDenguespotid(), query.getDenguespotid());
		dao.remove(ds);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testPersistIncident() {
		java.util.Date date= new java.util.Date();

		Incidenttype incidentType = (Incidenttype) dao.findByName("IncidentType", "Fire Fighting"); 
		String operator = "WanQing";
		Incident incident = new Incident("test 123", "test 123", "test 123", "123456", "test 123", 1, new Timestamp(date.getTime()), incidentType, true, operator);
		dao.persist(incident);
		Incident query = em.createQuery("SELECT a from Incident a where a.address = :test", Incident.class ).setParameter("test", "test 123").getSingleResult();
		assertSame(incident.getIncidentid(), query.getIncidentid());
		dao.remove(incident);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testPersistIncidentType() {
		//fail("Not yet implemented");
		Agenttype agentType = (Agenttype) dao.findById("AgentType", 1); 
		Incidenttype it = new Incidenttype("test 123", 5, agentType);
		dao.persist(it);
		Incidenttype query = em.createQuery("SELECT a from Incidenttype a where a.incidenttypename = :test", Incidenttype.class ).setParameter("test", "test 123").getSingleResult();
		assertSame(it.getIncidenttypeid(), query.getIncidenttypeid());
		dao.remove(it);
	}
	
	
	//ADd
	@Test
	public void testAddAgency() {

		boolean test = false;
		JSONObject newJSON = new JSONObject();
	    newJSON.put("address", "Test New Agency");
	    newJSON.put("phone", "412414124");
	    newJSON.put("postal", "12567");
	    newJSON.put("typeId", "1");
	    dao.addAgency(newJSON.toJSONString());
		Agency query = em.createQuery("SELECT a from Agency a where a.branchaddress = :test", Agency.class ).setParameter("test", "Test New Agency").getSingleResult();
		if(query.getBranchaddress().equals("Test New Agency"))
				test = true;
		assertTrue(test);
		Agency testAgency = (Agency) dao.find(Agency.class,query.getAgencyid());
		dao.remove(testAgency);
	}

	@Test
	public void testAddAgentType() {
		
		JSONObject newJSON = new JSONObject();
	    newJSON.put("typeName", "new agency type test one");
	    dao.addAgencyType(newJSON.toJSONString());
	    
	    boolean test = false;
		Agenttype query = em.createQuery("SELECT a from Agenttype a where a.typename = :test", Agenttype.class ).setParameter("test", "new agency type test one").getSingleResult();
		if(query.getTypename().equals("new agency type test one"))
				test = true;
		assertTrue(test);
		Agenttype testAgentType = (Agenttype) dao.find(Agenttype.class,query.getAgenttypeid());
		dao.remove(testAgentType);
	}
	
	@Test
	public void testAddOperator() {
		
		JSONObject newOperator = new JSONObject();
        newOperator.put("username", "mad");
        newOperator.put("password", "shit");
        dao.addOperator(newOperator.toJSONString());
		boolean test = false;
		Operator query = em.createQuery("SELECT a from Operator a where a.username = :test", Operator.class ).setParameter("test", "mad").getSingleResult();
		if(query.getUsername().equals("mad"))
			test = true;
		Operator testOper = (Operator) dao.find(Operator.class, "mad");
		assertTrue(test);
		dao.remove(testOper);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testAddShelter() {
		
		boolean test = false;
		 JSONObject newShelter = new JSONObject();
	     newShelter.put("address", "test test shelter");
	     newShelter.put("contact", "4124788");
	     newShelter.put("postal", "523453");
	     dao.addShelter(newShelter.toJSONString());
		Shelter query = em.createQuery("SELECT a from Shelter a where a.address = :test", Shelter.class ).setParameter("test", "test test shelter").getSingleResult();
		if(query.getAddress().equals("test test shelter"))
			test = true;
		assertTrue(test);
		Shelter shelterTest = (Shelter) dao.find(Shelter.class, query.getShelterid());
		dao.remove(shelterTest);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testAddHaze() {
		
		boolean test = false;
		java.util.Date date= new java.util.Date();
		JSONObject mainJSONObj = new JSONObject();
		
		JSONArray jsonArray = new JSONArray();
		
		JSONObject newHaze = new JSONObject();
        newHaze.put("psi", "999");
        newHaze.put("dateTimeReported", new Timestamp(date.getTime()).toString());
        newHaze.put("location", "South");
        newHaze.put("classification", "Hazardeous");
        jsonArray.add(newHaze);
        mainJSONObj.put("hazeArray", jsonArray);
        
        dao.addHaze(mainJSONObj.toJSONString());
		Haze query = em.createQuery("SELECT a from Haze a where a.psi = :test", Haze.class ).setParameter("test", "999").getSingleResult();
		if(query.getPsi().equals("999"))
			test = true;
		assertTrue(test);
		Haze testHaze  = (Haze) dao.find(Haze.class, query.getHazeid());
		dao.remove(testHaze);

		//fail("Not yet implemented");
	}
	
	@Test
	public void testAddDengueSpot() {
		boolean test = false;
		 JSONObject newDS = new JSONObject();
        newDS.put("address", "Test Dengue Spot");
        newDS.put("count", "12356");
        newDS.put("postal", "523457");
        dao.addDengueSpot(newDS.toJSONString());
		
		Denguespot query = em.createQuery("SELECT a from Denguespot a where a.address = :test", Denguespot.class ).setParameter("test", "Test Dengue Spot").getSingleResult();
		if(query.getAddress().equals("Test Dengue Spot"))
			test = true;
		assertTrue(test);
		Denguespot testDengueSpot  = (Denguespot) dao.find(Denguespot.class, query.getDenguespotid());
		dao.remove(testDengueSpot);
		//fail("Not yet implemented");
	}
	@Test
	public void testAddIncident() {
		boolean test = false;
		java.util.Date date= new java.util.Date();
        JSONObject newIncident = new JSONObject();
        newIncident.put("callerName", "Mr low ba low ba");
        newIncident.put("location", "207 Nanyang Avenue");
        newIncident.put("postal", "122230");
        newIncident.put("callerPhone", "11412412502");
        newIncident.put("description", "this is a trial run3");
        newIncident.put("display", "true");
        newIncident.put("level", "4");
        newIncident.put("dateTimeReported", new Timestamp(date.getTime()).toString());
        newIncident.put("type", "Fire Fighting");
        newIncident.put("operatorUsername", "wanqing");

        String id = dao.addIncident(newIncident.toJSONString());
        System.out.println("id :" + id);
		Incident query = em.createQuery("SELECT a from Incident a where a.callername = :test", Incident.class ).setParameter("test", "Mr low ba low ba").getSingleResult();
		if(query.getCallername().equals("Mr low ba low ba"))
			test = true;
		assertTrue(test);
		Incident testIncident = (Incident) dao.find(Incident.class, query.getIncidentid());
		dao.remove(testIncident);
		
		//fail("Not yet implemented");
	}
	
	@Test
	
	public void testAddIncidentType() {
		//fail("Not yet implemented");
		boolean test = false;
	    JSONObject newIncidentType = new JSONObject();
        newIncidentType.put("typeName", "Very Dangerously High");
        newIncidentType.put("defaultLevel", "5");
        newIncidentType.put("agentTypeId", "1");

        dao.addIncidentType(newIncidentType.toJSONString());
		Incidenttype query = em.createQuery("SELECT a from Incidenttype a where a.incidenttypename = :test", Incidenttype.class ).setParameter("test", "Very Dangerously High").getSingleResult();
		if(query.getIncidentTypeName().equals("Very Dangerously High"))
			test = true;
		assertTrue(test);
		Incidenttype testIncidentType = (Incidenttype) dao.find(Incidenttype.class, query.getIncidenttypeid());
		dao.remove(testIncidentType);
	}
	
	

	@Test
	public void testGetAgency() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getAgency());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
			
		
	}

	@Test
	public void testGetAgentType() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getAgentType());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
			
	}

	@Test
	public void testGetHaze() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getHaze());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
	}

	@Test
	public void testGetDengueSpot() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getDengueSpot());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
	}

	@Test
	public void testGetIncident() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getIncident());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
	}

	@Test
	public void testGetIncidentType() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getIncidentType());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
	}

	@Test
	public void testGetOperator() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getOperator());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
	}

	@Test
	public void testGetShelter() {
		JSONArray jsonArrayTest =  (JSONArray) JSONValue.parse(dao.getShelter());
		JSONObject jsonTest = (JSONObject) jsonArrayTest.get(0);
		assertNotNull(jsonTest);
	}
	
	@Test
	public void testGetRecentTrend()
	{
		JSONObject jsonObjTest =  (JSONObject) JSONValue.parse(dao.getRecentTrend());
		JSONArray jsonIncidentTest = (JSONArray) jsonObjTest.get("Incident");
		assertNotNull(jsonIncidentTest);
		
		
	}
	/*
	@Test
	public void testGetNearestShelter() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}
	*/

}
