package com.ds.monitoringservice.service;

import com.ds.monitoringservice.model.LearnerProgress;
import com.ds.monitoringservice.model.LearnerProgressResponse;

import java.util.List;
import java.util.Map;

public interface LearnerProgressService {
        LearnerProgressResponse getAllProgress();
        Map<String, Map<String, Integer>> getCourseIdCounts();
        Map<String, Map<String, Integer>> getUserIdCounts();
}

