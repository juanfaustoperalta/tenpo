package com.tenpo.app.aspect;

import com.tenpo.app.model.Status;
import com.tenpo.app.services.TransactionHistoryService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MetricRecorderAspect {

	private final TransactionHistoryService service;

	public MetricRecorderAspect(TransactionHistoryService service) {
		this.service = service;
	}

	@AfterReturning("@annotation(metricRecorder)")
	public void afterController(final MetricRecorder metricRecorder) {
		service.createTransactionHistory(Status.SUCCESS, metricRecorder.name());
	}

	@AfterThrowing(value = "@annotation(metricRecorder)", throwing = "e")
	public void afterThrowingController(final MetricRecorder metricRecorder, Exception e) {
		service.createTransactionHistory(Status.FAILED, metricRecorder.name(), e);
	}


}