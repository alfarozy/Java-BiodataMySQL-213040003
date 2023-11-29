package MyBiodata;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import dao.BiodataDao;
import view.BiodataFrame;

public class MainFrame extends JFrame {


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataDao biodataDao = new BiodataDao();
                
                
                BiodataFrame biodataFrame = new BiodataFrame(biodataDao);
                biodataFrame.setVisible(true);
            }
        });
    }
}
