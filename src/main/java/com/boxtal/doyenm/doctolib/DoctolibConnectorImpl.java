package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.StructureDto;
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
    private final CloseableHttpClient client = HttpClients.createDefault();

    @Override
    public List<StructureDto> getStructures(String zipCode) {
        List<StructureDto> structures = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.doctolib.fr/vaccination-covid-19/"
                    + zipCode
                    + "?ref_visit_motive_ids[]=6970&ref_visit_motive_ids[]=7005&ref_visit_motive_ids[]=8740&ref_visit_motive_ids[]=8739")
                    .get();
            Elements htmlStructures = doc.getElementsByAttributeValueStarting("id", "search-result-");
            for (Element struc : htmlStructures) {
                structures.add(StructureDto.builder()
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
    public AppointmentDto getAppointmentsByStructure(StructureDto structure) {

        try {
            HttpGet request = new HttpGet("https://www.doctolib.fr/search_results/" +
                   structure.getId() +
                    ".json?limit=5&ref_visit_motive_ids%5B%5D=6970&ref_visit_motive_ids%5B%5D=7005&ref_visit_motive_ids%5B%5D=8740&ref_visit_motive_ids%5B%5D=8739&speciality_id=5494&search_result_format=json");

            AppointmentDto response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), AppointmentDto.class));
            response.setStructure(structure);
            return response;

        } catch (IOException ex) {
           log.error("Exception when calling Doctolib for Structure {} : {}", structure.getId(), ex);
            return new AppointmentDto();
        }
    }
}
