package com.neotys.util.LoadXmlFromFile;

import static org.junit.Assert.assertEquals;

import com.neotys.xmpp.XmppDisconnectAction;
import org.junit.Test;

public class XmppDisconnectActionTest {
	@Test
	public void shouldReturnType() {
		final XmppDisconnectAction action = new XmppDisconnectAction();
		assertEquals("XmppDisconnect", action.getType());
	}

}
