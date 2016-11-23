import fileFilter.AudioFileFilter;
import fileFilter.ImageFileFilter;
import fileFilter.VideoFileFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final FileFilter AUDIO_FILE_FILTER = new AudioFileFilter();
        final FileFilter IMAGE_FILE_FILTER = new ImageFileFilter();
        final FileFilter VIDEO_FILE_FILTER = new VideoFileFilter();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.out.println("Error");
            return;
        }

        WriteFileToZip service = new WriteFileToZip();

        List<File> audioFiles = new ArrayList<>();
        service.filterFiles(dir, AUDIO_FILE_FILTER, audioFiles);
        if (!audioFiles.isEmpty()) {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream("audio.zip"));
            audioFiles.forEach(file -> service.zip(file, out));
            out.close();
        }
        List<File> imageFiles = new ArrayList<>();
        service.filterFiles(dir, IMAGE_FILE_FILTER, imageFiles);
        if (!imageFiles.isEmpty()) {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream("image.zip"));
            imageFiles.forEach(file -> service.zip(file, out));
            out.close();
        }
        List<File> videoFiles = new ArrayList<>();
        service.filterFiles(dir, VIDEO_FILE_FILTER, videoFiles);
        if (!videoFiles.isEmpty()) {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream("video.zip"));
            videoFiles.forEach(file -> service.zip(file, out));
            out.close();
        }
    }
}
