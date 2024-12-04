package de.johannes.processing;

import java.io.*;

public class FileUtil {
    public static String readFile(String location) throws Exception{
        File file = new File(location);
        if(!file.exists()) throw new IOException("File does not exist");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder str = new StringBuilder();
        String line = "";
        while((line = reader.readLine()) != null) {
            str.append(line);
            str.append("\n");
        }
        reader.close();
        return str.toString();
    }

    public static void writeFile(String content, String location) throws IOException {
        File output = new File(location);
        if(!output.exists()) {
            output.getParentFile().mkdirs();
            output.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
        writer.write(content);
        writer.flush();
        writer.close();
    }
}
