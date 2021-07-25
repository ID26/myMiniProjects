package com.denisov26.solution.cashmachine.command;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
