package com.denisov26.solution.cashmachine.command;


import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {

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

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Withdrawing...");

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            try {
                ConsoleHelper.writeMessage("Please specify integer amount for withdrawing.");
                String s = ConsoleHelper.readString();
                if (s == null) {
                    ConsoleHelper.writeMessage("Please specify valid positive integer amount for withdrawing.");
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
                            ConsoleHelper.writeMessage("Not enough money on your account, please try again");
                        }
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeMessage("Please specify valid positive integer amount for withdrawing.");
                    }
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Exact amount is not available");
            }
        }
    }
}
