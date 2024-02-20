package courseonline4399.online.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	@NotEmpty(message = "Username không được để trống")
	private String username;
	private String image;
	@Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
	private String password;
	@NotEmpty(message = "Tên học viên không được để trống")
	private String fullname;
	private Boolean status;
	@Column(unique = true)
	@NotEmpty(message = "Email không được để trống")
	@Email(message = "Email không hợp lệ")
	private String email;

	@NotEmpty(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})", message = "Số điện thoại không hợp lệ")
	private String phonenumber;


	@Column(name = "tokenrestpass")
	private String token;
}