package com.tenpo.app.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MathServiceTest {

	@Autowired
	private MathService service;

	@Test
	public void testMultiply_success() {
		assertEquals(BigDecimal.valueOf(4.0).compareTo(service.multiply(BigDecimal.valueOf(2.0), BigDecimal.valueOf(2.0))),
						0);
		assertEquals(BigDecimal.valueOf(12.0).compareTo(service.multiply(BigDecimal.valueOf(6.0), BigDecimal.valueOf(2.0))),
						0);
		assertEquals(BigDecimal.valueOf(-8.0).compareTo(service.multiply(BigDecimal.valueOf(4.0), BigDecimal.valueOf(-2.0))),
						0);
	}

}