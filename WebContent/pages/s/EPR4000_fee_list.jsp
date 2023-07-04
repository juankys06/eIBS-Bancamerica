<html>
<head>
<title>Parametros Tablas de Comisiones de Transferencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EPR4000Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function closeEnter(){
   	  enterFee.filters[0].apply();
      enterFee.style.visibility="hidden";
      enterFee.filters[0].Play();
}

function ShowEnterCod() {	 
	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterFee.style.pixelTop=' + y);
     eval('enterFee.style.pixelLeft=' + x);
	 enterFee.filters[0].apply();
     enterFee.style.visibility="visible";
     enterFee.filters[0].Play();
	 
}


function goAction(op,bnew) {

	if (bnew == true) {
		document.forms[0].ORG.value = document.forms[0].ORG1.value;
		document.forms[0].TYP.value = document.forms[0].TYP1.value;
		document.forms[0].COP.value = document.forms[0].COP1.value;
		document.forms[0].COM.value = document.forms[0].COM1.value;
	}
	
	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(bnk, org, typ, cop, com) {

	document.forms[0].BNK.value = bnk;
	document.forms[0].ORG.value = org;
    document.forms[0].TYP.value = typ;
    document.forms[0].COP.value = cop;
    document.forms[0].COM.value = com;
    
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

<%	String lectura=" "; 
  
%>  	

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Parametros Tablas de Comision de Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fee_list.jsp, EPR4000"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEPR4000" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="BNK" value="<%=userPO.getBank()%>">
  <input type=HIDDEN name="ORG" value="">
  <input type=HIDDEN name="TYP" value="">
  <input type=HIDDEN name="COP" value="">
  <input type=HIDDEN name="COM" value=""> 
  <input type=HIDDEN name="VAL1" value=""> 
  
  <div id="enterFee" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Origen :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <select name="ORG1">
                  <option value="F" <% if ("ORG1".equals("F")) out.print("selected"); %>>Front Offices</option>
                  <option value="T" <% if ("ORG1".equals("T")) out.print("selected"); %>>Transferencias Financieras</option>
            </select>
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td align=CENTER width="18%"> 
          <div align="right">Tipo de Transferencia :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <select name="TYP1">
                  <option value="IW" <% if ("TYP1".equals("IW")) out.print("selected"); %>>Recibida</option>
                  <option value="OW" <% if ("TYP1".equals("OW")) out.print("selected"); %>>Enviada</option>
            </select>
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Tipo de Proceso :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
              <select name="COP1">
                  <option value="L" <% if ("COP1".equals("L")) out.print("selected"); %>>Local</option>
                  <option value="U" <% if ("COP1".equals("U")) out.print("selected"); %>>U.S.A</option>
                  <option value="B" <% if ("COP1".equals("B")) out.print("selected"); %>>Ambos</option>
                  <option value="O" <% if ("COP1".equals("O")) out.print("selected"); %>>Otros</option>
              </select>
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td align=CENTER width="18%"> 
          <div align="right">Tabla de Comision :</div>
        </td>
        <td align=CENTER width="34%">    
          <div align="left"> 
            <input type="text" name="COM1" size="3" maxlength="2" value="" onkeypress="enterInteger()">
            <a href="javascript:GetCNTRLPRF('COM1','VAL1')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a> 
            <input type="text" name='VAL1' size="15" maxlength="13" disabled>
          </div>
        </td>
      </tr>
       <tr id="trclear"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit" value="Ingresar" onClick="javascript:goAction(1,true)">
        </td>
      </tr>
    </table>
  </div>

  <%
	if ( EPR4000Help.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay registros para su criterio de búsqueda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
           <td class=TDBKG>
                <div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a><b>Nuevo</b></a></div>
              </td>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
           </tr>
         </table>
	  </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
   	  <td class=TDBKG width="33%">
        <div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(2, false)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(3, false)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%">Banco</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Origen</div>
            </th>
            <th align=CENTER nowrap width="20%">Tipo Transferencia</th>
            <th align=CENTER nowrap width="20%">Tipo Proceso</th>
            <th align=CENTER nowrap width="20%">Tabla de Comision</th>
          </tr>
          <%
                EPR4000Help.initRow();
				int k=1;
                while (EPR4000Help.getNextRow()) {
                 
                  out.println(EPR4000Help.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("ACCNUM");
  
</SCRIPT>
</form>

</body>
</html>
