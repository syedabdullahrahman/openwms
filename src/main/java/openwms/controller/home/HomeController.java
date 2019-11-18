package openwms.controller.home;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String viewWelcomePage(Model model) {

		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

		model.addAttribute("dateDec", day + "." + month + "." + year);
		model.addAttribute("dateBin",
				Integer.toBinaryString(day) + "." + Integer.toBinaryString(month) + "." + Integer.toBinaryString(year));
		model.addAttribute("dateHex",
				Integer.toHexString(day) + "." + Integer.toHexString(month) + "." + Integer.toHexString(year));
		model.addAttribute("dayOfYearDec", dayOfYear);
		model.addAttribute("dayOfYearBin", Integer.toBinaryString(dayOfYear));
		model.addAttribute("dayOfYearHex", Integer.toHexString(dayOfYear));

		model.addAttribute("text", "$@*&#@*#(");

		return "home/index";
	}

	@RequestMapping("/user/exec")
	public String viewExecPage(Model model) {
		return "home/exec";
	}
	
	@RequestMapping("/admin/manage")
	public String viewManagePage(Model model) {
		return "home/manage";
	}
	
	@RequestMapping("/err")
	public String viewErrorPage(Model model) throws Exception {
		throw new Exception("Unknown (Fake) Exception");
	}	
}