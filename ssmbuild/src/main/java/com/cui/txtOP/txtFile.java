package com.cui.txtOP;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class txtFile {
    private BufferedWriter bufferedWriter;
    private final DateFormat df = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
    private final DateFormat filedf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    public void txt_init(String pathname) throws Exception {
        String classPath = this.getClass().getResource("/").getPath();
        String filePath = classPath + pathname;
        // txt文档及写入流

        // 检查目录
        checkPath(filePath, classPath);

    }

    public void writeToTxt(String data) throws IOException {
        this.bufferedWriter.write(getTime());
        this.bufferedWriter.newLine();
        this.bufferedWriter.write(data);
        this.bufferedWriter.newLine();
    }

    public void close_txt() throws IOException {
        this.bufferedWriter.flush();
        this.bufferedWriter.close();
    }

    /*
    检查目录，不存在：创建，存在：移至日志文件夹
     */
    public void checkPath(String filePath, String classPath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }
        else {
            File logPath = new File(classPath + "log");
            String newfilePath = classPath + "log/" + get_Time() + ".txt";
            if(!logPath.exists()){
                logPath.mkdir();
            }
            //复制文件到log文件夹
            file.renameTo(new File(newfilePath));

        }
        System.out.println("txt文档路径：" + file.getAbsolutePath());
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
    }

    public String get_Time(){
        Calendar calendar = Calendar.getInstance();
        return filedf.format(calendar.getTime());
    }

    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
}
