package org.loktevik.entry_searcher;

import org.loktevik.entry_searcher.exception.SearchServiceException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 Класс, осуществляющий поиск данных
 и возвращающий результат в виде полученных данных, ошибок и т.д.
 **/
public class SearchService {
    private final String DATA_PATH;
    private final int SEARCH_COLUMN_NUMBER;
    private final String DATA_DELIMITER = ",";

    private Map<String, Integer> columnValues = new HashMap<>();
    private Set<String[]> result;

    public SearchService(String dataPath, int searchColumnNumber) throws SearchServiceException {
        DATA_PATH = dataPath;
        SEARCH_COLUMN_NUMBER = searchColumnNumber;

        try (BufferedReader bf = new BufferedReader(new FileReader(DATA_PATH))){
            int cnt = 0;
            while (bf.ready()){
                String val = bf.readLine().split(DATA_DELIMITER)[SEARCH_COLUMN_NUMBER];
                columnValues.put(val, cnt);
                cnt++;
            }
        }
        catch (FileNotFoundException e){
            throw new SearchServiceException("Не удалось открыть файл с данными.", e);
        }
        catch (IOException e){
            throw new SearchServiceException("Ошибка чтения файла", e);
        }
    }

    private List<Integer> getLineIndexes(String input){
        return columnValues.keySet().stream()
                .filter(k -> k.startsWith(setPrefix(input)))
                .map(k -> columnValues.get(k))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> search(String input) throws SearchServiceException {
        result = new TreeSet<>(Comparator.comparing(e -> e[SEARCH_COLUMN_NUMBER]));
        List<Integer> indexes = getLineIndexes(input);

        try (BufferedReader bf = new BufferedReader(new FileReader(DATA_PATH))){
            int cnt = 0;
            for (int index : indexes){
                while (cnt != index){
                    bf.readLine();
                    cnt++;
                }
                result.add(bf.readLine().split(DATA_DELIMITER));
                cnt++;
            }
        }
        catch (FileNotFoundException e){
            throw new SearchServiceException("Не удалось открыть файл с данными.", e);
        }
        catch (IOException e){
            throw new SearchServiceException("Ошибка чтения файла", e);
        }

        return convertResultToStringList(result);
    }

    private String setPrefix(String input){
        if ((1 <= SEARCH_COLUMN_NUMBER && SEARCH_COLUMN_NUMBER <= 5) ||
                (10 <= SEARCH_COLUMN_NUMBER && SEARCH_COLUMN_NUMBER <= 13)){
            input = "\"" + input;
        }
        return input;
    }

    private List<String> convertResultToStringList(Set<String[]> result){
        return result.stream().map(e -> e[SEARCH_COLUMN_NUMBER] + Arrays.toString(e))
                .collect(Collectors.toList());
    }
}
