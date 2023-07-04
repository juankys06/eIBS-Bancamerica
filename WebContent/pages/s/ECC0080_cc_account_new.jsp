<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Assign Accounts to Credit Cards</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgAcc" class= "datapro.eibs.beans.ECC008001Message"  scope="session" />

</head>
<body>
<h3 align="center">Asignacion de cuentas a tarjetas
<BR>Nueva
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_account_new.jsp,ECC0080"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Numero Tarjeta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CCANUM" size="21" maxlength="20" readonly value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente de la tarjeta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01CARCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>">                
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CARNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>                  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCAACC" size="12" maxlength="9" value="<%= msgAcc.getE01CCAACC().trim()%>">
                <a href="javascript:GetAccount('E01CCAACC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo de cuenta :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
	              <input type="text" readonly name="E01CCAATY" size="4" maxlength="3" value="<%= msgAcc.getE01CCAATY().trim()%>">                
                </b> </div>
            </td>
          </tr>   
          <%--   
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente de la cuenta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01CTACUN" size="9" maxlength="9" value="<%= msgAcc.getE01CTACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CTANA1" size="45" maxlength="45" readonly value="<%= msgAcc.getE01CTANA1().trim()%>">
              </div>
            </td>
          </tr>
          --%>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>

