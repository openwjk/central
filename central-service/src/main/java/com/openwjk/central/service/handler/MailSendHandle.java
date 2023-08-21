package com.openwjk.central.service.handler;

import com.alibaba.fastjson2.JSONObject;
import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/18 19:29
 */
@Service
public class MailSendHandle {
    private static final String INVALID_ADDRESS_MESSAGE = "Invalid Addresses";
    private static final Logger logger = LoggerFactory.getLogger(MailSendHandle.class);

    /**
     * 发送带附件的邮件
     *
     * @param mailHost
     * @param port
     * @param sendAccount
     * @param sendPassword
     * @param toMail
     * @param subject
     * @param content
     * @param file
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static void sendMail(String mailHost, String port, String sendAccount, String sendPassword, String[] toMail, String subject, String content, File file) throws MailException {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Properties properties = buildProperties(mailHost, port);

            Session mailSession = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sendAccount, sendPassword);
                }
            });
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject, "utf-8");
            message.setFrom(new InternetAddress(sendAccount));

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);


            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
            multipart.addBodyPart(attachmentBodyPart);

            //以smtp的方式登入mail server
            transport.connect(mailHost, sendAccount, sendPassword);
            for (String email : toMail) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            logger.info("send mail success");
        } catch (SendFailedException se) {
            if (INVALID_ADDRESS_MESSAGE.equals(se.getMessage())) {
                Address[] invalidAddresses = se.getInvalidAddresses();
                List<String> invalidList = new ArrayList<>();
                if (null != invalidAddresses && invalidAddresses.length != 0) {
                    for (Address address : invalidAddresses) {
                        InternetAddress internetAddress = (InternetAddress) address;
                        invalidList.add(internetAddress.getAddress());
                    }
                }
                logger.error(String.format("send mail error cause by invalid address :[%s]",
                        JSONObject.toJSONString(invalidList)), se);
                throw new CommonsException("invalid address:" + JSONObject.toJSONString(invalidList));
            }
            throw new CommonsException(se.getMessage());
        } catch (Exception ex) {
            logger.error(String.format("send mail error:[%s]", ex.getMessage()), ex);
            throw new CommonsException(ex.getMessage());
        }
    }


    /**
     * ssl加密发送邮件端口465，587是明文传输
     *
     * @param mailHost
     * @param port
     * @param sendAccount
     * @param sendPassword
     * @param toMail
     * @param subject
     * @param content
     */
    public static void sendMail(String mailHost, String port, String sendAccount, String sendPassword, String[] toMail, String subject, String content) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Properties properties = buildProperties(mailHost, port);

            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sendAccount, sendPassword);
                }
            });
            //通过会话,得到一个邮件,用于发送
            Message msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(sendAccount));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            for (String email : toMail) {
                if (StringUtils.isEmpty(email)) continue;
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            msg.setSubject(subject);
            //设置发送的日期
            msg.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(content, "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            msg.setContent(mainPart);

            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            logger.info("send mail success");
        } catch (SendFailedException se) {
            if (INVALID_ADDRESS_MESSAGE.equals(se.getMessage())) {
                Address[] invalidAddresses = se.getInvalidAddresses();
                List<String> invalidList = new ArrayList<>();
                if (null != invalidAddresses && invalidAddresses.length != 0) {
                    for (Address address : invalidAddresses) {
                        InternetAddress internetAddress = (InternetAddress) address;
                        invalidList.add(internetAddress.getAddress());
                    }
                }
                logger.error(String.format("send mail error cause by invalid address :[%s]",
                        JSONObject.toJSONString(invalidList)), se);
                throw new CommonsException("invalid address:" + JSONObject.toJSONString(invalidList));
            }
        } catch (Exception ex) {
            logger.error(String.format("send mail error:[%s]", ex.getMessage()), ex);
            throw new CommonsException(ex.getMessage());
        }
    }

    /**
     * 带抄送
     *
     * @param mailHost
     * @param port
     * @param sendAccount
     * @param sendPassword
     * @param toMail
     * @param ccMail       抄送
     * @param subject
     * @param content
     */
    public static void sendMail(String mailHost, String port, String sendAccount, String sendPassword, String[] toMail, String[] ccMail, String subject, String content) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Properties properties = buildProperties(mailHost, port);

            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sendAccount, sendPassword);
                }
            });
            //通过会话,得到一个邮件,用于发送
            Message msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(sendAccount));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            for (String email : toMail) {
                if (StringUtils.isEmpty(email)) continue;
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            //设置抄送人
            for (String email : ccMail) {
                if (StringUtils.isEmpty(email)) continue;
                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
            }
            msg.setSubject(subject);
            //设置发送的日期
            msg.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(content, "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            msg.setContent(mainPart);

            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            logger.info("send mail success");
        } catch (SendFailedException se) {
            if (INVALID_ADDRESS_MESSAGE.equals(se.getMessage())) {
                Address[] invalidAddresses = se.getInvalidAddresses();
                List<String> invalidList = new ArrayList<>();
                if (null != invalidAddresses && invalidAddresses.length != 0) {
                    for (Address address : invalidAddresses) {
                        InternetAddress internetAddress = (InternetAddress) address;
                        invalidList.add(internetAddress.getAddress());
                    }
                }
                logger.error(String.format("send mail error cause by invalid address :[%s]",
                        JSONObject.toJSONString(invalidList)), se);
                throw new CommonsException("invalid address:" + JSONObject.toJSONString(invalidList));
            }
            throw new CommonsException(se.getMessage());
        } catch (Exception ex) {
            logger.error(String.format("send mail error:[%s]", ex.getMessage()), ex);
            throw new CommonsException(ex.getMessage());
        }
    }

    /**
     * 设置邮件会话参数
     *
     * @return properties
     */
    private static Properties buildProperties(String mailHost, String port) {
        Properties properties = new Properties();
        final String sslFactory = "javax.net.ssl.SSLSocketFactory";
        //邮箱的发送服务器地址
        properties.setProperty("mail.smtp.host", mailHost);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.socketFactory.class", sslFactory);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        //邮箱发送服务器端口,这里设置为465端口
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.socketFactory.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        return properties;
    }
}
