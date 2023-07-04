<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Swift Format</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="swift" class="datapro.eibs.beans.ESWF10001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
    builtNewMenu(sfm_bo_opt);
 </SCRIPT>

<SCRIPT Language="javascript">

 function Validate(){
   document.forms[0].CODOPT.value = "V";
   document.forms[0].submit();
}
 function SubmitAcc(){
   document.forms[0].CODOPT.value = "S";
   document.forms[0].submit();
}
</SCRIPT>

</head>
<body >
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> <%
	if ( swift.getE01SWFCUN().equals("0") ) {
%> 
  <table class="tbenter" width=100% height=100%>
    <tr> 
      <td> 
        
      <div align="center"> <b> No hay resultados que correspondan con su criterio 
        de b&uacute;squeda</b> </div>
      </td>
    </tr>
  </table>
  
<%
	}
	else {
%>
<%
  if ( swift.getE01SWFFMT().equals("1000") ) {
 %>
<h3 align="center"> Mensaje FED</h3>
<%
	}
	else {
%>
<h3 align="center">Mensaje Swift Formato MT - <%= swift.getE01SWFFMT()%></h3>
<%}%>
  <input type="hidden" name="E01SWFFMT"  value="<%= swift.getE01SWFFMT()%>" >
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_sf_format.jsp,ESWF100M"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140" >
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="E01SWFCUN"  value="<%= swift.getE01SWFCUN()%>" >
                <%= swift.getE01SWFCUN()%> - 
                <input type="hidden" name="E01SWFNA1"  value="<%= swift.getE01SWFNA1()%>" >
                <%= swift.getE01SWFNA1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= swift.getE01SWFNA2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">
                <input type="hidden" name="CODOPT" >
                <input type="hidden" name="SCREEN"  value="32" >
              </div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= swift.getE01SWFNA3()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo" width="736">
    <tr id="trdark"> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">N&uacute;mero de Cuenta :</div>
            </td>
            <td nowrap >
              <input type="hidden" name="E01SWFACC"  value="<%= swift.getE01SWFACC()%>" >
              <%= swift.getE01SWFACC()%> </td>
            <td nowrap >
              <div align="right">Fecha :</div>
            </td>
            <td nowrap ><%= Util.formatDate(swift.getE01SWFOD1(),swift.getE01SWFOD2(),swift.getE01SWFOD3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="hidden" name="E01SWFREF"  value="<%= swift.getE01SWFREF()%>" >
              <%= swift.getE01SWFREF()%> </td>
            <td nowrap width="18%" > 
              <div align="right"> Referencia Relativa :</div>
            </td>
            <td nowrap width="20%" ><%= swift.getE01SWFRLR()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="23%" ><%= Util.formatDate(swift.getE01SWFVD1(),swift.getE01SWFVD2(),swift.getE01SWFVD3())%> 
            </td>
            <td nowrap width="18%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="20%"><%= swift.getE01SWFCCY().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="23%" ><%= Util.fcolorCCY(swift.getE01SWFAMT())%> 
            </td>
            <td nowrap width="18%" >&nbsp;</td>
            <td nowrap width="20%"> 
              <div align="left"> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n del Mensaje</h4>
  <table  class="tableinfo">
    <tr id="trdark"> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Identificaci&oacute;n Enviador : </div>
            </td>
            <td nowrap > 
              <input type="text" name="E01SWFSID" size="15" maxlength="12" value="<%= swift.getE01SWFSID()%>" 
			  readonly>
            </td>
            <td nowrap > 
              <div align="right">Identificaci&oacute;n Recibidor :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01SWFRID" size="15" maxlength="12" value="<%= swift.getE01SWFRID()%>" 
			  >
              <a href="javascript:GetSwiftIdDesc('E01SWFRID','E01SWFRNM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a> 
              <input type="text" name="E01SWFRNM" size="40" maxlength="35" value="<%= swift.getE01SWFRNM()%>" 
			  >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo">
    <tr id="trdark" > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="26%" >Banco Ordenante 52 </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="left">Intermediario 56 </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <select name="E01SWFOBO">
                  <option value=" " <% if (!(swift.getE01SWFOBO().equals("D") ||swift.getE01SWFOBO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFOBO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFOBO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFOB1" size="40" maxlength="35" value="<%= swift.getE01SWFOB1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <select name="E01SWFINO" >
                  <option value=" " <% if (!(swift.getE01SWFINO().equals("D") ||swift.getE01SWFINO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFINO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFINO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFIN1" size="40" maxlength="35" value="<%= swift.getE01SWFIN1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"><a href="javascript:DisabledRef('E01SWFIN1','E01SWFIN4',document.forms[0].E01SWFINO.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFOB2" size="40" maxlength="35" value="<%= swift.getE01SWFOB2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFIN2" size="40" maxlength="35" value="<%= swift.getE01SWFIN2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFOB3" size="40" maxlength="35" value="<%= swift.getE01SWFOB3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFIN3" size="40" maxlength="35" value="<%= swift.getE01SWFIN3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFOB4" size="40" maxlength="35" value="<%= swift.getE01SWFOB4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFIN4" size="40" maxlength="35" value="<%= swift.getE01SWFIN4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" >Cliente Ordenante 50 </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" >Cuenta con el Banco 57 </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <select name="E01SWFORO">
                  <option value=" " <% if (!(swift.getE01SWFORO().equals("D") ||swift.getE01SWFORO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFORO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFORO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFOR1" size="40" maxlength="35" value="<%= swift.getE01SWFOR1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <select name="E01SWFBBO">
                  <option value=" " <% if (!(swift.getE01SWFBBO().equals("D") ||swift.getE01SWFBBO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFBBO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFBBO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFBB1" size="40" maxlength="35" value="<%= swift.getE01SWFBB1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"><a href="javascript:DisabledRef('E01SWFBB1','E01SWFBB4',document.forms[0].E01SWFBBO.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFOR2" size="40" maxlength="35" value="<%= swift.getE01SWFOR2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBB2" size="40" maxlength="35" value="<%= swift.getE01SWFBB2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFOR3" size="40" maxlength="35" value="<%= swift.getE01SWFOR3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBB3" size="40" maxlength="35" value="<%= swift.getE01SWFBB3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFOR4" size="40" maxlength="35" value="<%= swift.getE01SWFOR4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBB4" size="40" maxlength="35" value="<%= swift.getE01SWFBB4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" >Banco Corresponsal Enviador 53 </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" >Beneficiario 58/59</td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <select name="E01SWFSCO">
                  <option value=" " <% if (!(swift.getE01SWFSCO().equals("D") ||swift.getE01SWFSCO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFSCO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFSCO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFSC1" size="40" maxlength="35" value="<%= swift.getE01SWFSC1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"><a href="javascript:DisabledRef('E01SWFSC1','E01SWFSC4',document.forms[0].E01SWFSCO.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <select name="E01SWFBCO">
                  <option value=" " <% if (!(swift.getE01SWFBCO().equals("D") ||swift.getE01SWFBCO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFBCO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFBCO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFBC1" size="40" maxlength="35" value="<%= swift.getE01SWFBC1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"><a href="javascript:DisabledRef('E01SWFBC1','E01SWFBC4',document.forms[0].E01SWFBCO.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFSC2" size="40" maxlength="35" value="<%= swift.getE01SWFSC2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBC2" size="40" maxlength="35" value="<%= swift.getE01SWFBC2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFSC3" size="40" maxlength="35" value="<%= swift.getE01SWFSC3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBC3" size="40" maxlength="35" value="<%= swift.getE01SWFBC3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFSC4" size="40" maxlength="35" value="<%= swift.getE01SWFSC4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBC4" size="40" maxlength="35" value="<%= swift.getE01SWFBC4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" >Banco Corresponsal Recibidor 54</td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" >Informaci&oacute;n Banco a Banco 72</td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <select name="E01SWFRBO">
                  <option value=" " <% if (!(swift.getE01SWFRBO().equals("D") ||swift.getE01SWFRBO().equals("A"))) out.print("selected"); %>></option>
                  <option value="A" <% if(swift.getE01SWFRBO().equals("A")) out.print("selected");%>>A</option>
                  <option value="D" <% if(swift.getE01SWFRBO().equals("D")) out.print("selected");%>>D</option>
                </select>
                <input type="text" name="E01SWFRC1" size="40" maxlength="35" value="<%= swift.getE01SWFRC1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"><a href="javascript:DisabledRef('E01SWFRC1','E01SWFRC4',document.forms[0].E01SWFRBO.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBK1" size="40" maxlength="35" value="<%= swift.getE01SWFBK1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFRC2" size="40" maxlength="35" value="<%= swift.getE01SWFRC2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBK2" size="40" maxlength="35" value="<%= swift.getE01SWFBK2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFRC3" size="40" maxlength="35" value="<%= swift.getE01SWFRC3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBK3" size="40" maxlength="35" value="<%= swift.getE01SWFBK3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"> 
                <input type="text" name="E01SWFRC4" size="40" maxlength="35" value="<%= swift.getE01SWFRC4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
            <td nowrap width="22%" > 
              <div align="right"> 
                <input type="text" name="E01SWFBK4" size="40" maxlength="35" value="<%= swift.getE01SWFBK4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="left"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
    </tr>
  </table>
</form>
<%}%>
</body>
</html>
