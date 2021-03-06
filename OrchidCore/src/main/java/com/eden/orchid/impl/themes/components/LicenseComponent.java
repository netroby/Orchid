package com.eden.orchid.impl.themes.components;

import com.eden.orchid.api.OrchidContext;
import com.eden.orchid.api.options.annotations.Description;
import com.eden.orchid.api.resources.resource.OrchidResource;
import com.eden.orchid.api.theme.components.OrchidComponent;

import javax.inject.Inject;

@Description(value = "Locate and display your project's License file.", name = "License")
public final class LicenseComponent extends OrchidComponent {

    @Inject
    public LicenseComponent(OrchidContext context) {
        super(context, "license", 40);
    }

    public String getContent() {
        OrchidResource licenseResource = context.findClosestFile("license");
        if (licenseResource != null) {
            return licenseResource.compileContent(this);
        }
        return null;
    }
}
