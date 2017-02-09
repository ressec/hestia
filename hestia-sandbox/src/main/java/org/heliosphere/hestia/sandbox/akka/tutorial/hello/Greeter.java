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

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Greeter extends UntypedActor
{
	private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

	/**
	 * 
	 */
	@SuppressWarnings("nls")
	String greeting = "";

	@SuppressWarnings("nls")
	@Override
	public void onReceive(Object message)
	{
		if (message instanceof WhoToGreet)
		{
			greeting = "hello, " + ((WhoToGreet) message).who;
		}
		else
		{
			if (message instanceof Greet)
			{
				// Send the current greeting back to the sender.
				LOG.info("Received String message: {}", message);
				getSender().tell(new Greeting(greeting), getSelf());
			}
			else
			{
				unhandled(message);
			}
		}
	}
}
