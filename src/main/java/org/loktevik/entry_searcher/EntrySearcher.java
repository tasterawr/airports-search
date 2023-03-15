package org.loktevik.entry_searcher;

import org.loktevik.entry_searcher.exception.SearchServiceException;
import org.loktevik.entry_searcher.ui.ApplicationUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
    Класс, осуществляющий вызов механизма поиска данных
    и отображающий результат поиска в виде полученных данных или ошибок.
 **/
public class EntrySearcher {
    private final SearchService searchService;
    private final ApplicationUI applicationUI;

    public EntrySearcher(String dataPath, int searchColumnNumber, ApplicationUI applicationUI){
        try{
            this.searchService = new SearchService(dataPath, searchColumnNumber);
        }
        catch (SearchServiceException e){
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        this.applicationUI = applicationUI;
    }

    public void start() {
        try{
            String input = applicationUI.requireInput();
            while (!input.equals("!quit")){
                search(input);
                input = applicationUI.requireInput();
            }
        } catch (SearchServiceException e) {
            applicationUI.displayError(e);
        }
    }

    private void search(String input) throws SearchServiceException {
        long startTime = System.currentTimeMillis();
        List<String> searchResult = searchService.search(input);
        long endTime = System.currentTimeMillis();

        printSearchResult(searchResult, endTime - startTime);
    }

    private void printSearchResult(List<String> searchResult, long elapsedTime){
        applicationUI.displayMultipleLines(searchResult);
        applicationUI.displayLine(String.format("Количество найденных строк: %d, Время: %d мс.", searchResult.size(), elapsedTime));
    }

}
