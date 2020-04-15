package com.summer.tech.spring.mybatis.service.impl;

import com.summer.tech.spring.mybatis.dao.StudentDao;
import com.summer.tech.spring.mybatis.entity.StudentBo;
import com.summer.tech.spring.mybatis.service.ICacheService;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service(ICacheService.SERVICEID)
public class CacheServiceImpl implements ICacheService {
	@Resource
	private StudentDao studentDao;

	@Override
	//@Cacheable("studentCache")
	public List<StudentBo> getAllStudent() {
		List<StudentBo> list = studentDao.selectAllStudent();
		if (list != null && list.size() > 0) {
			for (StudentBo student : list) {
				System.out.println("查询得到的学生的姓名：" + student.getName()
						+ ",学生的年龄：" + student.getAge() + "学生地址："
						+ student.getAddress());
			}
		}
		return list;
	}

	@Override
	public StudentBo getStudentById(int id) {
		StudentBo student = studentDao.selectByPrimaryKey(id);
		System.out.println("查询id为" + id + "的学生姓名是：" + student.getName()
				+ ",住址：" + student.getAddress());

		return student;
	}

    @Override
	//添加事务注解
	//1.使用 propagation 指定事务的传播行为, 即当前的事务方法被另外一个事务方法调用时
	//如何使用事务, 默认取值为 REQUIRED, 即使用调用方法的事务
	//REQUIRES_NEW: 事务自己的事务, 调用的事务方法的事务被挂起.
	//2.使用 isolation 指定事务的隔离级别, 最常用的取值为 READ_COMMITTED
	//3.默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚. 也可以通过对应的
	//属性进行设置. 通常情况下去默认值即可.
	//4.使用 readOnly 指定事务是否为只读. 表示这个事务只读取数据但不更新数据,
	//这样可以帮助数据库引擎优化事务. 若真的事一个只读取数据库值的方法, 应设置 readOnly=true
	//5.使用 timeout 指定强制回滚之前事务可以占用的时间.
	@Transactional(propagation= Propagation.REQUIRES_NEW,
			isolation= Isolation.READ_COMMITTED,
			noRollbackFor={BeansException.class},
			rollbackFor = IOException.class,
			readOnly=false,
			timeout=3)
    public void insert() {
		// 成功
		StudentBo studentBo=new StudentBo();
		studentBo.setName("小明1");
		studentBo.setAge(8);
		studentBo.setAddress("宝安裕丰花园1");
		studentDao.insert(studentBo);

		// 失败
		studentBo=new StudentBo();
		studentBo.setName("小明反反复复反反复复反反复复反反复复反反复复反反复复凤飞飞");
		studentBo.setAge(8);
		studentBo.setAddress("宝安裕丰花园");
		studentDao.insert(studentBo);
    }

}