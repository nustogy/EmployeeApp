import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModel extends AbstractTableModel {

    private List<Employee> employeeModel;


    private final String[] columnName = {"Name", "Surname", "Job seniority", "Salary", "Position"};

    EmployeeModel() {

        employeeModel = new ArrayList<Employee>();

    }

    public void add(Employee employee) {
        employeeModel.add(employee);
        fireTableDataChanged();
    }


    public void remove(int index) {
        employeeModel.remove(index);
        fireTableDataChanged();

    }

    public Employee get(int index) {
        return employeeModel.get(index);
    }

    public List<Employee> getEmployeeList(){

        return new ArrayList<>(employeeModel);
    }

    @Override
    public String getColumnName(int index) {
        return columnName[index];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex < 2)
            return String.class;
        else if (columnIndex >= 2 && columnIndex < 4)
            return Integer.class;
        else
            return Position.class;
    }

    @Override
    public int getRowCount() {
        return employeeModel == null ? 0 : employeeModel.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public void setValueAt(Object updatedValue, int rowIndex, int columnIndex) {
        Employee employee = employeeModel.get(rowIndex);
        if (columnIndex == 0) {
            employee.setName((String) updatedValue);
        } else if (columnIndex == 1) {
            employee.setSurname((String) updatedValue);
        } else if (columnIndex == 2) {
            employee.setJobSeniority((Integer) updatedValue);
        } else if (columnIndex == 3) {
            // TODO: 21.05.2021 salary validation 
            employee.setSalary((Integer) updatedValue);
        } else {
            employee.setPosition((Position) updatedValue);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employeeModel.get(rowIndex);
        if (columnIndex == 0)
            return employee.getName();
        else if (columnIndex == 1)
            return employee.getSurname();
        else if (columnIndex == 2)
            return employee.getJobSeniority();
        else if (columnIndex == 3)
            return employee.getSalary();
        else
            return employee.getPosition();

    }

    public void addExampleEmployees() throws SalaryOutOfBoundsException {

        Employee[][] employeesArr = {{new Employee("Anna", "Nowak", 15, 20000, Position.DIRECTOR)},
                {new Employee("Tomasz", "Kowalski", 4, 4000, Position.SPECIALIST)},
                {new Employee("Katarzyna", "Lewandowska", 1, 2500, Position.ASSISTANT)}};

        employeeModel.add(employeesArr[0][0]);
        employeeModel.add(employeesArr[1][0]);
        employeeModel.add(employeesArr[2][0]);

    }
}
