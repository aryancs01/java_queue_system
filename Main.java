public class Main {

    public static void main(String[] args) {

        QueueManager queueManager = new QueueManager();
        Producer producer = new Producer(queueManager);

        Job job1 = new Job(1, "Send Email");
        Job job2 = new Job(2, "Generate Report");
        Job job3 = new Job(3, "Process Payment");

        producer.produceJob(job1);
        producer.produceJob(job2);
        producer.produceJob(job3);

        System.out.println("Queue Size: " + queueManager.getSize());

        Worker worker1 = new Worker("Worker-1", queueManager);
        Thread thread1 = new Thread(worker1);
        thread1.start();
    }
}