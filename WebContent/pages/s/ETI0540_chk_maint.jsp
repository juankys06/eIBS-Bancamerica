<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Loan Parties Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgData" class= "datapro.eibs.beans.ETI054001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



</head>
<body>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chk_maint,ETI0540" >
<h3 align="center">Mantenimiento Proteccion de Cheques</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 }
 
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.teller.JSETI0540" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">


	<table class="tableinfo">
    <tr > 
      <td> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <TBODY>
          <tr id="trclear"> 
            <td nowrap align=right> 
              <b>Banco :</b>
            </td>
            <td nowrap>  
               <input type="text" name="E01CKCBNK" size="3" maxlength="2" value="<%= userPO.getBank()%>" readonly>
            </td>
            <td nowrap></td>
            <td nowrap ></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Cuenta :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E01CKCACC" size="13" maxlength="12" value="<%=userPO.getAccNum()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Titular :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E01CKCNME" size="35" maxlength="35" value="<%=(userPO.getCusName().equals("")?msgData.getE01CKCNME():userPO.getCusName())%>" readonly>
            </td>
          </tr>

          
        </TBODY></TABLE>
      </td>
    </tr>
  </table>
       
       <br>
   
        
    <table cellpadding=2 cellspacing=0 width="100%" border="0" class="tableinfo" >
          
          <tr id="trdark"> 
            <td nowrap align=right width="20%"> 
              Numero de Cheque :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCCKN" size="10" maxlength="9" value="<%= msgData.getE01CKCCKN()%>"  "<%=(userPO.getPurpose().equals("NEW")?"":"readonly")%>">
            </td>
            <td nowrap align=right width="20%">
            Moneda :</td>
            <td nowrap width="30%">
            <INPUT type="text" name="E01CKCCCY" size="4" maxlength="3" value="<%= msgData.getE01CKCCCY()%>" "<%=(userPO.getPurpose().equals("NEW")?"":"readonly")%>"	>
            </td>
          </tr>
          <tr id="trclear">           
            <td nowrap align=right width="20%"> 
              Monto :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCAMT" size="16" maxlength="15" onkeypress="enterDecimal()" value="<%= msgData.getE01CKCAMT()%>" 
                <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
               >
            </td>
            <td nowrap align=right width="20%"> 
              Beneficiario :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCBNF" size="51" maxlength="50" value="<%= msgData.getE01CKCBNF()%>" 
               <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
               >
            </td> 
          </tr>
          <tr id="trdark">            
            <td nowrap align=right width="20%"> 
              Medio de registro Cheque:
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCCNL" size="5" maxlength="4" value="<%= msgData.getE01CKCCNL()%>" 
                <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
               >
               <a href="javascript:GetCodeCNOFC('E01CKCCNL','MR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>  
            <td nowrap align=right width="20%"> 
              Canal de pago:
            </td>
            <td nowrap width="30%">  
                <SELECT name="E01CKCCNP" <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("disabled");}%> >
                    <OPTION value=""  <%if(msgData.getE01CKCCNP().equals("")){%> Selected <%}%> ></OPTION>
					<OPTION value="1"  <%if(msgData.getE01CKCCNP().equals("1")){%> Selected <%}%> >Caja  </OPTION>
					<OPTION value="2" <%if(msgData.getE01CKCCNP().equals("2")){%> Selected <%}%> >Compensacion</OPTION>					
				</SELECT>
            </td>           
          </tr>           
          <tr id="trclear"> 
            <td nowrap align=right width="20%"> 
              Estado :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCSTS" size="2" maxlength="1" value="<%= msgData.getE01CKCSTS()%>" 
               <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
               >      A=Activo I=Inactivo P=Pagado</td>  
            <td nowrap align=right width="20%"> Fecha de Vigencia</td>
            <td nowrap align="left" width="30%">
            <INPUT type="text" name="E01CKCCEXM" size="2" maxlength="2" value="<%= msgData.getE01CKCEXM()%>"
               <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
            >
            <INPUT type="text" name="E01CKCCEXD" size="2" maxlength="2" value="<%= msgData.getE01CKCEXD()%>"
               <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
            >
            <INPUT type="text" name="E01CKCCEXY" size="2" maxlength="2" value="<%= msgData.getE01CKCEXY()%>"
               <% if(msgData.getE01CKCSTS().equals("I")|| msgData.getE01CKCSTS().equals("O")){out.print("readonly");}%>                              
            >
            </td>          
          </tr>
               
    </table>
    
    <h4>Registro de la Operación </h4>
    <table cellpadding=2 cellspacing=0  border="0" class="tableinfo" width="100%">
          
          <tr id="trdark"> 
            <td nowrap align=right width="20%"> 
              Fecha :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCRDM" size="2" maxlength="2" value="<%= msgData.getE01CKCRDM()%>"  readonly>
               <input type="text" name="E01CKCRDD" size="2" maxlength="2" value="<%= msgData.getE01CKCRDD()%>"  readonly>
               <input type="text" name="E01CKCRDY" size="2" maxlength="2" value="<%= msgData.getE01CKCRDY()%>"  readonly>
            </td>
            <td nowrap align=right width="20%">
            Hora :</td>
            <td nowrap width="30%">
            <INPUT type="text" name="E01CKCRTM" size="10" maxlength="10" value="<%= msgData.getE01CKCRTM()%>" 	readonly>
            </td>
          </tr>
          <tr id="trclear">           
            <td nowrap align=right width="20%">Usuario :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCRUS" size="10" maxlength="10"  value="<%= msgData.getE01CKCRUS()%>" readonly>
            </td>
            <td nowrap align=right width="20%"></td>
            <td nowrap width="30%"></td> 
          </tr>          
               
    </table>

<H4>Verificación de la Operación</H4>
<table cellpadding=2 cellspacing=0  border="0" class="tableinfo" width="100%">
          
          <tr id="trdark"> 
            <td nowrap align=right width="20%"> 
              Fecha :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCVFM" size="2" maxlength="2" value="<%= msgData.getE01CKCVFM()%>"  readonly>
               <input type="text" name="E01CKCVFD" size="2" maxlength="2" value="<%= msgData.getE01CKCVFD()%>"  readonly>
               <input type="text" name="E01CKCVFY" size="2" maxlength="2" value="<%= msgData.getE01CKCVFY()%>"  readonly>
            </td>
            <td nowrap align=right width="20%">
            Hora :</td>
            <td nowrap width="30%">
            <INPUT type="text" name="E01CKCTIM" size="10" maxlength="10" value="<%= msgData.getE01CKCTIM()%>" 	readonly>
            </td>
          </tr>
          <tr id="trclear">           
            <td nowrap align=right width="20%">Usuario :
            </td>
            <td nowrap width="30%">  
               <input type="text" name="E01CKCUSR" size="10" maxlength="10"  value="<%= msgData.getE01CKCUSR()%>" readonly>
            </td>
            <td nowrap align=right width="20%">Agencia:</td>
            <td nowrap width="30%">
            <INPUT type="text" name="E01CKCBRN" size="4" maxlength="4" value="<%= msgData.getE01CKCBRN()%>" 	readonly>
            </td> 
          </tr>          
               
    </table>
<br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Submit">
  </div>
  </form>
</body>
</html>
