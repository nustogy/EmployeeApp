import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }

    public static void createAndShowGUI() {

        EmployeeList employeeList = new EmployeeList();
        JFrame frame = new JFrame();


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        String[] columnName = {"Name", "Surname","Job seniority", "Salary", "Position"};

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);


        final JTextField[] addFields = new JTextField[4];
        JPanel menu = new JPanel();


        for (int i = 0; i < addFields.length; i++) {

            addFields[i] = new JTextField(columnName[i], 10);
            menu.add(addFields[i]);

        }

        JComboBox positionList = new JComboBox(Position.values());
        menu.add(positionList);

        JButton addButton = new JButton("Add employee");
        menu.add(addButton);
        panel.add(menu, BorderLayout.PAGE_START);


        addButton.addActionListener(e -> {
            Employee employee = new Employee(addFields[0].getText(), addFields[1].getText(), Integer.parseInt(addFields[2].getText()), Integer.parseInt(addFields[3].getText()), Position.valueOf(positionList.getSelectedItem().toString()));
            employeeList.addEmployee(employee);
            textArea.setText(employeeList.toString());

        });


        Employee[][] employeesArr = {{new Employee("Anna", "Nowak", 15, 15000, Position.DIRECTOR)},
                {new Employee("Tomasz", "Kowalski", 4, 4000, Position.SPECIALIST)},
                {new Employee("Anna", "Nowak", 1, 3000, Position.ASSISTANT)}};

        employeeList.addEmployee(employeesArr[0][0]);
        employeeList.addEmployee(employeesArr[1][0]);
        employeeList.addEmployee(employeesArr[2][0]);
        textArea.setText(employeeList.toString());


        panel.add(textArea, BorderLayout.CENTER);


        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

    }
}
