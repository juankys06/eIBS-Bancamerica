<html>
<head>
<title>Inventario Documentos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "document" class= "datapro.eibs.beans.EDI010102Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
<SCRIPT Language="Javascript">

   

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }


</SCRIPT>



</head>

<body >

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
   
 
%>

<h3 align="center">Sistema de Inventario de Documentos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="doc_tables_detail_basic.jsp, EDI010"></h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDI010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">  
  <br> 
 <table class="tableinfo">
    <tr > 
      <td> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0"   >
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"><b>Tabla :</b> </div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="text" name="E02DCITNU" size="4" maxlength="3"  value="<%= document.getE02DCITNU()%>">
                <input type="text" name="E02DCISDS" size="40" maxlength="35"  value="<%= document.getE02DCISDS()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" > 
              <div align="center"><b>
                <% if(document.getE02DCIACT().equals("S")) out.print("Propuesta de Crédito");
              				else if(document.getE02DCIACT().equals("A")) out.print("Cuenta");
							else if(document.getE02DCIACT().equals("C")) out.print("Cliente Corporativo");
							else if(document.getE02DCIACT().equals("P")) out.print("Cliente Personal");
							else out.print("Propuesta de Crédito");%>
                </b></div>
            </td>
          </tr>
        </table>
           </td>
		</tr>
      </table>
    <br><table class="tableinfo">
    <tr > 
      <td> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0"    align="center">
            <tr id="trdark"> 
              <td nowrap width="10%"> 
                
              <div align="center"><b>Seq.</b></div>
              </td>
              <td nowrap width="10%"> 
                <div align="center"><b>Frec.</b></div>
              </td>
              <td nowrap> 
                <div align="center"><b>Tipo <br>
                  Doc.</b></div>
              </td>
              <td nowrap width="25%"> 
                <div align="center"><b>Descripción <br>
                  Español</b></div>
              </td>
              <td nowrap width="25%"> 
                <div align="center"><b>Descripción <br>
                  Ingles</b></div>
              </td>
              <td nowrap width="10%"> 
                <div align="center"><b>Doc.<br>
                  Req.</b></div>
              </td>
              <td nowrap width="10%"> 
                <div align="center"><b>Nivel<br>
                  Cons.</b></div>
              </td>
            </tr>
            <%
  int document_amount = 99;
  String name;
  for ( int i=1; i<=document_amount; i++ ) {
    name = (i<10)? "0" + i : ""+ i;

    
%>
            <tr id="trclear"> 
              <td nowrap width="10%"> 
                <div align="center"> 
                  <input type="text" name="E02DCISQ<%= name %>" maxlength="4" size="2" value="<%= name %>">
                </div>
              </td>
              <td nowrap width="10%"> 
                <div align="center"> 
                <input type="text" name="E02DCIFR<%= name %>" size="2"  maxlength="1"
				oncontextmenu="showPopUp(documentsFrecuencyHelp,this.name,'','','','','')"  value="<%= document.getField("E02DCIFR" + name ).getString().trim()%>" >

                </div>
              </td>
              <td nowrap width="10%"> 
                <div align="center"> 
                <input type="text" name="E02TBLTY<%= name %>" size="3"  maxlength="2"
				oncontextmenu="showPopUp(documentsTypeHelp,this.name,'','','','','')"  value="<%= document.getField("E02TBLTY" + name ).getString().trim()%>" >

                </div>
              </td>
              <td nowrap width="25%"> 
                <div align="center"> 
                  <input type="text" name="E02DCISD<%= name %>" maxlength="35" size="36" value="<%= document.getField("E02DCISD" + name).getString().trim()%>">
                </div>
              </td>
              <td nowrap width="25%"> 
                <div align="center">
                  <input type="text" name="E02DCIED<%= name %>" maxlength="35" size="35" value="<%= document.getField("E02DCIED" + name).getString().trim()%>">
                </div>
              </td>
              <td nowrap> 
                <div align="center" width="10%"> 
                  <select name="E02TBLRQ<%= name %>">
                    <option value=" "   <% if ( !(document.getField("E02TBLRQ" + name).getString().equals("0") || document.getField("E02TBLRQ" + name).getString().equals("1"))) out.print("selected"); %>></option>
                    <option value=0 <% if (document.getField("E02TBLRQ" + name).getString().equals("0")) out.print("selected"); %>>Si 
                    </option>
                    <option value=1 <% if (document.getField("E02TBLRQ" + name).getString().equals("1")) out.print("selected"); %>>No</option>
                  </select>
                </div>
              </td>
              <td nowrap> 
                <div align="center" width="10%">
                  <input type="text" name="E02TBLIL<%= name %>" maxlength="2" size="2" value="<%= document.getField("E02TBLIL" + name).getString().trim()%>">
                </div>
              </td>
            </tr>
            <%
  }
%>
          </table>
        </td>
            </tr>
  </table>
  
 
 
   <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
 	 
</form>
</body>

  
</html>
