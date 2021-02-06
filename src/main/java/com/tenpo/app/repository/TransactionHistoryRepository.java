package com.tenpo.app.repository;

import com.tenpo.app.model.TransactionHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionHistoryRepository
				extends PagingAndSortingRepository<TransactionHistory, Long> {


}
