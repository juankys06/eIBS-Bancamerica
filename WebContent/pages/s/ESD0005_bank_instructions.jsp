<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Instrucciones de Pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="pymInst" class="datapro.eibs.beans.ESD000506Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



<SCRIPT Language="Javascript">
  
  function showTab(index,name){
  
  for(var i=0;i<5;i++){
   document.all["Tab"+i].className="tabnormalv";
   document.all["dataTab"+i].style.display="none";
   }
  if(index < 5) {
    document.all["Tab"+index].className="tabhighlightl";
	colTab.className="tabdataleft";
  }else {
	document.all["Tab"+index].className="tabhighlightr";
	colTab.className="tabdataright";
  }
  document.all["dataTab"+index].style.display="";
  document.all[name].focus();
  }
  
  function del() {
    document.forms[0].ACTION.value = 'D';
    document.forms[0].submit();
  }
 
</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
%>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
<% 
 }
%>


<h3 align="center">Instrucciones del Banco<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bank_instructions.jsp,ESD0005"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="E06CCY" VALUE="<%= pymInst.getE06CCY()%>">
    <INPUT TYPE=HIDDEN NAME="E06BRN" VALUE="<%= pymInst.getE06BRN()%>">
    <INPUT TYPE=HIDDEN NAME="E06BNK" VALUE="<%= pymInst.getE06BNK()%>">
  </p>
  <h4>Informaci&oacute;n General</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Banco / Sucursal : <%= pymInst.getE06BNK()%> / <%= pymInst.getE06BRN()%></div>
            </td>
            <td nowrap width="19%">
              <div align="right">Moneda : <%= pymInst.getE06CCY()%></div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Tipo : </div>
            </td>
            <td nowrap width="26%">
              <input type="text" name="E06TYP" size="5" maxlength="3" value="<%= pymInst.getE06TYP()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Swift Id :</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E06SWI" size="15" maxlength="12" value="<%= pymInst.getE06SWI()%>">
              <a href="javascript:GetSwiftId('E06SWI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="26%"> 
              <div align="right">Cliente Corresponsal :</div>
            </td>
            <td nowrap width="26%">
              <input type="text" name="E06CC1" size="12" maxlength="9" value="<%= pymInst.getE06CC1()%>">
              <a href="javascript:GetCustomerDescId('E06CC1','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Cuenta DDA 1 :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <input type="text" name="E06DDA" size="15" maxlength="12" value="<%= pymInst.getE06DDA()%>">
              <a href="javascript:GetAccount('E06DDA',document.forms[0].E06BNK.value,'RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Cuenta Contable 1 :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <input type="text" name="E06GLN" size="12" maxlength="9" value="<%= pymInst.getE06GLN()%>">
              <a href="javascript:GetLedger('E06GLN',document.forms[0].E06BNK.value,document.forms[0].E06CCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Cuenta DDA 2 :</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <input type="text" name="E06DD1" size="15" maxlength="12" value="<%= pymInst.getE06DD1()%>">
              <a href="javascript:GetAccount('E06DD1',document.forms[0].E06BNK.value,'RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Cuenta Contable 2 :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E06GL1" size="12" maxlength="9" value="<%= pymInst.getE06GL1()%>">
              <a href="javascript:GetLedger('E06GL1',document.forms[0].E06BNK.value,document.forms[0].E06CCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <p>&nbsp;</p><div id="OtherOpt"> 
    <h4>Informaci&oacute;n B&aacute;sica</h4>
    <table class="tableinfo" width="639">
      <tr > 
      <td > 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td colspan="5" align="center"> 
                <table id="TableTab" cellspacing=0 cellpadding=0 border="0" width="100%">
                  <tr id=trdark> 
                    <td nowrap valign="top" align="right"> 
                      <table id="TableTabL" cellspacing=0 cellpadding=0 border="0">
                        <tr> 
                          <td nowrap id="Tab0" class="tabhighlightl" onClick="showTab(0,'E06PYC')"> 
                            <div nowrap >Corresponsal Pagador</div>
                          <td> 
                        </tr>
                        <tr> 
                          <td nowrap id="Tab1" class="tabnormalv" onClick="showTab(1,'E06RCC')"> 
                            <div nowrap >Corresponsal Recibidor </div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab2" class="tabnormalv" onClick="showTab(2,'E06PI1')"> 
                            <div >Pagador Intermediario</div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab3" class="tabnormalv" onClick="showTab(3,'E06RI1')"> 
                            <div nowrap >Recibidor Intermediario</div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab4" class="tabnormalv" onClick="showTab(4,'E06AW1')"> 
                            <div nowrap >Cuenta con</div>
                          <td> 
                        </tr>
                      </table>
                    </td>
                    <td id="colTab" align="center" class="tabdataleft"> 
                      <div id="DataTab"> 
                        <div id="dataTab0"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center> N&uacute;mero de Cliente : 
                                  <input type="text" name="E06PYC" size="9" maxlength="9" value="<%= pymInst.getE06PYC()%>" onkeypress="enterInteger()">
                                <a href="javascript:GetCustomerDescId('E06PYC','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PC1" size="36" maxlength="35" value="<%= pymInst.getE06PC1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PC2" size="36" maxlength="35" value="<%= pymInst.getE06PC2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PC3" size="36" maxlength="35" value="<%= pymInst.getE06PC3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PC4" size="36" maxlength="35" value="<%= pymInst.getE06PC4()%>">
                                </div>
                              </td>
                            </tr> <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PC5" size="36" maxlength="35" value="<%= pymInst.getE06PC5()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                        <div id="dataTab1" style="display: none"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center> N&uacute;mero de Cliente :
