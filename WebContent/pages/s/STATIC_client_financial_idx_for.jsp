<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Información Financiera
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</HEAD>

<BODY>

<h3 align="center">Formulas de Calculo de Indices Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_financial_idx_for.jsp,STATIC"></h3>
<hr size="4">

<BR>     
 
<TABLE  class="tableinfo" style="width:90%" align=center>
   <TR>
    <TD>
            <TABLE id="headTable" width="100%" cellpading=0 cellspacing="0">
    	  <TR border="1">
                        <TD id="trdark" width=50% ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Liquidez</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-width: thin; border-right-style: solid">Activo Corriente<BR>
                        --------------------<BR>
                        Pasivo Corriente</TD>
                    </TR>
      	  <TR>
                        <TD ALIGN="right" NOWRAP style="border-bottom-style: none; border-left-style: none; border-top-style: none; border-width: thin"><b>Prueba Acida</b> =</TD>
                        <TD ALIGN="center" NOWRAP>Disponibilidades + Inversiones Temporarias + Ctas x Cobrar<BR>
                        ------------------------------------------------------------------------<BR>
                        Pasivo Corriente</TD>
                    </TR>
      	  <TR>
                        <TD id="trdark" ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Periodo Medio Cobranza</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-right-style: solid; border-width: thin">Creditos por Ventas<BR>
                        -------------------------<BR>
                        Ventas / 360</TD>
                    </TR>
      	  
      	  <TR>
                        <TD ALIGN="right" NOWRAP style="border-bottom-style: none; border-left-style: none; border-top-style: none; border-width: thin"><b>Plazo Rotación Inventario</b> =</TD>
                        <TD ALIGN="center" NOWRAP>Bienes de Cambios<BR>
                        ------------------------<BR>
                        Costo Ventas / 360</TD>
                    </TR>
      	  <TR>
                        <TD id="trdark" ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Periodo Medio de Pagos</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-width: thin; border-right-style: solid">Cuentas por Pagar<BR>
                        -----------------------<BR>
                        Costo Ventas / 360</TD>
                    </TR>
      	  <TR>
                        <TD ALIGN="right" NOWRAP style="border-bottom-style: none; border-left-style: none; border-top-style: none; border-width: thin"><b>Endeudamiento</b> =</TD>
                        <TD ALIGN="center" NOWRAP>Total del Pasivo<BR>
                        ----------------------------<BR>
                        Patrimonio - Intangibles</TD>
                    </TR>
      	  <TR>
                        <TD id="trdark" ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Endeudamiento Financiero</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-width: thin; border-right-style: solid">Deudas Financieras<BR>
                        -------------------------<BR>
                        Deudas Totales</TD>
                    </TR>
      	  <TR>
                        <TD ALIGN="right" NOWRAP style="border-bottom-style: none; border-left-style: none; border-top-style: none; border-width: thin"><b>Rendimiento de los Activos</b> =</TD>
                        <TD ALIGN="center" NOWRAP>Utilidad Final<BR>
                        ------------------<BR>
                        Total Activos<b> </b></TD>
                    </TR>
      	  <TR>
                        <TD id="trdark" ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Rendimiento Patrimonio</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-width: thin; border-right-style: solid">Utilidad Final<BR>
                        -------------------------------<BR>
                        Patrimonio - Utilidad Final<b> </b></TD>
                    </TR>
      	  <TR>
                        <TD ALIGN="right" NOWRAP style="border-bottom-style: none; border-left-style: none; border-top-style: none; border-width: thin"><b>Margen Neto Utilidad</b> =</TD>
                        <TD ALIGN="center" NOWRAP>Utilidad Final<BR>
                        ----------------<BR>
                        Ventas<b> </b></TD>
                    </TR>
      	  <TR>
                        <TD id="trdark" ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Margen Operacional</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-right-style: solid; border-width: thin">1 + (Costo de Ventas + Gastos)<BR>
                        --------------------------------------<BR>
                        Ventas<b> </b></TD>
                    </TR>
      	  <TR>
                        <TD ALIGN="right" NOWRAP style="border-bottom-style: none; border-left-style: none; border-top-style: none; border-width: thin"><b>Riesgo/Patrimonio Liquido</b> =</TD>
                        <TD ALIGN="center" NOWRAP>Riesgo<BR>
                        ------------------------------<BR>
                        Patrimonio - Intangibles<b> </b></TD>
                    </TR>
      	  <TR>
                        <TD id="trdark" ALIGN="right" NOWRAP style="border-bottom-style: solid; border-left-style: solid; border-top-style: solid; border-width: thin"><b>Riesgo/Ventas</b> =</TD>
                        <TD id="trdark" ALIGN="center" NOWRAP style="border-bottom-style: solid; border-top-style: solid; border-width: thin; border-right-style: solid">Riesgo<BR>
                        ---------------<BR>
                        Ventas<b> </b></TD>
                    </TR>            
        </TABLE>
            </TD> 
	</TR>
</TABLE 

</BODY>
</HTML>
