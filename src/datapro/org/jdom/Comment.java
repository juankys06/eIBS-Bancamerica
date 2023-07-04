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

package datapro.org.jdom;

import java.io.Serializable;

/**
 * <p>
 * <code>Comment</code> defines behavior for an XML
 *   comment, modeled in Java.  Methods
 *   allow the user to obtain the text of the comment.
 * </p>
 *
 * @author Brett McLaughlin
 * @author Jason Hunter
 * @version 1.0
 */
public class Comment implements Serializable, Cloneable {

	/** Text of the <code>Comment</code> */
	protected String text;

	// XXX parent and document can use the same memory location

	/** Parent node, or null if none */
	protected Element parent;

	/** Document node if comment is outside the root element, or null if none */
	protected Document document;

	/**
	 * <p>
	 * Default, no-args constructor for implementations
	 *   to use if needed.
	 * </p>
	 */
	protected Comment() {}

	/**
	 * <p>
	 * This creates the comment with the supplied
	 *   text.
	 * </p>
	 *
	 * @param text <code>String</code> content of comment.
	 */
	public Comment(String text) {
		setText(text);
	}

	/**
	 * <p>
	 * This will return the parent of this <code>Comment</code>.
	 *   If there is no parent, then this returns <code>null</code>.
	 * </p>
	 *
	 * @return parent of this <code>Comment</code>
	 */
	public Element getParent() {
		return parent;
	}

	/**
	 * <p>
	 * This will set the parent of this <code>Comment</code>.
	 * </p>
	 *
	 * @param parent <code>Element</code> to be new parent.
	 * @return this <code>Comment</code> modified.
	 */
	protected Comment setParent(Element parent) {
		this.parent = parent;
		return this;
	}

	/**
	 * <p>
	 * This retrieves the owning <code>{@link Document}</code> for
	 *   this Comment, or null if not a currently a member of a
	 *   <code>{@link Document}</code>.
	 * </p>
	 *
	 * @return <code>Document</code> owning this Element, or null.
	 */
	public Document getDocument() {
		if (document != null) {
			return document;
		}

		Element p = getParent();
		if (p != null) {
			return p.getDocument();
		}

		return null;
	}

	/**
	 * <p>
	 * This sets the <code>{@link Document}</code> parent of this comment.
	 * </p>
	 *
	 * @param document <code>Document</code> parent
	 * @return this <code>Comment</code> modified
	 */
	protected Comment setDocument(Document document) {
		this.document = document;
		return this;
	}

	/**
	 * <p>
	 * This returns the textual data within the
	 *   <code>Comment</code>.
	 * </p>
	 *
	 * @return <code>String</code> - text of comment.
	 */
	public String getText() {
		return text;
	}

	/**
	 * <p>
	 * This will set the value of the <code>Comment</code>.
	 * </p>
	 *
	 * @param text <code>String</code> text for comment.
	 * @return <code>Comment</code> - this Comment modified.
	 */
	public Comment setText(String text) {
		String reason;
		if ((reason = Verifier.checkCommentData(text)) != null) {
			throw new IllegalDataException(text, "comment", reason);
		}

		this.text = text;
		return this;
	}

	/**
	 * <p>
	 *  This returns a <code>String</code> representation of the
	 *    <code>Comment</code>, suitable for debugging. If the XML
	 *    representation of the <code>Comment</code> is desired,
	 *    <code>{@link #getSerializedForm}</code> should be used.
	 * </p>
	 *
	 * @return <code>String</code> - information about the
	 *         <code>Attribute</code>
	 */
	public String toString() {
		return new StringBuffer()
			.append("[Comment: ")
			.append(getSerializedForm())
			.append("]")
			.toString();
	}

	/**
	 * <p>
	 *  This will return the <code>Comment</code> in XML format,
	 *    usable in an XML document.
	 * </p>
	 *
	 * @return <code>String</code> - the serialized form of the
	 *         <code>Comment</code>.
	 */
	public final String getSerializedForm() {
		return new StringBuffer()
			.append("<!--")
			.append(text)
			.append("-->")
			.toString();
	}

	/**
	 * <p>
	 *  This tests for equality of this <code>Comment</code> to the supplied
	 *    <code>Object</code>.
	 * </p>
	 *
	 * @param ob <code>Object</code> to compare to.
	 * @return <code>boolean</code> - whether the <code>Comment</code> is
	 *         equal to the supplied <code>Object</code>.
	 */
	public final boolean equals(Object ob) {
		return (ob == this);
	}

	/**
	 * <p>
	 *  This returns the hash code for this <code>Comment</code>.
	 * </p>
	 *
	 * @return <code>int</code> - hash code.
	 */
	public final int hashCode() {
		return super.hashCode();
	}

	/**
	 * <p>
	 *  This will return a clone of this <code>Comment</code>.
	 * </p>
	 *
	 * @return <code>Object</code> - clone of this <code>Comment</code>.
	 */
	public Object clone() {
		Comment comment = new Comment(text);
		return comment;
	}

}