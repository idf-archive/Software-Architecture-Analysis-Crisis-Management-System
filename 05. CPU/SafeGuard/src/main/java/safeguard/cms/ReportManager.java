package safeguard.cms;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import safeguard.cms.primitive.message.ReportMessage;

public class ReportManager extends Thread{
	private Scheduler scheduler;
	private BeanFactory factory;
	private boolean running;
	public ReportManager(Scheduler scheduler) {
		this.scheduler = scheduler;
		factory = new XmlBeanFactory(new FileSystemResource("Spring.xml"));
	}
	
	public void startReportManager() {
		running = true;
		start();
	}
	
	public void stopReportManager() {
		running = false;
	}
	
	@Override
	public void run() {
		try {
			while (running) {
				Thread.sleep(60000);
				String jsonStr = scheduler.getRecentTrendFromDB();
				ReportMessage reportMsg = (ReportMessage) factory.getBean("reportMessage");
				reportMsg.parseMessage(jsonStr);
				scheduler.receiveMessage(reportMsg);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
