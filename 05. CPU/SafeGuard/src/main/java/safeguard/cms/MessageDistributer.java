package safeguard.cms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import safeguard.Config;
import safeguard.cms.primitive.message.Message;
import safeguard.cms.stream.CriticalMessageStream;
import safeguard.cms.stream.NonCriticalMessageStream;
import safeguard.cms.stream.ReportStream;
import safeguard.cms.stream.StreamPoster;

public class MessageDistributer extends Thread {
	
	Collection<StreamPoster> streams; 
	Queue<Message> events;
	boolean running;
	public MessageDistributer() {
		streams = new HashSet<StreamPoster>();
		events = new ConcurrentLinkedQueue<Message>();
		StreamPoster stream;
		addStream(stream = new ReportStream());
		Config.instance.addObserver(stream);
		addStream(stream = new CriticalMessageStream());
		Config.instance.addObserver(stream);
		addStream(stream = new NonCriticalMessageStream());
		Config.instance.addObserver(stream);
	}
	
	public void startDistributer() {
		running = true;
		start();
	}
	public void stopDistributer() {
		running = false;
	}
	
	public void addToPushQueue(Message msg) {
		events.add(msg);
		
	}
	
	public void pushEvent(Message msg) {
		for (StreamPoster stream : streams) {
			stream.push(msg);
		}
	}
	@Override
	public void run() {
		while (running) {
			if (!events.isEmpty())
				pushEvent(events.poll());
		}
	}
	public void addStream(StreamPoster stream) {
		streams.add(stream);
	}

}
