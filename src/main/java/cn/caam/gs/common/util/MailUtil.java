package cn.caam.gs.common.util;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

import lombok.Data;
import cn.caam.gs.common.enums.MailSendResultType;
public class MailUtil {
    
    public static String PARAM_RECIVER_NAME = "$NAME";
    /**
     * データセット.
     */
    @Data
    public static class MailDataSet {
        /** 送信者の表示名. */
        private String fromName;
        /** 送信アドレス. */
        private String from;
        /** 宛先(TO). */
        private String[] toArray = new String[0];
        /** 宛先(CC). */
        private String[] ccArray = new String[0];
        /** 宛先(BCC). */
        private String[] bccArray = new String[0];
        /** 認証(ユーザー名). */
        private String authUsername;
        /** 認証(パスワード). */
        private String authPassword;
        /** ホスト名. */
        private String host;
        /** ポート. */
        private int port;
        /** 暗号化(TLS保護接続への接続). */
        private boolean starttls = false;
        /** 件名. */
        private String subject;
        /** 本文*/
        private String content;
        /** 附件 */
        private DataSource[] attachments;
        /** 附件名 */
        private String[] attachmentNames;
        
    }
 
    /**
     * メール送信.
     * @param mailDataset データセット
     * @return true:成功、false:失敗
     * @throws GeneralSecurityException 
     */
    public static MailSendResultType send(final MailDataSet mailDataSet) throws GeneralSecurityException {
        // ----------------------------------
        // 属性
        //  参考URL) https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html
        // ----------------------------------
        Properties props = new Properties();
        // ホスト名
        props.put("mail.smtp.host", mailDataSet.host);
        // ポート番号
        props.put("mail.smtp.port", String.valueOf(mailDataSet.port));
        // ソケット接続タイムアウト値(ミリ秒)
        props.put("mail.smtp.connectiontimeout", "60000");
        // ソケット読み取りタイムアウト値(ミリ秒)
        props.put("mail.smtp.timeout", "30000");
        // 暗号化(TLS保護接続への接続)
        props.put("mail.smtp.starttls.enable", String.valueOf(mailDataSet.starttls));
 
        // デバッグ情報出力有無
        props.put("mail.debug", "false");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);
 
