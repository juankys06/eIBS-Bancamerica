<html>
<head>
<title>GL Detail</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "glDetail" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<BODY>
<h3 align="center">Detalle de Cuenta de Tesoreria<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="gl_list.jsp, EGL0590"></h3>
<hr size="4">
<form name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEGL0590" >
  <p> 
  <input type=hidden name="SCREEN" 	  value="100">
  <input type=hidden name="E01GLMBNK" value="<%=userPO.getBank()%>">
  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="100%"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Numero de Cuenta:</b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E02GLMGLN" size="20" maxlength="2"  value="<%=userPO.getHeader10()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Balance Inicial:</b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left">
                <input type="text" name="E02GLMBBL" size="20"  maxlength="3" value="<%=userPO.getHeader20()%>" readonly>
			  </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <%if (!glDetail.getNoResult()) {%>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
			<th align="CENTER" nowrap width="25%">
				<div align="center">Descripcion</div>
			</th>
			<th align="CENTER" nowrap width="15%">
				<div align="center">Debito</div>
			</th>
			<th align="CENTER" nowrap width="15%">
				<div align="center">Credito</div>
			</th>
			<th align="CENTER" nowrap width="15%">
				<div align="center">Balance</div>
			</th>
			<th align="CENTER" nowrap width="7%">
				<div align="center">Lote</div>
			</th>
			<th align="CENTER" nowrap width="7%">
				<div align="center">Hora</div>
			</th>
			<th align="CENTER" nowrap width="8%">
				<div align="center">Agencia</div>
			</th>
			<th align="CENTER" nowrap width="8%">
				<div align="center">Usuario</div>
			</th>
    	  </tr>
		<%
		glDetail.initRow();
		while (glDetail.getNextRow()) {
			datapro.eibs.beans.EGL059002Message msgList = (datapro.eibs.beans.EGL059002Message) glDetail.getRecord();
		%>
		<tr>
			<td nowrap align="LEFT"   width="\"><%= msgList.getE02TRADSC() %></td>
			<td nowrap align="RIGHT"  width="\"><%= msgList.getE02TRADEB() %></td>
			<td nowrap align="RIGHT"  width="\"><%= msgList.getE02TRACRE() %></td>
			<td nowrap align="RIGHT"  width="\"><%= msgList.getE02TRAABL() %></td>
			<td nowrap align="CENTER" width="\"><%= msgList.getE02TRABTH() %></td>
			<td nowrap align="CENTER" width="\"><%= msgList.getE02TRATIM() %></td>
			<td nowrap align="CENTER" width="\"><%= msgList.getE02TRABRN() %></td>
			<td nowrap align="CENTER" width="\"><%= msgList.getE02TRAUSR() %></td>
		</tr>
		<%}%>
  		</table>
      </td> 
    </tr> 
  </table>
  <%}%>
  <br>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Regresar" >
  </p>
</form>
</body>
</html>
