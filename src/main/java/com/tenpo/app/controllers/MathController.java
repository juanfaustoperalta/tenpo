package com.tenpo.app.controllers;


import com.tenpo.app.aspect.MetricRecorder;
import com.tenpo.app.dtos.responses.MathResponse;
import com.tenpo.app.model.TransactionName;
import com.tenpo.app.services.MathService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/math")
public class MathController {

	private final MathService service;

	public MathController(MathService service) {
		this.service = service;
	}

	@MetricRecorder(name = TransactionName.MATH)
	@GetMapping("/{number1}/{number2}")
	public @ResponseBody ResponseEntity<MathResponse> multiply(@PathVariable("number1") BigDecimal number1,
					@PathVariable("number2") BigDecimal number2) {
		BigDecimal multiply = service.multiply(number1, number2);
		return ResponseEntity.ok(new MathResponse(multiply));
	}


}
