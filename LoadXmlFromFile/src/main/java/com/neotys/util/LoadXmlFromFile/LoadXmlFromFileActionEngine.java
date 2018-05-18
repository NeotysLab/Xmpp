package com.neotys.util.LoadXmlFromFile;

import java.io.File;
import java.util.List;


import com.google.common.base.Strings;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;
import com.neotys.extensions.action.engine.Context;
import com.neotys.extensions.action.engine.SampleResult;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public final class LoadXmlFromFileActionEngine implements ActionEngine {
	private String  FilePAth;
	@Override
	public SampleResult execute(Context context, List<ActionParameter> parameters) {
		final SampleResult sampleResult = new SampleResult();
		final StringBuilder requestBuilder = new StringBuilder();
		final StringBuilder responseBuilder = new StringBuilder();
		File file;

		for(ActionParameter parameter:parameters) {
			switch(parameter.getName()) {


				case LoadXmlFromFileAction.XMLFilePath:
					FilePAth = parameter.getValue();
					break;

			}
		}
		if (Strings.isNullOrEmpty(FilePAth)) {
			return getErrorResult(context, sampleResult, "Invalid argument: XMLFilePath cannot be null "
					+ LoadXmlFromFileAction.XMLFilePath + ".", null);
		}

		try
		{
			file=new File(FilePAth);
			if(!file.exists())
			{
				return getErrorResult(context, sampleResult, "Invalid argument: the file does not exist "
						+ LoadXmlFromFileAction.XMLFilePath + ".", null);

			}
			else
			{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				sampleResult.sampleStart();
				appendLineToStringBuilder(responseBuilder, doc.toString());
				sampleResult.sampleEnd();

			}
		}
		catch(Exception e)
		{
			return getErrorResult(context, sampleResult, "Technical Error :  "
					+ e.getMessage() + ".", e);

		}


		sampleResult.setRequestContent(requestBuilder.toString());
		sampleResult.setResponseContent(responseBuilder.toString());
		return sampleResult;
	}

	private void appendLineToStringBuilder(final StringBuilder sb, final String line){
		sb.append(line).append("\n");
	}

	/**
	 * This method allows to easily create an error result and log exception.
	 */
	private static SampleResult getErrorResult(final Context context, final SampleResult result, final String errorMessage, final Exception exception) {
		result.setError(true);
		result.setStatusCode("NL-XmppDisconnect_ERROR");
		result.setResponseContent(errorMessage);
		if(exception != null){
			context.getLogger().error(errorMessage, exception);
		} else{
			context.getLogger().error(errorMessage);
		}
		return result;
	}

	@Override
	public void stopExecute() {
		// TODO add code executed when the test have to stop.
	}

}
