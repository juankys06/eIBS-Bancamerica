<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@ page import = "datapro.eibs.master.Util" %>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers

function Action(user,type) {
  //CenterWindow(webapp+"/servlet/com.datapro.eibs.internet.JSESS2000?SCREEN=409&TYPE=" + type + "&CUSID=" + user+"&E01EUSENT="+entity,850,850,2)
  if(top.opener.fieldAux1 !== ""){top.opener.document.forms[0][top.opener.fieldAux1].value = user}
  //if(top.opener.fieldAux2 !== ""){top.opener.document.forms[0][top.opener.fieldAux2].value = code2}
  top.close();
 }


//-->
</script>
</head>

<jsp:useBean id= "ess0005Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( ess0005Help.getNoResult() ) {
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
      <TH ALIGN=CENTER  nowrap width="40%">Nombre Cliente</TH>
		<TH align="CENTER" nowrap width="15%">Usuario/Entidad</TH>
		<TH align="CENTER" nowrap width="15%">Tipo</TH>
		<TH ALIGN=CENTER  nowrap width="20%">Estado</TH>
    </TR>
    <%
                String Type = (String)session.getAttribute("Type");
                String NameSearch = (String)session.getAttribute("NameSearch");
                ess0005Help.initRow();
                while (ess0005Help.getNextRow()) {
                    if (ess0005Help.getFlag().equals("")) {
                    		out.println(ess0005Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ess0005Help.getShowPrev() ) {
      			int pos =ess0005Help.getFirstRec() - 81;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSESS0005?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&Type=" + Type + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ess0005Help.getShowNext() ) {
      			int pos = ess0005Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSESS0005?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&Type=" + Type + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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

