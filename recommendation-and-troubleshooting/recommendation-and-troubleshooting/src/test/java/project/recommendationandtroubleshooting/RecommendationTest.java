package project.recommendationandtroubleshooting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecommendationTest {

    @Test
    public void testBudgetRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");
        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.LAPTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        Budget b1 = new Budget(1000L, 80000L);
        ConfigurationUsageType usage1 = new ConfigurationUsageType(1L, "SoftwareDevelopment");
        ConfigurationUsageType usage2 = new ConfigurationUsageType(2L, "Gaming");

        ConfigurationCharacteristicType characteristic1 = new ConfigurationCharacteristicType(1L, "Presentations");
        ConfigurationCharacteristicType characteristic2 = new ConfigurationCharacteristicType(2L, "Touchscreen");

        Mobility m1 = new Mobility(80.0);
        ArrayList<ConfigurationCharacteristicType> characteristicsList1 = new ArrayList<ConfigurationCharacteristicType>();
        characteristicsList1.add(characteristic1);
        characteristicsList1.add(characteristic2);

        InputRequirements input1 = new InputRequirements(b1, usage1, characteristicsList1, m1);

        kieSession.insert(input1);

        Recommendations output = new Recommendations();

        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        assertTrue(c1.getConsidered());
        assertFalse(c2.getConsidered());
        assertFalse(c3.getConsidered());

        kieSession.dispose();

    }


    @Test
    public void testCharacteristicRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");
        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.LAPTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        Budget b1 = new Budget(1000L, 100000L);
        ConfigurationUsageType usage1 = new ConfigurationUsageType(1L, "SoftwareDevelopment");
        ConfigurationUsageType usage2 = new ConfigurationUsageType(2L, "Gaming");

        ConfigurationCharacteristicType characteristic1 = new ConfigurationCharacteristicType(1L, "Presentations");
        ConfigurationCharacteristicType characteristic2 = new ConfigurationCharacteristicType(2L, "Touchscreen");

        Mobility m1 = new Mobility(80.0);
        ArrayList<ConfigurationCharacteristicType> characteristicsList1 = new ArrayList<ConfigurationCharacteristicType>();
        characteristicsList1.add(characteristic1);
        characteristicsList1.add(characteristic2);

        ConfigurationCharacteristicTypeRequirements reqCharP = new ConfigurationCharacteristicTypeRequirements();
        reqCharP.setCharacteristic(characteristic1);
        ConfigurationCharacteristicTypeRequirements reqCharT = new ConfigurationCharacteristicTypeRequirements();
        reqCharT.setCharacteristic(characteristic2);

        kieSession.insert(reqCharP);
        kieSession.insert(reqCharT);

        InputRequirements input1 = new InputRequirements(b1, usage1, characteristicsList1, m1);

        kieSession.insert(input1);

        Recommendations output = new Recommendations();

        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        assertTrue(c1.getConsidered());
        assertTrue(c2.getConsidered());
        assertTrue(c3.getConsidered());

        kieSession.dispose();

    }

    @Test
    public void testUsageRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");
        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.LAPTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        Budget b1 = new Budget(1000L, 100000L);
        ConfigurationUsageType usage1 = new ConfigurationUsageType(1L, "SoftwareDevelopment");
        ConfigurationUsageType usage2 = new ConfigurationUsageType(2L, "Gaming");

        ConfigurationCharacteristicType characteristic1 = new ConfigurationCharacteristicType(1L, "Presentations");
        ConfigurationCharacteristicType characteristic2 = new ConfigurationCharacteristicType(2L, "Touchscreen");

        Mobility m1 = new Mobility(80.0);
        ArrayList<ConfigurationCharacteristicType> characteristicsList1 = new ArrayList<ConfigurationCharacteristicType>();
        characteristicsList1.add(characteristic1);
        characteristicsList1.add(characteristic2);

        Set<String> sdCPU = new HashSet<String>();
        sdCPU.add("Intel Core i3 Processor");
        Set<String> gGPU = new HashSet<String>();
        gGPU.add("ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G");
        ConfigurationUsageTypeRequirements reqSD = new ConfigurationUsageTypeRequirements();
        reqSD.setUsage(usage1);
        reqSD.setCPU(sdCPU);
        ConfigurationUsageTypeRequirements reqG = new ConfigurationUsageTypeRequirements();
        reqG.setUsage(usage2);
        reqG.setGPU(gGPU);

        kieSession.insert(reqSD);
        kieSession.insert(reqG);

        InputRequirements input1 = new InputRequirements(b1, usage1, characteristicsList1, m1);

        kieSession.insert(input1);

        Recommendations output = new Recommendations();

        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        assertTrue(c1.getConsidered());
        assertTrue(c2.getConsidered());
        assertFalse(c3.getConsidered());

        kieSession.dispose();

    }

    @Test
    public void testMobilityRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");
        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.DESKTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.DESKTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        Budget b1 = new Budget(1000L, 80000L);
        ConfigurationUsageType usage1 = new ConfigurationUsageType(1L, "SoftwareDevelopment");
        ConfigurationUsageType usage2 = new ConfigurationUsageType(2L, "Gaming");

        ConfigurationCharacteristicType characteristic1 = new ConfigurationCharacteristicType(1L, "Presentations");
        ConfigurationCharacteristicType characteristic2 = new ConfigurationCharacteristicType(2L, "Touchscreen");

        Mobility m1 = new Mobility(80.0);
        ArrayList<ConfigurationCharacteristicType> characteristicsList1 = new ArrayList<ConfigurationCharacteristicType>();
        characteristicsList1.add(characteristic1);
        characteristicsList1.add(characteristic2);

        InputRequirements input1 = new InputRequirements(b1, usage1, characteristicsList1, m1);

        kieSession.insert(input1);

        Recommendations output = new Recommendations();

        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        assertTrue(c1.getConsidered());
        assertFalse(c2.getConsidered());
        assertFalse(c3.getConsidered());

        kieSession.dispose();

    }


    @Test
    public void testRecommendationRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("recommendationSession");
        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, false);
        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.LAPTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, false);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        Recommendations output = new Recommendations();

        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        assertTrue(c1.getConsidered());
        assertFalse(c2.getConsidered());
        assertFalse(c3.getConsidered());
        assertFalse(output.getConfigurations().isEmpty());
        assertEquals(output.getConfigurations().size(), 1);

        kieSession.dispose();

    }

}
