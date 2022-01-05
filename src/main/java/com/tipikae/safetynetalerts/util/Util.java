package com.tipikae.safetynetalerts.util;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.constant.Constant;

/**
 * An utility class implementation of IUtil.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class Util implements IUtil {
	
	/**
	 * {@inheritDoc}
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	public int calculateAge(LocalDate birthDate) {
		if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
	}

	/**
	 * {@inheritDoc}
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	public boolean isAdult(LocalDate birthDate) {
		return calculateAge(birthDate) > Constant.ADULT_AGE;
	}
}
