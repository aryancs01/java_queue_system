public class Job {
    private int id;
    private String payload;
    private JobStatus status;
    private int retryCount;
    private int maxRetries;

    public Job(int id, String payload) {
        this.id = id;
        this.payload = payload;
        this.status = JobStatus.PENDING;
        this.retryCount = 0;
        this.maxRetries = AppConfig.MAX_RETRIES;
    }

    int getId(){
        return this.id;
    }

    void setStatus(JobStatus status){
        this.status = status;
    }

    int getRetryCount(){
        return this.retryCount;
    }

    int getMaxRetries() {
        return this.maxRetries;
    }

    void incrementRetry() {
        this.retryCount++;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id=" + this.id +
                ", payload='" + this.payload + '\'' +
                ", status=" + this.status +
                ", retryCount=" + this.retryCount +
                '}';
    }
}
