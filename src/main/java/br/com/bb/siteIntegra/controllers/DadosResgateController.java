package br.com.bb.siteIntegra.controllers;


import br.com.bb.siteIntegra.dto.DadosResgateDTO;
import br.com.bb.siteIntegra.entities.TipoPessoa;
import br.com.bb.siteIntegra.services.DadosResgateService;
import br.com.bb.siteIntegra.services.FinalidadeService;
import br.com.bb.siteIntegra.services.PesquisaService;
import br.com.bb.siteIntegra.services.exceptions.ResourcesNotFoudException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DadosResgateController {

    @Autowired
    private DadosResgateService service;

    @Autowired
    private PesquisaService pesquisaService;

    @Autowired
    private FinalidadeService finalidadeService;

//
//    @PostMapping("regaste")
//    public ModelAndView insert(@Valid DadosResgateDTO dto, BindingResult bindingResult){
//
//            if (bindingResult.hasErrors()) {
////                String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
//                String message = "******Tem erros******";
////                System.out.println("\n******Tem erros******\n");
//                ModelAndView mv = new ModelAndView("integra");
//                mv.addObject("hide", 3);
//                mv.addObject("pesquisa", dto);
//                mv.addObject("finalidades", finalidadeService.findAll());
//                mv.addObject("tipoPessoa", TipoPessoa.values());
//                return mv;
//            }
//            else {
//                try {
//                    dto = service.insert(dto);
//
//                } catch (ResourcesNotFoudException erro) {
//                    String sucesso = "Teve sucesso";
//                    ModelAndView mv = new ModelAndView("integra");
//                    mv.addObject("erro", erro.getMessage());
//                    mv.addObject("hide", 0);
//                    return mv;
//
//
//                }
//            }
//        return null;
//    }

//    @GetMapping("resgate")
//    public ModelAndView site(@Valid DadosResgateDTO dto, BindingResult bindingResult) {
//        if (! bindingResult.hasErrors()) {
//            ModelAndView mv = new ModelAndView("integra");
//            mv.addObject("pesquisa", dto);
//
//            mv.addObject("hide", 3);
//
//            return mv;
//        }
//        return null;
//    }


//    @PostMapping("regaste")
//    public ModelAndView insert(@Valid DadosResgateDTO dto, @NotNull BindingResult bindingResult)  {
//        if (bindingResult.hasErrors()) {
//
//            ModelAndView mv = new ModelAndView("resgate");
//            mv.addObject("hide", 3);
//            mv.addObject("pesquisa", dto);
//            mv.addObject("tipoPessoa", TipoPessoa.values());
//            mv.addObject("finalidades", finalidadeService.findAll());
//
//            return mv;
//        }
//        else {
//            try {
//                dto = service.insert(dto);
//
//            } catch (ResourcesNotFoudException erro) {
//                String sucesso = "Teve sucesso";
//                ModelAndView mv = new ModelAndView("resgate");
//                mv.addObject("erro", erro.getMessage());
//                mv.addObject("hide", 0);
//                mv.addObject("finalidades", finalidadeService.findAll());
//                return mv;
//
//
//            }
//        }
//        return null;
//    }




}

