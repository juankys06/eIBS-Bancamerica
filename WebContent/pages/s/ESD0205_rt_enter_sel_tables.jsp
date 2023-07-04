<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "ESD0205msg" class= "datapro.eibs.beans.ESD020501Message"  scope="session"/>


<META name="GENERATOR" content="IBM WebSphere Studio">
</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%> 
  <h3 align="center">Mantenimiento de Cargos por Servicios y Tasas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_enter_sel_tables.jsp, ESD0205"></H3>
  <hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0205" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">

  <table class="tableinfo">
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Banco : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
          <input type="text" name="E01RTEBNK"  size=3 maxlength="2" value="<%=ESD0205msg.getE01RTEBNK()%>">
        </div>
      </td>
    </tr>
    <tr > 
      <td width="49%" nowrap> 
        <div align="right">Tipo de Producto : </div>
      </td>
      <td width="51%" nowrap> 
        <input type="text" name="E01RTEATY"  size=5 maxlength="4" value="<%=ESD0205msg.getE01RTEATY()%>">
        <a href="javascript:GetProductRates('E01RTEATY','RT')"> 
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
      </td>
    </tr>
    <tr > 
      <td width="49%" nowrap> 
        <div align="right">Cliente : </div>
      </td>
      <td width="51%" nowrap> 
        <input type="text" name="E01RTECUN"  size=12 maxlength="9" value="<%=ESD0205msg.getE01RTECUN()%>">
        <a href="javascript:GetCustomer('E01RTECUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
              
</form>
</body>
</html>
