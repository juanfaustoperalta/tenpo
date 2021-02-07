package com.tenpo.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "transaction_name")
	@Enumerated(EnumType.STRING)
	private TransactionName transactionName;
	@JsonInclude(Include.NON_NULL)
	private String exception;
	@JsonInclude(Include.NON_NULL)
	private String cause;


	@CreationTimestamp
	private OffsetDateTime createdAt;

	public TransactionHistory() {
	}

	public TransactionHistory(Status status, TransactionName transactionName, String exception, String cause) {
		this.status = status;
		this.transactionName = transactionName;
		this.exception = exception;
		this.cause = cause;
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

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
