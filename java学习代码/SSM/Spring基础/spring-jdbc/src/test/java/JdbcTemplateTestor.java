import com.xiaxinyu.spring.jdbc.dao.EmployeeDao;
import com.xiaxinyu.spring.jdbc.entity.Employee;
import com.xiaxinyu.spring.jdbc.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JdbcTemplateTestor {
    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private EmployeeService employeeService;

    @Test
    public void testFindById(){
        Employee employee = employeeDao.findById(3308);
        System.out.println(employee);
    }

    @Test
    public void tesftFindByDname(){
        System.out.println(employeeDao.findByDname("市场部"));
    }

    @Test
    public void testFindMapByDname(){
        System.out.println(employeeDao.findMapByDname("研发部"));
    }

    @Test
    public void testInsert(){
        Employee employee = new Employee();
        employee.setEno(8888);
        employee.setEname("赵六");
        employee.setSalary(6666f);
        employee.setDname("研发部");
        employee.setHiredate(new Date());
        employeeDao.insert(employee);
    }

    @Test
    public void testUpdate(){
        Employee employee = employeeDao.findById(8888);
        employee.setSalary(employee.getSalary() + 1000);
        System.out.println(employeeDao.update(employee));
    }

    @Test
    public void testDelete(){
        System.out.println(employeeDao.delete(8888));
    }

    @Test
    public void testBatchImport(){
        employeeService.batchImport();
        System.out.println("批量导入成功");
    }

    @Test
    public void testStartImportJob(){
        employeeService.startImportJob();
    }
}
