<html>
<head>
<title>Special Instructions</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="instructions" class="datapro.eibs.beans.ESD009001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	
<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_a_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_a_opt);
<%   
}
%>
	
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>


<H3 align="center">Mensajes para el Cajero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_ap_teller_instructions, ESD0090"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
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
                <input type="text" readonly name="E02CUN" size="9" maxlength="9"  value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly name="E02NA1" size="45" maxlength="45"  value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E02PRO" size="4" maxlength="4"  value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4> Mensajes</H4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="10%"
> 
              <div align="center"><b>Proc.</b></div>
            </td>
            <td nowrap width="60%"> 
              <div align="center"><b>Mensajes</b></div>
            </td>
            <td nowrap width="45%"> 
              <div align="center"><b>Expira en </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="checkbox" disabled  name="E01SFLG01" value="X" <%if(instructions.getE01SFLG01().equals("X")) out.print("checked");%>>
              </div>
            </td>
            
            <td nowrap width="60%" > 
              <div align="left">
              <input type="text" readonly maxlength="5" size="4" name="E01CDOC01" value="<%= instructions.getE01CDOC01().trim()%>">
              <A href="javascript:GetCodeDescCNOFC('E01CDOC01','E01SDOC01','T1')">
	          <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
              <input type="text" readonly maxlength="81" size="80" name="E01SDOC01" value="<%= instructions.getE01SDOC01().trim()%>"></div>
            </td>
            
            <td nowrap width="45%"> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEM1" value="<%= instructions.getE01SDTEM1().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTED1" value="<%= instructions.getE01SDTED1().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEY1" value="<%= instructions.getE01SDTEY1().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="10%"
