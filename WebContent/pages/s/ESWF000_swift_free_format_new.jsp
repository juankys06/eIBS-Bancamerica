<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Swift Free Format New</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="swff" class="datapro.eibs.beans.ESWF00001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

 function Validate(){
   document.forms[0].SCREEN.value = "26";
   document.forms[0].submit();
}
 function SubmitAcc(){
   document.forms[0].SCREEN.value = "2";
   document.forms[0].submit();
}
</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%>
<h3 align="center"> SWIFT - Formatos Libres<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="swift_free_format_new.jsp,ESWF000"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF000" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="2">
  </p>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Identificaci&oacute;n Swift :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESWFSWI" size="15" maxlength="15" value="<%= swff.getESWFSWI()%>">
              <a href="javascript:GetSwiftId('ESWFSWI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="26%" > 
              <div align="right">Identificaci&oacute;n de Usuario :</div>
            </td>
            <td nowrap > 
              <input type="text" name="H01USR" size="11" maxlength="11" value="<%= swff.getH01USR()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESWFREF" size="15" maxlength="15" value="<%= swff.getESWFREF()%>">
            </td>
            <td nowrap width="26%" > 
              <div align="right">Referencia Relacionada : </div>
            </td>
            <td nowrap > 
              <input type="text" name="ESWFRLR" size="15" maxlength="15" value="<%= swff.getESWFRLR()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Formato Swift :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESWFFMT" size="15" maxlength="15" value="<%= swff.getESWFFMT()%>">
              <a href="javascript:GetCode('ESWFFMT','STATIC_Swif_FF_types.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="26%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="11%" >&nbsp; </td>
          </tr>
          <tr id="clear"> 
            <td nowrap colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="center">Comentarios</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF01" size="80" maxlength="50" value="<%= swff.getESWF01()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF02" size="80" maxlength="50" value="<%= swff.getESWF02()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF03" size="80" maxlength="50" value="<%= swff.getESWF03()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF04" size="80" maxlength="50" value="<%= swff.getESWF04()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF05" size="80" maxlength="50" value="<%= swff.getESWF05()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF06" size="80" maxlength="50" value="<%= swff.getESWF06()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF07" size="80" maxlength="50" value="<%= swff.getESWF07()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF08" size="80" maxlength="50" value="<%= swff.getESWF08()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF09" size="80" maxlength="50" value="<%= swff.getESWF09()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF10" size="80" maxlength="50" value="<%= swff.getESWF10()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF11" size="80" maxlength="50" value="<%= swff.getESWF11()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF12" size="80" maxlength="50" value="<%= swff.getESWF12()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF13" size="80" maxlength="50" value="<%= swff.getESWF13()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF14" size="80" maxlength="50" value="<%= swff.getESWF14()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF15" size="80" maxlength="50" value="<%= swff.getESWF15()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF16" size="80" maxlength="50" value="<%= swff.getESWF16()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF17" size="80" maxlength="50" value="<%= swff.getESWF17()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF18" size="80" maxlength="50" value="<%= swff.getESWF18()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF19" size="80" maxlength="50" value="<%= swff.getESWF19()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF20" size="80" maxlength="50" value="<%= swff.getESWF20()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF21" size="80" maxlength="50" value="<%= swff.getESWF21()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF22" size="80" maxlength="50" value="<%= swff.getESWF22()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF23" size="80" maxlength="50" value="<%= swff.getESWF23()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF24" size="80" maxlength="50" value="<%= swff.getESWF24()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF25" size="80" maxlength="50" value="<%= swff.getESWF25()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF26" size="80" maxlength="50" value="<%= swff.getESWF26()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF27" size="80" maxlength="50" value="<%= swff.getESWF27()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF28" size="80" maxlength="50" value="<%= swff.getESWF28()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF29" size="80" maxlength="50" value="<%= swff.getESWF29()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF30" size="80" maxlength="50" value="<%= swff.getESWF30()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF31" size="80" maxlength="50" value="<%= swff.getESWF31()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF32" size="80" maxlength="50" value="<%= swff.getESWF32()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF33" size="80" maxlength="50" value="<%= swff.getESWF33()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF34" size="80" maxlength="50" value="<%= swff.getESWF34()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESWF35" size="80" maxlength="50" value="<%= swff.getESWF35()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="SubmitAcc();">
        </div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
