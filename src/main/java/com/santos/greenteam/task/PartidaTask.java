package com.santos.greenteam.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.santos.greenteam.service.PartidasService;
import com.santos.greenteam.util.DataUtil;

@Configuration
@EnableScheduling
public class PartidaTask {

	private static final  Logger LOGGER = LoggerFactory.getLogger(PartidaTask.class);
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	@Autowired
	private PartidasService service;
	
	@Scheduled(cron = "0 34 19 * * *", zone = TIME_ZONE)
	public void taskPartidaFuturas() {
		carregarPartidasFuturas("taskPartidaFuturas()");
	}
	
	private void carregarPartidasFuturas(String diaSemana) {
		this.graLogInfo(String.format("%s: %s", diaSemana, DataUtil.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
		
		service.inserirPartidas();
	}

	private void graLogInfo(String mensagem) {
		LOGGER.info(mensagem);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
