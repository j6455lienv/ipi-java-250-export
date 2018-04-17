package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Facture;
import com.example.demo.repository.FactureRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void export2(OutputStream os, List<ClientDTO> clients) throws IOException {
        //List<Facture> facts = facture;
    }
}