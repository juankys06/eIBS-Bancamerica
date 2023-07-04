<html>
<head>
<title>Consulta de Tabla de Tasas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="cdRenRat" class="datapro.eibs.beans.ECN0030DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_i_opt);


</SCRIPT>



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

<h3 align="center">Tabla de Tasas de Certificados de Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_inq_ren_rate, EDL0130"></h3>
<hr size="4">

<form>
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Contrato :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>
  </p>
	
  <h4>Informaci&oacute;n General</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="24%"> 
              <div align="right">Fecha Efectiva:</div>
            </td>
            <td width="15%"> 
              <input type="text" name="E02CDRDT1" size="2" maxlength="2" value="<%= cdRenRat.getE02CDRDT1().trim()%>">
              <input type="text" name="E02CDRDT2" size="2" maxlength="2" value="<%= cdRenRat.getE02CDRDT2().trim()%>">
              <input type="text" name="E02CDRDT3" size="2" maxlength="2" value="<%= cdRenRat.getE02CDRDT3().trim()%>">
            </td>
            <td width="28%"> 
              <div align="right">Tasa M&aacute;xima:</div>
            </td>
            <td width="33%">
              <input type="text" name="E02CDRMXR" size="9" maxlength="9" value="<%= cdRenRat.getE02CDRMXR().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="24%"> 
              <div align="right">Moneda:</div>
            </td>
            <td width="15%"> 
              <input type="text" name="E02CDRCCY" size="3" maxlength="3" value="<%= cdRenRat.getE02CDRCCY().trim()%>">
            </td>
            <td width="28%"> 
              <div align="right">D&iacute;as de Penalidad:</div>
            </td>
            <td width="33%">
              <input type="text" name="E02CDRDPE" size="4" maxlength="3" value="<%= cdRenRat.getE02CDRDPE().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="24%"> 
              <div align="right">Tasa M&iacute;nima:</div>
            </td>
            <td width="15%"> 
              <input type="text" name="E02CDRMIR" size="9" maxlength="9" value="<%= cdRenRat.getE02CDRMIR().trim()%>">
            </td>
            <td width="28%"> 
              <div align="right">Tasa de Penalidad:</div>
            </td>
            <td width="33%">
              <input type="text" name="E02CDRPRT" size="5" maxlength="4" value="<%= cdRenRat.getE02CDRPRT().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Tasas y T&eacute;rminos</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>D&iacute;as /</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                  <td width="13%" height="22"> 
                    <div align="center"><i><b>Hasta:</b></i></div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    
              <div align="center"><i><b>T&eacute;rmino:</b></i></div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CDUA1" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA1().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDUA2" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA2().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDUA3" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA3().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDUA4" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA4().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDUA5" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA5().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDUA6" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA6().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDUA7" size="10" maxlength="9" value="<%= cdRenRat.getE02CDUA7().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center"> 
                      
                <input type="text" name="E02CDD01" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD01().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL101" size="10" maxlength="9" value="<%= cdRenRat.getE02CL101().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL201" size="10" maxlength="9" value="<%= cdRenRat.getE02CL201().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL301" size="10" maxlength="9" value="<%= cdRenRat.getE02CL301().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL401" size="10" maxlength="9" value="<%= cdRenRat.getE02CL401().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL501" size="10" maxlength="9" value="<%= cdRenRat.getE02CL501().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL601" size="10" maxlength="9" value="<%= cdRenRat.getE02CL601().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL701" size="10" maxlength="9" value="<%= cdRenRat.getE02CL701().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD02" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD02().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL102" size="10" maxlength="9" value="<%= cdRenRat.getE02CL102().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL202" size="10" maxlength="9" value="<%= cdRenRat.getE02CL202().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center"> 
                      <input type="text" name="E02CL302" size="10" maxlength="9" value="<%= cdRenRat.getE02CL302().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL402" size="10" maxlength="9" value="<%= cdRenRat.getE02CL402().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL502" size="10" maxlength="9" value="<%= cdRenRat.getE02CL502().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL602" size="10" maxlength="9" value="<%= cdRenRat.getE02CL602().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL702" size="10" maxlength="9" value="<%= cdRenRat.getE02CL702().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD03" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD03().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL103" size="10" maxlength="9" value="<%= cdRenRat.getE02CL103().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL203" size="10" maxlength="9" value="<%= cdRenRat.getE02CL203().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL303" size="10" maxlength="9" value="<%= cdRenRat.getE02CL303().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL403" size="10" maxlength="9" value="<%= cdRenRat.getE02CL403().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL503" size="10" maxlength="9" value="<%= cdRenRat.getE02CL503().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL603" size="10" maxlength="9" value="<%= cdRenRat.getE02CL603().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL703" size="10" maxlength="9" value="<%= cdRenRat.getE02CL703().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD04" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD04().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL104" size="10" maxlength="9" value="<%= cdRenRat.getE02CL104().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL204" size="10" maxlength="9" value="<%= cdRenRat.getE02CL204().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL304" size="10" maxlength="9" value="<%= cdRenRat.getE02CL304().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL404" size="10" maxlength="9" value="<%= cdRenRat.getE02CL404().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL504" size="10" maxlength="9" value="<%= cdRenRat.getE02CL504().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL604" size="10" maxlength="9" value="<%= cdRenRat.getE02CL604().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL704" size="10" maxlength="9" value="<%= cdRenRat.getE02CL704().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD05" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD05().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL105" size="10" maxlength="9" value="<%= cdRenRat.getE02CL105().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL205" size="10" maxlength="9" value="<%= cdRenRat.getE02CL205().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL305" size="10" maxlength="9" value="<%= cdRenRat.getE02CL305().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL405" size="10" maxlength="9" value="<%= cdRenRat.getE02CL405().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL505" size="10" maxlength="9" value="<%= cdRenRat.getE02CL505().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL605" size="10" maxlength="9" value="<%= cdRenRat.getE02CL605().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL705" size="10" maxlength="9" value="<%= cdRenRat.getE02CL705().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD06" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD06().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL106" size="10" maxlength="9" value="<%= cdRenRat.getE02CL106().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL206" size="10" maxlength="9" value="<%= cdRenRat.getE02CL206().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL306" size="10" maxlength="9" value="<%= cdRenRat.getE02CL306().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL406" size="10" maxlength="9" value="<%= cdRenRat.getE02CL406().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL506" size="10" maxlength="9" value="<%= cdRenRat.getE02CL506().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL606" size="10" maxlength="9" value="<%= cdRenRat.getE02CL606().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL706" size="10" maxlength="9" value="<%= cdRenRat.getE02CL706().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD07" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD07().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL107" size="10" maxlength="9" value="<%= cdRenRat.getE02CL107().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL207" size="10" maxlength="9" value="<%= cdRenRat.getE02CL207().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL307" size="10" maxlength="9" value="<%= cdRenRat.getE02CL307().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL407" size="10" maxlength="9" value="<%= cdRenRat.getE02CL407().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL507" size="10" maxlength="9" value="<%= cdRenRat.getE02CL507().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL607" size="10" maxlength="9" value="<%= cdRenRat.getE02CL607().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL707" size="10" maxlength="9" value="<%= cdRenRat.getE02CL707().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD08" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD08().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL108" size="10" maxlength="9" value="<%= cdRenRat.getE02CL108().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL208" size="10" maxlength="9" value="<%= cdRenRat.getE02CL208().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL308" size="10" maxlength="9" value="<%= cdRenRat.getE02CL308().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL408" size="10" maxlength="9" value="<%= cdRenRat.getE02CL408().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL508" size="10" maxlength="9" value="<%= cdRenRat.getE02CL508().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL608" size="10" maxlength="9" value="<%= cdRenRat.getE02CL608().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL708" size="10" maxlength="9" value="<%= cdRenRat.getE02CL708().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD09" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD09().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL109" size="10" maxlength="9" value="<%= cdRenRat.getE02CL109().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL209" size="10" maxlength="9" value="<%= cdRenRat.getE02CL209().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL309" size="10" maxlength="9" value="<%= cdRenRat.getE02CL309().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL409" size="10" maxlength="9" value="<%= cdRenRat.getE02CL409().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL509" size="10" maxlength="9" value="<%= cdRenRat.getE02CL509().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL609" size="10" maxlength="9" value="<%= cdRenRat.getE02CL609().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL709" size="10" maxlength="9" value="<%= cdRenRat.getE02CL709().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD10" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD10().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL110" size="10" maxlength="9" value="<%= cdRenRat.getE02CL110().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL210" size="10" maxlength="9" value="<%= cdRenRat.getE02CL210().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL310" size="10" maxlength="9" value="<%= cdRenRat.getE02CL310().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL410" size="10" maxlength="9" value="<%= cdRenRat.getE02CL410().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL510" size="10" maxlength="9" value="<%= cdRenRat.getE02CL510().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL610" size="10" maxlength="9" value="<%= cdRenRat.getE02CL610().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL710" size="10" maxlength="9" value="<%= cdRenRat.getE02CL710().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD11" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD11().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL111" size="10" maxlength="9" value="<%= cdRenRat.getE02CL111().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL211" size="10" maxlength="9" value="<%= cdRenRat.getE02CL211().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL311" size="10" maxlength="9" value="<%= cdRenRat.getE02CL311().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL411" size="10" maxlength="9" value="<%= cdRenRat.getE02CL411().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL511" size="10" maxlength="9" value="<%= cdRenRat.getE02CL511().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL611" size="10" maxlength="9" value="<%= cdRenRat.getE02CL611().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL711" size="10" maxlength="9" value="<%= cdRenRat.getE02CL711().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CDD12" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD12().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL112" size="10" maxlength="9" value="<%= cdRenRat.getE02CL112().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL212" size="10" maxlength="9" value="<%= cdRenRat.getE02CL212().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL312" size="10" maxlength="9" value="<%= cdRenRat.getE02CL312().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL412" size="10" maxlength="9" value="<%= cdRenRat.getE02CL412().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL512" size="10" maxlength="9" value="<%= cdRenRat.getE02CL512().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL612" size="10" maxlength="9" value="<%= cdRenRat.getE02CL612().trim()%>">
                    </div>
                  </td>
                  <td width="13%" height="39"> 
                    <div align="center">
                      <input type="text" name="E02CL712" size="10" maxlength="9" value="<%= cdRenRat.getE02CL712().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD13" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD13().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL113" size="10" maxlength="9" value="<%= cdRenRat.getE02CL113().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL213" size="10" maxlength="9" value="<%= cdRenRat.getE02CL213().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL313" size="10" maxlength="9" value="<%= cdRenRat.getE02CL313().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL413" size="10" maxlength="9" value="<%= cdRenRat.getE02CL413().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL513" size="10" maxlength="9" value="<%= cdRenRat.getE02CL513().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL613" size="10" maxlength="9" value="<%= cdRenRat.getE02CL613().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL713" size="10" maxlength="9" value="<%= cdRenRat.getE02CL713().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trclear"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD14" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD14().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL114" size="10" maxlength="9" value="<%= cdRenRat.getE02CL114().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL214" size="10" maxlength="9" value="<%= cdRenRat.getE02CL214().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL314" size="10" maxlength="9" value="<%= cdRenRat.getE02CL314().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL414" size="10" maxlength="9" value="<%= cdRenRat.getE02CL414().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL514" size="10" maxlength="9" value="<%= cdRenRat.getE02CL514().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL614" size="10" maxlength="9" value="<%= cdRenRat.getE02CL614().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL714" size="10" maxlength="9" value="<%= cdRenRat.getE02CL714().trim()%>">
                    </div>
                  </td>
                </tr>
          <tr id="trdark"> 
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CDD15" size="5" maxlength="4" value="<%= cdRenRat.getE02CDD15().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL115" size="10" maxlength="9" value="<%= cdRenRat.getE02CL115().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL215" size="10" maxlength="9" value="<%= cdRenRat.getE02CL215().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL315" size="10" maxlength="9" value="<%= cdRenRat.getE02CL315().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL415" size="10" maxlength="9" value="<%= cdRenRat.getE02CL415().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL515" size="10" maxlength="9" value="<%= cdRenRat.getE02CL515().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL615" size="10" maxlength="9" value="<%= cdRenRat.getE02CL615().trim()%>">
                    </div>
                  </td>
                  <td width="13%"> 
                    <div align="center">
                      <input type="text" name="E02CL715" size="10" maxlength="9" value="<%= cdRenRat.getE02CL715().trim()%>">
                    </div>
                  </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp;</p>
  </form>
</body>
</html>
