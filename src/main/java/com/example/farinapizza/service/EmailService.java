package com.example.farinapizza.service;

import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    private String subject;
    private String htmlContent;
    private String b_account = "kenny19961108@gmail.com"; // 店長的Email

    private void sendHtmlEmail(String email) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("kenny199611081158@gmail.com", "Farina Pizza");
            helper.setTo(email);
            helper.setSubject(this.subject);
            helper.setText(this.htmlContent, true); // true = HTML

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 設定 三位數驗證碼 訊息 並寄送
    public void sendVerificationMail(short num, String email) {
        this.subject = "驗證碼";
        this.htmlContent = "<p><b>驗證碼：</b></p><p><span style=\"font-size:24px; color:#000000; font-weight:bold; font-family:Arial;\">" + num + "</span></p>";

        sendHtmlEmail(email);
    }

    // 設定 訂位成功 訊息 並寄送
    public void sendBookingSuccessfullyMail(String email, String branch, String date, String time, String people, String note) {
        this.subject = "訂位資訊";
        this.htmlContent = "<div style=\"font-family: Arial, sans-serif; font-size: 16px; color: #333; padding: 24px; max-width: 480px;\">" +
                "<h2 style=\"color: #2e7d32; margin-bottom: 20px;\">🍕 Farina Pizza 訂位成功通知</h2>" +
                "<table style=\"width: 100%; border-collapse: collapse; font-size: 16px;\">" +
                "<tr>" +
                "<td style=\"width: 90px; padding: 6px 10px; text-align: right; font-weight: bold; vertical-align: top;\">門市：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + branch + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">日期：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + date + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">時間：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + time + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">人數：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + people + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">備註：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + (note.equals("None") ? "無" : note) + "</td>" +
                "</tr>" +
                "</table>" +
                "<p style=\"margin-top: 20px; color: red; font-size: 15px; font-weight: bold;\">" +
                "※ 餐廳訂位只保留 10 分鐘，請準時抵達，謝謝您的配合！" +
                "</p>" +
                "</div>";

        sendHtmlEmail(email);
    }

    // 設定 寄給店長的訂單通知 訊息 並寄送
    public void sendCreateOrderSuccessfullyMail(String name, String email, String branch, String date, String time, String people, String note) {
        this.subject = "訂單通知";
        this.htmlContent = "<div style=\"font-family: Arial, sans-serif; font-size: 16px; color: #333; padding: 24px; max-width: 480px;\">" +
                "<h2 style=\"color: #2e7d32; margin-bottom: 20px;\">🍕 Farina Pizza 訂單通知!</h2>" +
                "<table style=\"width: 100%; border-collapse: collapse; font-size: 16px;\">" +
                "<tr>" +
                "<td style=\"width: 90px; padding: 6px 10px; text-align: right; font-weight: bold; vertical-align: top;\">門市：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + branch + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">日期：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + date + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">時間：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + time + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">貴賓：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + name + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">E-mail：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + email + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">人數：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + people + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 6px 10px; text-align: right; font-weight: bold;\">備註：</td>" +
                "<td style=\"padding: 6px 10px; color: #750000;\">" + (note.equals("None") ? "無" : note) + "</td>" +
                "</tr>" +
                "</table>" +
                "</div>";

        sendHtmlEmail(b_account);
    }
}
