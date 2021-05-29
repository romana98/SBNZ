package project.recommendationandtroubleshooting.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Service
public class RecommendationService {

    private final KieContainer kieContainer;

    @Autowired
    public RecommendationService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Recommendations getRecommendation() {
        KieSession kieSession = kieContainer.newKieSession();

        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.DESKTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.DESKTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        Budget b1 = new Budget(1000L, 100000L);
        ConfigurationUsageType usage1 = new ConfigurationUsageType(1L, "SoftwareDevelopment");
        ConfigurationUsageType usage2 = new ConfigurationUsageType(2L, "Gaming");
        //kieSession.insert(usage1);
        //kieSession.insert(usage2);

        ConfigurationCharacteristicType characteristic1 = new ConfigurationCharacteristicType(1L, "Presentations");
        ConfigurationCharacteristicType characteristic2 = new ConfigurationCharacteristicType(2L, "Touchscreen");

        Mobility m1 = new Mobility(80.0);
        Mobility m2 = new Mobility(10.0);
        ArrayList<ConfigurationCharacteristicType> characteristicsList1 = new ArrayList<ConfigurationCharacteristicType>();
        characteristicsList1.add(characteristic1);
        characteristicsList1.add(characteristic2);
        ArrayList<ConfigurationCharacteristicType> characteristicsList2 = new ArrayList<ConfigurationCharacteristicType>();
        characteristicsList2.add(characteristic1);
        characteristicsList2.add(characteristic2);

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

        ConfigurationCharacteristicTypeRequirements reqCharP = new ConfigurationCharacteristicTypeRequirements();
        reqCharP.setCharacteristic(characteristic1);
        ConfigurationCharacteristicTypeRequirements reqCharT = new ConfigurationCharacteristicTypeRequirements();
        reqCharT.setCharacteristic(characteristic2);

        kieSession.insert(reqCharP);
        kieSession.insert(reqCharT);

        InputRequirements input1 = new InputRequirements(b1, usage1, characteristicsList1, m1);
        InputRequirements input2 = new InputRequirements(b1, usage2, characteristicsList2, m2);

        kieSession.insert(input1);
        //kieSession.insert(input2);

        Recommendations output = new Recommendations();

        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        System.out.println(output.getConfigurations().size());
        for (Configuration cc : output.getConfigurations()) {
            System.out.println(cc.toString());
        }

        kieSession.dispose();
        return null;
    }
    /*ConfigurationUsageType usage3 = new ConfigurationUsageType(3L, "Editing");
        ConfigurationUsageType usage4 = new ConfigurationUsageType(4L, "Bookkeeping");
        ConfigurationUsageType usage5 = new ConfigurationUsageType(5L, "3DDesign");
        ConfigurationUsageType usage6 = new ConfigurationUsageType(6L, "AcademicPurposes");
        ConfigurationUsageType usage7 = new ConfigurationUsageType(7L, "PeronalUse");*/

    /*ConfigurationCharacteristicType characteristic3 = new ConfigurationCharacteristicType(3L, "LowBrightness");
        ConfigurationCharacteristicType characteristic4 = new ConfigurationCharacteristicType(4L, "OnlineMeeting");
        ConfigurationCharacteristicType characteristic5 = new ConfigurationCharacteristicType(5L, "LowPowerConsumption");
        ConfigurationCharacteristicType characteristic6 = new ConfigurationCharacteristicType(6L, "DamagdEyesight");*/
}
