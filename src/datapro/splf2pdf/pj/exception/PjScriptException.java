package datapro.splf2pdf.pj.exception;

/*
  
  Copyright (C) 1998 Etymon Systems, Inc. <info@etymon.com>

  This program is free software; you can redistribute it and/or modify
  it under the terms of version 2 of the GNU General Public License as
  published by the Free Software Foundation.

  This program is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
  02111-1307, USA.

*/

/**
   An exception that gets thrown by PjScript.
   @author Nassib Nassar
*/
public class PjScriptException
	extends PjException {

	String _message;
	int _lineNumber;
	String _source;
	int _errorType;

	/**
	   Creates a PjScriptException with detailed arguments.
	   @param message a detailed message.
	   @param lineNumber the line number in the script where the exception occurred.
	   @param source the file or program where the script originated.
	   @param errorType the general class of error.
	*/
	public PjScriptException(String message, int lineNumber, String source, int errorType) {
		super(message);
		_message = message;
		_lineNumber = lineNumber;
		_source = source;
		_errorType = errorType;
	}
	public int getErrorType() {
		return _errorType;
	}
	public String getFullMessage() {
		if (_lineNumber == -1) {
			return "pjscript: " + _source + ": " + _message;
		} else {
			return "pjscript: " + _source + ":" + _lineNumber + ": " + _message;
		}
	}
	public int getLineNumber() {
		return _lineNumber;
	}
	public String getMessage() {
		return _message;
	}
	public String getSource() {
		return _source;
	}
}