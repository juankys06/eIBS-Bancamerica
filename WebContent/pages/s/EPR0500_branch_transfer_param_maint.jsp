<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Parámetros de Transferencias por Agencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "msgPart" class="datapro.eibs.beans.EPR050001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }

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
%> 
<h3 align="center">Parámetros de Transferencias por Agencia<br><% if (userPO.getPurpose().equals("NEW")) { out.print("Nuevo"); } else { out.print("Mantenimiento"); } %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_transfer_param_maint.jsp, EPR0500"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEPR0500">
 
    <input type=HIDDEN name="SCREEN" value="500">

  <div id="OtherOpt">
    <table class="tableinfo"></table>
 <table class="tableinfo">
 <tr > 
      <td > 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
          	<tr id="trdark"> 
			    <td nowrap width="50%"> 
                	<div align="right">Banco : </div>
                </td>
                <td nowrap> 
                	<div align="left">
                  		<input type="text" name="E01PRPBAN" size="3" maxlength="2" value="<% if (userPO.getPurpose().equals("NEW")) { out.print(currUser.getE01UBK()); } else { out.print(msgPart.getE01PRPBAN()); } %>" readonly>
                	</div>
              	</td>
            </tr>
            <tr id="trclear"> 
				<TD nowrap width="50%">
					<div align="right">Agencia : </div>
				</td>
				<TD nowrap>
					<div align="left">
              			<input type="text" name="E01PRPBRN" size="4" maxlength="3" value="<%= msgPart.getE01PRPBRN().trim()%>" onKeyPress="enterInteger()">
              			<a href="javascript:GetBranch('E01PRPBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" border="0" align="absbottom" ></a>
			        </div>
			    </TD>
			</tr>
			<tr id="trdark"> 
			    <td nowrap width="50%"> 
                	<div align="right">Monto (Persona Jurídica) :</div>
                </td>
                <td nowrap>
                	<div align="left">
                  		<input type="text" name="E01PRPAMT" size="18" maxlength="17" value="<%= msgPart.getE01PRPAMT().trim()%>" onKeyPress="enterDecimal()">                  	
                	</div>
              	</td>
            </tr>            
            <tr id="trclear"> 
              	<td nowrap width="50%"> 
                	<div align="right">Máximo Días para Autorización (Persona Jurídica) :</div>
              	</td>
              	<td nowrap> 
                	<div align="left"> 
	                	<input type="text" name="E01PRPDYS" size="5" maxlength="4" value="<%= msgPart.getE01PRPDYS().trim()%>" onKeyPress="enterInteger()">
                	</div>         	         
				</td>                 
            </tr>
            <tr id="trdark"> 
              	<td nowrap width="50%"> 
                	<div align="right">Monto (Persona Natural) :</div>
              	</td>
              	<td nowrap> 
                	<div align="left">
                  		<input type="text" name="E01PRPAM1" size="18" maxlength="17" value="<%= msgPart.getE01PRPAM1().trim()%>" onKeyPress="enterDecimal()">                  	
                	</div>
              	</td>
            </tr>   
            <tr id="trclear"> 
              	<td nowrap width="50%"> 
                	<div align="right">Máximo Días para Autorización (Persona Natural) :</div>
              	</td>
              	<td nowrap> 
                	<div align="left"> 
	                	<input type="text" name="E01PRPDY1" size="5" maxlength="4" value="<%= msgPart.getE01PRPDY1().trim()%>" onKeyPress="enterInteger()">
                	</div>
              	</td>
            </tr>                       
	</table>
	</td>
   </tr>
</table>
</div>

  
<p align="center"> 
    <input id="EIBSBTN" type=SUBMIT name="Submit" value="Enviar" >
  </p>

</form>
  

</body>
</html>
