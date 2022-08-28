package com.example.cashregister.Service.extra;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import static com.example.cashregister.Service.extra.Properties.getProperty;


public class EmailServiceOtpToken {
    static final String emailfrom = getProperty("email");


    static final String token = getProperty("token");
    private int otpvalue;
    public int getOtpvalue() {
        return otpvalue;
    }

    public void sendOtp(String email) {
        this.otpvalue = new Random().nextInt(1255650);
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailfrom, token);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailfrom));
            message.addRecipient(
                    Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Hello");
            message.setText("your OTP is: " + otpvalue);
            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
