package v2.pages.health.test

import common.util.GebConfigHelper
import geb.junit4.GebReportingTest
import groovy.json.JsonSlurper
import org.junit.Assume
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4



@RunWith(JUnit4)
class CDSAndSelfServiceHealthTest extends GebReportingTest {

    @Before
    public void setUp() throws Exception {

        Assume.assumeTrue(!GebConfigHelper.isIE());

    }

    @Test
    void HealthCheck() {
        go "https://api.ipify.org?format=json"
        def slurper = new JsonSlurper()
        def value = $("body").text()
        def result = slurper.parseText(value)

        def version= result.ip;

        assert version.equals("206.218.52.33")


    }

}