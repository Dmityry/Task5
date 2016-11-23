import fileFilter.AudioFileFilter;
import fileFilter.DirectoryFileFilter;
import fileFilter.ImageFileFilter;
import fileFilter.VideoFileFilter;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class WriteFileToZip {
    private static final FileFilter AUDIO_FILE_FILTER = new AudioFileFilter();
    private static final FileFilter IMAGE_FILE_FILTER = new ImageFileFilter();
    private static final FileFilter VIDEO_FILE_FILTER = new VideoFileFilter();
    private static final FileFilter DIRECTORY_FILE_FILTER = new DirectoryFileFilter();


    public WriteFileToZip() throws FileNotFoundException {
    }

    public void doZip(File dir, ZipOutputStream audio, ZipOutputStream video, ZipOutputStream image) throws IOException {

        for (File f : dir.listFiles(AUDIO_FILE_FILTER)) {
            audio.putNextEntry(new ZipEntry(f.getPath()));
            writeFile(new FileInputStream(f), audio);
        }

        for (File f : dir.listFiles(IMAGE_FILE_FILTER)) {
            image.putNextEntry(new ZipEntry(f.getPath()));
            writeFile(new FileInputStream(f), image);
        }

        for (File f : dir.listFiles(VIDEO_FILE_FILTER)) {
            video.putNextEntry(new ZipEntry(f.getPath()));
            writeFile(new FileInputStream(f), video);
        }

        for (File f : dir.listFiles(DIRECTORY_FILE_FILTER)) {
            doZip(f, audio, video, image);
        }
    }
/*
    public Map<ZipEntry, FileInputStream> filterFilesToStream(File f, Map fileMap) throws FileNotFoundException {
        if (f.isDirectory()) {

        } else {
            for (File file : f.listFiles(AUDIO_FILE_FILTER)) {
                fileMap.put(new ZipEntry(f.getPath()), new FileInputStream(f));
            }
        }
        fileMap.put(new ZipEntry(f.getPath()), new FileInputStream(f));
        return fileMap;
    }*/

    private static void writeFile(InputStream in, OutputStream zip) throws IOException {
        byte[] buffer = new byte[8192];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            zip.write(buffer, 0, len);
        }

        in.close();
    }
}
