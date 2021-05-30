package project.recommendationandtroubleshooting;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.recommendationandtroubleshooting.dto.IntervalDTO;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.recommendation.Configuration;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemplateTest {

    @Test
    public void testIntervalReportTemplateRule() {

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        try {
            InputStream template = new FileInputStream(
                    "..\\recommendation-and-troubleshooting-drools\\src\\main\\resources\\project\\recommendationandtroubleshooting\\recommendation\\templates\\interval-report.drt");

            List<IntervalDTO> arguments = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm");
            arguments.add(new IntervalDTO("2021-05-28", "2021-05-31"));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            FileOutputStream drlFile = new FileOutputStream(new File(
                    "..\\recommendation-and-troubleshooting-drools\\src\\main\\resources\\project\\recommendationandtroubleshooting\\recommendation\\interval-report.drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();

            InvocationRequest request = new DefaultInvocationRequest();
            //request.setInputStream(InputStream.nullInputStream());
            request.setPomFile(new File("../recommendation-and-troibleshooting/pom.xml"));
            request.setGoals(Arrays.asList("clean", "install"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("M2_HOME")));
            invoker.execute(request);

            // Fire new rule

            Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
            Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.DESKTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
            Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.DESKTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

            kieSession.insert(c1);
            kieSession.insert(c2);
            kieSession.insert(c3);

            User u1 = new User();
            u1.getFavorites().add(new Favorite(c1, 6L, new Date()));
            u1.getFavorites().add(new Favorite(c2, 1L, new Date()));

            User u2 = new User();
            u2.getFavorites().add(new Favorite(c1, 6L, new Date()));
            u2.getFavorites().add(new Favorite(c2, 1L, new Date()));

            User u3 = new User();
            u3.getFavorites().add(new Favorite(c1, 6L, new Date()));
            u3.getFavorites().add(new Favorite(c3, 1L, new Date()));

            User u4 = new User();
            u4.getFavorites().add(new Favorite(c1, 1L, new Date()));
            u4.getFavorites().add(new Favorite(c2, 1L, new Date()));

            User u5 = new User();
            u5.getFavorites().add(new Favorite(c1, 1L, new Date()));
            u5.getFavorites().add(new Favorite(c2, 1L, new Date()));

            User u6 = new User();
            u6.getFavorites().add(new Favorite(c1, 6L, new Date()));
            u6.getFavorites().add(new Favorite(c2, 1L, new Date()));


            Configurations output = new Configurations();

            kieSession.insert(output);
            kieSession.insert(u1);
            kieSession.insert(u2);
            kieSession.insert(u3);
            kieSession.insert(u4);
            kieSession.insert(u5);
            kieSession.insert(u6);

            kieSession.getAgenda().getAgendaGroup("interval_popular").setFocus();
            kieSession.fireAllRules();

            assertEquals(1, output.getConfigurations().size());

            kieSession.dispose();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
