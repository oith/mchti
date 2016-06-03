package mchti.controller;

import mchti.model.hcm.tl.Lookup;
import mchti.dto._SearchDTO;
import mchti.exception.LookupNotFoundException;
import mchti.service.LookupService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/lookup")
public class LookupController extends _OithController {

    protected static final String MODEL = "lookup";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private LookupService lookupService;

    protected void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new Lookup());
        return CREATE;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid Lookup currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            Lookup lookup = lookupService.create(currObject);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, lookup.getCode());
            return "redirect:/" + SHOW + "/" + lookup.getId();
        } else {
            return CREATE;
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attributes) {

        Lookup lookup = lookupService.findById(id);

        if (lookup == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, lookup);
        return EDIT;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid Lookup currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return EDIT;
        }

        try {
            Lookup lookup = lookupService.update(currObject);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, lookup.getCode());
        } catch (LookupNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
        }
        return "redirect:/" + SHOW + "/" + currObject.getId();
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attributes) {

        Lookup lookup = lookupService.findById(id);

        if (lookup == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, lookup);
        return COPY;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.POST)
    public String copied(@PathVariable("id") Long id, @ModelAttribute(MODEL) @Valid Lookup currObject, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return COPY;
        }

        try {
            Lookup lookup = lookupService.copy(currObject);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, lookup.getCode());
            return "redirect:/" + SHOW + "/" + lookup.getId();
        } catch (Exception e) {
            errorHandler(bindingResult, e);
            return COPY;
        }
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
//        _SearchDTO searchCriteria = new _SearchDTO();
//        searchCriteria.setPage(1);
//        searchCriteria.setPageSize(5);

        List<Lookup> lookups = lookupService.findAll();//searchCriteria

        model.addAttribute(MODELS, lookups);
//        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
//
//        List<Integer> pages = new ArrayList<>();
//        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
//            pages.add(i);
//        }
//        model.addAttribute("pages", pages);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {

        //String searchTerm = searchCriteria.getSearchTerm();
        List<Lookup> lookups;

//        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
//            lookups = lookupService.search(searchCriteria);
//        } else {
        lookups = lookupService.findAll();//searchCriteria
        // }
        model.addAttribute(MODELS, lookups);
//        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
//
//        List<Integer> pages = new ArrayList<>();
//        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
//            pages.add(i);
//        }
//        model.addAttribute("pages", pages);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showForm(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attributes) {

        Lookup lookup = lookupService.findById(id);

        if (lookup == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, lookup);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {

        try {
            Lookup deleted = lookupService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (LookupNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
