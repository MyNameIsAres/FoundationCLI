package org.ares.foundation.cli.util;

import org.apache.velocity.VelocityContext;

public interface Buildable {
    VelocityContext buildContext();
}
