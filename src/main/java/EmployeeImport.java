import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImport {

    public static List<Employee> importEmployees(String filePath) {
        // TODO: 22.05.2021 exception handling
        BufferedReader bufferedReader;
        FileReader fileReader = null;
        String line = "";
        Employee employee;
        List<Employee> importedEmployees = new ArrayList<Employee>();

        try {
            fileReader = new FileReader(filePath);
        } catch (
                FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        bufferedReader = new BufferedReader(fileReader);
        try {
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                employee = new Employee(null, null, 0, 0, null);
                String[] employeeLine = line.split("\\t");

                employee.setName(employeeLine[0]);
                employee.setSurname(employeeLine[1]);
                employee.setJobSeniority(Integer.parseInt(employeeLine[2]));
                employee.setSalary(Integer.parseInt(employeeLine[3]));
                employee.setPosition(Position.findPositionByPositionName(employeeLine[4]));


                importedEmployees.add(employee);

            }

        } catch (
                IOException ioException) {
            ioException.printStackTrace();
        } catch (SalaryOutOfBoundsException salaryOutOfBoundsException) {
            salaryOutOfBoundsException.printStackTrace();
        }
        return importedEmployees;
    }

}


