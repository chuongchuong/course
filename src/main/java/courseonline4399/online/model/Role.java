package courseonline4399.online.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;

	@Override
	public String toString() {
		return "Role{" +
				"id='" + id + '\'' +
				'}';
	}
}