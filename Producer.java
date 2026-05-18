public class Producer {
    private QueueManager queueManager;

    public Producer(QueueManager queueManager) {
        this.queueManager = queueManager;
    }

    public void produceJob(Job job) {

        queueManager.addJob(job);

        System.out.println("Producer created job: " + job.getId());
    }
}
