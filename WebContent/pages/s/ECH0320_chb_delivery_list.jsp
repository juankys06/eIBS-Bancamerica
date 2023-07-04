<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Cuentas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  
  function goAction() {
     
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(var n=0;n<formLength;n++)
      {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ROW") 
      	{
        		ok = true;
        		break;
      	}
      }
      if ( ok ) 
      {          
      	if (document.forms[0].E02CHMENT.value != "")      
      	{
	      	if (document.forms[0].E02CHMEIDN.value != "")
		 	{
			<% if (currUser.getE01INT().equals("18")) {%> 			 	
	 			if (Fun_Valida_Rut())
	 			{
	 	 	      document.forms[0].submit();
	 	  	    }
 	
			<%} else {%> 
                document.forms[0].submit(); 
			<%}%>  	  	 	  	    
	 	  	 }
	 	  	 else
	 	  	 {
			 	window.document.forms[0].E02CHMEIDN.focus();
				window.document.forms[0].E02CHMEIDN.select();
			<% if (currUser.getE01INT().equals("18")) {%>  				
				alert("Debe ingresar el Rut");
			<%} else {%>  
                alert("Debe ingresar la Identificación");
			<%}%>  					   	 	  	 
	 	  	 }
	 	  	 
 	    }
 	    else
		  	if (document.forms[0].E02CHMEIDN.value != "")
		 	{ 	    
			 	window.document.forms[0].E02CHMENT.focus();
				window.document.forms[0].E02CHMENT.select();
				alert("Debe ingresar el nombre");
 	    	}
 	    
 	  else
 	   {
	 	       document.forms[0].submit(); 	    
 	    }
      }
      else {
 		alert("Seleccione una chequera antes de realizar esta operacion");	   
      }

 }
  
 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
 }
 
 function showAddInfo(idxRow){
   var myTable = document.all["dataTable"];
   for ( var i=1; i< myTable.rows.length; i++ ) {
       myTable.rows[i].className="trnormal";
    }    
   myTable.rows[idxRow + 1].className="trhighlight";
   document.forms[0].E02CHMENT.focus();
  } 
    
