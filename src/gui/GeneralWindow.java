package gui;

import database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOError;

public class GeneralWindow {
    private DataBase db;
    private JFrame frame;
    private static final int WIDTH_WINDOW = 400;
    private static final int HEIGHT_WINDOW = 600;

    private static final int HEIGHT_LINE = 2;

    private static final int CLARIFICATION_MONEY_BOX_PANEL_X = 0;
    private static final int CLARIFICATION_MONEY_BOX_PANEL_Y = 0;
    private static final int WIDTH_CLARIFICATION_MONEY_BOX_PANEL = WIDTH_WINDOW;
    private static final int HEIGHT_CLARIFICATION_MONEY_BOX_PANEL = 40;

    private static final int NAVIGATION_PANEL_X = CLARIFICATION_MONEY_BOX_PANEL_X;
    private static final int NAVIGATION_PANEL_Y = CLARIFICATION_MONEY_BOX_PANEL_Y + HEIGHT_CLARIFICATION_MONEY_BOX_PANEL + HEIGHT_LINE;
    private static final int WIDTH_NAVIGATION_PANEL = WIDTH_CLARIFICATION_MONEY_BOX_PANEL;
    private static final int HEIGHT_NAVIGATION_PANEL = 100;

    private static final int GENERAL_PANEL_X = CLARIFICATION_MONEY_BOX_PANEL_X;
    private static final int GENERAL_PANEL_Y = CLARIFICATION_MONEY_BOX_PANEL_Y + HEIGHT_CLARIFICATION_MONEY_BOX_PANEL + HEIGHT_NAVIGATION_PANEL + (HEIGHT_LINE * 2);
    private static final int WIDTH_GENERAL_PANEL = WIDTH_CLARIFICATION_MONEY_BOX_PANEL;
    private static final int HEIGHT_GENERAL_PANEL = HEIGHT_WINDOW - GENERAL_PANEL_Y;

    private static final String DOUBLE_CHARACTER = "0123456789";
    private static final double maxDouble = 9999999.99;

    private CardLayout generalLayout, moneyLayout;
    private JPanel clarificationMoneyBox, moneyBox, moneyAdd, moneyPick,
            navigation,
            generalPanel, task, statistic, setting,
            taskEntity, statisticEntity;

    /*moneyBox*/
    private JButton addMoney, pickMoney;
    private JLabel moneyLabel;

    /*moneyAdd and moneyPick*/
    private JButton okMoneyAdd, okMoneyPick;
    private JTextField textFieldMoneyAdd, textFieldMoneyPick;

    /*navigation*/
    private JButton prevPanel, nextPanel;
    private JLabel navigationLabel;
    private String[] infoNavigation = {"ЗАДАЧИ", "СТАТИСТИКА", "НАСТРОЙКИ"};

    /*task*/
    private JButton addTask, deliteTask;
    private TaskTableModel taskTableModel;
    private JTable taskTable;
    private JScrollPane taskScroll;

    public GeneralWindow(String nameWindow) {
        db = new DataBase();
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
            initAndRefreshNavigationPanel(infoNavigation[0]);
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
        initAndShowMoneyBox();
    }

