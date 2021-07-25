package com.denisov26.solution.cashmachine;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String text = bis.readLine();
            if (text.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
            return text;
        } catch (IOException ignored) {

        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Введите 3-х значный код валюты.");
            String currencyCode = ConsoleHelper.readString();
            if (currencyCode == null || currencyCode.trim().length() != 3) {
                ConsoleHelper.writeMessage("Код валюты не соответствует 3-м знакам.");
                continue;
            }
            return currencyCode.trim().toUpperCase();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(String.format("Please specify integer denomination and integer count. For example '10 3' means 30 %s", currencyCode));
            String line = ConsoleHelper.readString();
            String[] split = null;
            if (line == null || (split = line.split(" ")).length != 2) {
                ConsoleHelper.writeMessage("Введенные данные некоректны.");
            } else {
                try {
                    if (Integer.parseInt(split[0]) <= 0 || Integer.parseInt(split[1]) <= 0) {
                        ConsoleHelper.writeMessage("Введенные данные некоректны.");
                    } 
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage("Введенные данные некоректны.");
                    continue;
                }
                return split;
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Выберите тип операции.");
            ConsoleHelper.writeMessage("\t 1 - operation.INFO");
            ConsoleHelper.writeMessage("\t 2 - operation.DEPOSIT");
            ConsoleHelper.writeMessage("\t 3 - operation.WITHDRAW");
            ConsoleHelper.writeMessage("\t 4 - operation.EXIT");
            int operation = Integer.parseInt(ConsoleHelper.readString().trim());
            try {
                return Operation.getAllowableOperationByOrdinal(operation);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Введенные данные некоректны.");
                continue;
            }
        }
    }

    
}
