package com.tenpo.app.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Status status;
	private TransactionName transactionName;
	private Exception exception;
	@CreationTimestamp
	private OffsetDateTime createdAt;

	public TransactionHistory() {
	}

	public TransactionHistory(Status status, TransactionName transactionName, Exception exception) {
		this.status = status;
		this.transactionName = transactionName;
		this.exception = exception;
	}

	public TransactionHistory(Status status, TransactionName transactionName) {
		this.status = status;
		this.transactionName = transactionName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TransactionName getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(TransactionName transactionName) {
		this.transactionName = transactionName;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
