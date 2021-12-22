package com.tipikae.safetynetalerts.util;

import java.time.LocalDate;
import java.time.Period;

public class Util {

	public static int calculateAge(LocalDate birthDate) {
		if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
	}
	
	public static boolean isAdult(LocalDate birthDate) {
		return calculateAge(birthDate) > 18;
	}
}
