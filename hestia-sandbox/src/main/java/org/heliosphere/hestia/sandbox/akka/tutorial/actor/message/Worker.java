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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.heliosphere.demeter.base.element.Element;
import com.heliosphere.demeter.base.element.IElement;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Simple worker actor in charge of handling messages of type {@link RequestMessage}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public final class Worker extends UntypedActor
{
	// Akka logger.
	private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

	/**
	 * Worker actor name.
	 */
	@SuppressWarnings("nls")
	private final String name = "Ukulele";

	/**
	 * Count of messages 'requests' already processed by the worker.
	 */
	private long count;

	@SuppressWarnings("nls")
	@Override
	public final void onReceive(Object message)
	{
		IElement<String> element = null;

		count += 1;

		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = null;

		if (message instanceof RequestMessage)
		{
			//LOG.info(String.format("Received message of type: %1$s", ((RequestMessage) message).name()));
			RequestMessage request = (RequestMessage) message;

			switch ((RequestMessage) message)
			{
				case ASK_NAME:
					element = new Element<>("My name is: ", name);
					getSender().tell(new ResponseMessage(request, element), getSelf());
					break;

				case ASK_COMPUTATION:
					element = new Element<>("Result of (4 + 1 - 3) is: ", "2");
					getSender().tell(new ResponseMessage(request, element), getSelf());
					break;

				case ASK_DATE:
					sdf = new SimpleDateFormat("dd.MM.yyyy");
					element = new Element<>("Current date is: ", sdf.format(date));
					getSender().tell(new ResponseMessage(request, element), getSelf());
					break;

				case ASK_TIME:
					sdf = new SimpleDateFormat("hh:mm:ss");
					element = new Element<>("Current time is: ", sdf.format(date));
					getSender().tell(new ResponseMessage(request, element), getSelf());
					break;

				case ASK_MESSAGE_COUNT:
					element = new Element<>("Requests being processed at this time is: ", String.valueOf(count));
					getSender().tell(new ResponseMessage(request, element), getSelf());
					break;

				default:
					LOG.debug(String.format("Not processing message of type: %1$s", request.name()));
					break;
			}
		}
		else
		{
			unhandled(message);
		}
	}
}
