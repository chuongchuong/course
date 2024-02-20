package courseonline4399.online.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name="salescourses")
@NamedQuery(name="SalesCourses.findAll", query="SELECT r FROM SalesCourse r")
public class SalesCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value=1 , message = "Tối thiểu là 1%")
    @Max(value = 100,message = "Tối đa là 100%")
    private float salepercent;


    private Date createddate = new Date();

    @NotNull(message = "Ngày không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endsaledate;

    @Column( columnDefinition = "BIT default 0")
    private Boolean statussale;

    @ManyToOne
    @JoinColumn(name = "coursesid")
    private Course course;


}
