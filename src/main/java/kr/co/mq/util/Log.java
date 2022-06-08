package kr.co.mq.util;

public class Log {

    public static void print(String... msgs) {

        StringBuffer buffer = new StringBuffer();

        for ( String msg : msgs ) {

            buffer.append(msg);
            buffer.append(" ");
        }

        System.out.println(buffer);
    }
}
