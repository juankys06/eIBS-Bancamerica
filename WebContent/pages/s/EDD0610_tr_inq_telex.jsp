<html>
<head>
<title>Documento Telex</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="inqTelex" class="datapro.eibs.beans.EDD061004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<h3 align="center">Documento Telex <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0610_tr_inq_telex.jsp"></h3>
<hr size="4" width="100%">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0160" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>
  <p> <b>Transferencia Telex Regular</b></p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="23%" >Dpto. Originador:</td>
            <td nowrap width="23%" > 
              <div align="left">N&uacute;mero de Telex:</div>
            </td>
            <td nowrap width="20%" ><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Fecha 
              Valor: </font></font></font></font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="left"> 
                <input type="text" readonly name="E04TLXORD" size="10" maxlength="12" value="<%= inqTelex.getE04TLXORD().trim()%>" >
              </div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="6" maxlength="6" name="E04SCHNUM" value="<%=userPO.getHeader20().trim()%>" >
                </font></font></div>
            </td>
            <td nowrap width="20%" ><font face="Arial"><font size="2"> 
              <input type="text" readonly size="1" maxlength="2" name="E04INSDT1" value="<%= inqTelex.getE04INSDT1().trim()%>" >
              </font><font size="2"> 
              <input type="text" readonly name="E04INSDT2" size="1" maxlength="2" value="<%= inqTelex.getE04INSDT2().trim()%>" >
              </font><font size="2"> 
              <input type="text" readonly name="E04INSDT3" size="1" maxlength="2" value="<%= inqTelex.getE04INSDT2().trim()%>" >
              </font></font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%"> 
              <div align="left">Bco Recibidor:</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"><font face="Arial"><font size="2"> </font></font><font size="2">Ref. 
                de Telex</font>: </div>
            </td>
            <td nowrap width="20%"><font face="Arial"><font size="2">Monto: </font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%"> 
              <div align="left"><font face="Arial"><font size="2"> 
                <input type="text" readonly name="E04TLXCNU" size="10" maxlength="9" value="<%= inqTelex.getE04TLXCNU().trim()%>" >
                </font></font></div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="12" maxlength="12" name="E04TLXLOG" value="<%= inqTelex.getE04TLXLOG().trim()%>" >
                </font></font> </div>
            </td>
            <td nowrap width="20%"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E04WRTCAM" size="12" maxlength="15" value="<%= inqTelex.getE04WRTCAM().trim()%>" >
              </font></font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%">Nombre:</td>
            <td nowrap width="23%">Nombre Clave:</td>
            <td nowrap width="20%">V&iacute;a de Aviso:<font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E04TLXTO1" size="20" maxlength="25" value="<%= inqTelex.getE04TLXTO1().trim()%>" >
              </font></font></td>
            <td nowrap width="23%"><font face="Arial"><font size="2"> 
              <input type="text" readonly size="15" maxlength="15" name="E04TLXCSN" value="<%= inqTelex.getE04TLXCSN().trim()%>" >
              </font></font></td>
            <td nowrap width="20%"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E04TLXVIA" size="12" maxlength="15" value="<%= inqTelex.getE04TLXVIA().trim()%>" >
              </font></font> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%"> 
              <div align="left"><font face="Arial"><font size="2"> 
                <input type="text" readonly name="E04TLXTO2" size="20" maxlength="25" value="<%= inqTelex.getE04TLXTO2().trim()%>" >
                </font></font></div>
            </td>
            <td nowrap width="23%"><font face="Arial"></font> </td>
            <td nowrap width="20%"><font face="Arial"><font size="2"> </font></font></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4><b>S&iacute;rvase a Ejecutar el Pago</b></h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="26%" >N&uacute;mero de Prueba:</td>
            <td nowrap width="17%" > 
              <div align="left">Por Orden de:<font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></font></font></div>
            </td>
            <td nowrap width="19%" ><font face="Arial"><font face="Arial"><font size="2">Banco 
              Originador: </font></font></font></td>
            <td nowrap width="38%" >Nuestra Referencia:<font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="left"> 
                <input type="text" readonly name="E04TLXTYN" size="18" maxlength="20" value="<%= inqTelex.getE04TLXTYN().trim()%>" >
                <font size="2"> 
                <input type="text" readonly size="1" maxlength="20" name="E04TLXTSN" value="<%= inqTelex.getE04TLXTSN().trim()%>" >
                </font></div>
            </td>
            <td nowrap width="17%" > 
              <div align="left"><font size="2"> 
                <input type="text" readonly size="20" maxlength="20" name="E05WRTBYO" value="<%= userPO.getHeader4().trim()%>" >
                 </font></div>
            <td nowrap width="19%" > 
              <div align="left"><font size="2"> 
                <input type="text" readonly name="E04TLXORB" size="20" maxlength="30" value="<%= inqTelex.getE04TLXORB().trim()%>" >
              </div>
            </td>
            <td nowrap width="38%" ><font size="2"> 
              <input type="text" readonly name="E04WRTORF" size="20" maxlength="30" value="<%= inqTelex.getE04WRTORF().trim()%>" >
              </font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%">Banco Beneficiario:</td>
            <td nowrap width="17%"> 
              <div align="left"><font size="2"> Cliente Beneficiario:</font size="2"> 
              </div>
            </td>
            <td nowrap colspan="2"> 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E04TLXBF1" size="30" maxlength="35" value="<%= inqTelex.getE04TLXBF1().trim()%>" >
              </div>
            </td>
            <td nowrap colspan="3" height="41"> 
              <div align="left"><font size="2"> 
                <input type="text" readonly size="45" maxlength="30" name="E04TLXBNA" value="<%= inqTelex.getE04TLXBNA().trim()%>" >
                </font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%"> 
              <input type="text" readonly name="E04TLXBF2" size="30" maxlength="35" value="<%= inqTelex.getE04TLXBF2().trim()%>" >
            </td>
            <td nowrap colspan="3" height="41"> 
              <div align="left"><font size="2"> 
                <input type="text" readonly size="45" maxlength="30" name="E04TLXBNB" value="<%= inqTelex.getE04TLXBNB().trim()%>" >
                </font></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <input type="text" readonly name="E04TLXBB1" size="30" maxlength="35" value="<%= inqTelex.getE04TLXBB1().trim()%>" >
            </td>
            <td nowrap colspan="3"><font size="2"> 
              <input type="text" readonly size="45" maxlength="30" name="E04TLXBN1" value="<%= inqTelex.getE04TLXBN1().trim()%>" >
              </font> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%">Detalle del Pago:</td>
            <td nowrap colspan="3">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4"><font size="2">  
              <input type="text" readonly size="45" maxlength="30" name="E04TLXDT1" value="<%= inqTelex.getE04TLXDT1().trim()%>" >
              </font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" height="38"> 
              <div align="left"><font size="2"> 
                <input type="text" readonly size="45" maxlength="30" name="E04TLXDT2" value="<%= inqTelex.getE04TLXDT2().trim()%>" >
                </font></div>
              </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Instrucciones de Reembolso</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap colspan="2" > Cr&eacute;dito a su cuenta: </td>
            <td nowrap colspan="2" > 
              <input type="text" readonly name="E04TLXOUA" size="10" maxlength="12" value="<%= inqTelex.getE04TLXOUA().trim()%>" >
            </td>
            <td nowrap width="22%" > 
              <div align="right">Carguen Nuestra Cuenta:<font face="Arial"><font size="2"> 
                </font></font><font face="Arial"><font size="2"> </font></font></div>
            </td>
            <td nowrap width="17%" ><font face="Arial"><font size="2"> 
              <input type="text" readonly size="13" maxlength="13" name="E04TLXOU1" value="<%= inqTelex.getE04TLXOU1().trim()%>" >
              </font></font></td>
            <td nowrap colspan="2" > <font face="Arial"><font size="2"> </font></font></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > <font face="Arial"><font size="2">Cargo por 
              </font><font face="Arial"><font size="2">Nuestro</font></font><font size="2">:</font><font face="Arial"><font size="2"> 
              </font></font></font></td>
            <td nowrap width="3%" ><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E04TLXCHO" size="1" maxlength="2" value="<%= inqTelex.getE04TLXCHO().trim()%>" >
              </font></font></font></td>
            <td nowrap width="6%" ><font face="Arial"><font size="2"> Benef</font><font face="Arial"><font size="2"> 
              </font></font></font></td>
            <td nowrap width="4%" ><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly size="1" maxlength="2" name="E04TLXCHB" value="<%= inqTelex.getE04TLXCHB().trim()%>" >
              </font></font></font></td>
            <td nowrap width="22%" > 
              <div align="right">Cubierta con <font face="Arial"><font size="2">Nuestro</font></font>:<font face="Arial"><font size="2"> 
                </font></font><font face="Arial"><font size="2"> </font></font></div>
            </td>
            <td nowrap width="17%" ><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E04TLXCVP" size="20" maxlength="30" value="<%= inqTelex.getE04TLXCVP().trim()%>" >
              </font></font></td>
            <td nowrap width="7%" ><font face="Arial"><font size="2"> </font><font face="Arial"><font face="Arial"><font size="2">Benef:</font></font></font><font size="2"> 
              </font></font></td>
            <td nowrap width="26%" ><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E04TLXOTC" size="19" maxlength="20" value="<%= inqTelex.getE04TLXOTC().trim()%>" >
              </font></font></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>
