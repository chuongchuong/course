package courseonline4399.online.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the comments database table.
 *
 */
@Data
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;



	@Column(columnDefinition = "TEXT")
	private String contents;

	@Temporal(TemporalType.DATE)
	private Date createddate ;

	@ManyToOne
	@JoinColumn(name="courseid",referencedColumnName = "id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "userid",referencedColumnName = "id")
	private User user;

	private boolean status;

}