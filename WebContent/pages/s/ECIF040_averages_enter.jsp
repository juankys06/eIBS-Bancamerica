<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1"> 
<TITLE>Conexión</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "average" class= "datapro.eibs.beans.ECIF04001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<body bgcolor="#FFFFFF">

 <% if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   
 %>

<h3 align="center">Consulta de Promedios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="average_enter, ECIF040" width="32" height="32"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF040" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter"  border=0  width=70% align="center" >
      <tr valign="middle"> 
        <td nowrap colspan="2" align="middle" >&nbsp;</td>
      </tr>
	  <tr height="100">
	  <td nowrap>&nbsp;</td></tr>
	  
      <tr valign="middle"> 
        <td nowrap width=40% align="right" > 
          <p>Ingrese el N&uacute;mero de Cuenta : 
        </td>
        <td nowrap width=40% align="left"> 
          <p> 
            <input type=TEXT name="E01ACCNUM" value="<%= average.getE01ACCNUM() %>" size=15 maxlength=9 onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01ACCNUM','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            &nbsp; 
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle" height="200"> 
         
 			<div align="center"> 
 				<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
 			</div>
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle">&nbsp;</td>
      </tr>
    </table>
  </CENTER>
 </FORM>
</BODY>
</HTML>
 