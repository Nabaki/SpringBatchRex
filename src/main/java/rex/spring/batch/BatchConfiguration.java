package rex.spring.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importUserJob(Step step1) {
		DefaultJobParametersValidator jobParametersValidator = new DefaultJobParametersValidator(
				new String[]{},
				new String[]{"dryrun"});

		return jobBuilderFactory.get("importUserJob")
				.validator(jobParametersValidator)
				.flow(step1)
				.end()
				.build();
    }

    @Bean
    public Step step1(ItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(10)
                .reader(new BasicReader())
                .processor(new BasicProcessor())
                .writer(writer)
                .build();
    }
}
