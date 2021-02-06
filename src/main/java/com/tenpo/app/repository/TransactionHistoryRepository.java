package com.tenpo.app.repository;

import com.tenpo.app.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository
				extends JpaRepository<TransactionHistory, Long> {
}
