package org.loktevik.entry_searcher.ui;

import java.util.List;
import java.util.Set;

public interface ApplicationUI {
    String requireInput();

    void displayLine(String line);

    void displayMultipleLines(List<String> lines);

    void displayError(Exception cause);
}
