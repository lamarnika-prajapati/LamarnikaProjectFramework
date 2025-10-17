package com.org.project.automation.utils;

import static com.xebialabs.overthere.ConnectionOptions.*;
import static com.xebialabs.overthere.OperatingSystemFamily.UNIX;
import static com.xebialabs.overthere.ssh.SshConnectionBuilder.CONNECTION_TYPE;
import static com.xebialabs.overthere.ssh.SshConnectionType.SFTP;

import com.xebialabs.overthere.ConnectionOptions;
import com.xebialabs.overthere.Overthere;
import com.xebialabs.overthere.OverthereConnection;
import com.xebialabs.overthere.CmdLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides methods to execute shell commands on remote machine
 */
public class RemoteShellManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteShellManager.class);
    private String host;
    private String userName;
    private String password;

    public RemoteShellManager(String host, String userName, String password) {
        this.host = host;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Method to execute shell command on remote host
     *
     * @param cmdName
     * @param cmd
     * @param args
     * @throws Exception
     */

    public void runRemoteShellCommand(String cmdName, String cmd, String... args) throws Exception {
        ConnectionOptions options = new ConnectionOptions();
        options.set(ADDRESS, host);
        options.set(USERNAME, userName);
        options.set(PASSWORD, password);
        options.set(OPERATING_SYSTEM, UNIX);
        options.set(CONNECTION_TYPE, SFTP);
        OverthereConnection connection = Overthere.getConnection("ssh", options);
        String params = "";
        try {
            for (String arg : args) {
                params += arg + ",";
            }
            LOGGER.info("Executing remote shell command: {}", cmdName);
            connection.execute(CmdLine.build(cmdName, cmd, params.replaceAll(",$", "")));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            LOGGER.info("Remote shell connection closed");
            connection.close();
        }
    }
}

