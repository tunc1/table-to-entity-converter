package app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOUtil
{
    public static void writeToFile(String data,String fileName,String path) throws IOException
    {
        File file=new File(path+"\\"+fileName+".java");
        file.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.close();
    }
}