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

import java.util.Random;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.extern.log4j.Log4j;

/**
 * Actor consuming services (defined by {@link RequestMessage}) provided by the {@link Worker } actor.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Log4j
public final class Consumer extends UntypedActor
{
	// Akka logger.
	private final LoggingAdapter LOGGER = Logging.getLogger(getContext().system(), this);

	/**
	 * Limit of message requests to be processed.<br>
	 * After this limit has been reached, we stop the communication.
	 */
	private final long COUNT_LIMIT = 100000;

	/**
	 * Reference to the worker actor.
	 */
	@SuppressWarnings("nls")
	private final ActorSelection worker = getContext().actorSelection("/user/worker");

	/**
	 * Creates a new {@code consumer} actor.
	 */
	public Consumer()
	{
		// At construction time, request from the 'worker' its name so that we can start the communication.
		worker.tell(RequestMessage.ASK_NAME, getSelf());
	}

	@SuppressWarnings("nls")
	@Override
	public final void onReceive(Object message)
	{
		if (message instanceof ResponseMessage)
		{
			if (((ResponseMessage) message).getRequest() == RequestMessage.ASK_MESSAGE_COUNT)
			{
				ResponseMessage response = (ResponseMessage) message;

				//LOG.info(String.format("Received response from request of type: %1$s ; answer is: %2$s", ((ResponseMessage) message).getRequest().name(), ((ResponseMessage) message).getResponse()));
				log.info(String.format("Received response from request of type: %1$s ; answer is: %2$s", response.getRequest().name(), response.getResponse().getName() + ": " + response.getResponse().getValue()));

				if (Long.valueOf(response.getResponse().getValue()).longValue() >= COUNT_LIMIT)
				{
					// Shutdown this actor.
					//context().stop(getSelf());

					// Shutdown the sender actor.
					//context().stop(getSender());

					context().system().terminate();
				}
			}

			// Randomly send a new request to the worker actor.
			switch (getRandomNumberInRange(1, 5))
			{
				case 1:
					worker.tell(RequestMessage.ASK_NAME, getSelf());
					break;

				case 2:
					worker.tell(RequestMessage.ASK_DATE, getSelf());
					break;

				case 3:
					worker.tell(RequestMessage.ASK_TIME, getSelf());
					break;

				case 4:
					worker.tell(RequestMessage.ASK_COMPUTATION, getSelf());
					break;

				case 5:
					worker.tell(RequestMessage.ASK_MESSAGE_COUNT, getSelf());
					break;

				default:
					break;
			}
		}
		else
		{
			unhandled(message);
		}
	}

	@SuppressWarnings("nls")
	private static int getRandomNumberInRange(int min, int max)
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();

		return r.nextInt(max - min + 1) + min;
	}
}
