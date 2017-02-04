<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
	<head>
 		<title>Agregar Valores Clave BC</title>
 		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-datepicker.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendors/datatables/css/jquery.dataTables.css">
		<!-- Optional theme -->
		
 		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
 		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/jquery.min.js"></script>
 		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script>
 		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap-datepicker.js"></script>

 	</head>

 	<body>
 		<header>
 			<div class="bg-main">
 				<div id="header-nav" class="wrap">
 					<div class="pull-left">
 						<img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" /> 
 					</div>
 					<div class="pull-right" id="picture">
 						<img src="${pageContext.request.contextPath}/img/usuario.png" alt="Logo" /> 
 					</div>
 				</div>
 			</div>
 			<div class="wrap">
 				<p class="txt-oge text-right" >Usuario SI2921 - Javier Huaman</p>
 			</div>
 		</header>

 		<div class="wrap">
  			<div class="pull-left" id="menu">
    			<ul class="nav nav-pills nav-stacked">
  					<li><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Administraci&oacute;n</a></li>
            		<li >
            			<i class="glyphicon glyphicon-triangle-top arrow"></i>
            			<a href="#" class="menu">
            				<span class="glyphicon glyphicon-folder-close"></span>  Sol. de Atenci&oacute;n de Incidente 
            			</a>
            		</li>
            		<li>
            			<a href="${pageContext.request.contextPath}/solicitud/consulta" class="menu">
            				Consulta de Sol. CTI
            			</a>
            		</li>
            		<li ><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Sol. de Servicio</a></li>
            		<li ><a href="#" class="menu"> <span class="glyphicon glyphicon-folder-close"></span> Sol. de Atenci&oacute;n de Req.</a></li>
            		<li class="active"><a href="${pageContext.request.contextPath}/incidente/actualizarbc" style="padding-left: 30px" > <span class="glyphicon glyphicon-folder-close"></span> Actualizar Incidentes BC.</a></li>
				</ul>
  			</div>
  			<div id="content">
    			<div class="container-fluid">
    				<div class="panel panel-default">
  						<div class="panel-heading">
    						<h3 class="panel-title">Agregar Valores Clave BC</h3>
  						</div>
  						<div class="panel-body">
    						<form>
    							<div class="row">
    								<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Tipo Solicitud:</label>
    											<div class="col-sm-7">
      												<form:select path="tiposSolicitud" id="tipoSolicitud" cssClass="form-control" required="true" disabled="true" >
				    									<c:forEach items="${tiposSolicitud}" var="tipo">
				            							  <option 
				            							       <c:if test="${tipo.key eq incidente.idTipoSolicitud}">
				            							         selected="selected"
				            							       </c:if>
				            							  value="${tipo.key}">${tipo.value} </option>
				        								</c:forEach>
													</form:select>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Sistema:</label>
												<div class="col-sm-7">
      												<form:select path="sistemas" id="sistema" cssClass="form-control" required="true" disabled="true" >
				    									<c:forEach items="${sistemas}" var="tipo">
				            							  <option 
				            							       <c:if test="${tipo.key eq incidente.idSistema}">
				            							         selected="selected"
				            							       </c:if>
				            							  value="${tipo.key}">${tipo.value} </option>
				        								</c:forEach>
													</form:select>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Proceso:</label>
    											<div class="col-sm-7">
      												<form:select path="procesos" id="procesos" cssClass="form-control" required="true" disabled="true" >
				    									<c:forEach items="${procesos}" var="tipo">
				            							  <option 
				            							       <c:if test="${tipo.key eq incidente.idProceso}">
				            							         selected="selected"
				            							       </c:if>
				            							  value="${tipo.key}">${tipo.value} </option>
				        								</c:forEach>
													</form:select>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Sub Proceso:</label>
 												<div class="col-sm-7">
      												<form:select path="subProcesos" id="subProcesos" cssClass="form-control" required="true" disabled="true" >
				    									<c:forEach items="${subProcesos}" var="tipo">
				            							  <option 
				            							       <c:if test="${tipo.key eq incidente.idSubproceso}">
				            							         selected="selected"
				            							       </c:if>
				            							  value="${tipo.key}">${tipo.value} </option>
				        								</c:forEach>
													</form:select>
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
							</form><!-- fin form-->
    						<form:form modelAttribute="incidente"  style="margin-top: -49px;" >
    							<div class="row">
    								<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="secuencia" class="col-sm-5 control-label">Secuencia:</label>
    											<div class="col-sm-5">
      												<form:input path="nuSecuencia" id="txtSecuencia" disabled="true" class="form-control" style="" />
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="txtDescripcion" class="col-sm-5 control-label">Descripción:</label>
    											<div class="col-sm-7">
      												<form:input path="txtDescripcion" id="txtDescripcion" style="width:300px;" disabled="true" class="form-control" />
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
    						</form:form><!-- fin form-->
  						</div>
					</div>
					
					<div class="panel panel-default">
  						<div class="panel-body" style="padding-left: 178px;" >
    						<form>
    							<input type="hidden" id="idIncidenteBase" value="${incidente.idIncidenteBase}" />
    							<div class="row">
    								<div class="col-md-3">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-10">
    											<label for="valoresClave" class="col-sm-10 control-label">Valores Clave:</label>
      												<form:select path="valoresClave" id="valoresClave" cssClass="form-control" multiple="multiple" style="width: 200px; height: 250px;" >
    													<form:options items="${valoresClave}" />
													</form:select>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-2">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-5" style="padding-left: 32px;padding-top: 81px;" >
    											<button id="btn_agregarVC" type="button" class="btn btn-primary" style="width: 100px; margin-bottom: 10px;" >Agregar > </button>
    											
    											<button id="btn_quitarVC" type="button" class="btn btn-primary" style="width: 100px;">< Quitar</button>
    											</div>
  											</div>
  										</div>
  									</div>
									<div class="col-md-5">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-8">
    											<label for="txtDescripcion" class="col-sm-12 control-label">Valores Clave por Incidente:</label>
      												<select id="valoresClaveIncidente" class="form-control" multiple="multiple" style="width: 200px; height: 250px;" >
													</select>
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								<div class="row">
								  <div class="pull-right" style="margin-right:10px;">
								    <button id="btn_guardar_valor_clave" type="button" class="btn btn-primary">Guardar</button>
								    <a class="btn btn-primary" href="${pageContext.request.contextPath}/incidente" >Volder</a>
								  </div>
								</div>
    						</form><!-- fin form-->
  						</div>
					</div>

    			</div>
  			</div>
		</div>

		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/vendors/datatables/js/jquery.dataTables.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/moment.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/incidente.js"></script>
 	</body>

 </html>