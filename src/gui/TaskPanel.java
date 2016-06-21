package gui;

import database.*;

import static gui.GeneralWindow.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;

public class TaskPanel {
    private JPanel task, addTaskEntity, taskEntity;
    private GeneralWindow generalWindow;

    /*task*/
    private JButton addTask;
    private static final int HEIGHT_ADD_TASK_BUTTON = 30;
    private TaskTableModel taskTableModel;
    private JTable taskTable;
    private JScrollPane taskScroll;

    /*taskAdd*/
    private JLabel errorEmptyLabel;
    private JTextField nameTaskTextField, priceTextField, countTextField;
    private JTextPane descriptionTextField;
    private JButton cancelButton, addTaskEntityButton;
    private JComboBox<Integer> priorityNumberComboBox;

    public TaskPanel(GeneralWindow generalWindow) {
        this.generalWindow = generalWindow;
    }

    protected void initAndShowTaskPanel() {
        if (task == null) {
            task = new JPanel(null);

            addTask = new JButton("ДОБАВИТЬ");
            addTask.setSize(generalWindow.WIDTH_GENERAL_PANEL, HEIGHT_ADD_TASK_BUTTON);
            addTask.setLocation(0, 0);
            addTask.addActionListener(new Listener());
            task.add(addTask);

            taskTableModel = new TaskTableModel();
            taskTable = new JTable(taskTableModel);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            taskTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            taskScroll = new JScrollPane(taskTable);
            taskScroll.setLocation(0, HEIGHT_ADD_TASK_BUTTON);
            taskScroll.setSize(generalWindow.WIDTH_GENERAL_PANEL, generalWindow.HEIGHT_GENERAL_PANEL - HEIGHT_ADD_TASK_BUTTON);
            taskTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        initAndShowTaskEntityPanel((String) taskTableModel.getValueAt(taskTable.getSelectedRow(), 0));
                    }
                }
            });
            taskTable.setOpaque(false);
            taskTable.setDefaultRenderer (Object.class, new DefaultTableCellRenderer() {

                public Component getTableCellRendererComponent (JTable table, Object value,
                                                                boolean isSelected, boolean hasFocus,
                                                                int row, int column ) {
                    JLabel component = ( JLabel ) super
                            .getTableCellRendererComponent ( table, value, isSelected, hasFocus, row,
                                    column );
                    component.setForeground ( isSelected ? Color.BLUE : Color.BLACK );
                    component.setOpaque ( isSelected );
                    return component;
                }
            } );
            taskTable.getTableHeader().setReorderingAllowed ( false );
            taskScroll.setOpaque(false);
            taskScroll.getViewport().setOpaque(false);
            task.add(taskScroll);
            updateTableTask();

            generalWindow.generalPanel.add("task", task);
        }
        generalWindow.generalLayout.show(generalWindow.generalPanel, "task");
    }

    protected void updateTableTask() {
        generalWindow.taskList = (ArrayList<TaskEntity>) generalWindow.db.getTaskDAO().getAll();
        taskTableModel.removeIsAll();

        for (int k = 1; k <= generalWindow.taskList.size(); k++) {
            //System.out.println(taskList.get(k-1).toString());
            for (int i = 0; i < generalWindow.taskList.size(); i++) {
                if (generalWindow.taskList.get(i).getNumberPriority() == k) {
                    String[] table = {generalWindow.taskList.get(i).getName()};
                    taskTableModel.addDate(table);
                    break;
                }
            }
        }
        taskTable.setVisible(false);
        taskTable.setVisible(true);
    }

    private void initAndShowAddTaskPanel() {
        if (addTaskEntity == null) {
            addTaskEntity = new JPanel(null);

            JLabel nameTaskLabel = new JLabel("* НАЗВАНИЕ ТОВАРА:");
            nameTaskLabel.setLocation(0, 0);
            nameTaskLabel.setSize(WIDTH_GENERAL_PANEL, 20);
            nameTaskLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameTaskLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            addTaskEntity.add(nameTaskLabel);

            nameTaskTextField = new JTextField();
            nameTaskTextField.setLocation(20, 20);
            nameTaskTextField.setSize(WIDTH_GENERAL_PANEL - 40, 20);
            addTaskEntity.add(nameTaskTextField);

            JLabel priceLabel = new JLabel("* ЦЕНА ТОВАРА (за едн.):");
            priceLabel.setLocation(20, 50);
            priceLabel.setSize(WIDTH_GENERAL_PANEL/2, 20);
            priceLabel.setVerticalAlignment(SwingConstants.CENTER);
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            addTaskEntity.add(priceLabel);

            priceTextField = new JTextField();
            priceTextField.setLocation((WIDTH_GENERAL_PANEL/2)  + 25, 50);
            priceTextField.setSize(100, 20);
            priceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    priceTextField.setText(stringCastToDoubleInString(priceTextField.getText()));
                }
            });
            addTaskEntity.add(priceTextField);

            JLabel countLabel = new JLabel("* КОЛИЧЕСТВО ТОВАРА (едн.):");
            countLabel.setLocation(50, 80);
            countLabel.setSize(WIDTH_GENERAL_PANEL/2 + 30, 20);
            countLabel.setVerticalAlignment(SwingConstants.CENTER);
            countLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            addTaskEntity.add(countLabel);

            countTextField = new JTextField();
            countTextField.setLocation((WIDTH_GENERAL_PANEL/2)  + 85, 80);
            countTextField.setSize(50, 20);
            countTextField.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char key = e.getKeyChar();

                    if(Character.isWhitespace(key) || !Character.isDigit(key) || countTextField.getText().length() >= 5) {
                        e.consume();
                    } else {

                    }
                }
            });
            addTaskEntity.add(countTextField);

            JLabel priorityNumberLabel = new JLabel("* ПРИОРИТЕТ ТОВАРА:");
            priorityNumberLabel.setLocation(35, 110);
            priorityNumberLabel.setSize(WIDTH_GENERAL_PANEL/2, 20);
            priorityNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
            priorityNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            addTaskEntity.add(priorityNumberLabel);

            priorityNumberComboBox = new JComboBox<Integer>();
            priorityNumberComboBox.setLocation((WIDTH_GENERAL_PANEL/2)  + 40, 110);
            priorityNumberComboBox.setSize(50, 20);
            addTaskEntity.add(priorityNumberComboBox);

            JLabel descriptionLabel = new JLabel("ОПИСАНИЕ ТОВАРА:");
            descriptionLabel.setLocation(20, 130);
            descriptionLabel.setSize(WIDTH_GENERAL_PANEL - 40, 20);
            descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
            descriptionLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            addTaskEntity.add(descriptionLabel);

            descriptionTextField = new JTextPane();
            JScrollPane scrollDescription = new JScrollPane(descriptionTextField);
            scrollDescription.setLocation(20, 155);
            scrollDescription.setSize(WIDTH_GENERAL_PANEL - 40, (HEIGHT_GENERAL_PANEL - 160) - 100);
            addTaskEntity.add(scrollDescription);

            errorEmptyLabel = new JLabel();
            errorEmptyLabel.setLocation(20, HEIGHT_GENERAL_PANEL - 80);
            errorEmptyLabel.setSize(WIDTH_GENERAL_PANEL - 40, 20);
            errorEmptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            errorEmptyLabel.setVerticalAlignment(SwingConstants.CENTER);
            errorEmptyLabel.setForeground(Color.RED);
            addTaskEntity.add(errorEmptyLabel);

            int widthButton = WIDTH_GENERAL_PANEL/3;

            cancelButton = new JButton("ОТМЕНА");
            cancelButton.setLocation((WIDTH_GENERAL_PANEL/3)-(widthButton/2) - 10, HEIGHT_GENERAL_PANEL - 40);
            cancelButton.setSize(widthButton, 20);
            cancelButton.addActionListener(new Listener());
            addTaskEntity.add(cancelButton);

            addTaskEntityButton = new JButton("ДОБАВИТЬ");
            addTaskEntityButton.setLocation(((WIDTH_GENERAL_PANEL/3)*2)-(widthButton/2) + 10, HEIGHT_GENERAL_PANEL - 40);
            addTaskEntityButton.setSize(widthButton, 20);
            addTaskEntityButton.addActionListener(new Listener());
            addTaskEntity.add(addTaskEntityButton);

            generalWindow.generalPanel.add("addTask", addTaskEntity);
        }
        nameTaskTextField.setText(null);
        priceTextField.setText(null);
        countTextField.setText(null);
        descriptionTextField.setText(null);

        errorEmptyLabel.setVisible(false);

        priorityNumberComboBox.removeAllItems();
        for (int i = 1; i <= generalWindow.taskList.size() + 1; i++) {
            priorityNumberComboBox.addItem(i);
        }
        priorityNumberComboBox.setVisible(false);
        priorityNumberComboBox.setVisible(true);

        generalWindow.generalLayout.show(generalWindow.generalPanel, "addTask");
    }

    private void initAndShowTaskEntityPanel(String nameEntity) {
        if (taskEntity == null) {
            taskEntity = new JPanel(null);

        }
    }


    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addTask) {
                initAndShowAddTaskPanel();
            } else if (e.getSource() == addTaskEntityButton) {
                if (nameTaskTextField.getText() == null || priceTextField.getText() == null ||
                        countTextField == null || nameTaskTextField.getText().equals("") ||
                        priceTextField.getText().equals("") || countTextField.getText().equals("")) {
                    errorEmptyLabel.setText("все поля позначеные '*' должны быть заполнены");
                    errorEmptyLabel.setVisible(true);
                } else {
                    if (descriptionTextField.getText() == null) {
                        descriptionTextField.setText("");
                    }
                    generalWindow.db.getTaskDAO().add(new TaskEntity(1, nameTaskTextField.getText(), Double.parseDouble(priceTextField.getText()),
                            Integer.valueOf(countTextField.getText()), Integer.valueOf(String.valueOf(priorityNumberComboBox.getSelectedItem())),
                            descriptionTextField.getText(), DATE_FORMAT.format(new Date(System.currentTimeMillis()))));

                    generalWindow.db.getStatisticDAO().add(new StatisticEntity(1,
                            "добавлено новое задание на покупку " + countTextField.getText() + "-х новых товаров: "
                            + nameTaskTextField.getText() + " с ценой: " + priceTextField.getText() + "грн., и приоритетом: "
                            + String.valueOf(priorityNumberComboBox.getSelectedItem()),
                            DATE_FORMAT.format(new Date(System.currentTimeMillis()))));

                    updateTableTask();
                    generalWindow.generalLayout.show(generalWindow.generalPanel, "task");
                }
            } else if (e.getSource() == cancelButton) {
                generalWindow.generalLayout.show(generalWindow.generalPanel, "task");
            }
        }
    }
}
