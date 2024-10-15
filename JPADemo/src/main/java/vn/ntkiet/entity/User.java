package vn.ntkiet.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "username", columnDefinition = "varchar(255)")
	private String username;
	
	@Column(name = "password", columnDefinition = "varchar(255)")
	private String password;
	
	@Column(name = "fullname", columnDefinition = "nvarchar(255)")
	private String fullName;

	@Column(name = "email")
	@Email(message = "Nhập đúng định dạng email")
	@NotEmpty(message = "Hãy nhập email")
	private String email;

	@Column(name = "images", columnDefinition = "varchar(255)")
	private String image;
	
	@Column(name = "roleId")
	private int roleId;
	
	@Pattern(regexp = "0\\d{8,10}", message = "Số điện thoại 8 đến 10 số")
	@NotEmpty(message = "Hãy nhập số điện thoại")
	@Column(name = "phone")
	private String phone;
}
