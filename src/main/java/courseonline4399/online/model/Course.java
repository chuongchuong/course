package courseonline4399.online.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="courses")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotEmpty(message = "Tên khóa học không được để trống")
	private String coursename;


	@Min(value = 1, message = "Tối thiểu giá là 1")
	@Max(value = 50000000, message = "Tối đa giá là 50000000")
	private float price;

	@NotNull
	@NotEmpty(message = "Hình không được để trống")
	@Column(columnDefinition = "TEXT")
	private String image;

	@NotEmpty(message = "Mô tả không được để trống")
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String descriptions;
	@NotNull
	private Date createddate;
	@NotNull
	private Date updatedate;
	@NotNull
	private Boolean status ;


	@ManyToOne
	@JoinColumn(name = "categoryid",referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private Category category;


	@ManyToOne
	@JoinColumn(name = "created_by",referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private User created_by;
}