package org.projectlarp.test.util;

import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Throwables;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Custom SSH connection manager.
 */
public class SSHConnection implements AutoCloseable {

	private JSch jschSSHChannel;
	private String strUserName;
	private String strConnectionIP;
	private int intConnectionPort;
	private String strPassword;
	private Session sesConnection;
	private int intTimeOut;

	public SSHConnection(Properties props) {
		jschSSHChannel = new JSch();
		try {
			jschSSHChannel.setKnownHosts("");
		} catch (JSchException jschX) {
			// do nothing
		}
		strUserName = props.getProperty("user");
		strPassword = props.getProperty("pass");
		strConnectionIP = props.getProperty("host");
		intConnectionPort = Integer.valueOf(props.getProperty("port", "22"))
				.intValue();
		intTimeOut = 6000;
	}

	public void connect() {
		try {
			sesConnection = jschSSHChannel.getSession(strUserName,
					strConnectionIP, intConnectionPort);
			sesConnection.setPassword(strPassword);
			// UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
			sesConnection.setConfig("StrictHostKeyChecking", "no");
			sesConnection.setConfig("PreferredAuthentications",
					"publickey,keyboard-interactive,password");
			sesConnection.connect(intTimeOut);
		} catch (JSchException jschX) {
			jschX.getMessage();
			throw Throwables.propagate(jschX);
		}
	}

	public String sendCommand(String command) {
		System.out.println("command: '" + command + "' on " + strConnectionIP);
		StringBuilder outputBuffer = new StringBuilder();
		try {
			Channel channel = sesConnection.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			InputStream commandOutput = channel.getInputStream();
			channel.connect();
			int readByte = commandOutput.read();

			while (readByte != 0xffffffff) {
				outputBuffer.append((char) readByte);
				readByte = commandOutput.read();
			}

			channel.disconnect();
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
		return outputBuffer.toString();
	}

	public void close() {
		sesConnection.disconnect();
	}

}
