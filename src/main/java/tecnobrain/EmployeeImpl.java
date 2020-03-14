package tecnobrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeImpl {

    List<Employee>employees;

    public EmployeeImpl(List<Employee> employees) throws  Exception{
        if(moreThanOneManager(employees)){
            throw new Exception("List has more than one manager per employee");
        }

        if(!onlyOneCEO(employees)){
            throw new Exception("List has more than one CEO");
        }

        if(circularReference(employees)){
            throw new Exception("List has circular reference between employees and managers");
        }

        if(!allManagersEmployees(employees)){
            throw new Exception("Not all managers are employees");
        }

        this.employees = employees;
    }

    public List<Employee> getEmployees(){
        return employees;
    }

    public boolean moreThanOneManager(List<Employee> employees){

        boolean moreThanOneManager=false;
        HashMap<Integer, Integer>hashMap=new HashMap<>();

        for(int i=0;i<employees.size();i++){
            Integer employeeId=employees.get(i).getEmployeeId();
            Integer managerId=employees.get(i).getManagerId();

            if(!hashMap.containsKey(employeeId)){
                hashMap.put(employeeId,managerId);
            }else{
                if(hashMap.get(employeeId)!=null){
                    moreThanOneManager=true;
                    break;
                }else{
                    hashMap.put(employeeId,managerId);
                }
            }
        }

        return moreThanOneManager;
    }


    public boolean onlyOneCEO(List<Employee> employees){

        boolean onlyOneCEO=true;
        int countCEOs=0;
        for(int i=0;i<employees.size();i++){
            Integer managerId=employees.get(i).getManagerId();

            if(managerId==null){
                countCEOs++;
            }

            if(countCEOs>1){
                onlyOneCEO=false;
                break;
            }

        }

        return onlyOneCEO;
    }

    public boolean circularReference(List<Employee> employees){
        boolean circularReference=false;

        for(int i=0;i<employees.size();i++){
            Integer employeeId=employees.get(i).getEmployeeId();
            Integer managerId=employees.get(i).getManagerId();

            for (int j = 0; j < employees.size(); j++) {
                if(i!=j){
                    Integer employeeId2=employees.get(j).getEmployeeId();
                    Integer managerId2=employees.get(j).getManagerId();
                    if(employeeId==managerId2 && managerId==employeeId2){
                        circularReference=true;
                        return circularReference;
                    }
                }

            }

        }

        return circularReference;

    }


    public boolean allManagersEmployees(List<Employee> employees){
        boolean allManagersEmployees=true;

        for(int i=0;i<employees.size();i++){
            Integer managerId=employees.get(i).getManagerId();

            if(managerId!=null) {
                for (int j = 0; j < employees.size(); j++) {
                    if(i!=j) {
                        Integer employeeId = employees.get(j).getEmployeeId();
                        if (employeeId == managerId) {
                            break;
                        }

                        if (j == employees.size() - 1) {
                            allManagersEmployees = false;
                            return allManagersEmployees;
                        }
                    }
                }
            }
        }
        return allManagersEmployees;
    }


    int salaryBudgetEmployee =0;

    public int salaryBudgetEmployee(List<Employee> employees, Employee employee) {

        List<Employee> directChildrenList=new ArrayList<>();
        for(int i=0;i<employees.size();i++){
            Integer employeeId=employees.get(i).getEmployeeId();
            Integer managerId=employees.get(i).getManagerId();
            if(employee.getEmployeeId()!=employeeId) {
                if (employee.getEmployeeId() == managerId) {
                    directChildrenList.add(employees.get(i));
                    salaryBudgetEmployee(employees, employees.get(i));
                }
            }
        }

        Integer sumDirectChildren = directChildrenList.stream()
                .mapToInt(employee1-> employee1.getSalary())
                .sum();

        Integer employeeSalary=employee.getSalary();

        if(sumDirectChildren==0) {
            salaryBudgetEmployee = salaryBudgetEmployee + employeeSalary + sumDirectChildren;
        }else{
            salaryBudgetEmployee = salaryBudgetEmployee +employeeSalary;
        }

        return salaryBudgetEmployee;

    }

    public void resetSalaryBudgetEmployee(){
        this.salaryBudgetEmployee =0;
    }
}
