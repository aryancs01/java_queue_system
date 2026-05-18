import java.util.ArrayList;

public class QueueManager {
    private ArrayList<Job> jobs = new ArrayList<>();

    public synchronized void addJob(Job job){
        jobs.add(job);
        System.out.println("Job added: " + job.getId());
    }

    public synchronized Job getNextJob() {
        if(jobs.isEmpty()){
            return null;
        }

        Job job = jobs.remove(0);
        System.out.println("Job taken: " + job.getId());

        return job;
    }

    public synchronized int getSize() {
        return jobs.size();
    }

    public synchronized boolean isEmpty() {
        return jobs.isEmpty();
    }
}
