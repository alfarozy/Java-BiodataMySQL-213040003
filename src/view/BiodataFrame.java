package view;

import javax.swing.*;
import dao.BiodataDao;
import model.*;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BiodataFrame extends JFrame {

    private List<Biodata> biodataList;
    private JTextField textFieldNama;
    private JTextField textFieldNoTelp;
    private JTextArea textAreaAlamat;
    private JRadioButton radioButtonL;
    private JRadioButton radioButtonP;
    private BiodataTableModel tableModel;
    private BiodataDao biodataDao;
    public boolean isEditMode = false;
    private Biodata selectedBiodata;
    private JButton buttonUbah;
    public String MemberId = "";
    public int IndexTable;
    javax.swing.JTable table = new JTable();

    public BiodataFrame(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
        this.biodataList = biodataDao.findAll();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInputNama = new JLabel("Nama:");
        labelInputNama.setBounds(15, 40, 350, 30);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 70, 350, 30);

        JLabel labelInputNoTelp = new JLabel("No Telp:");
        labelInputNoTelp.setBounds(15, 100, 350, 30);
        textFieldNoTelp = new JTextField();
        textFieldNoTelp.setBounds(15, 130, 350, 30);

        JLabel labelInputAlamat = new JLabel("Alamat:");
        labelInputAlamat.setBounds(15, 160, 350, 30);
        textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 190, 350, 60);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 250, 350, 30);
        radioButtonL = new JRadioButton("Laki-Laki", true);
        radioButtonL.setBounds(15, 280, 350, 30);
        radioButtonP = new JRadioButton("Perempuan");
        radioButtonP.setBounds(15, 310, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 410, 100, 40);

        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 410, 100, 40);
        buttonUbah = new JButton("Ubah");
        buttonUbah.setBounds(245, 410, 100, 40);

        JButton buttonRefresh = new JButton("Refresh");
        buttonRefresh.setBounds(360, 410, 100, 40);

        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 460, 500, 200);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonL);
        bg.add(radioButtonP);

        tableModel = new BiodataTableModel(biodataList);
        table.setModel(tableModel);

        BiodataButtonSimpanActionListener actionListener = new BiodataButtonSimpanActionListener(this, biodataDao);

        BiodataButtonDeleteActionListener deleteActionListener = new BiodataButtonDeleteActionListener(this,
                biodataDao);
        BiodataButtonEditActionListener editActionListener = new BiodataButtonEditActionListener(this,
                biodataDao);

        BiodataButtonRefreshActionListener refreshActionListener = new BiodataButtonRefreshActionListener(this,
                biodataDao);

        buttonUbah.addActionListener(editActionListener);
        buttonRefresh.addActionListener(refreshActionListener);
        button.addActionListener(actionListener);
        buttonDelete.addActionListener(deleteActionListener);

        table.getSelectionModel().addListSelectionListener(new BiodataTableSelectionListener());

        this.add(button);
        this.add(buttonRefresh);
        this.add(buttonDelete);
        this.add(buttonUbah);
        this.add(textFieldNama);
        this.add(textFieldNoTelp);
        this.add(textAreaAlamat);
        this.add(radioButtonL);
        this.add(radioButtonP);
        this.add(labelInputNama);
        this.add(labelInputNoTelp);
        this.add(labelInputAlamat);
        this.add(labelJenisKelamin);
        this.add(scrollableTable);

        this.setSize(700, 700);
        this.setLayout(null);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmExit();
            }
        });
    }

    public String getId() {
        return MemberId;
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public String getNoTelp() {
        return textFieldNoTelp.getText();
    }

    public String getAlamat() {
        return textAreaAlamat.getText();
    }

    public String getJenisKelamin() {
        String jenisKelamin = "";

        if (radioButtonL.isSelected()) {
            jenisKelamin = radioButtonL.getText();
        }
        if (radioButtonP.isSelected()) {
            jenisKelamin = radioButtonP.getText();
        }
        return jenisKelamin;
    }

    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata);
        textFieldNama.setText("");
        textFieldNoTelp.setText("");
        textAreaAlamat.setText("");
    }

    public String takeBiodata() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaBiodata = (String) tableModel.getValueAt(selection[0], 4);
            return namaBiodata;
        } else {
            return "";
        }
    }

    public void refreshData() {
        // Retrieve the latest data from the database
        biodataList = biodataDao.findAll();

        // Update the table model with the new data
        tableModel = new BiodataTableModel(biodataList);
        table.setModel(tableModel);

        textFieldNama.setText("");
        textFieldNoTelp.setText("");
        textAreaAlamat.setText("");
    }

    public void deleteBiodata() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    private Biodata findBiodataByName(String nama) {
        for (Biodata biodata : biodataList) {
            if (nama.equals(biodata.getNama())) {
                return biodata;
            }
        }
        return null;
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private class BiodataTableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // Get the selected row index
                int selectedRow = table.getSelectedRow();

                // Check if any row is selected
                if (selectedRow >= 0) {
                    // Convert the selected row index to model index
                    int modelIndex = table.convertRowIndexToModel(selectedRow);
                    IndexTable = modelIndex;
                    selectedBiodata = tableModel.getBiodata(modelIndex);
                    MemberId = selectedBiodata.getId();
                    // Populate the text fields and other components with the selected Biodata
                    tableModel.getValueAt(modelIndex, 4);
                    populateFieldsWithSelectedBiodata();
                }
            }
        }
    }

    // Method to populate fields with the selected Biodata
    private void populateFieldsWithSelectedBiodata() {
        // Populate your text fields, radio buttons, etc. with the values from selectedBiodata
        textFieldNama.setText(selectedBiodata.getNama());
        textFieldNoTelp.setText(selectedBiodata.getNoTelp());
        textAreaAlamat.setText(selectedBiodata.getAlamat());

        // Select the appropriate radio button based on the selected Biodata's gender
        if ("Laki-Laki".equals(selectedBiodata.getJenisKelamin())) {
            radioButtonL.setSelected(true);
        } else {
            radioButtonP.setSelected(true);
        }
    }
    
    private void confirmExit() {
        int confirmed = JOptionPane.showConfirmDialog(null, 
            "Apakah anda ingin menutup aplikasi?", "Confirm Exit",
            JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            // Perform cleanup or additional actions before exiting
            System.exit(0);
        } else {
             BiodataDao biodataDao = new BiodataDao();
                
                BiodataFrame biodataFrame = new BiodataFrame(biodataDao);
                biodataFrame.setVisible(true);
        }
    }
}
