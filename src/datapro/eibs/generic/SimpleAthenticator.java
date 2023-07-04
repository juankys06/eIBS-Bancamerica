package datapro.eibs.generic;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author ogarcia
 *
 */
public class SimpleAthenticator extends Authenticator {
	private String username = null;
	private String password = null;

	public SimpleAthenticator(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
