 /*Data for e-IBS Menu*/

/*WEBMM*/
   DELETE FROM R47CYFILES.WEBMM;
 
	insert into R47CYFILES.webmm values ('WEB010',   'S', 'Clientes', 0, 1);
	insert into R47CYFILES.webmm values ('WEB020',   'S', 'Certificados', 0, 1);
	insert into R47CYFILES.webmm values ('WEB030',   'S', 'Cuentas Corrientes', 0, 1);
    insert into R47CYFILES.webmm values ('WEB035',   'S', 'Chequeras', 0, 1);
	insert into R47CYFILES.webmm values ('WEB040',   'S', 'Cuentas de Ahorro', 0, 1);
    insert into R47CYFILES.webmm values ('WEB045',   'S', 'Tarjetas', 0, 1);
	insert into R47CYFILES.webmm values ('WEB050',   'S', 'Prestamos', 0, 1);
	insert into R47CYFILES.webmm values ('WEB051',   'S', 'Transacciones', 0, 1);
	insert into R47CYFILES.webmm values ('WEB055',   'S', 'Dcto Documentos',0,1);	
    insert into R47CYFILES.webmm values ('WEB056',   'S', 'Tesoreria',0,1);	
    insert into R47CYFILES.webmm values ('WEB057',   'S', 'Boletas Garantia',0,1);	
	insert into R47CYFILES.webmm values ('WEB060',   'S', 'Cartas de Cr&eacute;dito', 0, 1);
	insert into R47CYFILES.webcm values ('WEB061',   'S', 'Tarjetas de Credito', 0, 1);
	insert into R47CYFILES.webcm values ('WEB062',   'S', 'Tarjetas de Debito', 0, 1);
	insert into R47CYFILES.webmm values ('WEB070',   'S', 'Cobranzas', 0, 1);
	insert into R47CYFILES.webmm values ('WEB080',   'S', 'Productos', 0, 1);
	insert into R47CYFILES.webmm values ('WEB090',   'S', 'Lineas de Cr&eacute;dito', 0, 1);
	insert into R47CYFILES.webmm values ('WEB100',   'S', 'Cheques Oficiales', 0, 1);	
	insert into R47CYFILES.webmm values ('WEB105',   'S', 'Act. Fij./Amort.', 0, 1);
	
	insert into R47CYFILES.webmm values ('WEB106',   'S', 'Proceso Camara', 0, 1);
	insert into R47CYFILES.webmm values ('WEB107',   'S', 'Consulta Caja Canje', 0, 1);
	
	insert into R47CYFILES.webmm values ('WEB110',   'S', 'Contabilidad', 0, 1);
    insert into R47CYFILES.webmm values ('WEB115',   'S', 'Evaluacion de Riesgo', 0, 1);
    insert into R47CYFILES.webmm values ('WEB120',   'S', 'Garant&iacute;as', 0, 1);
	insert into R47CYFILES.webmm values ('WEB125',   'S', 'Plataforma', 0, 1);
    insert into R47CYFILES.webmm values ('WEB126',   'S', 'Propuesta', 0, 1);
	insert into R47CYFILES.webmm values ('WEB127',   'S', 'Control Remesas', 0, 1);
	insert into R47CYFILES.webmm values ('WEB130',   'S', 'Consultas', 0, 1);
	insert into R47CYFILES.webmm values ('WEB140',   'S', 'Aprobaciones', 0, 1);
	insert into R47CYFILES.webmm values ('WEB145',   'S', 'Devoluci&oacute;n Cheques', 0, 1);
	insert into R47CYFILES.webmm values ('WEB150',   'S', 'Reportes', 0, 1);
	insert into R47CYFILES.webmm values ('WEB160',   'S', 'Seguridad', 0, 1);
	insert into R47CYFILES.webmm values ('WEB165',   'S', 'Parametros Generales', 0, 1);
	insert into R47CYFILES.webmm values ('WEB166',   'S', 'Parametros Tesoreria', 0, 1);
	insert into R47CYFILES.webmm values ('WEB167',   'S', 'Parametros Prestamos', 0, 1);
	insert into R47CYFILES.webmm values ('WEB170',   'S', 'Miscelaneos', 0, 1);
	insert into R47CYFILES.webmm values ('WEB175',   'S', 'Consulta A&ntilde;o Anterior', 0, 1);
	insert into R47CYFILES.webmm values ('WEB180',   'S', 'Utilitarios', 0, 1);
  	insert into R47CYFILES.webmm values ('WEB210',   'S', 'Transferencias', 0, 1);
  	insert into R47CYFILES.webmm values ('WEB220',   'S', 'Transacciones Financieras', 0, 1);  	
  	
  SELECT * FROM R47CYFILES.webmm ;

