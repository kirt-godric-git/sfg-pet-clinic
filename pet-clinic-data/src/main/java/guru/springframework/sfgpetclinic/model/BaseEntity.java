package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// *** Removed below all setters/getters because of using Lombok to remove boiler plate codes...****
	/*public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/
	
	public boolean isNew() {
		return this.id == null;
	}
}
