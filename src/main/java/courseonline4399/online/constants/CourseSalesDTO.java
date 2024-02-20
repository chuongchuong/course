package courseonline4399.online.constants;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSalesDTO {
    private int courseId;


    private String courseName;
    private float price;
    private String createdBy;
    private boolean courseStatus;

    // Fields that might be null
    private Integer salesCourseId;


    private Float salePercent;


    private Date saleEndDate;
    private Boolean saleStatus;
}
