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
package org.heliosphere.hestia.sandbox.akka.tutorial.actor.config;

import java.util.Map;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

/**
 * {@code Akka} tutorial showing how to use the {@link Config} entities.
 * <p>
 * This tutorial loads a dedicated custom file named {@code mmorpg-test.conf} written in {@code HOCON} that contains a set of categorized properties
 * representing a kind of cluster.
 * <p>
 * It dumps 3 subsets of this configuration:
 * <ul>
 * <li>the whole config. including the {@code akka} properties.</li>
 * <li>the {@code heliosphere} config. only.</li>
 * <li>the {@code heliosphere} config. for the server located in {@code Paris}.</li>
 * </ul>
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class ConfigTutorial
{
	/**
	 * Configuration object.
	 */
	private static Config configuration;

	/**
	 * Main entry point.
	 * <hr>
	 * @param arguments Arguments passed on the command line.
	 */
	@SuppressWarnings({ "nls" })
	public static void main(String[] arguments)
	{
		configuration = ConfigFactory.load("mmorpg-test.conf");

		dumpKeysForPath("");

		dumpKeysForPath("heliosphere");

		dumpKeysForPath("heliosphere.world.europe.france.paris");
	}

	/**
	 * Dumps on the console the keys and their values for a given path.
	 * <hr>
	 * @param path Path.
	 */
	@SuppressWarnings("nls")
	private static void dumpKeysForPath(final String path)
	{
		String aPath = path;

		if (path == null || path.isEmpty())
		{
			aPath = "root";
		}

		System.out.println(" ");
		System.out.println("Dumping entries found for path: " + aPath);
		for (Map.Entry<String, ConfigValue> entry : aPath.equals("root") ? configuration.entrySet() : configuration.withOnlyPath(path).entrySet())
		{
			System.out.println("   " + entry.getKey() + " = " + entry.getValue().render());
		}
	}

}
