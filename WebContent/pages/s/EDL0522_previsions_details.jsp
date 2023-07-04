<html>
<head>
<title>Tablas de prevision</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="prevDetails" class="datapro.eibs.beans.EDL052201Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Tabla de Previsi&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_overdraft.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0522" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Tabla :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRVTBL" size="3" maxlength="2"  value="<%= prevDetails.getE01PRVTBL().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Descripci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01PRVDSC" size="35"  maxlength="30" value="<%= prevDetails.getE01PRVDSC().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="center"><b>Nivel</b></div>
            </td>
            <td nowrap width="40%"> 
              <div align="center"><b>D&iacute;as <br>
                Vencimiento</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="center"><b>% Previsi&oacute;n <br>
                Capital</b></div>
            </td>
            <td nowrap width="28%"> 
              <div align="center"><b>% Previsi&oacute;n <br>
                Interes</b></div>
            </td>
            <td nowrap width="28%"> 
              <div align="center"><b>C&oacute;digo <br>
                Calificaci&oacute;n</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="center"><b>1</b></div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01PRVDY1" maxlength="4" size="5" value="<%= prevDetails.getE01PRVDY1().trim()%>" >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center">
                <input type="text" name="E01PRVPP1" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPP1().trim()%>" >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center">
                <input type="text" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPI1().trim()%>" name="E01PRVPI1" >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center">
                <input type="text" maxlength="1" size="2" value="<%= prevDetails.getE01PRVCA1().trim()%>" name="E01PRVCA1" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"><b>2</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center">
                <input type="text" name="E01PRVDY2" maxlength="4" size="5" value="<%= prevDetails.getE01PRVDY2().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center">
                <input type="text" name="E01PRVPP2" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPP2().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center">
                <input type="text" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPI2().trim()%>" name="E01PRVPI2" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center">
                <input type="text" maxlength="1" size="2" value="<%= prevDetails.getE01PRVCA2().trim()%>" name="E01PRVCA2" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"><b>3</b></div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center">
                <input type="text" name="E01PRVDY3" maxlength="4" size="5" value="<%= prevDetails.getE01PRVDY3().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center">
                <input type="text" name="E01PRVPP3" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPP3().trim()%>" >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center">
                <input type="text" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPI3().trim()%>" name="E01PRVPI3" >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center">
                <input type="text" maxlength="1" size="2" value="<%= prevDetails.getE01PRVCA3().trim()%>" name="E01PRVCA3" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"><b>4</b></div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center">
                <input type="text" name="E01PRVDY4" maxlength="4" size="5" value="<%= prevDetails.getE01PRVDY4().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center">
                <input type="text" name="E01PRVPP4" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPP4().trim()%>" >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center">
                <input type="text" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPI4().trim()%>" name="E01PRVPI4" >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center">
                <input type="text" maxlength="1" size="2" value="<%= prevDetails.getE01PRVCA4().trim()%>" name="E01PRVCA4" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"><b>5</b></div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center">
                <input type="text" name="E01PRVDY5" maxlength="4" size="5" value="<%= prevDetails.getE01PRVDY5().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center">
                <input type="text" name="E01PRVPP5" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPP5().trim()%>" >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center">
                <input type="text" maxlength="5" size="6" value="<%= prevDetails.getE01PRVPI5().trim()%>" name="E01PRVPI5" >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center">
                <input type="text" maxlength="1" size="2" value="<%= prevDetails.getE01PRVCA5().trim()%>" name="E01PRVCA5" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                Calificaci&oacute;n que indica Previsi&oacute;n/Calificaci&oacute;n 
                Global Cliente : 
                <input type="text" maxlength="1" size="2" value="<%= prevDetails.getE01PRVLMC().trim()%>" name="E01PRVLMC" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="3" >(Si la Previsi&oacute;n es por Cr&eacute;ditos 
              Individuales, basados en su <b>n&uacute;mero de nivel</b>,</td>
          </tr>
          <tr id="trdark">
            <td nowrap colspan="3" >dejar el <b>campo en blanco</b>, si es por 
              Cliente/Calificaci&oacute;n, entre el<b> c&oacute;digo correspondiente</b>)</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><br>
  </p>
  <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
