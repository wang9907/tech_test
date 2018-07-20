package com.summer.tech.ehcache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.summer.tech.memcached.User;

import bsh.EvalError;
import bsh.Interpreter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Direction;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import net.sf.ehcache.search.attribute.DynamicAttributesExtractor;

public class CacheManagerTest {
	public void test() {
		CacheManager cacheManager = CacheManager.create();
		CacheConfiguration cacheConfig = new CacheConfiguration();
		cacheConfig.name("cache1").maxBytesLocalHeap(100, MemoryUnit.MEGABYTES);
		Searchable searchable = new Searchable();
		// 指定Cache的Searchable对象。
		cacheConfig.searchable(searchable);
		// 如下指定也行
		// cacheConfig.addSearchable(searchable);
		Cache cache1 = new Cache(cacheConfig);
		cacheManager.addCache(cache1);
	}

	@Test
	public void dynamicExtractor() {
		CacheManager cacheManager = CacheManager.create();
		Cache userCache = cacheManager.getCache("userCache");
		userCache
				.registerDynamicAttributesExtractor(new DynamicAttributesExtractor() {

					@Override
					public Map<String, Object> attributesFor(Element element) {
						Map<String, Object> attrMap = new HashMap<String, Object>();
						attrMap.put("hitCount", element.getHitCount());
						return attrMap;
					}

				});
		this.listSearchableAttrs(userCache); // key、value和name
		userCache.put(new Element("1", new User()));
		this.listSearchableAttrs(userCache); // key、value、name和hitCount
	}

	/**
	 * 输出当前Ehcache中可查询的属性
	 *
	 * @param cache
	 */
	private void listSearchableAttrs(Ehcache cache) {
		Set<Attribute> attrSet = cache.getSearchAttributes();
		for (Attribute attr : attrSet) {
			System.out.println(attr.getAttributeName());
		}
	}

	@Test
	public void setSearchAttrInProgram() {
		CacheManager cacheManager = CacheManager.create();
		Cache cache = cacheManager.getCache("searchableCache");
		CacheConfiguration cacheConfig = cache.getCacheConfiguration();
		Searchable searchable = cacheConfig.getSearchable();
		SearchAttribute searchAttribute = new SearchAttribute();
		searchAttribute.name("hello");
		searchable.addSearchAttribute(searchAttribute);
		this.listSearchableAttrs(cache);
	}

	@Test
	public void testSearchAttrInProgram() {
		CacheManager cacheManager = CacheManager.create();
		CacheConfiguration cacheConfig = new CacheConfiguration();
		cacheConfig.name("cacheName").maxBytesLocalHeap(100,
				MemoryUnit.MEGABYTES);
		// 新建一个Searchable对象
		Searchable searchable = new Searchable();
		// 给Cache配置Searchable对象，表明该Cache是一个可查询的Cache
		cacheConfig.searchable(searchable);
		// 新建一个查询属性
		SearchAttribute searchAttribute = new SearchAttribute();
		// 指定查询属性的名称和属性提取器的类名
		searchAttribute.name("查询属性名称");
		// searchAttribute.className("属性提取器的类名");
		// Searchalbe对象添加查询属性
		searchable.addSearchAttribute(searchAttribute);
		// 使用CacheConfig创建Cache对象
		Cache cache = new Cache(cacheConfig);
		// 把Cache对象纳入CacheManager的管理中
		cacheManager.addCache(cache);
		this.listSearchableAttrs(cache);
	}

	@Test
	public void search() {
		CacheManager cacheManager = CacheManager.create();
		Cache userCache = cacheManager.getCache("userCache");
		User user;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setName("name" + (i % 2));
			user.setUserId("" + i);
			userCache.put(new Element(user.getUserId(), user));
		}
		Query query = userCache.createQuery();
		Attribute<String> name = userCache.getSearchAttribute("name");
		// 给当前query添加一个筛选条件——可查询属性name的值等于“name1”
		query.addCriteria(name.eq("name1"));

