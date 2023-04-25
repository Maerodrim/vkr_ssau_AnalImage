package tools;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileHelper {

    public static List<File> getFile() {
        File dir = new File("image");
        List<File> imageList = new LinkedList<>();
        if (dir.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : dir.listFiles()) {

                if (item.isDirectory()) {

                    System.out.println(item.getName() + "  \t folder");
                } else {
                    imageList.add(item);
                    System.out.println(item.getName() + "\t file");
                }
            }
        }
        return imageList;
    }
}
