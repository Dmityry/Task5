import java.io.*;
import java.util.zip.ZipOutputStream;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        File dir = new File(path);
        File[] filesAudio = dir.listFiles((FileFilter) new ExpansionAudio.AudioFileFilter());
        File[] filesImage = dir.listFiles((FileFilter) new ExpansionImage.ImageFileFilter());
        File[] filesVideo = dir.listFiles((FileFilter) new ExpantionVideo.VideoFileFilter());

        for (File file : filesAudio) {
            ZipOutputStream zipAudio = new ZipOutputStream(new FileOutputStream("audio.zip"));
            WriteFileToZip.doZipAudio(dir, zipAudio);
            zipAudio.close();
        }

        for (File file : filesImage) {
            ZipOutputStream zipImage = new ZipOutputStream(new FileOutputStream("images.zip"));
            WriteFileToZip.doZipImage(dir, zipImage);
            zipImage.close();
        }

        for (File file : filesVideo) {
            ZipOutputStream zipVideo = new ZipOutputStream(new FileOutputStream("video.zip"));
            WriteFileToZip.doZipImage(dir, zipVideo);
            zipVideo.close();
        }

    }
}
