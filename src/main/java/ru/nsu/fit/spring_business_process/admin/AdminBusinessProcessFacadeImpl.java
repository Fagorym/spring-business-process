package ru.nsu.fit.spring_business_process.admin;

import java.util.List;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.service.BusinessProcessService;

@Component
@RequiredArgsConstructor
public class AdminBusinessProcessFacadeImpl implements AdminBusinessProcessFacade {
    private final BusinessProcessService businessProcessService;

    @Nonnull
    @Override
    public ModelAndView getAll() {
        List<BusinessProcess> processes = businessProcessService.findAll();

        ModelAndView modelAndView = new ModelAndView("processes/list");
        modelAndView.addObject("processes", processes);
        return modelAndView;
    }

    @Nonnull
    @Override
    public ModelAndView getDetail(Long id) {
        BusinessProcess process = businessProcessService.getById(id);

        ModelAndView modelAndView = new ModelAndView("processes/details");
        modelAndView.addObject("process", process);
        modelAndView.addObject("currentStage", process.getStage());
        modelAndView.addObject("payloads", process.getPayloads());

        return modelAndView;
    }
}
