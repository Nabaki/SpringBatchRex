package rex.spring.batch.step1;

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

    @Override
    public void write(List<? extends Person> list) throws Exception {
        System.out.println("dryrun = " + dryrun);
        if (!dryrun) {
            System.out.println("WRITER " + list);
        }
    }
}
