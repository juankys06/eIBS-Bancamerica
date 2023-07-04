<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@ page import = "datapro.eibs.master.Util" %>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers

function enter(code1,code2,code3,code4,code5,code6,code7,code8) {

  if(top.opener.fieldAux1 !== ""){top.opener.document.forms[0][top.opener.fieldAux1].value = code1}
  if(top.opener.fieldAux2 !== ""){top.opener.document.forms[0][top.opener.fieldAux2].value = code2}
  if(top.opener.fieldAux3 !== ""){top.opener.document.forms[0][top.opener.fieldAux3].value = code3}
  if(top.opener.fieldAux4 !== ""){top.opener.document.forms[0][top.opener.fieldAux4].value = code4}
  if(top.opener.fieldAux5 !== ""){top.opener.document.forms[0][top.opener.fieldAux5].value = code5}
  if(top.opener.fieldAux6 !== ""){top.opener.document.forms[0][top.opener.fieldAux6].value = code6}
  if(top.opener.fieldAux7 !== ""){top.opener.document.forms[0][top.opener.fieldAux7].value = code7}
  if(top.opener.fieldAux8 !== ""){top.opener.document.forms[0][top.opener.fieldAux8].value = code8}
  top.close();
 }


//-->
</script>
</head>

<jsp:useBean id= "ewd0001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( ewd0001Help.getNoResult() ) {
		String sMsg = "";
		if (userPO.getHeader1().equals("*")) {
			sMsg = "Usuario no tiene permisos para ver este Cliente";
		} else {
			//sMsg = "There is no match for your search criteria";
			sMsg = "No hay datos que correspondan con su criterio de busqueda";
		}	
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <b> <%= sMsg %></b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="10%"> Cliente</TH>
      <TH ALIGN=CENTER  nowrap width="40%">Nombre Corto</TH>
		<TH align="CENTER" nowrap width="15%">Ciudad</TH>
		<TH align="CENTER" nowrap width="15%">Pais</TH>
		<TH ALIGN=CENTER  nowrap width="20%">Identificación</TH>
    </TR>
    <%
                String Type = (String)session.getAttribute("Type");
                String NameSearch = (String)session.getAttribute("NameSearch");
                ewd0001Help.initRow();
                while (ewd0001Help.getNextRow()) {
               		out.println(ewd0001Help.getRecord());
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0001Help.getShowPrev() ) {
      			int pos =ewd0001Help.getFirstRec() - 81;
      			   out.print("<A HREF=\"" +
      			   			 request.getContextPath() +
      			   			 "/servlet/datapro.eibs.helps.JSEWD0001D_LC?NameSearch=" + 
      			   			 NameSearch + 
      			   			 "&FromRecord=" + 
      			   			 pos + 
      			   			 "&Type=" + 
      			   			 Type + 
      			   			 "\" > <img src=\"" +
      			   			 request.getContextPath() +
      			   			 "/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0001Help.getShowNext() ) {
      			int pos = ewd0001Help.getLastRec();
      			out.print("<A HREF=\"" +
      			          request.getContextPath() +
      			          "/servlet/datapro.eibs.helps.JSEWD0001D_LC?NameSearch=" + 
      			          NameSearch + 
      			          "&FromRecord=" + 
      			          pos + 
      			          "&Type=" + 
      			          Type + 
      			          "\" ><img src=\"" +
      			          request.getContextPath() +
      			          "/images/s/next_records.gif\" border=0></A>");
        }
%> </TD>
	 </TR>
	 </TABLE>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>

