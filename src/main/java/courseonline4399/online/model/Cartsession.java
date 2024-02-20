package courseonline4399.online.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NamedQuery(name="Cartsession.findAll", query="SELECT c FROM Cartsession c")
public class Cartsession implements Serializable {
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private float price;
	private int quantity;
	private Date createdate;
	private Date updatedate;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;



}