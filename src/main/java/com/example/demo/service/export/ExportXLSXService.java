package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportXLSXService {


    public void export(OutputStream os, List<ClientDTO> clients) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");
        XSSFRow headerRow = sheet.createRow(0);
        XSSFCell cellPrenom = headerRow.createCell(0);
        cellPrenom.setCellValue("Prénom");
        XSSFCell cellNom = headerRow.createCell(1);
        cellNom.setCellValue("Nom");
        int i = 1;
        for (ClientDTO client : clients) {
            XSSFRow Row = sheet.createRow(i);
            i++;
            XSSFCell cellPrenomWrite = Row.createCell(0);
            cellPrenomWrite.setCellValue(client.getPrenom().replaceAll(";", ""));
            XSSFCell cellNomWrite = Row.createCell(1);
            cellNomWrite.setCellValue(client.getNom().replaceAll(";", ""));
        }

        workbook.write(os);
        workbook.close();
    }

    public void export2(OutputStream os, List<FactureDTO> facts) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        //LigneFactureDTO factsDTO = (LigneFactureDTO)facts;

        for (FactureDTO facture : facts) {
            XSSFSheet sheet = workbook.createSheet("Facture" + facture.getId());
            XSSFRow headerRow = sheet.createRow(0);
            XSSFCell celldesignation = headerRow.createCell(0);
            celldesignation.setCellValue("désignation");
            XSSFCell cellQuantite = headerRow.createCell(1);
            cellQuantite.setCellValue("quantité");
            XSSFCell cellPrixUn = headerRow.createCell(2);
            cellPrixUn.setCellValue("prixUnitaire");
            XSSFCell cellPrixLine = headerRow.createCell(3);
            cellPrixLine.setCellValue("prixLigne");

            int total = 0;


            int i = 1;
            for (LigneFactureDTO factsDTO : facture.getLigneFactures()) {
                XSSFRow rowl = sheet.createRow(i);
                XSSFCell cellDes = rowl.createCell(0);
                cellDes.setCellValue(factsDTO.getDesignation());
                XSSFCell cellQuanti = rowl.createCell(1);
                cellQuanti.setCellValue(factsDTO.getQuantite());
                XSSFCell cellpu = rowl.createCell(2);
                cellpu.setCellValue(factsDTO.getPrixUnitaire());
                XSSFCell cellpl = rowl.createCell(3);
                cellpl.setCellValue(factsDTO.getPrixUnitaire() * factsDTO.getQuantite());
                total += factsDTO.getPrixUnitaire() * factsDTO.getQuantite();
                i++;
            }
            XSSFRow rowTotal = sheet.createRow(i);
            XSSFCell cellTotName = rowTotal.createCell(0);
            cellTotName.setCellValue("Total");
            XSSFCell cellTotVal = rowTotal.createCell(0);
            cellTotVal.setCellValue(total);
        }
        workbook.write(os);
        workbook.close();
    }
}
