package org.ares.foundation.cli;

import org.apache.velocity.VelocityContext;

public interface Buildable {
    VelocityContext buildContext();
}
