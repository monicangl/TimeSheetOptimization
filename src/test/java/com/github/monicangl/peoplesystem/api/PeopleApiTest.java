package com.github.monicangl.peoplesystem.api;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.monicangl.peoplesystem.model.People;
import com.github.monicangl.peoplesystem.repository.PeopleRepository;
import com.github.monicangl.peoplesystem.service.Filter;
import com.github.monicangl.peoplesystem.service.PeopleService;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class PeopleApiTest
{
    private MockMvc mockMvc;
    @Mock
    private PeopleRepository peopleRepository;
    @InjectMocks
    private PeopleService peopleService;

    @Before
    public void setUp() throws Exception
    {
        this.mockMvc = standaloneSetup(new PeopleApi(peopleService)).build();
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
    public void should_be_able_to_return_people_ordered_by_full_name_when_receive_request_of_get_people_without_parameter_order_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .content(json(names)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shortName", is("bwang")))
                .andExpect(jsonPath("$[0].fullName", is("Bing Wang")))
                .andExpect(jsonPath("$[0].workingOffice", is("Chengdu")))
                .andExpect(jsonPath("$[1].shortName", is("glnie")))
                .andExpect(jsonPath("$[1].fullName", is("Guiling Nie")))
                .andExpect(jsonPath("$[1].workingOffice", is("Chengdu")));
    }

    @Test
    public void should_be_able_to_return_people_ordered_by_full_name_when_receive_request_of_get_people_with_value_full_name_of_parameter_order_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .param("order_by", Filter.FULL_NAME.getValue())
                .content(json(names)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shortName", is("bwang")))
                .andExpect(jsonPath("$[0].fullName", is("Bing Wang")))
                .andExpect(jsonPath("$[0].workingOffice", is("Chengdu")))
                .andExpect(jsonPath("$[1].shortName", is("glnie")))
                .andExpect(jsonPath("$[1].fullName", is("Guiling Nie")))
                .andExpect(jsonPath("$[1].workingOffice", is("Chengdu")));
    }

    @Test
    public void should_be_able_to_return_people_ordered_by_working_office_when_receive_request_of_get_people_with_value_working_office_of_parameter_order_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .param("order_by", Filter.WORKING_OFFICE.getValue())
                .content(json(names)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].shortName", is("hwen")))
                .andExpect(jsonPath("$[0].fullName", is("Hao Wen")))
                .andExpect(jsonPath("$[0].workingOffice", is("Beijing")))
                .andExpect(jsonPath("$[1].shortName", is("bwang")))
                .andExpect(jsonPath("$[1].fullName", is("Bing Wang")))
                .andExpect(jsonPath("$[1].workingOffice", is("Chengdu")))
                .andExpect(jsonPath("$[2].shortName", is("glnie")))
                .andExpect(jsonPath("$[2].fullName", is("Guiling Nie")))
                .andExpect(jsonPath("$[2].workingOffice", is("Chengdu")));

    }

    //    @Test(expected = RequestParameterValueUnsupportedException.class)
    @Test(expected = NestedServletException.class)
    public void should_be_able_to_return_bad_request_when_receive_request_of_get_people_with_unsupported_value_of_parameter_order_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        mockMvc.perform(post("/people")
                .param("order_by", "loginName")
                .header("content-type", "application/json")
                .content(json(names)));
//                .andExpect(status().isInternalServerError());
    }

    @Test
    public void should_be_able_to_return_people_group_by_working_office_when_receive_request_of_get_people_with_value_working_office_of_parameter_group_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .param("group_by", Filter.WORKING_OFFICE.getValue())
                .content(json(names)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].workingOffice", is("Beijing")))
                .andExpect(jsonPath("$[0].peopleCount", is(1)))
                .andExpect(jsonPath("$[0].people", hasSize(1)))
                .andExpect(jsonPath("$[0].people[0].shortName", is("hwen")))
                .andExpect(jsonPath("$[0].people[0].fullName", is("Hao Wen")))
                .andExpect(jsonPath("$[1].workingOffice", is("Chengdu")))
                .andExpect(jsonPath("$[1].peopleCount", is(2)))
                .andExpect(jsonPath("$[1].people", hasSize(2)))
                .andExpect(jsonPath("$[1].people[0].shortName", is("bwang")))
                .andExpect(jsonPath("$[1].people[0].fullName", is("Bing Wang")))
                .andExpect(jsonPath("$[1].people[1].shortName", is("glnie")))
                .andExpect(jsonPath("$[1].people[1].fullName", is("Guiling Nie")));
    }

    @Test(expected = NestedServletException.class)
    public void should_be_able_to_return_bad_request_when_receive_request_of_get_people_with_unsupported_value_of_parameter_group_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .param("group_by", Filter.FULL_NAME.getValue())
                .content(json(names)));
//                .andExpect(status().isBadRequest());
    }

    private String json(Object object) throws IOException
    {
        return new ObjectMapper().writeValueAsString(object);
    }
}
