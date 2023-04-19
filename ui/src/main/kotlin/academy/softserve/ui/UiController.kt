package academy.softserve.ui

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@SessionAttributes("name")
class UiController(
        private val messageService: MessageService,
        private val guestbookService: GuestbookService
) {

    @GetMapping("/")
    fun index(model: Model): String {
        if (model.containsAttribute("name")) {
            val name = model.asMap()["name"] as String
            val message = messageService.message(name)
            model.addAttribute("greeting", message)
        }
        model.addAttribute("messages", guestbookService.all())
        return "index"
    }

    @PostMapping("/greet")
    fun greet(@RequestParam name: String, @RequestParam message: String?, model: Model): String {
        model.addAttribute("name", name)
        message?.let {
            if (it.trim().isNotBlank()) guestbookService.add(name, message)
        }
        return "redirect:/"
    }

}