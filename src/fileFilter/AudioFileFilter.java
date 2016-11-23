package fileFilter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class AudioFileFilter implements FileFilter {
    private static String[] audioExpansion = new String[]{"mp3", "wav", "wma"};

    @Override
    public boolean accept(File file) {
        for (String expansion : audioExpansion) {
            if (file.getName().toLowerCase().endsWith(expansion)) {
                return true;
            }
        }
        return false;
    }
}
