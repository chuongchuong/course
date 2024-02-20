package courseonline4399.online.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="studyresults")
public class StudyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer correct;

    private Integer wrong;


    @ManyToOne
    @JoinColumn(name = "detailid")
    private CourseDetail detail;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    private Boolean status;

}
