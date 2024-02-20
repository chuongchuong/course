package courseonline4399.online.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="coursedetail")

public class CourseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Tên khóa học không được để trống")
    private String name;

    @Min(value = 1,message = "Chapter khong hop le")
    @Column(name = "chapter")
    private int chapter;

    @NotEmpty(message = "URL khóa học không được để trống")
    @Column(name = "url", columnDefinition = "text")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String exercise;

    @Column(columnDefinition = "TEXT")
    private String exercisesolution;

    private Date createddate;
    private Date updatedate;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;
}
