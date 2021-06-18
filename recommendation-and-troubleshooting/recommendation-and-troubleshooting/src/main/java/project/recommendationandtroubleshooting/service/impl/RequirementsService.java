package project.recommendationandtroubleshooting.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.dto.RequirementResponseDTO;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;
import project.recommendationandtroubleshooting.repository.ConfigurationCharacteristicTypeRequirementsRepository;
import project.recommendationandtroubleshooting.repository.ConfigurationUsageTypeRequirementsRepository;

@Service
public class RequirementsService {
	
	@Autowired
	KieSession kieSession;
	
	@Autowired
	ConfigurationUsageTypeRequirementsRepository usageRepo;

	@Autowired
	ConfigurationCharacteristicTypeRequirementsRepository charRepo;
	
	public Page<RequirementResponseDTO> getAll(Pageable pageable) {
		
		List<RequirementResponseDTO> result = new ArrayList<RequirementResponseDTO>();
		
		List<ConfigurationUsageTypeRequirements> usages = usageRepo.findAll();
		List<ConfigurationCharacteristicTypeRequirements> chars = charRepo.findAll();
		
		for (ConfigurationUsageTypeRequirements u : usages) {
			for (Map.Entry<String, Set<String>> attr : u.getRequirements().entrySet()) {
				for (String value : attr.getValue()) {
					RequirementResponseDTO dto = new RequirementResponseDTO(u.getUsage().getId(), u.getUsage().getUsage(), attr.getKey(), value, "USAGE");
					result.add(dto);
				}
			}
		}
		for (ConfigurationCharacteristicTypeRequirements u : chars) {
			for (Map.Entry<String, Set<String>> attr : u.getRequirements().entrySet()) {
				for (String value : attr.getValue()) {
					RequirementResponseDTO dto = new RequirementResponseDTO(u.getCharacteristic().getId(), u.getCharacteristic().getCharacteristic(), attr.getKey(), value, "CHARACTERISTIC");
					result.add(dto);
				}
			}
		}
		int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), result.size());
        Page<RequirementResponseDTO> page = new PageImpl<>(result.subList(start, end), pageable, result.size());
        return page;
	}
	
	public boolean changeUsageRequirement(RequirementResponseDTO dto, boolean toDelete) {
		ConfigurationUsageTypeRequirements req = usageRepo.getByUsage(dto.getUsageOrCharId());
        switch(dto.getAttribute())
        {
            case "CPU":
                if (toDelete)
                	req.getCPU().remove(dto.getValue());
            	else
            		req.getCPU().add(dto.getValue()); 
                break;
            case "GPU":
            	if (toDelete)
                	req.getGPU().remove(dto.getValue());
            	else
            		req.getGPU().add(dto.getValue()); 
            	break;
            case "RAM":
            	if (toDelete)
                	req.getRAM().remove(dto.getValue());
            	else
            		req.getRAM().add(dto.getValue()); 
            	break;
            case "OS":
            	if (toDelete)
                	req.getOS().remove(dto.getValue());
            	else
            		req.getOS().add(dto.getValue()); 
            	break;
            case "PSU":
            	if (toDelete)
                	req.getPSU().remove(dto.getValue());
            	else
            		req.getPSU().add(dto.getValue()); 
            	break;
            case "discType":
            	if (toDelete)
                	req.getDiscType().remove(dto.getValue());
            	else
            		req.getDiscType().add(dto.getValue()); 
            	break;
            case "discSize":
            	if (toDelete)
                	req.getDiscSize().remove(dto.getValue());
            	else
            		req.getDiscSize().add(dto.getValue()); 
            	break;
            case "motherboard":
            	if (toDelete)
                	req.getMotherboard().remove(dto.getValue());
            	else
            		req.getMotherboard().add(dto.getValue()); 
            	break;
            case "screenResolution":
            	if (toDelete)
                	req.getScreenResolution().remove(dto.getValue());
            	else
            		req.getScreenResolution().add(dto.getValue()); 
            	break;
            case "screenSize":
            	if (toDelete)
                	req.getScreenSize().remove(dto.getValue());
            	else
            		req.getScreenSize().add(dto.getValue()); 
            	break;
            case "musicCard":
            	if (toDelete)
                	req.getMusicCard().remove(dto.getValue());
            	else
            		req.getMusicCard().add(dto.getValue()); 
            	break;
            default:
                System.out.println("no match");
        }
        usageRepo.save(req);
        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle: handlers) {
        	Object sessionObject = kieSession.getObject(handle);
        	if (sessionObject instanceof ConfigurationUsageTypeRequirements) {
        		kieSession.delete(handle);
        	}
        }
        kieSession.insert(req);
        return true;	
	}
	
	public boolean changeCharacteristicRequirement(RequirementResponseDTO dto, boolean toDelete) {
		ConfigurationCharacteristicTypeRequirements req = charRepo.getByCharacterstic(dto.getUsageOrCharId());
		 switch(dto.getAttribute())
        {
        case "CPU":
            if (toDelete)
            	req.getCPU().remove(dto.getValue());
        	else
        		req.getCPU().add(dto.getValue()); 
            break;
        case "GPU":
        	if (toDelete)
            	req.getGPU().remove(dto.getValue());
        	else
        		req.getGPU().add(dto.getValue()); 
        	break;
        case "RAM":
        	if (toDelete)
            	req.getRAM().remove(dto.getValue());
        	else
        		req.getRAM().add(dto.getValue()); 
        	break;
        case "OS":
        	if (toDelete)
            	req.getOS().remove(dto.getValue());
        	else
        		req.getOS().add(dto.getValue()); 
        	break;
        case "PSU":
        	if (toDelete)
            	req.getPSU().remove(dto.getValue());
        	else
        		req.getPSU().add(dto.getValue()); 
        	break;
        case "discType":
        	if (toDelete)
            	req.getDiscType().remove(dto.getValue());
        	else
        		req.getDiscType().add(dto.getValue()); 
        	break;
        case "discSize":
        	if (toDelete)
            	req.getDiscSize().remove(dto.getValue());
        	else
        		req.getDiscSize().add(dto.getValue()); 
        	break;
        case "motherboard":
        	if (toDelete)
            	req.getMotherboard().remove(dto.getValue());
        	else
        		req.getMotherboard().add(dto.getValue()); 
        	break;
        case "screenResolution":
        	if (toDelete)
            	req.getScreenResolution().remove(dto.getValue());
        	else
        		req.getScreenResolution().add(dto.getValue()); 
        	break;
        case "screenSize":
        	if (toDelete)
            	req.getScreenSize().remove(dto.getValue());
        	else
        		req.getScreenSize().add(dto.getValue()); 
        	break;
        case "musicCard":
        	if (toDelete)
            	req.getMusicCard().remove(dto.getValue());
        	else
        		req.getMusicCard().add(dto.getValue()); 
        	break;
        default:
                System.out.println("no match");
        }
        charRepo.save(req);
        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle: handlers) {
        	Object sessionObject = kieSession.getObject(handle);
        	if (sessionObject instanceof ConfigurationCharacteristicTypeRequirements) {
        		kieSession.delete(handle);
        	}
        }
        kieSession.insert(req);
        return true;	
		
	}
}
