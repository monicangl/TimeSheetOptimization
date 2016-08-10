package com.github.monicangl.peoplesystem.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.monicangl.peoplesystem.domain.Office;
import com.github.monicangl.peoplesystem.domain.PeopleBasicInfo;
import com.github.monicangl.peoplesystem.service.Filter;
import com.github.monicangl.peoplesystem.service.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleApi
{
    private PeopleService peopleService;

    @Autowired
    public PeopleApi(PeopleService peopleService)
    {
        this.peopleService = peopleService;
    }

    //    @RequestMapping(method = RequestMethod.POST, params = {})
//    public ResponseEntity<List<PeopleBasicInfo>> getPeopleList(@RequestBody List<String> names)
//    {
//        List<PeopleBasicInfo> people = peopleService.getPeopleList(names
//                , Filter.FULL_NAME.getValue());
//        return new ResponseEntity<>(people, HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, params = {"order_by"})
//    public ResponseEntity getPeopleList(@RequestBody List<String> names
//            , @RequestParam(value = "order_by") String order)
//    {
//        List<PeopleBasicInfo> people = peopleService.getPeopleList(names, order);
//        return new ResponseEntity<>(people, HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, params = {"group_by"})
//    public ResponseEntity<List<Office>> getPeopleGroup(@RequestBody List<String> names
//            , @RequestParam(value = "group_by") String group)
//    {
//        List<Office> people = peopleService.getPeopleGroup(names, group);
//        return new ResponseEntity<>(people, HttpStatus.OK);
//    }
    @RequestMapping(method = RequestMethod.POST, params = {})
    public String getPeopleList(@RequestBody List<String> names, Model model)
    {
        List<PeopleBasicInfo> people = peopleService.getPeopleList(names
                , Filter.FULL_NAME.getValue());
        model.addAttribute("people", people);
        return "people/list";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"order_by"})
    public String getPeopleList(@RequestBody List<String> names
            , @RequestParam(value = "order_by") String order, Model model)
    {
        List<PeopleBasicInfo> people = peopleService.getPeopleList(names, order);
        model.addAttribute("people", people);
        return "people/list";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"group_by"})
    public String getPeopleGroup(@RequestBody List<String> names
            , @RequestParam(value = "group_by") String group, Model model)
    {
        List<Office> people = peopleService.getPeopleGroup(names, group);
        model.addAttribute("people", people);
        return "people/group";
    }
}
