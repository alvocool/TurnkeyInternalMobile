package com.turnkeyafrica.bankassurance.utils;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/*
 *This will let a reactive function retry with a delay..i.e.
 * it will retry 3 times and on each retry it will wait 3 seconds (param is ms)
 *
 * Usage in a ViewModel:
 *      .retryWhen(new RetryWithDelay(3000));
 * */

public class RetryWithDelay implements
        Function<Flowable<Throwable>, Publisher<?>> {

    private final int maxRetries = 3;
    private final int retryDelaySecond;
    private int retryCount;

    public RetryWithDelay(int retryDelaySecond) {
        this.retryDelaySecond = retryDelaySecond;
    }

    @Override
    public Publisher<?> apply(@NonNull Flowable<Throwable> throwableFlowable) {
        return throwableFlowable
                .flatMap((Function<Throwable, Publisher<?>>) throwable -> {
                    if (++retryCount <= maxRetries) {
                        return Flowable.timer(retryDelaySecond,
                                TimeUnit.SECONDS);
                    }
                    return Flowable.error(throwable);
                });
    }
}