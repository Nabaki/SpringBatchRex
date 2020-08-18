package rex.spring.batch;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;

@Component
@StepScope
public class BasicReader implements ItemReader<String> {

    private final Iterator<String> iterator = Arrays.asList("un", "deux", "trois").iterator();

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (iterator.hasNext())
            return iterator.next();

        return null;
    }
}
