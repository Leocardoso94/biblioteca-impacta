<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
	<h1>
		Editoras <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-6">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Editoras</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">#</th>
							<th>Nome</th>
							<th>Alterar</th>
							<th>Excluir</th>
						</tr>
						<c:forEach items="${editoras}" var="editora">
							<tr>
								<td>${editora.ideditora}.</td>
								<td>${editora.nome_editora}</td>
								<td><button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i></button></td>
								<td><button class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i></button></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- /.box-body -->
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

		<div class="col-md-6">
			<!-- Horizontal Form -->
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">Adicionar Editora</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form class="form-horizontal" method="post" action="adicionarEditora">
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Nome</label>

							<div class="col-sm-10">
								<input type="text" name="nome_editora" class="form-control" id="inputEmail3"
									placeholder="Nome da Editora" required>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-primary center-block">Inserir</button>
					</div>
					<!-- /.box-footer -->
				</form>
			</div>
		</div>
	</div>
</section>