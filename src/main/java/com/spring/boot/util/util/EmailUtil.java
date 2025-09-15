package com.spring.boot.util.util;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import static com.spring.boot.util.constant.ApplicationConstant.*;

public class EmailUtil {

    private static final Log log = LogFactory.getLog(EmailUtil.class);

    public static void sendEmailWithUrl(JavaMailSenderImpl javaMailSender, String email, String jobId, String url, String contentTemplateFile) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 设置标题
            mimeMessageHelper.setSubject("Online analysis of " + CommonCode.PROJECT_EN_NAME + " platform");
            // 获取 html 模板内容
            String template = FileUtil.readResourceToString(contentTemplateFile, "utf-8");
            // 添加作业 ID
            template = template.replaceAll(EMAIL_ID_REPLACE_CODE, jobId);
            if (StringUtil.isNotEmpty(url)) {
                template = template.replaceAll(EMAIL_URL_REPLACE_CODE, url);
            }
            // 设置发送内容
            mimeMessageHelper.setText(template, true);
            // 设置邮箱信息
            mimeMessageHelper.setFrom(FROM_EMAIL);
            mimeMessageHelper.setTo(email);
        } catch (MessagingException e) {
            log.error("[sendEmail] Failed to create the sent mailbox information: e-mail: {}, jobId: {} error: {}", email, jobId, e);
        }
        log.info("[sendEmail] Mailbox information sent: e-mail: {}, jobId: {}", email, jobId);
        // 发送邮箱
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            log.error("[executeSendEmailCode] Failed to send mailbox: e-mail: {}, jobId: {} error: {}", email, jobId, e);
        }
    }

    public static void sendEmail(JavaMailSenderImpl javaMailSender, String email, String jobId, String contentTemplateFile) {
        sendEmailWithUrl(javaMailSender, email, jobId, null, contentTemplateFile);
    }

}
