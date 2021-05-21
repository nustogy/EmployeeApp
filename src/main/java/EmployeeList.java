import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    List<Employee> employeeList;

    EmployeeList() {

        employeeList = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public List<Employee> getList() {

        return new ArrayList<>(employeeList);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < employeeList.size(); i++) {
            stringBuilder.append((i + 1 + " " + employeeList.get(i)) + "\n");
        }
        return stringBuilder.toString();
    }
}
