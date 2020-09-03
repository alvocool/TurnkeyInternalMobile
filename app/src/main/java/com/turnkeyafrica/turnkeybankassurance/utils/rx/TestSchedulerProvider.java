package com.turnkeyafrica.turnkeybankassurance.utils.rx;


import io.reactivex.schedulers.TestScheduler;
import io.reactivex.Scheduler;

public class TestSchedulerProvider implements SchedulerProvider {

    private TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler mTestScheduler) {
        this.mTestScheduler = mTestScheduler;
    }

    public Scheduler computation() {
        return mTestScheduler;
    }

     public Scheduler io() {
        return mTestScheduler;
    }

    public Scheduler ui() {
        return mTestScheduler;
    }
}
