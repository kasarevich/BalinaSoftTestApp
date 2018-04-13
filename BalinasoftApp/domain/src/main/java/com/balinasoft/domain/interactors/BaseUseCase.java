package com.balinasoft.domain.interactors;

import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.executor.ThreadExecutor;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase {
    protected Scheduler postExecutionThread;
    protected Scheduler threadExecution;

    public BaseUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        threadExecution = Schedulers.io();
    }
    public BaseUseCase(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        this.threadExecution = Schedulers.from(threadExecutor);
    }
}