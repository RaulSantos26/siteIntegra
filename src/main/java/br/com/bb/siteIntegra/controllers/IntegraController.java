package br.com.bb.siteIntegra.controllers;

import br.com.bb.siteIntegra.dto.DadosResgateDTO;
import br.com.bb.siteIntegra.dto.PesquisaDTO;
import br.com.bb.siteIntegra.entities.TipoPessoa;
import br.com.bb.siteIntegra.services.DadosResgateService;
import br.com.bb.siteIntegra.services.FinalidadeService;
import br.com.bb.siteIntegra.services.PesquisaService;
import br.com.bb.siteIntegra.services.exceptions.DatabaseException;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;

@Controller
public class IntegraController {

    @Autowired
    private PesquisaService pesquisaService;

    @Autowired
    private FinalidadeService finalidadeService;


    @GetMapping("/")
    public ModelAndView site(@Valid PesquisaDTO dto,  BindingResult bindingResult) {
        DadosResgateDTO dadosResgateDTO = new DadosResgateDTO();
        if (! bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("pesquisa", dto);
            mv.addObject("tipoPessoa", TipoPessoa.values());
            mv.addObject("dadosResgateDTO", dadosResgateDTO);
            mv.addObject("hide", 1);
            return mv;
        }
        return null;
    }

    @PostMapping("integra")
    public ModelAndView pesquisa(@Valid PesquisaDTO dto, BindingResult bindingResult) {
        DadosResgateDTO dadosResgateDTO = new DadosResgateDTO();

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
                ModelAndView mv = new ModelAndView("integra");
                mv.addObject("hide", 0);
                mv.addObject("aof", result);
                mv.addObject("tipoPessoa", TipoPessoa.values());
                mv.addObject("finalidades", finalidadeService.findAll());
                mv.addObject("dadosResgateDTO", dadosResgateDTO);
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
