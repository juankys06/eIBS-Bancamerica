<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Comisiones de Tarjeta</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0005DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Comisiones de Tarjetas<BR>Consulta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_commissions_inq.jsp, ECD0005"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0005" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E05CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE05CDRNPL() %>">
				
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Comisión : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E05CDRFED" size="17" maxlength="15" readonly value="<%= msgCD.getE05CDRFED() %>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Período de Cobro de Comisión : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E05CDRPED" size="20" maxlength="15" readonly value="<%= msgCD.getE05CDRPED() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Factor para Cargos : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
	              <select name="E05CDRFFC" disabled>
	                <option value=" " <% if (!(
			                	msgCD.getE05CDRFFC().equals("F") || 
	                			msgCD.getE05CDRFFC().equals("P"))) 
	                			out.print("selected"); %> selected></option>
	                <option value="F" <% if(msgCD.getE05CDRFFC().equals("F")) out.print("selected");%>>Fijo</option>
	                <option value="P" <% if(msgCD.getE05CDRFFC().equals("P")) out.print("selected");%>>Porcentaje</option>
	              </select>
			  </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cargo Comisión : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E05CDRCHG" size="5" maxlength="4" readonly value="<%= msgCD.getE05CDRCHG() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Monto Comisión : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E05CDRAMT" size="20" maxlength="17" readonly value="<%= msgCD.getE05CDRAMT() %>" >
			  </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Cuenta a Acreditar : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E05CDRACC" size="14" maxlength="12" readonly value="<%= msgCD.getE05CDRACC() %>">
        		  
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Cuenta Contable a Acreditar : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E05CDRGLN" size="14" maxlength="12" readonly value="<%= msgCD.getE05CDRGLN() %>" >
        		  
              </div>
            </td>
          </tr> 
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  </form>
</body>
</html>
