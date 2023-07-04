<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="datapro.eibs.tools.ST_DocumentStatus"%>
<HTML>
<HEAD>
<TITLE>
Lista de Documentos
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "docList" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  function goAction(row) {
     document.forms[0].ROW.value = row;
     document.forms[0].submit();
  }

  
<%
boolean menu = true;
if ( userPO.getPurpose().equals("INQUIRY") ) 
 {
 
   if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
 <%   
 }
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
else if (userPO.getOption().equals("CD")){
%>
	builtNewMenu(cd_i_opt);
<%   
}
else if (userPO.getOption().equals("LN")){
 
   if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
   %>
	builtNewMenu(ln_i_1_opt);
  <%   
    }
   else  {
  %>
	builtNewMenu(ln_i_2_opt);
<%   
  }
 }
else if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_inq_personal_opt);
  <%      
 }
else if ( userPO.getOption().equals("CLIENT_C") ) {
   %>
		builtNewMenu(client_inq_corp_opt);
  <%      
 }
else if ( userPO.getOption().equals("LC") ) {
   %>
		builtNewMenu(lc_i_opt);
  <%      
 }
else if ( userPO.getOption().equals("DV") ) {
   %>
		builtNewMenu(coll_i_opt);
   <%
   }
else menu = false;
  }
   
else {
   
     if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_a_opt);
 <%   
 }
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_a_opt);
<%   
}
else if (userPO.getOption().equals("CD")){
%>
	builtNewMenu(cd_a_opt);
<%   
}
else if (userPO.getOption().equals("LN")){
%>
	builtNewMenu(ln_a_opt);
   <%   
 }
else if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_ap_personal_opt);
  <%      
 }
else if ( userPO.getOption().equals("CLIENT_C") ) {
   %>
		builtNewMenu(client_ap_corp_opt);
  <%      
    }
else menu = false;
   }
   %>
   
</SCRIPT>

</HEAD>

<BODY>

<% if (menu == true) { %>
<SCRIPT Language="Javascript">  initMenu(); </SCRIPT>
<% } %>

<h3 align="center">Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="doc_inq_list.jsp, EDI0010"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0">

<%
if ( docList.getNoResult() ) {
%>
  
  <TABLE class="tbenter" width=100% height=100%>
    <TR>
      <TD> 
        <div align="center">
          <font size="3"><b>
        	No hay resultados que correspondan a su criterio de búsqueda 
          </b></font>
        </div>
      </TD>
    </TR>
  </TABLE>

<%   		
}
else {
%>

<% 
      	String action = "";
	try {
		action = userPO.getHeader20();
		userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }
               
       if ( action.equals("DO_INQ") ) {
              if ( !pagename.equals("") ) {
%>
       			<SCRIPT Language="Javascript">	
					CenterWindow('<%= pagename %>',620,500,2);
              	</SCRIPT>

<%
              }
	}
%> 

  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="15%">
		Table : 
      </TD>
      <TD ALIGN=LEFT width="5%">
		<%= userPO.getHeader18() %>
      </TD>
      <TD ALIGN=CENTER width="20%">
		Descripción : 
      </TD>
      <TD ALIGN=LEFT width="60%">
		<%= userPO.getHeader19() %>
      </TD>
    </TR>
  </TABLE>

  <TABLE class="tableinfo">
    <TR id="TRDARK"> 
      <TH ALIGN=CENTER>Sequencia</TH>
      <TH ALIGN=CENTER>Descripción</TH>
      <TH ALIGN=CENTER>Frecuencia</TH>
      <TH ALIGN=CENTER>Status</TH>
    </TR>
  <%
                int row;
		          try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
                docList.initRow();
                while (docList.getNextRow()) {
					     if (!docList.getRecord(8).equals("0")) {
  %>

    <TR id="TRCLEAR"> 
      <TD NOWRAP ALIGN=CENTER><A HREF="javascript:goAction(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(2) %></A></TD>
      <TD NOWRAP ALIGN=CENTER><A HREF="javascript:goAction(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(3) %></A></TD>
      <TD NOWRAP ALIGN=CENTER><A HREF="javascript:goAction(<%= docList.getCurrentRow() %>)">
        <% if (docList.getRecord(4).equals("M")) out.print("MENSUAL");
           else if (docList.getRecord(4).equals("B")) out.print("BIMENSUAL"); 
           else if (docList.getRecord(4).equals("Q")) out.print("CUATRIMESTRAL");
           else if (docList.getRecord(4).equals("S")) out.print("SEMESTRAL"); 
           else if (docList.getRecord(4).equals("X")) out.print("SIN VENCIMIENTO");
           else if (docList.getRecord(4).equals("1")) out.print("CADA UN AÑO");
           else if (docList.getRecord(4).equals("2")) out.print("CADA DOS AÑOS");
           else if (docList.getRecord(4).equals("3")) out.print("CADA TRES AÑOS");        
           else if (docList.getRecord(4).equals("4")) out.print("CADA CUATRO AÑOS");
           else if (docList.getRecord(4).equals("5")) out.print("CADA CINCO AÑOS");
		   else out.print(""); %>
	  </A>	
      </TD>
      <% ST_DocumentStatus docStat = new ST_DocumentStatus("es"); %>
      <TD NOWRAP ALIGN=CENTER>
      <A HREF="javascript:goAction(<%= docList.getCurrentRow() %>)">
      <%= docStat.get(docList.getRecord(5)) %>
	  </A>
      </TD>
    </TR>

  <%
                  }
                  else {
  %>

    <TR id="TRCLEAR"> 
      <TD NOWRAP ALIGN=CENTER><%= docList.getRecord(2) %></TD>
      <TD NOWRAP ALIGN=CENTER><%= docList.getRecord(3) %></TD>
      <TD NOWRAP ALIGN=CENTER>
        <% if (docList.getRecord(4).equals("M")) out.print("MENSUAL");
           else if (docList.getRecord(4).equals("B")) out.print("BIMENSUAL"); 
           else if (docList.getRecord(4).equals("Q")) out.print("CUATRIMESTRAL");
           else if (docList.getRecord(4).equals("S")) out.print("SEMESTRAL"); 
           else if (docList.getRecord(4).equals("X")) out.print("SIN VENCIMIENTO");
           else if (docList.getRecord(4).equals("1")) out.print("CADA UN AÑO");
           else if (docList.getRecord(4).equals("2")) out.print("CADA DOS AÑOS");
           else if (docList.getRecord(4).equals("3")) out.print("CADA TRES AÑOS");        
           else if (docList.getRecord(4).equals("4")) out.print("CADA CUATRO AÑOS");
           else if (docList.getRecord(4).equals("5")) out.print("CADA CINCO AÑOS");
		   else out.print(""); %>
      </TD>
      <TD NOWRAP ALIGN=CENTER>
        <% if (docList.getRecord(5).equals("CO")) out.print("ESCANEADO");
           else if (docList.getRecord(5).equals("NP")) out.print("NO PRESENTADO");
           else if (docList.getRecord(5).equals("")) out.print("INCOMPLETO");
           else if (docList.getRecord(5).equals("PD")) out.print("VENCIDO");
		   else out.print(""); %>	
      </TD>
    </TR>

  <%
                  }
                }
  %> 
  </TABLE>

<%
}
%>

</FORM>

</BODY>
</HTML>
