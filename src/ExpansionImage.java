import java.io.File;
import java.io.FileFilter;

/**
 * Created by Дмитрий on 23.11.2016.
 */
public class ExpansionImage {
    static class ImageFileFilter implements FileFilter {
        private static String[] imageExpansion = new String[]{"jpeg", "jpg", "gif" , "png"};

        @Override
        public boolean accept(File file) {
            for (String expansion : imageExpansion) {
                if (file.getName().toLowerCase().endsWith(expansion)) {
                    return true;
                }

            }
            return false;
        }
    }
}
