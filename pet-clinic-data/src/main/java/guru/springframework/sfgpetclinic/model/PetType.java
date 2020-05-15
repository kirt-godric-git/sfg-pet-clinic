package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "types")
public class PetType extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Override
	public String toString() {
		return name;
	}

	// *** Removed below all setters/getters because of using Lombok to remove boiler plate codes...****
	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/
}
