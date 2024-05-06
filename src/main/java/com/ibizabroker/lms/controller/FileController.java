package com.ibizabroker.lms.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FileController {
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportUsers(
            @RequestParam String role,
            @RequestParam String format) throws IOException {
        // Assurez-vous que ce code génère bien les données à exporter
        ByteArrayInputStream stream = generateCSV(role);

        InputStreamResource resource = new InputStreamResource(stream);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users." + format);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    private ByteArrayInputStream generateCSV(String role) {
        // Exemple de génération de CSV
        String data = "Nom,Email,Rôle\n"; // Exemple simple
        data += "John Doe,johndoe@example.com," + role + "\n";
        return new ByteArrayInputStream(data.getBytes());
    }
}
