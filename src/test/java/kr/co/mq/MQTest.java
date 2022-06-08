package kr.co.mq;

import kr.co.mq.basic.BasicPublisher;
import kr.co.mq.basic.BasicSubscriberJob;
import kr.co.mq.queue.QueuePublisher;
import kr.co.mq.queue.QueueSubscriberJob;
import kr.co.mq.rr.RRPublisher;
import kr.co.mq.rr.RRSubscriberJob;
import kr.co.mq.util.ConnectionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

public class MQTest extends CommonMQTest {

    @BeforeEach
    public void beforeEach() {

        createSubject("test");
        createMsg(3);
    }

    @AfterEach
    public void afterEach() {

        ConnectionUtil.allDisconnection();
    }

    @DisplayName("기본 Pub/Sub 테스트")
    @Test
    public void testBasicPubSub() throws InterruptedException {

        try {

            for ( int index = 0; index < JOB_COUNT; index++ ) {

                jobList.add(new BasicSubscriberJob("Job-" + index, subjects));
            }

            service = Executors.newFixedThreadPool(JOB_COUNT);

            for ( int index = 0; index < JOB_COUNT; index++ ) {

                service.submit(jobList.get(index));
            }

            Thread.sleep(3 * 1000);

            BasicPublisher publisher = new BasicPublisher(msgs, subjects);
            publisher.start();

            Thread.sleep(5 * 1000);

            System.out.println("종료");
        } finally {

            service.shutdownNow();
        }
    }

    @DisplayName("Request Reply 테스트")
    @Test
    public void testRequestReply() throws InterruptedException {

        try {

            for ( int index = 0; index < JOB_COUNT; index++ ) {

                jobList.add(new RRSubscriberJob("Job-" + index, subjects));
            }

            service = Executors.newFixedThreadPool(JOB_COUNT);

            for ( int index = 0; index < JOB_COUNT; index++ ) {

                service.submit(jobList.get(index));
            }

            Thread.sleep(3 * 1000);

            RRPublisher publisher = new RRPublisher(msgs, subjects);
            publisher.start();

            Thread.sleep(10 * 1000);

            System.out.println("종료");
        } finally {

            service.shutdownNow();
        }
    }

    @DisplayName("Queue Group Pub/Sub 테스트")
    @Test
    public void testQueueGroupPubSub() throws InterruptedException {

        try {

            for ( int index = 0; index < JOB_COUNT; index++ ) {

                jobList.add(new QueueSubscriberJob("Job-" + index, subjects, queueGroupName));
            }

            service = Executors.newFixedThreadPool(JOB_COUNT);

            for ( int index = 0; index < JOB_COUNT; index++ ) {

                service.submit(jobList.get(index));
            }

            Thread.sleep(3 * 1000);

            QueuePublisher publisher = new QueuePublisher(msgs, subjects);
            publisher.start();

            Thread.sleep(5 * 1000);

            System.out.println("종료");
        } finally {

            service.shutdownNow();
        }
    }
}
