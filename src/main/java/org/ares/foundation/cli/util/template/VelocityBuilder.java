package org.ares.foundation.cli.util.template;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityBuilder {

    VelocityEngine velocityEngine = new VelocityEngine();
    public VelocityEngine createVelocityEngineFoundation() {
        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, System.getProperty("user.home") + "\\FoundationCLI\\foundation\\");
        velocityEngine.init();

        return velocityEngine;

    }
}
