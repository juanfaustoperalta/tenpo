package com.tenpo.app.services;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MathService {

	public BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
		return number1.multiply(number2);
	}
}
