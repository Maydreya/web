package com.may.dft.controller;

import com.may.dft.domain.Bicycles;
import com.may.dft.domain.Cart;
import com.may.dft.repos.BicycleRepos;
import com.may.dft.repos.CartRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class WelcomeController {

    // inject via application.properties

    ArrayList<String> bicycles_list;
    ArrayList<Integer> cost_list;

    @Autowired
    private BicycleRepos bicycleRepos;
    @Autowired
    private CartRepos cartRepos;

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Bicycles> bicycles = bicycleRepos.findAll();

        model.put("bicycle", bicycles);

        return "welcome";
    }

    @PostMapping("/")
    public @ResponseBody
    String addNewItem(@RequestParam String name, @RequestParam Integer cost, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        bicycles_list = (ArrayList<String>) session.getAttribute("bicycles_list");
        cost_list = (ArrayList<Integer>) session.getAttribute("cost_list");
        if (bicycles_list == null && cost_list == null) {
            bicycles_list = new ArrayList<>();
            cost_list = new ArrayList<>();
        }
        assert bicycles_list != null;
        bicycles_list.add(name);
        cost_list.add(cost);
        session.setAttribute("bicycles_list", bicycles_list);
        session.setAttribute("cost_list", cost_list);
        return "welcome";
    }

    @PostMapping("/cart")
    public @ResponseBody
    String deleteItem(@RequestParam int id, Map<String, Object> model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        bicycles_list = (ArrayList<String>) session.getAttribute("bicycles_list");
        cost_list = (ArrayList<Integer>) session.getAttribute("cost_list");
        bicycles_list.remove(id);
        cost_list.remove(id);
        session.setAttribute("bicycles_list", bicycles_list);
        session.setAttribute("cost_list", cost_list);

        return "cart";
    }
    @PostMapping("/addbase")
    public @ResponseBody
    String addbase() {
        for(int i = 0; i < bicycles_list.size(); i++)
        {
            Cart cart = new Cart();
            cart.setName(bicycles_list.get(i));
            cart.setPrice(cost_list.get(i));
            cartRepos.save(cart);
        }
        return "base";
    }

    @GetMapping("/cart")
    public String maincart(Map<String, Object> model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<String> carts = (ArrayList<String>) session.getAttribute("bicycles_list");
        ArrayList<Integer> costs = (ArrayList<Integer>) session.getAttribute("cost_list");
        model.put("carts", carts);
        model.put("costs", costs);

        return "cart";
    }
}