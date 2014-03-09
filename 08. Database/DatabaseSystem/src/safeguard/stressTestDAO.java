package safeguard;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.json.simple.JSONObject;

public class stressTestDAO implements Runnable {
	
	
	static final int totalThreads = 50;
	static final int iterationPerThread = 1000;
	static long [] threadDurationList = new long[totalThreads];

	int threadNo;
	
	public stressTestDAO(int i)
	{
		threadNo = i;
		
	}
	public void run()
	{
		//access get all incidents from database for 1000 times
		SafeGuardDAO dao = new SafeGuardDAO();
		long threadStartTime  = System.nanoTime();
		long threadEndTime;
		long threadDuration;
		long startTime; 
		long endTime;
		long duration;

		for(int i=0; i<iterationPerThread; i++)
		{
			//System.out.println("No of Access: " + i);
			startTime = System.nanoTime();
			if(dao.getIncident() != null)
			{
				endTime = System.nanoTime();
				duration = endTime - startTime;
				duration = TimeUnit.NANOSECONDS.toMillis(duration);
				
				//System.out.println("Successful Run on Query Incident:  ThreadNo: " + threadNo + " Iteration:" + i + 
				//		" Time taken: " + duration + " milliseconds");
				
			}
			startTime = System.nanoTime();
			if(testAddIncident(dao, threadNo, i))
			{
				endTime = System.nanoTime();
				duration = endTime - startTime;
				duration = TimeUnit.NANOSECONDS.toMillis(duration);

				//System.out.println("Successful Run on Add Incident:  ThreadNo: " + threadNo + " Iteration: " + i + 
				//		" Time taken: " + duration + " milliseconds");
			
			}
		}
		threadEndTime = System.nanoTime(); 
		threadDuration = TimeUnit.NANOSECONDS.toSeconds(threadEndTime - threadStartTime);
		threadDurationList[threadNo] = threadDuration;
		System.out.println("Thread " + threadNo +" is done and took: " + threadDuration + " Seconds");
	}
	
	public boolean testAddIncident(SafeGuardDAO dao, int threadNo, int i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("safeguard");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		boolean test = false;
		java.util.Date date= new java.util.Date();
        JSONObject newIncident = new JSONObject();
        newIncident.put("callerName", "Mr low ba low ba "  + threadNo +":" + i);
        newIncident.put("location", "207 Nanyang Avenue");
        newIncident.put("postal", "122230");
        newIncident.put("callerPhone", "11412412502");
        newIncident.put("description", "this is a trial run3");
        newIncident.put("display", "true");
        newIncident.put("level", "4");
        newIncident.put("dateTimeReported", new Timestamp(date.getTime()).toString());
        newIncident.put("type", "Fire Fighting");
        newIncident.put("operatorUsername", "wanqing");
        dao.addIncident(newIncident.toJSONString());
        
		Incident query = em.createQuery("SELECT a from Incident a where a.callername = :test", Incident.class ).setParameter("test", "Mr low ba low ba " + threadNo +":" + i).getSingleResult();
		if(query.getCallername().equals("Mr low ba low ba " + threadNo +":" + i) )
			test = true;
		
		Incident testIncident = (Incident) dao.find(Incident.class, query.getIncidentid());
		dao.remove(testIncident);
		return test;
	}

	
	public static void main(String args[]) 
	{
	
		long startTime; 
		long endTime;
		long duration;
		startTime = System.nanoTime();
		for(int i=0; i<totalThreads; i++)
		{
			//create new thread and start
			(new Thread(new stressTestDAO(i))).start();
			
			//main is considered one thread
			System.out.println("No of Threads: " + (Thread.activeCount() - 1) + " are running");
			
			try {
				//sleep 3 seconds
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//main is one count
		while(Thread.activeCount() != 1)
		{
			
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		duration = TimeUnit.NANOSECONDS.toSeconds(duration);
		System.out.println("Total Runtime: " + duration + " Seconds");
		System.out.println("Average Runtime Per Thread: " + (duration/ (double) totalThreads) + " Seconds");
		System.out.println("Average Runtime Per Iteration: " + (duration/ (double) (totalThreads*iterationPerThread)) + " Seconds");

		
		
	}
}
