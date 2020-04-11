package com.summer.tech.spring;

import com.summer.tech.spring.jdbc.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateTest {
	private static JdbcTemplate jdbcTemplate;

	@BeforeClass
	public static void setUpClass() {
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String username = "root";
		String password = "123456";
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
				username, password);
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("初始化");
	}

	@Test
	public void testPpreparedStatement1() {
		int count = jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn.prepareStatement("select count(*) from goods");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement pstmt)
					throws SQLException, DataAccessException {
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				rs.next();
				return rs.getInt(1);
			}
		});
		System.out.println(count);
	}

	@Test
	public void testPreparedStatement2() {
		final String sql = "select count(*) from goods where id>?";
		int count = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn.prepareStatement(sql);
			}
		}, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setObject(1, 1);
			}
		}, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				rs.next();
				return rs.getInt(1);
			}

		});
		System.out.println(count);
	}

	@Test
	public void testResultSet1() {
		String listSql = "select * from goods";
		List result = jdbcTemplate.query(listSql, new RowMapper<Map>() {
			@Override
			public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map row = new HashMap();
				row.put(rs.getInt("id"), rs.getString("name"));
				return row;
			}
		});
		Assert.assertEquals(1, result.size());
	}

	@Test
	public void testResultSet2() {
		String listSql = "select * from goods";
		final List result = new ArrayList();
		jdbcTemplate.query(listSql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Map row = new HashMap();
				row.put(rs.getInt("id"), rs.getString("name"));
				result.add(row);
			}
		});
		Assert.assertEquals(1, result.size());
	}

	@Test
	public void testResultSet3() {
		String listSql = "select * from goods";
		List result = jdbcTemplate.query(listSql,
				new ResultSetExtractor<List>() {
					@Override
					public List extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						List result = new ArrayList();
						while (rs.next()) {
							Map row = new HashMap();
							row.put(rs.getInt("id"), rs.getString("name"));
							result.add(row);
						}
						return result;
					}
				});
		Assert.assertEquals(0, result.size());
	}

	public void testQuery() {
		// 2. 查询一行数据并将该行数据转换为Map返回
		jdbcTemplate.queryForMap("select * from goods where name='name5'");
		// 3.查询一行任何类型的数据，最后一个参数指定返回结果类型
		jdbcTemplate
				.queryForObject("select count(*) from goods", Integer.class);
		// 4.查询一批数据，默认将每行数据转换为Map
		jdbcTemplate.queryForList("select * from goods");
		// 5.只查询一列数据列表，列类型是String类型，列名字是name
		jdbcTemplate.queryForList("select name from goods where name=?",
				new Object[] { "name5" }, String.class);
		// 6.查询一批数据，返回为SqlRowSet，类似于ResultSet，但不再绑定到连接上
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from goods");
	}

	@Test
	public void testCallableStatementCreator2() {
		// 2.创建自定义函数
		String createFunctionSql = "CREATE FUNCTION FUNCTION_TEST(str VARCHAR(100)) "
				+ "returns INT return LENGTH(str)";
		String dropFunctionSql = "DROP FUNCTION IF EXISTS FUNCTION_TEST";
		jdbcTemplate.update(dropFunctionSql);
		jdbcTemplate.update(createFunctionSql);
		// 3.准备sql,mysql支持{?= call …}
		final String callFunctionSql = "{?= call FUNCTION_TEST(?)}";
		// 4.定义参数
		List<SqlParameter> params = new ArrayList<SqlParameter>();
		params.add(new SqlOutParameter("result", Types.INTEGER));
		params.add(new SqlParameter("str", Types.VARCHAR));
		Map<String, Object> outValues = jdbcTemplate.call(
				new CallableStatementCreator() {
					@Override
					public CallableStatement createCallableStatement(
							Connection conn) throws SQLException {
						CallableStatement cstmt = conn
								.prepareCall(callFunctionSql);
						cstmt.registerOutParameter(1, Types.INTEGER);
						cstmt.setString(2, "test");
						return cstmt;
					}
				}, params);
		System.out.println(outValues.get("result"));
	}

	@Test
	public void testNamedParameterJdbcTemplate1() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		// namedParameterJdbcTemplate =
		// new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		String insertSql = "insert into goods(name) values(:name)";
		String selectSql = "select * from goods where name=:name";
		String deleteSql = "delete from goods where name=:name";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "name5");
		namedParameterJdbcTemplate.update(insertSql, paramMap);
		final List<Integer> result = new ArrayList<Integer>();
		namedParameterJdbcTemplate.query(selectSql, paramMap,
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						result.add(rs.getInt("id"));
					}
				});
		Assert.assertEquals(1, result.size());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		//namedParameterJdbcTemplate.update(deleteSql, paramSource);
	}

	@Test
	public void testSqlQuery() {
		SqlQuery query = new UserModelSqlQuery(jdbcTemplate);
		List<UserModel> result = query.execute("name5");
		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testMappingSqlQuery() {
		jdbcTemplate.update("insert into goods(name) values('name2')");
		SqlQuery<UserModel> query = new UserModelMappingSqlQuery(jdbcTemplate);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "name2");
		UserModel result = query.findObjectByNamedParam(paramMap);
		Assert.assertNotNull(result);
	}
	@Test
	// SqlFunction：SQL“函数”包装器，用于支持那些返回单行结果集的查询。该类主要用于返回单行单列结果集。
	public void testSqlFunction() {
		jdbcTemplate.update("insert into goods(name) values('name5')");
		String countSql = "select count(*) from goods";
		SqlFunction<Integer> sqlFunction1 = new SqlFunction<Integer>(
				jdbcTemplate.getDataSource(), countSql);
		Assert.assertEquals(1, sqlFunction1.run());
		String selectSql = "select name from goods where name=?";
		SqlFunction<String> sqlFunction2 = new SqlFunction<String>(
				jdbcTemplate.getDataSource(), selectSql);
		sqlFunction2.declareParameter(new SqlParameter(Types.VARCHAR));
		String name = (String) sqlFunction2
				.runGeneric(new Object[] { "name5" });
		Assert.assertEquals("name5", name);
	}

	@Test
	public void testSqlUpdate() {
		SqlUpdate insert = new InsertUserModel(jdbcTemplate);
		insert.update("name5");

		String updateSql = "update goods set name=? where name=?";
		SqlUpdate update = new SqlUpdate(jdbcTemplate.getDataSource(),
				updateSql, new int[] { Types.VARCHAR, Types.VARCHAR });
		update.update("name6", "name5");
		String deleteSql = "delete from goods where name=:name";

		SqlUpdate delete = new SqlUpdate(jdbcTemplate.getDataSource(),
				deleteSql, new int[] { Types.VARCHAR });
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "name5");
		delete.updateByNamedParam(paramMap);
	}

	@Test
	public void testStoredProcedure2() {
		String createFunctionSql = "CREATE FUNCTION FUNCTION_TEST(str VARCHAR(100)) "
				+ "returns INT return LENGTH(str)";
		String dropFunctionSql = "DROP FUNCTION IF EXISTS FUNCTION_TEST";
		jdbcTemplate.update(dropFunctionSql);
		jdbcTemplate.update(createFunctionSql);
		StoredProcedure lengthFunction = new MysqlLengthFunction(jdbcTemplate);
		Map<String, Object> outValues = lengthFunction.execute("test");
		Assert.assertEquals(4, outValues.get("result"));
	}

	@Test
	public void testSimpleJdbcInsert() {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("goods");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("name", "name5");
		insert.compile();
		// 1.普通插入
		insert.execute(args);
		// 2.插入时获取主键值
		insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("goods");
		insert.setGeneratedKeyName("id");
		Number id = insert.executeAndReturnKey(args);
		Assert.assertEquals(1, id);
		// 3.批处理
		insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("goods");
		insert.setGeneratedKeyName("id");
		int[] updateCount = insert.executeBatch(new Map[] { args, args, args });
		Assert.assertEquals(1, updateCount[0]);
	}

	@Test
	public void testSimpleJdbcCall1() {
		// 此处用mysql,因为hsqldb调用自定义函数和存储过程一样
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
		call.withFunctionName("FUNCTION_TEST");
		call.declareParameters(new SqlOutParameter("result", Types.INTEGER));
		call.declareParameters(new SqlParameter("str", Types.VARCHAR));
		Map<String, Object> outVlaues = call.execute("test");
		Assert.assertEquals(4, outVlaues.get("result"));
	}

	@Test
	public void testSimpleJdbcCall2() {
		// 调用hsqldb自定义函数得使用如下方式
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
		call.withProcedureName("FUNCTION_TEST");
		call.declareParameters(new SqlReturnResultSet("result",
				new ResultSetExtractor<Integer>() {
					@Override
					public Integer extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						while (rs.next()) {
							return rs.getInt(1);
						}
						return 0;
					}
				}));
		call.declareParameters(new SqlParameter("str", Types.VARCHAR));
		Map<String, Object> outVlaues = call.execute("test");
		Assert.assertEquals(4, outVlaues.get("result"));
	}

	@Test
	public void testFetchKey1() throws SQLException {
		final String insertSql = "insert into goods(name) values('name1')";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn.prepareStatement(insertSql, new String[] { "id" });
			}
		}, generatedKeyHolder);
		System.out.println(generatedKeyHolder.getKey());
		//Assert.assertEquals(0, generatedKeyHolder.getKey());
	}

	@Test
	public void testFetchKey2() {
		final String insertSql = "insert into goods(name) values('name2')";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlUpdate update = new SqlUpdate();
		update.setJdbcTemplate(jdbcTemplate);
		update.setReturnGeneratedKeys(true);
		// update.setGeneratedKeysColumnNames(new String[]{"ID"});
		update.setSql(insertSql);
		update.update(null, generatedKeyHolder);
		System.out.println(generatedKeyHolder.getKey());
	}

	@Test
	public void testBatchUpdate1() {
		String insertSql = "insert into goods(name) values('name5')";
		String[] batchSql = new String[] { insertSql, insertSql };
		jdbcTemplate.batchUpdate(batchSql);
	}

	@Test
	public void testBatchUpdate2() {
		String insertSql = "insert into goods(name) values(?)";
		final String[] batchValues = new String[] { "name5", "name6" };
		jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, batchValues[i]);
			}

			@Override
			public int getBatchSize() {
				return batchValues.length;
			}
		});
	}

	@Test
	public void testBatchUpdate3() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		String insertSql = "insert into goods(name) values(:myName)";
		UserModel model = new UserModel();
		model.setName("name5");
		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(new Object[] { model, model });
		namedParameterJdbcTemplate.batchUpdate(insertSql, params);
	}

	@Test
	public void testBatchUpdate5() {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("goods");
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("name", "name5");
		insert.executeBatch(new Map[] { valueMap, valueMap });
	}

}