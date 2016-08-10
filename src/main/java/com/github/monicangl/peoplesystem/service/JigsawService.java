package com.github.monicangl.peoplesystem.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.github.monicangl.peoplesystem.domain.WorkingOffice;
import com.github.monicangl.peoplesystem.domain.jigsaw.People;

@Service
public class JigsawService
{
    private static final Integer PEOPLE_COUNT_OF_A_PAGE = 50;
    private String url;
    private RestTemplate restTemplate;
    private HttpEntity<String> httpEntity;
    private Environment environment;

    @Autowired
    public JigsawService(Environment environment)
    {
        this.environment = environment;
        this.restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(newArrayList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", environment.getProperty("token"));
        url = environment.getProperty("api").concat("?working_office=%s&page=%s");
        this.httpEntity = new HttpEntity<>("parameters", headers);
    }

    public List<People> getPeopleOfWorkingOffices(List<WorkingOffice> workingOffices)
    {
        List<People> people = newArrayList();
        for (WorkingOffice workingOffice : workingOffices) {
            people.addAll(newArrayList(getPeopleOfWorkingOffice(workingOffice)));
        }
        return people;
    }

    private List<People> getPeopleOfWorkingOffice(WorkingOffice workingOffice)
    {
        List<People> people = newArrayList();
        ResponseEntity<People[]> result;
        Integer page = 1;
        do {
            result = restTemplate.exchange(
                    String.format(url, workingOffice.getValue(), page.toString())
                    , HttpMethod.GET, httpEntity, People[].class);
            people.addAll(newArrayList(result.getBody()));
            ++page;
        } while (result.getBody().length == PEOPLE_COUNT_OF_A_PAGE);
        return people;
    }
}
