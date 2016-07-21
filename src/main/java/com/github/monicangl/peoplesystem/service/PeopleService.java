package com.github.monicangl.peoplesystem.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.monicangl.peoplesystem.domain.Name;
import com.github.monicangl.peoplesystem.domain.Office;
import com.github.monicangl.peoplesystem.domain.PeopleBasicInfo;
import com.github.monicangl.peoplesystem.model.People;
import com.github.monicangl.peoplesystem.repository.PeopleRepository;
import com.github.monicangl.peoplesystem.service.exception.RequestParameterValueUnsupportedException;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newTreeSet;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class PeopleService
{
    private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository)
    {
        this.peopleRepository = peopleRepository;
    }

    public List<PeopleBasicInfo> getPeopleList(List<String> names, String order)
    {
        if (order.equals(Filter.FULL_NAME.getValue())) {
            return getPeopleOrderByFullName(names);
        } else if (order.equals(Filter.WORKING_OFFICE.getValue())) {
            return getPeopleOrderByWorkingOffice(names);
        }
        String errorMessage = String.format("Unsupported value of parameter order_by, supported values: [%s, %s], and %s is the default supported"
                , Filter.FULL_NAME.getValue(), Filter.WORKING_OFFICE.getValue(), Filter.FULL_NAME.getValue());
        throw new RequestParameterValueUnsupportedException(errorMessage);
    }

    public List<Office> getPeopleGroup(List<String> names, String group)
    {
        if (group.equals(Filter.WORKING_OFFICE.getValue())) {
            return getPeopleGroupByWorkingOffice(names);
        }
        String errorMessage = String.format("Unsupported value of parameter group_by, supported values: [%s]", Filter.WORKING_OFFICE.getValue());
        throw new RequestParameterValueUnsupportedException(errorMessage);
    }

    private List<People> getPeople(List<String> names)
    {
        List<People> peopleList = newArrayList();
        for (String name : names) {
            People people = peopleRepository.findByLoginName(name);
            if (null != people) {
                peopleList.add(people);
            }
        }
        return peopleList;
    }

    private List<PeopleBasicInfo> getPeopleOrderByFullName(List<String> names)
    {
        return getPeople(names).stream()
                .map(p -> new PeopleBasicInfo(p.getLoginName(), p.getPreferredName(), p.getWorkingOffice()))
                .sorted((p1, p2) -> (p1.getFullName().compareTo(p2.getFullName())))
                .collect(toList());
    }

    private List<PeopleBasicInfo> getPeopleOrderByWorkingOffice(List<String> names)
    {
        return getPeopleOrderByFullName(names).stream()
                .sorted((p1, p2) -> (p1.getWorkingOffice().compareTo(p2.getWorkingOffice())))
                .collect(toList());
    }

    private List<Office> getPeopleGroupByWorkingOffice(List<String> names)
    {
        List<PeopleBasicInfo> people = getPeopleOrderByFullName(names);
        Set<String> workingOffices = newTreeSet();
        workingOffices.addAll(people.stream().map(PeopleBasicInfo::getWorkingOffice).collect(toList()));
        List<Office> offices = newArrayList();
        for (String office : workingOffices) {
            offices.add(new Office(office, people.stream()
                    .filter(p -> p.getWorkingOffice().equals(office))
                    .map(p -> new Name(p.getShortName(), p.getFullName()))
                    .collect(toList())));
        }
        return offices;
    }
}
