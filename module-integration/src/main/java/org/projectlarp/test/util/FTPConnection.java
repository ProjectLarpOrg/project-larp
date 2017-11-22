package org.projectlarp.test.util;

import static org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

import com.google.common.base.Throwables;

/**
 * Custom FTP connection manager.
 */
@Deprecated
public class FTPConnection {

	private String host;
	private String user;
	private String pass;
	private String path;
	private int port;

	public FTPConnection(Properties props) {
		host = props.getProperty("host");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
		path = props.getProperty("path");
		port = Integer.valueOf(props.getProperty("port", "22"));
	}

	public void store(File localFile, String remoteFileName) {
		System.out.println(localFile);
		String remoteFilePath = path + "/" + remoteFileName;
		System.out.println(remoteFilePath);
		try (FileInputStream fis = new FileInputStream(localFile)) {
			FTPClient ftp = new FTPClient();
			ftp.connect(host, port);
			ftp.login(user, pass);
			ftp.setFileType(BINARY_FILE_TYPE);
			ftp.storeFile(remoteFilePath, fis);
			ftp.logout();
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
