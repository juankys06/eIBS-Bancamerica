<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html> 
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Consulta Datos de Identificacion</title>
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
   userPO.setHeader3(msgMT.getE02CUFFIR());
   userPO.setHeader4(request.getParameter("ROW"));
%>

<%
 if (msgMT.getE02CUFFIR().equals("Y")) {
%>
<SCRIPT Language="Javascript">
       builtNewMenu(bastanteo_menu_inq_1_1);
</SCRIPT>
<% } else { %>
<SCRIPT Language="Javascript">
       builtNewMenu(bastanteo_menu_inq_1);
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

<h3 align="center">Sistema de Bastanteo - Consulta Datos de Identificaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_customers_inq, ECU0000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
  <h4>Datos de la Empresa</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">C&oacute;digo de Cliente :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSCUN" size="10" maxlength="9" readonly value="<%= msgMT.getE02CUSCUN().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right">Nombre o Denominaci&oacute;n Social :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSNA1" size="52" maxlength="45" readonly value="<%= msgMT.getE02CUSNA1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <p align="right">Siglas :</p>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSLN3" size="37" maxlength="30" readonly value="<%= msgMT.getE02CUSLN3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero/Tipo de Identificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSIDN" size="16" maxlength="15" readonly value="<%= msgMT.getE02CUSIDN().trim()%>">
              <input type="text" name="E02CUSTIN" size="35" maxlength="35" readonly value="<%= msgMT.getE02CUSTIN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSPIN" size="35" maxlength="35" readonly value="<%= msgMT.getE02CUSPIN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%"> 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CUSCTY" size="35" maxlength="35" readonly value="<%= msgMT.getE02CUSCTY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="D02CUSSTE" size="35" maxlength="35" readonly value="<%= msgMT.getD02CUSSTE().trim()%>">
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
                <p> 
                   <input type="text" readonly name="E02CUFLGL" size="3" maxlength="2"
				   value="<% if (msgMT.getE02CUFLGL().equals("Y")) { out.print("Si"); }
						else if (msgMT.getE02CUFLGL().equals("N")) { out.print("No"); }
						else { out.print(""); } %>" >
                </p>
              </td>
              <td nowrap  width="25%"> 
                <div align="right">Es Firma Personal:</div>
              </td>
              <td nowrap  width="25%">
                <p> 
                   <input type="text" readonly name="E02CUFFIR" size="3" maxlength="2"
				   value="<% if (msgMT.getE02CUFFIR().equals("Y")) { out.print("Si"); }
						else if (msgMT.getE02CUFFIR().equals("N")) { out.print("No"); }
						else { out.print(""); } %>" >
                </p>
              </td>
            </tr>
            <tr id="teclear"> 
              
                <td nowrap  width="25%"> 
                <div align="right">Tiene Reformas:</div>
              </td>
              <td nowrap  width="25%">
                <p> 
                   <input type="text" readonly name="E02CUFRFM" size="3" maxlength="2"
				   value="<% if (msgMT.getE02CUFRFM().equals("Y")) { out.print("Si"); }
						else if (msgMT.getE02CUFRFM().equals("N")) { out.print("No"); }
						else { out.print(""); } %>" >
                </p>
              </td>
              <td nowrap width="25%"> 
                <div align="right">Fecha de Vencimiento:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUSMD1" size="2" maxlength="2"  readonly value="<%= msgMT.getE02CUSMD1().trim()%>">
                <input type="text" name="E02CUSMD2" size="2" maxlength="2"  readonly value="<%= msgMT.getE02CUSMD2().trim()%>">
                <input type="text" name="E02CUSMD3" size="2" maxlength="2"  readonly value="<%= msgMT.getE02CUSMD3().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
            <td nowrap  width="25%"> 
              <div align="right">Forma de Administraci&oacute;n:</div>
            </td>
            <td nowrap  width="25%"> 
              <input type="text" name="E02CUFADM" size="5" maxlength="4"  readonly value="<%= msgMT.getE02CUFADM().trim()%>">
              <input type="text" name="E02CUFADN" size="40" maxlength="35"  readonly value="<%= msgMT.getE02CUFADN().trim()%>"> 
            </td>
            <td nowrap width="25%"> 
                <div align="right">Duraci&oacute;n en Años:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFTER" size="6" maxlength="5" readonly value="<%= msgMT.getE02CUFTER().trim()%>">
                </td>
            </tr>
            <tr id="teclear">
              <td nowrap  width="25%"> 
              	<div align="right">Abogado Revisor:</div>
              </td>
              <td nowrap  width="25%"> 
              	<input type="text" name="E02CUFABO" size="5" maxlength="4"  readonly value="<%= msgMT.getE02CUFABO().trim()%>">
              	<input type="text" name="E02CUFABN" size="40" maxlength="35"  readonly value="<%= msgMT.getE02CUFABN().trim()%>"> 
              </td> 
              <td nowrap  width="25%"> 
                <div align="right">Administración Vencida:</div>
              </td>
              <td nowrap  width="25%">
                <p> 
                   <input type="text" readonly name="E02CUFINT" size="3" maxlength="2"
				   value="<% if (msgMT.getE02CUFINT().equals("Y")) { out.print("Si"); }
						else if (msgMT.getE02CUFINT().equals("N")) { out.print("No"); }
						else { out.print(""); } %>" >
                </p>
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
                <input type="text" name="E02CUFMPA" size="3" maxlength="2"  readonly value="<%= msgMT.getE02CUFMPA().trim()%>">
                </td>
                <td nowrap  width="25%"> 
                <div align="right">Circunscripci&oacute;n Judicial:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFCIR" size="40" maxlength="35"  readonly value="<%= msgMT.getE02CUFCIR().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="25%"> 
                <div align="right">N&uacute;mero de Documento:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFNDO" size="10" maxlength="7"  readonly value="<%= msgMT.getE02CUFNDO().trim()%>">
                </td>
              <td nowrap width="25%"> 
                <div align="right">Tomo:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUFTOM" size="10" maxlength="8"  readonly value="<%= msgMT.getE02CUFTOM().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="25%"> 
                <div align="right">Fecha de Registro:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUSRD1" size="2" maxlength="2"  readonly value="<%= msgMT.getE02CUSRD1().trim()%>">
                <input type="text" name="E02CUSRD2" size="2" maxlength="2"  readonly value="<%= msgMT.getE02CUSRD2().trim()%>">
                <input type="text" name="E02CUSRD3" size="2" maxlength="2"  readonly value="<%= msgMT.getE02CUSRD3().trim()%>">
                </td>
                <td nowrap  width="25%"> 
                <div align="right">N&uacute;mero de Expediente</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E02CUSREN" size="17" maxlength="15"  readonly value="<%= msgMT.getE02CUSREN().trim()%>">
                </td>
            </tr>
            <tr id="teclear">
              <td nowrap  width="25%"> 
                <div align="right">Posee Publicaci&oacute;n:</div>
              </td>
              <td nowrap  width="25%">
                <p> 
                   <input type="text" readonly name="E02CUFFG2" size="3" maxlength="2"
				   value="<% if (msgMT.getE02CUFFG2().equals("Y")) { out.print("Si"); }
						else if (msgMT.getE02CUFFG2().equals("N")) { out.print("No"); }
						else { out.print(""); } %>" >
                </p>
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
              <input type="text" name="E02CUSOB1" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUSOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB2" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUSOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB3" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUSOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB4" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUSOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUSOB5" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUSOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table> 

</form>
</body>
</html>
