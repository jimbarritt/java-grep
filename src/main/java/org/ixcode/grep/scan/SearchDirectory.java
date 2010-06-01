package org.ixcode.grep.scan;

import java.io.*;
import java.util.*;

public class SearchDirectory {

    private static final FileFilter DIRECTORY_FILTER = new DirectoryFilter();
    private final File delegate;

    public SearchDirectory(File delegate) {
        this.delegate = delegate;
    }
    
    public SearchDirectory(String directoryPath) {
        this(new File(directoryPath));        
    }

    public File asFile() {
        return delegate;
    }

    public String toString() {
        return delegate.getAbsolutePath();
    }

    public List<SearchFile> listFiles(FilenameFilter filter) {
        ArrayList<SearchFile> searchFiles = new ArrayList<SearchFile>();

        File[] files = delegate.listFiles(filter);
        for (File file : files) {
            searchFiles.add(new SearchFile(file));
        }

        return searchFiles;
    }

    public List<SearchDirectory> listDirectories() {
        ArrayList<SearchDirectory> directoryArrayList = new ArrayList<SearchDirectory>();
        File[] directories = delegate.listFiles(DIRECTORY_FILTER);
        if (directories != null) {
            for(File directory : directories) {
                directoryArrayList.add(new SearchDirectory(directory));
            }
        }
        return directoryArrayList;
    }

    private static class DirectoryFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }
}
