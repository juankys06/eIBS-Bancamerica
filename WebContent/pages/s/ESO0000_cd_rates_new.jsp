<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Negociación de Tasas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ESO000002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="JavaScript">
function validate(){
	if (document.forms[0].E02SOLRTE.value <= 0) {
		alert("La tasa no puede ser menor o igual a cero.");
		return false;
	}
	else if (document.forms[0].E02SOLPRI.value <= 0) {
		alert("El monto no puede ser menor o igual a cero.");
		return false;
	}
	else {
		return true;
	}
}
</SCRIPT>

</head>
<body>
<h3 align="center">Negociación de Tasas
<BR>Nueva
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_rates_new.jsp, ESO0000"> 
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESO0000" onSubmit="return validate();">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="7">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Cliente : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E02SOLCUN" size="11" maxlength="10" value="">
				<a href="javascript:GetCustomerDescId('E02SOLCUN', 'E02CUSNA1', '')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Nombre : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E02CUSNA1" size="46" maxlength="45" value="" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E02SOLRTE" size="12" maxlength="11" value="0">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E02SOLPRI" size="20" maxlength="19" value="0" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Plazo : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E02SOLTRM" size="4" maxlength="3" value="">
              	<select name="E02SOLTRC">
                  <option value=" "></option>
                  <option value="D">Día(s)</option>
                </select>
              </div>
            </td>
            <td nowrap width="25%" ></td>
            <td nowrap width="25%" ></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>
