package kr.co.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public abstract class CommonMQTest {

    protected String queueGroupName = "queue";

    protected String[] subjects = { "test" };

    protected String[] msgs;

    protected List<Runnable> jobList = new ArrayList<>();

    protected final int JOB_COUNT = 3;

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
