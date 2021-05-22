import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

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

        JButton importButton = new JButton("Import data");
        menu.add(importButton);

        JPanel filterPanel = new JPanel();
        JLabel[] filterLabels = {new JLabel("filter salary below the value: "), new JLabel("filter salary above the value: ")};

        JLabel label = new JLabel("Filter employees by salary:");
        filterPanel.add(label);
        JTextField filterField = new JTextField(10);
        filterPanel.add(filterField);

        JButton filterBelow = new JButton("Filter below");
        filterPanel.add(filterBelow);
        JButton filterAbove = new JButton("Filter above");
        filterPanel.add(filterAbove);
        JButton filterReset = new JButton("Reset filter");
        filterPanel.add(filterReset);

        JLabel searchLabel = new JLabel("Type phrase to search: ");
        filterPanel.add(searchLabel);

        JTextField searchField = new JTextField(20);
        filterPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        filterPanel.add(searchButton);


        panel.add(filterPanel, BorderLayout.PAGE_END);


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
                EmployeeExport.exportEmployees(file.getAbsolutePath(), employeeModel.getEmployeeList());


            }
        });

        importButton.addActionListener(e -> {

            int returnValue = fileChooser.showSaveDialog(menu);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                List<Employee> importedEmployees = EmployeeImport.importEmployees(file.getAbsolutePath());
                employeeModel.replaceAll(importedEmployees);
            }
        });

        filterBelow.addActionListener(e -> {

            employeeModel.filterBySalaryBelow(Integer.parseInt(filterField.getText()));

        });

        filterAbove.addActionListener(e -> {
            employeeModel.filterBySalaryAbove(Integer.parseInt(filterField.getText()));
        });

        filterReset.addActionListener(e -> {

            employeeModel.resetFilter();
        });

        searchButton.addActionListener(e -> {
            String textToSearch = searchField.getText();
            if (textToSearch.isEmpty())
                employeeModel.resetFilter();
            else
                employeeModel.filterByKeyWord(textToSearch);
        });


        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);


    }

}
