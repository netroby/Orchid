package com.eden.orchid.wiki

import com.eden.orchid.api.generators.OrchidGenerator
import com.eden.orchid.api.registration.OrchidModule
import com.eden.orchid.api.theme.menus.OrchidMenuFactory
import com.eden.orchid.utilities.addToSet
import com.eden.orchid.wiki.menu.WikiPagesMenuItemType
import com.eden.orchid.wiki.menu.WikiSectionsMenuItemType
import com.openhtmltopdf.slf4j.Slf4jLogger
import com.openhtmltopdf.util.XRLog

class WikiModule : OrchidModule() {

    override fun configure() {
        XRLog.setLoggingEnabled(false)
        XRLog.setLoggerImpl(Slf4jLogger())

        withResources(50)

        addToSet<OrchidGenerator, WikiGenerator>()
        addToSet<OrchidMenuFactory>(
                WikiPagesMenuItemType::class,
                WikiSectionsMenuItemType::class)
    }
}

