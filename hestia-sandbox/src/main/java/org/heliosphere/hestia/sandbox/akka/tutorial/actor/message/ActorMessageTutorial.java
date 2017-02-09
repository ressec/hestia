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
package org.heliosphere.hestia.sandbox.akka.tutorial.actor.message;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

/**
 * Provides a tutorial based on how to send messages to an {@code Akka} actor.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class ActorMessageTutorial
{
	/**
	 * Main entry point.
	 * <hr>
	 * @param arguments Arguments passed on the command line.
	 */
	@SuppressWarnings({ "nls", "unused" })
	public static void main(String[] arguments)
	{
		// Create an actor system named: 'org.heliosphere.hestia.actor-system'.
		final ActorSystem system = ActorSystem.create("ActorSystem");

		// Create the actors.
		final ActorRef worker = system.actorOf(Props.create(Worker.class), "worker");
		final ActorRef consumer = system.actorOf(Props.create(Consumer.class), "consumer");

		final Inbox inbox = Inbox.create(system);
	}
}
