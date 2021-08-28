package com.denisov26.solution.cashmachine.command;

import com.denisov26.solution.cashmachine.CashMachine;
import com.denisov26.solution.cashmachine.ConsoleHelper;
import com.denisov26.solution.cashmachine.exception.InterruptOperationException;


import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards"
            /*"resources.verifiedCards"*/);
    private ResourceBundle res = ResourceBundle.getBundle(/*CashMachine.RESOURCE_PATH + */"login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String creditCardNumber = ConsoleHelper.readString();
            String pinStr = ConsoleHelper.readString();
            if (creditCardNumber == null || (creditCardNumber = creditCardNumber.trim()).length() != 12 ||
                    pinStr == null || (pinStr = pinStr.trim()).length() != 4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            } else {
                try {
                    if (validCreditCards.containsKey(creditCardNumber) && pinStr.equals(validCreditCards.getString(creditCardNumber))) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), creditCardNumber));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), creditCardNumber));
                        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    }
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                }
            }
        }
//        String enteredCardNumber;
//        String enteredPinCode;
//        do {
//            ConsoleHelper.writeMessage(res.getString("specify.data"));
//            enteredCardNumber = ConsoleHelper.readString();
//            enteredPinCode = ConsoleHelper.readString();
//
//            if (!Pattern.matches("\\d{12}", enteredCardNumber)
//                    || !Pattern.matches("\\d{4}", enteredPinCode)) {
//                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
//            }
//            if ((validCreditCards.containsKey(enteredCardNumber) && !enteredPinCode.equals(validCreditCards.getString(enteredCardNumber)))
//                    || !validCreditCards.containsKey(enteredCardNumber)) {
//                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), enteredCardNumber));
//            }
//            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
//        } while (!validCreditCards.containsKey(enteredCardNumber)
//                && !enteredPinCode.equals(validCreditCards.getString(enteredCardNumber)));
//
//        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), enteredCardNumber));
    }
}
