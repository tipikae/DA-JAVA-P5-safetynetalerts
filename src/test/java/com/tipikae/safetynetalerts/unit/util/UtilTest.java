package com.tipikae.safetynetalerts.unit.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.safetynetalerts.util.IUtil;

@SpringBootTest
class UtilTest {
	
	@Autowired
	private IUtil utility;
	
	@Test
	void testCalculateAge_whenOk() {
		LocalDate birthday = LocalDate.of(1973, 9, 5);
		assertEquals(48, utility.calculateAge(birthday));
	}

	@Test
	void testCalculateAge_whenNow() {
		LocalDate birthday = LocalDate.now();
		assertEquals(0, utility.calculateAge(birthday));
	}

	@Test
	void testIsAdult_whenTrue() {
		LocalDate birthday = LocalDate.of(1973, 9, 5);
		assertTrue(utility.isAdult(birthday));
	}

	@Test
	void testIsAdult_whenFalse() {
		LocalDate birthday = LocalDate.of(2020, 9, 5);
		assertFalse(utility.isAdult(birthday));
	}
}
