package kr.co.mq.queue;

import io.nats.client.Connection;
import kr.co.mq.util.ConnectionUtil;
import kr.co.mq.util.Log;

import java.nio.charset.StandardCharsets;

public class QueuePublisher {

    private Connection connection;

    private String[] msgs;

    private String[] subjects;

    public QueuePublisher(String[] msgs, String[] subjects) {

        this.msgs = msgs;
        this.subjects = subjects;

        this.connection = ConnectionUtil.getConnection();
    }

    public void start() {

        publish();
    }

    private void publish() {

        Log.print("Pub: ", "전송 시작");

        for ( String msg : msgs ) {

            Log.print("Pub: ", msg, "전송");

            for ( String subject : subjects ) {

                this.connection.publish(subject, msg.getBytes(StandardCharsets.UTF_8));
            }
        }

        Log.print("Pub: ", "전송 완료");
    }
}
