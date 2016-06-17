package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static gui.GeneralWindow.*;

public class MoneyBoxPanel {
    private GeneralWindow generalWindow;

    private JPanel moneyBox, moneyAdd, moneyPick;

    /*moneyBox*/
    private JButton addMoney, pickMoney;
    private JLabel moneyLabel;

    /*moneyAdd and moneyPick*/
    private JButton okMoneyAdd, okMoneyPick;
    private JTextField textFieldMoneyAdd, textFieldMoneyPick;

    public MoneyBoxPanel(GeneralWindow generalWindow) {
        this.generalWindow = generalWindow;
    }

    protected void initAndShowMoneyBox() {
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
            generalWindow.clarificationMoneyBox.add("money", moneyBox);
        }
        moneyLabel.setVisible(false);
        moneyLabel.setText(String.valueOf(generalWindow.db.getMoneyBox().getMoney()));
        moneyLabel.setVisible(true);
        generalWindow.moneyLayout.show(generalWindow.clarificationMoneyBox, "money");
    }

    protected void initAndShowMoneyAdd() {
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
            generalWindow.clarificationMoneyBox.add("moneyAdd", moneyAdd);
        }
        generalWindow.moneyLayout.show(generalWindow.clarificationMoneyBox, "moneyAdd");
    }

    protected void initAndShowMoneyPick() {
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
            generalWindow.clarificationMoneyBox.add("moneyPick", moneyPick);
        }
        generalWindow.moneyLayout.show(generalWindow.clarificationMoneyBox, "moneyPick");
    }

    private class TextListener extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            if (e.getSource() == textFieldMoneyAdd || e.getSource() == textFieldMoneyPick) {
                JTextField jt = (JTextField) e.getSource();
                jt.setText(stringCastToDoubleInString(jt.getText()));
            }
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
                if (!textFieldMoneyAdd.getText().equals("")) {
                    double money = generalWindow.db.getMoneyBox().getMoney();

                    if (Double.parseDouble(textFieldMoneyAdd.getText()) + money <= maxDouble) {
                        generalWindow.db.getMoneyBox().addMoney(Double.parseDouble(textFieldMoneyAdd.getText()));
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
                    double money = generalWindow.db.getMoneyBox().getMoney();

                    if (money - Double.parseDouble(textFieldMoneyPick.getText()) >= 0) {
                        generalWindow.db.getMoneyBox().pickMoney(Double.parseDouble(textFieldMoneyPick.getText()));
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
