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
 * {@code Akka} tutorial showing how to send messages asynchronously between two actors.
 * <p>
 * This tutorial creates an actor system and 2 different actors. One is considered as the client {@link Consumer} which send asynchronous request
 * messages {@link RequestMessage} to a producer actor {@link Worker} which computes the requests sent by the client and provide the response to the
 * client through response messages {@link ResponseMessage}.
 * <p>
 * When the client has processed a pre-defined number of requests, it then shutdown the system.
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
