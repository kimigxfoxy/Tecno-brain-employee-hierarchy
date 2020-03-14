package tecnobrain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeImplTest {

    EmployeeImpl employeeImpl;

    public EmployeeImplTest() {
        List<Employee> employeeList=new ArrayList<>();

        Employee employee1=new Employee();
        employee1.setEmployeeId(1);
        employee1.setManagerId(null);
        employee1.setSalary(1000);

        employeeList.add(employee1);

        Employee employee2=new Employee();
        employee2.setEmployeeId(2);
        employee2.setManagerId(1);
        employee2.setSalary(800);

        employeeList.add(employee2);

        Employee employee3=new Employee();
        employee3.setEmployeeId(3);
        employee3.setManagerId(1);
        employee3.setSalary(500);

        employeeList.add(employee3);

        Employee employee4=new Employee();
        employee4.setEmployeeId(4);
        employee4.setManagerId(1);
        employee4.setSalary(500);

        employeeList.add(employee4);

        Employee employee5=new Employee();
        employee5.setEmployeeId(5);
        employee5.setManagerId(2);
        employee5.setSalary(500);

        employeeList.add(employee5);

        Employee employee6=new Employee();
        employee6.setEmployeeId(6);
        employee6.setManagerId(2);
        employee6.setSalary(500);

        employeeList.add(employee6);

        try {
            employeeImpl=new EmployeeImpl(employeeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Test
    public void hasMoreThanOneManager(){
        assertEquals(employeeImpl.moreThanOneManager(employeeImpl.getEmployees()),false);
    }

    @Test
    public void hasOnlyOneCEO(){
        assertEquals(employeeImpl.onlyOneCEO(employeeImpl.getEmployees()),true);
        employeeImpl.getEmployees().get(1).setManagerId(null);
        assertEquals(employeeImpl.onlyOneCEO(employeeImpl.getEmployees()),false);

    }

    @Test
    public void hasCircularReference(){
        assertEquals(employeeImpl.circularReference(employeeImpl.getEmployees()),false);
        employeeImpl.getEmployees().get(1).setManagerId(5);
        assertEquals(employeeImpl.onlyOneCEO(employeeImpl.getEmployees()),true);
    }

    @Test
    public void allManagersEmployees(){
        assertEquals(employeeImpl.allManagersEmployees(employeeImpl.getEmployees()),true);
        employeeImpl.getEmployees().get(1).setManagerId(8);
        assertEquals(employeeImpl.allManagersEmployees(employeeImpl.getEmployees()),false);
    }

    @Test
    public void salaryBudgetEmployee(){
        int budget= employeeImpl.salaryBudgetEmployee(employeeImpl.getEmployees(),employeeImpl.getEmployees().get(0));
        assertEquals(budget,3800);
        employeeImpl.resetSalaryBudgetEmployee();
        int budget2= employeeImpl.salaryBudgetEmployee(employeeImpl.getEmployees(),employeeImpl.getEmployees().get(1));
        assertEquals(budget2,1800);

    }

}
