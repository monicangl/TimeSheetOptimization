package com.github.monicangl.peoplesystem.service;

import static java.util.stream.Collectors.toList;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.github.monicangl.peoplesystem.domain.WorkingOffice;
import com.github.monicangl.peoplesystem.domain.jigsaw.People;
import com.github.monicangl.peoplesystem.repository.PeopleRepository;
import com.github.monicangl.peoplesystem.service.mapper.PeopleMapper;

@Service
public class RepositoryService
{
    private static final List<WorkingOffice> workingOffices = newArrayList(
            WorkingOffice.CHENGDU
            , WorkingOffice.BEIJING
            , WorkingOffice.XIAN
            , WorkingOffice.SHANGHAI
            , WorkingOffice.SHENZHEN
            , WorkingOffice.WUHAN);
    private JigsawService jigsawService;
    private PeopleRepository peopleRepository;
    private PeopleMapper peopleMapper;

    @Autowired
    public RepositoryService(JigsawService jigsawService
            , PeopleRepository peopleRepository
            , PeopleMapper peopleMapper)
    {
        this.jigsawService = jigsawService;
        this.peopleRepository = peopleRepository;
        this.peopleMapper = peopleMapper;
        updateRepository();
    }

    @Scheduled(cron = "0 0 1 * * MON")
    public void updateRepository()
    {
        long start = System.currentTimeMillis();
        List<com.github.monicangl.peoplesystem.model.People> people = getNewestPeople();
        System.out.println("get jigsaw people, time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        peopleRepository.save(people);
        System.out.println("update repository, time: " + (System.currentTimeMillis() - start));
    }

    private List<com.github.monicangl.peoplesystem.model.People> getNewestPeople()
    {
        List<People> jigsawPeople =
                jigsawService.getPeopleOfWorkingOffices(workingOffices);
        List<com.github.monicangl.peoplesystem.model.People> modelPeople = newArrayList();
        modelPeople.addAll(jigsawPeople.stream().map(peopleMapper::map).collect(toList()));
        return modelPeople;
    }
}
