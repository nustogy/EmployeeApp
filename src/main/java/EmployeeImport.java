import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImport {

    public static List<Employee> importEmployees(String filePath) throws IOException, EmptyFieldException, SalaryOutOfBoundsException {


        BufferedReader bufferedReader;
        FileReader fileReader = null;
        String line = "";
        Employee employee;
        List<Employee> importedEmployees = new ArrayList<Employee>();


        fileReader = new FileReader(filePath);


        bufferedReader = new BufferedReader(fileReader);

        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {

            String[] employeeLine = line.split("\\t");
            employee = new Employee(employeeLine[0], employeeLine[1], Integer.parseInt(employeeLine[2]), Integer.parseInt(employeeLine[3]), Position.findPositionByPositionName(employeeLine[4]));


            importedEmployees.add(employee);

        }


        return importedEmployees;
    }

}


