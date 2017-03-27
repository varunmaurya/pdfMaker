package com.varun.pdfmaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Text actiontarget;
    @FXML
    private Label txt1;
    @FXML
    private Label txt2;
    @FXML
    private Label txt3;

    private String outLocation;

    private List<File> filesToBeMerged = new ArrayList<>();

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        try {
            if(null == outLocation){
                actiontarget.setText("Please provide the output location");
                return;
            }
            if (filesToBeMerged.size() > 0) {
                mergePDF(filesToBeMerged);
                actiontarget.setText("pdf merge is done");

            } else {
                actiontarget.setText("Please select some pdfs to be merged");
            }
        }catch (Exception e){
            System.out.println("Error while merging pdf" +e);
        }

    }

    public void handleAddBtn(ActionEvent actionEvent) {
        Button btn = (Button)actionEvent.getSource();
        FileChooser fileChooser = new FileChooser();
        DirectoryChooser dirChooser = new DirectoryChooser();
        if("btn1".equals(btn.getId())){
            File file = fileChooser.showOpenDialog(null);
            if(null!=file){
                filesToBeMerged.add(file);
                txt1.setText(file.getAbsolutePath());
            }
        }

        else if("btn2".equals(btn.getId())){
            File file = fileChooser.showOpenDialog(null);
            if(null!=file){
                filesToBeMerged.add(file);
                txt2.setText(file.getAbsolutePath());
            }
        }

        else if("btn3".equals(btn.getId())){
            File file = dirChooser.showDialog(null);
            if(null!=file){
               if(file.isDirectory()) {
                   txt3.setText(file.getAbsolutePath());
                   outLocation=file.getAbsolutePath();
               }else{
                   actiontarget.setText("Please select a directory for output");
               }
            }
        }

    }

    private void mergePDF(List<File> allPdf) throws IOException {
        int keyLength = 128;


        PDFMergerUtility ut = new PDFMergerUtility();

        for(File pdf : allPdf){
            ut.addSource(pdf);
        }

        ut.setDestinationFileName(outLocation+File.separator+"merged.pdf");
        ut.mergeDocuments();
//        PDDocument doc = PDDocument.load(new File(outLocation+"merged.pdf"));
//        AccessPermission ap = new AccessPermission();
//        ap.setCanPrint(false);
//        StandardProtectionPolicy spp = new StandardProtectionPolicy("12345", "123", ap);
//        spp.setEncryptionKeyLength(keyLength);
//        spp.setPermissions(ap);
//        doc.protect(spp);
//        doc.save(pdfFolder+"protected.pdf");
//        doc.close();
        System.out.println("Merge Done");
    }
}
