package com.denisov26.solution.cashmachine.command;


import com.denisov26.solution.cashmachine.CashMachine;
import com.denisov26.solution.cashmachine.ConsoleHelper;
import com.denisov26.solution.cashmachine.CurrencyManipulator;
import com.denisov26.solution.cashmachine.CurrencyManipulatorFactory;
import com.denisov26.solution.cashmachine.exception.InterruptOperationException;
import com.denisov26.solution.cashmachine.exception.NotEnoughMoneyException;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(/*CashMachine.RESOURCE_PATH + */"withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                String s = ConsoleHelper.readString();
                if (s == null) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                } else {
                    try {
                        int amount = Integer.parseInt(s);
                        boolean isAmountAvailable = manipulator.isAmountAvailable(amount);
                        if (isAmountAvailable) {
                            Map<Integer, Integer> denominations = manipulator.withdrawAmount(amount);
                            for (Integer item : denominations.keySet()) {
                                ConsoleHelper.writeMessage("\t" + item + " - " + denominations.get(item));
                            }

                            ConsoleHelper.writeMessage(String.format("%d %s was withdrawn successfully", amount, currencyCode));
                            break;
                        } else {
                            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                        }
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeMessage("Please specify valid positive integer amount for withdrawing.");
                    }
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }

//    @Override
//    public void execute() throws InterruptOperationException {
//        ConsoleHelper.writeMessage("Withdrawing...");
//
//        String currencyCode = ConsoleHelper.askCurrencyCode();
//        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
//        while (true) {
//            try {
//                ConsoleHelper.writeMessage("Введите сумму.");
//                int amount = Integer.parseInt(ConsoleHelper.readString());
//                if (amount == 0 || !currencyManipulator.isAmountAvailable(amount)) {
//                    ConsoleHelper.writeMessage("Не коректно введено значение.");
//                    continue;
//                } else {
//                    Map<Integer, Integer> orderQuantityMap = currencyManipulator.withdrawAmount(amount);
//                    for (Integer order : orderQuantityMap.keySet()) {
//                        ConsoleHelper.writeMessage(String.format("  %d-%d", order, orderQuantityMap.get(order)));
//                    }
//                }
//
//            } catch (NumberFormatException ignored) {
//                ConsoleHelper.writeMessage("Введены некоректные данные.");
//                continue;
//            } catch (NotEnoughMoneyException ignored) {
//                ConsoleHelper.writeMessage("Недостаточно денег в банкомате.");
//            }
//        }
//    }


}
