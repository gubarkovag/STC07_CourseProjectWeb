package com.stc07.gubarkovag.db.pooling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class ObjectPool<T> {
    private ConcurrentLinkedQueue<T> pool;

    private ScheduledExecutorService executorService;

    public ObjectPool(int minIdle) {
        initialize(minIdle);
    }

    public ObjectPool(int minIdle, int maxIdle, long validationInterval) {
        initialize(minIdle);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            int size = pool.size();
            if (size < minIdle) {
                int sizeToBeAdded = minIdle - size;
                for (int i = 0; i < sizeToBeAdded; i++) {
                    pool.add(createObject());
                }
            } else if (size > maxIdle) {
                int sizeToBeRemoved = size - maxIdle;
                for (int i = 0; i < sizeToBeRemoved; i++) {
                    pool.poll();
                }
            }
        }, validationInterval, validationInterval, TimeUnit.SECONDS);
    }

    public T borrowObject() {
        T object;
        if ((object = pool.poll()) == null) {
            object = createObject();
        }

        return object;
    }

    public void returnObject(T object) {
        if (object == null)
            return;

        this.pool.offer(object);
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    protected abstract T createObject();

    private  void initialize(int minIdle) {
        pool = new ConcurrentLinkedQueue<T>();

        for (int i = 0; i < minIdle; i++) {
            pool.add(createObject());
        }
    }
}
