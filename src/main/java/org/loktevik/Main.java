package org.loktevik;

import org.loktevik.entry_searcher.EntrySearcher;
import org.loktevik.entry_searcher.SearchService;
import org.loktevik.entry_searcher.ui.ConsoleUI;
import org.loktevik.entry_searcher.ui.ApplicationUI;

public class Main {

    public static void main(String[] args) {
        ApplicationUI applicationUI = new ConsoleUI();
        int searchColumnNumber = 0;
        try{
            searchColumnNumber = Integer.parseInt(args[0]) - 1;
        }
        catch (ArrayIndexOutOfBoundsException e){
            IllegalArgumentException ex = new IllegalArgumentException("Не передано значение столбца в качестве параметра.", e);
            applicationUI.displayError(ex);
            System.exit(0);
        }
        catch (NumberFormatException e){
            IllegalArgumentException ex = new IllegalArgumentException("Передан нечисловой параметр.", e);
            applicationUI.displayError(ex);
            System.exit(0);
        }

        EntrySearcher entrySearcher = new EntrySearcher("..\\src\\main\\resources\\airports.csv", searchColumnNumber, applicationUI);
        entrySearcher.start();
    }

}
