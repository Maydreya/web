package com.may.dft.controller;

import javafx.scene.control.TextInputControl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties

    private List<String> bicycles  = Arrays.asList("Forward Flash 26", "Forward Next 29", "Forward Sporting 27,5", "Forward Apache 27,5");
    private List<String> prices = Arrays.asList("13930", "25570", "15090", "17370");
    private List<String> sales = Arrays.asList("no", "no", "no", "no");

    @GetMapping("/")
    public String main(Model model, @RequestParam(required=false) boolean success)
    {
        if (success)
        {
            model.addAttribute("result", "Покупка произведена");
        }

        List<String> filteredBicycles = new ArrayList<>();
        List<String> filteredPrices = new ArrayList<>();

        for (int i = 0; i < bicycles.size(); i++)
        {
            if (sales.get(i).equals("no"))
            {
                filteredBicycles.add(bicycles.get(i));
                filteredPrices.add(prices.get(i));
            }
        }

        model.addAttribute("bicycles", filteredBicycles);
        model.addAttribute("prices", filteredPrices);

        return "welcome";
    }

    @GetMapping("/sale/{number}")
    public String sale(Model model, @PathVariable("number") int number)
    {
        model.addAttribute("bicyclePrice", prices.get(number));
        model.addAttribute("bicycleId", number);
        return "sale";
    }

    // /hello?name=kotlin
    @PostMapping("/sale/{bicycleId}")
    public String rentPost(@PathVariable("bicycleId") int id)
    {
        sales.set(id, "yes");
        return "redirect:/?success=true";
    }

}