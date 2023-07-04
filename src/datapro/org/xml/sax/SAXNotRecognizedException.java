// SAXNotRecognizedException.java - unrecognized feature or value.
// Written by David Megginson, sax@megginson.com
// NO WARRANTY!  This class is in the Public Domain.

// $Id: SAXNotRecognizedException.java,v 1.1 2010/06/07 19:09:44 fhernandez Exp $


package datapro.org.xml.sax;


// SAXNotRecognizedException.java - unrecognized feature or value.
// Written by David Megginson, sax@megginson.com
// NO WARRANTY!  This class is in the Public Domain.

// $Id: SAXNotRecognizedException.java,v 1.1 2010/06/07 19:09:44 fhernandez Exp $


/**
 * Exception class for an unrecognized identifier.
 *
 * <blockquote>
 * <em>This module, both source code and documentation, is in the
 * Public Domain, and comes with <strong>NO WARRANTY</strong>.</em>
 * </blockquote>
 *
 * <p>An XMLReader will throw this exception when it finds an
 * unrecognized feature or property identifier; SAX applications and
 * extensions may use this class for other, similar purposes.</p>
 *
 * @since SAX 2.0
 * @author David Megginson, 
 *         <a href="mailto:sax@megginson.com">sax@megginson.com</a>
 * @version 2.0r2pre
 * @see datapro.org.xml.sax.SAXNotSupportedException
 */
public class SAXNotRecognizedException extends SAXException
{

	/**
	 * Default constructor.
	 */
	public SAXNotRecognizedException ()
	{
	super();
	}


	/**
	 * Construct a new exception with the given message.
	 *
	 * @param message The text message of the exception.
	 */
	public SAXNotRecognizedException (String message)
	{
	super(message);
	}

}