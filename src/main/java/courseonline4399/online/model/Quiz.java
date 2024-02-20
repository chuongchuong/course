package courseonline4399.online.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;



@Data
@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String question;

    @NotNull
    private String answer_a;

    @NotNull
    private String answer_b;

    @NotNull
    private String answer_c;
    @NotNull
    private String answer_d;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "coursedetailid")
    private CourseDetail coursedetail;


}