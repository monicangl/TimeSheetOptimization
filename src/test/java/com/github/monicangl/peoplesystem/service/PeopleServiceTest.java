package com.github.monicangl.peoplesystem.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.github.monicangl.peoplesystem.domain.Office;
import com.github.monicangl.peoplesystem.domain.PeopleBasicInfo;
import com.github.monicangl.peoplesystem.model.People;
import com.github.monicangl.peoplesystem.repository.PeopleRepository;
import com.github.monicangl.peoplesystem.service.exception.RequestParameterValueUnsupportedException;

public class PeopleServiceTest
{
    @Mock
    private PeopleRepository peopleRepository;
    @InjectMocks
    private PeopleService peopleService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        People glnie = new People();
        glnie.setLoginName("glnie");
        glnie.setPreferredName("Guiling Nie");
        glnie.setWorkingOffice("Chengdu");
        People bwang = new People();
        bwang.setLoginName("bwang");
        bwang.setPreferredName("Bing Wang");
        bwang.setWorkingOffice("Chengdu");
        People hwen = new People();
        hwen.setLoginName("hwen");
        hwen.setPreferredName("Hao Wen");
        hwen.setWorkingOffice("Beijing");
        when(peopleRepository.findByLoginName("glnie")).thenReturn(glnie);
        when(peopleRepository.findByLoginName("bwang")).thenReturn(bwang);
        when(peopleRepository.findByLoginName("hwen")).thenReturn(hwen);
    }

    @Test
    public void should_be_able_to_return_people_order_by_full_name_when_get_people_order_by_full_name()
    {
        // given
        List<String> names = newArrayList("glnie", "bwang");

        // when
        List<PeopleBasicInfo> people = peopleService.getPeopleList(names, Filter.FULL_NAME.getValue());

        // then
        assertThat(people.size(), is(2));
        assertThat(people.get(0).getShortName(), is("bwang"));
        assertThat(people.get(0).getFullName(), is("Bing Wang"));
        assertThat(people.get(0).getWorkingOffice(), is("Chengdu"));
        assertThat(people.get(1).getShortName(), is("glnie"));
        assertThat(people.get(1).getFullName(), is("Guiling Nie"));
        assertThat(people.get(1).getWorkingOffice(), is("Chengdu"));
    }

    @Test
    public void should_be_able_to_return_existing_people_order_by_full_name_when_get_people_order_by_full_name()
    {
        // given
        List<String> names = newArrayList("glnie", "bwang");
        when(peopleRepository.findByLoginName("glnie")).thenReturn(null);

        // when
        List<PeopleBasicInfo> people = peopleService.getPeopleList(names, Filter.FULL_NAME.getValue());

        // then
        assertThat(people.size(), is(1));
        assertThat(people.get(0).getShortName(), is("bwang"));
        assertThat(people.get(0).getFullName(), is("Bing Wang"));
        assertThat(people.get(0).getWorkingOffice(), is("Chengdu"));
    }

    @Test
    public void should_be_able_to_return_people_order_by_working_office_when_get_people_order_by_working_office()
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        List<PeopleBasicInfo> people = peopleService.getPeopleList(names, Filter.WORKING_OFFICE.getValue());

        // then
        assertThat(people.size(), is(3));
        assertThat(people.get(1).getShortName(), is("bwang"));
        assertThat(people.get(1).getFullName(), is("Bing Wang"));
        assertThat(people.get(1).getWorkingOffice(), is("Chengdu"));
        assertThat(people.get(0).getShortName(), is("hwen"));
        assertThat(people.get(0).getFullName(), is("Hao Wen"));
        assertThat(people.get(0).getWorkingOffice(), is("Beijing"));
        assertThat(people.get(2).getShortName(), is("glnie"));
        assertThat(people.get(2).getFullName(), is("Guiling Nie"));
        assertThat(people.get(2).getWorkingOffice(), is("Chengdu"));
    }

    @Test(expected = RequestParameterValueUnsupportedException.class)
    public void should_be_able_to_raise_exception_when_get_people_with_unsupported_order()
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        peopleService.getPeopleList(names, "loginName");
    }

    @Test
    public void should_be_able_to_return_people_group_by_working_office_when_get_people_group_by_working_office()
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        List<Office> people = peopleService.getPeopleGroup(names, Filter.WORKING_OFFICE.getValue());

        // then
        assertThat(people.size(), is(2));
        assertThat(people.get(0).getName(), is("Beijing"));
        assertThat(people.get(0).getPeopleCount(), is(1));
        assertThat(people.get(0).getPeople().get(0).getShortName(), is("hwen"));
        assertThat(people.get(0).getPeople().get(0).getFullName(), is("Hao Wen"));
        assertThat(people.get(1).getName(), is("Chengdu"));
        assertThat(people.get(1).getPeopleCount(), is(2));
        assertThat(people.get(1).getPeople().get(0).getShortName(), is("bwang"));
        assertThat(people.get(1).getPeople().get(0).getFullName(), is("Bing Wang"));
        assertThat(people.get(1).getPeople().get(1).getShortName(), is("glnie"));
        assertThat(people.get(1).getPeople().get(1).getFullName(), is("Guiling Nie"));
    }

    @Test(expected = RequestParameterValueUnsupportedException.class)
    public void should_be_able_to_raise_exception_when_get_people_with_unsupported_group()
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        peopleService.getPeopleGroup(names, "loginName");
    }
}
