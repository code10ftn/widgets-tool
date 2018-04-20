package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateWidgetTest.class, LoginTest.class, OpenProjectTest.class })
public class AllTests {

}
