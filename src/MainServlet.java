import dataPack.DataForPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            DataForPage.initial();
            DataForPage.insert(request.getRemoteAddr().toString());
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html; charset=UTF8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <link rel=\"stylesheet\" TYPE=\"text/css\" HREF=\"css/index.css\">\n" +
                "    <title>2 ЛАБОРАТОРНАЯ РАБОТА</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id='maindiv'>\n" +
                "    <div id='titlediv'>\n" +
                "        <p id='title1'>HTML-страница бригады №6</p>\n" +
                "        <b>Выполнили</b><br/>\n" +
                "        студенты 1 курса<br/>\n" +
                "        <a href=\"http://www.pnzgu.ru/\">Пензенского Государственного Университета</a><br>\n" +
                "        группы 16ВП1:\n" +
                "        <ul>\n" +
                "            <li>Денисов Артем</li>\n" +
                "            <li>Дунаев Владислав</li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div id='denicov'>\n" +
                "        <img class='photo'  src=\"img/Denicov.jpg\">\n" +
                "        <p class='phototext'>Денисов Артем</p>\n" +
                "        <p><b>Почтовый адрес:</b><a href=\"mailto://artyomdenisov98@mail.ru\">artyomdenisov98@mail.ru</a></p>\n" +
                "        <p><b>Образование:</b> Окончил Кадетскую школу по делам Гражданской обороны, Чрезвычайным ситуациям и Ликвидации последствий стихийных бедствий имени<br/> 70-летия Победы г.Пензы с отличием</p>\n" +
                "        <b>Увлечения</b>:\n" +
                "        <ol>\n" +
                "            <li>программирование</li>\n" +
                "            <li>компьютеры</li>\n" +
                "            <li>компьютерные игры</li>\n" +
                "        </ol>\n" +
                "        <p><b>Моя будущая профессия:</b> разработчик ПО</p>\n" +
                "    </div>\n" +
                "    <div id='dunaev'>\n" +
                "        <img class='photo'  src=\"img/Dunaev.jpg\">\n" +
                "        <p class='phototext'>Дунаев Владислав</p>\n" +
                "        <p><b>Почтовый адрес:</b><a href=\"mailto://dunvladyur4@gmail.com\">dunvladyur4@gmail.com</a></p>\n" +
                "        <p><b>Образование:</b> окончил 77 школу г.Пензы с отличием</p>\n" +
                "        <b>Увлечения:</b>\n" +
                "        <ol>\n" +
                "            <li>программирование</li>\n" +
                "            <li>компьютеры</li>\n" +
                "            <li>спидкубинг</li>\n" +
                "        </ol>\n" +
                "        <p><b>Моя будущая профессия:</b> backend разработчик</p>\n" +
                "    </div>");
        out.println("<p>" + DataForPage.getDate() + "</p>");
        try {
            out.println("<p>" + DataForPage.getCountUsers() + "</p>");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</div>\n" +
                "</body>\n" +
                "</html>");
        out.close();
        try {
            DataForPage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
