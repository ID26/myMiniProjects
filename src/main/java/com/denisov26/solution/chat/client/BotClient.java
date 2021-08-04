package com.denisov26.solution.chat.client;

import com.denisov26.solution.chat.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random()*100));
    }

    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();

    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] income = message.split(": ");
            if (income.length != 2) return;
            Calendar calendar = new GregorianCalendar(Locale.ENGLISH);
            String dateFormat = null;
            switch (income[1]) {
                case "дата":
                 dateFormat = "d.MM.YYYY";
                break;
                case  "день":
                dateFormat = "d";
                break;
                case "месяц":
                dateFormat = "MMMM";
                break;
                case "год":
                dateFormat = "YYYY";
                break;
                case "время":
                dateFormat = "H:mm:ss";
                break;
                case "час":
                dateFormat = "H";
                break;
                case "минуты":
                dateFormat = "m";
                break;
                case "секунды":
                dateFormat = "s";
                break;
            }
            if (dateFormat != null) {
                BotClient.this.sendTextMessage("Информация для " + income[0] + ": " + new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime()));
            }
        }

        @Override
        protected void informAboutAddingNewUser(String userName) {
            super.informAboutAddingNewUser(userName);
        }

        @Override
        protected void informAboutDeletingNewUser(String userName) {
            super.informAboutDeletingNewUser(userName);
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            super.notifyConnectionStatusChanged(clientConnected);
        }

        @Override
        public void run() {
            super.run();
        }

        @Override
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            super.clientHandshake();
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
}
