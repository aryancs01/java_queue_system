import java.util.Random;

public class Worker implements Runnable {

    private String workerName;
    private QueueManager queueManager;
    private Random random = new Random();

    public Worker(String workerName, QueueManager queueManager) {
        this.workerName = workerName;
        this.queueManager = queueManager;
    }

    @Override
    public void run() {

        while (true) {

            Job job = queueManager.getNextJob();

            if (job != null) {

                System.out.println(workerName + " processing job: " + job.getId());
                job.setStatus(JobStatus.PROCESSING);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // random failure simulation
                boolean isFailed = random.nextBoolean();

                if (isFailed) {

                    job.setStatus(JobStatus.FAILED);

                    job.incrementRetry();

                    System.out.println(
                            workerName +
                                    " failed job: " +
                                    job.getId() +
                                    " Retry: " +
                                    job.getRetryCount()
                    );

                    // retry logic
                    if (job.getRetryCount() <= job.getMaxRetries()) {

                        System.out.println("Retrying job: " + job.getId());

                        queueManager.addJob(job);

                    } else {
                        job.setStatus(JobStatus.DEAD);
                        System.out.println("Job moved to DEAD state: " + job.getId());
                    }
                } else {
                    job.setStatus(JobStatus.SUCCESS);
                    System.out.println(workerName + " completed job: " + job.getId());
                }
            } else {
                System.out.println(workerName + " waiting for jobs...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}