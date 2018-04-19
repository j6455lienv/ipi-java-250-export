package com.example.demo.service.export;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.example.demo.entity.Facture;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@Service
public class ExportPDFITextService {

    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Désignation");
        table.addCell("Quantité");
        table.addCell("Prix Unitaire");
        //table.addCell("Prix Ligne");
        table.setHeaderRows(1);

        PdfPCell[] cells = table.getRow(0).getCells();
        for (int i=0;i<cells.length;i++){
            cells[i].setBackgroundColor(BaseColor.GRAY);
        }


        for (LigneFactureDTO lfDTO : facture.getLigneFactures()){
            table.addCell(String.valueOf(lfDTO.getDesignation()));
            table.addCell(String.valueOf(lfDTO.getQuantite()));
            table.addCell(String.valueOf(lfDTO.getPrixUnitaire()));
        }

        PdfWriter.getInstance(document, new FileOutputStream("sample3.pdf"));

        document.open();
        document.add(new Paragraph("Facture Version PDF"));
        document.add(table);
        document.add(new Paragraph("Et pleins d'autre mots interessants pour une facture !!"));
        document.add(new Paragraph("Mais je ne comprend pas ce genre de choses..."));
        document.close();
    }
}
