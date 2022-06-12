package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;

public class EmailBodyDTO {
    @NotBlank
    String toEmail;
    @NotBlank
    String subject;
    @NotBlank
    String bodyQuestion;
    @NotBlank
    String idQuestion;


    public EmailBodyDTO( @NotBlank String toEmail, @NotBlank String subject, @NotBlank String bodyQuestion,   @NotBlank String idQuestion) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.bodyQuestion = bodyQuestion;
        this.idQuestion = idQuestion;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyQuestion() {
        return bodyQuestion;
    }

    public void setBodyQuestion(String bodyQuestion) {
        this.bodyQuestion = bodyQuestion;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public static String getBodyMailQuestion(String bodyQuestion,String idQuestion){
        return  "<!DOCTYPE html\n" +
                "    PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"width: 100% !important; -webkit-text-size-adjust: none; margin: 0; padding: 0;\">\n" +
                "    <center>\n" +
                "        <table id=\"backgroundTable\"\n" +
                "            style=\"border-spacing: 0; border-collapse: collapse; font-family: proxima-nova, 'helvetica neue', helvetica, arial, geneva, sans-serif; width: 100% !important; height: 100% !important; color: #4c4c4c; font-size: 15px; line-height: 150%; background: #ffffff; margin: 0; padding: 0; border: 0;\">\n" +
                "            <tr style=\"vertical-align: top; padding: 0;\">\n" +
                "                <td align=\"center\" valign=\"top\" style=\"vertical-align: top; padding: 0;\">\n" +
                "                    <table id=\"templateContainer\"\n" +
                "                        style=\"border-spacing: 0; border-collapse: collapse; font-family: proxima-nova, 'helvetica neue', helvetica, arial, geneva, sans-serif; width: 600px; color: #4c4c4c; font-size: 15px; line-height: 150%; background: #ffffff; margin: 40px 0; padding: 0; border: 0;\">\n" +
                "                        <tr style=\"vertical-align: top; padding: 0;\">\n" +
                "                            <td class=\"templateContainerPadding\" align=\"center\" valign=\"top\"\n" +
                "                                style=\"vertical-align: top; padding: 0 40px;\">\n" +
                "                                <table id=\"templateContent\"\n" +
                "                                    style=\"border-spacing: 0; border-collapse: collapse; font-family: proxima-nova, 'helvetica neue', helvetica, arial, geneva, sans-serif; width: 100%; background: #ffffff; margin: 0; padding: 0; border: 0;\">\n" +
                "                                    <tr style=\"vertical-align: top; padding: 0;\">\n" +
                "                                        <td style=\"vertical-align: top; text-align: left; padding: 0;\" align=\"left\"\n" +
                "                                            valign=\"top\">\n" +
                "                                            <h1 id=\"logo\"\n" +
                "                                                style=\"color: #6E5BAA; display: block; font-family: hybrea, proxima-nova, 'helvetica neue', helvetica, arial, geneva, sans-serif; font-size: 32px; font-weight: 200; text-align: left; margin: 0 0 40px;\"\n" +
                "                                                align=\"left\"><img\n" +
                "                                                    src=\"https://ci4.googleusercontent.com/proxy/P-Ww4rruLIPP35oJLO69xB7ybk8v1VFImfcmq3FtDU2Pz25YR-WgAFfiIb8IPDhSlhthwQzInQdur2pgeBlIC1tQTYZIqZL96DaMmTs6MjxsOSkijshBHdeEshdyFe_IsmWY0w=s0-d-e1-ft#https://www.sofka.com.co/wp-content/uploads/2020/08/sofka-logo-gradient-white.png\"\n" +
                "                                                    alt=\"heroku\" width=\"120\" height=\"42\"\n" +
                "                                                    style=\"outline: none; text-decoration: none; border: 0;\" /></h1>\n" +
                "\n" +
                "                                            <h2>Hola!</h2>\n" +
                "                                            <p style=\"margin: 20px 0;\">Parece que alguien a respondido a tu pregunta:\n" +
                "                                            </p>\n" +
                "                                            <h3> "+bodyQuestion+" </h3>\n" +
                "                                            <p>puedes ver la respuesta aquí:</p>\n" +
                "\n" +
                "\n" +
                "                                            <a target=\"_blank\" href=\"https://angular1-a60b9.web.app/wuestion/"+idQuestion+"\">\n" +
                "                                                <button >Ver Respuestas</button>\n" +
                "                                            </a>\n" +
                "                                                \n" +
                "                                \n" +
                "                                            <p style=\"margin: 20px 0;\">Gracias por participar en <span\n" +
                "                                                    style=\"color: #24A1EE; font-weight:bold; font-size: large;\">Sofka\n" +
                "                                                    Questions.</span>\n" +
                "                                            </p>\n" +
                "\n" +
                "\n" +
                "                                            <p style=\"margin: 20px 0;\">\n" +
                "                                                Marlon Camila Team<br />\n" +
                "                                                <a href=\"https://angular1-a60b9.web.app/preguntas\" target=\"_blank\"\n" +
                "                                                    style=\"color: #6E5BAA;\">https://sofkapreguntas.com</a>\n" +
                "                                            </p>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr style=\"vertical-align: top; padding: 0;\">\n" +
                "                            <td class=\"templateContainerPadding\" align=\"center\" valign=\"top\"\n" +
                "                                style=\"vertical-align: top; padding: 0 40px;\">\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </center>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "        body {\n" +
                "            width: 100% !important;\n" +
                "        }\n" +
                "\n" +
                "        .ReadMsgBody {\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        .ExternalClass {\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            border: 0;\n" +
                "            outline: none;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        #backgroundTable {\n" +
                "            height: 100% !important;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            width: 100% !important;\n" +
                "        }\n" +
                "\n" +
                "        #backgroundTable {\n" +
                "            color: #4c4c4c;\n" +
                "            background-color: #ffffff;\n" +
                "            font-family: proxima-nova, 'helvetica neue', helvetica, arial, geneva, sans-serif;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 150%;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width: 540px) {\n" +
                "            #templateContainer {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            #templateContainer .templateContainerPadding {\n" +
                "                padding: 0 5% !important;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        button {\n" +
                "            align-items: center;\n" +
                "            background-color: #0A66C2;\n" +
                "            border: 0;\n" +
                "            border-radius: 100px;\n" +
                "            box-sizing: border-box;\n" +
                "            color: #ffffff;\n" +
                "            cursor: pointer;\n" +
                "            display: inline-flex;\n" +
                "            font-family: -apple-system, system-ui, system-ui, \"Segoe UI\", Roboto, \"Helvetica Neue\", \"Fira Sans\", Ubuntu, Oxygen, \"Oxygen Sans\", Cantarell, \"Droid Sans\", \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Lucida Grande\", Helvetica, Arial, sans-serif;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: 600;\n" +
                "            justify-content: center;\n" +
                "            line-height: 20px;\n" +
                "            max-width: 480px;\n" +
                "            min-height: 40px;\n" +
                "            min-width: 0px;\n" +
                "            overflow: hidden;\n" +
                "            padding: 0px;\n" +
                "            padding-left: 20px;\n" +
                "            padding-right: 20px;\n" +
                "            text-align: center;\n" +
                "            touch-action: manipulation;\n" +
                "            transition: background-color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, box-shadow 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s;\n" +
                "            user-select: none;\n" +
                "            -webkit-user-select: none;\n" +
                "            vertical-align: middle;\n" +
                "        }\n" +
                "\n" +
                "        button:hover,\n" +
                "        button:focus {\n" +
                "            background-color: #16437E;\n" +
                "            color: #ffffff;\n" +
                "        }\n" +
                "\n" +
                "        button:active {\n" +
                "            background: #09223b;\n" +
                "            color: rgb(255, 255, 255, .7);\n" +
                "        }\n" +
                "\n" +
                "        button:disabled {\n" +
                "            cursor: not-allowed;\n" +
                "            background: rgba(0, 0, 0, .08);\n" +
                "            color: rgba(0, 0, 0, .3);\n" +
                "        }\n" +
                "    </style>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

    }
}
