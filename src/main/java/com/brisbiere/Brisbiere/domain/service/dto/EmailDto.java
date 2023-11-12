package com.brisbiere.Brisbiere.domain.service.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String toUser;
    private String subject;
    private String message;
    private String[] content;
    private String totalCompra;
    private String fecha;

    public String getToUser() {
        return toUser;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String[] getContent() {
        return content;
    }

    public String getTotalCompra() {
        return totalCompra;
    }

    public String getFecha() {
        return fecha;
    }
}
