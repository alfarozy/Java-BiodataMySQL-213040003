package view;

import java.awt.event.*;
import dao.BiodataDao;
import javax.swing.JOptionPane;
import model.*;

public class BiodataButtonRefreshActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonRefreshActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          this.biodataFrame.refreshData();
    }
}
