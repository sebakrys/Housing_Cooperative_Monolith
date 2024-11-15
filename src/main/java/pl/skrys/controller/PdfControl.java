package pl.skrys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.skrys.app.SpUserApp;
import pl.skrys.service.PdfService;
import pl.skrys.service.SpFlatChargesService;
import pl.skrys.service.SpUserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PdfControl {

    private PdfService pdfService;
    private SpUserService userAppService;
    private SpFlatChargesService flatChargesService;

    @Autowired
    public PdfControl(PdfService pdfService, SpUserService userAppService, SpFlatChargesService flatChargesService) {
        this.pdfService = pdfService;
        this.userAppService = userAppService;
        this.flatChargesService = flatChargesService;
    }




    @RequestMapping(value = "/generatePdf-{userAppId}", method = RequestMethod.GET)
    public void generatePdf(@PathVariable Integer userAppId, HttpServletResponse response){
        pdfService.generatePdf(userAppService.getUserApp(userAppId), response);
    }


    @RequestMapping(value = "/rachunek{r}_{userId}_{rachunekID}", method = RequestMethod.GET)
    public void generateRachunekPdf(@PathVariable Integer userId, @PathVariable Integer rachunekID, @PathVariable char r, HttpServletResponse response){

        List<SpUserApp> userAppList = userAppService.getUserAppByFlat(flatChargesService.getFlatCharges(rachunekID).getFlat().getId());
        boolean locatorValid = false;

        for (SpUserApp user:
        userAppList) {
            if(user.getId()==userId){
                locatorValid=true;
            }
        }

        boolean ryczalt = false;

        if(r=='r'){
            System.out.println("RAchunek z ryczaltem");
            ryczalt = true;
        }

        if(locatorValid){
            pdfService.generateRachunekPdf(flatChargesService.getFlatCharges(rachunekID), userAppService.getUserAppByFlat(flatChargesService.getFlatCharges(rachunekID).getFlat().getId()),  userAppService.getUserApp(userId), ryczalt,  response);

        }else{
            System.out.println("BRAK DOSTEPU bledne dane rachunku");
            //TODO return "redirect:accessDenied.html";
        }

    }


}
