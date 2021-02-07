package com.tenpo.app.services;

import com.tenpo.app.model.Status;
import com.tenpo.app.model.TransactionHistory;
import com.tenpo.app.model.TransactionName;
import org.springframework.data.domain.Page;

public interface TransactionHistoryService {
	void createTransactionHistory(Status status, TransactionName transactionName, Exception exception);

	void createTransactionHistory(Status status, TransactionName transactionName);

	Page<TransactionHistory> getPage(Integer offset, Integer limit, String orderBy, Status status,
					TransactionName transactionName);
}
