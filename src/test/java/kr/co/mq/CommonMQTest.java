package kr.co.mq;

import kr.co.mq.basic.BasicSubscriberJob;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public abstract class CommonMQTest {

    protected final String REPLY_TO = "reply-to";
    protected String[] subjects = { "test" };

    protected String[] msgs;

    protected List<Runnable> jobList = new ArrayList<>();

    protected final int JOB_COUNT = 1;

    protected ExecutorService service;

    protected void createSubject(String... subjects) {

        this.subjects = subjects;
    }

    protected void createMsg(int createMsgCount) {

        msgs = new String[createMsgCount];

        for ( int index = 0; index < createMsgCount; index++ ) {

            msgs[index] = String.format("Send Message - %d", index);
        }
    }
}
