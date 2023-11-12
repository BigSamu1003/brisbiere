package com.brisbiere.Brisbiere.domain.service;

import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.repository.EmailRepository;
import com.brisbiere.Brisbiere.domain.repository.UserRepository;
import com.brisbiere.Brisbiere.domain.service.dto.EmailDto;
import com.google.common.math.Quantiles;
import com.google.type.DateTime;
import com.google.type.Decimal;
import com.ironsoftware.ironpdf.PdfDocument;
import com.ironsoftware.ironpdf.edit.PageSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    int numeroFactura=1000;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void setEmailWithFile(String toUser, String subject, String message, String[] content, String totalCompra, String fecha) throws IOException {

        numeroFactura++;
        System.out.println("Numero de Factura: " + numeroFactura);
        Optional<User> user = userRepository.getUserByEmail(toUser);
        String usuario = user.get().getName();
        String[] totCompra = totalCompra.split(" ");
        BigDecimal subtotal = BigDecimal.valueOf(Double.parseDouble(totCompra[1])/1.16);
        Double impuestos = (Double.parseDouble(totCompra[1])/1.16)*0.16;
        int i = 1;

        PdfDocument pdfDoc = PdfDocument.fromFile(Paths.get("src/main/resources/files/FACTURABrisbiere.pdf"));

        for (String item: content) {
            pdfDoc.replaceText(PageSelection.firstPage(),String.valueOf(i)+" -", i+" -"+item);
            System.out.println("{item"+i+"}"+item);
            i++;
        }

        pdfDoc.replaceText(PageSelection.firstPage(),"{fec}",String.valueOf(fecha));
        pdfDoc.replaceText(PageSelection.firstPage(),"{Fac}", String.valueOf(numeroFactura));
        pdfDoc.replaceText(PageSelection.firstPage(), "{nom}", toUser);
        pdfDoc.replaceText(PageSelection.firstPage(),"{uId}", String.valueOf(user.get().getUserId()));
        pdfDoc.replaceText(PageSelection.firstPage(),"{sub}", df.format(subtotal));
        pdfDoc.replaceText(PageSelection.firstPage(),"{imp}", df.format(impuestos));
        pdfDoc.replaceText(PageSelection.firstPage(),"{com}", totCompra[1]);

        try {
            pdfDoc.saveAs(Paths.get("src/main/resources/files/FACTURA.pdf"));
            String fileName = "FACTURA.pdf";
            Path path = Paths.get("src/main/resources/Files/FACTURA.pdf");
            File file = path.toFile();

            emailRepository.sendEmailWithFile(toUser,subject,message, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
