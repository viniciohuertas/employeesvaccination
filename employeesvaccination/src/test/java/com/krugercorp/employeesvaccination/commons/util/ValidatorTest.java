package com.krugercorp.employeesvaccination.commons.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatorTest {
	
	@Test
	void validIdentification() {
		assertTrue(Validator.validatorIdentification("0401240353"));
	}

	@Test
	void invalidIdentification() {
		assertFalse(Validator.validatorIdentification("0401240354"));
	}

}