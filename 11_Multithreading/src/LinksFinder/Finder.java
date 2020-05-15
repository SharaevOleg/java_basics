package LinksFinder;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Finder {
    private ExecutorService executorService;
    private Map<String, Set<String>> links;
    private boolean isPaused;

    private AtomicInteger count;

    private Object lock = new Object();

    public Finder() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        links = new HashMap<>();
        count = new AtomicInteger();
    }


    public void addTask(String url) {
        while (isPaused) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        //if (!links.containsKey(url)) {
            executorService.submit(new Task(url, this));
        //}
    }

    public Object getLock() {
        return lock;
    }

    public Map<String, Set<String>> getLinks() {
        return links;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public AtomicInteger getCount() {
        return count;
    }
}
