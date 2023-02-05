package br.com.bb.siteIntegra.controllers;

import br.com.bb.siteIntegra.dto.DadosResgateDTO;
import br.com.bb.siteIntegra.dto.PesquisaDTO;
import br.com.bb.siteIntegra.entities.TipoPessoa;
import br.com.bb.siteIntegra.services.DadosResgateService;
import br.com.bb.siteIntegra.services.FinalidadeService;
import br.com.bb.siteIntegra.services.PesquisaService;
import br.com.bb.siteIntegra.services.exceptions.DatabaseException;
import br.com.bb.siteIntegra.services.exceptions.ResourcesNotFoudException;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
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

    @Autowired
    private DadosResgateService dadosResgateService;


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
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("hide", 1);
            mv.addObject("pesquisa", dto);

            return mv;
        } else {
            try {
                PesquisaDTO result = dadosDto(dto);
                System.out.println("####" + result + "####");
                ModelAndView mv = new ModelAndView("integra");
                mv.addObject("hide", 0);
                mv.addObject("aof", result);
                mv.addObject("tipoPessoa", TipoPessoa.values());
                mv.addObject("finalidades", finalidadeService.findAll());
                mv.addObject("dadosResgateDTO", dadosResgateDTO);

                return mv;
            } catch (DatabaseException erro) {
                ModelAndView mv = new ModelAndView("index");
                mv.addObject("erro", erro.getMessage());
                mv.addObject("hide", 1);
                return mv;

            }
        }
    }

    @PostMapping("regaste")
    public ModelAndView insert(@Valid DadosResgateDTO dto, BindingResult bindingResult){
        PesquisaDTO result = pesquisaService.findById(dto.getId());
        ModelAndView mv = new ModelAndView("integra");
        if (bindingResult.hasErrors()) {
            mv.addObject("hide", 3);
            mv.addObject("pesquisa", dto);
            mv.addObject("finalidades", finalidadeService.findAll());
            mv.addObject("aof", result);
            mv.addObject("tipoPessoa", TipoPessoa.values());
            return mv;
        }
        else {
            try {
                dadosResgateService.insert(dto);

                return mv;

            } catch (ResourcesNotFoudException erro) {
                mv.addObject("erro", erro.getMessage());
                mv.addObject("hide", 0);
                return mv;
            }
        }
    }




    @GetMapping("/docpreview")
    public String docPreview() {

        return "docpreview";
    }

    private PesquisaDTO dadosDto(PesquisaDTO dto){
        if (dto.getJudicial() != null) {
            dto = pesquisaService.findBycontaJudicial(dto.getJudicial());
        } else {
            dto = pesquisaService.findBygsv(dto.getGsv());
        }
        return dto;
    }
}
