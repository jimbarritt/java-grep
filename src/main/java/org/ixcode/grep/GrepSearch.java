package org.ixcode.grep;

import org.ixcode.grep.scan.*;

import java.io.*;
import java.util.*;

public class GrepSearch implements FileScanningAction {
    public GrepSearch(String searchExpression) {

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

}
