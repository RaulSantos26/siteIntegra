package br.com.bb.siteIntegra.controllers;

import br.com.bb.siteIntegra.dto.PesquisaDTO;
import br.com.bb.siteIntegra.entities.TipoPessoa;
import br.com.bb.siteIntegra.services.FinalidadeService;
import br.com.bb.siteIntegra.services.PesquisaService;
import br.com.bb.siteIntegra.services.exceptions.DatabaseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IntegraController {

    @Autowired
    private PesquisaService pesquisaService;

    @Autowired
    private FinalidadeService finalidadeService;

    @GetMapping("/")
    public ModelAndView site(@Valid PesquisaDTO dto,  BindingResult bindingResult) {
        if (! bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("pesquisa", dto);
            mv.addObject("tipoPessoa", TipoPessoa.values());
            mv.addObject("hide", 1);
            return mv;
        }
        return null;
    }

    @PostMapping("/")
    public ModelAndView pesquisa(@Valid PesquisaDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            System.out.println("\n******Tem erros******\n");
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("hide", 1);
            mv.addObject("pesquisa", dto);
            return mv;
        } else {
            try {
                PesquisaDTO result;
                if (dto.getJudicial() != null) {
                    result = pesquisaService.findBycontaJudicial(dto.getJudicial());
                } else {
                    result = pesquisaService.findBygsv(dto.getGsv());
                }


                System.out.println("####" + result + "####");
                ModelAndView mv = new ModelAndView("index");
                mv.addObject("hide", 0);
                mv.addObject("aof", result);
                mv.addObject("tipoPessoa", TipoPessoa.values());
                mv.addObject("finalidades", finalidadeService.findAll());
//            System.out.println("$$$$ " + dto.getContaJudicial() + "$$$$");

                return mv;
            } catch (DatabaseException erro) {
                ModelAndView mv = new ModelAndView("index");
                mv.addObject("erro", erro.getMessage());
                mv.addObject("hide", 1);
                return mv;

            }
        }
    }




    @GetMapping("/docpreview")
    public String docPreview() {

        return "docpreview";
    }
}
