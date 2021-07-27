package com.denisov26.solution.cashmachine.command;


import com.denisov26.solution.cashmachine.CashMachine;
import com.denisov26.solution.cashmachine.ConsoleHelper;
import com.denisov26.solution.cashmachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String command = ConsoleHelper.readString();
        if (command != null && "y".equals(command.toLowerCase())) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        } else {

        }
    }
}
