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

        EmployeeModel employeeModel = new EmployeeModel();
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

        JButton addButton = new JButton("Add employee");
        menu.add(addButton);
        panel.add(menu, BorderLayout.PAGE_START);

        JButton removeButton = new JButton("Remove employee");
        menu.add(removeButton);


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addButton.addActionListener(e -> {
            Employee employee = new Employee(addFields[0].getText(), addFields[1].getText(), Integer.parseInt(addFields[2].getText()), Integer.parseInt(addFields[3].getText()),
                    Position.setPositionByPositionName(positionList.getSelectedItem().toString()));
            employeeModel.add(employee);
        });

        removeButton.addActionListener(e -> {
            if(table.getSelectedRow() != -1){
                int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
                employeeModel.remove(rowIndex);
            }

        });

        Employee[][] employeesArr = {{new Employee("Anna", "Nowak", 15, 15000, Position.DIRECTOR)},
                {new Employee("Tomasz", "Kowalski", 4, 4000, Position.SPECIALIST)},
                {new Employee("Katarzyna", "Lewandowska", 1, 3000, Position.ASSISTANT)}};

        employeeModel.add(employeesArr[0][0]);
        employeeModel.add(employeesArr[1][0]);
        employeeModel.add(employeesArr[2][0]);
        employeeModel.fireTableStructureChanged();





        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);



    }
}
