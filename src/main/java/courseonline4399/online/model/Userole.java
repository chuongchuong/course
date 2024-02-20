package courseonline4399.online.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="useroles")
@NamedQuery(name="Userole.findAll", query="SELECT u FROM Userole u")
public class Userole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "roleid")
	private Role role;



}