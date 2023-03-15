package org.loktevik.entry_searcher.ui;

import java.util.List;
import java.util.Scanner;

/**
 * Класс, представляющий собой консольную реализацию пользовательского интерфейса.
 */
public class ConsoleUI implements ApplicationUI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String requireInput() {
        System.out.println("Введите строку: ");
        return scanner.nextLine();
    }

    @Override
    public void displayLine(String line) {
        System.out.println(line);
    }

    @Override
    public void displayMultipleLines(List<String> lines) {
        lines.forEach(System.out::println);
    }

    @Override
    public void displayError(Exception cause) {
        System.out.println("Ошибка: " + cause.getMessage() + " Причина: " + cause.getCause());
    }
}
