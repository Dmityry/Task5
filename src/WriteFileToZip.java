import fileFilter.DirectoryFileFilter;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class WriteFileToZip {

    public static final DirectoryFileFilter DIRECTORY_FILE_FILTER = new DirectoryFileFilter();

    public void filterFiles(File file, FileFilter filter, List<File> result) {

        Collections.addAll(result, file.listFiles(filter));
        for (File file1 : file.listFiles(DIRECTORY_FILE_FILTER)) {
            filterFiles(file1, filter, result);
        }


    }

    public void zip(File file, ZipOutputStream zip) {
        try {
            zip.putNextEntry(new ZipEntry(file.getPath()));
            writeFile(new FileInputStream(file), zip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(InputStream in, OutputStream zip) throws IOException {
        byte[] buffer = new byte[8192];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            zip.write(buffer, 0, len);
        }

        in.close();
    }
}
