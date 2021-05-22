import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImport {

    public static List<Employee> importEmployees(String filePath) {
        // TODO: 22.05.2021 exception handling:q

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

                String[] employeeLine = line.split("\\t");
                employee = new Employee(employeeLine[0], employeeLine[1], Integer.parseInt(employeeLine[2]), Integer.parseInt(employeeLine[3]), Position.findPositionByPositionName(employeeLine[4]));



                importedEmployees.add(employee);

            }

        } catch (
                IOException ioException) {
            ioException.printStackTrace();
        } catch (SalaryOutOfBoundsException salaryOutOfBoundsException) {
            salaryOutOfBoundsException.printStackTrace();
        } catch (EmptyFieldException e) {
            e.printStackTrace();
        }
        return importedEmployees;
    }

}


