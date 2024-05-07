package com.example.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "course_contents")
public class CourseContent {

        @Id
        private String id;
        private String courseId;
        @DBRef
        private Media media;
        private String createdAt;
        private String updatedAt;
}