<% if (currUser.getE01INT().equals("18")) {%>   

function Fun_Valida_Rut()
{	
	var Str_Temp = "";
	var Str_RutInvertido = "";
	var Str_Dtexto = "";


	Str_TextoRut = document.forms[0].E02CHMEIDN.value;
 			 			 
	if (Str_TextoRut == '')
	{//	window.document.forms[0].E02CHMEIDN.focus();
	//	window.document.forms[0].E02CHMEIDN.select();
		return false;
	}
	//Reupera Rut sin puntos ni guiones //
	for ( i=0; i < Str_TextoRut.length ; i++ )
		if ( Str_TextoRut.charAt(i) != ' ' && Str_TextoRut.charAt(i) != '.' && Str_TextoRut.charAt(i) != '-' )
			Str_Temp = Str_Temp + Str_TextoRut.charAt(i);
		         	
	Str_TextoRut = Str_Temp;
	Int_Largo = Str_TextoRut.length;	 
	if (Int_Largo < 2 ) 
	{	alert("Debe ingresar un R.U.T completo.");
		window.document.forms[0].E02CHMEIDN.focus();
		window.document.forms[0].E02CHMEIDN.select();
		return false;
	}
	for (i=0; i < Int_Largo ; i++ )	
	{	if ( Str_TextoRut.charAt(i) !="0" && Str_TextoRut.charAt(i) != "1" && Str_TextoRut.charAt(i) !="2" && Str_TextoRut.charAt(i) != "3" && Str_TextoRut.charAt(i) != "4" && Str_TextoRut.charAt(i) !="5" && Str_TextoRut.charAt(i) != "6" && Str_TextoRut.charAt(i) != "7" && Str_TextoRut.charAt(i) !="8" && Str_TextoRut.charAt(i) != "9" && Str_TextoRut.charAt(i) !="k" && Str_TextoRut.charAt(i) != "K" )
		{	alert("El valor no corresponde a un R.U.T");
			window.document.forms[0].E02CHMEIDN.focus();
			window.document.forms[0].E02CHMEIDN.select();
			return false;
		}
	}
	//Invertir Rut                
	for ( i=(Int_Largo-1); i>=0; i--)
		Str_RutInvertido = Str_RutInvertido + Str_TextoRut.charAt(i);
                 
	Str_Dtexto = Str_Dtexto + Str_RutInvertido.charAt(0);
	Str_Dtexto = Str_Dtexto + '-';
             
	Int_Cnt = 0;
	for ( i=1; i<Int_Largo; i++)  
	{	if ( Int_Cnt == 3 ) 
		{	Str_Dtexto = Str_Dtexto + '.';
			Str_Dtexto = Str_Dtexto + Str_RutInvertido.charAt(i);
			Int_Cnt = 1;
		}
		else 
		{	Str_Dtexto = Str_Dtexto + Str_RutInvertido.charAt(i);
			Int_Cnt++;
		}
	}               
	Str_RutAux = "";
	for ( i=(Str_Dtexto.length-1); i>=0;i--)
		Str_RutAux = Str_RutAux + Str_Dtexto.charAt(i);
	         
	//window.document.forms[0].NameSearch.value  = Str_RutAux;  

	if ( Fun_Chequea_DigVer(Str_TextoRut) )
	{	//window.document.forms[0].NameSearch = Str_TextoRut;  
		//window.document.forms[0].NameSearch.value  = Str_RutAux;  
		return true;
	}
	return false;
}

function Fun_Chequea_DigVer( Str_RutPar )
{	Int_Largo = Str_RutPar.length;

	if ( Int_Largo < 2 )
	{	alert("Debe ingresar un R.U.T completo.");
		window.document.forms[0].E02CHMEIDN.focus();
		window.document.forms[0].E02CHMEIDN.select();
		return false;
	}

	if ( Int_Largo > 2 )
		Str_Rut = Str_RutPar.substring(0, Int_Largo - 1);
	else
		Str_Rut = Str_RutPar.charAt(0);
            
	Str_DigVer = Str_RutPar.charAt(Int_Largo-1);
    
	Fun_Chequea_Valor_DigVer( Str_DigVer );
	if ( Str_Rut == null || Str_DigVer == null )
		return false;

	var Str_DigVer_Valido = '0';
	Int_Suma = 0;
	Int_Multiplo  = 2;

	for (i= Str_Rut.length -1 ; i >= 0; i--)
	{	Int_Suma = Int_Suma + Str_Rut.charAt(i) * Int_Multiplo;
		if (Int_Multiplo == 7)
			Int_Multiplo = 2;
		else    
			Int_Multiplo++;
	}

	Int_Resto = Int_Suma % 11;
	
	if (Int_Resto==1)
		Str_DigVer_Valido = 'k';
	else 
		if (Int_Resto==0)
			Str_DigVer_Valido = '0';
 		else 
		{	Inr_DigVer_Valido = 11-Int_Resto;
			Str_DigVer_Valido = Inr_DigVer_Valido + "";
		}

	if ( Str_DigVer_Valido != Str_DigVer.toLowerCase() )  
	{	alert("El Rut es incorrecto.");
		window.document.forms[0].E02CHMEIDN.focus();
		window.document.forms[0].E02CHMEIDN.select();
		return false;
	}
	return true;
}

function Fun_Chequea_Valor_DigVer( Str_DigVer )
{	Str_DigVerAux = Str_DigVer + "";
	
	if ( Str_DigVerAux != '0' && Str_DigVerAux != '1' && Str_DigVerAux != '2' && Str_DigVerAux != '3' && Str_DigVerAux != '4' && Str_DigVerAux != '5' && Str_DigVerAux != '6' && Str_DigVerAux != '7' && Str_DigVerAux != '8' && Str_DigVerAux != '9' && Str_DigVerAux != 'k'  && Str_DigVerAux != 'K')
	{	alert("Debe ingresar un digito verificador valido.");
		window.document.forms[0].E02CHMEIDN.focus();
		window.document.forms[0].E02CHMEIDN.select();
		return false;
	}
	return true;
}
      

<%}%>  
	
</SCRIPT>

</HEAD>

<BODY>

<% 
 boolean firstTime = true;
 String chk = "";
 int row;
 try {
	  	row = Integer.parseInt(request.getParameter("ROW"));
	  	firstTime = false;
	} 
 catch (Exception e) {
		row = 0;
		firstTime = true;		
	}
	
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     //firstTime = false;
 } 
%>


