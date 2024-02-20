package courseonline4399.online.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="support")
public class Support implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	@NotNull
	private Date createddate= new Date();

	private Date updatedate;
	private String email;
	@ManyToOne
	@JoinColumn(name = "productid")
	private Course course;
	@NotEmpty
	private String noiDung;

	private Integer status;
	// == 0 chưa hỗ trợ
	// == 1 đã hỗ trợ
	// == 2 không liên lạc được với user
	private String video;


}