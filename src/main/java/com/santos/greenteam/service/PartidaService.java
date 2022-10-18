package com.santos.greenteam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.greenteam.DTO.PartidaFinalizadaDTO;
import com.santos.greenteam.DTO.PartidaFuturaDTO;
import com.santos.greenteam.entity.Planejamento;
import com.santos.greenteam.util.DataUtil;
import com.santos.greenteam.util.PartidaFinalizadaUtil;
import com.santos.greenteam.util.PartidasFuturasUtil;


@Service
public class PartidaService {
	
	@Autowired
	private PlanejamentoService planejamentoService;
	
	@Autowired
	private PartidaFinalizadaUtil finalizadaUtil;
	
	@Autowired
	private PartidasFuturasUtil futurasUtil;
	
	

	public void finalizarPartidaPlanejamento() {
		Integer quantidadePartidas = planejamentoService.buscarQuantidadesPlanejemento();
		
		if(quantidadePartidas > 0) {
			List<Planejamento> Planejamentos = planejamentoService.buscarPartidasPeriodos();
			
			for(Planejamento p : Planejamentos) {
				String urlPartida = finalizadaUtil.montaUrlGoogle(
						p.getEquipeCasa(),
						p.getEquipeVisitante(),
						DataUtil.formataDateEmString(p.getDataHoraPartida(), "dd/MM/yyyy"));
					
				PartidaFinalizadaDTO PartidaFinalizadaDTO = finalizadaUtil.obtemInformacoesPartida(urlPartida);
				
				planejamentoService.atualizaPlanejamentoFinalizado(p, PartidaFinalizadaDTO);
				
				}
			
		}
	}
	
	public List<PartidaFuturaDTO> listarPartidasFuturas(String data, String liga){
		
		List<PartidaFuturaDTO> partidas = new ArrayList<>();
		
		String url = futurasUtil.montaUrl(data, liga);
		
		partidas = futurasUtil.obtemInformacoesPartida(url);
		
		return partidas;
		
	}
	
	public PartidaFinalizadaDTO listarPartidaFinalizada(String nomeEquipeCasa, String nomeEquipeVisitante, String data){
		
		PartidaFinalizadaDTO partidas = new PartidaFinalizadaDTO();
		
		String url = finalizadaUtil.montaUrlGoogle(nomeEquipeCasa, nomeEquipeVisitante, data);
		
		partidas = finalizadaUtil.obtemInformacoesPartida(url);
		
		return partidas;
		
	}
	
}
