<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session"/>
<jsp:useBean id="wtParam" class="datapro.eibs.beans.ESD000504Message" scope="session"/>
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session"/>

<script language="JavaScript">
	function enter(){
	 	document.forms[0].submit();
	}
</script>

</head>
<body> 
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%>
 
 <h3 align="center">Parametros de Transferencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="wt_parameters.jsp,ESD0005"> 
 </h3>
 <hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  
  <table class="tableinfo" width="100%">
	  <tr> 
	      <td nowrap> 
	        <table class=tbhead cellspacing="0" cellpadding="2" width="100%" align="center">
		        <tr>
		          <td nowrap width="30%" align="right"> Banco: </td>
		          <td nowrap width="70%" align="left">
		             <input type="text" name="E04CWTBNK" size="4" maxlength="2" value="<%= wtParam.getE04CWTBNK()%>" readonly>
                  </td>
		        </tr>
		        <tr>
		          <td nowrap width="30%" align="right">Sucursal por Omision: </td>
		          <td nowrap width="70%" align="left">
		      		<input type="text" name="E04CWTF01" size="4" maxlength="3" value="<%= wtParam.getE04CWTF01()%>" onkeypress="enterInteger()">
                    <a href="javascript:GetBranch('E04CWTF01','<%= wtParam.getE04CWTBNK()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"  ></a>
                  </td>
		        </tr>
	        </table>
	      </td>
	   </tr>
  </table>

  <h4>FED</h4>
   
  <table class="tableinfo">
      <tr > 
        <td nowrap>
          <table cellspacing="0" cellpadding="2" width="100%" border="0">
            <tr  id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Cuenta FED/ABA :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTBAB" size="10" maxlength="9" value="<%= wtParam.getE04CWTBAB() %>">
              </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Nombre :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTNME" size="25" maxlength="18" value="<%= wtParam.getE04CWTNME()%>">
              </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Cuenta Suspenso (CR) :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTFCA" size="17" maxlength="17" value="<%= wtParam.getE04CWTFCA()%>">
                <a href="javascript:GetLedger('E04CWTFCA','<%= wtParam.getE04CWTBNK()%>','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
              </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Cuenta Suspenso (DR) :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTDGL" size="17" maxlength="17" value="<%= wtParam.getE04CWTDGL()%>">
                <a href="javascript:GetLedger('E04CWTDGL','<%= wtParam.getE04CWTBNK()%>','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
              </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Tipo Interfaz :</div>
              </td>
              <td nowrap width="70%"> 
                 <select name="E04CWTFIN">
                  <option value="P" <% if (wtParam.getE04CWTFIN().equals("P")) { out.print("selected"); }%>>Produccion</option>
                  <option value="T" <% if (wtParam.getE04CWTFIN().equals("T")) { out.print("selected"); }%>>Prueba</option>
                  <option value="N" <% if (!(wtParam.getE04CWTFIN().equals("P") || wtParam.getE04CWTFIN().equals("T"))) { out.print("selected"); }%>>No Interfase</option>
                 </select>
              </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Ultimo Numero :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTFTN" size="8" maxlength="6" value="<%= wtParam.getE04CWTFTN()%>">
              </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Terminal : </div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTFTR" size="10" maxlength="8" value="<%= wtParam.getE04CWTFTR().trim()%>">
              </td>
            </tr>
			<tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Campo para Descripcion:</div>
              </td>
              <td nowrap width="70%"> 
                <select name="E04CWTFDS">
                  <option value="ORG" <% if (wtParam.getE04CWTFDS().equals("ORG")) { out.print("selected"); }%>>Produccion</option>
                  <option value="BNF" <% if (wtParam.getE04CWTFDS().equals("BNF")) { out.print("selected"); }%>>Prueba</option>
                  <option value="RFB" <% if (wtParam.getE04CWTFDS().equals("RFB")) { out.print("selected"); }%>>No Interfase</option>
                  <option value="OBI" <% if (wtParam.getE04CWTFDS().equals("OBI")) { out.print("selected"); }%>>No Interfase</option>
                 </select>
               </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Incluye Cta Contable :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="radio" name="E04CWTAIG" value="Y" <% if (wtParam.getE04CWTAIG().equals("Y")) out.print("checked");%>>Si
                <input type="radio" name="E04CWTAIG" value="N" <% if (wtParam.getE04CWTAIG().equals("N")) out.print("checked");%>>No
              </td>
            </tr>            
          </table>
        </td>
      </tr>
    </table>
    <h4>Swift</h4>
    <table class="tableinfo">
      <tr > 
        <td nowrap>
          <table cellspacing="0" cellpadding="2" width="100%" border="0">
     		<tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Nuestro Numero SWIFT :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTSWN" size="12" maxlength="11" value="<%= wtParam.getE04CWTSWN()%>">
              </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Cuenta Suspenso (CR) :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTSCG" size="17" maxlength="17" value="<%= wtParam.getE04CWTSCG().trim()%>">
                <a href="javascript:GetLedger('E04CWTSCG','<%= wtParam.getE04CWTBNK()%>','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
              </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Cuenta Suspenso (DR) :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTSDG" size="17" maxlength="17" value="<%= wtParam.getE04CWTSDG().trim()%>">
                <a href="javascript:GetLedger('E04CWTSDG','<%= wtParam.getE04CWTBNK()%>','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
              </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Interfase :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="radio" name="E04CWTSIN" value="Y" <% if (wtParam.getE04CWTSIN().equals("Y")) out.print("checked");%>>Si
                <input type="radio" name="E04CWTSIN" value="N" <% if (wtParam.getE04CWTSIN().equals("N")) out.print("checked");%>>No
              </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Ultimo Numero :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTSTN" size="8" maxlength="6" value="<%= wtParam.getE04CWTSTN()%>">
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Telex</h4>
    <table class="tableinfo">
      <tr > 
        <td nowrap>
          <table cellspacing="0" cellpadding="2" width="100%" border="0">
     		<tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Codigo Telex :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTTLC" size="2" maxlength="1" value="<%= wtParam.getE04CWTTLC()%>">
              </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Identificacion Banco :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTTLB" size="8" maxlength="6" value="<%= wtParam.getE04CWTTLB().trim()%>">
                </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Respuesta (Answerback) :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTABK" size="20" maxlength="17" value="<%= wtParam.getE04CWTABK()%>">
                </td>
            </tr>
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Ultimo Numero :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E04CWTRTN" size="5" maxlength="4" value="<%= wtParam.getE04CWTRTN()%>">
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Internas</h4>
    <table class="tableinfo" width="100%">
	   <tr> 
	      <td nowrap> 
	        <table cellspacing="0" cellpadding="2" width="100%" align="center">
		        <tr id=trdark>
		          <td nowrap width="30%">
		          	<div align="right">Ultimo No.Entrada :</div> 
		          </td>
		          <td nowrap width="70%">
		      			<input type="text" name="E04CWTITN" size="8" maxlength="6" value="<%= wtParam.getE04CWTITN()%>">
                   </td>
		        </tr>
	        </table>
	      </td>
	   </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</FORM>
</body>
</html>
