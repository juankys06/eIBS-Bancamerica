 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Transacciones Financieras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="msgPart" class="datapro.eibs.beans.EPR030201Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />






<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">
  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }

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

<h3 align="center"> Canal de Pago <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chanel_codes_basic.jsp, EPR0300"> </h3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR0300">
 
    <input type=HIDDEN name="SCREEN" value="15">

  <div id="OtherOpt">
<table class="tableinfo" width="570">
	<tr id="trdark">
		<td nowrap width="35%">
		<div align="right">Código de Canal :</div>
		</td>
		<td nowrap width="368"><input type="text" name="E01PRCCNL" size="1"
			maxlength="1" value="<%= msgPart.getE01PRCCNL().trim()%>"></td>
	</tr>
	<tr id="trclear">
		<td nowrap width="35%">
		<div align="right">Descripción :</div>
		</td>
		<TD nowrap width="368"><INPUT type="text" name="E01PRCDSC" size="30"
			maxlength="30" value="<%= msgPart.getE01PRCDSC().trim()%>"></TD>
	</tr>

	<tr id="trdark">
		<td nowrap width="35%">
		<div align="right">Código SWIFT :</div>
		</td>
		<td nowrap width="368"><input type="text" name="E01PRCCID" size="15"
			maxlength="15" value="<%= msgPart.getE01PRCCID().trim()%>"></td>
	</tr>
    <tr id="trclear">
		<td nowrap width="194">
		<div align="right">Habilitado? :</div>
		</td>
		<td nowrap width="65%">
		<div align="left">
		<input type="radio" name="E01PRCFL1" value="Y"
		<%if(msgPart.getE01PRCFL1().equals("Y") && msgPart.getE01PRCFL1().equals("Y")) out.print("checked");%> >Sí 
		<input type="radio" name="E01PRCFL1" value="N"
		<%if(msgPart.getE01PRCFL1().equals("N") && msgPart.getE01PRCFL1().equals("N")) out.print("checked");%> >No

		</div>
		</td>
	</tr>

