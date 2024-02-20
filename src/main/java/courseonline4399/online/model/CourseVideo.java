package courseonline4399.online.model;

import jakarta.persistence.*;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="coursevideos")
@NamedQuery(name="CourseVideo.findAll", query="SELECT c FROM CourseVideo c")
public class CourseVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;


    private int chapter;

    @Column( columnDefinition = "text")
    private String url;


    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;


}
