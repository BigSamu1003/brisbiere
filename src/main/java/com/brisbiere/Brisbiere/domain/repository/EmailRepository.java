package com.brisbiere.Brisbiere.domain.repository;

import java.io.File;

public interface EmailRepository {
    public void sendEmail(String toUser, String subject, String message);
    void  sendEmailWithFile(String toUser, String subject, String message, File file);
}
