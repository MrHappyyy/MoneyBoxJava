package gui;

import javax.swing.*;
import java.awt.*;

public class SettingPanel {
    private GeneralWindow generalWindow;

    private JPanel setting;

    public SettingPanel(GeneralWindow generalWindow) {
        this.generalWindow = generalWindow;
    }

    protected void initAndShowSettingPanel() {//
        if (setting == null) {
            setting = new JPanel(null);
            setting.setBackground(Color.MAGENTA);
            generalWindow.generalPanel.add("setting", setting);
        }
        generalWindow.generalLayout.show(generalWindow.generalPanel, "setting");
    }
}
