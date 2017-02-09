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
package org.heliosphere.hestia.sandbox.akka.tutorial.hello;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import lombok.extern.log4j.Log4j;
import scala.concurrent.duration.Duration;

/**
 * Provide a detailed description here.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Log4j
public final class HelloWorld
{
	/**
	 * Hide implicit public constructor.
	 */
	private HelloWorld()
	{
		// Empty.
	}

	@SuppressWarnings("nls")
	public static void main(String[] args)
	{
		try
		{
			// Create the 'helloakka' actor system
			final ActorSystem system = ActorSystem.create("helloakka");

			// Prints the settings.
			//System.out.println(system.settings());

			Config conf = ConfigFactory.load("application.conf");
			System.out.println("akka.actor.debug.lifecycle: " + conf.getString("akka.actor.debug.lifecycle")); //should be 'on'

			// Create the 'greeter' actor
			final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");

			// Create the "actor-in-a-box"
			final Inbox inbox = Inbox.create(system);

			// Tell the 'greeter' to change its 'greeting' message
			greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());

			// Ask the 'greeter for the latest 'greeting'
			// Reply should go to the "actor-in-a-box"
			inbox.send(greeter, new Greet());

			// Wait 5 seconds for the reply with the 'greeting' message
			final Greeting greeting1 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
			log.info(String.format("Greeting: %1$s", greeting1.message));

			// Change the greeting and ask for it again
			greeter.tell(new WhoToGreet("lightbend"), ActorRef.noSender());
			inbox.send(greeter, new Greet());
			final Greeting greeting2 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
			log.info(String.format("Greeting: %1$s", greeting2.message));

			// after zero seconds, send a Greet message every second to the greeter with a sender of the GreetPrinter
			final ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));
			system.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), greetPrinter);
		}
		catch (TimeoutException ex)
		{
			log.error(String.format("Got a timeout waiting for reply from an actor"));
			ex.printStackTrace();
		}
	}

}
