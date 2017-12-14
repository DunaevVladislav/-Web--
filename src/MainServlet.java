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

        DataForPage data = null;


        response.setContentType("text/html; charset=UTF8");
        PrintWriter out = response.getWriter();


        try {
            data = new DataForPage();
            data.insert(request.getRemoteAddr().toString());
        } catch (SQLException|ClassNotFoundException e) {
            out.println("<p>Не удалось подключиться к  базе данных</p>");
            out.close();
            return;
        }

        try {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"ru\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <link rel='stylesheet' type='text/css' href='css/index.css'/>\n" +
                    "    <title>Страница бригады №11</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div id='maindiv'>\n" +
                    "    <div id='titlediv'>\n" +
                    "        <p id='title1'>Страница бригады №11</p>\n" +
                    "        <b>Выполнили</b><br/>\n" +
                    "        студенты 2 курса<br/>\n" +
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
            out.println("<p>" + data.getDate() + "</p>");
            out.println("<p> Количество <a href='about'> посетителей</a>: " + data.getCountUsers() + "</p>");
            out.println("</div>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (SQLException e) {
            out.println("<p>Кажется что-то пошло не так</p>");
        }finally {
            out.close();
        }

        try {
            data.finalize();
        } catch (SQLException ignored){};

    }
}
