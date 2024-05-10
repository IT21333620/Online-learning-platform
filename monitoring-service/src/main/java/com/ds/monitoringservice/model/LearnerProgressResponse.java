package com.ds.monitoringservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LearnerProgressResponse {
    private List<LearnerProgress> learnerProgressList;
    private int totalCount;
    private long trueCount;

//    public LearnerProgressResponse(List<LearnerProgress> learnerProgressList, int totalCount, long trueCount) {
//        this.learnerProgressList = learnerProgressList;
//        this.totalCount = totalCount;
//        this.trueCount = trueCount;
//    }
//
//    public List<LearnerProgress> getLearnerProgressList() {
//        return learnerProgressList;
//    }
//
//    public int getTotalCount() {
//        return totalCount;
//    }
//
//    public long getTrueCount() {
//        return trueCount;
//    }
}
