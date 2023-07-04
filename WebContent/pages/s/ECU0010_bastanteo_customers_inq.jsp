<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html> 
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Consulta Vencimientos Sistema de Bastanteo</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.ECU001001Message msgMT = (datapro.eibs.beans.ECU001001Message) mtList.getRecord();
%>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
 
</head>

<body bgcolor="#FFFFFF">

<h3 align="center">Sistema de Bastanteo - Consulta de Vencimientos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_customers_inq, ECU0010" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0010">
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
              <input type="text" name="E01CUSCUN" size="10" maxlength="9" readonly value="<%= msgMT.getE01CUSCUN().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right">Nombre o Denominaci&oacute;n Social :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01CUSNA1" size="46" maxlength="45" readonly value="<%= msgMT.getE01CUSNA1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <p align="right">Siglas :</p>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01CUSLN3" size="31" maxlength="30" readonly value="<%= msgMT.getE01CUSLN3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de R.I.F. :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01CUSID4" size="16" maxlength="15" readonly value="<%= msgMT.getE01CUSID4().trim()%>">
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
                   <input type="text" readonly name="E01CUFLGL" size="3" maxlength="2"
				   value="<% if (msgMT.getE01CUFLGL().equals("Y")) { out.print("Si"); }
					else if (msgMT.getE01CUFLGL().equals("N")) { out.print("No"); }
					else { out.print(""); } %>" >
                </p>
              </td>
              <td nowrap  width="25%"> 
                <div align="right">Es Firma Personal:</div>
              </td>
              <td nowrap  width="25%">
                <p> 
                   <input type="text" readonly name="E01CUFFIR" size="3" maxlength="2"
				   value="<% if (msgMT.getE01CUFFIR().equals("Y")) { out.print("Si"); }
					else if (msgMT.getE01CUFFIR().equals("N")) { out.print("No"); }
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
                   <input type="text" readonly name="E01CUFRFM" size="3" maxlength="2"
				   value="<% if (msgMT.getE01CUFRFM().equals("Y")) { out.print("Si"); }
					else if (msgMT.getE01CUFRFM().equals("N")) { out.print("No"); }
					else { out.print(""); } %>" >
                </p>
              </td>
              <td nowrap width="25%"> 
                <div align="right">Fecha de Vencimiento:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUSMD1" size="2" maxlength="2"  readonly value="<%= msgMT.getE01CUSMD1().trim()%>">
                <input type="text" name="E01CUSMD2" size="2" maxlength="2"  readonly value="<%= msgMT.getE01CUSMD2().trim()%>">
                <input type="text" name="E01CUSMD3" size="2" maxlength="2"  readonly value="<%= msgMT.getE01CUSMD3().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
            <td nowrap  width="25%"> 
              <div align="right">Forma de Administraci&oacute;n:</div>
            </td>
            <td nowrap  width="25%"> 
              <input type="text" name="E01CUFADM" size="5" maxlength="4"  readonly value="<%= msgMT.getE01CUFADM().trim()%>">
              <input type="text" name="E01CUFADN" size="40" maxlength="35"  readonly value="<%= msgMT.getE01CUFADN().trim()%>"> 
            </td>
            <td nowrap width="25%"> 
                <div align="right">Duraci&oacute;n en Años:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUFTER" size="6" maxlength="5" readonly value="<%= msgMT.getE01CUFTER().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap  width="25%"> 
                <div align="right">Administración Vencida:</div>
              </td>
              <td nowrap  width="25%">
                <p> 
                   <input type="text" readonly name="E01CUFINT" size="3" maxlength="2"
				   value="<% if (msgMT.getE01CUFINT().equals("Y")) { out.print("Si"); }
					else if (msgMT.getE01CUFINT().equals("N")) { out.print("No"); }
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
                <input type="text" name="E01CUFMPA" size="3" maxlength="2"  readonly value="<%= msgMT.getE01CUFMPA().trim()%>">
                </td>
                <td nowrap  width="25%"> 
                <div align="right">Circunscripci&oacute;n Judicial:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUFCIR" size="40" maxlength="35"  readonly value="<%= msgMT.getE01CUFCIR().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="25%"> 
                <div align="right">N&uacute;mero de Documento:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUFNDO" size="10" maxlength="7"  readonly value="<%= msgMT.getE01CUFNDO().trim()%>">
                </td>
              <td nowrap width="25%"> 
                <div align="right">Tomo:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUFTOM" size="10" maxlength="8"  readonly value="<%= msgMT.getE01CUFTOM().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="25%"> 
                <div align="right">Fecha de Registro:</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUSRD1" size="2" maxlength="2"  readonly value="<%= msgMT.getE01CUSRD1().trim()%>">
                <input type="text" name="E01CUSRD2" size="2" maxlength="2"  readonly value="<%= msgMT.getE01CUSRD2().trim()%>">
                <input type="text" name="E01CUSRD3" size="2" maxlength="2"  readonly value="<%= msgMT.getE01CUSRD3().trim()%>">
                </td>
                <td nowrap  width="25%"> 
                <div align="right">N&uacute;mero de Expediente</div>
              </td>
              <td nowrap width="25%">  
                <input type="text" name="E01CUSREN" size="17" maxlength="15"  readonly value="<%= msgMT.getE01CUSREN().trim()%>">
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
              <input type="text" name="E01CUSOB1" size="90" maxlength="80" readonly value="<%= msgMT.getE01CUSOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E01CUSOB2" size="90" maxlength="80" readonly value="<%= msgMT.getE01CUSOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E01CUSOB3" size="90" maxlength="80" readonly value="<%= msgMT.getE01CUSOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E01CUSOB4" size="90" maxlength="80" readonly value="<%= msgMT.getE01CUSOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E01CUSOB5" size="90" maxlength="80" readonly value="<%= msgMT.getE01CUSOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table> 

</form>
</body>
</html>
