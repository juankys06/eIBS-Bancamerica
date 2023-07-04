<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Detalle de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "gaPoliza" class= "datapro.eibs.beans.ERA001103Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>


<% 
	if ( !error.getERRNUM().equals("0")  ) {
    	error.setERRNUM("0");
     	out.println("<SCRIPT Language=\"Javascript\">");
     	out.println("       showErrors()");
     	out.println("</SCRIPT>");
 	}
 	int row;
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
		collList.setCurrentRow(row);		 
		gaPoliza = (datapro.eibs.beans.ERA001103Message) collList.getRecord();		
	} catch (Exception e) {
		row = -1;
	}
%>

<SCRIPT Language="Javascript">

function hidePoliza(value){
 if (value) {
   Poliza.style.display="none";}
 else{
   Poliza.style.display=""; }
}

</SCRIPT>


<H3 align="center">Otras Polizas de Seguro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_poliza_detail.jsp , ERA0011"></H3> 
<hr size="4">
<form method="post" action="" >
  <input type=hidden name="E03ROCREF" value="<%= userPO.getIdentifier().trim()%>">
  <input type=hidden name="E03ROCBNK" value="<%= userPO.getBank().trim()%>">
  <input type=hidden name="E03ROCCUN" value="<%= userPO.getCusNum().trim()%>">
  <input type=hidden name="ROW" value="<%= row %>">
                        
  <table class="tableinfo">
    	<tr > 
      		<td nowrap> 
        		<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
		          <tr id="trdark"> 
		            <td nowrap><div align="right"><b>Cliente :</b></div></td>
		            <td nowrap><div align="left"><%=userPO.getCusNum().trim()%></div></td>
		            <td nowrap><div align="right"><b>Nombre :</b></div></td>
		            <td nowrap><div align="left"><%= userPO.getCusName().trim()%></div></td>
		            <td nowrap><div align="right"><b>Referencia :</b></div></td>
		            <td nowrap><div align="left"><%= userPO.getIdentifier().trim()%></div></td>
		          </tr>
        		</table>
      		</td>
    	</tr>
  </table>   
  
  <div id="Poliza">
   <h4>Datos Seguro</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap><div align="right"> Compa&ntilde;&iacute;a:</div></td>
            <td nowrap colspan=2><input type="text" name="E03ROCICN" readonly size="7" maxlength="5" value="<%= gaPoliza.getE03ROCICN().trim()%>" ></td>
            <td nowrap><div align="right">Nombre:</div></td>
            <td nowrap colspan=2><INPUT type="text" name="E03ROCICM" readonly size="35" maxlength="35" value="<%= gaPoliza.getE03ROCICM().trim()%>"></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap><div align="right">N&uacute;mero P&oacute;liza:</div></td>
            <td nowrap colspan=2><INPUT type="text" name="E03ROCCIN" readonly size="45" maxlength="40" value="<%= gaPoliza.getE03ROCCIN().trim()%>"></td>
            <td nowrap><div align="right">Descripci&oacute;n:</div></td>
            <td nowrap colspan=2><INPUT type="text" name="E03ROCIPD" readonly size="35" maxlength="35" value="<%= gaPoliza.getE03ROCIPD().trim()%>"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap><div align="right">Monto:</div></td>
            <td nowrap colspan=2><INPUT type="text" name="E03ROCIPA" readonly size="15" maxlength="15" value="<%= gaPoliza.getE03ROCIPA().trim()%>"></td>
            <td nowrap ><div align="right">Moneda:</div></td>
            <td nowrap colspan=2><input type="text" name="E03ROCICY" readonly size="4" maxlength="3" value="<%= gaPoliza.getE03ROCICY().trim()%>" ></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap><div align="right">Tipo de Seguro :</div></td>
            <td nowrap ><INPUT type="checkbox" name="E03ROCTS1" disabled value="1" <% if (gaPoliza.getE03ROCTS1().equals("1")) out.print("checked"); %> >Incendio</td>
            <td nowrap ><INPUT type="checkbox" name="E03ROCTS2" disabled value="1" <% if (gaPoliza.getE03ROCTS2().equals("1")) out.print("checked"); %> >Vehiculo</td>
            <td nowrap ><INPUT type="checkbox" name="E03ROCTS3" disabled value="1" <% if (gaPoliza.getE03ROCTS3().equals("1")) out.print("checked"); %> >Transporte</td>
            <td nowrap ><INPUT type="checkbox" name="E03ROCTS4" disabled value="1" <% if (gaPoliza.getE03ROCTS4().equals("1")) out.print("checked"); %> >Adicional</td>
            <td nowrap ><INPUT type="checkbox" name="E03ROCTS5" disabled value="1" <% if (gaPoliza.getE03ROCTS5().equals("1")) out.print("checked"); %> >Otros</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap><div align="right">Fecha Emisi&oacute;n:</div></td>
            <td nowrap colspan=2> 
              <input type="text" name="E03ROCEM1" readonly size="2" maxlength="2" value="<%= gaPoliza.getE03ROCEM1().trim()%>">
              <input type="text" name="E03ROCEM2" readonly size="2" maxlength="2" value="<%= gaPoliza.getE03ROCEM2().trim()%>">
              <input type="text" name="E03ROCEM3" readonly size="2" maxlength="2" value="<%= gaPoliza.getE03ROCEM3().trim()%>">
            </td>
            <td nowrap><div align="right">Emitido:</div></td>
            <td nowrap colspan=2><input type="text" name="E03ROCEMB" readonly size="10" maxlength="9" value="<%= gaPoliza.getE03ROCEMB().trim()%>" ></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap><div align="right">Fecha Vencimiento:</div></td>
            <td nowrap colspan=2> 
              <input type="text" name="E03ROCMD1" readonly size="2" maxlength="2" value="<%= gaPoliza.getE03ROCMD1().trim()%>">
              <input type="text" name="E03ROCMD2" readonly size="2" maxlength="2" value="<%= gaPoliza.getE03ROCMD2().trim()%>">
              <input type="text" name="E03ROCMD3" readonly size="2" maxlength="2" value="<%= gaPoliza.getE03ROCMD3().trim()%>">
            </td>
            <td nowrap><div align="right">Excepci&oacute;n:</div></td>
              <td nowrap colspan=2>
              	<input type="hidden" name="E03ROCRGK" value="<%= gaPoliza.getE03ROCRGK()%>">
              	<input type="radio" name="CE03ROCRGK" disabled value="Y" <% if (gaPoliza.getE03ROCRGK().equals("Y")) out.print("checked");%>>Sí 
                <input type="radio" name="CE03ROCRGK" disabled value="N" <% if (gaPoliza.getE03ROCRGK().equals("N")) out.print("checked");%>>No 
              </td>
          </tr>
          <tr id="trdark"> 
              <td nowrap><div align="right">Aviso Vencimiento:</div></td>
              <td nowrap colspan=2>
                <input type="hidden" name="E03ROCCLF" value="<%= gaPoliza.getE03ROCCLF()%>">
                <input type="radio" name="CE03ROCCLF" disabled value="Y" <% if (gaPoliza.getE03ROCCLF().equals("Y")) out.print("checked");%>>Sí 
                <input type="radio" name="CE03ROCCLF" disabled value="N" <% if (gaPoliza.getE03ROCCLF().equals("N")) out.print("checked");%>>No 
             </td>
            <td nowrap><div align="right">Banco/Endosa:</div></td>
            <td nowrap colspan=2>
                <input type="radio" name="E03ROCEDO" disabled value="1" <% if (gaPoliza.getE03ROCEDO().equals("1")) out.print("checked");%>>Sí 
                <input type="radio" name="E03ROCEDO" disabled value="2" <%if(gaPoliza.getE03ROCEDO().equals("2")) out.print("checked");%>>No
            </td>
          </tr>
          <tr id="trclear">
            <TD align="right">Corredora:</TD>
            <TD colspan=2><INPUT type="text" name="E03ROCCRR" readonly size="5" maxlength="4" value="<%= gaPoliza.getE03ROCCRR().trim()%>"></TD>
            <td nowrap><div align="right">Nombre :</div></td>
            <td nowrap colspan=2><INPUT type="text" readonly name="E03ROCDSC" size="35" value="<%= gaPoliza.getE03ROCDSC().trim()%>"></td>
          </tr>
         </table>         
      </td>
    </tr>
  </table>

  </div>
  
</form>
</body>
</html>
