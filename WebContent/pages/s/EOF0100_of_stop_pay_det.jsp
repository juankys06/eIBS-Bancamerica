<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Stop Payments</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="stpchk" class= "datapro.eibs.beans.EOF010001Message"  scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


 <script language=javascript>
  function valida_motivo(){
  	var motivo=trim(document.forms[0].E01DSCCAU.value);

  	if(motivo =="MOTIVOS VARIOS"){
  		alert("Motivo Invalido para este Producto");
  		return false;
  	} 
  	else
  	{
  		return true;
  	}	
  }

  function enviar()
  {
    if (valida_motivo())
    { document.forms[0].submit();
     }
     
  }
  
  function goAction(op) {
    document.forms[0].opt.value 		= op;
    
    switch(op){
    	  case 2 :
    	  		enviar();
				break;
		  case 3 :
				if (!confirm("Esta seguro que desea eliminar esta orden de no pago?")) return;
				document.forms[0].H01OPECOD.value 	= '0009';
				document.forms[0].submit();
				break;
		  case 5 :
				document.forms[0].H01OPECOD.value 	= '0020';
				document.forms[0].submit();
				break;
	}  
  } 
 </script>
 
</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>


<H3 align="center">Cheques de Gerencia - Orden de No Pago
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_stop_pay_det.jsp , EOF0100"></H3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0100" >
  <input type=HIDDEN name="SCREEN" value="10">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="H01OPECOD" VALUE="0005">
  <INPUT TYPE=HIDDEN NAME="E01STPSEQ" VALUE="1">


  

  <% if (!stpchk.getH01FLGMAS().equals("N")) {%>
  <table class="tbenter" >
    <tr>
      <td class=TDBKG>
        <div align="center"><a href="javascript:goAction(2)">Enviar</a></div>
      </td>
      <td class=TDBKG>
        <div align="center"><a href="javascript:goAction(3)">Eliminar</a></div>
      </td>
      <td class=TDBKG>
        <div align="center"><a href="javascript:goAction(5)">Aclarar</a></div>
      </td>
      <td class=TDBKG>
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a></div>
      </td>
    </tr>
  </table>
  <% } %>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cheque :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01STPCKN" size="9" maxlength="9" value="<%= stpchk.getE01STPCKN()%>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap> 
              <div align="left"><b><input type="text" name="E01STPCCY" size="4" maxlength="3" value="<%= stpchk.getE01STPCCY()%>" readonly>
                </b> </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Monto : </b></div>
            </td>
            <td nowrap> 
              <div align="left"><b><input type="text" name="E01STPAMT" size="15" maxlength="15" readonly value="<%= stpchk.getE01STPAMT()%>">
                </b> </div>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">          
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha del Cheque :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01STPCK1" size="3" maxlength="2" value="<%= stpchk.getE01STPCK1()%>">
                <input type="text" name="E01STPCK2" size="3" maxlength="2" value="<%= stpchk.getE01STPCK2()%>">
                <input type="text" name="E01STPCK3" size="3" maxlength="2" value="<%= stpchk.getE01STPCK3()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly name="E01STPBNF" size="30" maxlength="30" value="<%= stpchk.getE01STPBNF()%>" >            
            </td>
           </tr>
          
          <tr id="trdark">
            <td nowrap> 
              <div align="right">Aplicante : </div>
            </td>
            <td nowrap> 
              <div align="left"><input type="text" name="E01STPAPL" size="45" maxlength="45" readonly value="<%= stpchk.getE01STPAPL()%>">
              </div>
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Banco / Sucursal :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01STPBNK" size="4" maxlength="3" value="<%= stpchk.getE01STPBNK()%>" readonly>
              / 
              <input type="text" name="E01STPBRN" size="4" maxlength="3" value="<%= stpchk.getE01STPBRN()%>" readonly>
            </td>
          </tr>
          
          <tr id="trdark">
            <td nowrap>
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01STPGLN" size="16" maxlength="12" value="<%= stpchk.getE01STPGLN()%>" readonly>
            </td>
          </tr>
       
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Comentario :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01STPRMK" size="35" maxlength="35" value="<%= stpchk.getE01STPRMK()%>">
            </td>
          </tr>
          <tr id="trdark">             
            <td nowrap width="46%" > 
              <div align="right">Motivo :</div>
            </td>
            <td nowrap width="54%" > 
              <input type="text" name="E01STPF02" size="5" maxlength="4" value="<%= stpchk.getE01STPF02().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01STPF02','E01DSCCAU','51')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
              <input type="text" name="E01DSCCAU" size="35" maxlength="35" value="<%=stpchk.getE01DSCCAU().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<% if (stpchk.getH01FLGMAS().equals("N")) {%>
 <p align="center"> 
	<input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="enviar()">
 </p>  
 <% }%>
  </form>
</body>
</html>
