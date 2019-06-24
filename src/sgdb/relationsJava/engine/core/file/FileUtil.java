package sgdb.relationsJava.engine.core.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileUtil {
    public static String getFileContent(String fileName) throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
        }

        return stringBuilder.toString();
    }

    public static void writeInFile(String fileName, String fileContent) throws Exception{
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write(fileContent);
        fileWriter.flush();
    }
}
