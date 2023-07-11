package edu.javacourse.city.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CheckPersonResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtmlPage() {
        // Здесь можно вернуть HTML-страницу в виде строки
        String htmlPage = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Приветствие</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<h1>Добро пожаловать!</h1>\n" +
                "<p>Это страница приветствия.</p>\n" +
                "<button onclick=\"window.location.href = 'checkPersonHandler'\">Перейти к проверке аутентификации</button>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return htmlPage;
    }
}
