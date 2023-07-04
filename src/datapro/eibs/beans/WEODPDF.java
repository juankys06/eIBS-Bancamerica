/*
 * Created on Nov 20, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.beans;

import org.apache.taglibs.display.TableDecorator;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WEODPDF extends TableDecorator {

	public WEODPDF() {
		super();
	}
	
	public String getGroup() {
		String gr = (String) this.getObject();
		String rt = "<INPUT TYPE=RADIO  NAME=\"usercode\" CHECKED VALUE='" + gr + "'>";
		return rt;		
	}	
	public String getUser() {
		String gr = (String) this.getObject();
		String rt = "<INPUT TYPE=RADIO  NAME=\"usercode\" CHECKED VALUE='" + gr + "' onclick=\"goAction('Usuario ID')\">";
		return rt;		
	}	
	public String getName() {
		String rt =  (String) this.getObject();
		return rt;		
	}	
}
