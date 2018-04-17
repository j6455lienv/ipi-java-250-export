package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<ClientDTO> clients) throws IOException {
        printWriter.write("Nom;");
        printWriter.write("Prenom;\n");
        for (ClientDTO client : clients) {
            printWriter.write(client.getNom().replaceAll(";",""));
            printWriter.write(";");
            printWriter.write(client.getPrenom().replaceAll(";",""));
            printWriter.write("\n");
        }
    }
}
