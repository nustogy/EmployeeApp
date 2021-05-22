import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeExport {

    public static void exportEmployees(String filePath, List<Employee> list){

        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        String line = "";
        try {
            fileWriter = new FileWriter(filePath);
            bufferedWriter = new BufferedWriter(fileWriter);

            line = "Name\tSurname\tJob Seniority\tSalary\t Position\n";
            bufferedWriter.write(line);
            for (int i = 0; i < list.size(); i++) {
                line = list.get(i).toString();
                bufferedWriter.write(line);
            }

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException ex) {
            ex.getMessage();
        }

    }
}
