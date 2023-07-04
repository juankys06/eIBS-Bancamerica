<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transacciones Financieras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="prMant" class="datapro.eibs.beans.EPR012001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser"      class= "datapro.eibs.beans.ESS0030DSMessage" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="Javascript">

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
  <%String lectura =" ";
  	String lectura2=" "; 
  	boolean mostrar=true;
 %>

<h3 align="center">Transacciones Financieras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_enter.jsp,EPR1080"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1080">
 
    <input type=HIDDEN name="SCREEN" value="1">  
    <div id="DivHead">
    
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
 		    
            <td nowrap > 
                  <div align="right">Tipo de Transferencia :</div>
            </td>
              <td nowrap align="left">          
                <select name="TYP">
                  <option value=" " <% if(prMant.getE01PRITYP().equals(" ")){ out.print("selected");} %> ></option>
                  <option value="B" <% if(prMant.getE01PRITYP().equals("B")){ out.print("selected");} %> >Transferencia Interna</option>
                  <option value="I" <% if(prMant.getE01PRITYP().equals("I")){ out.print("selected");} %> >Transferencia Externa Recibida </option>
                  <option value="O" <% if(prMant.getE01PRITYP().equals("O")){ out.print("selected");} %> >Transferencia Externa Enviada </option>
                </select>
              </td>
          </tr>
          <TR>
          		<TD></TD>
            	<td nowrap > 
                  <div align="right"></div>
            	</td>
                <td nowrap>
                </td>
          	</TR>
        </table>
      </td>
    </tr>
   </table>          
 </div>
 
<p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
        <p>&nbsp;</p>
  <p><BR>
</p>
  <p align="center">&nbsp; </p>


</form>
  
</body>
</html>
