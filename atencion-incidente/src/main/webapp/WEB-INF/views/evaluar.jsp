<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
	<head>
 		<title>Evaluar Solicitud</title>
 		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="/atencion-incidente/vendors/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="/atencion-incidente/vendors/bootstrap/css/bootstrap-datepicker.css" />
		
 		<link rel="stylesheet" href="/atencion-incidente/resources/css/main.css" />
 		<script type="text/javascript" src="/atencion-incidente/vendors/js/jquery.min.js"></script>
 		<script src="/atencion-incidente/vendors/bootstrap/js/bootstrap.min.js"></script>
 		<script src="/atencion-incidente/vendors/bootstrap/js/bootstrap-datepicker.js"></script>
	
 	</head>

 	<body>
 		<header>
 			<div class="bg-main">
 				<div id="header-nav" class="wrap">
 					<div class="pull-left">
 						<img src="/atencion-incidente/img/logo.png" alt="Logo" /> 
 					</div>
 					<div class="pull-right" id="picture">
 						<img src="/atencion-incidente/img/usuario.png" alt="Logo" /> 
 					</div>
 				</div>
 			</div>
 			<div class="wrap">
 				<p class="txt-oge text-right" >Usuario ${analistaName}</p>
 			</div>
 		</header>

 		<div class="wrap">
  			<div class="pull-left" id="menu">
    			<ul class="nav nav-pills nav-stacked">
  					<li><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Administracion</a></li>
            		<li >
            			<i class="glyphicon glyphicon-triangle-top arrow"></i>
            			<a href="#" class="menu">
            				<span class="glyphicon glyphicon-folder-close"></span>  Sol. de Atencion de Incidente 
            			</a>
            		</li>
            		<li class="active">
            			<a href="/atencion-incidente/solicitud/consulta" style="padding-left: 30px">
            				Consulta de Sol. CTI
            			</a>
            		</li>
            		<li ><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Sol. de Servicio</a></li>
            		<li ><a href="#" class="menu"> <span class="glyphicon glyphicon-folder-close"></span> Sol. de Atencion de Req.</a></li>
				</ul>
  			</div>
  			<div id="content">
    			<div class="container-fluid">

            <h3>Evaluar Solicitud de Atencion de Incidente</h3>
			
            <div class="row">
              <div class="col-md-6">
				<form:form  modelAttribute="solicitante">
    				    <div class="panel panel-default">
  						    <div class="panel-heading">
    						    <h3 class="panel-title">Datos del Solicitante</h3>
  						    </div><!-- panel-heading -->
  						    <div class="panel-body" style="min-height: 282px;">
									
    							 <div class="row">
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="matricula">Matricula:</label>
                          <form:input path="matricula" disabled="true" class="form-control"/>
                        </div>
                      </div>
                      <div class="col-md-8">
                        <div class="form-group">
                          <label for="nomApe">Nombres y Apellidos:</label>
                          <form:input path="nombresApellidos" disabled="true" class="form-control"/>
                        </div>
                      </div>
                    </div><!-- row -->

                    <div class="row">
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="area">Area:</label>
                          <form:input path="txtArea" disabled="true" class="form-control"/>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="cargo">Cargo:</label>
                           <form:input path="txtCargo" disabled="true" class="form-control"/>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="anexo">Anexo:</label>
                          <form:input path="anexo" disabled="true" class="form-control"/>
                        </div>
                      </div>
                    </div><!-- row -->

                    <div class="row">
                      <div class="col-md-8">
                        <div class="form-group">
                          <label for="agencia">Agencia:</label>
                          <form:input path="txtAgencia" disabled="true" class="form-control"/>
                        </div>
                      </div>
                    </div><!-- row -->

							   </div><!-- panel-body -->
						    </div><!-- panel-default -->
						    
				</form:form>

              </div><!-- col-md-6 -->

              <div class="col-md-6">

                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h3 class="panel-title">Datos de Solicitud</h3>
                  </div><!-- panel-heading -->
                  
             
                  <input type="hidden" value="${idSolicitud}" id="id_solicitud" name="id_solicitud" class="form-control">
                 <div class="panel-body">
                 	<div class="row">
                 	  <div class="col-md-8"> 
                        <div class="form-horizontal">
                          <div class="form-group">
                            <label for="nrocti" class="col-sm-4 control-label">Nro Cti:</label>
                            <div class="col-sm-8">
                              <input type="text" id="nroCti" class="form-control" value="${cti}" disabled="true"/>
                               <input type="hidden" name="nroCti"  class="form-control" value="${cti}" readonly/>
                            </div>
                          </div>
                        </div>
                      </div>
                 	</div>
                 
                   	<div class="row">
                      <div class="col-md-8">
                        <div class="form-horizontal">
                          <div class="form-group">
                            <label for="tipoSolicitud" class="col-sm-4 control-label">Tipo Solicitud:</label>
                            <div class="col-sm-8">
                            	
                       			<form:select path="tipoSolicitud" id="tipoSolicitud" name="tipoSolicitud" cssClass="form-control" disabled="true" >
    									<c:forEach items="${tipoSolicitud}" var="tipo">
            							  <option 
            							       <c:if test="${tipo.key eq idTipoSolicitud}">
            							         selected="selected"
            							       </c:if>
            							  value="${tipo.key}">${tipo.value} </option>
        								</c:forEach>
								</form:select>
                            	
                            	
                            </div>
                          </div>
                        </div>
                      </div>
                      
                    </div><!-- row -->

                     <div class="row">
                      <div class="col-md-8">
                        <div class="form-horizontal">
                          <div class="form-group">
                            <label for="sistema" class="col-sm-4 control-label">Sistema:</label>
                            <div class="col-sm-8">
                               <form:select path="sistemas" id="sistema" name="sistema" cssClass="form-control" disabled="true">
    									<c:forEach items="${sistemas}" var="sis">
            							  <option 
            							       <c:if test="${sis.key eq idSistema}">
            							         selected="selected"
            							       </c:if>
            							  value="${sis.key}">${sis.value} </option>
        								</c:forEach>
								</form:select>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div><!-- row -->

                
                     <div class="row">
                      <div class="col-md-8">
                        <div class="form-horizontal">
                          <div class="form-group">
                            <label for="proceso" class="col-sm-4 control-label">Proceso:</label>
                            <div class="col-sm-8">
                             
                              <form:select path="procesos" id="proceso" name="proceso" cssClass="form-control" disabled="true">
    									<c:forEach items="${procesos}" var="proceso">
            							  <option 
            							       <c:if test="${proceso.key eq idProceso}">
            							         selected="selected"
            							       </c:if>
            							  value="${proceso.key}">${proceso.value} </option>
        								</c:forEach>
								</form:select>
                              
                            </div>
                          </div>
                        </div>
                      </div>
                    </div><!-- row -->

                    <div class="row">
                      <div class="col-md-8">
                        <div class="form-horizontal">
                          <div class="form-group">
                            <label for="subProceso" class="col-sm-4 control-label">SubProceso:</label>
                            <div class="col-sm-8">
                                
                              <form:select path="subProcesos" id="subProceso" name="subProceso" cssClass="form-control" disabled="true">
    									<c:forEach items="${subProcesos}" var="subProceso">
            							  <option 
            							       <c:if test="${subProceso.key eq idProceso}">
            							         selected="selected"
            							       </c:if>
            							  value="${subProceso.key}">${subProceso.value} </option>
        								</c:forEach>
								</form:select>
                              
                            </div>
                          </div>
                        </div>
                      </div>
                    </div><!-- row -->

                    <div class="row">
                      <div class="col-md-8">
                        <div class="form-horizontal">
                          <div class="form-group">
                            <label for="nroUsAfec" class="col-sm-6 control-label">Nro. Usuarios Afect. :</label>
                            <div class="col-sm-6">
                                     
							   <form:select path="nroUsAfec" id="nroUsAfec" name="nroUsAfec" cssClass="form-control" disabled="true">
    									<c:forEach items="${nroUsAfec}" var="usAfec">
            							  <option 
            							       <c:if test="${usAfec.key eq idNroUsAfec}">
            							         selected="selected"
            							       </c:if>
            							  value="${usAfec.key}">${usAfec.value} </option>
        								</c:forEach>
								</form:select>
							  
                            </div>
                          </div>
                        </div>
                      </div>
                    </div><!-- row -->

                </div><!-- panel-body -->
                  

                </div><!-- panel-default -->

              </div><!-- col-md-6 -->

            </div><!-- row principal-->

             <div class="row" id="view_tipo_solicitud">   

                <div class="col-md-12 panel-dinamico ">
  
                 	  <input type="hidden" value="${idSolicitud}"  name="idSolicitud" class="form-control">
                 	  <input type="hidden" value="${idNroUsAfec}"  class="usAfectados" name="usAfectados" class="form-control">
                       
                  <div  class="panel panel-default tipo1 tipo2" 
                  
                    
                     <c:if test="${idTipoSolicitud eq 3}">
            			style="display: none"
            		 </c:if>
            		 
                  >
                    <div class="panel-heading">
                      <h3 class="panel-title">Detalle Solicitud</h3>
                    </div><!-- panel-heading -->
                    <div class="panel-body">

                      <div class="row">

                        <input type="hidden" id="asunto" name="asunto" value="${asunto}" class="form-control" disabled="true">

                        <div class="col-md-4"> 
                          <div class="form-group">
                            <div class="col-sm-8">
                              <div class="checkbox">
                                  <label>
                                    <input type="checkbox" name="afectaIdi" id="afectaIdi" disabled="true" value="${afectaIdi}" 
                                    <c:if test="${afectaIdi eq 1}">
            									checked
            		 					</c:if>> Afecta IDI
                                  </label>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div><!-- row -->

                      <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                              <label for="descripcion" class="control-label">Descripcion:</label>
                              <textarea id="descripcion" name="descripcion" class="form-control" rows="8" disabled="true">${description}</textarea>
                            </div>
                        </div>
                        <div class="col-md-6"> 
                            <div class="form-horizontal" style="margin-top: 20px;">
                              <div class="form-group" 
                              	 <c:if test="${idTipoSolicitud eq 2}">
            						style="display: none"
            		 			</c:if>
                              >
                                  <input type="hidden" id="error" name ="error" value="${error}" class="form-control" disabled="true">
                              </div>
                              <div class="form-group" 
                              	<c:if test="${idTipoSolicitud eq 1}">
            						style="display: none"
            		 			</c:if>
                              >
                                <label for="datosModif" class="col-sm-4 control-label">Datos a Modificar:</label>
                                <div class="col-sm-8">
                                  <input type="text" id="datosModif" name="datosModif" value="${datosModif}" class="form-control" disabled="true">
                                </div>
                              </div>
                            </div>
                            <table class="table table-bordered">
                              <thead> 
                                  <tr> 
                                  <th>Nro</th> 
                                  <th>Nombre Archivo</th>
                                  <th>Fecha de Carga</th> 
                                  </tr>
                              </thead>
                              <tbody class="tb-files-tipo1 tb-files-tipo2">
                                
                                    
                                     <c:forEach items="${anexos}" var="anexo">
            							<tr>
                                      		<td>${anexo.numSecuencia} </td>
                                      		<td>${anexo.txtRuta}</td>
                                      		<td>${anexo.fechaCarga}</td>
                                        </tr>
        							</c:forEach>  
                                 
                              </tbody>

                            </table>
                          </div>
                      </div><!-- row -->

                     
                    </div><!-- panel-body -->
                  </div><!-- panel-default -->
               
                 
                  <div  class="panel panel-default" 
                  
                  	 <c:if test="${idTipoSolicitud eq 1}">
            			style="display: none"
            		 </c:if>
            		 
            		 <c:if test="${idTipoSolicitud eq 2}">
            			style="display: none"
            		 </c:if>
                  >
                    <div class="panel-heading">
                      <h3 class="panel-title">Datos de Extraccion</h3>
                    </div><!-- panel-heading -->
                    <div class="panel-body">

                      <div class="row">
                        <div class="col-md-12"> 
                          <div class="form-group">
                            <div class="col-sm-8">
                              <div class="checkbox">
                                  <label>
                                    <input type="checkbox" value="${regulatorio}"  disabled="true" 
                                    	<c:if test="${regulatorio eq 1}">
            									checked
            		 					</c:if> 
            		 					name="regulatorio" id="regulatorio" value="1">Regulatorio
                                  </label>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div><!-- row -->

                      <div class="row">
                          <div class="col-md-6"> 
                            <div class="form-group">
                              <label for="resumen" class="control-label">Resumen:</label>
                              <input type="text" id="resumen"  name="resumen" value="${resumen}" disabled="true" class="form-control" />
                            </div>
                        
                            <div class="form-group">
                              <label for="descripcion2" class="control-label">Descripcion:</label>
                              <textarea id="descripcion2" name="descripcion2" class="form-control" disabled="true" rows="5">${description}</textarea>
                            </div>

                            <div class="form-group">
                              <label for=volumenes class="control-label">Volumenes a Considerar:</label>
                              <textarea id="volumenes" name="volumenes" class="form-control" rows="5" disabled="true">${volumenes}</textarea>
                            </div>
                          </div>

                          <div class="col-md-6"> 
                            <div class="form-group">
                              <label for="fechasCorte" class="control-label">Fechas de Corte:</label>
                              <input type="text" id="fechasCorte" name="fechasCorte" class="form-control" value="${fechasCorte}" disabled="true"/>
                            </div>
                        
                            <div class="form-group">
                              <label for="estructuraReporte" class="control-label">Estructura de Reporte:</label>
                              <textarea id="estructuraReporte" name="estructuraReporte" class="form-control" rows="5" disabled="true">${estruct}</textarea>
                            </div>

                            <div class="row">
                              <div class="col-md-6"> 
                                <div class="form-horizontal">
                                  <div class="form-group">
                                    
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-4"> 
                               
                              </div>
                            </div><!-- row -->  

                            <div class="row">
                              <div class="col-md-12"> 
                                  <table class="table table-bordered">
                                    <thead> 
                                        <tr> 
                                        <th>Nro</th> 
                                        <th>Nombre Archivo</th>
                                        <th>Fecha de Carga</th> 
                                        </tr>
                                    </thead>
                                    <tbody class="tb-files-tipo3">
                                    
                                    <c:forEach items="${anexos}" var="anexo">
            							<tr>
                                      		<td>${anexo.numSecuencia} </td>
                                      		<td>${anexo.txtRuta}</td>
                                      		<td>${anexo.fechaCarga}</td>
                                        </tr>
        							</c:forEach>  
        							 
                                    </tbody>

                                  </table>
                              </div>
                            </div><!-- row -->  

                            
                          </div>
                      </div><!-- row -->

                      

                     

                      <div class="row">
                        
                      </div><!-- row -->  
                
                    </div><!-- panel-body -->
                  </div><!-- panel-default -->
               
                     
                </div><!-- fin panel-dinamico-->

             </div><!-- fin row dynamic-->
	  
             <div class="row" >
                <div class="col-md-6 col-md-offset-6">
                  <div class="pull-right">
                    <button style="display: none;" class="btn btn-primary" id="btnRechazar">Rechazar</button>
                    <button style="display: none;" class="btn btn-primary" id="btnObservar">Observar</button>
                    <button class="btn btn-primary" id="btnProcesarSolucion">Procesar Solucion</button>
                    <button class="btn btn-primary" id="btnVolver">Volver</button>
                  </div>
                </div>
             </div>
             
   <!--start div wrap procesar solucion hidden  -->   
			 <form id="formEvaluar" enctype="multipart/form-data" method="post" accept-charset="utf-8">    
			  <input type="hidden" value="${idSolicitud}" id="nroSolicitud" name="nroSolicitud" class="form-control">
			     <div id="wrap_procesar_solucion">        
			             <div class="row" style="margin-top: 20px;">	
							<div class="panel panel-default">
			        			<div class="panel-heading">
			           				<h3 class="panel-title">Solucion Propuesta</h3>
			        			</div><!-- panel-heading -->
			        			<div class="panel-body">
									<table class="table table-bordered">
			                              <thead> 
			                                  <tr> 
			                                  <th>Nro</th> 
			                                  <th>Detalle Solucion</th>
			                                  <th>Solucion Planteada</th> 
			                                  <th>Cantid CTI Aplic.</th>
			                                  <th>Valor</th>
			                                  <th>Ver</th> 
			                                  <th></th>
			                                  </tr>
			                              </thead>
			                 			  <tbody id="tbSolucionPropuesta">
											<div>No se encontraron</div>
			                			</tbody>
			           				</table>
			           				<div class="row" >
										 <div class="col-md-4 col-md-offset-8">
											<div class="form-group">
												<label for="valor" class="col-sm-2  col-md-offset-6 control-label">Valor:</label>
												<div class="col-sm-4">
													<select id="valor" name="valor" class="form-control" >
														<option value=""></option>
														<option value="1">Alto</option>
														<option value="2">Bajo</option>
													</select>
												</div>
											</div>
			 							</div>
									</div>
			           								
							</div>
						</div>
					</div>
			             
			             <!-- start -->
			             	<div class="row" >
			                <div class="col-md-6 col-md-offset-6">
			                  <div class="pull-right">
			                    <button class="btn btn-primary" id="btnSolicitarApoyo">Solicitar Apoyo</button>
			                    <button class="btn btn-primary" id="btnPlantearSolucion">Plantear Solucion</button>
			                  </div>
			                </div>
			             </div>
			             <!-- end  -->
			       </div>   	   
	<!-- end div wrap procesar solucion hidden  -->   
      
    <!--start div wrap hidden  -->     
     <div id="wrap_plantear_solucion">     
             <!-- start -->
             <div class="row" style="margin-top: 20px;">	
				<div class="panel panel-default">
        			<div class="panel-heading">
           				<h3 class="panel-title">Solucion</h3>
        			</div><!-- panel-heading -->
        			<div class="panel-body">
						
						<div class="row" >
							<div class="col-md-6">
								<div class="form-group">
                              		<label for="descripcion" class="control-label">Analisis Funcional:</label>
								 	<textarea id="analisisFuncional" name="analisisFuncional" class="form-control" rows="8" ></textarea>
								 </div>
 							</div>
 							<div class="col-md-6">
 								<div class="form-group">
                              		<label for="descripcion" class="control-label">Analisis Tecnico:</label>
								 	<textarea id="analisisTecnico" name="analisisTecnico" class="form-control" rows="8" ></textarea>
								 </div>
 							</div>
						</div>
						
							<!-- start -->
             <div class="row">	
				
					<div class="col-md-6">
						<div class="row">
                        	<div class="col-md-6"> 
                            	<div class="form-horizontal">
                              		<div class="form-group">
                                		<label for="nrocti" class="col-sm-4 control-label">Ruta:</label>
                                		<div class="col-sm-8">
                                			<input type="file"  id="fileEvaluacion" name="fileEvaluacion" class="form-control">
                                		</div>
                              		</div>
                            </div>
                        	</div>
                        	<div class="col-md-4"> 
                          		<a class="btn btn-default btnAdjuntarEval">Adjuntar</a>
                        	</div>
                      </div>
                      <div class="row">
                        <div class="col-md-8"> 
                            <table class="table table-bordered">
                              <thead> 
                                  <tr> 
                                  <th>Nro</th> 
                                  <th>Nombre Archivo</th>
                                  <th>Fecha de Carga</th> 
                                  </tr>
                              </thead>
                              <tbody class="tb_file_eval">
                              
                              </tbody>
                            </table>
                        </div>
                      </div>	
 					</div>
 					<div class="col-md-6">
						<div class="row">
                      		<div class="col-md-6">
                        		<div class="form-horizontal">
                          			<div class="form-group">
                            			<label for="tipoSolicitud" class="col-sm-8 control-label">Tiempo Programado Atencion</label>
                            			<div class="col-sm-4">     
                                			<input type="text" id="tProgramadoAtencion" name="tProgramadoAtencion" class="form-control" >
                            			</div>
                          			</div>
                        		</div>
                      		</div>
                      		<div class="col-md-6">
                        		<div class="form-horizontal">
                          			<div class="form-group">
                            			<label for="tipoSolicitud" class="col-sm-8 control-label">Tipo Solucion:</label>
                            			<div class="col-sm-4">       
                                			<select id="tipoSolucion" name="tipoSolucion" class="form-control" >
												<option value=""></option>
												<option value="1">RAI -  Actualizacion de Datos</option>
												<option value="2">RAI - Aplicativo</option>
											</select>
                            			</div>
                          			</div>
                        		</div>
                      		</div>
                      </div><!-- row -->
                      <div class="row">
                      		<div class="col-md-6">
                        		<div class="form-horizontal">
                          			<div class="form-group">
                            			<label for="tipoSolicitud" class="col-sm-8 control-label">Tiempo Calculado Atencion</label>
                            			<div class="col-sm-4">     
                                			<input type="text" id="tCalAtencion" name="tCalAtencion" class="form-control" disabled>
                            			</div>
                          			</div>
                        		</div>
                      		</div>
                      		<div class="col-md-6">
                        		<div class="form-horizontal">
                          			<div class="form-group">
                            			<label for="tipoSolicitud" class="col-sm-8 control-label">Nivel Solucion:</label>
                            			<div class="col-sm-4">     
                                			<select id="nivelSolucion" name="nivelSolucion" class="form-control" >
												<option value=""></option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
                            			</div>
                          			</div>
                        		</div>
                      		</div>
                      </div><!-- row -->
                      <div class="row">
                      		<div class="col-md-6">
                        		<div class="form-horizontal">
                          			<div class="form-group">
                            			<label for="tipoSolicitud" class="col-sm-8 control-label">Tiempo Real Atencion:</label>
                            			<div class="col-sm-4">     
                                			<input type="text" id="tRealAtencion" name="tRealAtencion" class="form-control" >
                            			</div>
                          			</div>
                        		</div>
                      		</div>
                      </div><!-- row -->
				</div>			
			</div>	
       	<!-- end  --> 
       	
       		<div class="row" >
                <div class="col-md-6 col-md-offset-6">
                  <div class="pull-right">
                    <button class="btn btn-primary"  id="btnPaseAProd">Pase a Prod.</button>
                    <button class="btn btn-primary hidden"  id="btnMensaje">Mensaje</button>
                    <button class="btn btn-primary"  id="btnCerrarSolucion">Cerrar</button>
                  </div>
                </div>
             </div>
						
					</div><!-- end panel-body -->   
				</div>
			</div>
       	<!-- end  -->  
    </div>   	   
   <!-- end div wrap hidden  -->     	
	 <!-- end -->
		
	<!-- Modales -->	
	
	<!-- Modal Error-->
		<div class="modal fade" id="modalCerrar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog modal-sm">
    			<div class="modal-content">
    				<div class="modal-body">
    				
      					<div class="row" >
 							<div class="col-md-12">
								  <div class="form-group">
                              		<label for="descripcion" class="control-label">Detalle de Mensaje:</label>
                              		<textarea id="modalErrorMensaje" name="modalErrorMensaje" class="form-control" rows="8" ></textarea>
                            	</div>
 							</div>
						</div>
      					
    					<div class="modal-footer">
        					<button class="btn btn-primary" id="btnModalErrorAceptar">Aceptar</button>
      					</div>
    					
    				</div>
    			</div>
  			</div>
		</div>
		
	<!-- Modal Observacion -->
		<div class="modal fade" id="modalObservar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog modal-lg">
    			<div class="modal-content">
    				<div class="modal-body">
    				
      					<div class="row" >
 							<div class="col-md-12">
								  <div class="form-group">
                              		<h3>Propuesta de Soluci&oacute;n</h3>
                              		<input type="hidden" value="" id="idSolucion" name="idSolucion" class="form-control">
                              		<input type="hidden" value="" id="idAnalista" name="idAnalista" class="form-control">
                              		<label for="descripcion" class="control-label">Estimado(a):</label><br>
                              		<label for="descripcion" class="control-label">Analista Funcional</label>
                              		<label id="modalAnalista" class="control-label"></label><br>
                              		<label for="descripcion" class="control-label">Se realiz&oacute; el an&aacute;lisis del incidente presentado y la soluci&oacute;n propuesta es:</label><br>
                              		<textarea id="modalObservacionDetalle" name="modalObservacionDetalle" disabled="true" class="form-control" rows="4" ></textarea>
                              		<label id="captionAcciones" class="control-label"></label><a id="linkAcciones" href=""></a><br><br>
                              		<textarea id="modalConsideraciones" name="modalConsideraciones" disabled="true" class="form-control" rows="4" ></textarea>
                              		<label id="captionConsider" class="control-label"></label><a id="linkConsider" href=""></a><br><br>
                            	</div>
 							</div>
						</div>
						
    					<div class="modal-footer">
    						<label for="descripcion" class="control-label"><h4>&iquest;Se solucion&oacute; el problema reportado?&nbsp;&nbsp;&nbsp;</h4></label>
        					<button class="btn btn-primary" id="btnModalObservacionGuardar">S&iacute;</button>
    						<button class="btn btn-primary" id="btnModalNoSolucion">No</button>
      					</div>
    					
    				</div>
    			</div>
  			</div>
		</div>
	
	<!-- Modal Adjunta Script -->
		<div class="modal fade" id="modaAdjuntar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog modal-lg">
    			<div class="modal-content">
    				<div class="modal-body">
    				
      					<div class="row" >
 							<div class="col-md-12">
								  <div class="form-group">
                              		<h3>Ajuntar Script</h3>
                            	</div>
 							</div>
						</div>
						
						<div class="row">
                        	<div class="col-md-8"> 
                            	<div class="form-horizontal">
                              		<div class="form-group">
                               			 <label for="nrocti" class="col-sm-4 control-label">Ruta:</label>
                                		<div class="col-sm-8">
                                			<input type="file" id="file_observacion" name="file_observacion" class="form-control">
                                		</div>
                              		</div>
                            	</div>
                        	</div>
                        	<div class="col-md-4"> 
                          		<a class="btn btn-default btnAdjuntarObservacion">Adjuntar</a>
                        	</div>
                      </div>
                      
                      <div class="row">
                        <div class="col-md-8 col-md-offset-2"> 
                            <table class="table table-bordered">
                              <thead> 
                                  <tr> 
                                  <th>Nro</th> 
                                  <th>Nombre Archivo</th>
                                  <th>Fecha de Carga</th> 
                                  </tr>
                              </thead>
                              <tbody class="tbObservacionFiles"></tbody>

                            </table>
                        </div>
                      </div>
      					
    					<div class="modal-footer">
        					<button class="btn btn-primary" id="btnModalAdjuntarGuardar">Guardar</button>
    						<button class="btn btn-primary" id="btnModalAjuntarCancelar">Cancelar</button>
      					</div>
    					
    				</div>
    			</div>
  			</div>
		</div>
	
	<div class="modal fade" id="modalRechazar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg">
    			<div class="modal-content">
    				<div class="modal-body">
      					<div class="row" >
 							<div class="col-md-12">
								  <div class="form-group">
                              		<label for="descripcion" class="control-label">Detalle de Rechazo:</label>
                              		<textarea id="modalRechazoDetalle" name="modalRechazoDetalle" class="form-control" rows="8" ></textarea>
                            	</div>
 							</div>
						</div>
    					<div class="modal-footer">
        					<button class="btn btn-primary" id="btnModalRechazoGuardar">Guardar</button>
      					</div>
    				</div>
    			</div>
  			</div>
	</div>
		
	<!-- Modal Soporte -->
	<div class="modal fade" id="modalSoporte" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog modal-lg">
			<div class="modal-content">
    				<div class="modal-body">
    					
    					<div class="row" >
    					
 							<div class="col-md-6 col-md-offset-3"> 
                            	<div class="form-horizontal">
                              		<div class="form-group">
                                		<label for="nrocti" class="col-sm-4 control-label">Area:</label>
                                		<div class="col-sm-8 ">
                                			<select id="areaSoporte" class="form-control" >
												<option value=""></option>
												<option value="desarrollo">Desarrollo</option>
												<option value="proveedor">Proveedor</option>
											</select>
                                		</div>
                              		</div>
                            	</div>
                        	</div>
                        	
						</div>
						<div class="row" id="area-desarrollo" style="display:none;">
						
                        	<div class="col-md-12"> 
                            	<div class="form-horizontal">
                             	 	<div class="form-group">
                                		<label for="nrocti" class="col-sm-4 control-label">Servicio de desarrollo:</label>
                                		<div class="col-sm-8">
                                			<input type="text" name="servicioDesarrollo" class="form-control" disabled value="${servicioDesarrollo}">
                              			</div>
                            		</div>
                        		</div>	
                      		</div>
                      		<div class="col-md-12"> 
                            	<div class="form-horizontal">
                             	 	<div class="form-group">
                                		<label for="nrocti" class="col-sm-4 control-label">Jefe de desarrollo:</label>
                                		<div class="col-sm-8">
                                			<input type="text" name="jefeDesarrollo" class="form-control" disabled value="${JefeDesarrollo}">
                                		</div>
                            		</div>
                        		</div>
                      		</div>
                      		<div class="col-md-12"> 
                            	<div class="form-horizontal">
                             	 	<div class="form-group">
                                		<label for="nrocti" class="col-sm-4 control-label">Analista Funcional:</label>
                                		<div class="col-sm-8">
                                			<input type="text" name="analistaFuncional" class="form-control" disabled value="${AnalistaFuncional}">
                                		</div>
                            		</div>
                        		</div>
                      		</div>
                      		<div class="col-md-12">
								  <div class="form-group">
                              		<label for="descripcion" class="control-label">Detalle de Analisis:</label>
                              		<textarea id="detalleAnalisisDesarrollo" name="detalleAnalisisDesarrollo" class="form-control" rows="8" ></textarea>
                            	</div>
 							</div>
 							<div class="col-sm-8">
                              <div class="checkbox">
                                  <label>
                                    <input type="checkbox" name="generarSS" id="generarSS" />
           							Generar SS		
                                  </label>
                              </div>
                            </div>
						</div>
						<!-- Proveedor -->
				    		<div class="row" id="area-proveedor" style="display:none;">
						
                        	<div class="col-md-12"> 
                            	<div class="form-horizontal">
                             	 	<div class="form-group">
                                		<label for="nrocti" class="col-sm-4 control-label">Nombre Proveedor:</label>
                                		<div class="col-sm-8">
                                			<input type="text" name="nombreProveedor" class="form-control" disabled>
                              			</div>
                            		</div>
                        		</div>	
                      		</div>
                      		<div class="col-md-12">
								  <div class="form-group">
                              		<label for="descripcion" class="control-label">Detalle de Analisis:</label>
                              		<textarea id="detalleAnalisisProveedor" name="detalleAnalisisProveedor" class="form-control" rows="8" ></textarea>
                            	</div>
 							</div>
						</div>

				    	<div class="modal-footer">
        					<button class="btn btn-primary" id="btnModalSoporteGuardar">Guardar</button>
      					</div>
				    </div>
  			</div>
		</div>
	</div>
  
        </form>
      </div>
	</div>
</div>
   
    <script type="text/javascript" src="/atencion-incidente/vendors/js/jquery.form.js"></script>
    <script type="text/javascript" src="/atencion-incidente/vendors/js/moment.js"></script>
    <script type="text/javascript" src="/atencion-incidente/resources/js/evaluar.js"></script>
 	</body>

 </html>