<input type="text" name="E06RCC" size="9" maxlength="9" value="<%= pymInst.getE06RCC()%>" onkeypress="enterInteger()">
                                <a href="javascript:GetCustomerDescId('E06RCC','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RC1" size="36" maxlength="35" value="<%= pymInst.getE06RC1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RC2" size="36" maxlength="35" value="<%= pymInst.getE06RC2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RC3" size="36" maxlength="35" value="<%= pymInst.getE06RC3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RC4" size="36" maxlength="35" value="<%= pymInst.getE06RC4()%>">
                                </div>
                              </td>
                            </tr>
							<tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RC5" size="36" maxlength="35" value="<%= pymInst.getE06RC5()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                        <div id="dataTab2" style="display: none"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PI1" size="36" maxlength="35" value="<%= pymInst.getE06PI1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PI2" size="36" maxlength="35" value="<%= pymInst.getE06PI2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PI3" size="36" maxlength="35" value="<%= pymInst.getE06PI3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PI4" size="36" maxlength="35" value="<%= pymInst.getE06PI4()%>">
                                </div>
                              </td>
                            </tr>
							<tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06PI5" size="36" maxlength="35" value="<%= pymInst.getE06PI5()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                        <div id="dataTab3" style="display: none"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RI1" size="36" maxlength="35" value="<%= pymInst.getE06RI1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RI2" size="36" maxlength="35" value="<%= pymInst.getE06RI2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RI3" size="36" maxlength="35" value="<%= pymInst.getE06RI3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RI4" size="36" maxlength="35" value="<%= pymInst.getE06RI4()%>">
                                </div>
                              </td>
                            </tr>
							<tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06RI5" size="36" maxlength="35" value="<%= pymInst.getE06RI5()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                        <div id="dataTab4" style="display: none"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06AW1" size="36" maxlength="35" value="<%= pymInst.getE06AW1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06AW2" size="36" maxlength="35" value="<%= pymInst.getE06AW2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06AW3" size="36" maxlength="35" value="<%= pymInst.getE06AW3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06AW4" size="36" maxlength="35" value="<%= pymInst.getE06AW4()%>">
                                </div>
                              </td>
                            </tr>
							<tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" name="E06AW5" size="36" maxlength="35" value="<%= pymInst.getE06AW5()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
      </TD>
 </tr>
</table>
    <p>&nbsp;</p>
    <table width="100%">
      <tr> 
        <td width="50%"> 
          <p align="center"> <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="submit()">
          </p>
        </td>
      </tr>
    </table>

</div>

</form>
  

</body>
</html>
