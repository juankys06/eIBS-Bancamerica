<html>
<head>
<title>Documento Swift</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="inqSwift" class="datapro.eibs.beans.EDD061003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<h3 align="center">Documento Swift<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0610_tr_inq_swift.jsp"></h3>
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
            <td nowrap width="23%" >No Recibidor :</td>
            <td nowrap width="35%" >Direcci&oacute;n Recibidor:</td>
            <td nowrap width="42%" >Enviado ID:</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="left"> 
                <input type="text" readonly size="15" maxlength="12" name="E03SWFRID" value="<%= inqSwift.getE03SWFRID().trim()%>">
              </div>
            </td>
            <td nowrap width="35%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly size="35" maxlength="35" name="E03SWFRA1" value="<%= inqSwift.getE03SWFRA1().trim()%>" >
                </font></font></font></div>
            </td>
            <td nowrap width="42%" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFSID" size="12" maxlength="15" value="<%= inqSwift.getE03SWFSID().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%">Recibidor:</td>
            <td nowrap width="35%">Ciudad y Pa&iacute;s :</td>
            <td nowrap width="42%">Monto:</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E03SWFAD2" size="25" maxlength="30" value="<%= inqSwift.getE03SWFAD2().trim()%>" >
              </div>
            </td>
            <td nowrap width="35%"> 
              <div align="left"> 
                <input type="text" readonly name="E03SWFAD3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFAD3().trim()%>" >
              </div>
            </td>
            <td nowrap width="42%"> 
              <div align="left"> 
                <input type="text" readonly name="E03WRTCAM" size="12" maxlength="15" value="<%= inqSwift.getE03WRTCAM().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%">Prioridad:</td>
            <td nowrap width="35%"> 
              <input type="text" readonly name="E03SWFAD4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFAD4().trim()%>" >
            </td>
            <td nowrap width="42%">Codigo Moneda</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E03WRTBTH" size="3" maxlength="2" >
              </div>
            </td>
            <td nowrap width="35%"> 
              <div align="left"> 
                <input type="text" readonly size="30" maxlength="35" name="E03SWFAD5" value="<%= inqSwift.getE03SWFAD5().trim()%>" >
              </div>
            </td>
            <td nowrap width="42%"> 
              <div align="left"> 
                <input type="text" readonly name="E03SWFCCY" size="11" maxlength="11" value="<%= inqSwift.getE03SWFCCY().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%">Ref.Swift M20:</td>
            <td nowrap width="35%">&nbsp;</td>
            <td nowrap width="42%">Fecha Valor:</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E03WRTDS1" size="16" maxlength="16" >
              </div>
            </td>
            <td nowrap width="35%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="42%"> 
              <div align="left"> 
                <input type="text" readonly name="E03INSDT1" size="2" maxlength="2" value="<%= inqSwift.getE03INSDT1().trim()%>" >
                <input type="text" readonly name="E03INSDT2" size="2" maxlength="3" value="<%= inqSwift.getE03INSDT2().trim()%>" >
                <input type="text" readonly name="E03INSDT3" size="2" maxlength="3" value="<%= inqSwift.getE03INSDT3().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="48%" height="100%"> 
        <table cellspacing=0 cellpadding=2 border="0" width="100%" height="363">
          <tr id="trdark"> 
            <td nowrap colspan="2" bordercolor="#000000" > 
              <div align="center"><b>Ordenante</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" bordercolor="#000000" >Cliente Ordenante M50:</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFCU1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCU1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFCU2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCU2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFCU3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCU3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"> </div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFCU4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCU4().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" colspan="2" >Bco Ordenante....52</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" > 
              <p align="right">A</p>
            </td>
            <td nowrap bordercolor="#000000" > 
              <input type="text" readonly name="E03SWFBK1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBK1().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">D</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFBK2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBK2().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap height="21" > 
              <p>&nbsp;</p>
            </td>
            <td nowrap height="21" > 
              <input type="text" readonly name="E03SWFBK3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBK3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="left"> </div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFBK4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBK4().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
      <td nowrap width="48%" height="363"> 
        <table cellspacing=0 cellpadding=2 border="0" width="100%" height="363">
          <tr id="trdark"> 
            <td nowrap colspan="2" bordercolor="#000000" > 
              <div align="center"><b>Corresponsal</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" bordercolor="#000000" >Corresponsal Enviador 
              53: </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="right">A</div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFSC1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFSC1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="right">B</div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFSC2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFSC2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="right">D</div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFSC3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFSC3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"> </div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFSC4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFSC4().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" colspan="2" >Corresponsal Recibidor 
              54: </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" > 
              <p align="right">A</p>
            </td>
            <td nowrap bordercolor="#000000" > 
              <input type="text" readonly name="E03SWFRC1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFRC1().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">B</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFRC2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFRC2().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap height="21" > 
              <p align="right">D</p>
            </td>
            <td nowrap height="21" > 
              <input type="text" readonly name="E03SWFRC3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFRC3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="left"> </div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFRC4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFRC4().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="48%" height="315"> 
        <table cellspacing=0 cellpadding=2 border="0" width="100%" height="363">
          <tr id="trdark"> 
            <td nowrap colspan="2" bordercolor="#000000" > 
              <div align="center"><b>Intermediario</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" bordercolor="#000000" >Bco Intermediario:</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"> </div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB4().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" colspan="2" >Inf. Bco a Bco</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" > 
              <p align="right">A</p>
            </td>
            <td nowrap bordercolor="#000000" > 
              <input type="text" readonly name="E03SWFBB1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBB1().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">D</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFBB2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBB2().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap height="21" > 
              <p>&nbsp;</p>
            </td>
            <td nowrap height="21" > 
              <input type="text" readonly name="E03SWFBB3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBB3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >&nbsp;</td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFBB4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBB4().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
      <td nowrap width="48%" height="100%"> 
        <table cellspacing=0 cellpadding=2 border="0" width="100%" height="363">
          <tr id="trdark"> 
            <td nowrap colspan="2" bordercolor="#000000" > 
              <div align="center"><b>Beneficiario</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" bordercolor="#000000" >Cliente Beneficiario: 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="right">A</div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFBC1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBC1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="right">B</div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFBC2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBC2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="right">D</div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> <b> 
                <input type="text" readonly name="E03SWFBC3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBC3().trim()%>" >
                </b> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" bordercolor="#000000" > 
              <div align="left"> </div>
            </td>
            <td nowrap width="67%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFBC4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFBC4().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" colspan="2" >Bco Beneficiario: </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" > 
              <p align="right">A</p>
            </td>
            <td nowrap bordercolor="#000000" > 
              <input type="text" readonly name="E03SWFCW1" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCW1().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">B</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFCW2" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCW2().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap height="21" > 
              <p align="right">D</p>
            </td>
            <td nowrap height="21" > 
              <input type="text" readonly name="E03SWFCW3" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCW3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >&nbsp;</td>
            <td nowrap > 
              <input type="text" readonly name="E03SWFCW4" size="30" maxlength="35" value="<%= inqSwift.getE03SWFCW4().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="30%" height="150"> 
        <table cellspacing=0 cellpadding=2 border="0" width="100%">
          <tr id="trdark"> 
            <td nowrap colspan="2" bordercolor="#000000" > 
              <div align="left">Detalle del Pago:</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="36%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="64%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB12" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="36%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="64%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB22" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="36%" bordercolor="#000000" > 
              <div align="left"></div>
            </td>
            <td nowrap width="64%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB32" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="36%" bordercolor="#000000" > 
              <div align="left"> </div>
            </td>
            <td nowrap width="64%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E03SWFTB42" size="30" maxlength="35" value="<%= inqSwift.getE03SWFTB4().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
</form>
</body>
</html>
