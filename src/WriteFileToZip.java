import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class WriteFileToZip {
    static void writeFile(InputStream in, OutputStream zip) throws IOException {
        byte[] buffer = new byte[8192];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            zip.write(buffer, 0, len);
        }

        in.close();
    }

    static void doZipImage(File dir, ZipOutputStream zip) throws NullPointerException, IOException {
        for (File file : dir.listFiles((FileFilter) new ExpansionImage.ImageFileFilter())) {
            if (file.isDirectory()) {
                doZipImage(file, zip);
            } else {
                zip.putNextEntry(new ZipEntry(file.getName()));
                writeFile(new FileInputStream(file), zip);
            }
        }
    }

    static void doZipVideo(File dir, ZipOutputStream zip) throws NullPointerException, IOException {
        for (File file : dir.listFiles((FileFilter) new ExpantionVideo.VideoFileFilter())) {
            if (file.isDirectory()) {
                doZipVideo(file, zip);
            } else {
                zip.putNextEntry(new ZipEntry(file.getName()));
                writeFile(new FileInputStream(file), zip);
            }
        }
    }

    static void doZipAudio(File dir, ZipOutputStream zip) throws NullPointerException, IOException {
        for (File file : dir.listFiles((FileFilter) new ExpansionAudio.AudioFileFilter())) {
            if (file.isDirectory()) {
                doZipAudio(file, zip);
            } else {
                zip.putNextEntry(new ZipEntry(file.getName()));
                writeFile(new FileInputStream(file), zip);
            }
        }
    }
}
