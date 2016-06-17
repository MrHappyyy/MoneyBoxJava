package gui;

import database.DataBase;
import database.TaskEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel newTaskNameLable, newTaskPriceLable, newTaskNumberPriorityLable, newTaskDescriptionLable, newTaskErrorNameLable;
    private JTextField newTaskNameTexrtField, newTaskPriceTextField, newTaskDescriprionTextField;
    private JButton newTaskAddButton, newTaskCancelButton, newTaskCheckNameButton;
    private JComboBox<Integer> newTaskNumberPriorityComboBox;

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

    protected void initAndShowAddTaskEntityPanel() {
        if (addTaskEntity == null) {
            addTaskEntity = new JPanel(null);

            generalWindow.generalPanel.add("addTask", addTaskEntity);
        }
        generalWindow.generalLayout.show(generalWindow.generalPanel, "addTask");
    }

    protected void initAndShowTaskEntityPanel(String nameEntity) {
        if (taskEntity == null) {
            taskEntity = new JPanel(null);

        }
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addTask) {
                initAndShowAddTaskEntityPanel();
            }
        }
    }
}
