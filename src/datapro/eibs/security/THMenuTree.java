package datapro.eibs.security;

import java.io.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class THMenuTree extends BodyTagSupport{
	public int doStartTag() throws JspTagException{
		return(EVAL_BODY_TAG);
	}
}