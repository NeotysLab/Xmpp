package com.neotys.util.LoadXmlFromFile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoadXmlFromFileActionTest {
	@Test
	public void shouldReturnType() {
		final LoadXmlFromFileAction action = new LoadXmlFromFileAction();
		assertEquals("LoadXmlFromFile", action.getType());
	}

}
