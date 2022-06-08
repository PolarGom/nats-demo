package kr.co.mq.util;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.io.IOException;

public class ConnectionUtil {

    private static final String[] urls = { "nats://0.0.0.0:4222" };

    public static Connection getConnection() {

        try {

            return Nats.connect(String.join(",", urls));
        } catch ( IOException e ) {

            e.printStackTrace();
        } catch ( InterruptedException e ) {

            e.printStackTrace();
        }

        return null;
    }
}
