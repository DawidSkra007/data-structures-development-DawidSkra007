import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class JobScheduler {
    private final ExecutorService priorityJobPoolExecutor;
    private final ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
    private final PriorityBlockingQueue<Job> priorityQueue;

    public JobScheduler(Integer poolSize, Integer queueSize) {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<Job>(queueSize, Comparator.comparing(Job::getDuration).thenComparing(Job::getJobPriority));
        priorityJobScheduler.execute(() -> {
            while (true) {
                try {
                    priorityJobPoolExecutor.execute(priorityQueue.take());
                } catch (InterruptedException e) {
                    // exception needs special handling
                    break;
                }
            }
        });
    }

    public void scheduleJob(Job job) {
        priorityQueue.add(job);
    }
}

enum JobPriority { VHIGH ,HIGH, MEDIUM, LOW, LOWEST}

class Job implements Runnable {
    private String jobName;
    private JobPriority jobPriority;
    private int duration;

    public Job(String jobName, JobPriority jobPriority,int duration) {
        this.jobName = jobName;
        this.jobPriority = jobPriority;
        this.duration = duration;
    }

    @Override
    public void run() {
        System.out.println("Job:" + jobName + " Priority:" + jobPriority + " Duration:" + duration);
        try {
            Thread.sleep(1000); // to simulate actual execution time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // standard setters and getters

    public JobPriority getJobPriority() {
        return jobPriority;
    }
    public void setJobPriority(JobPriority jobPriority) {
        this.jobPriority = jobPriority;
    }

    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
