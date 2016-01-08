package v2.pages.health.page

import common.CommonPage

class SelfServiceHealth extends CommonPage {

    static at = { driver.currentUrl.contains("selfservice/monitor/health") }

    static url = "/selfservice/monitor/health";

}
