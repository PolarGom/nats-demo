package kr.co.mq.util;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import io.nats.client.Nats;
import io.nats.client.Options;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtil {

    private static final String[] urls = { "nats://mytoken@0.0.0.0:4222" };

    private static List<Connection> connectionList = new ArrayList<>();

    public static Connection getConnection() {

        try {

            Options.Builder options = new Options.Builder();

            for ( String url : urls ) {

                options.server(url);
            }

            options.connectionTimeout(Duration.ofSeconds(10));
            options.maxReconnects(10);
            options.reconnectWait(Duration.ofSeconds(10));
            options.noRandomize();
            options.connectionListener((conn, type) -> {
                if ( type == ConnectionListener.Events.CONNECTED ) {
                  
                    Log.print("커넥션 성공");
                } else if ( type == ConnectionListener.Events.DISCONNECTED ) {

                    Log.print("커넥션 끊김");
                } else if ( type == ConnectionListener.Events.RECONNECTED ) {

                    Log.print("재 커넥션");
                } 
            });

            Connection connection = Nats.connect(options.build());
            connectionList.add(connection);

            return connection;
        } catch ( IOException e ) {

            e.printStackTrace();
        } catch ( InterruptedException e ) {

            e.printStackTrace();
        }

        return null;
    }

    public static void allDisconnection() {

        connectionList.forEach(connection -> {
            try {

                connection.close();
            } catch ( InterruptedException e ) {

                e.printStackTrace();
            }
        });
    }
}
