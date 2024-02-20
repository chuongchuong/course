package courseonline4399.online.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usercourses")
@NamedQuery(name="UserCourse.findAll", query="SELECT u FROM UserCourse u")

public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Boolean statusmail;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "ownby")
    private User owner;
}