		Attribute<Integer> age = userCache.getSearchAttribute("age");
		Attribute<String> unitNo = userCache.getSearchAttribute("unitNo");
		Attribute<String> mobile = userCache.getSearchAttribute("mobile");
		query.addCriteria(age.between(25, 35).and(unitNo.eq("002")));
		// 或者使用两次addCriteria
		// query.addCriteria(age.between(25, 35)).addCriteria(unitNo.eq("002"));

		query.addCriteria(age.gt(35).and(unitNo.eq("002").or(unitNo.eq("003")))
				.and(mobile.ilike("137*")));
		query.addCriteria(unitNo.ne("002").and(age.lt(30)));
		// 或者使用not()方法
		query.addCriteria(unitNo.eq("002").not().and(age.lt(30)));
		// 查询默认是包含key和value里面的数据
		// 查询内容包含查询属性，
		query.includeAttribute(name, age);
		// 对单位编号进行分组
		query.addGroupBy(unitNo);
		// 查询结果中包含age的平均值和age的最大值,统计信息
		query.includeAggregator(age.average(), age.max(), age.count());
		// 查询结果按部门编号的升序和年龄的降序进行排列
		query.addOrderBy(unitNo, Direction.ASCENDING).addOrderBy(age,
				Direction.DESCENDING);

		// 查询结果
		// 执行查询操作，返回查询结果Results
		Results results = query.execute();
		// 获取Results中包含的所有的Result对象
		List<Result> resultList = results.all();
		if (resultList != null && !resultList.isEmpty()) {
			for (Result result : resultList) {
				// 结果中包含key时可以获取key
				if (results.hasKeys()) {
					result.getKey();
				}
				// 结果中包含value时可以获取value
				if (results.hasValues()) {
					result.getValue();
				}
				// 结果中包含属性时可以获取某个属性的值
				if (results.hasAttributes()) {
					Attribute<String> attribute = userCache
							.getSearchAttribute("name");
					result.getAttribute(attribute);
				}
				// 结果中包含统计信息时可以获取统计信息组成的List
				if (results.hasAggregators()) {
					result.getAggregatorResults();
				}

				// 多个统计信息将会组成一个List进行返回
				List<Object> aggregatorResults = result.getAggregatorResults();
				Number averageAge = (Number) aggregatorResults.get(0);
				Integer maxAge = (Integer) aggregatorResults.get(1);
				System.out.println(averageAge + "---" + maxAge);
			}
		}

	}

	@Test
	public void beanShell() throws EvalError {
		CacheManager cacheManager = CacheManager.create();
		Cache userCache = cacheManager.getCache("userCache");
		User user;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setName("name" + (i % 2));
			user.setUserId("" + i);
			userCache.put(new Element(user.getUserId(), user));
		}
		// BeanShell解释器，需引入BeanShell相关jar包
		Interpreter interpreter = new Interpreter();
		Query query = userCache.createQuery().includeValues();
		// Interpreter进行计算的字符串中出现的变量都需要放入Interpreter的环境中
		interpreter.set("query", query);// 把query放入Interpreter环境中
		// 把age放入Interpreter环境中
		interpreter.set("age", userCache.getSearchAttribute("age"));
		String queryStr = "query.addCriteria(age.lt(30)).execute();";
		// BeanShell执行字符串表达式对userCache进行查询，并返回Results
		Results results = (Results) interpreter.eval(queryStr);
		for (Result result : results.all()) {
			System.out.println(result);
		}
		results.discard();
	}

	@Test
	public void testConrrent() {
		CacheManager cacheManager = CacheManager.create();
		cacheManager.addCache("test");
		Cache cache = cacheManager.getCache("test");
		final String key = "abc";
		cache.acquireWriteLockOnKey(key);
		try {
			cache.put(new Element(key, "123"));
		} finally {
			System.out.println(cache.get(key));
			cache.releaseWriteLockOnKey(key);
		}
	}
}
