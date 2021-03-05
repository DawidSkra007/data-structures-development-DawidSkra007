import org.junit.Test;

import java.util.concurrent.ExecutorService;

public class JobSchedulerTest {

    @Test
    public void whenMultiplePriorityJobsQueued_thenHighestPriorityJobIsPicked() {
        Job job1 = new Job("Job1", JobPriority.LOW,2);
        Job job2 = new Job("Job2", JobPriority.MEDIUM,5);
        Job job3 = new Job("Job3", JobPriority.HIGH,6);
        Job job4 = new Job("Job4", JobPriority.MEDIUM,1);
        Job job5 = new Job("Job5", JobPriority.LOW,2);
        Job job6 = new Job("Job6", JobPriority.HIGH,9);
        Job job7 = new Job("Job7", JobPriority.LOWEST,4);
        Job job8 = new Job("Job8", JobPriority.VHIGH,3);

        int POOL_SIZE = 1;
        int QUEUE_SIZE = 10;
        JobScheduler pjs = new JobScheduler(POOL_SIZE, QUEUE_SIZE);

        pjs.scheduleJob(job1);
        pjs.scheduleJob(job2);
        pjs.scheduleJob(job3);
        pjs.scheduleJob(job4);
        pjs.scheduleJob(job5);
        pjs.scheduleJob(job6);
        pjs.scheduleJob(job7);
        pjs.scheduleJob(job8);


    }
}
