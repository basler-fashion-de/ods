package com.blauband.ods;

import com.softteco.toolset.restlet.AbstractUserSession;

/**
 *
 * @author sergeizenevich
 */
public class UserSessionImpl extends AbstractUserSession {

    @Override
    protected void cleanup() {

    }

    public boolean isUser(final String account) {
        return true;
    }
}
