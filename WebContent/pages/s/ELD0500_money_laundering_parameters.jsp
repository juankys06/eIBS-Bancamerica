<html>
<head>
<title>Parametros de Lavado de Dinero</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="launder" class="datapro.eibs.beans.ELD050001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Par&aacute;metros de Control de Lavado de Dinero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_overdraft.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSELD0500" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
	<INPUT TYPE=HIDDEN NAME="E01DLPBNK" value="<%= launder.getE01LDPBNK().trim()%>">
  <h4>Parametros para Reportar Excepciones</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Variaci&oacute;n en N&uacute;mero de Transacciones 
                :</div>
            </td>
            <td nowrap width="60%"> 
              <div align="left"> 
                <input type="text" name="E01LDPVNT" size="7" maxlength="5" value="<%= launder.getE01LDPVNT().trim()%>">
                <a href="javascript:GetLedger('E01OFCPRT',document.forms[0].E01OFCBNK.value,document.forms[0].E01OFCCCY.value,'')"> 
                </a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Variaci&oacute;n en Porcentaje de Montos :</div>
            </td>
            <td nowrap width="60%"> 
              <div align="left"> 
                <input type="text" name="E01LDPVAM" size="7" maxlength="5" value="<%= launder.getE01LDPVAM().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Parametros de C&oacute;digos de Transacci&oacute;n</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Dep&oacute;sitos Efectivo :</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDE1" size="3" maxlength="2" value="<%= launder.getE01LDPDE1().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDE2" size="3" maxlength="2" value="<%= launder.getE01LDPDE2().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDE3" size="3" maxlength="2" value="<%= launder.getE01LDPDE3().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDE4" size="3" maxlength="2" value="<%= launder.getE01LDPDE4().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDE5" size="3" maxlength="2" value="<%= launder.getE01LDPDE5().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDE6" size="3" maxlength="2" value="<%= launder.getE01LDPDE6().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Dep&oacute;sitos Otros :</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDC1" size="3" maxlength="2" value="<%= launder.getE01LDPDC1().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDC2" size="3" maxlength="2" value="<%= launder.getE01LDPDC2().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDC3" size="3" maxlength="2" value="<%= launder.getE01LDPDC3().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDC4" size="3" maxlength="2" value="<%= launder.getE01LDPDC4().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDC5" size="3" maxlength="2" value="<%= launder.getE01LDPDC5().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPDC6" size="3" maxlength="2" value="<%= launder.getE01LDPDC6().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Retiros Efectivos :</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPWH1" size="3" maxlength="2" value="<%= launder.getE01LDPWH1().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPWH2" size="3" maxlength="2" value="<%= launder.getE01LDPWH2().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPWH3" size="3" maxlength="2" value="<%= launder.getE01LDPWH3().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPWH4" size="3" maxlength="2" value="<%= launder.getE01LDPWH4().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPWH5" size="3" maxlength="2" value="<%= launder.getE01LDPWH5().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPWH6" size="3" maxlength="2" value="<%= launder.getE01LDPWH6().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Pago de Cheques :</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPPC1" size="3" maxlength="2" value="<%= launder.getE01LDPPC1().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPPC2" size="3" maxlength="2" value="<%= launder.getE01LDPPC2().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPPC3" size="3" maxlength="2" value="<%= launder.getE01LDPPC3().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPPC4" size="3" maxlength="2" value="<%= launder.getE01LDPPC4().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPPC5" size="3" maxlength="2" value="<%= launder.getE01LDPPC5().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPPC6" size="3" maxlength="2" value="<%= launder.getE01LDPPC6().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Transferencias Emitidas :</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTI1" size="3" maxlength="2" value="<%= launder.getE01LDPTI1().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTI2" size="3" maxlength="2" value="<%= launder.getE01LDPTI2().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTI3" size="3" maxlength="2" value="<%= launder.getE01LDPTI3().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTI4" size="3" maxlength="2" value="<%= launder.getE01LDPTI4().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTI5" size="3" maxlength="2" value="<%= launder.getE01LDPTI5().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTI6" size="3" maxlength="2" value="<%= launder.getE01LDPTI6().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Transaferencias Recibidas :</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTR1" size="3" maxlength="2" value="<%= launder.getE01LDPTR1().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTR2" size="3" maxlength="2" value="<%= launder.getE01LDPTR2().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTR3" size="3" maxlength="2" value="<%= launder.getE01LDPTR3().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTR4" size="3" maxlength="2" value="<%= launder.getE01LDPTR4().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTR5" size="3" maxlength="2" value="<%= launder.getE01LDPTR5().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01LDPTR6" size="3" maxlength="2" value="<%= launder.getE01LDPTR6().trim()%>"
					 oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