    private void initAndShowMoneyBox() {
        if (moneyBox == null) {
            moneyBox = new JPanel(null);
            moneyBox.setBackground(Color.cyan);

            int sizeButton = (HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/6)*4;

            addMoney = new JButton(new ImageIcon("/home/mrhappyyy/Programming/Java/Projects/Money-Box/Image/add.png"));
            addMoney.setBorderPainted(false);
            addMoney.setLocation((((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/3)/4)-(sizeButton/2)), (HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/2)-(sizeButton/2));
            addMoney.setSize(sizeButton, sizeButton);
            addMoney.addActionListener(new Listener());
            moneyBox.add(addMoney);

            pickMoney = new JButton(new ImageIcon("/home/mrhappyyy/Programming/Java/Projects/Money-Box/Image/pick.png"));
            pickMoney.setBorderPainted(false);
            pickMoney.setLocation(((((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/3)/4)*2)-(sizeButton/2)), (HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/2)-(sizeButton/2));
            pickMoney.setSize(sizeButton, sizeButton);
            pickMoney.addActionListener(new Listener());
            moneyBox.add(pickMoney);

            moneyLabel = new JLabel();
            moneyLabel.setLocation((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/5) + CLARIFICATION_MONEY_BOX_PANEL_X, CLARIFICATION_MONEY_BOX_PANEL_Y);
            moneyLabel.setSize((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/3)*2, HEIGHT_CLARIFICATION_MONEY_BOX_PANEL);
            moneyLabel.setVerticalAlignment(SwingConstants.CENTER);
            moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            moneyBox.add(moneyLabel);
            clarificationMoneyBox.add("money", moneyBox);
        }
        moneyLabel.setVisible(false);
        moneyLabel.setText(String.valueOf(db.getMoneyBox().getMoney()));
        moneyLabel.setVisible(true);
        moneyLayout.show(clarificationMoneyBox, "money");
    }

    private void initAndShowMoneyAdd() {
        if (moneyAdd == null) {
            moneyAdd = new JPanel(null);
            moneyAdd.setBackground(Color.cyan);

            int sizeButton = (HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/6)*4;

            okMoneyAdd = new JButton(new ImageIcon("/home/mrhappyyy/Programming/Java/Projects/Money-Box/Image/ok.png"));
            okMoneyAdd.setBorderPainted(false);
            okMoneyAdd.setLocation((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/16)*14, HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/5);
            okMoneyAdd.setSize(sizeButton, sizeButton);
            okMoneyAdd.addActionListener(new Listener());
            moneyAdd.add(okMoneyAdd);

            textFieldMoneyAdd = new JTextField();
            textFieldMoneyAdd.setSize((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/16)*12 ,(HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/4)*2);
            textFieldMoneyAdd.setLocation(WIDTH_CLARIFICATION_MONEY_BOX_PANEL/16, HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/4);
            textFieldMoneyAdd.addKeyListener(new TextListener());
            textFieldMoneyAdd.addActionListener(new Listener());
            moneyAdd.add(textFieldMoneyAdd);
            clarificationMoneyBox.add("moneyAdd", moneyAdd);
        }
        moneyLayout.show(clarificationMoneyBox, "moneyAdd");
    }

    private void initAndShowMoneyPick() {
        if (moneyPick == null) {
            moneyPick = new JPanel(null);
            moneyPick.setBackground(Color.cyan);

            int sizeButton = (HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/6)*4;

            okMoneyPick = new JButton(new ImageIcon("/home/mrhappyyy/Programming/Java/Projects/Money-Box/Image/ok.png"));
            okMoneyPick.setBorderPainted(false);
            okMoneyPick.setLocation((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/16)*14, HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/5);
            okMoneyPick.setSize(sizeButton, sizeButton);
            okMoneyPick.addActionListener(new Listener());
            moneyPick.add(okMoneyPick);

            textFieldMoneyPick = new JTextField();
            textFieldMoneyPick.setSize((WIDTH_CLARIFICATION_MONEY_BOX_PANEL/16)*12 ,(HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/4)*2);
            textFieldMoneyPick.setLocation(WIDTH_CLARIFICATION_MONEY_BOX_PANEL/16, HEIGHT_CLARIFICATION_MONEY_BOX_PANEL/4);
            textFieldMoneyPick.addKeyListener(new TextListener());
            textFieldMoneyPick.addActionListener(new Listener());
            moneyPick.add(textFieldMoneyPick);
            clarificationMoneyBox.add("moneyPick", moneyPick);
        }
        moneyLayout.show(clarificationMoneyBox, "moneyPick");
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

    private void initAndRefreshNavigationPanel(String textInfoNavigation) {
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
            frame.add(navigation);
        }
        navigationLabel.setVisible(false);
        navigationLabel.setText(textInfoNavigation);
        navigationLabel.setVisible(true);
    }

