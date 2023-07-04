<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html> 
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Mantenimiento Datos de Identificacion</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.ECU000002Message msgMT = (datapro.eibs.beans.ECU000002Message) mtList.getRecord();
   userPO.setHeader16(msgMT.getE02CUSCUN());
   userPO.setHeader17(msgMT.getE02CUSNA1());
   userPO.setHeader19(msgMT.getE02CUSREN());
   userPO.setHeader3(msgMT.getE02CUFFIR());
   userPO.setHeader4(request.getParameter("ROW"));
%>

<%
 if (msgMT.getE02CUFFIR().equals("Y")) {
%>
<SCRIPT Language="Javascript">
       builtNewMenu(bastanteo_menu_1_1);
</SCRIPT>
<% } else { %>
<SCRIPT Language="Javascript">
       builtNewMenu(bastanteo_menu_1);
</SCRIPT>
<%}%>
 
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 	 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

<h3 align="center">Sistema de Bastanteo - Mantenimiento Datos de Identificaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_customers_maint, ECU0000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
  <h4>Datos de la Empresa</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr> 
            <td nowrap width="39%"> 
              <div align="right">C&oacute;digo de Cliente :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSCUN" size="10" maxlength="9" readonly value="<%= msgMT.getE02CUSCUN().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="39%"> 
              <div align="right">Nombre o Denominaci&oacute;n Social :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSNA1" size="52" maxlength="45" value="<%= msgMT.getE02CUSNA1().trim()%>">
            </td>
            
          </tr>
          <tr>
          <td nowrap width="39%"> 
              <div align="right"> </div>
            </td>
          <td nowrap colspan="2"> 
              <input type="text" name="E02CUSCP1" size="37" maxlength="30" value="<%= msgMT.getE02CUSCP1().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="39%"> 
              <p align="right">Siglas :</p>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSLN3" size="31" maxlength="30" value="<%= msgMT.getE02CUSLN3().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de Identificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSIDN" size="16" maxlength="15" value="<%= msgMT.getE02CUSIDN().trim()%>">
            </td>
          </tr>
          <tr>  
            <td nowrap width="39%"> 
              <div align="right">Tipo de Identificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2"> 
            <input type="text" name="E02CUSTID" size="4" maxlength="4" value="<%= msgMT.getE02CUSTID().trim()%>">
			<a href="javascript:GetCodeDescCNOFC('E02CUSTID','E02CUSTIN','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            <input type="text" name="E02CUSTIN" size="32" maxlength="35" readonly value="<%= msgMT.getE02CUSTIN().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="39%"> 
              <div align="right">Pa&iacute;s de Identificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2"> 
            <input type="text" name="E02CUSPID" size="4" maxlength="4" value="<%= msgMT.getE02CUSPID().trim()%>">
			<a href="javascript:GetCodeDescCNOFC('E02CUSPID','E02CUSPIN','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
              <input type="text" name="E02CUSPIN" size="35" maxlength="35" readonly value="<%= msgMT.getE02CUSPIN().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="39%"> 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="hidden" name="E02UC7" size="5" maxlength="4" value=" ">
              <input type="text" name="E02CUSCTY" size="32" maxlength="30" value="<%= msgMT.getE02CUSCTY().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC7','E02CUSCTY','84')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr> 
            <td nowrap width="39%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap colspan="2"> 
			 <input type="text" name="E02CUSSTE" size="5" maxlength="4" value="<%= msgMT.getE02CUSSTE().trim()%>" readonly>
			 <a href="javascript:GetCodeDescCNOFC('E02CUSSTE','D02CUSSTE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
			 <input type="text" name="D02CUSSTE" size="32" maxlength="30" value="<%= msgMT.getD02CUSSTE().trim()%>" readonly>
            </td>
          </tr>





         </table>
        </td>
      </tr>
    </table>
    
  <h4>Situaci&oacute;n Legal</h4> 
  <table class="tableinfo">
      <tr > 
        <td nowrap > 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap  width="25%"> 
                <div align="right">Pendiente Documentación Legal:</div>
              </td>
              <td nowrap  width="25%">  
                <input type="radio" name="E02CUFLGL" value="S" <%if (msgMT.getE02CUFLGL().equals("S")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E02CUFLGL" value="N" <%if (msgMT.getE02CUFLGL().equals("N")) out.print("checked"); %>>
                No 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"></td>
              <td nowrap  width="25%"> 
                <div align="right">Es Firma Personal:</div>
              </td>
              <td nowrap  width="25%">  
                <input type="radio" name="E02CUFFIR" value="Y" <%if (msgMT.getE02CUFFIR().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E02CUFFIR" value="N" <%if (!msgMT.getE02CUFFIR().equals("Y")) out.print("checked"); %>>
                No 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"></td>
                
            </tr>
            <tr id="teclear"> 
              
                <td nowrap  width="25%"> 
                <div align="right">Tiene Reformas:</div>
              </td>
              <td nowrap  width="25%">  
                <input type="text" name="E02CUFRFM" value="<%if (msgMT.getE02CUFRFM().equals("Y")) {out.print("SI");} else {out.print("NO");} %>" readonly size="7">
              <td nowrap width="25%"> 
                <div align="right">Fecha de Vencimiento:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUSMD1" size="2" maxlength="2" value="<%= msgMT.getE02CUSMD1().trim()%>">
                <input type="text" name="E02CUSMD2" size="2" maxlength="2" value="<%= msgMT.getE02CUSMD2().trim()%>">
                <input type="text" name="E02CUSMD3" size="2" maxlength="2" value="<%= msgMT.getE02CUSMD3().trim()%>">
                <a href="javascript:DatePicker(document.forms[0].E02CUSMD1,document.forms[0].E02CUSMD2,document.forms[0].E02CUSMD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
                </td>
            </tr>
            <tr id="trdark"> 
            <td nowrap  width="25%"> 
              <div align="right">Forma de Administraci&oacute;n:</div>
            </td>
            <td nowrap  width="25%"> 
              <input type="text" name="E02CUFADM" size="5" maxlength="4" value="<%= msgMT.getE02CUFADM().trim()%>">
              <input type="text" name="E02CUFADN" size="35" maxlength="35" value="<%= msgMT.getE02CUFADN() %>" readonly >
      	      <a href="javascript:GetCodeDescCNOFC('E02CUFADM','E02CUFADN','YR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
                <div align="right">Duraci&oacute;n en Años:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFTER" size="6" maxlength="5" value="<%= msgMT.getE02CUFTER().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap  width="25%"> 
              	<div align="right">Abogado Revisor:</div>
              </td>
              <td nowrap  width="25%"> 
              	<input type="text" name="E02CUFABO" size="5" maxlength="4" value="<%= msgMT.getE02CUFABO().trim()%>">
              	<input type="text" name="E02CUFABN" size="35" maxlength="35" value="<%= msgMT.getE02CUFABN() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02CUFABO','E02CUFABN','YQ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
              </td>
              <td nowrap  width="25%"> 
                <div align="right">Administración Vencida:</div>
              </td>
              <td nowrap  width="25%">  
                <input type="radio" name="E02CUFINT" value="Y" <%if (msgMT.getE02CUFINT().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E02CUFINT" value="N" <%if (!msgMT.getE02CUFINT().equals("Y")) out.print("checked"); %>>
                No 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
              </td>
            </tr>
         </table>
       </td>
     </tr>
  </table>
    
    <h4>Datos del Registro</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap > 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap  width="25%"> 
                <div align="right">N&uacute;mero del Registro Mercantil:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFMPA" size="3" maxlength="2" value="<%= msgMT.getE02CUFMPA().trim()%>">
                </td>
                <td nowrap  width="25%"> 
                <div align="right">Circunscripci&oacute;n Judicial:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFCIR" size="40" maxlength="35" value="<%= msgMT.getE02CUFCIR().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="25%"> 
                <div align="right">N&uacute;mero de Documento:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFNDO" size="10" maxlength="7" value="<%= msgMT.getE02CUFNDO().trim()%>">
                </td>
              <td nowrap width="25%"> 
                <div align="right">Tomo:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFTOM" size="10" maxlength="8" value="<%= msgMT.getE02CUFTOM().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="25%"> 
                <div align="right">Fecha de Registro:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUSRD1" size="2" maxlength="2" value="<%= msgMT.getE02CUSRD1().trim()%>">
                <input type="text" name="E02CUSRD2" size="2" maxlength="2" value="<%= msgMT.getE02CUSRD2().trim()%>">
                <input type="text" name="E02CUSRD3" size="2" maxlength="2" value="<%= msgMT.getE02CUSRD3().trim()%>">
                <a href="javascript:DatePicker(document.forms[0].E02CUSRD1,document.forms[0].E02CUSRD2,document.forms[0].E02CUSRD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
                </td>
                <td nowrap  width="25%"> 
                <div align="right">N&uacute;mero de Expediente</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUSREN" size="17" maxlength="15" value="<%= msgMT.getE02CUSREN().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap  width="25%"> 
              	<div align="right">Posee Publicaci&oacute;n:</div>
              </td>
              <td nowrap  width="25%">  
                <input type="radio" name="E02CUFFG2" value="Y" <%if (msgMT.getE02CUFFG2().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E02CUFFG2" value="N" <%if (!msgMT.getE02CUFFG2().equals("Y")) out.print("checked"); %>>
                No 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
              </td>
              <td nowrap  width="25%"> 
                <div align="right"></div>
              </td>
              <td nowrap  width="25%">  
              </td>
            </tr>

          </table>
        </td>
      </tr>
    </table>
 <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB1" size="90" maxlength="80" value="<%= msgMT.getE02CUSOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB2" size="90" maxlength="80" value="<%= msgMT.getE02CUSOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB3" size="90" maxlength="80" value="<%= msgMT.getE02CUSOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB4" size="90" maxlength="80" value="<%= msgMT.getE02CUSOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB5" size="90" maxlength="80" value="<%= msgMT.getE02CUSOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table> 

<p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>

</form>
</body>
</html>
