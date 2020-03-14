package tecnobrain;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        List<Employee>employeeList=new ArrayList<>();

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

        EmployeeImpl employeeImpl= null;
        try {
            employeeImpl = new EmployeeImpl(employeeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int budget= employeeImpl.salaryBudgetEmployee(employeeList,employee1);
        employeeImpl.resetSalaryBudgetEmployee();
        int budget2= employeeImpl.salaryBudgetEmployee(employeeList,employee2);


        System.out.println("budget: "+budget);
        System.out.println("budget: "+budget2);

        boolean moreThanOneManger = employeeImpl.moreThanOneManager(employeeList);
        System.out.println("moreThanOneManger: " + moreThanOneManger);

        boolean onlyOneCEO = employeeImpl.onlyOneCEO(employeeList);
        System.out.println("onlyOneCEO: " + onlyOneCEO);

        boolean circularReference = employeeImpl.circularReference(employeeList);
        System.out.println("circularReference: " + circularReference);

        boolean allManagersEmployees = employeeImpl.allManagersEmployees(employeeList);
        System.out.println("allManagersEmployees: " + allManagersEmployees);

    }
}
