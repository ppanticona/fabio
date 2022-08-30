package com.ppanticona.fabio.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.ppanticona.fabio.domain.Caja;
import com.ppanticona.fabio.domain.HistoricoCaja;
import com.ppanticona.fabio.repository.CajaRepository;
import com.ppanticona.fabio.repository.HistoricoCajaRepository;
import com.ppanticona.fabio.service.CajaService;
import java.time.ZonedDateTime;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;
    private final HistoricoCajaRepository historicoCajaRepository;

    public CajaServiceImpl(CajaRepository cajaRepository, HistoricoCajaRepository historicoCajaRepository) {
        this.cajaRepository = cajaRepository;
        this.historicoCajaRepository = historicoCajaRepository;
    }

    private final Logger log = LoggerFactory.getLogger(CajaService.class);

    @Override
    public String aperturarCaja(Map datosMap) {
        try {
            this.log.debug("caja =  ");
            this.log.debug(String.valueOf(datosMap.get("caja")));
            String usuCrea = String.valueOf(datosMap.get("usuCrea"));
            ZonedDateTime fecCrea = ZonedDateTime.parse(String.valueOf(datosMap.get("fecCrea")));
            final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            mapper.registerModule(new JSR310Module());
            Map<String, Object> cajaMap = (Map<String, Object>) datosMap.get("caja");
            Caja cajaBean = mapper.convertValue(cajaMap, Caja.class);

            cajaBean.setEstado("02");
            cajaBean.setUsuModif(usuCrea);
            cajaBean.setFecModif(fecCrea);

            Caja res = this.cajaRepository.save(cajaBean);

            HistoricoCaja historicoCajaBean = new HistoricoCaja();

            historicoCajaBean.setEstado("01"); //   01 activa ;  02 historica
            historicoCajaBean.setFecIniVig(fecCrea);
            historicoCajaBean.setFecFinVig(fecCrea);
            historicoCajaBean.setMontoInicialDolares(Double.valueOf(String.valueOf(datosMap.get("montoInicialDolares"))));
            historicoCajaBean.setMontoMaximoDolares(Double.valueOf(String.valueOf(datosMap.get("montoMaximoDolares"))));
            historicoCajaBean.setMontoInicialSoles(Double.valueOf(String.valueOf(datosMap.get("montoInicialSoles"))));
            historicoCajaBean.setMontoMaximoSoles(Double.valueOf(String.valueOf(datosMap.get("montoMaximoSoles"))));
            historicoCajaBean.setCaja(cajaBean);
            historicoCajaBean.setUsuCrea(usuCrea);
            historicoCajaBean.setFecCrea(fecCrea);
            historicoCajaBean.setVersion(1);
            historicoCajaBean.setIndDel(false);
            historicoCajaBean.ipCrea("0.0.0.0");
            historicoCajaBean.setUsuarioAsignado(String.valueOf(datosMap.get("usuarioAsignado")));

            this.historicoCajaRepository.save(historicoCajaBean);

            String jsonData = "{\"Result\":\"OK\",\"caja\":\"" + res.toString() + "\"}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante la apertura de la caja ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return error;
        }
    }

    @Override
    public String cerrarCaja(Map datosMap) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JSR310Module());

            Map<String, Object> cajaMap = (Map<String, Object>) datosMap.get("caja");

            Caja cajaBean = objectMapper.convertValue(cajaMap, Caja.class);

            cajaBean.setEstado("01");

            Caja res = this.cajaRepository.save(cajaBean);

            String jsonData = "{\"Result\":\"OK\",\"caja\":\"" + res.toString() + "\"}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante la apertura de la caja ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return error;
        }
    }
}
