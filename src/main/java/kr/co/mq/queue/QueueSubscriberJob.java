package kr.co.mq.queue;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import kr.co.mq.util.ConnectionUtil;
import kr.co.mq.util.Log;

import java.nio.charset.StandardCharsets;

public class QueueSubscriberJob implements Runnable {

    private Connection connection;

    private String jobName;

    private String queueGroupName;

    private String[] subjects;

    public QueueSubscriberJob(String jobName, String[] subjects, String queueGroupName) {

        this.jobName = jobName;
        this.subjects = subjects;
        this.queueGroupName = queueGroupName;
        this.connection = ConnectionUtil.getConnection();
    }

    private void subscribe() {

        Dispatcher dispatcher = connection.createDispatcher(msg -> {
            String response = new String(msg.getData(), StandardCharsets.UTF_8);
            String subject = msg.getSubject();
            String replyTo = msg.getReplyTo();

            Log.print(this.jobName, " Sub: ", subject, replyTo, " 의 응답 정보 ", response);
        });

        for ( String subject : subjects ) {

            dispatcher.subscribe(subject, queueGroupName);
        }

        Log.print(jobName, " Sub: ", "응답 준비 완료");
    }

    @Override
    public void run() {

        subscribe();
    }
}
