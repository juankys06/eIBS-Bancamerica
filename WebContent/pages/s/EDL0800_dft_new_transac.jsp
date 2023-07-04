
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnGenTran" class="datapro.eibs.beans.EDL080030Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
 
    builtNewMenu(dft_m_opt);

</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

  out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>
<h3 align="center">Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_new_transac.jsp, EDL0800"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="26">
  <INPUT TYPE=HIDDEN NAME="E30DEABNK"  value="<%= lnGenTran.getE30DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E30DEACCY"  value="<%= lnGenTran.getE30DEACCY().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E30DEACUN" size="10" maxlength="9" value="<%= lnGenTran.getE30DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E30CUSNA1" size="50" maxlength="45" value="<%= lnGenTran.getE30CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E30DEAACC" size="13" maxlength="12" value="<%= lnGenTran.getE30DEAACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E30DEACCY2" size="4" maxlength="3" value="<%= lnGenTran.getE30DEACCY().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E30DEAPRO" size="5" maxlength="4" value="<%= lnGenTran.getE30DEAPRO().trim()%>" >
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Distribuci&oacute;n del Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">VALOR DEL PRESTAMO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30TRNPRI" value="<%= lnGenTran.getE30TRNPRI().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
              <div align="right">FECHA DE VENCIMIENTO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="2" maxlength="2" name="E30DEAMD1" value="<%= lnGenTran.getE30DEAMD1().trim()%>"  readonly>
              <input type="text" size="2" maxlength="2" name="E30DEAMD2" value="<%= lnGenTran.getE30DEAMD2().trim()%>"  readonly>
              <input type="text" size="2" maxlength="2" name="E30DEAMD3" value="<%= lnGenTran.getE30DEAMD3().trim()%>"  readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">INTERESES PREPAGADOS :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30TRNINT" value="<%= lnGenTran.getE30TRNINT().trim()%>"  readonly>
            </td>
            <td nowrap colspan="2"> 
              <div align="right">TÉRMINO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="5" maxlength="5" name="E30DEATRM" value="<%= lnGenTran.getE30DEATRM().trim()%>"  readonly>
              <select name="E30DEATRC" disabled>
                            <OPTION value=" " <% if (lnGenTran.getE30DEATRC().trim().equals(""))  out.print("selected"); %>=""></OPTION>
                            <OPTION value="D" <% if (lnGenTran.getE30DEATRC().trim().equals("D"))  out.print("selected"); %>="">Día(s)</OPTION>
                            <OPTION value="M" <% if (lnGenTran.getE30DEATRC().trim().equals("M")) out.print("selected"); %>="">Mes(es)</OPTION>
                            <OPTION value="Y" <% if (lnGenTran.getE30DEATRC().trim().equals("Y")) out.print("selected"); %>="">Año(s)</OPTION>
                        </select>
            </td>
          </tr>
          <%
		   if(!lnGenTran.getE30PDSC01().trim().equals("")){
		 %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC01().trim()%> : </div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT01" value="<%= lnGenTran.getE30PAMT01().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC01().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC01().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC01().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC01().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
          </tr>
          <%
		   }
		  %> <%
		   if(!lnGenTran.getE30PDSC02().trim().equals("")){
		 %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC02().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT02" value="<%= lnGenTran.getE30PAMT02().trim()%>" readonly>
            </td>
            <td nowrap colspan="2">
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC02().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC02().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC02().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC02().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		   if(!lnGenTran.getE30PDSC03().trim().equals("")){
		 %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC03().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT03" value="<%= lnGenTran.getE30PAMT03().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC03().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC03().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC03().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC03().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		    if(!lnGenTran.getE30PDSC04().trim().equals("")){
	      %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC04().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT04" value="<%= lnGenTran.getE30PAMT04().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC04().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC04().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC04().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC04().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC05().trim().equals("")){
		    %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC05().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT05" value="<%= lnGenTran.getE30PAMT05().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC05().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC05().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC05().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC05().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC06().trim().equals("")){
		    %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC06().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT06" value="<%= lnGenTran.getE30PAMT06().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC06().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC06().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC06().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC06().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC07().trim().equals("")){
	   	 %> 
          <tr id="trdark"> 
            <td nowrap colspan="2" height="20"> 
              <div align="right"><%= lnGenTran.getE30PDSC07().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%" height="20"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT07" value="<%= lnGenTran.getE30PAMT07().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC07().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC07().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC07().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC07().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		      if(!lnGenTran.getE30PDSC08().trim().equals("")){
		    %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC08().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT08" value="<%= lnGenTran.getE30PAMT08().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC08().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC08().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC08().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC08().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC09().trim().equals("")){
		    %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC09().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT09" value="<%= lnGenTran.getE30PAMT09().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC09().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC09().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC09().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC09().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		      if(!lnGenTran.getE30PDSC10().trim().equals("")){
		    %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC10().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT10" value="<%= lnGenTran.getE30PAMT10().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC10().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC10().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC10().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC10().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC11().trim().equals("")){
		   %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC11().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT11" value="<%= lnGenTran.getE30PAMT11().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC11().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC11().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC11().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC11().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC12().trim().equals("")){
	   	 %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC12().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT12" value="<%= lnGenTran.getE30PAMT12().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC12().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC12().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC12().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC12().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
           }
          %> <%
		     if(!lnGenTran.getE30PDSC13().trim().equals("")){
		   %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC13().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT13" value="<%= lnGenTran.getE30PAMT13().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC13().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC13().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC13().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC13().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		    if(!lnGenTran.getE30PDSC14().trim().equals("")){
	      %> 
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC14().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT14" value="<%= lnGenTran.getE30PAMT14().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC14().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC14().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC14().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC14().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <%
          }
          %> <%
		     if(!lnGenTran.getE30PDSC15().trim().equals("")){
		   %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getE30PDSC15().trim()%> :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30PAMT15" value="<%= lnGenTran.getE30PAMT15().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            	<div align="right">
	            	<% if(lnGenTran.getE30PDSC15().equals("TIMBRE FISCAL")) { 
	            		out.print("CONDONAR TIMBRE FISCAL :");
					} else if (lnGenTran.getE30PDSC15().equals("COMISION FLAT")) { 
						out.print("CONDONAR COMISION FLAT :");	
					} %>
            	</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
            	<% if(lnGenTran.getE30PDSC15().equals("TIMBRE FISCAL")) { %>
                <input type="radio" name="E30CONTIM" value="1" <%if(lnGenTran.getE30CONTIM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONTIM" value="N" <%if(lnGenTran.getE30CONTIM().equals("N")) out.print("checked");%>>No 
				<% } else if (lnGenTran.getE30PDSC15().equals("COMISION FLAT")) { %>
                <input type="radio" name="E30CONCOM" value="1" <%if(lnGenTran.getE30CONCOM().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E30CONCOM" value="N" <%if(lnGenTran.getE30CONCOM().equals("N")) out.print("checked");%>>No 
				<% } %>
            </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2">&nbsp;</td>
            <td nowrap colspan="3" width="56%">&nbsp;</td>
            <td nowrap colspan="2"> 
            </td>
            <td nowrap colspan="3" width="56%"> 
            </td>
            
          </tr>
          <%
		     }
		    %> 
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">TOTAL DESEMBOLSO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" size="15" maxlength="13" name="E30TRNTOT" value="<%= lnGenTran.getE30TRNTOT().trim()%>" readonly>
            </td>
            <td nowrap colspan="2"> 
            </td>
            <td nowrap colspan="3" width="56%"> 
            </td>
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> 
    <h4>Cuenta de Desembolso</h4>
  </h4>
<TABLE id="mainTable" class="tableinfo">
  <TR><TD>
  
   <table id="headTable" >
    <tr id="trdark"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco</td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%>			 
          <tr id="trclear">
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="text" name="E30OFFOP<%= name %>" value="<%= lnGenTran.getField("E30OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E30OFFGL<%= name %>" value="<%= lnGenTran.getField("E30OFFGL"+name).getString().trim()%>">
                <input type="text" name="E30OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= lnGenTran.getField("E30OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E30DEABNK.value,'','E30OFFGL<%= name %>','E30OFFOP<%= name %>','10')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E30OFFBK<%= name %>" size="2" maxlength="2" value="<%= lnGenTran.getField("E30OFFBK"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E30OFFBR<%= name %>" size="3" maxlength="3" value="<%= lnGenTran.getField("E30OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E30DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                <input type="text" name="E30OFFCY<%= name %>" size="3" maxlength="3" value="<%= lnGenTran.getField("E30OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E30DEABNK.value,'','','','')">
               </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E30OFFAC<%= name %>" size="12" maxlength="12"  value="<%= lnGenTran.getField("E30OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E30DEABNK.value,'',document.forms[0].E30DEACUN.value,'','RT')">
               </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                <input type="text" name="E30OFFAM<%= name %>" size="15" maxlength="15"  value="<%= lnGenTran.getField("E30OFFAM"+name).getString().trim()%>">
              </div>
            </td>
          </tr>
          
    		<%
    		}
    		%>
    	</table>
        </div>
        
          <table id="footTable" > 
          <tr id="trdark"> 
            <td nowrap align="right"><b>Equivalente Moneda del Préstamo : </b>
            </td>
            <td nowrap align="center"><b><i><strong> 
                <input type="text" name="E30OFFEQV" size="15" maxlength="15" readonly value="<%= lnGenTran.getE30OFFEQV().trim()%>">
                </strong></i></b>
            </td>
          </tr>
        </table>
        
        </TD>  
</TR>	
</TABLE>    
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize; 
  </SCRIPT>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  </form>
</body>
</html>
