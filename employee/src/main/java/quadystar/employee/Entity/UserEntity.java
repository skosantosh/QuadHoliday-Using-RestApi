package quadystar.employee.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="user_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	private String email;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	private String avatar;

}
