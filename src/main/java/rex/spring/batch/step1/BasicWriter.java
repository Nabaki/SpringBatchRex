package rex.spring.batch.step1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rex.spring.batch.Person;

import java.util.List;

@Component
@StepScope
public class BasicWriter implements ItemWriter<Person> {

    @Value("#{jobParameters['dryrun']?:true}")
    private boolean dryrun;

    private static final Logger log = LoggerFactory.getLogger(BasicWriter.class);

    @Override
    public void write(List<? extends Person> list) throws Exception {
        log.info("Dryrun mode is " + dryrun);
        if (!dryrun) {
            list.forEach(i -> log.info(i.toString()));
        }
    }
}
