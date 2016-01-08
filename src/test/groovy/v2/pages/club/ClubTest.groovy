package v2.pages.club


import common.util.GebConfigHelper
import geb.junit4.GebReportingTest
import org.junit.Assume
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


//@Ignore
@RunWith(JUnit4)
class ClubTest extends GebReportingTest {

    @Before
    public void setUp() throws Exception {

        //Run test case based on certain condition
        Assume.assumeTrue(!GebConfigHelper.isSafari());

    }
    @Test
    void ClubTest1() {

        go "http://aaa.com/stop"
        $("form").ZipCodeEntry = "85027"
        $("#goBtn").click()
        waitFor {$("#block-aaa-login-block").displayed}
        $("#block-aaa-login-block a").click()

        waitFor{$("#edit-name").displayed}
        waitFor{$("#edit-pass").displayed}

        assert ($("#edit-name").displayed)
        assert ($("#edit-pass").displayed)

    }





}