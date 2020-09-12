package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
        } catch (IOException ignore) {
        }
        if ("exit".equalsIgnoreCase(s)){
            throw new InterruptOperationException();
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currencyCode = readString();
        if (currencyCode == null || currencyCode.length() != 3) {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
        return currencyCode.toUpperCase();
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage("1 - " + res.getString("operation.INFO"));
        writeMessage("2 - " + res.getString("operation.DEPOSIT"));
        writeMessage("3 - " + res.getString("operation.WITHDRAW"));
        writeMessage("4 - " + res.getString("operation.EXIT"));
        try {
            int op = Integer.parseInt(readString());
            return Operation.getAllowableOperationByOrdinal(op);
        } catch (IllegalArgumentException iae){
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String s = readString();
        if (s != null && s.matches("\\d* \\d*")) {
            return s.split(" ");
        } else {
            writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currencyCode);
        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