</Table>
<H4><br>Parametros Mensajes Outgoing<br></H4>       
       <table class="tableinfo">
            <tr id="trdark"> 
              	<td nowrap  > 
                	<div align="right">Cuenta de Crédito (Liberación) :</div>
              	</td>
              	<td nowrap > 
                	<div align="left"> 
                  		<input type="text" name="E01PRCDBK" size="2" maxlength="2" value="<%= msgPart.getE01PRCDBK().trim()%>" >
                  		<input type="text" name="E01PRCDBR" size="4" maxlength="3"  value="<%= msgPart.getE01PRCDBR().trim()%>" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRCDBK.value,'','','','')"onkeypress="enterInteger()">
                		<input type="text" name="E01PRCDCY" size="3" maxlength="3" value="<%= msgPart.getE01PRCDCY().trim()%>"  oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRCDBK.value,'','','','')"> 
                		<input type="text" name="E01PRCDGL" size="17" maxlength="16" value="<%= msgPart.getE01PRCDGL().trim()%>" onkeypress="enterInteger()" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRCDBK.value,document.forms[0].E01PRCDCY.value,'','','')">
                	</div>
              	</td>
            </tr>   
            <tr id="trclear"> 
              	<td nowrap > 
                	<div align="right">Indicador de Contabilización :</div>
              	</td>
              	<td nowrap > 
					<div align="left"> 
              			<input type="radio" name="E01PRCACG" value="Y"
               			<%if(msgPart.getE01PRCACG().equals("Y") && msgPart.getE01PRCACG().equals("Y")) out.print("checked");%> >Sí 
              			<input type="radio" name="E01PRCACG" value="N"
              			<%if(msgPart.getE01PRCACG().equals("N") && msgPart.getE01PRCACG().equals("N")) out.print("checked");%> >No
 
	                	</div>
              	</td>
            </tr>

            <tr id="trdark"> 
              	<td nowrap  > 
                	<div align="right">Cuenta de Crédito (Liquidación) :</div>
              	</td>
              	<td nowrap > 
                	<div align="left"> 
                  		<input type="text" name="E01PRCLBK" size="2" maxlength="2" value="<%= msgPart.getE01PRCLBK().trim()%>" >
                  		<input type="text" name="E01PRCLBR" size="4" maxlength="3"  value="<%= msgPart.getE01PRCLBR().trim()%>" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRCLBK.value,'','','','')"onkeypress="enterInteger()">
                		<input type="text" name="E01PRCLCY" size="3" maxlength="3" value="<%= msgPart.getE01PRCLCY().trim()%>"  oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRCLBK.value,'','','','')"> 
                		<input type="text" name="E01PRCLGL" size="17" maxlength="16" value="<%= msgPart.getE01PRCLGL().trim()%>" onkeypress="enterInteger()" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRCLBK.value,document.forms[0].E01PRCLCY.value,'','','')">
                	</div>
              	</td>
            </tr>   

            <tr id="trclear"> 
              	<td nowrap > 
                	<div align="right">Indicador de Contabilización :</div>
              	</td>
              	<td nowrap > 
					<div align="left"> 
              			<input type="radio" name="E01PRCLCG" value="Y"
						<%if(msgPart.getE01PRCLCG().equals("Y") && msgPart.getE01PRCLCG().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCLCG" value="N"
						<%if(msgPart.getE01PRCLCG().equals("N") && msgPart.getE01PRCLCG().equals("N")) out.print("checked");%> >No
	                	</div>
              	</td>
            </tr>
             <tr id="trdark"> 
              	<td nowrap  > 
                	<div align="right">Mecanismo de Pago:</div>
              	</td>
           	<td nowrap > 
                	<div align="left"> 
                  		<input type="text" name="E01PRCMPG" size="2" maxlength="2" value="<%= msgPart.getE01PRCMPG().trim()%>">
			      		<input	type="text" name="D01PRCMPG" size="35" maxlength="30" value="<%= msgPart.getD01PRCMPG().trim()%>" readonly >
 	  	       		 <a href="javascript:GetCodeDescCNOFC('E01PRCMPG','D01PRCMPG','MP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>

                	</div>
              	</td>
            </tr>   
            <tr id="trclear"> 
            	<td nowrap  > 
            		<div align="right">Indicador de DVP :</div>
            	</td>
            	<TD nowrap>
					<div align="left"> 
              			<input type="radio" name="E01PRCDVP" value="Y"
						<%if(msgPart.getE01PRCDVP().equals("Y") && msgPart.getE01PRCDVP().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCDVP" value="N"
						<%if(msgPart.getE01PRCDVP().equals("N") && msgPart.getE01PRCDVP().equals("N")) out.print("checked");%> >No


			        </div>
			    </TD>
            </tr>   
            <tr id="trdark"> 
              	<td nowrap  > 
            		<div align="right">Liquidación por Compensación :</div>
            	</td>
            	<TD nowrap>
					<div align="left"> 
              			<input type="radio" name="E01PRCLPC" value="Y"
						<%if(msgPart.getE01PRCLPC().equals("Y") && msgPart.getE01PRCLPC().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCLPC" value="N"
						<%if(msgPart.getE01PRCLPC().equals("N") && msgPart.getE01PRCLPC().equals("N")) out.print("checked");%> >No


			        </div>
			    </TD>
            </tr>                                               
            <tr id="trclear"> 
				<td nowrap  > 
            		<div align="right">Retención en Cola de Pago :</div>
            	</td>
            	<TD nowrap>
					<div align="left"> 
              			<input type="radio" name="E01PRCRCP" value="Y"
						<%if(msgPart.getE01PRCRCP().equals("Y") && msgPart.getE01PRCRCP().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCRCP" value="N"
						<%if(msgPart.getE01PRCRCP().equals("N") && msgPart.getE01PRCRCP().equals("N")) out.print("checked");%> >No

			        </div>
			    </TD>
			</tr>
            <tr id="trdark"> 
				<td nowrap  > 
            		<div align="right">Código de Sesion :</div>
            	</td>
            	<TD nowrap>
					<div align="left"> 
	                 		<input type="text" name="E01PRCSES" size="11" maxlength="8" value="<%= msgPart.getE01PRCSES().trim()%>">
			        </div>
			    </TD>
			</tr>
		</table>
  			<H4><br>Parametros Mensajes Incoming<br></H4>

           <table id="mainTable" class="tableinfo">
	            <tr id="trdark"> 
	              	<td nowrap > 
	                	<div align="right">Cuenta de Débito :</div>
	              	</td>
	              	<td nowrap colspan="2"> 
	                	<div align="left"> 
                  		<input type="text" name="E01PRCCBK" size="3" maxlength="2" value="<%= msgPart.getE01PRCCBK().trim()%>">
                  		<input type="text" name="E01PRCCBR" size="5" maxlength="3" value="<%= msgPart.getE01PRCCBR().trim()%>">
                  		<input type="text" name="E01PRCCCY" size="4" maxlength="3" value="<%= msgPart.getE01PRCCCY().trim()%>">
                  		<input type="text" name="E01PRCCGL" size="17" maxlength="16" value="<%= msgPart.getE01PRCCGL().trim()%>" onkeypress="enterInteger()" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRCCBK.value,document.forms[0].E01PRCCCY.value,'','','')">
                	</div>
              	</td>
            </tr>
            <tr id="trclear"> 
              	<td nowrap > 
                	<div align="right">Indicador de Contabilización :</div>
              	</td>
              	<td nowrap colspan="2"> 
					<div align="left"> 
              			<input type="radio" name="E01PRCCAG" value="Y"
						<%if(msgPart.getE01PRCCAG().equals("Y") && msgPart.getE01PRCCAG().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCCAG" value="N"
						<%if(msgPart.getE01PRCCAG().equals("N") && msgPart.getE01PRCCAG().equals("N")) out.print("checked");%> >No


	                	</div>
              	</td>
            </tr>

	            <tr id="trdark"> 
	              	<td nowrap > 
	                	<div align="right">Cuenta de Rechazo:</div>
	              	</td>
	              	<td nowrap colspan="2"> 
	                	<div align="left"> 
                  		<input type="text" name="E01PRCRBK" size="3" maxlength="2" value="<%= msgPart.getE01PRCRBK().trim()%>">
                  		<input type="text" name="E01PRCRBR" size="5" maxlength="3" value="<%= msgPart.getE01PRCRBR().trim()%>">
                  		<input type="text" name="E01PRCRCY" size="4" maxlength="3" value="<%= msgPart.getE01PRCRCY().trim()%>">
                  		<input type="text" name="E01PRCRGL" size="17" maxlength="16" value="<%= msgPart.getE01PRCRGL().trim()%>" onkeypress="enterInteger()" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRCRBK.value,document.forms[0].E01PRCRCY.value,'','','')">
                	</div>
              	</td>
            </tr>
            <tr id="trclear"> 
              	<td nowrap > 
                	<div align="right">Indicador de Contabilización :</div>
              	</td>
              	<td nowrap > 
					<div align="left"> 
              			<input type="radio" name="E01PRCRAG" value="Y"
						<%if(msgPart.getE01PRCRAG().equals("Y") && msgPart.getE01PRCRAG().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCRAG" value="N"
						<%if(msgPart.getE01PRCRAG().equals("N") && msgPart.getE01PRCRAG().equals("N")) out.print("checked");%> >No
	                	</div>
              	</td>
            	<TD nowrap>
					<div align="left">Nro.Lote :
                 		<input type="text" name="E01PRCRLT" size="11" maxlength="8" value="<%= msgPart.getE01PRCRLT().trim()%>">
			        </div>
			    </TD>
            </tr>
            <tr id="trdark"> 
              	<td nowrap > 
                	<div align="right">Cuenta de Verificación :</div>
              	</td>
              	<td nowrap colspan="2"> 
	                	<div align="left"> 
                  		<input type="text" name="E01PRCVBK" size="3" maxlength="2" value="<%= msgPart.getE01PRCVBK().trim()%>">
                  		<input type="text" name="E01PRCVBR" size="5" maxlength="3" value="<%= msgPart.getE01PRCVBR().trim()%>">
                  		<input type="text" name="E01PRCVCY" size="4" maxlength="3" value="<%= msgPart.getE01PRCVCY().trim()%>">
                  		<input type="text" name="E01PRCVGL" size="17" maxlength="16" value="<%= msgPart.getE01PRCVGL().trim()%>" onkeypress="enterInteger()" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRCVBK.value,document.forms[0].E01PRCVCY.value,'','','')">
                	</div>
              	</td>
            </tr>
            <tr id="trclear"> 
              	<td nowrap > 
                	<div align="right">Indicador de Contabilización :</div>
              	</td>
              	<td nowrap > 
					<div align="left"> 
              			<input type="radio" name="E01PRCVRY" value="Y"
						<%if(msgPart.getE01PRCVRY().equals("Y") && msgPart.getE01PRCVRY().equals("Y")) out.print("checked");%> >Sí 
						<input type="radio" name="E01PRCVRY" value="N"
						<%if(msgPart.getE01PRCVRY().equals("N") && msgPart.getE01PRCVRY().equals("N")) out.print("checked");%> >No
	                	</div>
              	</td>
            	<TD nowrap>
					<div align="left">Nro.Lote :
                 		<input type="text" name="E01PRCVLT" size="11" maxlength="8" value="<%= msgPart.getE01PRCVLT().trim()%>">
			        </div>
			    </TD>
       	</table>

</div>

  
<p align="center"> 
    <input id="EIBSBTN" type=SUBMIT name="Submit" value="Enviar" >
  </p>

</form>
  

</body>
</html>
