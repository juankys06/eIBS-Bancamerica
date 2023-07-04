<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Assign Accounts to Credit Cards</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
  
</head>
<body>
<h3 align="center">Asignacion de cuentas a tarjetas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_account.jsp,ECC0080"> 
<BR>Mantenimiento
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
 <%
        int row;
		  try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
		  appList.setCurrentRow(row);
  %>     
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Numero Tarjeta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CCANUM" size="21" maxlength="20" readonly value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente de la Tarjeta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01CARCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>">                
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CARNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>                  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCAACC" size="12" maxlength="9" value="<%= appList.getRecord(4).trim()%>">
                <a href="javascript:GetAccount('E01CCAACC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo Cuenta :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>                 
					<input type="text" name="E01CCAATY" size="4" maxlength="3" value="<%= appList.getRecord(3).trim()%>">
                </b> </div>
            </td>
          </tr>       
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente de la cuenta:</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01CTACUN" size="9" maxlength="9" value="<%= appList.getRecord(15).trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CTANA1" size="45" maxlength="45" readonly value="<%= appList.getRecord(16).trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Permitir enviar  transferencias :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="radio" name="E01CCAFAU" value="F" <%if(appList.getRecord(5).equals("F")) out.print("checked");%>>Y-Si 
              <input type="radio" name="E01CCAFAU" value="" <%if(appList.getRecord(5).equals("")) out.print("checked");%>>No 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Permitir consultar :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="radio" name="E01CCAIAU" value="Q" <%if(appList.getRecord(6).equals("Q")) out.print("checked");%>>Y-Si 
              <input type="radio" name="E01CCAIAU" value="" <%if(appList.getRecord(6).equals("")) out.print("checked");%>>No 
            </td>
          </tr>   
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Permitir recibir transferencias :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="radio" name="E01CCATAU" value="T" <%if(appList.getRecord(7).equals("T")) out.print("checked");%>>Y-Si 
              <input type="radio" name="E01CCATAU" value="" <%if(appList.getRecord(7).equals("")) out.print("checked");%>>No 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Permiso por defecto :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="radio" name="E01CCADAU" value="D" <%if(appList.getRecord(8).equals("D")) out.print("checked");%>>Y-Si 
              <input type="radio" name="E01CCADAU" value="" <%if(appList.getRecord(8).equals("")) out.print("checked");%>>No 
            </td>
          </tr>  
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Permitir reserva  :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="radio" name="E01CCARAU" value="R" <%if(appList.getRecord(9).equals("R")) out.print("checked");%>>Y-Si 
              <input type="radio" name="E01CCARAU" value="" <%if(appList.getRecord(9).equals("")) out.print("checked");%>>No 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cuenta indexada :</div>
            </td>
            <td nowrap width="25%"> 
				<input type="text" name="E01CCAIND size="3" maxlength="3" value="<%= appList.getRecord(10).trim()%>">
            </td>
          </tr>                                                                                         
        </table>
      </td>
    </tr>
  </table>  
  <h4> Montos Limites</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Limite retiro en efectivo :</div>
            </td>
            <td nowrap width="25%"> 
				<input type="text" name="E01CCACWL" size="17" maxlength="16" value="<%= appList.getRecord(11).trim()%>" onkeypress="enterDecimal()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite fondos de reserva :</div>
            </td>
            <td nowrap width="25%"> 
            	<input type="text" name="E01CCAFBL" size="17" maxlength="16" value="<%= appList.getRecord(12).trim()%>" onkeypress="enterDecimal()">
  			</td>
          </tr>      
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Limite Sobregiro :</div>
            </td>
            <td nowrap width="25%"> 
				<input type="text" name="E01CCAODL" size="17" maxlength="16" value="<%= appList.getRecord(13).trim()%>" onkeypress="enterDecimal()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite retiro por cajero :</div>
            </td>
            <td nowrap width="25%"> 
            	<input type="text" name="E01CCATWL" size="17" maxlength="16" value="<%= appList.getRecord(14).trim()%>" onkeypress="enterDecimal()">
  			</td>
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