<h3 align="center">Entrega de Chequeras	a Clientes
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_delivery_list.jsp,ECH0320">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0320" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="9"> 
<%
	if ( chkbList.getNoResult() ) {
%> 

  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay resultados que correspondan a su criterio de búsqueda</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else { 
	
	chkbList.initRow();
	chkbList.getNextRow();
	datapro.eibs.beans.ECH032002Message msgChkB = (datapro.eibs.beans.ECH032002Message) chkbList.getRecord();

  %>
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E02CHMCUN" size="9" maxlength="9" readonly value="<%= msgChkB.getE02CHMCUN().trim()%>">
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02CHMNA1" size="45" maxlength="45" readonly value="<%= msgChkB.getE02CHMNA1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02CHMACC" size="12" maxlength="12" value="<%= msgChkB.getE02CHMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02CHMCCY" size="3" maxlength="3" value="<%= msgChkB.getE02CHMCCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02CHMPRO" size="4" maxlength="4" readonly value="<%= msgChkB.getE02CHMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n Relevante</H4>
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Cliente ID.</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02CHMIDN" size="15" readonly value="<%= msgChkB.getE02CHMIDN().trim()%>">
            </td>
            <td nowrap> 
              <div align="right">Tipo ID. :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02CHMTID" size="4" readonly value="<%= msgChkB.getE02CHMTID().trim()%>">
            </td>
            <td nowrap> 
              <div align="right">Pais :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02CHMPID" size="4" readonly value="<%= msgChkB.getE02CHMPID().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Stock Minimo :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02CHMMSK" size="10" value="<%= msgChkB.getE02CHMMSK().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Saldo Contable :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02CHMMGB" size="10" value="<%= msgChkB.getE02CHMMGB().trim()%>" readonly>
            </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Stock Actual :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02CHMASK" size="10" value="<%= msgChkB.getE02CHMASK().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02CHMMNB" size="10" value="<%= msgChkB.getE02CHMMNB().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Veces Sobregiro :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02CHMNRY" size="10" value="<%= msgChkB.getE02CHMNRY().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Fecha Sobregiro :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02LSTOD1" size="3" value="<%= msgChkB.getE02LSTOD1().trim()%>" readonly>
              <input type="text" name="E02LSTOD2" size="3" value="<%= msgChkB.getE02LSTOD2().trim()%>" readonly>
              <input type="text" name="E02LSTOD3" size="3" value="<%= msgChkB.getE02LSTOD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap > 
              <div align="right">Con Cobro :</div>
            </td>
            <td nowrap colspan=2>
              <select name="E02CHMCBC">
                <option value="N" <%if (msgChkB.getE02CHMCBC().equals("N")) out.print("selected"); %>>NO</option>
                <option value="Y" <% if (msgChkB.getE02CHMCBC().equals("Y")) out.print("selected"); %>>SI</option>
              </select>
            </td>
            <td nowrap> 
              <div align="right">Fecha Apertura :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E02OPNDT1" size="3" value="<%= msgChkB.getE02OPNDT1().trim()%>" readonly>
              <input type="text" name="E02OPNDT2" size="3" value="<%= msgChkB.getE02OPNDT2().trim()%>" readonly>
              <input type="text" name="E02OPNDT3" size="3" value="<%= msgChkB.getE02OPNDT3().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
     
  <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction('A')" id="linkApproval">Entregar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goExit()">Salir</a> </div>
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
 <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Fecha<br>Solicitud</TH>    
    		<TH ALIGN=CENTER >Número<br>Cheques</TH>
    		<TH ALIGN=CENTER >Cheque<br>Inicial</TH>
    		<TH ALIGN=CENTER >Cheque<br>Final</TH>
  		</TR>
     <%
                chkbList.initRow();                
                while (chkbList.getNextRow()) {
                  if (firstTime) {
						firstTime = false;
						chk = "checked";
				  }
				  else {
						if (chkbList.getCurrentRow() == row )
							chk = "checked";
						else 
							chk = "";
				  }
                  msgChkB = (datapro.eibs.beans.ECH032002Message) chkbList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= chkbList.getCurrentRow()%>" <%=chk%>  onclick="showAddInfo(<%= chkbList.getCurrentRow()%>)"></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgChkB.getE02CHMRQ1(),msgChkB.getE02CHMRQ2(),msgChkB.getE02CHMRQ3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgChkB.getE02CHMNCB())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgChkB.getE02CHMICK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgChkB.getE02CHMFCK())%></TD>
		</TR>    		
    <%                  
                }
    %> 
   
     </TABLE>
    </TD>
     <TD valign=top>
        <TABLE  border = "0" width="100%" >
          <TR id="trdark"> 
            <TH ALIGN=CENTER colspan=2>Entrega <br>
              a Terceros</TH>
          </TR>
          <TR> 
            <td ALIGN=right>Nombre</td>
            <td> 
              <input type="text" name="E02CHMENT" size="60" maxlength="60" value="">
            </td>
          </TR>
          <TR> 

		<% if (currUser.getE01INT().equals("18")) {%> 
            <td ALIGN=right>Rut</td>
            <td> 
              <input type="text" name="E02CHMEIDN" size="15" maxlength="15" onblur="Fun_Valida_Rut();">
            </td>
        <%} else {%>           
            <td ALIGN=right>Identificaci&oacute;n</td>
            <td> 
              <input type="text" name="E02CHMEIDN" size="15" maxlength="15">
            </td>
		<%}%> 
          </TR>
          <TR> 
            <td ALIGN=right></td>
            <td> 
              <input type="hidden" name="E02CHMETID" size="4" maxlength="4" value="RUT">
              <!--    		    <a href="javascript:GetCodeCNOFC('E02CHMETID','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>           --> 
            </td>
          </TR>
          <TR> 
            <td  ALIGN=right ></td>
            <td> 
              <input type="hidden" name="E02CHMEPID" size="4" maxlength="4" value="CL">
              <!--    		    <a href="javascript:GetCodeCNOFC('E02CHMEPID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>             --> 
            </td>
          </TR>
        </TABLE>
            </TD>
        </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("ROW");       
</SCRIPT>

<%   		
	} 
 %>
</FORM>

</BODY>
</HTML>
