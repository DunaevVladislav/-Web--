package dataPack;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataForPage{

    private static final String path = System.getenv("CATALINA_HOME") + "/webapps/TeamPage/";
/*
    static {
        File checkExist = new File(path + "WEB-INF/Data/cntVisitors.txt");
        try {
            if (!checkExist.exists()) {
                checkExist.createNewFile();
                RandomAccessFile f = new RandomAccessFile(path + "WEB-INF/Data/cntVisitors.txt", "w");
                f.writeInt(0);
                f.close();
            }
            RandomAccessFile file = new RandomAccessFile(path + "WEB-INF/Data/cntVisitors.txt", "rw");
            Integer cntVisitors = file.readInt();
            cntVisitors++;
            file.writeInt(cntVisitors);
            file.close();
        } catch (IOException ignored){};
    }*/

    public static String getDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("Текущая дата E dd.MM.yyyy '. Текущее время' hh:mm:ss zzz");
        return formatForDate.format(dateNow);
    }

    public static Integer getCntVisits() {

       /*try {
            RandomAccessFile file= new RandomAccessFile(path + "WEB-INF/Data/cntVisitors.txt", "r");
            Integer cntVisitots = Integer.parseInt(file.readLine());
            file.close();
            return cntVisitots;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }*/
       return -1;
    }

}
