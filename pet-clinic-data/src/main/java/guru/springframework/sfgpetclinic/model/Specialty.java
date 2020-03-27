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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialty")
public class Specialty extends BaseEntity {
	@Column(name = "description")
	private String description;

	// *** Removed below all setters/getters because of using Lombok to remove boiler plate codes...****
	/*public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}*/
}
