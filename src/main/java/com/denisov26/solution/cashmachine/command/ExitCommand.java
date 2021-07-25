package com.denisov26.solution.cashmachine.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Are you wont exit? Press \"y\" or \"n\"");
        String command = ConsoleHelper.readString();
        if (command != null && command.trim().equalsIgnoreCase("y")) {
            ConsoleHelper.writeMessage("See you later.");
        }
    }
}
