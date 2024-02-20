package courseonline4399.online.constants;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.CourseVideo;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailsResponse {
    private Course course;
    private List<CourseVideo> videos;
}
