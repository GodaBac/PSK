package mif.vu.lt.psktp1.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class FirstNameGenerator implements Serializable {

    public String generateFirstName() {
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        Random random = new Random();
        String generatedFirstName = random.ints(97, 123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedFirstName;
    }
}
