<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Consulta de Situaci&oacute;n de Cr&eacute;ditos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPOLevel" class= "datapro.eibs.beans.UserPos"  		scope="session"/>
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.ECIF09004Message"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
function goAction() {
    document.forms[0].submit();	
}

</SCRIPT>

</HEAD>
<BODY>

<% 
if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
    
} 
%>

<h3 align="center">Consulta de Situaci&oacute;n de Cr&eacute;ditos
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_loans_sts.jsp,ECIF090">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF090" >
<input type=hidden name="SCREEN" value="200"> 

 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0">
 <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Banco:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E04LNSBNK" size="3" maxlength="3" value="<%= msgMT.getE04LNSBNK() %>" readonly> 
				<input type="text" name="E04LNSBNN" size="35" maxlength="35" value="<%= msgMT.getE04LNSBNN() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Ejecutivo:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E04LNSOFC" size="4" maxlength="4" value="<%= msgMT.getE04LNSOFC() %>" readonly> 
				<input type="text" name="E04LNSOFN" size="35" maxlength="35" value="<%= msgMT.getE04LNSOFN() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Año - Mes:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E04LNSRDY" size="5" maxlength="4" value="<%= msgMT.getE04LNSRDY() %>" readonly> 
				<input type="text" name="E04LNSRDM" size="3" maxlength="2" value="<%= msgMT.getE04LNSRDM() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>     
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top ALIGN=CENTER>
    <h4> Estado de la Cartera </h4>
    
  	 <TABLE id="dataTable" width="100%">
        
        
        
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER >Clasificaci&oacute;n</TH>
    		<TH ALIGN=CENTER >Vista Propia</TH>
    		<TH ALIGN=CENTER >Vista Global</TH>
    	</TR>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Sobregiros.... : </div>
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSSOB" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSSOB()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1SOB" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1SOB()%>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Cartera Vigente.... : </div>
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSVIG" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSVIG()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1VIG" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1VIG()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Cartera Vencida.... : </div>
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSVEN" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSVEN()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1VEN" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1VEN()%>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Cartera Demorada.... : </div>
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSDEM" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSDEM()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1DEM" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1DEM()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Cartera en Litigio.... : </div>
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSLIT" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSLIT()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1LIT" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1LIT()%>">
	      </td>
	      
     	</tr>
     	    
     </TABLE>
    </TD>
   </TR>	
</TABLE>
     
     
     <br>
<TABLE  id="mainTable1" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top ALIGN=CENTER>
    <h4> Estado de las Garant&iacute;as </h4>
    
  	 <TABLE id="dataTable1" width="100%">
        
        
        
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER >Tipo de Garant&iacute;a</TH>
    		<TH ALIGN=CENTER >Monto Vista Propia</TH>
    		<TH ALIGN=CENTER >Monto Vista Global</TH>
    	</TR>
     	<tr id=trclear> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA1" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA1()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA1" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA1()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA1" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA1()%>">
	      </td>
	    </tr>
     	<tr id=trdark> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA2" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA2()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA2" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA2()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA2" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA2()%>">
	      </td>
	    </tr>
     	<tr id=trclear> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA3" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA3()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA3" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA3()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA3" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA3()%>">
	      </td>
	    </tr>
     	<tr id=trdark> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA4" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA4()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA4" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA4()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA4" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA4()%>">
	      </td>
	    </tr>
     	<tr id=trclear> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA5" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA5()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA5" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA5()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA5" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA5()%>">
	      </td>
	    </tr>
     	<tr id=trdark> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA6" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA6()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA6" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA6()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA6" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA6()%>">
	      </td>
	    </tr>
     	<tr id=trclear> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA7" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA7()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA7" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA7()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA7" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA7()%>">
	      </td>
	    </tr>
     	<tr id=trdark> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA8" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA8()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA8" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA8()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA8" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA8()%>">
	      </td>
	    </tr>
     	<tr id=trclear> 
	      <td nowrap> 
	      <input type="text" name="E04LNSGA9" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSGA9()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSMA9" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSMA9()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1MA9" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1MA9()%>">
	      </td>
	    </tr>
     	<tr id=trdark> 
	      <td nowrap> 
	      <input type="text" name="E04LNSG10" size="30" maxlength="30" readonly value="<%= msgMT.getE04LNSG10()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LNSM10" size="20" maxlength="20" readonly value="<%= msgMT.getE04LNSM10()%>">
	      </td>
	      <td nowrap> 
	      <input type="text" name="E04LN1M10" size="20" maxlength="20" readonly value="<%= msgMT.getE04LN1M10()%>">
	      </td>
	    </tr>
	    <tr id=trclear>
	    </tr>
     	    
     </TABLE>
     
    </TD>
   </TR>	
</TABLE>

<p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Regresar" >
</p>
</FORM>
</BODY>
</HTML>
