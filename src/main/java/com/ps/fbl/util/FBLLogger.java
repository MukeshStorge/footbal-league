/**
 * 
 */
package com.ps.fbl.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Mukesh.V
 *
 */
public class FBLLogger  {

	private static final Logger fBLLogger = LogManager.getLogger(FBLLogger.class.getName());
	
	public static void info(String msg) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode logMsgNode = mapper.createObjectNode();
		ObjectNode _node = (ObjectNode) logMsgNode;
		_node.put("dateTime", new Date().toString());
		_node.put("type", "info");
		_node.put("payload", msg);
		_node.put("timeInMillisec", System.currentTimeMillis());
		fBLLogger.info(_node.toString());
	}

	public static void debug(String msg) {
		fBLLogger.debug(msg);
	}

	public static void error(String msg, Exception e) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode logMsgNode = mapper.createObjectNode();
		ObjectNode _node = (ObjectNode) logMsgNode;
		_node.put("dateTime", new Date().toString());
		_node.put("type", "error");
		_node.put("payload", msg);
		_node.put("timeInMillisec", System.currentTimeMillis());
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		_node.put("error", sw.toString());
		fBLLogger.info(_node.toString());
	}

	public static void infoAsString(String msg) {
		fBLLogger.info(msg);
	}

}

