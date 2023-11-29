package view;

import java.awt.event.*;
import dao.BiodataDao;
import java.util.UUID;
import javax.swing.JOptionPane;
import model.*;

public class BiodataButtonEditActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonEditActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.biodataFrame.getNama();
        String MemberId = this.biodataFrame.getId();
        String noTelp = this.biodataFrame.getNoTelp();
        String alamat = this.biodataFrame.getAlamat();
        String jenisKelamin = this.biodataFrame.getJenisKelamin();

        
        if (nama.trim().isEmpty() || noTelp.trim().isEmpty() || alamat.trim().isEmpty()) {
            this.biodataFrame.showAlert("Form tidak boleh ada yang kosong!");
        } else if (!noTelp.matches("[0-9]+")) {
            this.biodataFrame.showAlert("No Telepon harus berisi angka saja!");
        } else {
            int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
                    "Update Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Biodata biodata = new Biodata();
                biodata.setNama(nama);
                biodata.setNoTelp(noTelp);
                biodata.setAlamat(alamat);
                biodata.setId(MemberId);
                biodata.setJenisKelamin(jenisKelamin);

                
                this.biodataDao.update(biodata);
                this.biodataFrame.refreshData();
            } else {
                JOptionPane.showMessageDialog(this.biodataFrame, "Anda tidak memasukan data");
            }
        }
    }
}
