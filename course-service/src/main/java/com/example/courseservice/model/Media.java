package com.example.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media {

        @Id
        private String id;
        private String type;
        private String url;
        private String createdAt;
        private String updatedAt;
}