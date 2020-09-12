package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private final ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String input1 = ConsoleHelper.readString();
            String input2 = ConsoleHelper.readString();
            if (!input1.matches("\\d{12}") || !input2.matches("\\d{4}")) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            long number = Long.parseLong(input1);
            int pin = Integer.parseInt(input2);

            if (validCreditCards.containsKey(input1) && validCreditCards.getString(input1).equals(input2)) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), input1));
                break;
            }else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), input1));
            }
        }
    }
}
