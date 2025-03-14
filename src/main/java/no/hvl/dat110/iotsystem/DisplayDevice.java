package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// START
				
		// create a client object and use it to
		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		
		// - connect to the broker - use "display" as the username
		client.connect();

		// - create the temperature topic on the broker
		client.createTopic(Common.TEMPTOPIC);

		// - subscribe to the topic
		client.subscribe(Common.TEMPTOPIC);

		// - receive messages on the topic
		for (int i = 0; i < COUNT; i++) {
			PublishMsg msg = (PublishMsg) client.receive();
			System.out.println("DISPLAY: " + msg.getMessage());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {}
		}

		// - unsubscribe from the topic
		client.unsubscribe(Common.TEMPTOPIC);

		// - disconnect from the broker
		client.disconnect();
		
		// END
		
		System.out.println("Display stopping ... ");
	}
}
