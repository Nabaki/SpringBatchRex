package rex.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class BasicProcessor implements ItemProcessor<String, String> {

    private static final Logger log = LoggerFactory.getLogger(BasicProcessor.class);

    @Override
    public String process(final String person) throws Exception {
        log.info("Processing " + person);
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(parent);
        
        for (ConstraintViolation<Person> violation : violations) {
            System.err.println(violation.getMessage());
        }
        
        return person;
    }

}
