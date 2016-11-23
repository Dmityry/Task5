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
        if (!dir.isDirectory()) {
            System.out.println("Error");
            return;
        }

        WriteFileToZip service = new WriteFileToZip();
        ZipOutputStream audio = new ZipOutputStream(new FileOutputStream("audio.zip"));
        ZipOutputStream video = new ZipOutputStream(new FileOutputStream("video.zip"));
        ZipOutputStream image = new ZipOutputStream(new FileOutputStream("image.zip"));
        service.doZip(dir, audio, video, image);
        audio.close();
        video.close();
        image.close();
    }
}
