/*-- 

 Copyright (C) 2000 Brett McLaughlin & Jason Hunter.
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.
 
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows 
    these conditions in the documentation and/or other materials 
    provided with the distribution.

 3. The name "JDOM" must not be used to endorse or promote products
    derived from this software without prior written permission.  For
    written permission, please contact license@jdom.org.
 
 4. Products derived from this software may not be called "JDOM", nor
    may "JDOM" appear in their name, without prior written permission
    from the JDOM Project Management (pm@jdom.org).
 
 In addition, we request (but do not require) that you include in the 
 end-user documentation provided with the redistribution and/or in the 
 software itself an acknowledgement equivalent to the following:
     "This product includes software developed by the
      JDOM Project (http://www.jdom.org/)."
 Alternatively, the acknowledgment may be graphical using the logos 
 available at http://www.jdom.org/images/logos.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 This software consists of voluntary contributions made by many 
 individuals on behalf of the JDOM Project and was originally 
 created by Brett McLaughlin <brett@jdom.org> and 
 Jason Hunter <jhunter@jdom.org>.  For more information on the 
 JDOM Project, please see <http://www.jdom.org/>.
 
 */

package datapro.org.jdom.adapters;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;

import datapro.org.w3c.dom.Document;

/**
 * <b><code>AbstractDOMAdapter</code></b>
 * <p>
 * This class defines wrapper behavior for obtaining a DOM
 *   <code>Document</code> object from a DOM parser.
 * </p>
 *
 * @author Brett McLaughlin
 * @author Jason Hunter
 * @version 1.0
 */
public abstract class AbstractDOMAdapter implements DOMAdapter {

	/**
	 * <p>
	 * This creates a new <code>{@link Document}</code> from an
	 *   existing <code>InputStream</code> by letting a DOM
	 *   parser handle parsing using the supplied stream.
	 * </p>
	 *
	 * @param in <code>InputStream</code> to parse.
	 * @param validate <code>boolean</code> to indicate if validation should occur.
	 * @return <code>Document</code> - instance ready for use.
	 * @throws <code>IOException</code> when errors occur in
	 *                                  parsing.
	 */
	public Document getDocument(File filename, boolean validate)
		throws IOException {

		return getDocument(new FileInputStream(filename), validate);
	}

	/**
	 * <p>
	 * This creates a new <code>{@link Document}</code> from an
	 *   existing <code>InputStream</code> by letting a DOM
	 *   parser handle parsing using the supplied stream.
	 * </p>
	 *
	 * @param in <code>InputStream</code> to parse.
	 * @param validate <code>boolean</code> to indicate if validation should occur.
	 * @return <code>Document</code> - instance ready for use.
	 * @throws <code>IOException</code> when errors occur in
	 *                                  parsing.
	 */
	public abstract Document getDocument(InputStream in, boolean validate)
		throws IOException;

	/**
	 * <p>
	 * This creates an empty <code>Document</code> object based
	 *   on a specific parser implementation.
	 * </p>
	 *
	 * @return <code>Document</code> - created DOM Document.
	 * @throws <code>IOException</code> when errors occur.
	 */
	public abstract Document createDocument() throws IOException;
}