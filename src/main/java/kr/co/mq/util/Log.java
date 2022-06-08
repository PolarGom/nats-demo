package kr.co.mq.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void print(String... msgs) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[").append(LocalDateTime.now().format(DATE_FORMATTER)).append("] ");

        for ( String msg : msgs ) {

            buffer.append(msg);
            buffer.append(" ");
        }

        System.out.println(buffer);
    }
}
