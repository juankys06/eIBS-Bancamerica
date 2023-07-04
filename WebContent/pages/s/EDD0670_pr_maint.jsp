<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Payment and Receiving</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="prBasic" class="datapro.eibs.beans.EDD067001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
//if ( document.forms[0].E01ACMACC.value.length < 1) {
//  alert("A valid account number must be entered");
//  document.forms[0].E01ACMACC.value='';
//  document.forms[0].E01ACMACC.focus();
//}
//else {
  document.forms[0].submit();
//}
}

</SCRIPT>

<SCRIPT Language="Javascript">
  
  function showTab(index,name){
  
  if((document.forms[0].E01PODTYP.value == 2)&& index == 7 ){
  alert("This option is not valid for  Swift MT - 100");
  return;
  }
  if((document.forms[0].E01PODTYP.value == 3)&& ((index == 4 )||(index== 5)||(index== 7))){
  alert("This option is not valid for  Swift MT - 200");
  return;
  }
  if((document.forms[0].E01PODTYP.value == 4)&& (index == 5)||(index== 7) ){
  alert("This option is not valid for  Swift MT - 202");
  return;
  }
  if((document.forms[0].E01PODTYP.value == 5)&& ((index == 0 )||(index== 1)||(index== 2)||(index== 3)||(index== 6))){
  alert("This option is not valid for  Telex");
  return;
  }
  if((document.forms[0].E01REFNUM.value == '0' || document.forms[0].E01REFNUM.value == '') && index == 8){
  alert("This is not an Stand Alone Order");
  return;
  }
  
  for(var i=0;i<9;i++){
   document.all["Tab"+i].className="tabnormalv";
   document.all["dataTab"+i].style.display="none";
   }
  if(index < 4) {
    document.all["Tab"+index].className="tabhighlightl";
	colTab.className="tabdataleft";
  }else {
	document.all["Tab"+index].className="tabhighlightr";
	colTab.className="tabdataright";
  }
  document.all["dataTab"+index].style.display="";
  document.all[name].focus();
  }

 function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
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

