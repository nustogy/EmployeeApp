import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModel extends AbstractTableModel {

    private List<Employee> employeeList;
    private List<Employee> employeeBeforeFilterList;


    private final String[] columnName = {"Name", "Surname", "Job seniority", "Salary", "Position"};

    EmployeeModel() {
        employeeList = new ArrayList<Employee>();
    }

    public void add(Employee employee) {
        employeeList.add(employee);
        if (employeeBeforeFilterList != null)
            employeeBeforeFilterList.add(employee);

        fireTableDataChanged();
    }

    public void remove(int index) {
        if (employeeBeforeFilterList != null) {
            Employee toRemove = employeeList.get(index);
            employeeBeforeFilterList.remove(toRemove);
        }

        employeeList.remove(index);
        fireTableDataChanged();
    }

    public void replaceAll(List<Employee> list) {
        resetFilter();
        employeeList = list;
        fireTableDataChanged();
    }

    public Employee get(int index) {
        return employeeList.get(index);
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeList);
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
        return employeeList == null ? 0 : employeeList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public void setValueAt(Object updatedValue, int rowIndex, int columnIndex) {
        Employee employee = employeeList.get(rowIndex);
        if (columnIndex == 0) {
            if (!((String) updatedValue).isEmpty())
                employee.setName((String) updatedValue);
        } else if (columnIndex == 1) {
            if (!((String) updatedValue).isEmpty())
                employee.setSurname((String) updatedValue);
        } else if (columnIndex == 2) {
            employee.setJobSeniority((Integer) updatedValue);
        } else if (columnIndex == 3) {
            if (employee.validateNewSalary((Integer) updatedValue))
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
        Employee employee = employeeList.get(rowIndex);
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

    public void addExampleEmployees() throws SalaryOutOfBoundsException, EmptyFieldException {

        Employee[][] employeesArr = {{new Employee("Anna", "Nowak", 15, 20000, Position.DIRECTOR)},
                {new Employee("Tomasz", "Kowalski", 4, 4000, Position.SPECIALIST)},
                {new Employee("Katarzyna", "Lewandowska", 1, 2500, Position.ASSISTANT)}};

        employeeList.add(employeesArr[0][0]);
        employeeList.add(employeesArr[1][0]);
        employeeList.add(employeesArr[2][0]);

    }

    public void filterBySalaryBelow(int salary) {
        if (employeeBeforeFilterList == null) {
            employeeBeforeFilterList = employeeList;
        }

        employeeList = employeeBeforeFilterList
                .stream()
                .filter(a -> a.getSalary() < salary)
                .collect(Collectors.toList());

        fireTableDataChanged();
    }


    public void filterBySalaryAbove(int salary) {

        if (employeeBeforeFilterList == null) {
            employeeBeforeFilterList = employeeList;
        }

        employeeList = employeeBeforeFilterList
                .stream()
                .filter(a -> a.getSalary() > salary)
                .collect(Collectors.toList());

        fireTableDataChanged();
    }

    public void resetFilter() {
        if (employeeBeforeFilterList != null) {
            employeeList = employeeBeforeFilterList;
            employeeBeforeFilterList = null;
            fireTableDataChanged();
        }
    }

    public void filterByKeyWord(String text) {
        if (employeeBeforeFilterList == null) {
            employeeBeforeFilterList = employeeList;
        }

        String lowerCaseText = text.toLowerCase();
        employeeList = employeeBeforeFilterList
                .stream()
                .filter(a -> a.getName().toLowerCase().equals(lowerCaseText)
                        || a.getSurname().toLowerCase().equals(lowerCaseText)
                        || String.valueOf(a.getJobSeniority()).equals(lowerCaseText)
                        || String.valueOf(a.getSalary()).equals(lowerCaseText)
                        || a.getPosition().toString().equals(lowerCaseText))

                .collect(Collectors.toList());

        fireTableDataChanged();

    }
}
