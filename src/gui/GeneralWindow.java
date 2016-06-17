package gui;

import database.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOError;
import java.util.ArrayList;

public class GeneralWindow {
    protected TaskPanel taskPanel;
    protected NavigationPanel navigationPanel;
    protected SettingPanel settingPanel;
    protected StatisticPanel statisticPanel;
    protected MoneyBoxPanel moneyBoxPanel;

    protected DataBase db;

    protected JFrame frame;

    protected static final int WIDTH_WINDOW = 400;
    protected static final int HEIGHT_WINDOW = 600;

    protected static final int HEIGHT_LINE = 2;

    protected static final int CLARIFICATION_MONEY_BOX_PANEL_X = 0;
    protected static final int CLARIFICATION_MONEY_BOX_PANEL_Y = 0;
    protected static final int WIDTH_CLARIFICATION_MONEY_BOX_PANEL = WIDTH_WINDOW;
    protected static final int HEIGHT_CLARIFICATION_MONEY_BOX_PANEL = 40;

    protected static final int NAVIGATION_PANEL_X = CLARIFICATION_MONEY_BOX_PANEL_X;
    protected static final int NAVIGATION_PANEL_Y = CLARIFICATION_MONEY_BOX_PANEL_Y + HEIGHT_CLARIFICATION_MONEY_BOX_PANEL + HEIGHT_LINE;
    protected static final int WIDTH_NAVIGATION_PANEL = WIDTH_CLARIFICATION_MONEY_BOX_PANEL;
    protected static final int HEIGHT_NAVIGATION_PANEL = 100;

    protected static final int GENERAL_PANEL_X = CLARIFICATION_MONEY_BOX_PANEL_X;
    protected static final int GENERAL_PANEL_Y = CLARIFICATION_MONEY_BOX_PANEL_Y + HEIGHT_CLARIFICATION_MONEY_BOX_PANEL + HEIGHT_NAVIGATION_PANEL + (HEIGHT_LINE * 2);
    protected static final int WIDTH_GENERAL_PANEL = WIDTH_CLARIFICATION_MONEY_BOX_PANEL;
    protected static final int HEIGHT_GENERAL_PANEL = HEIGHT_WINDOW - GENERAL_PANEL_Y;

    private static final String DOUBLE_CHARACTER = "0123456789";
    protected static final double maxDouble = 9999999.99;

    protected CardLayout generalLayout, moneyLayout;
    protected JPanel clarificationMoneyBox, generalPanel;

    protected ArrayList<TaskEntity> taskList;
    protected ArrayList<StatisticEntity> statisticList;

    public GeneralWindow(String nameWindow) {
        db = new DataBase();
        taskPanel = new TaskPanel(this);
        statisticPanel = new StatisticPanel(this);
        settingPanel = new SettingPanel(this);
        moneyBoxPanel = new MoneyBoxPanel(this);
        navigationPanel = new NavigationPanel(this);
        createWindow(nameWindow);
    }

    private void createWindow(String nameWindow) {
        if (frame == null) {
            frame = new JFrame(nameWindow);
            frame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLayout(null);
            initClarificationMoneyBox();
            initLine();
            navigationPanel.initAndRefreshNavigationPanel(NavigationPanel.INFO_NAVIGATION[0]);
            initGeneralPanel();
            /*frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                }
            });*/
            frame.setVisible(true);
        }
    }

    private void initClarificationMoneyBox() {
        if (clarificationMoneyBox == null) {
            clarificationMoneyBox = new JPanel(new CardLayout());
            clarificationMoneyBox.setSize(WIDTH_CLARIFICATION_MONEY_BOX_PANEL, HEIGHT_CLARIFICATION_MONEY_BOX_PANEL);
            clarificationMoneyBox.setLocation(CLARIFICATION_MONEY_BOX_PANEL_X, CLARIFICATION_MONEY_BOX_PANEL_Y);
            moneyLayout = (CardLayout) clarificationMoneyBox.getLayout();
            frame.add(clarificationMoneyBox);
        }
        moneyBoxPanel.initAndShowMoneyBox();
    }

    private void initLine() {
        JPanel line1 = new JPanel();
        line1.setBackground(Color.black);
        line1.setLocation(CLARIFICATION_MONEY_BOX_PANEL_X, CLARIFICATION_MONEY_BOX_PANEL_Y + HEIGHT_CLARIFICATION_MONEY_BOX_PANEL);
        line1.setSize(WIDTH_CLARIFICATION_MONEY_BOX_PANEL, HEIGHT_LINE);
        frame.add(line1);
        JPanel line2 = new JPanel();
        line2.setLocation(CLARIFICATION_MONEY_BOX_PANEL_X, NAVIGATION_PANEL_Y + HEIGHT_NAVIGATION_PANEL);
        line2.setBackground(Color.black);
        line2.setSize(WIDTH_CLARIFICATION_MONEY_BOX_PANEL, HEIGHT_LINE);
        frame.add(line2);
    }

    private void initGeneralPanel() {
        if (generalPanel == null) {
            generalPanel = new JPanel(new CardLayout());
            generalPanel.setSize(WIDTH_GENERAL_PANEL, HEIGHT_GENERAL_PANEL);
            generalPanel.setLocation(GENERAL_PANEL_X, GENERAL_PANEL_Y);
            //generalPanel.setBackground(Color.GREEN);
            generalLayout = (CardLayout) generalPanel.getLayout();
            frame.add(generalPanel);
        }
        taskPanel.initAndShowTaskPanel();
    }

    public static String stringCastToDoubleInString(String s) {
        String parseDouble = "";
        boolean isDote = true;

        for (int i = 0; i < s.length(); i++) {
            if (isDote && !parseDouble.equals("") && String.valueOf(s.charAt(i)).equals(".")) {
                parseDouble += String.valueOf(s.charAt(i));
                isDote = false;
            } else if (parseDouble.length() >= 7 && isDote) {
                break;
            } else if (parseDouble.length() >= 10 && !isDote) {
                break;
            } else {
                for (int k = 0; k < DOUBLE_CHARACTER.length(); k++) {
                    if (String.valueOf(s.charAt(i)).equals(String.valueOf(DOUBLE_CHARACTER.charAt(k)))) {
                        parseDouble += String.valueOf(s.charAt(i));
                        break;
                    }
                }
            }
        }

        try {
            if (!parseDouble.equals("")) {
                Double.parseDouble(parseDouble);
                return parseDouble;
            }
        } catch (IOError e) {
            System.out.println("Не удалось строку привести к типу double");
        }
        return "0";
    }
}