package com.tipikae.safetynetalerts.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * An utility class.
 * @author tipikae
 * @version 1.0
 *
 */
public class Util {
	
	private static final int ADULT_AGE = 18;

	/**
	 * Calculate an age.
	 * @param birthDate a LocalDate object.
	 * @return int
	 */
	public static int calculateAge(LocalDate birthDate) {
		if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
	}

	/**
	 * Check if a LocalDate is an adult age.
	 * @param birthDate a LocalDate object.
	 * @return boolean
	 */
	public static boolean isAdult(LocalDate birthDate) {
		return calculateAge(birthDate) > ADULT_AGE;
	}
}
