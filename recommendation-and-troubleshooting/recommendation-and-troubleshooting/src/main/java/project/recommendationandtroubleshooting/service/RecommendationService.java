package project.recommendationandtroubleshooting.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.TestModel;
import project.recommendationandtroubleshooting.model.recommendation.Budget;
import project.recommendationandtroubleshooting.model.recommendation.Configuration;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.InputRecommendation;
import project.recommendationandtroubleshooting.model.recommendation.Mobility;
import project.recommendationandtroubleshooting.model.recommendation.Output;

@Service
public class RecommendationService {

    private final KieContainer kieContainer;

    @Autowired
    public RecommendationService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Output getRecommendation() {
        KieSession kieSession = kieContainer.newKieSession();
        
        
        Configuration c1 = new Configuration(1L, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);

        Configuration c2 = new Configuration(2L, 99999L, ConfigurationType.DESKTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);

        Configuration c3 = new Configuration(3L, 89999L, ConfigurationType.DESKTOP, "GIGATRON PRIME R16008G480S1650", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);
        
        Budget b1 = new Budget(1000L, 100000L);
        ConfigurationUsageType usage1 = new ConfigurationUsageType(1L, "SoftwareDevelopment");
        ConfigurationUsageType usage2 = new ConfigurationUsageType(2L, "Gaming");
        //kieSession.insert(usage1);
        //kieSession.insert(usage2);
        /*ConfigurationUsageType usage3 = new ConfigurationUsageType(3L, "Editing");
        ConfigurationUsageType usage4 = new ConfigurationUsageType(4L, "Bookkeeping");
        ConfigurationUsageType usage5 = new ConfigurationUsageType(5L, "3DDesign");
        ConfigurationUsageType usage6 = new ConfigurationUsageType(6L, "AcademicPurposes");
        ConfigurationUsageType usage7 = new ConfigurationUsageType(7L, "PeronalUse");*/
        ConfigurationCharacteristicType characteristic1 = new ConfigurationCharacteristicType(1L, "Presentations");
        ConfigurationCharacteristicType characteristic2 = new ConfigurationCharacteristicType(2L, "Touchscreen");
        /*ConfigurationCharacteristicType characteristic3 = new ConfigurationCharacteristicType(3L, "LowBrightness");
        ConfigurationCharacteristicType characteristic4 = new ConfigurationCharacteristicType(4L, "OnlineMeeting");
        ConfigurationCharacteristicType characteristic5 = new ConfigurationCharacteristicType(5L, "LowPowerConsumption");
        ConfigurationCharacteristicType characteristic6 = new ConfigurationCharacteristicType(6L, "DamagdEyesight");*/
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

        InputRecommendation input1 = new InputRecommendation(b1, usage1, characteristicsList1, m1);
        InputRecommendation input2 = new InputRecommendation(b1, usage2, characteristicsList2, m2);
        
        kieSession.insert(input1);
        //kieSession.insert(input2);
        
        Output output = new Output();

        kieSession.insert(output);
        
        kieSession.fireAllRules();
        
        /*System.out.println(output.getBudget());
        System.out.println(output.getType());
        System.out.println(output.getUsage());
        System.out.println(output.getCharacteristics());*/
        System.out.println(output.getRecommendedConfigurations().size());
        for (Configuration cc : output.getRecommendedConfigurations()) {
        	System.out.println(cc.toString());
        }
        
        kieSession.dispose();
        return null;
    }
}
