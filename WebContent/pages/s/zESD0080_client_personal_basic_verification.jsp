<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "employment" class= "datapro.eibs.beans.ESD008006Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
       
     builtNewMenu(client_personal_opt);
       
   </SCRIPT>

<%
}
%>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Verificaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <input type="hidden" name="E01LGT" size="15" maxlength="10" value="<%= client.getE01LGT().trim()%>">
  </p>
  <h4>Telef&oacute;nica </h4>
  <div align="left"> 
    <table class="tableinfo" >
      <tr > 
        <td wrap > 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
            <tr id="trclear"> 
              <td wrap colspan="5"><b>Titular 
                <input type="text" name="E06CP13" size="31" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
                </b></td>
            </tr>
            <tr id="trclear"> 
              <td wrap colspan="2"> 
                <div align="right">Empresa :</div>
              </td>
              <td wrap colspan="3"> 
                <input type="text" name="E06CP1" size="31" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Direcci&oacute;n :</div>
              </td>
              <td wrap colspan="3"> 
                <input type="text" name="E06CP2" size="31" maxlength="30" value="<%= employment.getE06CP2().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Provincia :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT2" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
              <td wrap> 
                <div align="right">Cant&oacute;n :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT222" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Parroquia :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT22" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
              <td wrap> 
                <div align="right">Barrio :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT223" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Sector :</div>
              </td>
              <td wrap> 
                <input type="text" name="E06EPT224" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
              <td wrap> 
                <div align="right">Tel&eacute;fono :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06RUC2" size="16" maxlength="15" value="<%= employment.getE06RUC().trim()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap > 
                <div align="right">Tipo Empresa :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E06EPT" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              </td>
              <td nowrap colspan="2" > 
                <div align="right">Antiguedad Laboral :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E06EPT3" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap > 
                <div align="right">Verificado por :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E06UC5" value="<%= employment.getE06UC5().trim()%>" size="5" maxlength="4">
                <a href="javascript:GetCodeCNOFC('E06UC5','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              </td>
              <td nowrap colspan="2" > 
                <div align="right">Estado :</div>
              </td>
              <td nowrap > 
                <select name="E06NEM">
                  <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                  <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                  <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                </select>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap > 
                <div align="right">Observaciones :</div>
              </td>
              <td nowrap colspan="4" > 
                <input type="text" name="E06SWM" size="50" maxlength="50" value="<%= employment.getE06SWM().trim()%>" onKeyPress="enterInteger()">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap> 
                <div align="right"></div>
              </td>
              <td nowrap colspan="4" > 
                <div align="left"> 
                  <input type="text" name="E06SWM2" size="50" maxlength="50" value="<%= employment.getE06SWM().trim()%>" onKeyPress="enterInteger()">
                </div>
              </td>
            </tr>
            <!--<tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">
            </td>
          </tr>-->
          </table>
        </td>
      </tr>
    </table>
    <br><table class="tableinfo" >
      <tr > 
        <td wrap > 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
            <tr id="trclear"> 
              <td wrap colspan="5"><b>Aval 
                <input type="text" name="E06CP14" size="31" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
                </b></td>
            </tr>
            <tr id="trclear"> 
              <td wrap colspan="2"> 
                <div align="right">Empresa :</div>
              </td>
              <td wrap colspan="3"> 
                <input type="text" name="E06CP12" size="31" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Direcci&oacute;n :</div>
              </td>
              <td wrap colspan="3"> 
                <input type="text" name="E06CP22" size="31" maxlength="30" value="<%= employment.getE06CP2().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Provincia :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT23" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
              <td wrap> 
                <div align="right">Cant&oacute;n :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT2222" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Parroquia :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT225" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
              <td wrap> 
                <div align="right">Barrio :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06EPT2232" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td wrap colspan="2"> 
                <div align="right">Sector :</div>
              </td>
              <td wrap> 
                <input type="text" name="E06EPT2242" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
              <td wrap> 
                <div align="right">Tel&eacute;fono :</div>
              </td>
              <td wrap> 
                <div align="left"> 
                  <input type="text" name="E06RUC22" size="16" maxlength="15" value="<%= employment.getE06RUC().trim()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap > 
                <div align="right">Tipo Empresa :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E06EPT4" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              </td>
              <td nowrap colspan="2" > 
                <div align="right">Antiguedad Laboral :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E06EPT32" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap > 
                <div align="right">Verificado por :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E06UC52" value="<%= employment.getE06UC5().trim()%>" size="5" maxlength="4">
                <a href="javascript:GetCodeCNOFC('E06UC5','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              </td>
              <td nowrap colspan="2" > 
                <div align="right">Estado :</div>
              </td>
              <td nowrap > 
                <select name="select">
                  <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                  <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                  <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                </select>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap > 
                <div align="right">Observaciones :</div>
              </td>
              <td nowrap colspan="4" > 
                <input type="text" name="E06SWM3" size="50" maxlength="50" value="<%= employment.getE06SWM().trim()%>" onKeyPress="enterInteger()">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap> 
                <div align="right"></div>
              </td>
              <td nowrap colspan="4" > 
                <div align="left"> 
                  <input type="text" name="E06SWM22" size="50" maxlength="50" value="<%= employment.getE06SWM().trim()%>" onKeyPress="enterInteger()">
                </div>
              </td>
            </tr>
            <!--<tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">
            </td>
          </tr>-->
          </table>
        </td>
      </tr>
    </table>
    <h4>Domiciliaria</h4>
    <div align="left"> 
      <table class="tableinfo" >
        <tr > 
          <td wrap > 
            <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
              <tr id="trclear"> 
                <td wrap> 
                  <div align="right">Nombre :</div>
                </td>
                <td wrap colspan="2"><b> 
                  <input type="text" name="E06CP132" size="31" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
                  </b></td>
              </tr>
              <tr id="trclear"> 
                <td wrap> 
                  <div align="right">Identificaci&oacute;n :</div>
                </td>
                <td wrap colspan="2"> 
                  <input type="text" name="E06CP15" size="16" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
                  <input type="text" name="E06EPT242" size="6" maxlength="4" value="<%= employment.getE06EPT().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E06EPT','46')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right">Estado Civil :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <select name="select3">
                      <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                      <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                      <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                    </select>
                  </div>
                </td>
                <td wrap> 
                  <div align="right">Residencia :</div>
                </td>
                <td wrap> 
                  <select name="select4">
                    <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                    <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                    <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                  </select>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap colspan="4"> 
                  <div align="left"><b>Antecedentes de la Vivienda</b></div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right">Tipo de Vivienda :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <select name="select2">
                      <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                      <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                      <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                    </select>
                  </div>
                </td>
                <td wrap> 
                  <div align="right">Estado :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <select name="select5">
                      <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                      <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                      <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                    </select>
                  </div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right">Construcci&oacute;n :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <select name="select6">
                      <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                      <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                      <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                    </select>
                  </div>
                </td>
                <td wrap> 
                  <div align="right">Acceso :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <select name="select7">
                      <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                      <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                      <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                    </select>
                  </div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap colspan="4"> 
                  <div align="left"><b>Bienes</b></div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox" value="checkbox">
                  </div>
                </td>
                <td wrap>Autom&oacute;vil</td>
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox2" value="checkbox">
                  </div>
                </td>
                <td wrap>Televisor</td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox3" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Refrigerador</div>
                </td>
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox7" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Lavadora</div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox4" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">VHS Video</div>
                </td>
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox8" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">DVD Video</div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox5" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Radio</div>
                </td>
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox9" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Stereo</div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox6" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Cocina de Gas</div>
                </td>
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox10" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Cocina Electrica</div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox62" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Horno Microondas</div>
                </td>
                <td wrap> 
                  <div align="right"> 
                    <input type="checkbox" name="checkbox63" value="checkbox">
                  </div>
                </td>
                <td wrap> 
                  <div align="left">Computadora</div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap colspan="4">&nbsp;</td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right">Verificado por :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <input type="text" name="E06UC522" value="<%= employment.getE06UC5().trim()%>" size="5" maxlength="4">
                    <a href="javascript:GetCodeCNOFC('E06UC5','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></div>
                </td>
                <td wrap> 
                  <div align="right">Estado :</div>
                </td>
                <td wrap> 
                  <div align="left"> 
                    <select name="select8">
                      <option value=" " <% if (!(employment.getE06NEM().equals("1") || employment.getE06NEM().equals("2")) ) out.print("selected");%>></option>
                      <option value="1" <% if (employment.getE06NEM().equals("1")) out.print("selected"); %>="">Fijo</option>
                      <option value="2" <% if (employment.getE06NEM().equals("2")) out.print("selected"); %>="">Indefinido</option>
                    </select>
                  </div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right">Observaciones :</div>
                </td>
                <td wrap colspan="3"> 
                  <div align="left"> 
                    <input type="text" name="E06SWM32" size="50" maxlength="50" value="<%= employment.getE06SWM().trim()%>" onKeyPress="enterInteger()">
                  </div>
                </td>
              </tr>
              <tr id="trdark"> 
                <td wrap> 
                  <div align="right"></div>
                </td>
                <td wrap colspan="3"> 
                  <div align="left"></div>
                  <div align="right"></div>
                  <div align="left"> 
                    <input type="text" name="E06SWM33" size="50" maxlength="50" value="<%= employment.getE06SWM().trim()%>" onKeyPress="enterInteger()">
                  </div>
                </td>
              </tr>
              <!--<tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">
            </td>
          </tr>-->
            </table>
          </td>
        </tr>
      </table>
    </div>
    
    <h4>&nbsp;</h4>
    </div>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>

