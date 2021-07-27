package com.denisov26.solution.cashmachine.command;

import com.denisov26.solution.cashmachine.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
