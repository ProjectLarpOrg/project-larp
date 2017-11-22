package org.projectlarp.test.util;

import java.io.File;
import java.util.Properties;

import com.google.common.base.Throwables;

/**
 * Custom FTP connection manager.
 */
public class SFTPConnection {

	private String host;
	private String user;
	private String pass;
	private String path;
	private int port;

	public SFTPConnection(Properties props) {
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
		try {
			SFTPClient ftp = new SFTPClient();
			ftp.connect(host, user, pass, port);
			ftp.uploadFile(localFile.getAbsolutePath(), remoteFilePath);
			ftp.disconnect();
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	public boolean isARemoteFile(String folder, String fileName) {
		System.out.println(folder);
		System.out.println(fileName);
		String remoteFilePath = path + "/" + folder;
		System.out.println(remoteFilePath);
		try {
			SFTPClient ftp = new SFTPClient();
			ftp.connect(host, user, pass, port);
			boolean is = false;
			for (String fileInDir : ftp.listFileInDir(remoteFilePath)) {
				if (fileInDir.contains(fileName))
					is = true;
			}
			ftp.disconnect();
			return is;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