    private void initGeneralPanel() {
        if (generalPanel == null) {
            generalPanel = new JPanel(new CardLayout());
            generalPanel.setSize(WIDTH_GENERAL_PANEL, HEIGHT_GENERAL_PANEL);
            generalPanel.setLocation(GENERAL_PANEL_X, GENERAL_PANEL_Y);
            generalPanel.setBackground(Color.GREEN);
            generalLayout = (CardLayout) generalPanel.getLayout();
            frame.add(generalPanel);
        }
        initAndShowTaskPanel();
    }

    private void initAndShowTaskPanel() {
        if (task == null) {
            task = new JPanel(null);

            generalPanel.add("task", task);
        }
        generalLayout.show(generalPanel, "task");
    }

    private void initAndShowStatisticPanel() {
        if (statistic == null) {
            statistic = new JPanel(null);
            statistic.setBackground(Color.BLUE);
            generalPanel.add("statistic", statistic);
        }
        generalLayout.show(generalPanel, "statistic");
    }

    private void initAndShowSettingPanel() {
        if (setting == null) {
            setting = new JPanel(null);
            setting.setBackground(Color.MAGENTA);
            generalPanel.add("setting", setting);
        }
        generalLayout.show(generalPanel, "setting");
    }

    private class TextListener extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            if (e.getSource() == textFieldMoneyAdd || e.getSource() == textFieldMoneyPick) {
                JTextField jt = (JTextField) e.getSource();
                jt.setText(stringCastToDoubleInString(jt.getText()));
            }
        }
    }

    private static String stringCastToDoubleInString(String s) {
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

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextPanel) {
                String infoNavig = navigationLabel.getText();
                if (infoNavigation[0].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(infoNavigation[1]);
                    initAndShowStatisticPanel();
                } else if (infoNavigation[1].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(infoNavigation[2]);
                    initAndShowSettingPanel();
                } else if (infoNavigation[2].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(infoNavigation[0]);
                    initAndShowTaskPanel();
                }
            } else if (e.getSource() == prevPanel) {
                String infoNavig = navigationLabel.getText();
                if (infoNavigation[0].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(infoNavigation[2]);
                    initAndShowSettingPanel();
                } else if (infoNavigation[1].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(infoNavigation[0]);
                    initAndShowTaskPanel();
                } else if (infoNavigation[2].equals(infoNavig)) {
                    initAndRefreshNavigationPanel(infoNavigation[1]);
                    initAndShowStatisticPanel();
                }
            } else if (e.getSource() == addMoney) {
                initAndShowMoneyAdd();
            } else if (e.getSource() == pickMoney) {
                initAndShowMoneyPick();
            } else if (e.getSource() == okMoneyAdd || e.getSource() == textFieldMoneyAdd) {
                if (!textFieldMoneyAdd.getText().equals("")) {
                    double money = db.getMoneyBox().getMoney();

                    if (Double.parseDouble(textFieldMoneyAdd.getText()) + money <= maxDouble) {
                        db.getMoneyBox().addMoney(Double.parseDouble(textFieldMoneyAdd.getText()));
                        textFieldMoneyAdd.setText("");
                        initAndShowMoneyBox();
                    } else {
                        textFieldMoneyAdd.setText(String.valueOf(maxDouble - money));
                    }
                } else {
                    initAndShowMoneyBox();
                }
            } else if (e.getSource() == okMoneyPick || e.getSource() == textFieldMoneyPick) {
                if (!textFieldMoneyPick.getText().equals("")) {
                    double money = db.getMoneyBox().getMoney();

                    if (money - Double.parseDouble(textFieldMoneyPick.getText()) >= 0) {
                        db.getMoneyBox().pickMoney(Double.parseDouble(textFieldMoneyPick.getText()));
                        textFieldMoneyPick.setText("");
                        initAndShowMoneyBox();
                    } else {
                        textFieldMoneyPick.setText(String.valueOf(money));
                    }
                } else {
                    initAndShowMoneyBox();
                }
            }






        }
    }
}
