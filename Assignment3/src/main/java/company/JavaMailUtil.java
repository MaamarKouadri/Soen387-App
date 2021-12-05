package company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMailUtil {

    public static void sendMail(String recepient, String userName) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "dummy.for.school.23@gmail.com";
        String password = "zyrxkvjavllnlbyq";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }

        });


        try {
            Message message = prepareMessage(session, myAccountEmail, recepient, userName);
            Transport.send(message);
            Message ForgetPassword = prepareForgetPassword(session, myAccountEmail, recepient, userName);
            Transport.send(ForgetPassword);
            System.out.println("Message sent Succesfully!");
        }catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }


    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String userName) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,  new InternetAddress(recepient));
            message.setSubject("[TEST]: Welcome " + userName);
            String htmlCode = "Hello " + userName + "<br><br>Thank you for signing up. Please validate your email by click on the Validate Email butten below. <br><br>" +
                    "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "                                            <tr>\n" +
                    "                                                <td align=\"center\" width=\"180\" height=\"25\" bgcolor=\"#36a9e0\"\n" +
                    "                                                    style=\" display: block; font-family:Arial, sans-serif; font-size:18px; color:#ffffff; font-weight:bold;border-radius: 25px;\">\n" +
                    "                                                    <a href=\"https://Google.com\"\n" +
                    "                                                        target=\"_blank\"\n" +
                    "                                                        style=\"text-decoration:none; color:#ffffff; display:block; line-height:25px; \">\n" +
                    "                                                       Validate Email \n" +
                    "                                                    </a>\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>" +
                    "" +
                    "\n\n <br><br> Thank you!";
            message.setContent(htmlCode, "text/html");
        } catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println("Error");
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static Message prepareForgetPassword(Session session, String myAccountEmail, String recepient, String userName) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,  new InternetAddress(recepient));
            message.setSubject("[TEST]: Reset Password");
            String htmlCode = "Hello " + userName + "<br><br>You have requested a password rest. Please confirm below by clicking the Reset Password below <br><br>" +
                    "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "                                            <tr>\n" +
                    "                                                <td align=\"center\" width=\"180\" height=\"25\" bgcolor=\"#36a9e0\"\n" +
                    "                                                    style=\" display: block; font-family:Arial, sans-serif; font-size:18px; color:#ffffff; font-weight:bold;border-radius: 25px;\">\n" +
                    "                                                    <a href=\"https://Google.com\"\n" +
                    "                                                        target=\"_blank\"\n" +
                    "                                                        style=\"text-decoration:none; color:#ffffff; display:block; line-height:25px; \">\n" +
                    "                                                       Reset Password \n" +
                    "                                                    </a>\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>" +
                    "" +
                    "\n\n <br><br> Thank you!";
            message.setContent(htmlCode, "text/html");



            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
