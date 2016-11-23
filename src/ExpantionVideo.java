import java.io.File;
import java.io.FileFilter;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class ExpantionVideo {
    static class VideoFileFilter implements FileFilter {
        private static String[] videoExpansion = new String[]{"avi", "mp4", "flv"};

        @Override
        public boolean accept(File file) {
            for (String expansion : videoExpansion) {
                if (file.getName().toLowerCase().endsWith(expansion)) {
                    return true;
                }

            }
            return false;
        }
    }
}
