package com.summer.tech.spring;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

//省略import
// 编程式事务例子
public class TransactionTest {

	private static final String CREATE_TABLE_SQL = "create table test"
			+ "(id int ,name varchar(100))";
	private static final String DROP_TABLE_SQL = "drop table test";
	private static final String INSERT_SQL = "insert into test(name) values(?)";
	private static final String COUNT_SQL = "select count(*) from test";

	private static ApplicationContext ctx;
	private static PlatformTransactionManager txManager;
	private static DataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	private static TransactionTemplate transactionTemplate;

	@BeforeClass
	public static void setUpClass() {
		String[] configLocations = new String[] { "classpath:spring-datasource.xml" };
		ctx = new ClassPathXmlApplicationContext(configLocations);
		txManager = ctx.getBean(PlatformTransactionManager.class);
		dataSource = ctx.getBean(DataSource.class);
		jdbcTemplate = new JdbcTemplate(dataSource);
		transactionTemplate = new TransactionTemplate(txManager);
	}

	@Test
	public void testPlatformTransactionManager() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		jdbcTemplate.execute(CREATE_TABLE_SQL);
		try {
			jdbcTemplate.update(INSERT_SQL, "test");
			txManager.commit(status);
		} catch (RuntimeException e) {
			txManager.rollback(status);
		}
		//jdbcTemplate.execute(DROP_TABLE_SQL);
	}

	@Test
	public void testPlatformTransactionManagerForLowLevel1() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		Connection conn = DataSourceUtils.getConnection(dataSource);
		try {
			conn.prepareStatement(CREATE_TABLE_SQL).execute();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, "test");
			pstmt.execute();
			conn.prepareStatement(DROP_TABLE_SQL).execute();
			txManager.commit(status);
		} catch (Exception e) {
			status.setRollbackOnly();
			txManager.rollback(status);
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	// 事务模板测试
	@Test
	public void testTransactionTemplate(){
		transactionTemplate.execute(new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus transactionStatus) {
				return null;
			}
		});

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

			}
		});
	}
}