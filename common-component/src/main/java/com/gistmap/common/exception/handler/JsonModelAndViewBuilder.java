package com.gistmap.common.exception.handler;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


public class JsonModelAndViewBuilder {

    public static ModelAndView build(Object object) {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView mav = new ModelAndView(view);
        mav.addObject(object);
        return mav;
    }
}
