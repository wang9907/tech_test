package com.summer.tech.spring.mybatis;

import com.summer.tech.spring.mybatis.entity.FemaleStudent;
import com.summer.tech.spring.mybatis.entity.MaleStudent;
import com.summer.tech.spring.mybatis.entity.Student;
import com.summer.tech.spring.mybatis.entity.User;
import com.summer.tech.spring.mybatis.mapper.StudentMapper;
import com.summer.tech.spring.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class SpringMybatisTest {

    @Resource
    StudentMapper studentMapper;

    @Resource
    UserMapper userMapper;
    
    @Test
    public void insertTest(){
        Student Student=new Student();
        Student.setName("小明1");
        Student.setAge(8);
        Student.setAddress("宝安裕丰花园1");
        studentMapper.insert(Student);
    }

    @Test
    public void queryTest(){
//        int id = 1;
//        Student Student = studentMapper.selectByPrimaryKey(id);
//        System.out.println(Student);

       /* List<Student> students =studentMapper.selectStudentAndIdcard();
        System.out.println(students.size());
        for (Student student:students){
            System.out.println(student.getId()+":"+student.getStudentIdcard().getIdcard());
            System.out.println(student.getId()+":"+student.getBankCards().size());
            if(student.getGender()==1){
                MaleStudent maleStudent = (MaleStudent)student;
                System.out.println(student.getId()+":"+maleStudent.getMaleHealthForms().size());
            }else{
                FemaleStudent femaleStudent = (FemaleStudent)student;
                System.out.println(student.getId()+":"+femaleStudent.getFemaleHealthForms().size());
            }
        }*/
        Student student = studentMapper.selectByPrimaryKey(1);
        if(student.getGender()==1){
            MaleStudent maleStudent = (MaleStudent)student;
            System.out.println(student.getId()+":"+maleStudent.getMaleHealthForms().size());
        }else{
            FemaleStudent femaleStudent = (FemaleStudent)student;
            System.out.println(student.getId()+":"+femaleStudent.getFemaleHealthForms().size());
        }
    }

    @Test
    public void annotationUpdateTest(){
        User user = new User();
        user.setName("网络小兵是是 ");
        user.setGender((byte)1);
        user.setAddress("我来自中国");
        user.setBirthday(new Date());
        userMapper.insert(user);

        userMapper.deleteByPrimaryKey(3);
    }

    @Test
    public void annotationQueryTest(){
        List<User> list = userMapper.findAll();
        for(User user:list){
            System.out.println(user.getName());
            System.out.println(user.getAccounts());
        }
    }
}
