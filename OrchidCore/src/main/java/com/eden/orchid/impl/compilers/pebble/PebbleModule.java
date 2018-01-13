package com.eden.orchid.impl.compilers.pebble;

import com.eden.orchid.api.compilers.TemplateFunction;
import com.eden.orchid.api.registration.OrchidModule;
import com.eden.orchid.impl.compilers.templateFunctions.AlertFunction;
import com.eden.orchid.impl.compilers.templateFunctions.CompileAsFunction;
import com.eden.orchid.impl.compilers.templateFunctions.FindTemplateFunction;
import com.mitchellbosecke.pebble.extension.Extension;

public final class PebbleModule extends OrchidModule {

    @Override
    protected void configure() {
        // Template Functions
        addToSet(TemplateFunction.class,
                AlertFunction.class,
                CompileAsFunction.class,
                FindTemplateFunction.class);

        // Pebble Extensions
        addToSet(Extension.class,
                PebbleFunctionsExtension.class);
    }
}
