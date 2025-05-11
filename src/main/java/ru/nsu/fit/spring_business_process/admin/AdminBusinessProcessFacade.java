package ru.nsu.fit.spring_business_process.admin;

import jakarta.annotation.Nonnull;
import org.springframework.web.servlet.ModelAndView;

public interface AdminBusinessProcessFacade {

    /**
     * Получить все бизнес-процессы.
     */
    @Nonnull
    ModelAndView getAll();

    /**
     * Получить детальную информацию о бизнес-процессе.
     *
     * @param id идентификатор бизнес-процесса.
     */
    @Nonnull
    ModelAndView getDetail(Long id);
}
