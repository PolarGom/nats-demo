package kr.co.mq.rr;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import kr.co.mq.util.ConnectionUtil;
import kr.co.mq.util.Log;

import java.nio.charset.StandardCharsets;

public class RRSubscriberJob implements Runnable {

    private Connection connection;

    private String jobName;
    private String[] subjects;

    public RRSubscriberJob(String jobName, String... subjects) {

        this.jobName = jobName;
        this.subjects = subjects;
        this.connection = ConnectionUtil.getConnection();
    }

    private void subscribe() {

        Dispatcher dispatcher = connection.createDispatcher(msg -> {
            String response = new String(msg.getData(), StandardCharsets.UTF_8);
            String subject = msg.getSubject();
            String replyTo = msg.getReplyTo();

            Log.print(jobName, " Sub: ", subject, replyTo, " 의 응답 정보 ", response);

            this.connection.publish(replyTo, ("Received" + response).getBytes(StandardCharsets.UTF_8));
        });

        for ( String subject : subjects ) {

            dispatcher.subscribe(subject);
        }

        Log.print(jobName, " Sub: ", "응답 준비 완료");
    }

    @Override
    public void run() {

        subscribe();
    }
}
