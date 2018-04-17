package com.example.demo.service.export;

import com.example.demo.dto.FactureDTO;
import com.example.demo.entity.Facture;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ExportPDFITextService {

    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Name");
        table.addCell("Age");
        table.addCell("Location");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j=0;j<cells.length;j++){
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (int i=1;i<5;i++){
            table.addCell("Name:"+i);
            table.addCell("Age:"+i);
            table.addCell("Location:"+i);
        }
        PdfWriter.getInstance(document, new FileOutputStream("sample3.pdf"));

        document.open();
        document.add(new Paragraph("Facture Version PDF"));
        document.add(table);
        document.close();
    }
}
