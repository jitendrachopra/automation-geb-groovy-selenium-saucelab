package v2.pages.gebbook.page

import geb.Page
import v2.pages.gebbook.module.*


class GebishOrgHomePage extends Page {

    static at = { title == "Geb - Very Groovy Browser Automation" }

    static content = {
        manualsMenu { module MenuModule, $("#header-content ul li", 0) }
    }
}
