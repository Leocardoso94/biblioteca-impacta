<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="content-header">
	<h1>
		404 Error Page <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="error-page">
		<h2 class="headline text-yellow">404</h2>

		<div class="error-content">
			<h3>
				<i class="fa fa-warning text-yellow"></i> Oops! P�gina n�o encontrada.
			</h3>

			<p>
				N�s n�o conseguimos encontrar a p�gina que voc� estava
				buscando. Enquanto isso, voc� pode <a href="<c:url value="/admin"/>">retornar
					ao dashboard</a>
			</p>

		</div>
		<!-- /.error-content -->
	</div>
	<!-- /.error-page -->
</section>