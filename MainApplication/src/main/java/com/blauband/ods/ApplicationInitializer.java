package com.blauband.ods;

import com.google.inject.Module;
import com.softteco.toolset.AbstractApplicationInitializer;

/**
 *
 * @author sergeizenevich
 */
public class ApplicationInitializer extends AbstractApplicationInitializer {

    @Override
    protected Module[] getModules() {
        return new Module[]{new ApplicationModule()};
    }
    
}