> 
              <div align="center"> 
                <input type="checkbox" disabled  name="E01SFLG02" value="X" <%if(instructions.getE01SFLG02().equals("X")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap width="60%" > 
              <div align="left">
              <input type="text" readonly maxlength="5" size="4" name="E01CDOC02" value="<%= instructions.getE01CDOC02().trim()%>">
              <A href="javascript:GetCodeDescCNOFC('E01CDOC02','E01SDOC02','T1')">
	          <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
              <input type="text" readonly maxlength="81" size="80" name="E01SDOC02" value="<%= instructions.getE01SDOC02().trim()%>"></div>
            </td>
            <td nowrap width="45%"
> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEM2" value="<%= instructions.getE01SDTEM2().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTED2" value="<%= instructions.getE01SDTED2().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEY2" value="<%= instructions.getE01SDTEY2().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="10%"
 height="23"> 
              <div align="center"> 
                <input type="checkbox" disabled  name="E01SFLG03" value="X" <%if(instructions.getE01SFLG03().equals("X")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap width="60%" > 
              <div align="left">
              <input type="text" readonly maxlength="5" size="4" name="E01CDOC03" value="<%= instructions.getE01CDOC03().trim()%>">
              <A href="javascript:GetCodeDescCNOFC('E01CDOC03','E01SDOC03','T1')">
	          <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
              <input type="text" readonly maxlength="81" size="80" name="E01SDOC03" value="<%= instructions.getE01SDOC03().trim()%>"></div>
            </td>
            <td nowrap width="45%"
 height="23"> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEM3" value="<%= instructions.getE01SDTEM3().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTED3" value="<%= instructions.getE01SDTED3().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEY3" value="<%= instructions.getE01SDTEY3().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="10%"
 height="23"> 
              <div align="center"> 
                <input type="checkbox" disabled  name="E01SFLG04" value="X" <%if(instructions.getE01SFLG04().equals("X")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap width="60%" > 
              <div align="left">
              <input type="text" readonly maxlength="5" size="4" name="E01CDOC04" value="<%= instructions.getE01CDOC04().trim()%>">
              <A href="javascript:GetCodeDescCNOFC('E01CDOC04','E01SDOC04','T1')">
	          <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
              <input type="text" readonly maxlength="81" size="80" name="E01SDOC04" value="<%= instructions.getE01SDOC04().trim()%>"></div>
            </td>
            <td nowrap width="45%"
 height="23"> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEM4" value="<%= instructions.getE01SDTEM4().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTED4" value="<%= instructions.getE01SDTED4().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEY4" value="<%= instructions.getE01SDTEY4().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="10%"
 height="23"> 
              <div align="center"> 
                <input type="checkbox" disabled  name="E01SFLG05" value="X" <%if(instructions.getE01SFLG05().equals("X")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap width="60%" > 
              <div align="left">
              <input type="text" readonly maxlength="5" size="4" name="E01CDOC05" value="<%= instructions.getE01CDOC05().trim()%>">
              <A href="javascript:GetCodeDescCNOFC('E01CDOC05','E01SDOC05','T1')">
	          <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
              <input type="text" readonly maxlength="81" size="80" name="E01SDOC05" value="<%= instructions.getE01SDOC05().trim()%>"></div>
            </td>
            <td nowrap width="45%"
 height="23"> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEM5" value="<%= instructions.getE01SDTEM5().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTED5" value="<%= instructions.getE01SDTED5().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01SDTEY5" value="<%= instructions.getE01SDTEY5().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Otros Mensajes</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="70%"
> 
              <div align="center"><b>Mensajes</b></div>
            </td>
            <td nowrap width="30%"
> 
              <div align="center"><b>Expira en </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="70%"
> 
              <div align="center"> 
                <input type="text" readonly maxlength="80" size="81" name="E01MDSC01" value="<%= instructions.getE01MDSC01().trim()%>" >
              </div>
            </td>
            <td nowrap width="30%"
> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01MDTEM1" value="<%= instructions.getE01MDTEM1().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED1" value="<%= instructions.getE01MDTED1().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED1" value="<%= instructions.getE01MDTEY1().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="70%" > 
              <div align="center"> 
                <input type="text" readonly maxlength="80" size="81" name="E01MDSC02" value="<%= instructions.getE01MDSC02().trim()%>" >
              </div>
            </td>
            <td nowrap width="30%" > 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01MDTEM2" value="<%= instructions.getE01MDTEM2().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED2" value="<%= instructions.getE01MDTED2().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED2" value="<%= instructions.getE01MDTEY2().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="70%" height="23"> 
              <div align="center"> 
                <input type="text" readonly maxlength="80" size="81" name="E01MDSC03" value="<%= instructions.getE01MDSC03().trim()%>" >
              </div>
            </td>
            <td nowrap width="30%" height="23"> 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01MDTEM3" value="<%= instructions.getE01MDTEM3().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED3" value="<%= instructions.getE01MDTED3().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED3" value="<%= instructions.getE01MDTEY3().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="70%" > 
              <div align="center"> 
                <input type="text" readonly maxlength="80" size="81" name="E01MDSC04" value="<%= instructions.getE01MDSC04().trim()%>" >
              </div>
            </td>
            <td nowrap width="30%" > 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01MDTEM4" value="<%= instructions.getE01MDTEM4().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED4" value="<%= instructions.getE01MDTED4().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED4" value="<%= instructions.getE01MDTEY4().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="70%" > 
              <div align="center"> 
                <input type="text" readonly maxlength="80" size="81" name="E01MDSC05" value="<%= instructions.getE01MDSC05().trim()%>" >
              </div>
            </td>
            <td nowrap width="30%" > 
              <div align="center"> 
                <input type="text" readonly maxlength="2" size="2" name="E01MDTEM5" value="<%= instructions.getE01MDTEM5().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED5" value="<%= instructions.getE01MDTED5().trim()%>" onKeyPress="enterInteger()">
                <input type="text" readonly maxlength="2" size="2" name="E01MDTED5" value="<%= instructions.getE01MDTEY5().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
  <br>
  </form>
</body>
</html>
