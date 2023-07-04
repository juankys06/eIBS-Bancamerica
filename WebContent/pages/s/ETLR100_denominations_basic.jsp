<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Denominaciones de Moneda</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id="msgDen" class="datapro.eibs.beans.ETLR10001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%> 

<H3 align="center">Denominaciones de Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="denominations_basic.jsp, ETLR100"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSETLR100" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="4">
  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01TLDCCY" size="4" maxlength="3" value="<%= msgDen.getE01TLDCCY().trim()%>" readonly>
                <input type="text" name="E01RATDSC" size="40" maxlength="35" value="<%= msgDen.getE01RATDSC().trim()%>" readonly>
			</div>
            </td>
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
<BR>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="75%">
            	<INPUT type="text" name="E01TLDNME" size="9" maxlength="8" value="<%= msgDen.getE01TLDNME().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Valor :</div>
            </td>
            <td nowrap width="75%">
            	<INPUT type="text" name="E01TLDVLU" size="15" maxlength="12" value="<%= msgDen.getE01TLDVLU().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Paquete :</div>
            </td>
            <td nowrap width="75%">
            	<INPUT type="text" name="E01TLDPCK" size="10" maxlength="9" value="<%= msgDen.getE01TLDPCK().trim()%>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap width="75%">
            	<SELECT name="E01TLDTYP">
             	   <OPTION value="" <% if (!(msgDen.getE01TLDTYP().equals("B")||msgDen.getE01TLDTYP().equals("C"))) out.print("selected"); %>></OPTION>
                   <OPTION value="B" <% if (msgDen.getE01TLDTYP().equals("B")) out.print("selected"); %>>Billete</OPTION>
                   <OPTION value="C" <% if (msgDen.getE01TLDTYP().equals("C")) out.print("selected"); %>>Moneda</OPTION>
                </SELECT>
            </td>
          </tr>
        </table>
      </td>
    </tr>
</table>
<BR>


<p align="center"> 
     <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>
</form>

</body>
</html>
