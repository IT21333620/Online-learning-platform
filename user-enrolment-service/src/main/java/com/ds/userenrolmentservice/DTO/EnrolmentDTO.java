package com.ds.userenrolmentservice.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrolmentDTO {

    private String userId;
    private String courseId;
    private Date createdAt;
    private Date updatedAt;
}
