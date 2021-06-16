package project.recommendationandtroubleshooting.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.dto.IntervalDTO;
import project.recommendationandtroubleshooting.dto.RateDTO;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;
import project.recommendationandtroubleshooting.model.recommendation.InputRequirements;
import project.recommendationandtroubleshooting.model.recommendation.Rating;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;
import project.recommendationandtroubleshooting.service.FavoriteService;
import project.recommendationandtroubleshooting.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	
	@Autowired
	KieSession kieSession;
	
	@Autowired
	FavoriteServiceImpl favoriteService;

	@Override
	public Page<ConfigurationResponseDTO> getRecommendation(InputRequirements input, Pageable pageable, int idUser) {
		System.out.println(input);
		kieSession.insert(input);
		
		Recommendations output = new Recommendations();
        kieSession.insert(output);

        kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
        kieSession.fireAllRules();

        //kieSession.dispose();

        /*Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle: handlers) {
            kieSession.delete(handle);
        }*/
        
        List<ConfigurationClass> configs = new ArrayList<ConfigurationClass>(output.getConfigurations());
        List<ConfigurationResponseDTO> result = this.toDTOList(configs, idUser);
        return new PageImpl<>(result, pageable, (long)result.size());
	}

	@Override
	public Page<ConfigurationResponseDTO> getCurrentlyPopular(Pageable pageable, int idUser) {
		Configurations output = new Configurations();

		kieSession.getAgenda().getAgendaGroup("currently_popular").setFocus();
        kieSession.fireAllRules();
        
        kieSession.insert(output);
        
        List<ConfigurationClass> configs = output.getConfigurations();
        List<ConfigurationResponseDTO> result = this.toDTOList(configs, idUser);
        return new PageImpl<>(result, pageable, (long)result.size());
	}

	@Override
	public Page<ConfigurationResponseDTO> getIntervalPopular(IntervalDTO dto, Pageable pageable, int idUser) {

		try {
            InputStream template = new FileInputStream(
                    "..\\recommendation-and-troubleshooting-drools\\src\\main\\resources\\project\\recommendationandtroubleshooting\\templates\\interval-report.drt");

            List<IntervalDTO> arguments = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm");
            arguments.add(new IntervalDTO("2021-06-05", "2021-06-07"));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            FileOutputStream drlFile = new FileOutputStream(new File(
                    "..\\recommendation-and-troubleshooting-drools\\src\\main\\resources\\project\\recommendationandtroubleshooting\\interval-report.drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();

            InvocationRequest request = new DefaultInvocationRequest();
            //request.setInputStream(InputStream.nullInputStream());
            request.setPomFile(new File("../recommendation-and-troubleshooting/pom.xml"));
            request.setGoals(Arrays.asList("clean", "install"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("M2_HOME")));
            invoker.execute(request);

            
            Configurations output = new Configurations();

            kieSession.insert(output);


            kieSession.getAgenda().getAgendaGroup("interval_popular").setFocus();
            kieSession.fireAllRules();
            
            List<ConfigurationClass> configs = output.getConfigurations();
            List<ConfigurationResponseDTO> result = this.toDTOList(configs, idUser);
            return new PageImpl<>(result, pageable, (long)result.size());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public Page<ConfigurationResponseDTO> searchByRate(RateDTO dto, Pageable pageable, int idUser) {
		try {
            InputStream template = new FileInputStream(
                    "..\\recommendation-and-troubleshooting-drools\\src\\main\\resources\\project\\recommendationandtroubleshooting\\templates\\search-by-rate.drt");

            List<RateDTO> arguments = new ArrayList<RateDTO>();
            arguments.add(new RateDTO(2.0, 5.0));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            System.out.println("++++++++++++++++++++++++++++++++");
            String drl = compiler.compile(arguments, template);
            
            FileOutputStream drlFile = new FileOutputStream(new File(
                    "..\\recommendation-and-troubleshooting-drools\\src\\main\\resources\\project\\recommendationandtroubleshooting\\search-by-rate.drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();

            InvocationRequest request = new DefaultInvocationRequest();
            //request.setInputStream(InputStream.nullInputStream());
            request.setPomFile(new File("../recommendation-and-troubleshooting/pom.xml"));
            request.setGoals(Arrays.asList("clean", "install"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("M2_HOME")));
            invoker.execute(request);

            Configurations output = new Configurations();
            output.setConfigurations(new ArrayList<ConfigurationClass>());
            kieSession.insert(output);

            kieSession.getAgenda().getAgendaGroup("search-by-rate").setFocus();
            kieSession.fireAllRules();
            
            List<ConfigurationClass> configs = output.getConfigurations();
            List<ConfigurationResponseDTO> result = this.toDTOList(configs, idUser);
            return new PageImpl<>(result, pageable, (long)result.size());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public Double getAverageRating(Long configurationId) {
		Double average = 0.0;
        QueryResults results = kieSession.getQueryResults("Average rating", configurationId);
        for (QueryResultsRow queryResult: results) {
        	average = (Double) queryResult.get("$average");
        }
        System.out.println(average);
        return average;
	}

	@Override
	public Long getUsersByRate(Long configurationId, Long grade) {
		Long result = 0L;
        QueryResults results = kieSession.getQueryResults("Number of users by rate", configurationId, grade);
        for (QueryResultsRow queryResult : results) {
        	result = (Long) queryResult.get("$result");
        }
        return result;
	}
	
	
	public ConfigurationResponseDTO toDto(ConfigurationClass entity, int idUser) {
        Double average = this.getAverageRating((long)entity.getId());
        boolean favorited = favoriteService.checkIfUserFavorited(idUser, entity.getId());
        return new ConfigurationResponseDTO(entity, average, favorited);
    }

    public List<ConfigurationResponseDTO> toDTOList(List<ConfigurationClass> configs, int idUser) {
        ArrayList<ConfigurationResponseDTO> payload = new ArrayList<ConfigurationResponseDTO>();
        for(ConfigurationClass culturalOffer : configs) {
        	ConfigurationResponseDTO DTO = toDto(culturalOffer, idUser);
            payload.add(DTO);
        }
        return payload;
    }

}