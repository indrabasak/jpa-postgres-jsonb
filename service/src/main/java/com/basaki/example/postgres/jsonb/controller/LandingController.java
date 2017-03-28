package com.basaki.example.postgres.jsonb.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * {@code LandingController} is the REST controller for redirecting the home
 * page to swagger UI. </p>
 *
 * @author Indra Basak
 * @since 2/23/17
 */
@RestController
@ApiIgnore
public class LandingController {

    @RequestMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
