package gui;

import database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GeneralWindow {
    private DataBase db;
    private JFrame frame;
    private static final int WIDTH_WINDOW = 400;
    private static final int HEIGHT_WINDOW = 600;

    private static final int CLARIFICATION_MONEY_BOX_PANEL_X = 0;
    private static final int CLARIFICATION_MONEY_BOX_PANEL_Y = 0;
    private static final int WIDTH_CLARIFICATION_MONEY_BOX_PANEL = WIDTH_WINDOW;
    private static final int HEIGHT_CLARIFICATION_MONEY_BOX_PANEL = 40;

    private static final int HEIGHT_LINE = 2;


    private CardLayout generalLayout, moneyLayout;
    private JPanel clarificationMoneyBox, moneyBox, moneyAdd, moneyPick,
            generalPanel, task, statistic, setting,
            taskEntity, statisticEntity;

    /*moneyBox*/
    private JButton addMoney, pickMoney;
    private JLabel moneyLabel;

    /*moneyAdd and moneyPick*/
    private JButton okMoneyAdd, okMoneyPick;
    private JTextField textFieldMoneyAdd, textFieldMoneyPick;


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
            initLine();
            initClarificationMoneyBox();
            //initGeneralPanel();
            /*frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                }
            });*/
            frame.setVisible(true);
        }
    }

    private void initLine() {
        JPanel line = new JPanel();
        line.setBackground(Color.black);
        line.setLocation(CLARIFICATION_MONEY_BOX_PANEL_X, CLARIFICATION_MONEY_BOX_PANEL_Y + HEIGHT_CLARIFICATION_MONEY_BOX_PANEL);
        line.setSize(WIDTH_CLARIFICATION_MONEY_BOX_PANEL, HEIGHT_LINE);
        frame.add(line);
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

    /*private void initGeneralPanel() {
        if (generalPanel == null) {
            generalPanel = new JPanel(new CardLayout());
            generalPanel.setSize(400, 570);
            generalPanel.setLocation(0, 30);
            generalPanel.setBackground(Color.GREEN);
            generalLayout = (CardLayout) generalPanel.getLayout();
            frame.add(generalPanel);
        }
        //initTaskPanel();
    }

    private void initTaskPanel() {
        if (task == null) {
            task = new JPanel(null);
        }

        generalPanel.add("task", task);
    }*/


    private class TextListener extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(1);
        }
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == addMoney) {
                initAndShowMoneyAdd();
            } else if (e.getSource() == pickMoney) {
                initAndShowMoneyPick();
            } else if (e.getSource() == okMoneyAdd || e.getSource() == textFieldMoneyAdd) {
                initAndShowMoneyBox();
            } else if (e.getSource() == okMoneyPick || e.getSource() == textFieldMoneyPick) {
                initAndShowMoneyBox();
            }
        }
    }
}