/*WEBCM*/
  DELETE FROM R47CYFILES.WEBCM;

 
 insert into R47CYFILES.webcm values ( 'WEB010', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.client.JSESD0080?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB010', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.client.JSESD0080?SCREEN=200', 'main');
 insert into R47CYFILES.webcm values ( 'WEB010', '003', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.client.JSESD0100?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB010', '004', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.client.JSESD0080?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB010', '005', 'S', 'Posici&oacute;n', 'cif.gif', '/servlet/datapro.eibs.client.JSECIF010?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB010', '006', 'S', 'Calificaci&oacute;n', 'new.gif', '/servlet/datapro.eibs.client.JSEDL0521?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB020', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=CD', 'main');
 insert into R47CYFILES.webcm values ( 'WEB020', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=200', 'main');
 insert into R47CYFILES.webcm values ( 'WEB020', '003', 'S', 'Pre-Cancelaci&oacute;n', 'cancel.gif', '/servlet/datapro.eibs.products.JSEDL0130?SCREEN=700', 'main');
 insert into R47CYFILES.webcm values ( 'WEB020', '004', 'S', 'Transacciones', 'transaction.gif', '/servlet/datapro.eibs.products.JSEDL0130?SCREEN=900', 'main');
 insert into R47CYFILES.webcm values ( 'WEB020', '005', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=CD', 'main');
 insert into R47CYFILES.webcm values ( 'WEB020', '006', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEDL0130I?SCREEN=300', 'main'); 
 insert into R47CYFILES.webcm values ( 'WEB020', '007', 'S', 'Estado de Cuentas', 'detail.gif', '/servlet/datapro.eibs.client.JSEDL0300?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB020', '008', 'S', 'Orden No Pago', 'stop.gif', '/servlet/datapro.eibs.products.JSEDD0180?SCREEN=500&FLAG=1', 'main');
 

 insert into R47CYFILES.webcm values ( 'WEB030', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=RT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '003', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=RT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '004', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEDD0000?SCREEN=1300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '005', 'S', 'Estado de Cuentas', 'detail.gif', '/servlet/datapro.eibs.client.JSECIF030?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '006', 'S', 'Orden No Pago', 'stop.gif', '/servlet/datapro.eibs.products.JSEDD0180?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '007', 'S', 'Relaciones', 'rel.gif', '/servlet/datapro.eibs.products.JSEDD0430?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '008', 'S', 'Diferidos', 'holdsuncollected.gif', '/servlet/datapro.eibs.products.JSEDD0300?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '009', 'S', 'Cancelaci&oacute;n', 'erase.gif', '/servlet/datapro.eibs.products.JSEDD1150?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '010', 'S', 'Lavado de Dinero', 'erase.gif', '/servlet/datapro.eibs.products.JSELD0100?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB030', '011', 'S', 'Conformaci&oacute;n Cheques', 'erase.gif', '/servlet/datapro.eibs.products.JSECH1000?SCREEN=1', 'main');



 insert into R47CYFILES.webcm values ( 'WEB035', '001', 'S', 'Consulta', 'new.gif', '/servlet/datapro.eibs.products.JSECH0565?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB035', '002', 'S', 'Solicitud Especial', 'maint.gif', '/servlet/datapro.eibs.products.JSECH0000?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB035', '003', 'S', 'Recepci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSECH0320?SCREEN=1&OPTION=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB035', '004', 'S', 'Venta de Chequera', 'inquiry.gif', '/servlet/datapro.eibs.products.JSECH0320?SCREEN=1&OPTION=2', 'main');
 insert into R47CYFILES.webcm values ( 'WEB035', '005', 'S', 'Aprobaci&oacute;n de Chequera', 'detail.gif', '/servlet/datapro.eibs.products.JSECH0000?SCREEN=402', 'main');
 insert into R47CYFILES.webcm values ( 'WEB035', '006', 'S', 'Ingreso Manual', 'stop.gif', '/servlet/datapro.eibs.products.JSECH0575?SCREEN=100', 'main');

 insert into R47CYFILES.webcm values ( 'WEB040', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=04', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=700', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '003', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=04', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '004', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEDD0000?SCREEN=1500', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '005', 'S', 'Estado de Cuentas', 'detail.gif', '/servlet/datapro.eibs.client.JSECIF030?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '006', 'S', 'Relaciones', 'rel.gif', '/servlet/datapro.eibs.products.JSEDD0430?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '007', 'S', 'Diferidos', 'holdsuncollected.gif', '/servlet/datapro.eibs.products.JSEDD0300?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB040', '008', 'S', 'Cancelaci&oacute;n', 'erase.gif', '/servlet/datapro.eibs.products.JSEDD1150?SCREEN=300', 'main');

insert into R47CYFILES.webcm values ( 'WEB045', '001', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEATM010?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB045', '002', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEATM010A?SCREEN=1', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB050', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=10', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '003', 'S', 'Pago', 'cancel.gif', '/servlet/datapro.eibs.products.JSEDL0950?SCREEN=500', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '004', 'S', 'Transacciones', 'transaction.gif', '/servlet/datapro.eibs.products.JSEDL0152?SCREEN=700', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '005', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=10', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '006', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEDL0160?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '007', 'S', 'Estado de Cuentas', 'detail.gif', '/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '008', 'S', 'Deudas Varias', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=10&GENERIC=U', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '009', 'S', 'Prorrogas', 'report.gif', '/servlet/datapro.eibs.products.JSEDL0910?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB050', '010', 'S', 'Gastos Extras', 'cif.gif', '/servlet/datapro.eibs.products.JSEDL0158?SCREEN=100', 'main');

insert into R47CYFILES.webcm values ( 'WEB051', '001', 'S', 'Nueva', 'new.gif', '/servlet/datapro.eibs.products.JSEPR0000?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB051', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEPR1080?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB051', '003', 'S', 'Aprobacion', 'app.gif', '/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB051', '004', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEPR1060?SCREEN=1', 'main');


 insert into R47CYFILES.webcm values ( 'WEB055', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=10&GENERIC=G', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEDL0800?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '003', 'S', 'Aprobacion Dcto.Doc.', 'app.gif', '/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=10&typCode=G', 'main');

 insert into R47CYFILES.webcm values ( 'WEB055', '004', 'S', 'Aceptante Nuevo/Mant', 'maint.gif', '/servlet/datapro.eibs.products.JSEDL0800?SCREEN=600', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '005', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1400', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '006', 'S', 'Pagos', 'cancel.gif', '/servlet/datapro.eibs.products.JSEDL0825?SCREEN=1&Opt=P', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '007', 'S', 'Aprobacion Pag/Canc', 'app.gif', '/servlet/datapro.eibs.approval.JSEDL0828?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '008', 'S', 'Consulta Pagos', 'inquiry.gif', '/servlet/datapro.eibs.products.JSEDL0825I?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB055', '009', 'S', 'Cancelaci&oacute;n', 'erase.gif', '/servlet/datapro.eibs.products.JSEDL0825?SCREEN=1&Opt=C', 'main');

insert into R47CYFILES.webcm values ( 'WEB056', '001', 'S', 'Dealer Slip', 'new.gif', '/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '002', 'S', 'Back Office', 'cl.gif', '/servlet/datapro.eibs.forex.JSEWD0325BO?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '003', 'S', 'B/O Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.forex.JSEWD0326BO?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '004', 'S', 'FRA Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.forex.JSEWD0332M', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '005', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=300', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '006', 'S', 'Aprobacion ', 'app.gif', '/servlet/datapro.eibs.forex.JSEDL0140T?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '007', 'S', 'Transacciones', 'transaction.gif', '/servlet/datapro.eibs.forex.JSEDL0130TR?SCREEN=900', 'main');
insert into R47CYFILES.webcm values ( 'WEB056', '008', 'S', 'Cuentas de Tesoreria', 'transaction.gif', '/servlet/datapro.eibs.forex.JSEGL0590?SCREEN=1', 'main');

 insert into R47CYFILES.webcm values ( 'WEB057', '001', 'S', 'Nueva', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=43', 'main');
 insert into R47CYFILES.webcm values ( 'WEB057', '002', 'S', 'Mantenimiento', 'cl.gif', '/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=200', 'main');
 insert into R47CYFILES.webcm values ( 'WEB057', '003', 'S', 'Consulta', 'maint.gif', '/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB057', '004', 'S', 'Aprobacion', 'maint.gif', '/servlet/datapro.eibs.bolgaran.JSELC5250?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB057', '005', 'S', 'Liquidacion', 'inquiry.gif', '/servlet/datapro.eibs.bolgaran.JSELC5560?SCREEN=1', 'main');
 
insert into R47CYFILES.webcm values ( 'WEB060', '001', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.trade.JSELC0450?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB060', '002', 'S', 'Estado de Cuentas', 'detail.gif', '/servlet/datapro.eibs.trade.JSESD0817?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB061', '001', 'S', 'Nuevo Cupo Credito', 'new.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=94', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB061', '002', 'S', 'Mant. Cupo Credito', 'maint.gif', '/servlet/datapro.eibs.products.JSECC0010?SCREEN=200', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB061', '003', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSECC0010I?SCREEN=100&Type=C', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB061', '004', 'S', 'Aprobacion', 'app.gif', '/servlet/datapro.eibs.products.JSECC0140?SCREEN=1&appCode=94', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB061', '005', 'S', 'Adicionar Tarjeta', 'maint.gif', '/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&Type=C', 'main',' ');

 insert into R47CYFILES.webcm values ( 'WEB062', '005', 'S', 'Adicionar Tarjeta', 'maint.gif', '/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&Type=D', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB062', '006', 'S', 'Asignar Cuentas', 'maint.gif', '/servlet/datapro.eibs.products.JSECC0080?SCREEN=700', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB062', '003', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSECC0060I?SCREEN=500&Type=D', 'main',' ');
 insert into R47CYFILES.webcm values ( 'WEB062', '005', 'S', 'Cambio de Status', 'maint.gif', '/servlet/datapro.eibs.products.JSECC0060?SCREEN=500&Type=D&OPT=4', 'main',' ');
 
 insert into R47CYFILES.webcm values ( 'WEB070', '001', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.trade.JSEDC0100?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB070', '002', 'S', 'Estado de Cuentas', 'detail.gif', '/servlet/datapro.eibs.trade.JSESD0817C?SCREEN=100', 'main');

 insert into R47CYFILES.webcm values ( 'WEB080', '001', 'S', 'Oferta', 'offer.gif', '/servlet/datapro.eibs.products.JSESD0711?TYPE=ALL', 'main');
 insert into R47CYFILES.webcm values ( 'WEB080', '002', 'S', 'Aplicaciones', 'report.gif', '/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=5', 'main');
 insert into R47CYFILES.webcm values ( 'WEB080', '003', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSESD0700?TYPE=ALL', 'main');

 insert into R47CYFILES.webcm values ( 'WEB090', '001', 'S', 'Nuevo/Mant', 'maint.gif', '/servlet/datapro.eibs.credit.JSELN0000?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB090', '002', 'S', 'Por Cliente', 'customer.gif', '/servlet/datapro.eibs.credit.JSELN0110?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB090', '003', 'S', 'Por Tipo', 'type.gif', '/servlet/datapro.eibs.credit.JSELN0110?SCREEN=500', 'main');
 insert into R47CYFILES.webcm values ( 'WEB090', '004', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.credit.JSELN0040?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB090', '005', 'S', 'Incidencia', 'detail.gif', '/servlet/datapro.eibs.forex.JSEWD0324?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB090', '006', 'S', 'Limites Financieros', 'trans.gif', '/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=900', 'main');


 insert into R47CYFILES.webcm values ( 'WEB100', '001', 'S', 'Emisi&oacute;n', 'new.gif', '/servlet/datapro.eibs.products.JSEOF0115?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB100', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.products.JSEOF0115?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB100', '003', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEOF0115A?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB100', '004', 'S', 'Consulta', 'inquiry.gif', '/servlet/datapro.eibs.products.JSETL0510?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB100', '005', 'S', 'Impresi&oacute;n', 'detail.gif', '/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB100', '006', 'S', 'Re-Impresi&oacute;n', 'report.gif', '/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB100', '007', 'S', 'Orden No Pago', 'stop.gif', '/servlet/datapro.eibs.products.JSEDD0180?SCREEN=300&FLAG=2', 'main');

 insert into R47CYFILES.webcm values ( 'WEB105', '001', 'S', 'Nuevo', 'new.gif', '/servlet/datapro.eibs.amort.JSEFIX000?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB105', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.amort.JSEFIX000?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB105', '003', 'S', 'Ubicaciones', 'maint.gif', '/servlet/datapro.eibs.amort.JSEFIX050?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB105', '004', 'S', 'Clases Activos', 'maint.gif', '/servlet/datapro.eibs.amort.JSEFIX060?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB105', '005', 'S', 'Referencias Cruzadas', 'maint.gif', '/servlet/datapro.eibs.amort.JSEFIX030?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB105', '006', 'S', 'Parametros', 'maint.gif', '/servlet/datapro.eibs.amort.JSEFIX100?SCREEN=100', 'main');


 insert into R47CYFILES.webcm values ( 'WEB106', '001', 'S', 'Cierre/Reapertura Cam.', 'new.gif', '/servlet/datapro.eibs.cleaning.JSEDD0933?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '002', 'S', 'Carga Cam. Saliente', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSEDD0932?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '003', 'S', 'Cuadre Camara Saliente', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCCJSAL1P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '004', 'S', 'Aplicacion Cam. Saliente', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSEDD0726?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '005', 'S', 'Carga Cam.Entrante', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSEDD0931?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '006', 'S', 'Cuadre Camara Entrante', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCCJENT1P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '007', 'S', 'Aplicacion Cam.Entrante', 'new.gif', '/servlet/datapro.eibs.cleaning.JSEDD0930?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '008', 'S', 'Cuadro Contable Protestos', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCCOPR02P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '009', 'S', 'Planilla Protestos', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCCOPR03P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '010', 'S', 'Protestados x Banco', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCJE26P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '011', 'S', 'Cuadro Contable Devueltos', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCJE11P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '012', 'S', 'Detalle Devueltos X Suc.', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCJE03P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '013', 'S', 'Detalle Devueltos X Bco.', 'new.gif', '/servlet/datapro.eibs.cleaning.JSELS0000?ACTION=S&PROG=CCJE04P&COLA=QBATCH&TRAB=PRDJOBDPBT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '014', 'S', 'Rechazos Otros Motivos', 'maint.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=UI', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '015', 'S', 'Liberacion Diferidos', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSEDD0101?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '016', 'S', 'Protesto de Cheques', 'maint.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=RP', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '017', 'S', 'Devolucion de Canje', 'maint.gif', '/servlet/datapro.eibs.products.JSEDD1200?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB106', '018', 'S', 'Lib/Dif. Canje Interno', 'maint.gif', '/servlet/datapro.eibs.cleaning.JSEDD0102?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB107', '001', 'S', 'Resoluc. Sobregiros', 'new.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=NSF', 'main');
 insert into R47CYFILES.webcm values ( 'WEB107', '002', 'S', 'Sobregiros Por Rango', 'maint.gif', '/servlet/datapro.eibs.approval.JSEDD0934?SCREEN=1&opt=RP', 'main');
 insert into R47CYFILES.webcm values ( 'WEB107', '003', 'S', 'Chqs Procesados x Cta', 'maint.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=PI', 'main');
 insert into R47CYFILES.webcm values ( 'WEB107', '004', 'S', 'Control de Protestos', 'maint.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=RR', 'main');
 insert into R47CYFILES.webcm values ( 'WEB107', '005', 'S', 'Protestos/Aclaraciones', 'maint.gif', '/servlet/datapro.eibs.products.JSEDD0190?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB110', '001', 'S', 'Plan Contable', 'detail.gif', '/servlet/datapro.eibs.products.JSEGL0340?SCREEN=1&OPT=M', 'main');
 insert into R47CYFILES.webcm values ( 'WEB110', '002', 'S', 'Cancelar Plan', 'erase.gif', '/servlet/datapro.eibs.products.JSEGL0340?SCREEN=1&OPT=C', 'main');
 insert into R47CYFILES.webcm values ( 'WEB110', '003', 'S', 'Lotes Contables', 'trans.gif', '/servlet/datapro.eibs.products.JSEGL0035?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB110', '004', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.products.JSEGL0012?SCREEN=1', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB115', '001', 'S', 'Calificaci&oacute;n', 'new.gif', '/servlet/datapro.eibs.client.JSEDL0521?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB115', '002', 'S', 'Formatos', 'rel.gif', '/servlet/datapro.eibs.client.JSEDP0035?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB115', '003', 'S', 'Info. Financiera', 'detail.gif', '/servlet/datapro.eibs.client.JSEDP0030?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB115', '004', 'S', 'Evaluacion Riesgo', 'detail.gif', '/servlet/datapro.eibs.client.JSECIF200?SCREEN=100', 'main');

 insert into R47CYFILES.webcm values ( 'WEB120', '001', 'S', 'Nuevo', 'maint.gif', '/servlet/datapro.eibs.client.JSERA0011?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB120', '002', 'S', 'Mantenimiento', 'maint.gif', '/servlet/datapro.eibs.client.JSERA0011?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB120', '003', 'S', 'Aprobaci&oacute;n', 'app.gif', '/servlet/datapro.eibs.client.JSERA0080?SCREEN=5', 'main');
 insert into R47CYFILES.webcm values ( 'WEB120', '004', 'S', 'Consulta', 'collateral.gif', '/servlet/datapro.eibs.client.JSERA0100?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB120', '005', 'S', 'Asignaci&oacute;n', 'collateral.gif', '/servlet/datapro.eibs.client.JSERA0005?SCREEN=100', 'main');

 insert into R47CYFILES.webcm values ( 'WEB125', '001', 'S', 'Ingreso', 'new.gif', '/servlet/datapro.eibs.platform.JSEPV0005?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB125', '002', 'S', 'Venta', 'sale.gif', '/servlet/datapro.eibs.platform.JSEPV0010?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB125', '003', 'S', 'Aprobacion', 'app.gif', '/servlet/datapro.eibs.platform.JSEPV0140?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB125', '004', 'S', 'Curso', 'curse.gif', '/servlet/datapro.eibs.platform.JSEPV0020?SCREEN=100', 'main');

 insert into R47CYFILES.webcm values ( 'WEB126', '001', 'S', 'Ingreso Cliente', 'new.gif', '/servlet/datapro.eibs.credprop.JSEPV5000?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB126', '002', 'S', 'Ingreso Propuesta', 'new.gif', '/servlet/datapro.eibs.credprop.JSEPV5000?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB126', '003', 'S', 'Verificacion', 'new.gif', '/servlet/datapro.eibs.credprop.JSEPV5010?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB127', '001', 'S', 'Nuevo/Manten', 'new.gif', '/servlet/datapro.eibs.moneytransfer.JSEFE0500?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB127', '002', 'S', 'Envio', 'new.gif', '/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB127', '003', 'S', 'Recepcion', 'new.gif', '/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=200', 'main');
 insert into R47CYFILES.webcm values ( 'WEB127', '004', 'S', 'Aprobacion', 'new.gif', '/servlet/datapro.eibs.moneytransfer.JSEFE0510?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB127', '005', 'S', 'Consulta', 'new.gif', '/servlet/datapro.eibs.moneytransfer.JSEFE0520?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB130', '001', 'S', 'Posici&oacute;n', 'cif.gif', '/servlet/datapro.eibs.client.JSECIF010?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '002', 'S', 'Clientes', 'customer.gif', '/servlet/datapro.eibs.client.JSESD0080?SCREEN=300', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '003', 'S', 'Cuentas', 'account.gif', '/servlet/datapro.eibs.client.JSECIF010?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '004', 'S', 'Garant&iacute;as No-Dep&oacute;sito', 'collateral.gif', '/servlet/datapro.eibs.client.JSERA0100?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '005', 'S', 'Estado Cta Contable', 'detail.gif', '/servlet/datapro.eibs.client.JSEGL0420?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '006', 'S', 'Documentos Varios', 'doc_var.gif', '/servlet/datapro.eibs.products.JSETL0510?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '007', 'S', 'Pagos y Recibos', 'transfer.gif', '/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '008', 'S', 'Documentaci&oacute;n', 'doc.gif', '/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '009', 'S', 'Tasas Int. x Plazo/Monto', 'reports.gif', '/servlet/datapro.eibs.params.JSECN0000?SCREEN=500', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '010', 'S', 'Tasas de Pizarra', 'reports.gif', '/servlet/datapro.eibs.params.JSECN0000?SCREEN=700', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '011', 'S', 'Cuentas de Tesoreria', 'transaction.gif', '/servlet/datapro.eibs.forex.JSEGL0590?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB130', '012', 'S', 'Cuentas Sobregiradas', 'transaction.gif', '/servlet/datapro.eibs.client.JSESD0300?SCREEN=1', 'main');

 insert into R47CYFILES.webcm values ( 'WEB140', '001', 'S', 'Todo', 'app.gif', '/servlet/datapro.eibs.approval.JSESD0140?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '002', 'S', 'Cliente', 'customer.gif', '/servlet/datapro.eibs.client.JSESD0100?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '003', 'S', 'Certificado', 'cd.gif', '/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=CD', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '004', 'S', 'Cuenta Corriente', 'retail.gif', '/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=RT', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '005', 'S', 'Cuenta Ahorro', 'saving.gif', '/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=04', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '006', 'S', 'Prestamo', 'loan.gif', '/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=10', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '007', 'S', 'Linea de Cr&eacute;dito', 'cl.gif', '/servlet/datapro.eibs.credit.JSELN0040?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '008', 'S', 'Lotes Contables', 'batch.gif', '/servlet/datapro.eibs.products.JSEGL0012?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB140', '009', 'S', 'Garant&iacute;as', 'Collaprvl.gif', '/servlet/datapro.eibs.client.JSERA0080?SCREEN=5', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB145', '001', 'S', 'Por Sobregiros', 'approvalcks.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=NSF', 'main');
 insert into R47CYFILES.webcm values ( 'WEB145', '002', 'S', 'Por L&iacute;mite', 'approvalcks5000.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=LI', 'main');
 insert into R47CYFILES.webcm values ( 'WEB145', '003', 'S', 'Otros Conceptos', 'unposteditems.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=UI', 'main');
 insert into R47CYFILES.webcm values ( 'WEB145', '004', 'S', 'Cheques Procesados', 'posteditems.gif', '/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=1&opt=PI', 'main');

 insert into R47CYFILES.webcm values ( 'WEB150', '001', 'S', 'FDD', 'eodreports.gif', '/servlet/datapro.eibs.tools.JSEODPDF?SCREEN=3', 'main');

 insert into R47CYFILES.webcm values ( 'WEB160', '001', 'S', 'Reportes FDD', 'reports.gif', '/servlet/datapro.eibs.tools.JSEODPDF?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB160', '002', 'S', 'Grupo/Usuarios', 'reports.gif', '/eIBS_R04M07/servlet/com.datapro.eibs.security.servlet.JSGroupProfile?SCREEN=1', 'main');
 
insert into R47CYFILES.webcm values ( 'WEB165', '001', 'S', 'Parametros de Control', 'maint.gif', '/servlet/datapro.eibs.params.JSESD0005M?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '002', 'S', 'Maestro de Agencias', 'maint.gif', '/servlet/datapro.eibs.params.JSESD0590?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '003', 'S', 'Retenciones e Impuestos', 'maint.gif', '/servlet/datapro.eibs.params.JSESD0009?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '004', 'S', 'Tablas de Documentos', 'reports.gif', '/servlet/datapro.eibs.params.JSEDI010?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '005', 'S', 'Lavado de Dinero', 'maint.gif', '/servlet/datapro.eibs.params.JSELDO500?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '006', 'S', 'Control de Chequeras', 'maint.gif', '/servlet/datapro.eibs.params.JSECH0110?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '007', 'S', 'Cheques Oficiales', 'maint.gif', '/servlet/datapro.eibs.params.JSEOF0000?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '008', 'S', 'Limites de Clientes', 'new.gif', '/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=1100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '009', 'S', 'Codigos de Referencia', 'maint.gif', '/servlet/datapro.eibs.params.JSESD0030?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '010', 'S', 'Cuentas de Contrapartida', 'maint.gif', '/servlet/datapro.eibs.params.JSEGL0348?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '011', 'S', 'Agentes de Bolsa/Seguros', 'maint.gif', '/servlet/datapro.eibs.params.JSEFE0090?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '012', 'S', 'Generacion(Cuentas Banco/Agencia)', 'maint.gif', '/servlet/datapro.eibs.params.JSEIB0000?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '013', 'S', 'Mantenimiento(Cuentas Banco/Agencia)', 'maint.gif', '/servlet/datapro.eibs.params.JSEIB0000?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '014', 'S', 'Act. Fijos/Amort', 'maint.gif', '/servlet/datapro.eibs.amort.JSEFIX100?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB165', '014', 'S', 'Referencias Tarjetas de Credito', 'maint.gif', '/servlet/datapro.eibs.params.JSECC0030?SCREEN=100', 'main');

insert into R47CYFILES.webcm values ( 'WEB166', '001', 'S', 'Referencias Monedas', 'maint.gif', '/servlet/datapro.eibs.params.JSEGL0360?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '002', 'S', 'Tasas,Cargos P. Servicios', 'reports.gif', '/servlet/datapro.eibs.params.JSESD0205?SCREEN=1000', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '003', 'S', 'Tasas Int. x Plazo/Monto', 'reports.gif', '/servlet/datapro.eibs.params.JSECN0000?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '004', 'S', 'Tasas Flotantes', 'reports.gif', '/servlet/datapro.eibs.params.JSEDL0110F?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '005', 'S', 'Tasas Lideres', 'reports.gif', '/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=1', 'main'); 
insert into R47CYFILES.webcm values ( 'WEB166', '006', 'S', 'Ap.Tasa Flot./Lider', 'reports.gif', '/servlet/datapro.eibs.params.JSEDL1110?SCREEN=1', 'main');  
insert into R47CYFILES.webcm values ( 'WEB166', '007', 'S', 'Tasas de Pizarra', 'reports.gif', '/servlet/datapro.eibs.params.JSECN0000?SCREEN=300', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '008', 'S', 'Tasas de Overnight', 'reports.gif', '/servlet/datapro.eibs.params.JSEDD0505?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '009', 'S', 'Tasas de Cambio', 'reports.gif', '/servlet/datapro.eibs.params.JSEFE0220?SCREEN=1', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '010', 'S', 'Instrucciones del Banco', 'maint.gif', '/servlet/datapro.eibs.params.JSESD0005?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB166', '011', 'S', 'Control Moneda Extranjera', 'maint.gif', '/servlet/datapro.eibs.params.JSEFE0200?SCREEN=100', 'main');

insert into R47CYFILES.webcm values ( 'WEB167', '001', 'S', 'Tablas de Previsiones', 'maint.gif', '/servlet/datapro.eibs.params.JSEDL0522?SCREEN=100', 'main');
insert into R47CYFILES.webcm values ( 'WEB167', '002', 'S', 'Tablas  Prestamos', 'reports.gif', '/servlet/datapro.eibs.params.JSEDL0510?SCREEN=1000', 'main');

 insert into R47CYFILES.webcm values ( 'WEB170', '001', 'S', 'Excepciones', 'excep.gif', '/servlet/datapro.eibs.misc.JSELD0010?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB170', '002', 'S', 'Cierre Masivo Cuentas', 'password.gif', '/servlet/datapro.eibs.misc.JSEDD1160?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB170', '003', 'S', 'Aprobacion Cierre Masivo ', 'password.gif', '/servlet/datapro.eibs.misc.JSEDD1170?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB170', '004', 'S', 'Formularios', 'report.gif', '/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB170', '005', 'S', 'Cambio Clave', 'password.gif', '/servlet/datapro.eibs.menu.JSESS0030?SCREEN=5', 'main');


 insert into R47CYFILES.webcm values ( 'WEB175', '001', 'S', 'Posicion', 'cif.gif', '/servlet/datapro.eibs.client.JSECIF010?SCREEN=1&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '002', 'S', 'Cuentas', 'account.gif', '/servlet/datapro.eibs.client.JSECIF010?SCREEN=100&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '003', 'S', 'Colaterales', 'collateral.gif', '/servlet/datapro.eibs.client.JSERA0100?SCREEN=100&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '004', 'S', 'Cuentas Contables', 'detail.gif', '/servlet/datapro.eibs.client.JSEGL0420?SCREEN=100&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '005', 'S', 'Transferencias', 'transfer.gif', '/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=1&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '006', 'S', 'Transacciones', 'doc.gif', '/servlet/datapro.eibs.products.JSEPR1060?SCREEN=1&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '007', 'S', 'Cheques Oficiales', 'doc.gif', '/servlet/datapro.eibs.products.JSETL0510?SCREEN=300&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '008', 'S', 'Lineas de Credito por Clientes', 'doc.gif', '/servlet/datapro.eibs.credit.JSELN0110?SCREEN=300&PY=true', 'main');
 insert into R47CYFILES.webcm values ( 'WEB175', '009', 'S', 'Lineas de Credito por Tipo', 'doc.gif', '/servlet/datapro.eibs.credit.JSELN0110?SCREEN=500&PY=true', 'main');

 insert into R47CYFILES.webcm values ( 'WEB180', '001', 'S', 'Nuevo eIBSForm', 'new.gif', '/servlet/datapro.eibs.tools.JSNewTemplate', 'main');
 insert into R47CYFILES.webcm values ( 'WEB180', '002', 'S', 'Editar eIBSForm', 'maint.gif', '/servlet/datapro.eibs.tools.JSEditTemplate', 'main');
 insert into R47CYFILES.webcm values ( 'WEB180', '003', 'S', 'Upload eIBSImage', 'maint.gif', '/servlet/datapro.eibs.tools.JSUpload', 'main');
 insert into R47CYFILES.webcm values ( 'WEB180', '004', 'S', 'Downloads', 'download.gif', '/servlet/datapro.eibs.tools.JSDownload', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB210', '001', 'S', 'Orden de Pago', 'new.gif', '/servlet/datapro.eibs.transfer.JSEPR2000?SCREEN=100', 'main');
 insert into R47CYFILES.webcm values ( 'WEB210', '002', 'S', 'Consulta Cta. Credito', 'maint.gif', '/servlet/datapro.eibs.transfer.JSEPR2000?SCREEN=500', 'main');
 insert into R47CYFILES.webcm values ( 'WEB210', '003', 'S', 'Aprobacion', 'app.gif', '/servlet/datapro.eibs.transfer.JSEPR2010?SCREEN=100', 'main');
 
 insert into R47CYFILES.webcm values ( 'WEB220', '001', 'S', 'Nueva', 'app.gif', '/servlet/datapro.eibs.products.JSEPR0000?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB220', '002', 'S', 'Mantenimiento', 'app.gif', '/servlet/datapro.eibs.products.JSEPR1080?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB220', '003', 'S', 'Consulta', 'app.gif', '/servlet/datapro.eibs.products.JSEPR1060?SCREEN=1', 'main');  
 insert into R47CYFILES.webcm values ( 'WEB220', '004', 'S', 'Aprobación', 'app.gif', '/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1', 'main');
 insert into R47CYFILES.webcm values ( 'WEB220', '005', 'S', 'Autorización Finanzas', 'app.gif', '/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&approv=A', 'main');  
 insert into R47CYFILES.webcm values ( 'WEB220', '006', 'S', 'Liberación de Transacciones', 'app.gif', '/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&approv=B', 'main');  
 insert into R47CYFILES.webcm values ( 'WEB220', '007', 'S', 'Formato Libre Swift', 'app.gif', '/servlet/datapro.eibs.forex.JSESWF010?SCREEN=5', 'main'); 
 insert into R47CYFILES.webcm values ( 'WEB220', '008', 'S', 'Consulta Formato Libre Swift', 'app.gif', '/servlet/datapro.eibs.forex.JSESWF0200?SCREEN=100', 'main'); 
 insert into R47CYFILES.webcm values ( 'WEB220', '009', 'S', 'Aprobación Formato Libre Swift', 'app.gif', '/servlet/datapro.eibs.forex.JSESWF010?SCREEN=6', 'main'); 
 insert into R47CYFILES.webcm values ( 'WEB220', '010', 'S', 'Aprobación Swift', 'app.gif', '/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=1', 'main');  
 insert into R47CYFILES.webcm values ( 'WEB220', '011', 'S', 'Tabla de Transacciones', 'app.gif', '/servlet/datapro.eibs.approval.JSEPR0300?SCREEN=1', 'main');  

select * from R47CYFILES.webcm ;
