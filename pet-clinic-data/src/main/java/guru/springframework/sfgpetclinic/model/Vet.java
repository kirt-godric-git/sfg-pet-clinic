package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

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
@Table(name = "vets")
public class Vet extends Person {
	@ManyToMany(fetch = FetchType.EAGER) // <-- coz by default @ManyToMany is lazy
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"), 
		inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties = new HashSet<>();

	// *** Removed below all setters/getters because of using Lombok to remove boiler plate codes...****
	/*public Set<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Set<Specialty> specialties) {
		this.specialties = specialties;
	}*/
}
