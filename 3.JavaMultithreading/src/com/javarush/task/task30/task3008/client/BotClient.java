package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        return "date_bot_" + (int)(100 * Math.random());
    }

    public class BotSocketThread extends Client.SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            String text = "Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.";

            sendTextMessage(text);
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if(!message.contains(":")) return;
            String[] arr = message.split(": ");
            switch (arr[1]){
                case "дата": makeMessage(arr[0], "d.MM.YYYY"); break;
                case "день": makeMessage(arr[0], "d"); break;
                case "месяц": makeMessage(arr[0], "MMMM"); break;
                case "год": makeMessage(arr[0], "YYYY"); break;
                case "время": makeMessage(arr[0], "H:mm:ss"); break;
                case "час": makeMessage(arr[0], "H"); break;
                case "минуты": makeMessage(arr[0], "m"); break;
                case "секунды": makeMessage(arr[0], "s"); break;

            }
            super.processIncomingMessage(message);
        }

        private void makeMessage(String name, String format) {
            String response = "Информация для " + name + ": " + new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
            sendTextMessage(response);
        }

    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
