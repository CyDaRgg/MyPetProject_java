package edu.javacourse.city.web;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("checkPersonHandler")
public class CheckPersonHandlerHtml {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtmlPage() {
        // Здесь можно вернуть HTML-страницу в виде строки
        String htmlPage = "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<form action=\"checkPerson\" method=\"get\">\n" +
                "    Surname:<input type=\"text\" name=\"surname\"/><br>\n" +
                "    Givenname:<input type=\"text\" name=\"givenname\"/><br>\n" +
                "    Patronymic:<input type=\"text\" name=\"patronymic\"/><br>\n" +
                "    Date of birth:<input type=\"text\" name=\"dateOfBirth\"/><br>\n" +
                "    Street Code:<input type=\"text\" name=\"streetCode\"/><br>\n" +
                "    Building:<input type=\"text\" name=\"building\"/><br>\n" +
                "    Extension:<input type=\"text\" name=\"extension\"/><br>\n" +
                "    Apartment:<input type=\"text\" name=\"apartment\"/><br>\n" +
                "    <input type=\"submit\" value=\"GO\">\n" +
                "</form>\n" +
                "<br>\n" +
                "<button onclick=\"goBack()\">Назад</button>\n" +
                "\n" +
                "<script>\n" +
                "        function goBack() {\n" +
                "            window.history.back();\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n" +
                "\n";
        return htmlPage;
    }

}
