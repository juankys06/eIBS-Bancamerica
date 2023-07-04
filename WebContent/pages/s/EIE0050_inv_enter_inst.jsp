<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<script language="JavaScript">
function advanceSearch(){
	  document.forms[0].SCREEN.value = 300;
	  document.forms[0].submit();
	 }
function newInstrument(){
	  document.forms[0].SCREEN.value = 2;
      document.forms[0].opt.value = 1;
	  document.forms[0].submit();
	 }

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

function showHiddenDivNew(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivNew = document.getElementById("hiddenDivNew");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	 
}

document.onclick= closeHiddenDivNew;
</script>

<META name="GENERATOR" content="IBM WebSphere Studio">
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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0308P" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">
  	<input type=HIDDEN name="NEXTE" value="N">
    <input type=HIDDEN name="opt" value="1">

<div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Tipo de Instrumento :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <select name="TYPE">
                <option value=" "> </option>
                <option value="BND">Bonos</option>
                <option value="EQT">Acciones</option>
                <option value="MUT">Fondos Mutuales</option>
                <option value="PFS">Acciones Preferenciales</option>
          </select>
        </div>
      </td>
    </tr>
   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit" value="Submit" onClick="newInstrument()"> 
   </TD>
   
   </tr>
 </TABLE>
</div>

  <h3 align="center">Instrumentos</h3>
  <table class="tbenter" height="55%" width="100%" border="0">
    <tr> 
      <td nowrap align="center"> 
        <table class="tableinfo" align="center" >
          <tr> 
            <td> 
              <table width="100%">
                <tr tr id="trdark"> 
                  <td valign="middle" align="center" colspan="3" height=33> 
                    <div align="left"><b>Buscar por </b></div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td>&nbsp;</td>
                  <td width="20%" nowrap> 
                    <div align="right">Código de Instrumento : </div>
                  </td>
                  <td width="70%" nowrap> 
                    <input type="text" name="INSTCODE"  size=19 maxlength="19" 
					  onKeyPress="document.forms[0].SEARCHC.value='C'">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td>&nbsp;</td>
                  <td width="20%" nowrap> 
                    <div align="right">S&iacute;mbolo :</div>
                  </td>
                  <td width="70%" nowrap> 
                    <input type="text" name="SYMBOL"  size=12 maxlength="14" 
					onKeyPress="document.forms[0].SEARCHC.value='S'">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap> 
                    <div align="right">N&uacute;mero de ISIN / Serial : </div>
                  </td>
                  <td width="70%" nowrap> 
                    <input type="text" name="ISIN"  size=15 maxlength="15" onKeyPress="document.forms[0].SEARCHC.value='I'">
                    / 
                    <input type="text" name="SERIAL"  size=12 maxlength="12" onKeyPress="document.forms[0].SEARCHC.value='I'">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">CUSIP : </div>
                  </td>
                  <td width="70%" nowrap > 
                    <input type="text" name="CUSIP"  size=15 maxlength="15" onKeyPress="document.forms[0].SEARCHC.value='U'">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Descripci&oacute;n :</div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> 
                      <input type="text" name="DESCRIPTION"  size=45 maxlength="35" 
					 onKeyPress="document.forms[0].SEARCHC.value='N'">
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Emisor : </div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> 
                      <input type="text" name="ISSUER"  size=45 maxlength="45" 
					  onKeyPress="document.forms[0].SEARCHC.value='E'">
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Tipo de Instrumento : </div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> 
                      <select name="TYPE">
                        <option value=" ">All </option>
                        <option value="BND">Bonds</option>
                        <option value="EQT">Equity</option>
                        <option value="MUT">Mutual Funds</option>
                        <option value="PFS">Preferred Stocks</option>
                      </select>
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Estado Interno : </div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> 
                      <input type=HIDDEN name="STATUS">
                      <input type="radio" name="CSTATUS" value="A" onClick="document.forms[0].STATUS.value='A'">
                      Activo 
                      <input type="radio" name="CSTATUS" value="I" onClick="document.forms[0].STATUS.value='I'">
                      Inactivo 
                      <input type="radio" name="CSTATUS" value="P" onClick="document.forms[0].STATUS.value='P'">
                      Pendiente 
                      <input type="radio" name="CSTATUS" value="R" onClick="document.forms[0].STATUS.value=''" checked>
                      Todos </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Fecha de Emisi&oacute;n : </div>
                  </td>
                  <td width="70%" nowrap > 
                    <input type="text" size="2" maxlength="2" name="DATEF1" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                    <input type="text" size="2" maxlength="2" name="DATEF2" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="DATEF3" onKeyPress="enterInteger()">
                    <a href="javascript:DatePicker(document.forms[0].DATEF1,document.forms[0].DATEF2,document.forms[0].DATEF3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                    a 
                    <input type="text" size="2" maxlength="2" name="DATET1" 
					   onKeyPress="document.forms[0].SEARCHC.value='D'">
                    <input type="text" size="2" maxlength="2" name="DATET2" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="DATET3" onKeyPress="enterInteger()">
                    <a href="javascript:DatePicker(document.forms[0].DATET1,document.forms[0].DATET2,document.forms[0].DATET3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Fecha de Vencimiento :</div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> 
                      <input type="text" size="2" maxlength="2" name="DATEMF1" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <input type="text" size="2" maxlength="2" name="DATEMF2" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <input type="text" size="2" maxlength="2" name="DATEMF3" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <a href="javascript:DatePicker(document.forms[0].DATEMF1,document.forms[0].DATEMF2,document.forms[0].DATEMF3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                      a 
                      <input type="text" size="2" maxlength="2" name="DATEMT1" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <input type="text" size="2" maxlength="2" name="DATEMT2" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <input type="text" size="2" maxlength="2" name="DATEMT3" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <a href="javascript:DatePicker(document.forms[0].DATEMT1,document.forms[0].DATEMT2,document.forms[0].DATEMT3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Tasa de Cup&oacute;n :</div>
                  </td>
                  <td width="70%" nowrap > 
                    <input type="text" name="RATE"  size=11 maxlength="11" 
					  onKeyPress="document.forms[0].SEARCHC.value='C'">
                    % </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Pa&iacute;s :</div>
                  </td>
                  <td width="70%" nowrap > 
                    <input type="text" name="COUNTRY"  size=25 maxlength="25" 
					  onKeyPress="document.forms[0].SEARCHC.value='C'">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Moneda de Instrumento :</div>
                  </td>
                  <td width="70%" nowrap > 
                    <input type="text" name="CURRENCY"  size=4 maxlength="3" 
					  onKeyPress="document.forms[0].SEARCHC.value='C'">
                    <a href="javascript:GetCurrency('CURRENCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Rating Standard &amp; Poor's :</div>
                  </td>
                  <td width="70%" nowrap >
                    <input type="text" name="STANDARDR"  size=25 maxlength="25" 
					  onKeyPress="document.forms[0].SEARCHC.value=''">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Rating Moody's :</div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left">
                      <input type="text" name="MOODYSR"  size=25 maxlength="25" 
					  onKeyPress="document.forms[0].SEARCHC.value=''">
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="10%" align="center">&nbsp;</td>
                  <td width="20%" nowrap > 
                    <div align="right">Mostrar solo Instrumentos Preferenciales 
                      : </div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> 
                      <input type=HIDDEN name="PREFINST" value="A">
                      <input type="radio" name="CPREFINST" value="Y" onClick="document.forms[0].PREFINST.value='Y'">
                      S&iacute; 
                      <input type="radio" name="CPREFINST" onClick="document.forms[0].PREFINST.value='N'">
                      No 
                      <input type="radio" name="CPREFINST" onClick="document.forms[0].PREFINST.value=''" checked>
                      Todos</div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>

        <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
                <input id="EIBSBTN" type=button name="Nuevo" value="Nuevo">
            </td>
            <td width="33%"> 
              <div align="center">
                <input id="EIBSBTN" type=submit name="Submit" value="Buscar">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td colspan="2"> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
<SCRIPT language="JavaScript">
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
 	getElementByNameAndId("Nuevo", 'EIBSBTN').onclick=showHiddenDivNew;
</SCRIPT> 
      
</form>
</body>
</html>
