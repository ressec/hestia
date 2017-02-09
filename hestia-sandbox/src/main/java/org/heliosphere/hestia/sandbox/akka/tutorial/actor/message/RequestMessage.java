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

/**
 * Request message a {@link Worker} actor must support.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public enum RequestMessage
{
	/**
	 * Message to ask for the name.
	 */
	ASK_NAME,

	/**
	 * Message to ask for the current date.
	 */
	ASK_DATE,

	/**
	 * Message to ask for the current time.
	 */
	ASK_TIME,

	/**
	 * Message to ask for a computation.
	 */
	ASK_COMPUTATION,

	/**
	 * Message to ask how many messages have already been requested by the consumer.
	 */
	ASK_MESSAGE_COUNT,
}
