<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Calificación de Clientes</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "clasif" class= "datapro.eibs.beans.EDL052101Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
 String title="";
 if ( userPO.getOption().equals("CLIENT_C") ) {
    title="Jurídico";
%>

   <SCRIPT Language="Javascript">
       builtNewMenu(client_corp_opt);       
   </SCRIPT>

<%
} else if ( userPO.getOption().equals("CLIENT_P") ) { 
  title="Personal";
%>
	<SCRIPT Language="Javascript">
       builtNewMenu(client_personal_opt);       
   </SCRIPT>
<%
}  
%> 
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getOption().equals("CLASIF") ) {
    out.println("<SCRIPT>initMenu();</SCRIPT>");
 }
 %> 
 
 <h3 align="center">Calificación de Cliente <%= title %>  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_clasif, EDL0521" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0521">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  	<table class="tableinfo">
      <tr > 
        <td nowrap>          
         <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Identificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly name="E01CUSIDN" size="10" maxlength="9" value="<%= clasif.getE01CUSIDN()%>">
            </td>
            <td nowrap> 
              <div align="right">No Cliente :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly name="E01CUSCUN" size="10" maxlength="9" value="<%= clasif.getE01CUSCUN()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" readonly name="E01CUSNA1" size="46" maxlength="45" value="<%= clasif.getE01CUSNA1().trim()%>">
            </td>
          </tr>          
         </table>
        </td>
      </tr>
    </table>
    
    <br>
	<table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="2" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="40%"></td>
              <td nowrap width="20%" align=center><b>Actual</b></td>
              <td nowrap width="40%" align=center><b>Nueva</b></td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Calificación :</B> </td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01CALSUB()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01CALSUB" size="2" maxlength="1" value="<%= clasif.getE01CALSUB().trim()%>">
              </td>
            </tr>
            <tr >
              <td nowrap id="trdark" align=right><B>Forma de Calificación :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01FRMCAL()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01FRMCAL" size="2" maxlength="1" value="<%= clasif.getE01FRMCAL().trim()%>">
                <a href="javascript:GetCode('E01FRMCAL','STATIC_client_help_qualification_type.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              </td>
            </tr>
            <tr >
              <td nowrap id="trdark" align=right><B>Tabla de Previsiones :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01TBLPRV()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01TBLPRV" size="3" maxlength="2" value="<%= clasif.getE01TBLPRV().trim()%>">
                <a href="javascript:GetPrevTable('E01TBLPRV')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
			  </td>
            </tr>
            <tr> 
              <td nowrap id="trdark" align=right><B>Clasificación Comercial :
              </B></td>
              <td nowrap > 
                <div align=left><%=clasif.getB01CALCOM()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01CALCOM" size="3" maxlength="2" value="<%= clasif.getE01CALCOM().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01CALCOM','16')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
			  </td>
            </tr>
            <tr >
              <td nowrap id="trdark" align=right><B>Fecha de Clasificación Comercial :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01COMDT1()%>/<%=clasif.getB01COMDT2()%>/<%=clasif.getB01COMDT3()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01COMDT1" size="3" maxlength="2" value="<%= clasif.getE01COMDT1().trim()%>">
                <input type="text" name="E01COMDT2" size="3" maxlength="2" value="<%= clasif.getE01COMDT2().trim()%>">
                <input type="text" name="E01COMDT3" size="3" maxlength="2" value="<%= clasif.getE01COMDT3().trim()%>">
              </td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Reclasificación SBIF :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01RECLAS()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01RECLAS" size="3" maxlength="2" value="<%= clasif.getE01RECLAS().trim()%>"> 
                <a href="javascript:GetCodeCNOFC('E01RECLAS','16')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
			  </td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Fecha Reclasificación SBIF :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01RECDT1()%>/<%=clasif.getB01RECDT2()%>/<%=clasif.getB01RECDT3()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01RECDT1" size="3" maxlength="2" value="<%= clasif.getE01RECDT1().trim()%>">
                <input type="text" name="E01RECDT2" size="3" maxlength="2" value="<%= clasif.getE01RECDT2().trim()%>">
                <input type="text" name="E01RECDT3" size="3" maxlength="2" value="<%= clasif.getE01RECDT3().trim()%>">
              </td>
            </tr>
            <tr>
  			  <td nowrap id="trdark" align=right><B>Vigilancia Especial :
              </B></td>
              <td nowrap > 
                <div align=left><%=clasif.getB01CALESP()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01CALESP" size="3" maxlength="2" value="<%= clasif.getE01CALESP().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01CALESP','16')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
			  </td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Fecha Actualización Vigilancia :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01ESPDT1()%>/<%=clasif.getB01ESPDT2()%>/<%=clasif.getB01ESPDT3()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01ESPDT1" size="3" maxlength="2" value="<%= clasif.getE01ESPDT1().trim()%>">
                <input type="text" name="E01ESPDT2" size="3" maxlength="2" value="<%= clasif.getE01ESPDT2().trim()%>">
                <input type="text" name="E01ESPDT3" size="3" maxlength="2" value="<%= clasif.getE01ESPDT3().trim()%>">
              </td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Clasif. Riesgo Deudor :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01CALRGO()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01CALRGO" size="3" maxlength="2" value="<%= clasif.getE01CALRGO().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01CALRGO','16')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
			  </td>
            </tr>
            <tr>
              
            <td nowrap id="trdark" align=right><B>Fecha Actulización Cr&eacute;dito 
              : </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01RGODT1()%>/<%=clasif.getB01RGODT2()%>/<%=clasif.getB01RGODT3()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01RGODT1" size="3" maxlength="2" value="<%= clasif.getE01RGODT1().trim()%>">
                <input type="text" name="E01RGODT2" size="3" maxlength="2" value="<%= clasif.getE01RGODT2().trim()%>">
                <input type="text" name="E01RGODT3" size="3" maxlength="2" value="<%= clasif.getE01RGODT3().trim()%>">
              </td>
            </tr>
            <tr>
              
            <td nowrap id="trdark" align=right><B>C&oacute;digo Usuario : </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01USRCOD()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01USRCOD" size="5" maxlength="4" value="<%= clasif.getE01USRCOD().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01USRCOD','21')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              </td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Nombre Usuario :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01USRNME()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01USRNME" size="31" maxlength="30" value="<%= clasif.getE01USRNME().trim()%>">
              </td>
            </tr>
            <tr>
              <td nowrap id="trdark" align=right><B>Fecha Ultima Actualización :
              </B></td> 
              <td nowrap > 
                <div align=left><%=clasif.getB01USRUD1()%>/<%=clasif.getB01USRUD2()%>/<%=clasif.getB01USRUD3()%></div>
              </td>
              <td nowrap >  
                <input type="text" name="E01USRUD1" size="3" maxlength="2" value="<%= clasif.getE01USRUD1().trim()%>">
                <input type="text" name="E01USRUD2" size="3" maxlength="2" value="<%= clasif.getE01USRUD2().trim()%>">
                <input type="text" name="E01USRUD3" size="3" maxlength="2" value="<%= clasif.getE01USRUD3().trim()%>">
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
<p>
  <center>
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </center>
</P>
</form>
</body>
</html>
