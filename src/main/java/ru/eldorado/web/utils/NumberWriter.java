package ru.eldorado.web.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by OsipovI on 15.07.2015.
 */
public class NumberWriter {
    public static void writeOut(String fileName, String text) throws FileNotFoundException {
        File file = new File(fileName);

        try {
            if(!file.exists()){ file.createNewFile();}

            PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile());
            try {
                printWriter.println(text);
            } finally {
                printWriter.close();
            }

        } catch (IOException e) { throw new RuntimeException(e);}
    }

}
