<html>
<head>
<title>Documento Fed</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="inqFed" class="datapro.eibs.beans.EDD061002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<h3 align="center">Docuemnto Fed<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0610_tr_inq_fed.jsp"></h3>
<hr size="4" width="100%">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0160" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>
  <p><b>Informaci&oacute;n General</b></p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="15%" >Fecha Instrucciones:</td>
            <td nowrap width="8%" > 
              <input type="text" readonly name="E02INSDT1" size="1" maxlength="2" value="<%= inqFed.getE02INSDT1().trim()%>" >
              <input type="text" readonly name="E02INSDT2" size="1" maxlength="3" value="<%= inqFed.getE02INSDT2().trim()%>" >
              <input type="text" readonly name="E02INSDT3" size="1" maxlength="3" value="<%= inqFed.getE02INSDT3().trim()%>" >
            </td>
            <td nowrap width="10%" > 
              <div align="right">Por Orden de:<font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></font></font></div>
            </td>
            <td nowrap width="20%" ><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly size="25" maxlength="35" name="E02FEDORG" value="<%= inqFed.getE02FEDORG().trim()%>" >
              </font></font></font></font></font></td>
            <td nowrap width="10%" > 
              <div align="right">No de Referencia:<font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></div>
            </td>
            <td nowrap width="37%" ><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly size="14" maxlength="16" name="E02FEDREF" value="<%= inqFed.getE02FEDREF().trim()%>" >
              </font></font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">Enviado ABA: </div>
            </td>
            <td nowrap width="8%" > 
              <input type="text" readonly name="E02FEDSAB" size="10" maxlength="9" value="<%= inqFed.getE02FEDSAB().trim()%>" >
            </td>
            <td nowrap width="10%" > 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font size="2"> </font></font></font></div>
            </td>
            <td nowrap width="20%" ><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly size="25" maxlength="35" name="E02FEDOR1" value="<%= inqFed.getE02FEDOR1().trim()%>" >
              </font></font></font></font></font></td>
            <td nowrap width="10%" > 
              <div align="right"><font face="Arial"><font face="Arial"><font size="2">Nombre: 
                </font></font></font> </div>
            </td>
            <td nowrap width="37%" ><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E02FEDSNM" size="14" maxlength="18" value="<%= inqFed.getE02FEDSNM().trim()%>" >
              </font></font></font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%"> 
              <div align="right">Recibido ABA: </div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" readonly name="E02FEDRAB" size="10" maxlength="9" value="<%= inqFed.getE02FEDRAB().trim()%>" >
            </td>
            <td nowrap width="10%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font size="2"> </font></font></font> </div>
            </td>
            <td nowrap width="20%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly size="25" maxlength="35" name="E02FEDOR2" value="<%= inqFed.getE02FEDOR2().trim()%>" >
              </font></font></font></font></font></td>
            <td nowrap width="10%"> 
              <div align="right">Nombre: </div>
            </td>
            <td nowrap width="37%"> 
              <input type="text" readonly name="E02FEDRNM" size="14" maxlength="18" value="<%= inqFed.getE02FEDRNM().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Tipo/ 
                Subtipo:</font></font></font></font></font></div>
            </td>
            <td nowrap width="8%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E02FEDTS1" size="1" maxlength="2" value="<%= inqFed.getE02FEDTS1().trim()%>" >
              / 
              <input type="text" readonly name="E02FEDTS2" size="1" maxlength="2" value="<%= inqFed.getE02FEDTS2().trim()%>" >
              </font></font></font><font size="2"></font></font></font></font></font> 
            </td>
            <td nowrap width="10%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font face="Arial"><font face="Arial"></font></font><font size="2">Numero: 
                </font></font></font> </div>
            </td>
            <td nowrap width="20%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font><font size="2"> 
              <input type="text" readonly name="E02WRTNUM" size="12" maxlength="15" value="<%= userPO.getHeader20().trim()%>" >
              </font></font></font></font></font></td>
            <td nowrap width="10%"> 
              <div align="right">Monto: </div>
            </td>
            <td nowrap width="37%"> 
              <input type="text" readonly size="14" maxlength="18" name="E02WRTCAM" value="<%= inqFed.getE02WRTCAM().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Par&aacute;metros</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="22%" >Banco Originador:</td>
            <td nowrap width="36%" > 
              <div align="left">Banco Beneficiario:<font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></font></font></div>
            </td>
            <td nowrap width="42%" >Informaci&oacute;n Origen a Benef.:<font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%" > 
              <div align="left"> 
                <input type="text" readonly name="E02FEDORB" size="30" maxlength="34" value="<%= inqFed.getE02FEDORB().trim()%>" >
              </div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="30" maxlength="35" name="E02FEDBBK" value="<%= inqFed.getE02FEDBBK().trim()%>" >
                </font></font></font><font size="2"> </font></font></font></div>
            </td>
            <td nowrap width="42%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly name="E02FEDOBI" size="30" maxlength="35" value="<%= inqFed.getE02FEDOBI().trim()%>" >
                </font></font></font> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%">C&oacute;digo de Producto :</td>
            <td nowrap width="36%"> 
              <div align="left"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                Beneficiario: </font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></font></font></font></font></font></font><font size="2"> 
                </font></font></font> </div>
            </td>
            <td nowrap width="42%"> 
              <div align="left">Informaci&oacute;n Banco a Banco:</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="left"> 
                <input type="text" readonly name="E02FEDPRC" size="3" maxlength="3" value="<%= inqFed.getE02FEDPRC().trim()%>" >
                <input type="text" readonly name="E02FEDIBK" size="25" maxlength="9" value="<%= inqFed.getE02FEDIBK().trim()%>" >
              </div>
            </td>
            <td nowrap width="36%" height="41"> 
              <div align="left"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></font></font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="30" maxlength="35" name="E02FEDBNF" value="<%= inqFed.getE02FEDBNF().trim()%>" >
                </font></font></font></font></font></font></font></font></font></font></font><font size="2"> 
                </font></font></font> </div>
            </td>
            <td nowrap width="42%" > 
              <div align="left"> 
                <input type="text" readonly name="E02FEDBBI" size="30" maxlength="35" value="<%= inqFed.getE02FEDBBI().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="22%">&nbsp;</td>
            <td nowrap width="36%" height="41"> 
              <div align="left"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="30" maxlength="35" name="E02FEDBN1" value="<%= inqFed.getE02FEDBN1().trim()%>" >
                </font></font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="42%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%">&nbsp;</td>
            <td nowrap width="36%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font><font size="2">Ref.para 
              Beneficiar </font></font></font></font></font></td>
            <td nowrap width="42%">Instrucciones Banco: </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="left"> </div>
            </td>
            <td nowrap width="36%"> 
              <div align="left"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="30" maxlength="35" name="E02FEDBRF" value="<%= inqFed.getE02FEDBRF().trim()%>" >
                </font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="42%"> 
              <div align="left"> 
                <input type="text" readonly size="30" maxlength="35" name="E02FEDINS" value="<%= inqFed.getE02FEDINS().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>
