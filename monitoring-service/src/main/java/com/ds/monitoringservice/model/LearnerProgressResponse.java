package com.ds.monitoringservice.model;

import java.util.List;

public class LearnerProgressResponse {
    private List<LearnerProgress> learnerProgressList;
    private int totalCount;
    private long trueCount;

    public LearnerProgressResponse(List<LearnerProgress> learnerProgressList, int totalCount, long trueCount) {
        this.learnerProgressList = learnerProgressList;
        this.totalCount = totalCount;
        this.trueCount = trueCount;
    }

    public List<LearnerProgress> getLearnerProgressList() {
        return learnerProgressList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public long getTrueCount() {
        return trueCount;
    }
}
