import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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


        EmployeeModel employeeModel = new EmployeeModel();
        try {
            employeeModel.addExampleEmployees();
        } catch (SalaryOutOfBoundsException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame();


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        JTable table = new JTable(employeeModel);
        table.setAutoCreateRowSorter(true);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);


        JTextField[] addFields = new JTextField[4];
        JPanel menu = new JPanel();



        for (int i = 0; i < addFields.length; i++) {

            addFields[i] = new JTextField(employeeModel.getColumnName(i), 10);
            menu.add(addFields[i]);

        }

        JComboBox positionList = new JComboBox(Position.values());
        menu.add(positionList);

        JComboBox positionListTable = new JComboBox(Position.values());
        TableColumn positionColumn = table.getColumnModel().getColumn(4);
        positionColumn.setCellEditor(new DefaultCellEditor(positionListTable));

        JButton addButton = new JButton("Add employee");
        menu.add(addButton);
        panel.add(menu, BorderLayout.PAGE_START);

        JButton removeButton = new JButton("Remove employee");
        menu.add(removeButton);

        JButton exportButton = new JButton("Export data");
        menu.add(exportButton);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);


        addButton.addActionListener(e -> {
            Employee employee;
            try {
                employee = new Employee(addFields[0].getText(), addFields[1].getText(), Integer.parseInt(addFields[2].getText()), Integer.parseInt(addFields[3].getText()),
                        (Position) positionList.getSelectedItem());
                if (!employee.validateSalary())
                    throw new SalaryOutOfBoundsException();
                employeeModel.add(employee);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Incorrect salary or job seniority, please check if you typed numbers only", "Incorrect data", JOptionPane.ERROR_MESSAGE);
                ex.getCause();


            } catch (SalaryOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(frame, "Salary is inadequate to position, please amend it", "Incorrect data", JOptionPane.ERROR_MESSAGE);
                ex.getCause();
            }
        });

        removeButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
                employeeModel.remove(rowIndex);
            }

        });

        exportButton.addActionListener(e -> {

            int returnValue = fileChooser.showSaveDialog(menu);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                EmployeeExport.exportEmployees(file.getAbsolutePath(), employeeModel.getEmployeeList() );


            }});







            frame.setSize(1200,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(panel);


}

    }
