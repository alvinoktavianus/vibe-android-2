package id.co.vibe.vibe.provider;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

    Scheduler computationThread();

}
