package br.com.bb.siteIntegra.controllers;

import br.com.bb.siteIntegra.dto.DadosResgateDTO;
import br.com.bb.siteIntegra.dto.FinalidadeDTO;
import br.com.bb.siteIntegra.dto.PesquisaDTO;
import br.com.bb.siteIntegra.dto.ProtocolosDTO;
import br.com.bb.siteIntegra.entities.TipoPessoa;
import br.com.bb.siteIntegra.services.DadosResgateService;
import br.com.bb.siteIntegra.services.FinalidadeService;
import br.com.bb.siteIntegra.services.PesquisaService;
import br.com.bb.siteIntegra.services.ProtocolosService;
import br.com.bb.siteIntegra.services.exceptions.DatabaseException;
import br.com.bb.siteIntegra.services.exceptions.ResourcesNotFoudException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes({"dadosResgateDTO", "tipoPessoa", "pesquisa", "finalidades", "protocolos"})
public class IntegraController {

    @Autowired
    private PesquisaService pesquisaService;

    @Autowired
    private FinalidadeService finalidadeService;

    @Autowired
    private DadosResgateService dadosResgateService;

    @Autowired
    private ProtocolosService protocolosService;

    @ModelAttribute("pesquisa")
    public PesquisaDTO pesquisaDTO() {
        return new PesquisaDTO();
    }

    @ModelAttribute("dadosResgateDTO")
    public DadosResgateDTO dadosResgateDTO() {
        return new DadosResgateDTO();
    }

    @ModelAttribute("finalidades")
    public List<FinalidadeDTO> finalidadeDTOList() {
        List<FinalidadeDTO> finalidadeDTOList = finalidadeService.findAll();
        return finalidadeDTOList;

    }

    @ModelAttribute("protocolos")
    public List<ProtocolosDTO> protocolosDTOList(){
        List<ProtocolosDTO> protocolosDTOList = protocolosService.findAll();
//        System.out.println(protocolosDTOList);
        return protocolosDTOList;
    }


    @ModelAttribute("tipoPessoa")
    public TipoPessoa[] tipoPessoa() {
        return TipoPessoa.values();
    }

    @GetMapping("/")
    public ModelAndView site(PesquisaDTO dto) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("hide", 1);
        return mv;

    }

    @PostMapping("integra")
    public ModelAndView pesquisa(@Valid PesquisaDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("hide", 1);
            return mv;
        } else {
            try {
                PesquisaDTO result = dadosDto(dto);
                System.out.println("####" + result + "####");
                ModelAndView mv = new ModelAndView("integra");
                mv.addObject("hide", 0);
                mv.addObject("aof", result);
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
    public ModelAndView insert(@Valid DadosResgateDTO dto, BindingResult bindingResult) {
        PesquisaDTO result = pesquisaService.findById(dto.getId());
        ModelAndView mv = new ModelAndView("integra");
        if (bindingResult.hasErrors()) {
            mv.addObject("hide", 3);
            mv.addObject("aof", result);

            return mv;
        } else {
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

    private PesquisaDTO dadosDto(PesquisaDTO dto) {
        if (dto.getJudicial() != null) {
            dto = pesquisaService.findBycontaJudicial(dto.getJudicial());
        } else {
            dto = pesquisaService.findBygsv(dto.getGsv());
        }
        return dto;
    }
}
