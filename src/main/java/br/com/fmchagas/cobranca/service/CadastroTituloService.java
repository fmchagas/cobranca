package br.com.fmchagas.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fmchagas.cobranca.model.StatusTitulo;
import br.com.fmchagas.cobranca.model.Titulo;
import br.com.fmchagas.cobranca.repository.Titulos;
import br.com.fmchagas.cobranca.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {
	
	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
		titulos.save(titulo);
		}catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("formato de data invalido");
		}
	}

	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "": filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}
}
