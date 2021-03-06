package com.tenpo.app.services.impl;


import com.tenpo.app.model.Status;
import com.tenpo.app.model.TransactionHistory;
import com.tenpo.app.model.TransactionName;
import com.tenpo.app.repository.TransactionHistoryRepository;
import com.tenpo.app.services.TransactionHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryServiceImpl
				implements TransactionHistoryService {

	private TransactionHistoryRepository transactionHistoryRepository;

	public TransactionHistoryServiceImpl(TransactionHistoryRepository transactionHistoryRepository) {
		this.transactionHistoryRepository = transactionHistoryRepository;
	}

	@Override
	public void createTransactionHistory(Status status, TransactionName transactionName, Exception exception) {
		TransactionHistory transactionHistory = new TransactionHistory(status, transactionName,
						exception.getClass().getName(), exception.getMessage());
		transactionHistoryRepository.save(transactionHistory);
	}

	@Override
	public void createTransactionHistory(Status status, TransactionName transactionName) {
		TransactionHistory transactionHistory = new TransactionHistory(status, transactionName);
		transactionHistoryRepository.save(transactionHistory);
	}

	@Override public Page<TransactionHistory> getPage(Integer offset, Integer limit, String orderBy,
					Status status, TransactionName transactionName) {
		Pageable pageable = PageRequest.of(offset, limit, Sort.by(orderBy));

		if (checkFilter(transactionName) && checkFilter(status)) {
			return transactionHistoryRepository
							.findByTransactionNameAndStatus(pageable, transactionName, status);
		}

		if (checkFilter(transactionName)) {
			return transactionHistoryRepository.findByTransactionName(pageable, transactionName);
		}

		if (checkFilter(status)) {
			return transactionHistoryRepository.findByStatus(pageable, status);
		}

		return transactionHistoryRepository.findAll(pageable);

	}

	private boolean checkFilter(Enum aEnum) {
		return aEnum != null;
	}

}
