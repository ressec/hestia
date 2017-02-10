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

import java.io.Serializable;

import com.heliosphere.demeter.base.element.IElement;

import lombok.Getter;

/**
 * Response message the {@link Worker} actor will use to provide answer to a received {@link RequestMessage}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class ResponseMessage implements Serializable
{
	/**
	 * Serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Original request type.
	 */
	@Getter
	private final RequestMessage request;

	/**
	 * Response message.
	 */
	@Getter
	private final IElement<String> response;

	/**
	 * Creates a new response message.
	 * <hr>
	 * @param request Original request.
	 * @param response Response.
	 */
	public ResponseMessage(RequestMessage request, IElement<String> response)
	{
		this.request = request;
		this.response = response;
	}
}
