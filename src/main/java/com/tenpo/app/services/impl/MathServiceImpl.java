package com.tenpo.app.services.impl;


import com.tenpo.app.services.MathService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MathServiceImpl implements MathService {

	@Override
	public BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
		return number1.multiply(number2);
	}
}
