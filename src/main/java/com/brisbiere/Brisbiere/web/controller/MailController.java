package com.brisbiere.Brisbiere.web.controller;

import com.brisbiere.Brisbiere.domain.service.EmailService;
import com.brisbiere.Brisbiere.domain.service.dto.EmailDto;
import com.google.type.DateTime;
import com.ironsoftware.ironpdf.PdfDocument;
import com.ironsoftware.ironpdf.edit.PageSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmailWithFile(@RequestBody EmailDto emailDto){
        try {
            emailService.setEmailWithFile(emailDto.getToUser(), emailDto.getSubject(), emailDto.getMessage(), emailDto.getContent(), emailDto.getTotalCompra(), emailDto.getFecha());
            return ResponseEntity.ok().body("Email: sent");
        } catch (Exception e){
            throw new RuntimeException("Error al enviar el email con archivo. " + e.getMessage());
        }
    }
}
