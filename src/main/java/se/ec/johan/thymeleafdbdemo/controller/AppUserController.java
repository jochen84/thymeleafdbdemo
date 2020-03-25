package se.ec.johan.thymeleafdbdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.ec.johan.thymeleafdbdemo.data.AppUserRepository;
import se.ec.johan.thymeleafdbdemo.dto.CreateAppUserForm;
import se.ec.johan.thymeleafdbdemo.dto.UppdateAppUserForm;
import se.ec.johan.thymeleafdbdemo.entity.AppUser;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class AppUserController {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    //                            domännamnet   /   resurs på domän
    ///   /users/register/form == localhost:8080/users/register/form
    @GetMapping("/users/register/form")
    public String getForm(Model model){
        model.addAttribute("form", new CreateAppUserForm());
        return "user-form";
    }

    @PostMapping("/users/register/process")
    public String formProcess(@Valid @ModelAttribute("form") CreateAppUserForm form, BindingResult bindingResult){

        if (appUserRepository.findByEmailIgnoreCase(form.getEmail()).isPresent()){
            FieldError error = new FieldError("form", "email", "Mail is already in use");
            bindingResult.addError(error);
        }
        if (!form.getPassword().equals(form.getPasswordConfirm())){
            FieldError error = new FieldError("form", "passwordConfirm", "You passwords doesn't match");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()){
            return "user-form";
        }
        AppUser user = appUserRepository.save(new AppUser(form.getFirstName(),form.getLastName(),form.getPassword(),form.getEmail(), LocalDate.now()));

        System.err.println(form.getEmail());
        System.err.println(form.getFirstName());
        System.err.println(form.getLastName());
        System.err.println(form.getPassword());
        System.err.println(form.getPasswordConfirm());
        return "redirect:/users/"+ user.getUserId();
    }

    @GetMapping("users/{id}")
    public String getUserView(@PathVariable(name = "id") int id, Model model){
    AppUser appUser = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    model.addAttribute("user", appUser);
    return "user-view";
    }

    @GetMapping("users/{id}/update")
    public String getUpdateForm(@PathVariable("id") int id, Model model){
        UppdateAppUserForm appUserForm = new UppdateAppUserForm();
        AppUser appUser = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        appUserForm.setEmail(appUser.getEmail());
        appUserForm.setFirstName(appUser.getFirstName());
        appUserForm.setLastName(appUser.getLastName());
        appUserForm.setUserId(appUser.getUserId());
        model.addAttribute("form", appUserForm);

        return "update-form";
    }

    @PostMapping("users/{id}/update")
    public String processUpdate(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("form") UppdateAppUserForm form,
            BindingResult bindingResult
    ){
        AppUser original = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (appUserRepository.findByEmailIgnoreCase(form.getEmail()).isPresent() && !form.getEmail().equals(original.getEmail())){
            FieldError error = new FieldError("form", "email", "Email is already in use");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()){
            return "update-form";
        }

        original.setEmail(form.getEmail());
        original.setFirstName(form.getFirstName());
        original.setLastName(form.getLastName());

        appUserRepository.save(original);

        System.err.println(id);
        System.err.println(form.getUserId());
        System.err.println(form.getFirstName());
        System.err.println(form.getLastName());
        System.err.println(form.getEmail());

        return "redirect:/users/" + original.getUserId();
    }
}