        // 認証設定
        Authenticator auth = null;
        if (mailDataSet.authUsername != null && mailDataSet.authPassword != null) {
            // ユーザー認証
            props.put("mail.smtp.auth", "true");
            // ユーザー名、パスワード設定
            auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailDataSet.authUsername, mailDataSet.authPassword);
                }
            };
        }
 
        // 送信
        if(Objects.nonNull(mailDataSet.attachments) && mailDataSet.attachments.length > 0) {
            return sendWithAttachments(props, auth, mailDataSet);
        } else {
            return send(props, auth, mailDataSet);
        }
    }
    
    private static MailSendResultType send(Properties props, Authenticator auth, final MailDataSet mailDataSet) {

        // セッション生成
        Session session = Session.getInstance(props, auth);
 
        try {
            MimeMessage message = new MimeMessage(session);
            // 送信者
            message.setFrom(new InternetAddress(mailDataSet.from, mailDataSet.fromName));
            // 宛先(TO)
            for (String to : mailDataSet.toArray) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            // 宛先(CC)
            if(Objects.nonNull(mailDataSet.ccArray) && mailDataSet.ccArray.length > 0) {
                for (String cc : mailDataSet.ccArray) {
                    message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }
            // 宛先(BCC)
            if(Objects.nonNull(mailDataSet.bccArray) && mailDataSet.bccArray.length > 0) {
                for (String bcc : mailDataSet.bccArray) {
                    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
                }
            }
 
            final String charset = "UTF-8";
            final String encoding = "base64";
 
            // 送信日
            message.setSentDate(new Date());
            // 件名
            message.setSubject(mailDataSet.subject, charset);
            // 本文
           // message.setText(mailDataSet.content, charset);
            message.setContent(mailDataSet.content, "text/html; charset=utf-8");
            // ヘッダー
            message.setHeader("Content-Transfer-Encoding", encoding);
            // 送信
            Transport.send(message);
 
            return MailSendResultType.OK;
 
        } catch (AuthenticationFailedException e) {
            // 認証失敗
            e.printStackTrace();
            return MailSendResultType.NG_AUTH;
 
        } catch (MessagingException e) {
            // smtpサーバへの接続失敗
            e.printStackTrace();
            return MailSendResultType.NG_SERVER;
 
        } catch (Exception e) {
            // その他例外
            e.printStackTrace();
            return MailSendResultType.NG_OTHER;
        }
    }
    
    private static MailSendResultType sendWithAttachments(Properties props, Authenticator auth, final MailDataSet mailDataSet) {

        // セッション生成
        Session session = Session.getInstance(props, auth);
 
        try {
            MimeMessage message = new MimeMessage(session);
            // 送信者
            message.setFrom(new InternetAddress(mailDataSet.from, mailDataSet.fromName));
            // 宛先(TO)
            for (String to : mailDataSet.toArray) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            // 宛先(CC)
            if(Objects.nonNull(mailDataSet.ccArray) && mailDataSet.ccArray.length > 0) {
                for (String cc : mailDataSet.ccArray) {
                    message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }
            // 宛先(BCC)
            if(Objects.nonNull(mailDataSet.bccArray) && mailDataSet.bccArray.length > 0) {
                for (String bcc : mailDataSet.bccArray) {
                    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
                }
            }
 
            final String charset = "UTF-8";
            final String encoding = "base64";
 
            // 送信日
            message.setSentDate(new Date());
            // 件名
            message.setSubject(mailDataSet.subject, charset);
            // 本文
            message.setText(mailDataSet.content, charset);
            // ヘッダー
            message.setHeader("Content-Transfer-Encoding", encoding);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
            messageBodyPart.setText(mailDataSet.content);
            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            for(int i=0; i<mailDataSet.attachments.length; i++) {
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(mailDataSet.attachments[i]));
                messageBodyPart.setFileName(mailDataSet.attachmentNames[i]);
                multipart.addBodyPart(messageBodyPart);
            }

            // Send the complete message parts
            message.setContent(multipart);
            
            // 送信
            Transport.send(message);
 
            return MailSendResultType.OK;
 
        } catch (AuthenticationFailedException e) {
            // 認証失敗
            e.printStackTrace();
            return MailSendResultType.NG_AUTH;
 
        } catch (MessagingException e) {
            // smtpサーバへの接続失敗
            e.printStackTrace();
            return MailSendResultType.NG_SERVER;
 
        } catch (Exception e) {
            // その他例外
            e.printStackTrace();
            return MailSendResultType.NG_OTHER;
        }
    }
    
    public static void main(String[] args) throws Exception{
    	MailDataSet mailDataSet = new MailDataSet();
    	  /** 送信者の表示名. */
//        mailDataSet.setFromName("guokedong7821@163.com");
        /** 送信アドレス. */
        mailDataSet.setFrom("guokedong7821@163.com");
        /** 宛先(TO). */
        mailDataSet.setToArray(new String[] {"guokedong7821@163.com"});
        /** 宛先(CC). */
        mailDataSet.setCcArray(new String[0]);
        /** 宛先(BCC). */
        mailDataSet.setBccArray(new String[0]);
        /** 認証(ユーザー名). */
        mailDataSet.setAuthUsername("guokedong7821");
        /** 認証(パスワード). */
        mailDataSet.setAuthPassword("RXSQESCNXNAUCSOS");
        /** ホスト名. */
        mailDataSet.setHost("smtp.163.com");
        /** ポート. */
        mailDataSet.setPort(25);
        /** 暗号化(TLS保護接続への接続). */
        mailDataSet.setStarttls(true);
        /** 件名. */
        mailDataSet.setSubject("test");
        /** 本文*/
        mailDataSet.setContent("test");
        /** 附件 */
        
        /** 附件名 */
    	
    	
//    	MailDataSet mailDataSet = new MailDataSet();
//  	  /** 送信者の表示名. */
//      mailDataSet.setFrom("syakt@jcbc.jp");
//      /** 送信アドレス. */
//      mailDataSet.setFrom("syakt@jcbc.jp");
//      /** 宛先(TO). */
//      mailDataSet.setToArray(new String[] {"guokedong7821@163.com"});
//      /** 宛先(CC). */
//      mailDataSet.setCcArray(new String[0]);
//      /** 宛先(BCC). */
//      mailDataSet.setBccArray(new String[0]);
//      /** 認証(ユーザー名). */
//      mailDataSet.setAuthUsername("syakt@jcbc.jp");
//      /** 認証(パスワード). */
//      mailDataSet.setAuthPassword("Tuf1xrrzyb2p3DBB7JOnjg==");
//      /** ホスト名. */
//      mailDataSet.setHost("mail.jcbc.jp");
//      /** ポート. */
//      mailDataSet.setPort(587);
//      /** 暗号化(TLS保護接続への接続). */
//      mailDataSet.setStarttls(true);
//      /** 件名. */
//      mailDataSet.setSubject("test");
//      /** 本文*/
//      mailDataSet.setContent("test");
//      /** 附件 */
//      
//      /** 附件名 */
//      
     
        
    	MailUtil.send(mailDataSet);
    }
}
