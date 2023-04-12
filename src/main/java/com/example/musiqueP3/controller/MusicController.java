package com.example.musiqueP3.controller;

import com.example.musiqueP3.entities.Music;
import com.example.musiqueP3.form.CreateMusicForm;
import com.example.musiqueP3.repository.MusicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@Controller
public class MusicController implements WebMvcConfigurer {
    @Autowired
    private MusicRepository musicRepository;
    @RequestMapping("/")
    public String displayHome(){
        return "index";
    }

    @RequestMapping("/list")
    public @ModelAttribute("musics") List<Music> displayList() {
        List<Music> musics = musicRepository.list() ;
        return musics;
    }

    @GetMapping("/create-music")
    public String getMusic(@ModelAttribute CreateMusicForm createMusicForm) {
        return "create-music";
    }

    @PostMapping("/createMusic")
    public String createMusic (@Valid @ModelAttribute CreateMusicForm createMusicForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-music";
        }
        Music music = new Music();
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.add(music);
        return "index";
    }

        @GetMapping("/view-music/"+"{id}")
    public String displayMusic(@PathVariable("id") String number, Model model) {
        try {
            Music music = musicRepository.view(number);
            model.addAttribute("music", music);
            return "view-music";
        } catch (DataAccessException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/edit-music/"+"{id}")
    public String editMusic(@PathVariable("id") String number, Model model) {
        Music music = musicRepository.view(number);
        CreateMusicForm createMusicForm = new CreateMusicForm();
        createMusicForm.setTitle(music.getTitle());
        createMusicForm.setDescription(music.getDescription());
        model.addAttribute("createMusicForm", music);
        return "edit-music";
    }

    @PostMapping("/editMusic/{id}")
    public String updateMusic (@PathVariable("id") String number, @ModelAttribute CreateMusicForm createMusicForm) {
        Music music = musicRepository.view(number);
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.update(music);
        return "index";
    }
}