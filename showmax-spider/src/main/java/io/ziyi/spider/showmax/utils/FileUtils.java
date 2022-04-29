package io.ziyi.spider.showmax.utils;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.util.CommonLogger;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class FileUtils {

    private static final CommonLogger logger = CommonLogger.getLogger(FileUtils.class);

    public static class FileInfo {
        private final String name;
        private final long length;
        private final String md5;

        private FileInfo(String name, long length, String md5) {
            this.name = name;
            this.length = length;
            this.md5 = md5;
        }

        public String getName() {
            return name;
        }

        public long getLength() {
            return length;
        }

        public String getMd5() {
            return md5;
        }
    }

    public static String getLocalDataPath() {
        return ConfigTools3.getString("spider.showmax.path.local", true);
    }

    public static boolean hasEnoughAvailableSpace() {
        if ( System.currentTimeMillis() > 0L ) {
            return true;
        }
        File dir = new File(getLocalDataPath());
        long threshold = ConfigTools3.getLong("spider.showmax.freespace.threshold-in-mb", 4096L) * 1048576L;
        return dir.getUsableSpace() > threshold;
    }

    public static File createLocalFile(String localPath) {
        File dir = new File(getLocalDataPath());
        File file = new File(dir, localPath);
        File parent = file.getParentFile();
        if ( !parent.exists() && !parent.mkdirs() ) {
            logger.error("Create file", "Cannot create folder: {}", parent.getAbsolutePath());
        }
        return file;
    }

    public static FileInfo readFileInfo(File file) {
        if ( file == null || !file.exists() || !file.isFile() ) {
            return null;
        }

        String md5 = null;
        try ( FileInputStream fin = new FileInputStream(file) ) {
            md5 = DigestUtils.md5Hex(fin);
        } catch ( Exception ex ) {
            logger.error(ex, "Read file", "Failed to calculate file hash: path={}", file.getAbsolutePath());
        }

        return new FileInfo(file.getName(), file.length(), md5);
    }

    public static void deleteFile(File file) {
        if ( file == null || !file.exists() || !file.isFile() ) {
            return;
        }

        try {
            boolean success = file.delete();
            logger.info("Delete file", "Delete local file: path={}, success={}", file.getAbsolutePath(), success);
        } catch ( Exception ex ) {
            logger.error(ex, "Delete file", "Failed to delete file: {}", file.getAbsolutePath());
        }
    }

    public static void moveFile(File source, File target) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(target, "target");

        if ( !source.exists() && target.exists() ) {
            logger.warn("Move file", "File may already moved. source={}, target={}", source, target);
            return;
        }
        try {
            org.apache.commons.io.FileUtils.moveFile(source, target);
        } catch ( Exception ex ) {
            logger.error(ex, "Move file", "Failed to move file: source={}, target={}", source, target);
        }
    }

    public static void deleteFile(File file, boolean withParent) {
        if ( file == null || !file.exists() || !file.isFile() ) {
            return;
        }

        File parent = file.getParentFile();
        try {
            boolean success = file.delete();
            if ( success && withParent ) {
                success = parent.delete();
            }
            logger.info("Delete file", "Delete local file: path={}, success={}", file.getAbsolutePath(), success);
        } catch ( Exception ex ) {
            logger.error(ex, "Delete file", "Failed to delete file: {}", file.getAbsolutePath());
        }
    }
}