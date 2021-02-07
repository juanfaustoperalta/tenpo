package com.tenpo.app.controllers;

import com.tenpo.app.aspect.MetricRecorder;
import com.tenpo.app.model.Status;
import com.tenpo.app.model.TransactionHistory;
import com.tenpo.app.model.TransactionName;
import com.tenpo.app.services.TransactionHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionHistoryController {

	private TransactionHistoryService service;

	public TransactionHistoryController(TransactionHistoryService service) {
		this.service = service;
	}

	@MetricRecorder(name = TransactionName.HISTORY)
	@GetMapping
	public ResponseEntity<Page<TransactionHistory>> history(@RequestParam(defaultValue = "0") Integer offset,
					@RequestParam(defaultValue = "10") Integer limit,
					@RequestParam(defaultValue = "id") String orderBy,
					@RequestParam(required = false) Status status,
					@RequestParam(name = "transaction_name", required = false) TransactionName transactionName) {
		return ResponseEntity.ok(service.getPage(offset, limit, orderBy, status, transactionName));
	}
}
