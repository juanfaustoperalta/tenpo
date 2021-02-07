package com.tenpo.app.repository;

import com.tenpo.app.model.Status;
import com.tenpo.app.model.TransactionHistory;
import com.tenpo.app.model.TransactionName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionHistoryRepository
				extends PagingAndSortingRepository<TransactionHistory, Long> {


	Page<TransactionHistory> findByTransactionName(Pageable pageable, TransactionName transactionName);

	Page<TransactionHistory> findByStatus(Pageable pageable, Status status);

	Page<TransactionHistory> findByTransactionNameAndStatus(Pageable pageable, TransactionName transactionName,
					Status status);
}
