package com.example.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "course_contents")
public class CourseContent {

        @Id
        private String id;
        private String courseId;
        private String title;
        private String description;
        @DBRef
        private Media media;
        private Date createdAt;
        private Date updatedAt;
}
