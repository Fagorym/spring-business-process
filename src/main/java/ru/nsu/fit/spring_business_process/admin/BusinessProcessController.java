package ru.nsu.fit.spring_business_process.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BusinessProcessController {
    private final AdminBusinessProcessFacade adminBusinessProcessFacade;

    @GetMapping("/processes")
    public ModelAndView getBusinessProcessDetails() {
        return adminBusinessProcessFacade.getAll();
    }

    @GetMapping("/processes/{id}")
    public ModelAndView getBusinessProcessDetails(@PathVariable Long id) {
        return adminBusinessProcessFacade.getDetail(id);
    }
}
