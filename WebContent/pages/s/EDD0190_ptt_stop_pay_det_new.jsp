<%
// CONTROL DE EXPIRACION DE PÁGINAS 
response.setHeader("Pragma", "No-cache"); 
response.setDateHeader("Expires", 0); 
response.setHeader("Cache-Control", "no-cache"); 
response.setHeader("Cache-Control", "private");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Emision de Protestos Manuales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "rtStop" class= "datapro.eibs.beans.EDD019001Message"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
function enter(){
    if ((document.form1.DHSCHK.value <= 0) || (document.form1.DHSAMT.value <= 0)){
	     alert("Ingrese N° de Cheque y Monto");
	}else{	
		if (document.form1.DHSOFC.value <= 0) {
	     	alert("Ingrese Código del Oficial");
		}else{
			if (document.form1.DHSORG.value <= 0) {
	     		alert("Ingrese Origen Protesto");
			}else{
				if (document.form1.DHSOBR.value <= 0) {
		     		alert("Ingrese Sucursal Protesto");
				}else{
					if (document.form1.DHSRES.value < 0) {
	     				alert("Ingrese Motivo Protesto");
					}else{
						return true;
					}
				}			
			}	
		}	
	}
	return false;
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


<H3 align="center">Generar Protesto Manual de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_stop_pay_det_new.jsp , EDD0190"> 
</H3>
<hr size="4">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190" onsubmit="return(enter())">
  <input type=HIDDEN name="SCREEN" value="4">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

   <input type=HIDDEN name="E01STPBNK" value="<%= userPO.getHeader10().trim()%>">
  <input type=HIDDEN name="E01STPBRN" value="<%= userPO.getHeader11().trim()%>">
  <input type=HIDDEN name="E01STPGLN" value="<%= userPO.getHeader12().trim()%>">

    <%
        int row;
		  try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
		  stop.setCurrentRow(row);
    %>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader1().trim()%></b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader5().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= userPO.getIdentifier().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getCurrency().trim()%></b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getHeader6().trim()%></b> </div>
            </td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Ejecutivo :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader18().trim()%></b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader19().trim()%></div>
            </td>
          </tr>
		  <tr id="trclear"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Contable :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader15().trim())%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Disponible : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader16().trim())%></b> </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Retención: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader17().trim())%></b> </div>
                    </td>
                  </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha del Cheque :</div>
            </td>
            <td nowrap> 
              <div align="left"><%= userPO.getHeader12().trim()%> / <%= userPO.getHeader13().trim()%> / <%= userPO.getHeader14().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="DHSCHK" size="10" maxlength="9" value="<%= rtStop.getDHSCHK().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
            
          </tr>
          
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto del Cheque :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="DHSAMT" size="15" maxlength="15" value="<%= rtStop.getDHSAMT().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap> 
               <input type="text" name="DHSOFC" size="4" maxlength="3" value="<%= rtStop.getDHSOFC().trim()%>" >
              <a href="javascript:GetCodeDescCNOFC('DHSOFC','DHSOFCDESC','15')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
			  <input type="text" name="DHSOFCDESC" size="35" maxlength="35" value="" readonly>
		
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%" > 
              <div align="right">Origen Protesto :</div>
            </td>
            <td nowrap> 
              <SELECT name="DHSORG" value="<%= rtStop.getDHSORG().trim()%>" readonly>
				<OPTION value="4" <% if (rtStop.getDHSORG().equals("4")) out.print("selected"); %>>Caja</OPTION>
			    <OPTION value="5" <% if (rtStop.getDHSORG().equals("5")) out.print("selected"); %>>Fuera Camara</OPTION>
		     </SELECT>     
		    </td>
          </tr>
		  <tr id="trclear"> 
            <td nowrap width="44%" > 
              <div align="right">Sucursal Origen :</div>
            </td>
            <td nowrap > 
              <input type="text" name="DHSOBR" size="4" maxlength="3" value="<%= rtStop.getDHSOBR().trim()%>" onKeypress="enterInteger()" >
              <a href="javascript:GetBranch('DHSOBR','')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>			   
			</td>			
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Motivo :</div>
            </td>
            <td nowrap> 
              <input type="text" name="DHSRES" size="5" maxlength="4" value="<%= rtStop.getDHSRES().trim()%>" >
              <a href="javascript:GetCasualTable('DHSRES','')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a>
			</td>
          </tr>
		 </table>
      </td>
    </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="enviar">
  </p>
  
  
  </form>
</body>
</html>
