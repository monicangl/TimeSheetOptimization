package com.github.monicangl.peoplesystem.api;

import static com.github.monicangl.peoplesystem.service.Filter.FULL_NAME;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.monicangl.peoplesystem.model.People;
import com.github.monicangl.peoplesystem.repository.PeopleRepository;
import com.github.monicangl.peoplesystem.service.Filter;
import com.github.monicangl.peoplesystem.service.PeopleService;

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
                .andExpect(view().name("people/list"))
//                .andExpect(forwardedUrl("/META-INF/resources/WEB-INF/jsp/people/list.jsp"))
                .andExpect(model().attribute("people", hasSize(2)))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("bwang")),
                                hasProperty("fullName", is("Bing Wang")),
                                hasProperty("workingOffice", is("Chengdu"))
                        )
                )))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("glnie")),
                                hasProperty("fullName", is("Guiling Nie")),
                                hasProperty("workingOffice", is("Chengdu"))
                        )
                )));
    }

    @Test
    public void should_be_able_to_return_people_ordered_by_full_name_when_receive_request_of_get_people_with_value_full_name_of_parameter_order_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .param("order_by", FULL_NAME.getValue())
                .content(json(names)))
                .andExpect(status().isOk())
                .andExpect(view().name("people/list"))
                .andExpect(model().attribute("people", hasSize(2)))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("bwang")),
                                hasProperty("fullName", is("Bing Wang")),
                                hasProperty("workingOffice", is("Chengdu"))
                        )
                )))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("glnie")),
                                hasProperty("fullName", is("Guiling Nie")),
                                hasProperty("workingOffice", is("Chengdu"))
                        )
                )));
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
                .andExpect(view().name("people/list"))
                .andExpect(model().attribute("people", hasSize(3)))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("bwang")),
                                hasProperty("fullName", is("Bing Wang")),
                                hasProperty("workingOffice", is("Chengdu"))
                        )
                )))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("glnie")),
                                hasProperty("fullName", is("Guiling Nie")),
                                hasProperty("workingOffice", is("Chengdu"))
                        )
                )))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("shortName", is("hwen")),
                                hasProperty("fullName", is("Hao Wen")),
                                hasProperty("workingOffice", is("Beijing"))
                        )
                )));
    }

    @Test
    public void should_be_able_to_return_bad_request_when_receive_request_of_get_people_with_unsupported_value_of_parameter_order_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        mockMvc.perform(post("/people")
                .param("order_by", "loginName")
                .header("content-type", "application/json")
                .content(json(names)))
                .andExpect(status().isBadRequest());
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
                .andExpect(view().name("people/group"))
                .andExpect(model().attribute("people", hasSize(2)))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("name", is("Beijing")),
                                hasProperty("peopleCount", is(1)),
                                hasProperty("people", hasSize(1)),
                                hasProperty("people", hasItem(
                                        allOf(
                                                hasProperty("shortName", is("hwen")),
                                                hasProperty("fullName", is("Hao Wen"))
                                        )
                                ))
                        )
                )))
                .andExpect(model().attribute("people", hasItem(
                        allOf(
                                hasProperty("name", is("Chengdu")),
                                hasProperty("peopleCount", is(2)),
                                hasProperty("people", hasSize(2)),
                                hasProperty("people", hasItem(
                                        allOf(
                                                hasProperty("shortName", is("bwang")),
                                                hasProperty("fullName", is("Bing Wang"))
                                        )
                                )),
                                hasProperty("people", hasItem(
                                        allOf(
                                                hasProperty("shortName", is("glnie")),
                                                hasProperty("fullName", is("Guiling Nie"))

                                        ))
                )))));
    }

    @Test
    public void should_be_able_to_return_bad_request_when_receive_request_of_get_people_with_unsupported_value_of_parameter_group_by() throws Exception
    {
        // given
        List<String> names = newArrayList("glnie", "bwang", "hwen");

        // when
        mockMvc.perform(post("/people")
                .header("content-type", "application/json")
                .param("group_by", FULL_NAME.getValue())
                .content(json(names)))
                .andExpect(status().isBadRequest());
    }

    private String json(Object object) throws IOException
    {
        return new ObjectMapper().writeValueAsString(object);
    }
}
