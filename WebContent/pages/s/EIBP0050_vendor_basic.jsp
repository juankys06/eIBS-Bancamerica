<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<title>Proovedores de Internet</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="vendor" class="datapro.eibs.beans.EIBP005001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>
<body bgcolor="#FFFFFF">

<h3 align="center">Consulta de Proovedores de Internet
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="vendor_basic, EIBP0050" ></h3>
<hr size="4">

<FORM name="form" METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEIBP0050" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
	
	<table  class="tableinfo">
    	<tr > 
      		<td nowrap> 
        		<table cellspacing="0" cellpadding="2" width="100%" border="0">
          			<tr id="trclear"> 
            			<td nowrap><div align="right">Número de Cuenta :</div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01INAACC" size="15" maxlength="10" value="<%= vendor.getE01INAACC().trim()%>" readonly="readonly">
             			</td>  
          			</tr>
          			<tr id="trdark"> 
            			<td nowrap><div align="right">Nombre del Cliente :</div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01CUSSHN" size="40" maxlength="35" value="<%= vendor.getE01CUSSHN().trim()%>" readonly="readonly">
             			</td>  
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap><div align="right">Número del Proveedor :</div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01INAVNO" size="40" maxlength="35" value="<%= vendor.getE01INAVNO().trim()%>" readonly="readonly"> 
            			</td>
          			</tr>
          			<tr id="trdark"> 
            			<td nowrap><div align="right">Nombre Proveedor :</div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01INVNM1" size="40" maxlength="35" value="<%= vendor.getE01INVNM1().trim()%>" readonly="readonly"> 
            			</td>
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap><div align="right">Monto : </div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01INAIAM" size="40" maxlength="35" value="<%= vendor.getE01INAIAM().trim()%>" readonly="readonly">
            			</td>
          			</tr>
           			<tr id="trdark"> 
           				<td nowrap><div align="right">Saldo Antes del Pago :</div></td>
            			<td nowrap > 
              				<input type="text" name="E01BALBEF" size="22" maxlength="20" value="<%= vendor.getE01BALBEF().trim()%>" readonly="readonly">
            			</td>            
          			</tr>        
          			<tr id="trclear"> 
           				<td nowrap><div align="right">Saldo Después del Pago :</div></td>
            			<td nowrap > 
              				<input type="text" name="E01BALAFT" size="22" maxlength="20" value="<%= vendor.getE01BALAFT().trim()%>" readonly="readonly">
            			</td>            
          			</tr>        
          			<tr id="trdark"> 
            			<td nowrap><div align="right">Número de Referencia :</div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01INAREF" size="40" maxlength="35" value="<%= vendor.getE01INAREF().trim()%>" readonly="readonly">
            			</td>
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap><div align="right">Comentarios :</div></td>
            			<td nowrap> 
              				<input type="text" name="E01INARMK" size="11" maxlength="10" value="<%= vendor.getE01INARMK().trim()%>" readonly="readonly">
            			</td>
          			</tr>        
           			<tr id="trdark"> 
           				<td nowrap><div align="right">Fecha del Pago :</div></td>
            			<td nowrap > 
              				<input type="text" name="E01INAPDD" size="4" maxlength="2" value="<%= vendor.getE01INAPDD().trim()%>" readonly="readonly">
              				<input type="text" name="E01INAPDM" size="4" maxlength="2" value="<%= vendor.getE01INAPDM().trim()%>" readonly="readonly">
              				<input type="text" name="E01INAPDY" size="4" maxlength="2" value="<%= vendor.getE01INAPDY().trim()%>" readonly="readonly">
            			</td>            
          			</tr>        
          			<tr id="trclear"> 
            			<td nowrap><div align="right">Frecuencia del Pago :</div></td>
            			<td nowrap  > 
              				<input type="text" name="E01INAPFR" size="10" maxlength="10" value="<%= vendor.getE01INAPFR().trim()%>" readonly="readonly">
              				<input type="text" name="E01INADAY" size="10" maxlength="10" value="<%= vendor.getE01INADAY().trim()%>" readonly="readonly">
            			</td>
          			</tr>        
          			<tr id="trdark"> 
            			<td nowrap><div align="right">Número de Pagos :</div></td>
            			<td nowrap colspan="3"> 
              				<input type="text" name="E01INATOT" size="6" maxlength="3" value="<%= vendor.getE01INATOT().trim()%>" readonly="readonly">
            			</td>
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap><div align="right">Pagos Realizados :</div></td>
            			<td nowrap  > 
              				<input type="text" name="E01INAPMD" size="10" maxlength="10" value="<%= vendor.getE01INAPMD().trim()%>" readonly="readonly">
            			</td>
          			</tr>        
        		</table>
      		</td>
    	</tr>
  	</table>
 
</form>

</body>
</html>

