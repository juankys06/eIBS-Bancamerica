<html>
<head>
<title>Información General</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnGenInf" class="datapro.eibs.beans.EDL015005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(ln_m_opt);


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
<h3 align="center">Informaci&oacute;n General <%= lnGenInf.getE05DSCPRO().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_gen_inf.jsp,EDL0150"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="24">
  <INPUT TYPE=HIDDEN NAME="E05DEABNK"  value="<%= lnGenInf.getE05DEABNK().trim()%>">
  <input type=HIDDEN name="E05DEATYP"  value="<%= lnGenInf.getE05DEATYP().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E05DEACUN" size="9" maxlength="9" value="<%= lnGenInf.getE05DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E05CUSNA1" size="45" maxlength="45" value="<%= lnGenInf.getE05CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E05DEAACC" size="12" maxlength="12" value="<%= lnGenInf.getE05DEAACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E05DEACCY2" size="3" maxlength="3" value="<%= lnGenInf.getE05DEACCY().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E05DEAPRO" size="4" maxlength="4" value="<%= lnGenInf.getE05DEAPRO().trim()%>" >
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
      
  <h4>Informaci&oacute;n General</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trclear">
            <td nowrap width="32%" >
              <div align="right">Tipo de Inter&eacute;s :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEAICT" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAICT().trim()%>">
              <a href="javascript:GetCode('E05DEAICT','STATIC_cd_formula.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></td>
            <td nowrap width="24%" >
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEABAS" size="4" maxlength="3" value="<%= lnGenInf.getE05DEABAS().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">C&aacute;lculo de Inter&eacute;s Normal :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEAIFL" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAIFL().trim()%>">
              <a href="javascript:lnGetOver('E05DEAIFL','STATIC_ln_prov.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">C&aacute;lculo de Inter&eacute;s Mora :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEAPCL" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPCL().trim()%>" >
              <a href="javascript:lnGetOver('E05DEAPCL','STATIC_ln_mor.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEAWHF" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAWHF().trim()%>">
              <a href="javascript:GetCode('E05DEAWHF','STATIC_cd_taxes.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Per&iacute;odo de Gracia :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEAGPD" size="3" maxlength="2" value="<%= lnGenInf.getE05DEAGPD().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Comisionista :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEABRK" size="4" maxlength="3" value="<%= lnGenInf.getE05DEABRK().trim()%>">
              <a href="javascript:GetBroker('E05DEABRK')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Direcci&oacute;n de Correos :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEAMLA" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAMLA().trim()%>">
              <a href="javascript:GetMailing('E05DEAMLA',document.forms[0].E05DEACUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Direcciones de Correo del Cliente" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Tasa del Comisionista :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEABCP" size="10" maxlength="9" value="<%= lnGenInf.getE05DEABCP().trim()%>" >
            </td>
            <td nowrap width="24%" > 
              <div align="right">Vencimiento en Feriados :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="hidden" name="E05DEAHFQ" value="<%= lnGenInf.getE05DEAHFQ()%>">
              <input type="radio" name="CE05DEAHFQ" value="1" onClick="document.forms[0].E05DEAHFQ.value='1'"
			  <%if(lnGenInf.getE05DEAHFQ().equals("1")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE05DEAHFQ" value="2" onClick="document.forms[0].E05DEAHFQ.value='2'"
			  <%if(lnGenInf.getE05DEAHFQ().equals("2")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Tabla Cargos :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEATLN" size="2" maxlength="2" value="<%= lnGenInf.getE05DEATLN().trim()%>">
              <a href="javascript:GetLoanTable('E05DEATLN',document.forms[0].E05DEATYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" width="25" height="25"></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">C&oacute;digo de Obligaci&oacute;n :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEAOBL" size="3" maxlength="2" value="<%= lnGenInf.getE05DEAOBL().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Tabla Tasas :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEARTB" size="2" maxlength="1" value="<%= lnGenInf.getE05DEARTB().trim()%>">
              <a href="javascript:GetRateTable('E05DEATRB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">C&oacute;digo de Usuario :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEARRC" size="3" maxlength="2" value="<%= lnGenInf.getE05DEARRC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Tipo de Relaciones 1 :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEAPAR" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPAR().trim()%>">
              <a href="javascript:GetRel1('E05DEAPAR','STATIC_ln_rel1.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Clase de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEACLF" size="2" maxlength="1" value="<%= lnGenInf.getE05DEACLF().trim()%>">
              <a href="javascript:GetClassCred('E05DEACLF','STATIC_ln_cred_class.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Cuenta de Relaciones 1 :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEAPAC" size="12" maxlength="12" value="<%= lnGenInf.getE05DEAPAC().trim()%>">
              <a href="javascript:GetAccount('E05DEAPAC',document.forms[0].E05DEABNK.value,'','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Condici&oacute;n del Cr&eacute;dito :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E05DEADLC" size="2" maxlength="1" value="<%= lnGenInf.getE05DEACLF().trim()%>">
              <a href="javascript:GetCredCond('E05DEADLC','STATIC_ln_cred_cond.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Tipo de Relaciones 2 :</div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEARET" size="2" maxlength="1" value="<%= lnGenInf.getE05DEARET().trim()%>">
              <a href="javascript:GetRel2('E05DEARET','STATIC_ln_rel2.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Pr&eacute;stamo a Demanda :</div>
            </td>
            <td nowrap width="27%">
              <input type="hidden" name="E05DEALNC" value="<%= lnGenInf.getE05DEALNC()%>">
              <input type="radio" name="CE05DEALNC" value="Y" onClick="document.forms[0].E05DEALNC.value='Y'"
			  <%if(lnGenInf.getE05DEALNC().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE05DEALNC" value="N" onClick="document.forms[0].E05DEALNC.value='N'"
			  <%if(lnGenInf.getE05DEALNC().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Cuenta de Relaciones 2 : </div>
            </td>
            <td nowrap width="17%" >
              <input type="text" name="E05DEAREA" size="12" maxlength="12" value="<%= lnGenInf.getE05DEAREA().trim()%>">
              <a href="javascript:GetAccount('E05DEAREA',document.forms[0].E05DEABNK.value,'10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right"> Porciento de Participaciones :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E05DEACCF" size="4" maxlength="3" value="<%= lnGenInf.getE05DEACCF().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Prioridad de Pagos </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"  > 
              <div align="center">
                <input type="text" name="E05DEAPP1" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP1().trim()%>">
              </div>
            </td>
            <td nowrap width="14%"  > 
              <div align="center"> 
                <input type="text" name="E05DEAPP2" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" name="E05DEAPP3" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP3().trim()%>">
              </div>
            </td>
            <td nowrap width="22%"  > 
              <div align="center"> 
                <input type="text" name="E05DEAPP4" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP4().trim()%>">
              </div>
            </td>
            <td nowrap width="17%" > 
              <div align="center"> 
                <input type="text" name="E05DEAPP5" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP5().trim()%>">
              </div>
            </td>
            <td nowrap width="15%"  > 
              <div align="center"> 
                <input type="text" name="E05DEAPP6" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP6().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="center">Principal</div>
            </td>
            <td nowrap width="14%" > 
              <div align="center">Intereses</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Mora</div>
            </td>
            <td nowrap width="22%" > 
              <div align="center">Impuestos / Comisiones</div>
            </td>
            <td nowrap width="17%" > 
              <div align="center">Deducciones</div>
            </td>
            <td nowrap width="15%" > 
              <div align="center">I.V.A.</div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit"  value="Enviar">
  </div>
</p>
  <h4>&nbsp;</h4>
  </form>
</body>
</html>
