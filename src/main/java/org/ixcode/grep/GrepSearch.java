package org.ixcode.grep;

import org.ixcode.grep.scan.*;

import java.io.*;
import java.util.*;

public class GrepSearch implements FileScanningAction {
    public GrepSearch(String searchExpression, SearchOption caseInsensitive) {

    }

    @Override
    public void scanFile(File file) {

    }

    public List<MatchedFile> matchedFiles() {
        return null;
    }

    public FileScanningAction fileScanningAction() {
        return null;
    }

    public static enum SearchOption {
        CASE_INSENSITIVE
    }
}
