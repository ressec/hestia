/*
 * Copyright(c) 2017 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.hestia.sandbox.akka;

public class HelloWorld
{
	//	public static void main(String[] args)
	//	{
	//		try
	//		{
	//			// Create the 'helloakka' actor system
	//			final ActorSystem system = ActorSystem.create("helloakka");
	//
	//			// Create the 'greeter' actor
	//			final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
	//
	//			// Create the "actor-in-a-box"
	//			final Inbox inbox = Inbox.create(system);
	//
	//			// Tell the 'greeter' to change its 'greeting' message
	//			greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());
	//
	//			// Ask the 'greeter for the latest 'greeting'
	//			// Reply should go to the "actor-in-a-box"
	//			inbox.send(greeter, new Greet());
	//
	//			// Wait 5 seconds for the reply with the 'greeting' message
	//			final Greeting greeting1 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
	//			System.out.println("Greeting: " + greeting1.message);
	//
	//			// Change the greeting and ask for it again
	//			greeter.tell(new WhoToGreet("lightbend"), ActorRef.noSender());
	//			inbox.send(greeter, new Greet());
	//			final Greeting greeting2 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
	//			System.out.println("Greeting: " + greeting2.message);
	//
	//			// after zero seconds, send a Greet message every second to the greeter with a sender of the GreetPrinter
	//			final ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));
	//			system.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), greetPrinter);
	//		}
	//		catch (TimeoutException ex)
	//		{
	//			System.out.println("Got a timeout waiting for reply from an actor");
	//			ex.printStackTrace();
	//		}
	//	}

}
