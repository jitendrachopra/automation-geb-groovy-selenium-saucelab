package v2.pages.gebbook.page

import geb.Page
import v2.pages.gebbook.module.*

class TheBookOfGebPage extends Page {

    static at = { title.startsWith("The Book Of Geb") }
}
