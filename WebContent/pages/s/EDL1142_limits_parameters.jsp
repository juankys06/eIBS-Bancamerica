<html>
<head>
<title>Tramos y Tasas Preferenciales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="prefere" class="datapro.eibs.beans.EDL114201Message"  scope="session" />

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


<H3 align="center">Tramos y Tasas Preferenciales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="limits_parameters.jsp, EDL1142"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL1142" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Tabla :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01TASTBL" size="3" maxlength="2" value="<%= prefere.getE01TASTBL().trim()%>">
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Descripción :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01TASDSC" size="36" maxlength="35" value="<%= prefere.getE01TASDSC().trim()%>">
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Fecha :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01TASDTE" size="36" maxlength="35" value="<%= prefere.getE01TASDTE().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Definición de Límites Para Tramos y Tasas Préstamos Preferenciales</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">

          <tr id="trclear"> 
            <td nowrap width="5%"> 
              <div align="right"> </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">Mínimo</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">Máximo</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">Tasa en Referencia</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">Subsidio</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">Tasa Preferencial</div>
            </td>
           </tr>


          <tr id="trdark"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 1 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI1" size="17" maxlength="15" value="<%= prefere.getE01TASMI1().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX1" size="17" maxlength="15" value="<%= prefere.getE01TASMX1().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE1" size="11" maxlength="9" value="<%= prefere.getE01TASRE1().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB1" size="11" maxlength="9" value="<%= prefere.getE01TASSB1().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF1" size="11" maxlength="9" value="<%= prefere.getE01TASPF1().trim()%>">
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 2 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI2" size="17" maxlength="15" value="<%= prefere.getE01TASMI2().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX2" size="17" maxlength="15" value="<%= prefere.getE01TASMX2().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE2" size="11" maxlength="9" value="<%= prefere.getE01TASRE2().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB2" size="11" maxlength="9" value="<%= prefere.getE01TASSB2().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF2" size="11" maxlength="9" value="<%= prefere.getE01TASPF2().trim()%>">
              </div>
            </td>

          </tr>


          <tr id="trdark"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 3 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI3" size="17" maxlength="15" value="<%= prefere.getE01TASMI3().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX3" size="17" maxlength="15" value="<%= prefere.getE01TASMX3().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE3" size="11" maxlength="9" value="<%= prefere.getE01TASRE3().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB3" size="11" maxlength="9" value="<%= prefere.getE01TASSB3().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF3" size="11" maxlength="9" value="<%= prefere.getE01TASPF3().trim()%>">
              </div>
            </td>

          </tr>

          <tr id="trclear"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 4 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI4" size="17" maxlength="15" value="<%= prefere.getE01TASMI4().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX4" size="17" maxlength="15" value="<%= prefere.getE01TASMX4().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE4" size="11" maxlength="9" value="<%= prefere.getE01TASRE4().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB4" size="11" maxlength="9" value="<%= prefere.getE01TASSB4().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF4" size="11" maxlength="9" value="<%= prefere.getE01TASPF4().trim()%>">
              </div>
            </td>

          </tr>


          <tr id="trdark"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 5 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI5" size="17" maxlength="15" value="<%= prefere.getE01TASMI5().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX5" size="17" maxlength="15" value="<%= prefere.getE01TASMX5().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE5" size="11" maxlength="9" value="<%= prefere.getE01TASRE5().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB5" size="11" maxlength="9" value="<%= prefere.getE01TASSB5().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF5" size="11" maxlength="9" value="<%= prefere.getE01TASPF5().trim()%>">
              </div>
            </td>

          </tr>

          <tr id="trclear"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 6 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI6" size="17" maxlength="15" value="<%= prefere.getE01TASMI6().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX6" size="17" maxlength="15" value="<%= prefere.getE01TASMX6().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE6" size="11" maxlength="9" value="<%= prefere.getE01TASRE6().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB6" size="11" maxlength="9" value="<%= prefere.getE01TASSB6().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF6" size="11" maxlength="9" value="<%= prefere.getE01TASPF6().trim()%>">
              </div>
            </td>

          </tr>


          <tr id="trdark"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 7 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI7" size="17" maxlength="15" value="<%= prefere.getE01TASMI7().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX7" size="17" maxlength="15" value="<%= prefere.getE01TASMX7().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE7" size="11" maxlength="9" value="<%= prefere.getE01TASRE7().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB7" size="11" maxlength="9" value="<%= prefere.getE01TASSB7().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF7" size="11" maxlength="9" value="<%= prefere.getE01TASPF7().trim()%>">
              </div>
            </td>

          </tr>

          <tr id="trclear"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 8 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI8" size="17" maxlength="15" value="<%= prefere.getE01TASMI8().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX8" size="17" maxlength="15" value="<%= prefere.getE01TASMX8().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE8" size="11" maxlength="9" value="<%= prefere.getE01TASRE8().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB8" size="11" maxlength="9" value="<%= prefere.getE01TASSB8().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF8" size="11" maxlength="9" value="<%= prefere.getE01TASPF8().trim()%>">
              </div>
            </td>

          </tr>


          <tr id="trdark"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 9 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI9" size="17" maxlength="15" value="<%= prefere.getE01TASMI9().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX9" size="17" maxlength="15" value="<%= prefere.getE01TASMX9().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE9" size="11" maxlength="9" value="<%= prefere.getE01TASRE9().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB9" size="11" maxlength="9" value="<%= prefere.getE01TASSB9().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF9" size="11" maxlength="9" value="<%= prefere.getE01TASPF9().trim()%>">
              </div>
            </td>

          </tr>

          <tr id="trclear"> 
            <td nowrap width="5%"> 
              <div align="right">Rango 10 :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMI0" size="17" maxlength="15" value="<%= prefere.getE01TASMI0().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASMX0" size="17" maxlength="15" value="<%= prefere.getE01TASMX0().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASRE0" size="11" maxlength="9" value="<%= prefere.getE01TASRE0().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASSB0" size="11" maxlength="9" value="<%= prefere.getE01TASSB0().trim()%>">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01TASPF0" size="11" maxlength="9" value="<%= prefere.getE01TASPF0().trim()%>">
              </div>
            </td>

          </tr>


        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
