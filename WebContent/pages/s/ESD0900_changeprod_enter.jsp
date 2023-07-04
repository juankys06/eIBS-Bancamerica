<html> 
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>


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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0900" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  <h3 align="center">Cambio de C&oacutedigo de Productos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="change_products.jsp,ESD0900"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table id="tbenter" align="center" style="width:85%" border="1">
  
  
  <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
           <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Modulo:</div>
            </td>
            <td width="60%" nowrap> 
              <input type="text" name="E01CHGAPL" size="3" maxlength="2" >
              <a href="javascript:GetCodeDescCNOFC('E01CHGAPL','E01PRDDSC','AP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            <INPUT type="text" name="E01PRDDSC" size="30" maxlength="30" readonly >
            </td>
          </tr>
        </table>
      </td>
    </tr>
    
  <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
           <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Código de Banco:</div>
            </td>
            <td width="60%" nowrap>
              <a href="javascript:GetBranch('E01CHGBNK','')"></a><INPUT
					type="text" name="E01CHGBNK" size="3" maxlength="2"
					onkeypress="enterInteger()"><A
					href="javascript:GetAccByClient('E01CHGBNK','','CD','','')"></A></td>
          </tr>
        </table>
      </td>
    </tr>
  
  
  <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
           <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Tipo de Producto:</div>
            </td>
            <td width="60%" nowrap> 
              <input type="text" name="E01CHGPRT" size="5" maxlength="4" >
              <a href="javascript:GetCodeCNOFC('E01CHGPRT','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
    
    
  </table>
  <BR>
<p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </p>
  <script language="JavaScript">
  document.forms[0].E01CHGAPL.focus();
  document.forms[0].E01CHGAPL.select();
  
  </script>
        <p>&nbsp;</p>
  <p><BR>
  </p>
  <p align="center">&nbsp; </p>
      
</form>
</body>
</html>
