package com.tipikae.safetynetalerts.util;

import java.time.LocalDate;

/**
 * Interface utilitaire
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUtil {

	/**
	 * Calculate age from birthdate.
	 * @param birthDate a LocalDate object.
	 * @return int
	 */
	int calculateAge(LocalDate birthDate);
	/**
	 * Check if a LocalDate is an adult age.
	 * @param birthDate a LocalDate object.
	 * @return boolean
	 */
	boolean isAdult(LocalDate birthDate);
}
