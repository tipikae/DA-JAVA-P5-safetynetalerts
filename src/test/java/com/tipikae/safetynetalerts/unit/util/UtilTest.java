package com.tipikae.safetynetalerts.unit.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.tipikae.safetynetalerts.util.Util;

class UtilTest {
	
	@Test
	void testCalculateAge_whenOk() {
		LocalDate birthday = LocalDate.of(1973, 9, 5);
		assertEquals(48, Util.calculateAge(birthday));
	}

	@Test
	void testCalculateAge_whenNow() {
		LocalDate birthday = LocalDate.now();
		assertEquals(0, Util.calculateAge(birthday));
	}

	@Test
	void testIsAdult_whenTrue() {
		LocalDate birthday = LocalDate.of(1973, 9, 5);
		assertTrue(Util.isAdult(birthday));
	}

	@Test
	void testIsAdult_whenFalse() {
		LocalDate birthday = LocalDate.of(2020, 9, 5);
		assertFalse(Util.isAdult(birthday));
	}
}
