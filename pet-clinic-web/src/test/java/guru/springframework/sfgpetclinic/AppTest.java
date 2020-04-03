package guru.springframework.sfgpetclinic;


//**** JUnit 3 & 4 API imports ****
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

//**** JUnit 5 API imports ****
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
//public class AppTest extends TestCase	// <-- used by JUnit 3 & 4
@ExtendWith(SpringExtension.class)	// <-- used by JUnit 5
public class AppTest 	
{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        //super( testName );	// <-- used by JUnit 3 & 4 on extends TestCase
//    }

// ******* used by JUnit 3 & 4 ************
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }

    /**
     * Rigourous Test :-)
     */
    @Test	// <-- used by both JUnit 4 & 5
    public void testApp()
    {
        assertTrue( true );
    }
}
