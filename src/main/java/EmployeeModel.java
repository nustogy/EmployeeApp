import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel extends AbstractTableModel {

    private List<Employee> employeeList;

    private final String[] columnName = {"Name", "Surname","Job seniority", "Salary", "Position"};

    EmployeeModel() {

        employeeList = new ArrayList<Employee>();

    }

    public void add(Employee employee){
        employeeList.add(employee);
        fireTableDataChanged();
    }


    public void remove(int index) {
    employeeList.remove(index);
        fireTableDataChanged();

    }


    @Override
    public String getColumnName(int index){
        return columnName[index];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        if(columnIndex <2)
            return String.class;
        else if(columnIndex >=2 && columnIndex <4)
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
    public void setValueAt(Object updatedValue, int rowIndex, int columnIndex)
    {
        Employee employee = employeeList.get(rowIndex);
        if(columnIndex == 0) {
            employee.setName((String) updatedValue);
        }
        else if(columnIndex == 1) {
            employee.setSurname((String) updatedValue);
        }
        else if(columnIndex == 2) {
           employee.setJobSeniority((Integer) updatedValue);
        }
        else if(columnIndex == 3) {
            employee.setSalary((Integer) updatedValue);
        }
        else {
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


}