<h3 align="center">Ordenes de Pago Mantenimiento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_maint.jsp,EDD0670" ></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD0670">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="800">
	<INPUT TYPE=HIDDEN NAME="E01POSPR1" VALUE="<%= prBasic.getE01POSPR1()%>">
    <INPUT TYPE=HIDDEN NAME="E01POSPR2" VALUE="<%= prBasic.getE01POSPR2()%>">
	<INPUT TYPE=HIDDEN NAME="E01POSCHB" VALUE="<%= prBasic.getE01POSCHB()%>">
    <INPUT TYPE=HIDDEN NAME="E01POSCHO" VALUE="<%= prBasic.getE01POSCHO()%>">
	<INPUT TYPE=HIDDEN NAME="E01PODTYP" VALUE="<%= prBasic.getE01PODTYP()%>">
    <INPUT TYPE=HIDDEN NAME="E01REFNUM" VALUE="<%= prBasic.getE01REFNUM()%>">
  <h4>Informaci&oacute;n B&aacute;sica </h4>
  
     
  <table class="tableinfo" >
    <tr id="trdark"> 
      <td nowrap width="30%" > 
        <div align="right">Identificaci&oacute;n de Swift :</div>
      </td>
      <td nowrap width="10%" > 
        <div align="left"> 
          <input type="text" name="E01POSSID" size="12" maxlength="12" value="<%= prBasic.getE01POSSID()%>">
          <a href="javascript:GetSwiftId('E01POSSID')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a></div>
      </td>
      <td nowrap width="20%" > 
        <div align="right">Prioridad : </div>
      </td>
      <td nowrap colspan="3" width="40%" > 
        <div align="left"> 
          <input type="radio" name="CE01POSPR1" 
              <%if(prBasic.getE01POSPR1().equals("X")) out.print("checked");%> 
		onClick="document.forms[0].E01POSPR1.value = 'X';document.forms[0].E01POSPR2.value = ' '">
          01 
          <input type="radio" name="CE01POSPR1" value="X" 
              <%if(prBasic.getE01POSPR2().equals("X")) out.print("checked");%>
		 onClick="document.forms[0].E01POSPR1.value = ' ';document.forms[0].E01POSPR2.value = 'X'">
          02 </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="30%" > 
        <div align="right"></div>
      </td>
      <td nowrap width="10%" >&nbsp; </td>
      <td nowrap width="20%" > 
        <div align="right">Cargos :</div>
      </td>
      <td nowrap colspan="3" width="40%" > 
        <input type="radio" name="CE01POSCHB" value="X" 
			  onClick="document.forms[0].E01POSCHB.value='X';document.forms[0].E01POSCHO.value=' '"
              <%if(prBasic.getE01POSCHB().equals("X")) out.print("checked");%>>
        Beneficiario 
        <input type="radio" name="CE01POSCHB" value="X" 
			  onClick="document.forms[0].E01POSCHB.value=' ';document.forms[0].E01POSCHO.value='X'"
              <%if(prBasic.getE01POSCHO().equals("X")) out.print("checked");%>>
        Nosotros</td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo">
    <tr> 
      <td align="center"> 
        <table id="TableTab" cellspacing=0 cellpadding=0 width="99%">
          <tr> 
            <td  valign="top" align="right" width="5%"> 
              <table id="TableTabL" cellspacing=0 cellpadding=0 border="0">
                <tr> 
                  <td  id="Tab0" class="tabhighlightl" onClick="showTab(0,'E01POSOR1')"> 
                    <div  >Ordenante</div>
                  <td> 
                </tr>
                <tr> 
                  <td  id="Tab1" class="tabnormalv" onClick="showTab(1,'E01POSOBO')"> 
                    <div  >Corresponsal</div>
                  <td> 
                </tr>
                <tr > 
                  <td  id="Tab2" class="tabnormalv" onClick="showTab(2,'E01POSSCO')"> 
                    <div >Banco Intermediario</div>
                  <td> 
                </tr>
                <tr > 
                  <td  id="Tab3" class="tabnormalv" onClick="showTab(3,'E01POSRP1')"> 
                    <div  >Cuenta con Banco</div>
                  <td> 
                </tr>
              </table>
            </td>
            <td id="colTab" align="center" class="tabdataleft"> 
              <div id="DataTab"> 
                <div id="dataTab0"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="14%" > 
                        <div align="right"> Cliente :</div>
                      </td>
                      <td nowrap width="21%"> 
                        <textarea name="E01POSORC" cols="35" rows="4"><%= prBasic.getE01POSORC()%></textarea>
                      </td>
                      <td nowrap width="23%"> 
                        <div align="right">Banco : </div>
                      </td>
                      <td nowrap width="4%" valign="top"> 
                        <input type="text" name="E01POSOBO" size="2" maxlength="1" value="<%= prBasic.getE01POSOBO()%>" >
                      </td>
                      <td nowrap width="4%">&nbsp;</td>
                      <td nowrap width="34%"> 
                        <textarea name="E01POSOBK" cols="35" rows="4"><%= prBasic.getE01POSOBK()%></textarea>
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab1" style="display: none"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="16%" > 
                        <div align="right">Banco Emisor :</div>
                      </td>
                      <td nowrap width="2%" valign="top" > 
                        <input type="text" name="E01POSSCO" size="2" maxlength="1" value="<%= prBasic.getE01POSSCO()%>" >
                      </td>
                      <td nowrap width="3%" valign="middle" >&nbsp;</td>
                      <td nowrap width="38%" valign="middle" > 
                        <textarea name="E01POSSCB" cols="35" rows="4"><%= prBasic.getE01POSSCB()%></textarea>
                      </td>
                      <td nowrap width="7%" > 
                        <div align="right">Receptor :</div>
                      </td>
                      <td nowrap width="34%" > 
                        <textarea name="E01POSRCB" cols="35" rows="4"><%= prBasic.getE01POSRCB()%></textarea>
                      </td>
                    </tr>
                    <tr > 
                      <td nowrap width="16%" > 
                        <div align="right"></div>
                      </td>
                      <td nowrap colspan="3" >&nbsp; </td>
                      <td nowrap colspan="2" >&nbsp; </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab2" style="display: none"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="28%" > 
                        <div align="right"> Banco Intermediario :</div>
                      </td>
                      <td nowrap width="21%" > 
                        <textarea name="E01POSINB" cols="35" rows="4"><%= prBasic.getE01POSINB()%></textarea>
                      </td>
                      <td nowrap width="51%" >&nbsp; </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab3" style="display: none"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="28%" > 
                        <div align="right">Cuenta con Banco : </div>
                      </td>
                      <td nowrap width="2%" valign="top" > 
                        <input type="text" name="E01POSBBO" size="2" maxlength="1" value="<%= prBasic.getE01POSBBO()%>" >
                      </td>
                      <td nowrap width="4%" >&nbsp;</td>
                      <td nowrap width="66%" > 
                        <textarea name="E01POSBBK" cols="35" rows="4"><%= prBasic.getE01POSBBK()%></textarea>
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab4" style="display: none"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="28%" > 
                        <div align="right"> Cliente :</div>
                      </td>
                      <td nowrap width="30%" > 
                        <textarea name="E01POSBCU" cols="35" rows="4"><%= prBasic.getE01POSBCU()%></textarea>
                      </td>
                      <td nowrap width="8%"> 
                        <div align="right">Banco : </div>
                      </td>
                      <td nowrap width="34%"> 
                        <textarea name="E01POSBCN" cols="35" rows="4"><%= prBasic.getE01POSBCN()%></textarea>
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab5" style="display: none"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="44%" > 
                        <div align="right">Detalle de Pagos :</div>
                      </td>
                      <td nowrap width="2%" >&nbsp; </td>
                      <td nowrap width="54%"> 
                        <textarea name="E01POSDTP" cols="35" rows="4"><%= prBasic.getE01POSDTP()%></textarea>
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab6" style="display: none"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr > 
                      <td nowrap width="44%" > 
                        <div align="right">Informaci&oacute;n Banco a Banco :</div>
                      </td>
                      <td nowrap width="2%">&nbsp; </td>
                      <td nowrap width="54%"> 
                        <textarea name="E01POSBKB" cols="35" rows="4"><%= prBasic.getE01POSBKB()%></textarea>
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab7" style="display: none"> 
                  <table width="100%">
                    <tr id="trclear" > 
                      <td nowrap width="31%" > 
                        <div align="right">Departamento Originador :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <input type="text" name="E01POTORD" size="12" maxlength="12" value="<%= prBasic.getE01POTORD().trim()%>">
                      </td>
                      <td nowrap width="27%"> 
                        <div align="right">Referencia de Telex :</div>
                      </td>
                      <td nowrap width="17%"> 
                        <input type="text" name="E01POTLOG" size="12" maxlength="12" value="<%= prBasic.getE01POTLOG().trim()%>">
                      </td>
                    </tr>
                    <tr   > 
                      <td nowrap width="31%" height="23" > 
                        <div align="right">Banco Receptor :</div>
                      </td>
                      <td nowrap height="23" width="25%"> 
                        <input type="text" name="E01POTCNU" size="10" maxlength="9" value="<%= prBasic.getE01POSSID().trim()%>">
                      </td>
                      <td nowrap height="23" width="27%"> 
                        <div align="right">Nombre Corto :</div>
                      </td>
                      <td nowrap height="23" width="17%"> 
                        <input type="text" name="E01POTCSN" size="20" maxlength="15" value="<%= prBasic.getE01POTCSN().trim()%>">
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="31%" height="23" > 
                        <div align="right">Nombre :</div>
                      </td>
                      <td nowrap height="23" width="25%"> 
                        <input type="text" name="E01POTTO1" size="30" maxlength="25" value="<%= prBasic.getE01POTTO1().trim()%>">
                      </td>
                      <td nowrap height="23" width="27%"> 
                        <div align="right">Via de Aviso :</div>
                      </td>
                      <td nowrap height="23" width="17%"> 
                        <input type="text" name="E01POTVIA" size="2" maxlength="1" value="<%= prBasic.getE01POTVIA().trim()%>">
                        ( /L/I/F) </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="31%" height="23" >&nbsp;</td>
                      <td nowrap height="23" width="25%"> 
                        <input type="text" name="E01POTTO2" size="30" maxlength="25" value="<%= prBasic.getE01POTTO2().trim()%>">
                      </td>
                      <td nowrap height="23" width="27%"> 
                        <div align="right">N&uacute;mero de Telex :</div>
                      </td>
                      <td nowrap height="23" width="17%"> 
                        <input type="text" name="E01POTNRO" size="20" maxlength="15" value="<%= prBasic.getE01POTNRO().trim()%>">
                      </td>
                    </tr>
                    <tr > 
                      <td nowrap width="31%" > 
                        <div align="right">Por Orden de :</div>
                      </td>
                      <td nowrap colspan="3"> 
                        <input type="text" name="E01POTBYO" size="30" maxlength="30" value="<%= prBasic.getE01POTBYO().trim()%>" >
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="31%" > 
                        <div align="right"> Banco Originador :</div>
                      </td>
                      <td nowrap colspan="3"> 
                        <input type="text" name="E01POTORB" size="30" maxlength="30" value="<%= prBasic.getE01POTORB().trim()%>" >
                      </td>
                    </tr>
                    <tr   > 
                      <td nowrap width="31%" >&nbsp;</td>
                      <td nowrap colspan="3">&nbsp;</td>
                    </tr>
                    <tr   > 
                      <td nowrap width="31%" > 
                        <div align="right"> Debitamos su cuenta con nosotros :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <input type="text" name="E01POTOU1" size="15" maxlength="13" value="<%= prBasic.getE01POTOU1().trim()%>" >
                      </td>
                      <td nowrap width="27%"> 
                        <div align="right">Debitamos nuestra cuenta con ustedes 
                          :</div>
                      </td>
                      <td nowrap width="17%"> 
                        <input type="text" name="E01POTOUA" size="15" maxlength="12" value="<%= prBasic.getE01POTOUA().trim()%>" >
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="31%" > 
                        <div align="right">Cobertura a traves de :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <input type="text" name="E01POTCVP" size="25" maxlength="20" value="<%= prBasic.getE01POTCVP().trim()%>" >
                      </td>
                      <td nowrap colspan="2"> 
                        <input type="text" name="E01POTOTC" size="35" maxlength="30" value="<%= prBasic.getE01POTOTC().trim()%>" >
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="dataTab8" style="display: none"> 
                  <table width="100%">
                    <tr id="trclear" > 
                      <td nowrap width="44%" > 
                        <div align="right">Monto :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01POSAM1" size="14" maxlength="13" value="<%= prBasic.getE01POSAM1()%>" >
                        <input type="text" name="E01POSAM2" size="3" maxlength="2" value="<%= prBasic.getE01POSAM2()%>" >
                      </td>
                    </tr>
                    <tr   > 
                      <td nowrap width="44%" > 
                        <div align="right">Cuenta de Debito :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01POSDBR" size="4" maxlength="3" value="<%= prBasic.getE01POSDBR()%>"
						  oncontextmenu="showPopUp(branchHelp,'E01POSDBR','01','','','','')" >
                        <input type="text" name="E01POSDCY" size="4" maxlength="3" value="<%= prBasic.getE01POSDCY()%>" 
						  oncontextmenu="showPopUp(currencyHelp,'E01POSDCY','01','','','','')"	>
                        <input type="text" name="E01POSDGL" size="14" maxlength="12" value="<%= prBasic.getE01POSDGL()%>" >
                        <br>
                        <input type="text" name="E01POSDAC" size="10" maxlength="9" value="<%= prBasic.getE01POSDAC()%>" 
						  oncontextmenu="showPopUp(accountHelp,'E01POSDAC','01','','','','')">
                        <input type="text" name="E01POSDSB" size="6" maxlength="5" value="<%= prBasic.getE01POSDSB()%>" >
                        <input type="text" name="E01POSDCS" size="7" maxlength="6" value="<%= prBasic.getE01POSDCS()%>" >
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="44%" > 
                        <div align="right">Cuenta de Credito :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01POSCBR" size="4" maxlength="3" value="<%= prBasic.getE01POSCBR()%>" 
						oncontextmenu="showPopUp(branchHelp,'E01POSCBR','01','','','','')">
                        <input type="text" name="E01POSCCY" size="4" maxlength="3" value="<%= prBasic.getE01POSCCY()%>" 
						oncontextmenu="showPopUp(currencyHelp,'E01POSCCY','01','','','','')"	>
                        <input type="text" name="E01POSCGL" size="14" maxlength="12" value="<%= prBasic.getE01POSCGL()%>" >
                        <br>
                        <input type="text" name="E01POSCAC" size="10" maxlength="9" value="<%= prBasic.getE01POSCAC()%>" 
						oncontextmenu="showPopUp(accountHelp,'E01POSCAC','01','','','','')">
                        <input type="text" name="E01POSCSB" size="6" maxlength="5" value="<%= prBasic.getE01POSCSB()%>" >
                        <input type="text" name="E01POSCCS" size="7" maxlength="6" value="<%= prBasic.getE01POSCCS()%>" >
                      </td>
                    </tr>
                    <tr > 
                      <td nowrap width="44%" > 
                        <div align="right">Frecuencia de Pago :</div>
                      </td>
                      <td nowrap> 
                        <select name="E01POSFRQ">
                          <option value="D" <% if(prBasic.getE01POSFRQ().equals("D")) out.print("selected");%>>Diario</option>
                          <option value="W" <% if(prBasic.getE01POSFRQ().equals("W")) out.print("selected");%> selected>Semanal</option>
                          <option value="M" <% if(prBasic.getE01POSFRQ().equals("M")) out.print("selected");%>>Mensual</option>
                          <option value="V" <% if(prBasic.getE01POSFRQ().equals("V")) out.print("selected");%>>Variable</option>
                        </select>
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="44%" > 
                        <div align="right"> D&iacute;a de Pago :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01POSPMD" size="3" maxlength="2" value="<%= prBasic.getE01POSPMD()%>" >
                        <a href="javascript:GetCode('E01POSPMD','STATIC_rt_ciclo.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="absbottom" border="0" alt="help" ></a> 
                      </td>
                    </tr>
                    <tr   > 
                      <td nowrap width="44%" > 
                        <div align="right"> o N&uacute;mero de D&iacute;as :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01POSDYS" size="4" maxlength="3" value="<%= prBasic.getE01POSDYS()%>" >
                        (Para frecuencia de pagos variable solamentey) </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="44%" > 
                        <div align="right">N&uacute;mero de Pagos :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01POSNPM" size="6" maxlength="4" value="<%= prBasic.getE01POSNPM()%>" >
                        (9999 = Indefinido) </td>
                    </tr>
                    <tr > 
                      <td nowrap width="44%" > 
                        <div align="right">Tipo de Pago :</div>
                      </td>
                      <td nowrap> 
                        <input type="radio" name="E01FLD001" value="F" 
              <%if(prBasic.getE01FLD001().equals("F")) out.print("checked");%> checked>
                        Fijo 
                        <input type="radio" name="E01FLD001" value="V" 
              <%if(prBasic.getE01FLD001().equals("V")) out.print("checked");%>>
                        Variable </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="44%" > 
                        <div align="right">Autorizaci&oacute;n de Sobregiros :</div>
                      </td>
                      <td nowrap> 
                        <input type="radio" name="E01POSODA" value="Y" 
              <%if(prBasic.getE01POSODA().equals("Y")) out.print("checked");%> checked>
                        S&iacute; 
                        <input type="radio" name="E01POSODA" value="N" 
              <%if(prBasic.getE01POSODA().equals("N")) out.print("checked");%>>
                        No </td>
                    </tr>
                    <tr > 
                      <td nowrap width="44%" > 
                        <div align="right">Suspender Pago :</div>
                      </td>
                      <td nowrap> 
                        <input type="radio" name="E01POSSPM" value="Y" 
              <%if(prBasic.getE01POSSPM().equals("Y")) out.print("checked");%> checked>
                        S&iacute; 
                        <input type="radio" name="E01POSSPM" value="N" 
              <%if(prBasic.getE01POSSPM().equals("N")) out.print("checked");%>>
                        No </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="44%"> 
                        <div align="right">Reflejado en estado :</div>
                      </td>
                      <td nowrap > 
                        <input type="text" name="E01FLD002" size="6" maxlength="4" value="<%= prBasic.getE01FLD002()%>" >
                        (D&iacute;as) </td>
                    </tr>
                  </table>
                </div>
              </div>
            </td>
            <td  valign=top width="5%"> 
              <table id="TableTabR" cellspacing=0 cellpadding=0 border="0">
                <tr > 
                  <td  id="Tab4" class="tabnormalv" onClick="showTab(4,'E01POSBBO')"> 
                    <div  align="right" >Beneficiario</div>
                  <td> 
                </tr>
                <tr > 
                  <td  id="Tab5" class="tabnormalv" onClick="showTab(5,'E01POSBC1')"> 
                    <div  align="right" >Detalle de Pagos </div>
                  <td> 
                </tr>
                <tr > 
                  <td  id="Tab6" class="tabnormalv" onClick="showTab(6,'E01POSBC5')"> 
                    <div  align="right" >Informaci&oacute;n Banco a Banco </div>
                  <td> 
                </tr>
                <tr > 
                  <td  id="Tab7" class="tabnormalv" onClick="showTab(7,'E01POTORD')"> 
                    <div align="right">Telex</div>
                  <td> 
                </tr>
                <tr > 
                  <td  id="Tab8" class="tabnormalv" onClick="showTab(8,'E01POSDT1')"> 
                    <div  align="right" >Ordenes de Pago Simple</div>
                  <td> 
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
