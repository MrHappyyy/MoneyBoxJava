package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticPanel {
    private GeneralWindow generalWindow;

    private JPanel statistic, statisticEntity;

    public StatisticPanel(GeneralWindow generalWindow) {
        this.generalWindow = generalWindow;
    }

    protected void initAndShowStatisticPanel() {//
        if (statistic == null) {
            statistic = new JPanel(null);
            statistic.setBackground(Color.BLUE);
            generalWindow.generalPanel.add("statistic", statistic);
        }
        generalWindow.generalLayout.show(generalWindow.generalPanel, "statistic");
    }
}
