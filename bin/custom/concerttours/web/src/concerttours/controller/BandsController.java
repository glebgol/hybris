package concerttours.controller;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bands")
public class BandsController {
    private final BandFacade bandFacade;

    public BandsController(BandFacade bandFacade) {
        this.bandFacade = bandFacade;
    }

    @GetMapping("/{name}")
    public String getBand(@PathVariable String name, Model model) {
        if (name == null || name.isBlank()) {
            return "404";
        }

        try {
            BandData band = bandFacade.getBand(name);
            model.addAttribute("band", band);
        } catch (RuntimeException e) {
            return "404";
        }

        return "BandDetails";
    }

    @GetMapping
    public String getBands(Model model) {
        model.addAttribute("bands", bandFacade.getBands());
        return "theBandList";
    }
}
