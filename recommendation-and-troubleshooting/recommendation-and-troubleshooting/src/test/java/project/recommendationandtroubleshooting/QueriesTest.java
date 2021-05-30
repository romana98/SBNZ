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
import project.recommendationandtroubleshooting.model.recommendation.Configuration;
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
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        c1.setRatings(new HashSet<Rating>());
        c1.getRatings().add(new Rating(1L, 3.0));
        c1.getRatings().add(new Rating(2L, 4.0));
        c1.getRatings().add(new Rating(3L, 5.0));

        kieSession.insert(c1);

        Double sum = 0.0;
        Long num = 0L;
        QueryResults results = kieSession.getQueryResults("Average rating", 1L);
        for (QueryResultsRow queryResult : results) {
            sum = (Double) queryResult.get("$sum");
            num = (Long) queryResult.get("$num");
        }

        assertTrue(sum / num == 4);

        kieSession.dispose();

    }


    @Test
    public void testNumberOfUsersByRateQuery() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        c1.setRatings(new HashSet<Rating>());
        c1.getRatings().add(new Rating(1L, 3.0));
        c1.getRatings().add(new Rating(2L, 4.0));
        c1.getRatings().add(new Rating(3L, 5.0));
        c1.getRatings().add(new Rating(4L, 5.0));
        c1.getRatings().add(new Rating(5L, 5.0));
        c1.getRatings().add(new Rating(6L, 2.0));
        c1.getRatings().add(new Rating(7L, 2.0));
        c1.getRatings().add(new Rating(8L, 4.0));

        kieSession.insert(c1);

        Long one = 0L;
        Long two = 0L;
        Long three = 0L;
        Long four = 0L;
        Long five = 0L;
        QueryResults results = kieSession.getQueryResults("Number of users by rate", 1L);
        for (QueryResultsRow queryResult : results) {
            one = (Long) queryResult.get("$one");
            two = (Long) queryResult.get("$two");
            three = (Long) queryResult.get("$three");
            four = (Long) queryResult.get("$four");
            five = (Long) queryResult.get("$five");
        }

        assertEquals(one, 0);
        assertEquals(two, 2);
        assertEquals(three, 1);
        assertEquals(four, 2);
        assertEquals(five, 3);

        kieSession.dispose();

    }

}
