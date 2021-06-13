package project.recommendationandtroubleshooting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Rating;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueriesTest {

    @Test
    public void testAverageRatingQuery() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        ConfigurationClass c1 = new ConfigurationClass(1, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        c1.setRatings(new HashSet<Rating>());
        c1.getRatings().add(new Rating(1, 3.0));
        c1.getRatings().add(new Rating(2, 4.0));
        c1.getRatings().add(new Rating(3, 5.0));

        kieSession.insert(c1);

        Double average = 0.0;
        QueryResults results = kieSession.getQueryResults("Average rating", 1L);
        for (QueryResultsRow queryResult: results) {
        	average = (Double) queryResult.get("$average");
        }
        
        assertTrue(average == 4);
        
        kieSession.dispose();

    }


    @Test
    public void testNumberOfUsersByRateQuery() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        ConfigurationClass c1 = new ConfigurationClass(1, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        c1.setRatings(new HashSet<Rating>());
        c1.getRatings().add(new Rating(1, 3.0));
        c1.getRatings().add(new Rating(2, 4.0));
        c1.getRatings().add(new Rating(3, 5.0));
        c1.getRatings().add(new Rating(4, 5.0));
        c1.getRatings().add(new Rating(5, 5.0));
        c1.getRatings().add(new Rating(6, 2.0));
        c1.getRatings().add(new Rating(7, 2.0));
        c1.getRatings().add(new Rating(8, 4.0));

        kieSession.insert(c1);

        Long result = 0L;
        /*Long one = 0L;
        Long two = 0L;
        Long three = 0L;
        Long four = 0L;
        Long five = 0L;*/
        QueryResults results = kieSession.getQueryResults("Number of users by rate", 1L, 2L);
        for (QueryResultsRow queryResult : results) {
        	result = (Long) queryResult.get("$result");
            /*one = (Long) queryResult.get("$one");
            two = (Long) queryResult.get("$two");
            three = (Long) queryResult.get("$three");
            four = (Long) queryResult.get("$four");
            five = (Long) queryResult.get("$five");*/
        }

        assertEquals(result, 2);
        /*assertEquals(one, 0);
        assertEquals(two, 2);
        assertEquals(three, 1);
        assertEquals(four, 2);
        assertEquals(five, 3);*/

        kieSession.dispose();

    }

}
