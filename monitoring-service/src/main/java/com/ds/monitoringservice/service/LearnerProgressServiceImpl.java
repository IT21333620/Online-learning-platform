package com.ds.monitoringservice.service;

import com.ds.monitoringservice.model.LearnerProgress;
import com.ds.monitoringservice.model.LearnerProgressResponse;
import com.ds.monitoringservice.repo.LearnerProgressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Service
public class LearnerProgressServiceImpl implements LearnerProgressService{
    @Autowired
    private LearnerProgressRepo learnerProgressRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;

//    public List<LearnerProgress> getAllProgress() {
//        // Retrieve learner progress data from the external service
//        LearnerProgress response = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8081/api/v1/enrolment/getAllEnrolments")
//                .retrieve()
//                .bodyToMono(LearnerProgress.class)
//                .block();
//
//        if (response != null && response.getLearnerProgressList() != null) {
//            List<LearnerProgress> learnerProgressList = response.getLearnerProgressList();
//
//            // Save the retrieved learner progress list to the database (if needed)
//            learnerProgressRepo.saveAll(learnerProgressList);
//
//            return learnerProgressList;
//        } else {
//            return Collections.emptyList(); // Or handle the case where no data is retrieved
//        }
//    }

//    public LearnerProgressResponse getAllProgress() {
//        // Retrieve learner progress data from the external service
//        LearnerProgress response = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8081/api/v1/enrolment/getAllEnrolments")
//                .retrieve()
//                .bodyToMono(LearnerProgress.class)
//                .block();
//
//        if (response != null && response.getLearnerProgressList() != null) {
//            List<LearnerProgress> learnerProgressList = response.getLearnerProgressList();
//
//            // Save the retrieved learner progress list to the database (if needed)
//            learnerProgressRepo.saveAll(learnerProgressList);
//
//            // Count the records with status "true"
//            long trueCount = learnerProgressList.stream()
//                    .filter(progress -> Boolean.TRUE.equals(progress.getStatus()))
//                    .count();
//
//            // Create and return the response object including counts
//            return new LearnerProgressResponse(learnerProgressList, learnerProgressList.size(), trueCount);
//        } else {
//            return new LearnerProgressResponse(Collections.emptyList(), 0, 0); // Or handle the case where no data is retrieved
//        }
//    }

    public LearnerProgressResponse getAllProgress() {
        // Retrieve learner progress data from the external service
        LearnerProgress response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/enrolment/getAllEnrolments")
                .retrieve()
                .bodyToMono(LearnerProgress.class)
                .block();

        if (response != null && response.getLearnerProgressList() != null) {
            List<LearnerProgress> learnerProgressList = response.getLearnerProgressList();

            // Save the retrieved learner progress list to the database (if needed)
            learnerProgressRepo.saveAll(learnerProgressList);

            // Count the records with status "true"
            long trueCount = learnerProgressList.stream()
                    .filter(progress -> "true".equalsIgnoreCase(progress.getStatus()))
                    .count();

            // Create and return the response object including counts
            return new LearnerProgressResponse(learnerProgressList, learnerProgressList.size(), trueCount);
        } else {
            return new LearnerProgressResponse(Collections.emptyList(), 0, 0); // Or handle the case where no data is retrieved
        }
    }



}
