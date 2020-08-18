package rex.spring.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * https://stackoverflow.com/questions/15653913/spring-batch-how-to-ensure-when-a-job-is-running-it-is-not-allowed-to-run-agai
 */
public class BasicJobParametersIncrementer extends RunIdIncrementer {

    private final JobRepository jobRepository;
    private final String jobName;

    private List<String> KEY_DISCRIMINATORS = Collections.emptyList();

    public BasicJobParametersIncrementer(JobRepository jobRepository, String jobName) {
        this.jobRepository = jobRepository;
        this.jobName = jobName;
    }

    @Override
    public JobParameters getNext(JobParameters parameters) {
        Map<String, JobParameter> allParameters = parameters.getParameters();

        //Isoler les parametres discriminants du job.
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        KEY_DISCRIMINATORS.forEach(key -> jobParametersBuilder.addParameter(key, allParameters.get(key)));

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParametersBuilder.toJobParameters());
        return lastJobExecution != null && lastJobExecution.getStatus() != BatchStatus.COMPLETED
                ? super.getNext(lastJobExecution.getJobParameters())
                : super.getNext(parameters);
    }
}