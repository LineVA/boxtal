package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.Structure;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DoctolibConnectorImpl implements DoctolibConnector {

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public List<Structure> getStructures(String zipCode) {
        List<Structure> structures = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.doctolib.fr/vaccination-covid-19/"
                    + zipCode
                    + "?ref_visit_motive_ids[]=6970&ref_visit_motive_ids[]=7005&ref_visit_motive_ids[]=8740&ref_visit_motive_ids[]=8739")
                    .get();
            Elements htmlStructures = doc.getElementsByAttributeValueStarting("id", "search-result-");
            for (Element struc : htmlStructures) {
                structures.add(Structure.builder()
                        .id(struc.id().substring(14))
                        .name(struc.select("div div div h3 a div").text())
                        .build()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return structures;
    }

    @Override
    public List<Object> getAppointmentsByStructure(Structure structure) {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("https://www.doctolib.fr/search_results/"
                    + structure.getId()
                    + ".json?limit=7&ref_visit_motive_ids[]=6970,7005,8740,8739&speciality_id=5494&search_result_format=json");

            AppointmentDto response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), AppointmentDto.class));
            return null;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
