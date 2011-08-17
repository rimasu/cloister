package org.rimasu.cloister.server.model;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Test code needs to generate repeatable hashes for passwords, but it is not
 * wise to pollute production code with hooks to override randomness of hashes.
 * This table allows test code to retrieve a predictable hash for a given
 * password (if registered).
 */
public class PasswordHashTable {

	@SuppressWarnings("serial")
	private static Map<String, String> LOOKUP = new HashMap<String, String>() {
		{
			put("AlfredAdams1",
					"$2a$10$16eAEHeZydha4tDuGUzAUutzIgnXUdZqczUW5CYK3cpVacxQ.Qs9O");
			put("BenBenforth1",
					"$2a$10$z/DAvAlgONi0EEYIsQ5eV.jHLBjD.5I29MS5lS6qfVNhge9HgE6JK");
			put("ChrisCarter1",
					"$2a$10$Xl2Exxk7NsIENEN1wjPJIuK8k6vTl4coMthISRqGJYy68oeNb4eYu");
			put("DavidDouglas1",
					"$2a$10$Br628BWLgoxOP3MCuzWK6OiKxYIFz9xrLLvtM0sf2QWF7ppSfEIMi");
			put("ElizabethEdwards1",
					"$2a$10$v.aVSIoihxCSZvuSqcpco.2bZMElClZ02zQUxWBBjvhGELufwKBES");
			put("Alfred",
					"$2a$10$VjKLkhbHWUwrSdZZXY89.uxrfNFSa3E6ZK4jsdpzH2NcsSlXA4Bh.");

		}
	};

	public static String getPasswordHash(String password) {
		if (LOOKUP.containsKey(password)) {
			return LOOKUP.get(password);
		} else {
			throw new IllegalStateException(
					"No example hash stored for password " + password
							+ " add the following line to above table\nput(\""
							+ password + "\", \""
							+ BCrypt.hashpw(password, BCrypt.gensalt())
							+ "\");");
		}
	}
}
