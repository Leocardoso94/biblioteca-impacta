<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section class="content-header">
	<h1>
		Exemplares <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Exemplares</h3>
					<button class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#inserir${exemplar.num_exemplar}">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
					<hr>
					<form action="buscaExemplar" method="get" class="">
						<div class="input-group">
							<input type="text" name="search" class="form-control"
								placeholder="Buscar..."> <span class="input-group-btn">
								<button type="submit" id="search-btn" class="btn btn-flat">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>

				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>Número do exemplar</th>
							<th>Título do Exemplar</th>
							<th>Data de Aquisição</th>
							<th>Emprestado?</th>
							<th>Alterar</th>
							<th>Excluir</th>
						</tr>
						<c:forEach items="${exemplares}" var="exemplar">
							<tr>
								<td>${exemplar.num_exemplar}</td>
								<td>${exemplar.nomeobra}</td>
								<td><fmt:formatDate value="${exemplar.data_aquisicao}"
										type="date" dateStyle="long" /></td>
								<c:choose>
									<c:when test="${exemplar.emprestado}">
										<td>Sim</td>
									</c:when>
									<c:otherwise>
										<td>Não</td>
									</c:otherwise>
								</c:choose>
								<td><button class="btn btn-warning" data-toggle="modal"
										data-target="#modal${exemplar.num_exemplar}">
										<i class="fa fa-pencil" aria-hidden="true"></i>
									</button></td>
								<td><button class="btn btn-danger" data-toggle="modal"
										data-target="#delete${exemplar.num_exemplar}">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button></td>
							</tr>

							<!-- Modal -->
							<div class="modal fade modal-warning"
								id="modal${exemplar.num_exemplar}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Alterar</h4>
										</div>
										<form role="form" method="post" class="form-horizontal"
											action="alterarExemplar">
											<div class="modal-body">
												<div class="box-body">
													<input type="hidden" name="num_exemplar"
														value="${exemplar.num_exemplar}">
													<div class="form-group">
														<label for="inputTipo${exemplar.num_exemplar}"
															class="col-sm-4 control-label">Título do
															Exemplar:</label>
														<div class="col-sm-12">
															<select id="inputTipo${exemplar.num_exemplar}"
																class="form-control" name="idobra">
																<option value="${exemplar.idobra}">${exemplar.nomeobra}</option>
																<c:forEach items="${obras}" var="obra">
																	<option value="${obra.idobra}">${obra.titulo}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="inputDataInsert${exemplar.num_exemplar}"
															class="col-sm-4 control-label">Data de aquisição:</label>

														<div class="col-sm-12">
															<input type="date" name="data" class="form-control"
																max='2000-13-13' value="${exemplar.data_aquisicao}"
																class="sDate"
																id="inputDataInsert${exemplar.num_exemplar}" required>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Cancelar</button>
												<button type="submit" class="btn btn-outline">Alterar</button>
											</div>
										</form>
									</div>
								</div>
							</div>

							<!-- /.Modal -->

							<!-- Modal -->
							<div class="modal fade modal-danger"
								id="delete${exemplar.num_exemplar}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Excluir</h4>
										</div>
										<form role="form" method="post" action="excluirExemplar">
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<p>
															Tem certeza que deseja excluir <b>${exemplar.num_exemplar}
																${exemplar.nomeobra}?</b>
														</p>
														<h6>Caso o exemplar não seja deletado é necessário
															deletar as obras que estão vinculadas a ele</h6>
														<div class="col-sm-12">
															<input type="hidden" name="num_exemplar"
																value="${exemplar.num_exemplar}" readonly> <input
																type="hidden" value="" name="nome" class="form-control"
																id="nomeExemplar" placeholder="Nome da Exemplar"
																required readonly>
														</div>
													</div>
												</div>
												<!-- /.box-body -->

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Cancelar</button>
												<button type="submit" class="btn btn-outline">Excluir</button>
											</div>
										</form>
									</div>

								</div>
							</div>
						</c:forEach>
					</table>
				</div>


				<!-- Modal -->
				<div class="modal fade modal-primary"
					id="inserir${exemplar.num_exemplar}" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Inserir</h4>
							</div>
							<form role="form" method="post" class="form-horizontal"
								action="adicionarExemplar">
								<div class="modal-body">
									<div class="box-body">
										<div class="form-group">
											<label for="inputNumeroExemplar"
												class="col-sm-2 control-label">Número do exemplar</label>

											<div class="col-sm-10">
												<input type="number" name="num_exemplar"
													class="form-control" id="inputNumeroExemplar"
													max="99999999999999" placeholder="Número do Exemplar"
													required>
											</div>
										</div>

										<div class="form-group">
											<label for="inputTipo3" class="col-sm-2 control-label">Título
												do Exemplar</label>
											<div class="col-sm-10">
												<select id="inputTipo3" class="form-control" name="idobra"
													required>
													<option></option>
													<c:forEach items="${obras}" var="obra">
														<option value="${obra.idobra}">${obra.titulo}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputDataInsert" class="col-sm-2 control-label">Data
												de aquisição</label>

											<div class="col-sm-10">
												<input type="date" name="data" class="form-control"
													max='2000-13-13' class="sDate" id="inputDataInsert"
													required>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancelar</button>
									<button type="submit" class="btn btn-outline">Inserir</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- /.Modal -->
				<div class="box-footer clearfix">
					<ul class="pagination pagination-sm no-margin pull-right">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
			<!-- /.box -->
		</div>

	</div>
</section>