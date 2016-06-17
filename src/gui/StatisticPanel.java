package gui;

import database.StatisticEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static gui.GeneralWindow.*;

public class StatisticPanel {
    private GeneralWindow generalWindow;

    private JPanel statistic, statisticEntity;

    /*statistic*/
    private JTable tableStatistic;
    private StatisticTableModel tableModelStatistic;
    private JScrollPane scrollPane;

    /*statisticEntity*/
    private JLabel data, event;
    private JButton beck;

    public StatisticPanel(GeneralWindow generalWindow) {
        this.generalWindow = generalWindow;
    }

    protected void initAndShowStatisticPanel() {
        if (statistic == null) {
            statistic = new JPanel(null);

            tableModelStatistic = new StatisticTableModel();
            tableStatistic = new JTable(tableModelStatistic);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            tableStatistic.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            scrollPane = new JScrollPane(tableStatistic);
            scrollPane.setLocation(0, 0);
            scrollPane.setSize(WIDTH_GENERAL_PANEL, HEIGHT_GENERAL_PANEL);
            tableStatistic.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println("sad");
                        initAndShowStatisticEntityPanel((String) tableModelStatistic.getValueAt(tableStatistic.getSelectedRow(), 0));
                    }
                }
            });
            tableStatistic.setOpaque(false);
            tableStatistic.setDefaultRenderer (Object.class, new DefaultTableCellRenderer() {

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
            tableStatistic.getTableHeader().setReorderingAllowed ( false );
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            statistic.add(scrollPane);
            updateStatisticTable();

            generalWindow.generalPanel.add("statistic", statistic);
        }
        generalWindow.generalLayout.show(generalWindow.generalPanel, "statistic");
    }

    private void initAndShowStatisticEntityPanel(String dataEvent) {
        if (statisticEntity == null) {
            statisticEntity = new JPanel(null);

            data = new JLabel();
            data.setLocation(0, 10);
            data.setSize(WIDTH_GENERAL_PANEL, 300);
            data.setHorizontalAlignment(SwingConstants.CENTER);
            data.setVerticalAlignment(SwingConstants.CENTER);
            statisticEntity.add(data);

            event = new JLabel();
            JScrollPane scrollPane = new JScrollPane(event);
            scrollPane.setLocation(0, 50);
            scrollPane.setSize(WIDTH_GENERAL_PANEL, HEIGHT_GENERAL_PANEL - 80);
            statisticEntity.add(scrollPane);

            beck = new JButton("<НАЗАД");
            beck.setLocation(0, HEIGHT_GENERAL_PANEL - 20);
            beck.setSize(WIDTH_GENERAL_PANEL, 20);
            beck.setHorizontalAlignment(SwingConstants.CENTER);
            beck.setVerticalAlignment(SwingConstants.CENTER);
            beck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initAndShowStatisticPanel();
                }
            });
            statisticEntity.add(beck);
            generalWindow.generalPanel.add("statisticEntity", statisticEntity);
        }
        for (int i = 0; i < generalWindow.statisticList.size(); i++) {
            if (generalWindow.statisticList.get(i).getData().equals(dataEvent)) {
                data.setText(generalWindow.statisticList.get(i).getData());
                event.setText(generalWindow.statisticList.get(i).getEvent());
                break;
            }
        }
        generalWindow.generalLayout.show(generalWindow.generalPanel, "statisticEntity");
    }

    private void updateStatisticTable() {
        generalWindow.statisticList = (ArrayList<StatisticEntity>) generalWindow.db.getStatisticDAO().getAll();
        tableModelStatistic.removeIsAll();

        for (int i = 0; i < generalWindow.statisticList.size(); i++) {
            String[] table = {generalWindow.statisticList.get(i).getData()};
            tableModelStatistic.addDate(table);
        }

        scrollPane.setVisible(false);
        scrollPane.setVisible(true);
    }
}
