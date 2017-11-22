package org.projectlarp.test.util;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

/**
 * Custom SSH command factory.
 */
public class SSHCommandFactory {

	private SSHConnection ssh;
	private String filePath;

	public SSHCommandFactory(Properties props, String filePath) {
		checkNotNull(props);
		checkNotNull(filePath);
		this.ssh = new SSHConnection(props);
		this.ssh.connect(); // MAINTAIN CONNECTION FOR PERFORMANCE
		this.filePath = filePath;
	}

	private static final String GREP_path_reg = "grep '%s' %s";
	private static final String GREP_COUNT = "grep -c '%s' %s";
	private static final String GREP_TAIL_path_reg = "cat %s | sed -n '1,/%s/p' | tail -5";
	private static final String GREP_LINE_NUMBER = "grep -n '%s' %s | cut -d : -f 1";
	private static final String EMPTY_FILE = "echo '' > %s";
	
	public String commandGrep(String reg) {
		checkNotNull(reg);
		return ssh.sendCommand(format(GREP_path_reg, reg, filePath));
	}

	public int commandGrepCount(String reg) {
		checkNotNull(reg);
		return Integer.valueOf(
				ssh.sendCommand(format(GREP_COUNT, reg, filePath))
						.replaceAll(" ", "").replaceAll("\\n", "")
						.replaceAll("\\r", "")).intValue();
	}
	
	public int commandGrepLineNumber(String reg){
		checkNotNull(reg);
		checkState(commandGrepCount(reg) == 1);
		
		return Integer.valueOf(
				ssh.sendCommand(format(GREP_LINE_NUMBER, reg, filePath))
						.replaceAll(" ", "").replaceAll("\\n", "")
						.replaceAll("\\r", "")).intValue();
	}

	public boolean commandGrepIsPresent(String reg) {
		return !commandGrep(reg).isEmpty();
	}

	// CUSTOM BUSINESS

	public String commandGetTrackerId(String reg) {
		checkNotNull(reg);
		String result = ssh.sendCommand(format(GREP_TAIL_path_reg, filePath,
				reg));
		checkState(result.contains("<trackerID>"), "contains <trackerID> in "
				+ result);
		Matcher matcher = Pattern.compile("<trackerID>(.*?)</trackerID>")
				.matcher(result);
		String _trackerId = null;
		if (matcher.find()) {
			_trackerId = matcher.group(1);
			System.out.println(_trackerId);
		}
		return _trackerId;
	}
	
	public String commandEmptyFile() {
		return ssh.sendCommand(format(EMPTY_FILE, filePath));
	}

}
