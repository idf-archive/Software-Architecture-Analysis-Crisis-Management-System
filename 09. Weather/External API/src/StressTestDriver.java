import java.util.concurrent.TimeUnit;


public class StressTestDriver implements Runnable {

	int threadNo;
	static int totalThreadNumber = 500;
	int totalRequestNumber = 30;
	
	public StressTestDriver(int i)
	{
		threadNo = i;
		
	}
	public void run()
	{
		//access get all incidents from database for 1000 times
		Parser weatherParser;
		Parser hazeParser;
		weatherParser = (Parser) new WeatherParser();
		hazeParser = (Parser) new HazeParser();
		long threadStartTime = System.nanoTime();
		long threadEndTime;
		long duration;
		int failure1 = 0;
		int failure2 = 0;
		
		weatherParser.getData();
		hazeParser.getData();
		
		for(int i=0; i<totalRequestNumber; i++)
		{
			
			if (weatherParser.isSuccessful()) {
				weatherParser.pushData();
//				System.out.println("Thread: "+threadNo+" Access: " + i);
			}
			else {
				System.out.println("Thread: "+threadNo+" Fail_weather: " + i);
				failure1++;
			}
				
			if (hazeParser.isSuccessful()) {
//				System.out.println("Thread: "+threadNo+" Access: " + i);
				hazeParser.pushData();
			}
			else {
				System.out.println("Thread: "+threadNo+" Fail_haze: " + i);
				failure2++;
			}

			
			
		}
		threadEndTime = System.nanoTime();
		duration = TimeUnit.NANOSECONDS.toMillis(threadEndTime-threadStartTime);
		System.out.println("======== Thread " + threadNo +" is done in "+ duration +" miliseconds with Fail_weather of "+ failure1+" times and Fail_haze of "+failure2+". =======");

	}

	
	public static void main(String args[]) 
	{
		for(int i=0; i<totalThreadNumber; i++)
		{
			System.out.println("No of Threads: " + i);
			//create new thread and start
			(new Thread(new StressTestDriver(i))).start();
			try {
				//sleep 4 seconds
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
