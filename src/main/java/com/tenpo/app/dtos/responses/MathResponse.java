package com.tenpo.app.dtos.responses;

import java.math.BigDecimal;

public class MathResponse {

	private BigDecimal result;

	public MathResponse(BigDecimal result) {
		this.result = result;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}
}
