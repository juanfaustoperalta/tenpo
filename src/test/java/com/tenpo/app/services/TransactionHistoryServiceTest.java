package com.tenpo.app.services;

import com.tenpo.app.model.Status;
import com.tenpo.app.model.TransactionHistory;
import com.tenpo.app.model.TransactionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(scripts = "classpath:test_transaction_history_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)})
public class TransactionHistoryServiceTest {

	@Autowired
	private TransactionHistoryService service;

	@Test public void test_without_filters() {
		Page<TransactionHistory> page = service.getPage(0, 10, "id", null, null);

		assertEquals(page.getTotalElements(), 4L);
		assertEquals(page.get().count(), 4L);
	}

	@Test public void test_with_status_filter() {
		Page<TransactionHistory> page = service.getPage(0, 10, "id", Status.SUCCESS, null);

		assertEquals(page.getTotalElements(), 2L);
		assertEquals(page.get().count(), 2L);
	}

	@Test public void test_with_transaction_name_filter() {
		Page<TransactionHistory> page = service.getPage(0, 10, "id", null, TransactionName.LOGIN);

		assertEquals(page.getTotalElements(), 2L);
		assertEquals(page.get().count(), 2L);
	}

	@Test public void test_with_transaction_name_and_status_filter() {
		Page<TransactionHistory> page = service.getPage(0, 10, "id", Status.SUCCESS, TransactionName.LOGIN);

		assertEquals(page.getTotalElements(), 1L);
		assertEquals(page.get().count(), 1L);
	}


}