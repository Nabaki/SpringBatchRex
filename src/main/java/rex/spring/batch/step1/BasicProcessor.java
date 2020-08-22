package rex.spring.batch.step1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import rex.spring.batch.model.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
@StepScope
public class BasicProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(BasicProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        log.info("Processing " + person);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(person);

        for (ConstraintViolation<Person> violation : violations) {
            System.err.println(violation.getMessage());
        }

        return person;
    }

}
