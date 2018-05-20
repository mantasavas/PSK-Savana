package lt.vu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LockController {

	@RequestMapping("/lock")
	public String lock(@ModelAttribute("lockEntity") final Object lockEntity) {
		return "lock";
	}
}
