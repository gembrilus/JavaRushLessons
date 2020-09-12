package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command {

    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String sum = ConsoleHelper.readString();
            if (!sum.matches("\\d+")){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            int summa = Integer.parseInt(sum);
            if (!manipulator.isAmountAvailable(summa)){
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }

            try {
                TreeMap<Integer, Integer> amount = new TreeMap<>(Comparator.reverseOrder());
                amount.putAll(manipulator.withdrawAmount(summa));
                amount.forEach((k, v) ->
                        ConsoleHelper.writeMessage("\t" + k + " - " + v)
                );
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), summa, code));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            break;
        }
    }
}
