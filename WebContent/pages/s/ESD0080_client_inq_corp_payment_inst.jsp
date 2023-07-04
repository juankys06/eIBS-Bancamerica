<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Instrucciones de Pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="pymInst" class="datapro.eibs.beans.ESD008211Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  builtNewMenu(client_inq_corp_opt);

</SCRIPT>

<SCRIPT Language="Javascript">
  
  function showTab(index,name){
  
  for(var i=0;i<6;i++){
   document.all["Tab"+i].className="tabnormalv";
   document.all["dataTab"+i].style.display="none";
   }
  if(index < 6) {
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
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%> 

<h3 align="center">Instrucciones de Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_maint.jsp,EPR0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0082">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="64">
  <INPUT TYPE=HIDDEN NAME="E11CCY" VALUE="<%= pymInst.getE11CCY()%>">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="S">
  <input type=HIDDEN name="E11CUS" value="<%= pymInst.getE11CUS()%>">
  <input type=HIDDEN name="E11TYP" value="<%= pymInst.getE11TYP()%>">
  <div id="OtherOpt"> 
<table class="tableinfo">
  <tr > 
    <td> 
          <table cellspacing="0" cellpadding="2" class="tbhead" width="100%"  align="center">
            <tr> 
              <td nowrap width="10%" align="right"> Cliente: </td>
              <td nowrap width="12%" align="left"> <%= userPO.getHeader1()%> </td>
              <td nowrap width="6%" align="right">ID: </td>
              <td nowrap width="14%" align="left"> <%= userPO.getHeader2()%> </td>
              <td nowrap width="8%" align="right"> Nombre: </td>
              <td nowrap width="50%"align="left"> <%= userPO.getHeader3()%> </td>
            </tr>
            <tr>
              <td nowrap width="10%" align="right">Moneda :</td>
              <td nowrap width="12%" align="left"><%= pymInst.getE11CCY()%></td>
              <td nowrap width="6%" align="right">Tipo :</td>
              <td nowrap width="14%" align="left"><% if(pymInst.getE11TYP().equals("1")) out.print("Default");
   else if(pymInst.getE11TYP().equals("2")) out.print("Ionversiones");
   else if(pymInst.getE11TYP().equals("3")) out.print("Moneda Extranjera");
   else if(pymInst.getE11TYP().equals("4")) out.print("FRA");
   else if(pymInst.getE11TYP().equals("0")) out.print("FED");
   else if(pymInst.getE11TYP().equals("C")) out.print("CLS Predefinido");
   else if(pymInst.getE11TYP().equals("D")) out.print("CLS Moneda Extranjera");
   else if(pymInst.getE11TYP().equals("E")) out.print("CLS Inversiones");
   else if(pymInst.getE11TYP().equals("F")) out.print("CLS FRA");      
   else out.print("");%></td>
              <td nowrap width="8%" align="right">&nbsp;</td>
              <td nowrap width="50%"align="left">&nbsp;</td>
            </tr>
          </table>
    </td>
  </tr>
</table>
    <h4>Basic Information</h4>
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
                          <td nowrap id="Tab0" class="tabhighlightl" onClick="showTab(0,'E11PYC')"> 
                            <div nowrap >Corresponsal Pagador</div>
                          <td> 
                        </tr>
                        <tr> 
                          <td nowrap id="Tab1" class="tabnormalv" onClick="showTab(1,'E11RCC')"> 
                            <div nowrap >Corresponsal Receptor</div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab2" class="tabnormalv" onClick="showTab(2,'E11PI1')"> 
                            <div >Pagador Intermediario</div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab3" class="tabnormalv" onClick="showTab(3,'E11RI1')"> 
                            <div nowrap >Intermediario Receptor</div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab4" class="tabnormalv" onClick="showTab(4,'E11AW1')"> 
                            <div nowrap >Cuenta con</div>
                          <td> 
                        </tr>
                        <tr > 
                          <td nowrap id="Tab5" class="tabnormalv" onClick="showTab(5,'E11BN1')"> 
                            <div nowrap >Beneficiario</div>
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
                                <div align=center>
                                No. Cliente : 
                                <input type="text" readonly  name="E11PYC" size="9" maxlength="9" value="<%= pymInst.getE11PYC()%>" onkeypress="enterInteger()">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PC1" size="36" maxlength="35" value="<%= pymInst.getE11PC1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PC2" size="36" maxlength="35" value="<%= pymInst.getE11PC2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PC3" size="36" maxlength="35" value="<%= pymInst.getE11PC3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PC4" size="36" maxlength="35" value="<%= pymInst.getE11PC4()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                        <div id="dataTab1" style="display: none"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                No. Cliente : 
                                <input type="text" readonly  name="E11RCC" size="9" maxlength="9" value="<%= pymInst.getE11RCC()%>" onkeypress="enterInteger()">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RC1" size="36" maxlength="35" value="<%= pymInst.getE11RC1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RC2" size="36" maxlength="35" value="<%= pymInst.getE11RC2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RC3" size="36" maxlength="35" value="<%= pymInst.getE11RC3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RC4" size="36" maxlength="35" value="<%= pymInst.getE11RC4()%>">
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
                                <input type="text" readonly  name="E11PI1" size="36" maxlength="35" value="<%= pymInst.getE11PI1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PI2" size="36" maxlength="35" value="<%= pymInst.getE11PI2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PI3" size="36" maxlength="35" value="<%= pymInst.getE11PI3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11PI4" size="36" maxlength="35" value="<%= pymInst.getE11PI4()%>">
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
                                <input type="text" readonly  name="E11RI1" size="36" maxlength="35" value="<%= pymInst.getE11RI1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RI2" size="36" maxlength="35" value="<%= pymInst.getE11RI2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RI3" size="36" maxlength="35" value="<%= pymInst.getE11RI3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11RI4" size="36" maxlength="35" value="<%= pymInst.getE11RI4()%>">
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
                                <input type="text" readonly  name="E11AW1" size="36" maxlength="35" value="<%= pymInst.getE11AW1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11AW2" size="36" maxlength="35" value="<%= pymInst.getE11AW2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11AW3" size="36" maxlength="35" value="<%= pymInst.getE11AW3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                <input type="text" readonly  name="E11AW4" size="36" maxlength="35" value="<%= pymInst.getE11AW4()%>">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                        <div id="dataTab5" style="display: none"> 
                          <table width="94%" border="0">
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                  <input type="text" readonly  name="E11BN1" size="36" maxlength="35" value="<%= pymInst.getE11BN1()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                  <input type="text" readonly  name="E11BN2" size="36" maxlength="35" value="<%= pymInst.getE11BN2()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                  <input type="text" readonly  name="E11BN3" size="36" maxlength="35" value="<%= pymInst.getE11BN3()%>">
                                </div>
                              </td>
                            </tr>
                            <tr> 
                              <td width="53%"> 
                                <div align=center>
                                  <input type="text" readonly  name="E11BN4" size="36" maxlength="35" value="<%= pymInst.getE11BN4()%>">
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
    
  </div>
  <h4>Cuenta Contrapartida</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero de Cuenta:</div>
            </td>
             <td nowrap width="75%" colspan="3" > 
              <input type="text" name="E11CTA" size="12" maxlength="9" value="<% if (pymInst.getE11OFB().equals("")) {out.print(pymInst.getE11OFA().trim());}%>" readonly>
              
              </td>
          </tr> 
          <tr id="trclear">  
          	<td nowrap width="25%" colspan="4"><div align="center">O</div>
          	 </td>
          </tr> 
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="25%" > 
              <input type="text"  name="E11OFB" size="3" maxlength="2" value="<%= pymInst.getE11OFB().trim()%>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero de Cuenta Contable:</div>
            </td>
            <td nowrap width="25%" > 
              <input type="text"  name="E11GL" size="16" maxlength="16" value="<% if (!pymInst.getE11OFB().equals("")) {out.print(pymInst.getE11OFA().trim());}%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
  

</body>
</html>
