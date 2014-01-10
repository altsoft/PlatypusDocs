/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eas.htmlconvertor;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author vv
 */
public class FileUtils {

    public static final char EXTENSION_SEPARATOR = '.';
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    public static String getFileExtension(String fileName) {
        int k = fileName.lastIndexOf(EXTENSION_SEPARATOR); // NOI18N
        String ext = null;
        if (k != -1) {
            ext = fileName.substring(k + 1, fileName.length());
        }
        return ext;
    }

    public static String getFileExtension(File file) {
        return getFileExtension(file.getName());
    }

    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfExtension(filename);
        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
        int lastSeparator = indexOfLastSeparator(filename);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static byte[] readBytes(File file) throws IOException {
        long len = file.length();
        if (len > Integer.MAX_VALUE) {
            throw new IOException("Too big file " + file.getPath()); // NOI18N
        }
        try (InputStream is = new FileInputStream(file)) {
            byte[] arr = new byte[(int) len];
            int pos = 0;
            while (pos < arr.length) {
                int read = is.read(arr, pos, arr.length - pos);
                if (read == -1) {
                    break;
                }
                pos += read;
            }
            if (pos != arr.length) {
                throw new IOException("Just " + pos + " bytes read from " + file.getPath()); // NOI18N
            }
            return arr;
        }
    }

    public static String readString(File file, String encoding) throws IOException {
        return new String(readBytes(file), encoding);
    }

    public static void writeBytes(File file, byte[] arr) throws IOException {
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(arr);
        }
    }

    public static void writeString(File file, String str, String encoding) throws IOException {
        try (Writer out = new OutputStreamWriter(new FileOutputStream(file), encoding)) {
            out.write(str);
        }
    }

    public static void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
            throw new IOException("Failed to delete file: " + f); // NOI18N
        }
    }

    public static void clearDirectory(File f) throws IOException {
        if (!f.isDirectory()) {
            throw new IllegalArgumentException("Only directory can be cleared."); // NOI18N
        }
        for (File c : f.listFiles()) {
            delete(c);
        }
    }

    public static void copyRecursivly(Path sourcePath, Path targetPath) throws IOException {
        Files.walkFileTree(sourcePath, new CopyFileVisitor(targetPath));
    }
    
    public static void copyRecursivly(File source, File target) throws IOException {
        copyRecursivly(source.toPath(), target.toPath());
    }
    
    private static class CopyFileVisitor extends SimpleFileVisitor<Path> {

        private final Path targetPath;
        private Path sourcePath = null;

        public CopyFileVisitor(Path targetPath) {
            this.targetPath = targetPath;
        }

        @Override
        public FileVisitResult preVisitDirectory(final Path dir,
                final BasicFileAttributes attrs) throws IOException {
            if (sourcePath == null) {
                sourcePath = dir;
            } else {
                Files.createDirectories(targetPath.resolve(sourcePath
                        .relativize(dir)));
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(final Path file,
                final BasicFileAttributes attrs) throws IOException {
            Files.copy(file,
                    targetPath.resolve(sourcePath.relativize(file)));
            return FileVisitResult.CONTINUE;
        }
    }
}
