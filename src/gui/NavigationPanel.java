package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.GeneralWindow.*;

public class NavigationPanel {
    private GeneralWindow generalWindow;

    private JPanel navigation;

    /*navigation*/
    private JButton prevPanel, nextPanel;
    private JLabel navigationLabel;
    protected static final String[] INFO_NAVIGATION = {"ЗАДАЧИ", "СТАТИСТИКА", "НАСТРОЙКИ"};

    public NavigationPanel(GeneralWindow generalWindow) {
        this.generalWindow = generalWindow;
    }

    protected void initAndRefreshNavigationPanel(String textInfoNavigation) {
        if (navigation == null) {
            navigation = new JPanel(null);
            navigation.setBackground(Color.GREEN);
            navigation.setLocation(NAVIGATION_PANEL_X, NAVIGATION_PANEL_Y);
            navigation.setSize(WIDTH_NAVIGATION_PANEL, HEIGHT_NAVIGATION_PANEL);

            {
                int height = HEIGHT_NAVIGATION_PANEL / 2;
                int locationY = (HEIGHT_NAVIGATION_PANEL / 2) - (height / 2);
                {
                    int widthLabel = WIDTH_NAVIGATION_PANEL / 2;
                    navigationLabel = new JLabel();
                    navigationLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    navigationLabel.setVerticalAlignment(SwingConstants.CENTER);
                    navigationLabel.setSize(widthLabel, height);
                    navigationLabel.setLocation(0, 0);
                    JPanel p = new JPanel(null);
                    p.setSize(widthLabel, height);
                    p.setLocation((WIDTH_NAVIGATION_PANEL / 2) - (widthLabel / 2),
                            locationY);
                    p.setBackground(Color.yellow);
                    p.add(navigationLabel);
                    navigation.add(p);
                }
                {
                    int widthB = (WIDTH_NAVIGATION_PANEL / 4) / 2;
                    prevPanel = new JButton(new ImageIcon("/home/mrhappyyy/Programming/Java/Projects/Money-Box/Image/prev.png"));
                    prevPanel.setSize(widthB, height);
                    prevPanel.setLocation((WIDTH_NAVIGATION_PANEL/5) - widthB,locationY);
                    prevPanel.setBorderPainted(false);
                    prevPanel.addActionListener(new Listener());
                    navigation.add(prevPanel);
                    nextPanel = new JButton(new ImageIcon("/home/mrhappyyy/Programming/Java/Projects/Money-Box/Image/next.png"));
                    nextPanel.setSize(widthB, height);
                    nextPanel.setLocation(((WIDTH_NAVIGATION_PANEL/5)*4),locationY);
                    nextPanel.setBorderPainted(false);
                    nextPanel.addActionListener(new Listener());
                    navigation.add(nextPanel);
                }
            }
            generalWindow.frame.add(navigation);
        }
        navigationLabel.setVisible(false);
        navigationLabel.setText(textInfoNavigation);
        navigationLabel.setVisible(true);
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextPanel) {//
                String infoNavig = navigationLabel.getText();
                if (INFO_NAVIGATION[0].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(INFO_NAVIGATION[1]);
                    generalWindow.statisticPanel.initAndShowStatisticPanel();
                } else if (INFO_NAVIGATION[1].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(INFO_NAVIGATION[2]);
                    generalWindow.settingPanel.initAndShowSettingPanel();
                } else if (INFO_NAVIGATION[2].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(INFO_NAVIGATION[0]);
                    generalWindow.taskPanel.initAndShowTaskPanel();
                }
            } else if (e.getSource() == prevPanel) {
                String infoNavig = navigationLabel.getText();
                if (INFO_NAVIGATION[0].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(INFO_NAVIGATION[2]);
                    generalWindow.settingPanel.initAndShowSettingPanel();
                } else if (INFO_NAVIGATION[1].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(INFO_NAVIGATION[0]);
                    generalWindow.taskPanel.initAndShowTaskPanel();
                } else if (INFO_NAVIGATION[2].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(INFO_NAVIGATION[1]);
                    generalWindow.statisticPanel.initAndShowStatisticPanel();
                }
            }
        }
    }
}
