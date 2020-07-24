package com.javarush.task.task38.task3804;

public class Factory {

    public static Throwable getInstance(Enum e){
        if (e == null) return new IllegalArgumentException();
        String message = e
                .name()
                .replaceAll("_", " ")
                .toLowerCase();
        String msg = message.substring(0,1).toUpperCase()+message.substring(1);

        if (e.getClass().isAssignableFrom(ApplicationExceptionMessage.class)){
            return new Exception(msg);
        } else if(e.getClass().isAssignableFrom(DatabaseExceptionMessage.class)){
            return new RuntimeException(msg);
        } else if(e.getClass().isAssignableFrom(UserExceptionMessage.class)){
            return new Error(msg);
        } else {
            return new IllegalArgumentException();
        }
    }
}
