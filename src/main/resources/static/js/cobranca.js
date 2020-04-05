$('#confirmModal').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget)
	var descricaoTitulo = button.data('descricao')
	var codigoTitulo = button.data('codigo')
	var urlRemove = button.attr('href');
	 
	var modal = $(this)
	
	modal.find('form').attr('action', urlRemove);
	
	modal.find('#descricaoTitulo').html('Você confirma a exclusão do titulo <strong>' + descricaoTitulo + '</strong>?')
})

$(function(){
	$('[rel="tooltip"]').tooltip();
	
	$('#valor').maskMoney({thousands:'.', decimal:',', allowZero:true});
	
	$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();
		
		var elementAncora = $(event.currentTarget);
		var urlReceber = elementAncora.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e){
			let codigoTitulo = elementAncora.data('codigo');
			$('[data-role=' + codigoTitulo +']').html('<span class="badge badge-pill badge-success">'+ e +'</span>');
			
			elementAncora.hide();
		});
		
		response.fail(function(e){
			console.log(e);
			alert('Erro ao receber cobrança');
		});
	});
});