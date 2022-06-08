package kr.co.mq.rr;

import io.nats.client.Connection;
import io.nats.client.Message;
import kr.co.mq.util.ConnectionUtil;
import kr.co.mq.util.Log;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class RRPublisher {

    private Connection connection;

    private String[] msgs;

    private String[] subjects;

    private String replyTo;

    public RRPublisher(String[] msgs, String[] subjects, String replyTo) {

        this.msgs = msgs;
        this.subjects = subjects;
        this.replyTo = replyTo;

        this.connection = ConnectionUtil.getConnection();
    }

    public void start() {

        try {

            publish();
        } catch ( InterruptedException e ) {

            e.printStackTrace();
        }
    }

    private void publish() throws InterruptedException {

        Log.print("Pub: ", "전송 시작");

        for ( String msg : msgs ) {

            Log.print("Pub: ", msg, "전송");

            for ( String subject : subjects ) {

                this.connection.request(subject, msg.getBytes(StandardCharsets.UTF_8), Duration.ofSeconds(3)).;
            }
        }

        Log.print("Pub: ", "전송 완료");
    }
}
