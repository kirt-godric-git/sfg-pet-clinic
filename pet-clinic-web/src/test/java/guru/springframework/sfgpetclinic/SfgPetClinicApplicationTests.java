package guru.springframework.sfgpetclinic;


// **** JUnit 4 API imports ****
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;

//**** JUnit 5 API imports ****
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//@RunWith(SpringRunner.class)		// <-- used by JUnit 4
@ExtendWith(SpringExtension.class)	// <-- used by JUnit 5
@SpringBootTest
public class SfgPetClinicApplicationTests {

	@Test	// <-- used by both JUnit 4 & 5
	public void contextLoads() {
	}

}
